package com.dtinone.earlywarningrules;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class Warning {
    JedisPoolConfig poolConfig = new JedisPoolConfig();
    JedisPool jedisPool = new JedisPool(poolConfig, "hadoop11", 6379);
    Jedis jedis = jedisPool.getResource();

    //从redis加载温度预警规则，并加以判断
    public void TemperatureWarning(int tem) {


        int a = Integer.parseInt(jedis.hget("TemperatureWarnRule", "minTemprature1"));
        int b = Integer.parseInt(jedis.hget("TemperatureWarnRule", "maxTemprature1"));

        int c = Integer.parseInt(jedis.hget("TemperatureWarnRule", "minTemprature2"));
        int d = Integer.parseInt(jedis.hget("TemperatureWarnRule", "maxTemprature2"));

        int e = Integer.parseInt(jedis.hget("TemperatureWarnRule", "minTemprature3"));
        int f = Integer.parseInt(jedis.hget("TemperatureWarnRule", "maxTemprature3"));


        //System.out.println(a+"\t"+b+"\t"+c+"\t"+d+"\t"+e+"\t"+f);

        //从redis中取出设定规则的数据，并且加以判断
        if (tem >= a && tem < b) {
            System.out.println("温度1级预警，指派人员到现场抢修");
        } else if (tem >= c && tem < d) {
            System.out.println("温度2级预警");
        } else if (tem >= e && tem < f) {
            System.out.println("温度3级预警");
        }

        if (null != jedisPool) {
            jedisPool.close();
        }

    }

    public void PressureWarning(int pre) {
        int a1 = Integer.parseInt(jedis.hget("PressureWarnRule", "minPressure1"));
        int b1 = Integer.parseInt(jedis.hget("PressureWarnRule", "maxPressure1"));

        int c1 = Integer.parseInt(jedis.hget("PressureWarnRule", "minPressure2"));
        int d1 = Integer.parseInt(jedis.hget("PressureWarnRule", "maxPressure2"));

        int e1 = Integer.parseInt(jedis.hget("PressureWarnRule", "minPressure3"));
        int f1 = Integer.parseInt(jedis.hget("PressureWarnRule", "maxPressure3"));


        //从redis中取出设定规则的数据，并且加以判断
        if (pre >= a1 && pre < b1) {
            System.out.println("压力1级预警，指派人员到现场抢修");
        } else if (pre >= c1 && pre < d1) {
            System.out.println("压力2级预警");
        } else if (pre >= e1 && pre < f1) {
            System.out.println("压力3级预警");
        }

        if (null != jedisPool) {
            jedisPool.close();
        }
    }

    public void FlowWarning(int flo) {
         int a2 = Integer.parseInt(jedis.hget("FlowWarnRule", "minFlow1"));
         int b2 = Integer.parseInt(jedis.hget("FlowWarnRule", "maxFlow1"));

         int c2 = Integer.parseInt(jedis.hget("FlowWarnRule", "minFlow2"));
         int d2 = Integer.parseInt(jedis.hget("FlowWarnRule", "maxFlow2"));

         int e2 = Integer.parseInt(jedis.hget("FlowWarnRule", "minFlow3"));
         int f2 = Integer.parseInt(jedis.hget("FlowWarnRule", "maxFlow3"));

        //从redis中取出设定规则的数据，并且加以判断
        if (flo >= a2 && flo < b2) {
            System.out.println("流量1级预警，指派人员到现场抢修");
        } else if (flo >= c2 && flo < d2) {
            System.out.println("流量2级预警");
        } else if (flo >= e2 && flo < f2) {
            System.out.println("流量3级预警");
        }

        if (null != jedisPool) {
            jedisPool.close();
        }
    }
}

