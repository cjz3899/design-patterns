package com.junzhecai.strategy.SimUDuck;

public class MiniDuckSimulator {
    public static void main(String[] args) {
        Duck mallard = new MallarDuck();
        mallard.performQuack();
        mallard.performFly();

        Duck model = new ModelDuck();
        model.performFly();
        /**
         * 动态改变行为
         */
        model.setFlyBehavior(new FlyRocketPowered());
        model.performFly();
    }
}
