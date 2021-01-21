package com.dtinone.bigdata;

/**
 正常范围：
 压力范围：0~400pa，温度范围-20~60°，流量范围0~1500 立方米/s
 预警规则
 rules：
 压力等级：
 (所有区间为左闭右开)
 1级：100~150pa
 2级：0~100pa
 3级：300~400pa（严重级别最高）
 level,type,pressure(scope max,min)
 */
public class Warning {
    public void Pressure(float pre) {
        if(pre<100||pre >=400){
            System.out.println("数据异常");
        } else if (pre >= 100 && pre < 150) {
            System.out.println("压力1级预警");
        } else if (pre >= 0 && pre < 100) {
            System.out.println("压力2级预警");
        } else if (pre >= 300 && pre < 400) {
            System.out.println("压力3级预警");
        }
    }

    /**
     * 温度等级：
     * 正常级别:0~40°(所有区间为左闭右开)
     * 1级：-10~0°
     * 2级：-20~-10°
     * 3级：40~60°
     * level,type,temperature(scope max,min)
     */
    public void Temperature(float tem){
        if(tem < -20 || tem > 60){
            System.out.println("数据异常");
        }else if(tem >= 0 && tem < 40){
            System.out.println("温度正常");
        } else if(tem >= -10 && tem < 0) {
            System.out.println("温度1级预警");
        }else if(tem >= -20 && tem < -10){
            System.out.println("温度2级预警");
        }else if(tem >= 40 && tem <60){
            System.out.println("温度3级预警");
        }
    }

/**
 流量等级：
 正常级别：300立方米/s~1200立方米/s
 1级：1200~1500立方米/s（严重级别最高）
 2级：0~300立方米/s
 3级：1000~1200立方米/s
 level,type,flow(scope max,min)
 */
public void Flow(float flo){
    if(flo < 0 || flo > 1500){
        System.out.println("数据异常");
    }else if(flo >= 300 && flo < 1200){
        System.out.println("流量正常");
    }else if((flo >= 200 && flo < 300)||(flo >= 1200 && flo < 1300)){
        System.out.println("流量1级预警");
    }else if((flo >= 100 && flo < 200)||(flo >= 1300 && flo < 1400)){
        System.out.println("流量2级预警");
    }else if((flo >=0 && flo < 100)||(flo >= 1400 && flo < 1500)){
        System.out.println("流量3级预警");
    }
  }

}

