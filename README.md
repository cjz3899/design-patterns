# design-patterns

Java 设计模式示例集合，基于《Head First 设计模式》和《重学Java设计模式》的学习实践。

## 环境要求

- JDK 21
- Maven
- Python 3（可选，仅运行天气数据爬虫时需要，只用标准库）

## 项目结构

```
src/main/java/com/junzhecai/
├── strategy/                # 策略模式
│   ├── README.md            # 策略模式详解
│   ├── SimUDuck/            # 鸭子示例（策略模式入门）
│   ├── assets/              # 配图
│   └── business/
│       ├── simple/          # 用 if-else 实现的优惠券计算
│       └── design/          # 策略模式重构版
│           └── event/       # 各优惠券策略实现
└── observer/                # 观察者模式
    ├── README.md            # 观察者模式详解
    ├── WeatherStation/      # 气象站示例（观察者模式入门，含 Python 天气爬虫）
    ├── assets/              # 配图
    └── business/
        ├── simple/          # 顺序调用的摇号结果通知
        └── design/          # 观察者模式重构版
            └── event/       # 事件管理器与监听器（MQ、短信）

src/test/java/com/junzhecai/
├── strategy/business/design/
│   └── ApiTest.java         # 策略模式测试类
└── observer/business/design/
    └── ApiTest.java         # 观察者模式测试类
```

## 快速开始

```bash
mvn test
```

## 设计模式

- [策略模式](src/main/java/com/junzhecai/strategy/README.md)
- [观察者模式](src/main/java/com/junzhecai/observer/README.md)

