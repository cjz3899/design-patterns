# design-patterns

Java 设计模式示例集合，基于《Head First 设计模式》和《重学Java设计模式》的学习实践。

## 环境要求

- JDK 21
- Maven

## 项目结构

```
src/main/java/com/junzhecai/strategy/
├── README.md              # 策略模式详解
├── SimUDuck/              # 鸭子示例（策略模式入门）
├── assets/                # 配图
└── business/
    ├── simple/            # 用 if-else 实现的优惠券计算
    └── design/            # 策略模式重构版
        └── event/         # 各优惠券策略实现

src/test/java/com/junzhecai/strategy/business/design/
└── ApiTest.java           # 策略模式测试类
```

## 快速开始

```bash
mvn test
```

## 设计模式

- [策略模式](src/main/java/com/junzhecai/strategy/README.md)（进行中）

