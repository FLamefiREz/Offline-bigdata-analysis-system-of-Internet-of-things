package com.bigdata.Bean;

import java.util.Date;
import java.util.GregorianCalendar;

/*
 *Hbase设备信息 Bean
 */

public class HbaseEquipmentInfo {
    private  int flag = -1;
    private  int id;
    //水 0 电 1 气 2
    private  int equipmentType;
    //城市
    private  String area;
    //详细地址
    private  String addr;

    private  int temperature;
    private  int temperatureLevel;

    private  int pressure;
    private  int pressureLevel;

    private  int flow;
    private  int flowLevel;

    private String time;

    public HbaseEquipmentInfo(){

    }

    public HbaseEquipmentInfo(int id, int equipmentType, String area, String addr, int temperature,
                              int temperatureLevel, int pressure, int pressureLevel, int flow, int flowLevel, String time)
    {
        this.id = id;
        this.equipmentType = equipmentType;
        this.area = area;
        this.addr = addr;
        this.temperature = temperature;
        this.temperatureLevel = temperatureLevel;
        this.pressure = pressure;
        this.pressureLevel = pressureLevel;
        this.flow = flow;
        this.flowLevel = flowLevel;
        this.time = time;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(int equipmentType) {
        this.equipmentType = equipmentType;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getTemperatureLevel() {
        return temperatureLevel;
    }

    public void setTemperatureLevel(int temperatureLevel) {
        this.temperatureLevel = temperatureLevel;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getPressureLevel() {
        return pressureLevel;
    }

    public void setPressureLevel(int pressureLevel) {
        this.pressureLevel = pressureLevel;
    }

    public int getFlow() {
        return flow;
    }

    public void setFlow(int flow) {
        this.flow = flow;
    }

    public int getFlowLevel() {
        return flowLevel;
    }

    public void setFlowLevel(int flowLevel) {
        this.flowLevel = flowLevel;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
