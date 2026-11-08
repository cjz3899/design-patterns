package com.junzhecai.observer.WeatherStation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/** 把外部 JSONL 适配成 Subject 能消费的读数，是数据进入观察者链的唯一入口。 */
public class JsonWeatherReader {

    // ObjectMapper 线程安全但构造昂贵（内部缓存反射与序列化器），全类共用一个实例
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // 路径由外部注入，测试时可指向临时文件，不必依赖爬虫真实产出
    private final Path dataFile;

    public JsonWeatherReader(Path dataFile) {
        this.dataFile = dataFile;
    }

    /** 取最后一条可解析的读数；取不到返回 empty，让轮询方跳过本轮而非中断。 */
    public Optional<WeatherReading> readLatest() {
        // 爬虫尚未产出第一行时文件不存在，属于正常启动顺序，不是错误
        if (!Files.exists(dataFile)) {
            return Optional.empty();
        }
        List<String> lines;
        try {
            // 每 15 分钟一行即每天 96 行，整体读入远比维护 RandomAccessFile 游标简单
            lines = Files.readAllLines(dataFile);
        } catch (IOException e) {
            return Optional.empty();
        }
        // 倒序扫描：末尾可能是爬虫正写入的半行，跳过它取上一条完整数据
        for (int i = lines.size() - 1; i >= 0; i--) {
            String line = lines.get(i).strip();
            if (line.isEmpty()) {
                continue;
            }
            try {
                // JSON key 与 record 组件同名，Jackson 原生映射，无需任何注解
                return Optional.of(MAPPER.readValue(line, WeatherReading.class));
            } catch (JsonProcessingException e) {
                // 脚本可能正追加到一半，退回上一条完整读数
            }
        }
        // 整个文件没有一条能解析，留给调用方下一轮重试
        return Optional.empty();
    }

    /** 按时间顺序取全部可解析读数，供历史回放遍历；坏行跳过而不中断整段回放。 */
    public List<WeatherReading> readAll() {
        if (!Files.exists(dataFile)) {
            return List.of();
        }
        List<String> lines;
        try {
            // 10 天逐小时共 240 行约 20 KB，全量读入无需游标
            lines = Files.readAllLines(dataFile);
        } catch (IOException e) {
            return List.of();
        }
        List<WeatherReading> readings = new ArrayList<>();
        for (String line : lines) {
            String trimmed = line.strip();
            if (trimmed.isEmpty()) {
                continue;
            }
            try {
                readings.add(MAPPER.readValue(trimmed, WeatherReading.class));
            } catch (JsonProcessingException e) {
                // 单行缺测或写坏不应让整段回放失败
            }
        }
        return readings;
    }
}
