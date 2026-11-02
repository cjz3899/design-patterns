package com.junzhecai.strategy.SimUDuck;

public class ModelDuck extends Duck{
    /**
     * 一开始不会飞
     */
    public ModelDuck() {
        flyBehavior = new FlyNoWay();
        quackBehavior = new MuteQuack();
    }

    @Override
    public void display() {
        System.out.println("I am a model duck");
    }
}
