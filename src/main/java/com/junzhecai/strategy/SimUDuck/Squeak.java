package com.junzhecai.strategy.SimUDuck;

public class Squeak implements QuackBehavior{
    @Override
    public void quack() {
        System.out.println("squeak");
    }
}
