package com.yao.Bean;

/*
 *预警规则 Bean
 */

public class warningRules {
    private  int warnLevel;
    private  int warnType;
    private  int maxPressure;
    private  int minPressure;
    private  int maxTemperature;
    private  int minTemperature;
    private int maxFlow;
    private int minFlow;

    public int getLevel() {
        return warnLevel;
    }

    public void setLevel(int warnLevel) {
        this.warnLevel = warnLevel;
    }

    public int getWarnType() {
        return warnType;
    }

    public void setWarnType(int warnType) {
        this.warnType = warnType;
    }

    public int getMaxPressure() {
        return maxPressure;
    }

    public void setMaxPressure(int maxPressure) {
        this.maxPressure = maxPressure;
    }

    public int getMinPressure() {
        return minPressure;
    }

    public void setMinPressure(int minPressure) {
        this.minPressure = minPressure;
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

    public int getMaxFlow() {
        return maxFlow;
    }

    public void setMaxFlow(int maxFlow) {
        this.maxFlow = maxFlow;
    }

    public int getMinFlow() {
        return minFlow;
    }

    public void setMinFlow(int minFlow) {
        this.minFlow = minFlow;
    }
}
