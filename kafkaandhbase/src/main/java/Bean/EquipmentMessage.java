package Bean;

import java.util.Date;

/*
 *设备状态消息 Bean
 */

public class EquipmentMessage {
    private  int id;
    private  int equipmentType;
    private  String addr;
    private  double temperature;
    private  double pressure;
    private  double flow;
    private  Date time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return equipmentType;
    }

    public void setType(int equipmentType) {
        this.equipmentType = equipmentType;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getFlow() {
        return flow;
    }

    public void setFlow(double flow) {
        this.flow = flow;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
