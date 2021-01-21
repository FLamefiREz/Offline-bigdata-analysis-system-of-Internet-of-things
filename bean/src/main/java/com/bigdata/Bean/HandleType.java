package com.bigdata.Bean;

/*
 *指令处理方式 Bean
 */

public class HandleType {
    private  int id;
    private  int equipmentId;
    private  int operateType;

    public HandleType(){}

    public HandleType(int id, int equipmentId, int operateType) {
        this.id = id;
        this.equipmentId = equipmentId;
        this.operateType = operateType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public int getOperateType() {
        return operateType;
    }

    public void setOperateType(int operateType) {
        this.operateType = operateType;
    }
}
