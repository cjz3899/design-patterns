import json
import urllib.request
from pathlib import Path

URL = (
    "https://api.open-meteo.com/v1/forecast"
    "?latitude=21.2719&longitude=110.3604"
    "&hourly=temperature_2m,relative_humidity_2m,surface_pressure"
    "&past_days=10&forecast_days=0"
    "&temperature_unit=celsius"
    "&timezone=Asia%2FShanghai"
)

# 与实时管道的 weather.jsonl 分开，一次性快照不会覆盖轮询产出
OUT = Path(__file__).parent / "weather_history.jsonl"


def fetch_history():
    with urllib.request.urlopen(URL, timeout=30) as res:
        hourly = json.load(res)["hourly"]

    rows = []
    # API 返回四个等长平行数组，需 zip 成逐行对象才能被按行读取的 Jackson 消费
    for t, temp, hum, pres in zip(
        hourly["time"],
        hourly["temperature_2m"],
        hourly["relative_humidity_2m"],
        hourly["surface_pressure"],
    ):
        # 缺测的 null 若落盘，Jackson 会静默映射成 0.0，产生假的冰点读数
        if None in (temp, hum, pres):
            continue
        rows.append({"time": t, "temperature": temp, "humidity": hum, "pressure": pres})
    return rows


def main():
    rows = fetch_history()
    # 快照语义用 w 覆盖：重复运行若追加，文件里会出现时间倒流的重复段
    with OUT.open("w", encoding="utf-8") as f:
        for row in rows:
            f.write(json.dumps(row, ensure_ascii=False) + "\n")
    print(f"wrote {len(rows)} rows -> {OUT}")


if __name__ == "__main__":
    main()