package com.bigdata.Bean;

/*
 *温度警报规则 Bean
 */

public class TemperatureWarnRule {

    private  int warnLevel;
    private  int warnType;
    private  int maxTemperature;
    private  int minTemperature;

    public TemperatureWarnRule() {

    }
    //全参数构造
    public TemperatureWarnRule(int warnLevel, int warnType, int maxTemperature, int minTemperature) {
        this.warnLevel = warnLevel;
        this.warnType = warnType;
        this.maxTemperature = maxTemperature;
        this.minTemperature = minTemperature;
    }

    public int getWarnLevel() {
        return warnLevel;
    }

    public void setWarnLevel(int warnLevel) {
        this.warnLevel = warnLevel;
    }

    public int getWarnType() {
        return warnType;
    }

    public void setWarnType(int warnType) {
        this.warnType = warnType;
    }

    public int getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(int maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public int getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(int minTemperature) {
        this.minTemperature = minTemperature;
    }
}
