package com.dtinone.earlywarningrules;

public class Test01 {
    public static void main(String[] args) {

        //往redis里面存储预警规则，只调用一次。
//        new RedisStore().TemperatureWarnRuleStore();
//        new RedisStore().PressureWarnRuleStore();
//        new RedisStore().FlowWarnRuleStore();

        final Warning warning = new Warning();
        warning.TemperatureWarning(-10);
        warning.PressureWarning(300);
        warning.FlowWarning(1200);
    }

}

