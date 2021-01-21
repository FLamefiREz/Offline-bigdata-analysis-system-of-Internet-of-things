package com.dtinone.kafkatohbasetest;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisStore1 {
    @Test
    //redis 温度存储预警规则
    public void test01(){
        JedisPoolConfig poolConfig = new JedisPoolConfig();

        JedisPool jedisPool = new JedisPool(poolConfig,"hadoop11",6379);
        System.out.println(jedisPool);
        Jedis jedis = jedisPool.getResource();

        //数据以hash的形式存储
        // 1级：-20~0°
        jedis.hset("TemperatureWarnRule","warnType1","0");
        jedis.hset("TemperatureWarnRule","maxTemprature1","0");
        jedis.hset("TemperatureWarnRule","minTemprature1","-20");

        // 2级：40~-50°
        jedis.hset("TemperatureWarnRule","warnType2","0");
        jedis.hset("TemperatureWarnRule","maxTemprature2","50");
        jedis.hset("TemperatureWarnRule","minTemprature2","40");

        //3级：51~60°(严重级别最高)
        jedis.hset("TemperatureWarnRule","warnType3","0");
        jedis.hset("TemperatureWarnRule","maxTemprature3","60");
        jedis.hset("TemperatureWarnRule","minTemprature3","51");



        final int a = Integer.parseInt(jedis.hget("TemperatureWarnRule", "minTemprature1"));
        final int b = Integer.parseInt(jedis.hget("TemperatureWarnRule","maxTemprature1"));

        final int c = Integer.parseInt(jedis.hget("TemperatureWarnRule", "minTemprature2"));
        final int d = Integer.parseInt(jedis.hget("TemperatureWarnRule", "maxTemprature2"));

        final int e = Integer.parseInt(jedis.hget("TemperatureWarnRule", "minTemprature3"));
        final int f = Integer.parseInt(jedis.hget("TemperatureWarnRule", "maxTemprature3"));

         int tem = 55;
        //System.out.println(a+"\t"+b+"\t"+c+"\t"+d+"\t"+e+"\t"+f);

        //从redis中取出设定规则的数据，并且加以判断
       if(tem >= a && tem < b) {
            System.out.println("温度1级预警");
        }else if(tem >= c && tem < d){
            System.out.println("温度2级预警");
        }else if(tem >= e && tem < f){
            System.out.println("温度3级预警");
        }

        if(null != jedisPool){
        jedisPool.close();
        }
    }

    @Test
    //redis 压力存储预警规则
    public void test02(){
        JedisPoolConfig poolConfig = new JedisPoolConfig();

        JedisPool jedisPool = new JedisPool(poolConfig,"hadoop11",6379);
        System.out.println(jedisPool);
        Jedis jedis = jedisPool.getResource();

        // 1级：100~150pa
        jedis.hset("PressureWarnRule","warnType1","0");
        jedis.hset("PressureWarnRule","minPressure1","100");
        jedis.hset("PressureWarnRule","maxPressure1","150");

        // 2级：0~100pa
        jedis.hset("PressureWarnRule","warnType2","0");
        jedis.hset("PressureWarnRule","minPressure2","0");
        jedis.hset("PressureWarnRule","maxPressure2","100");

        //3级：300~400pa（严重级别最高）
        jedis.hset("PressureWarnRule","warnType3","0");
        jedis.hset("PressureWarnRule","minPressure3","300");
        jedis.hset("PressureWarnRule","maxPressure3","400");


        final int a1 = Integer.parseInt(jedis.hget("PressureWarnRule", "minPressure1"));
        final int b1 = Integer.parseInt(jedis.hget("PressureWarnRule","maxPressure1"));

        final int c1 = Integer.parseInt(jedis.hget("PressureWarnRule", "minPressure2"));
        final int d1 = Integer.parseInt(jedis.hget("PressureWarnRule", "maxPressure2"));

        final int e1 = Integer.parseInt(jedis.hget("PressureWarnRule", "minPressure3"));
        final int f1 = Integer.parseInt(jedis.hget("PressureWarnRule", "maxPressure3"));


        float pre = 300;

        //从redis中取出设定规则的数据，并且加以判断
        if(pre >= a1 && pre < b1) {
            System.out.println("压力1级预警");
        }else if(pre >= c1 && pre < d1){
            System.out.println("压力2级预警");
        }else if(pre >= e1 && pre < f1){
            System.out.println("压力3级预警");
        }

        if(null != jedisPool){
            jedisPool.close();
        }
    }

    @Test
    //redis 流量存储预警规则
    public void test03(){
        JedisPoolConfig poolConfig = new JedisPoolConfig();

        JedisPool jedisPool = new JedisPool(poolConfig,"hadoop11",6379);
        System.out.println(jedisPool);
        Jedis jedis = jedisPool.getResource();


        // 1级：1000~1200立方米/s
        jedis.hset("FlowWarnRule","warnType1","0");
        jedis.hset("FlowWarnRule","minFlow1","1200");
        jedis.hset("FlowWarnRule","maxFlow1","1500");

        //  2级：0~300立方米/s
        jedis.hset("FlowWarnRule","warnType2","0");
        jedis.hset("FlowWarnRule","minFlow2","0");
        jedis.hset("FlowWarnRule","maxFlow2","300");

        //3级：1200~1500立方米/s（严重级别最高）
        jedis.hset("FlowWarnRule","warnType3","0");
        jedis.hset("FlowWarnRule","minFlow3","1000");
        jedis.hset("FlowWarnRule","maxFlow3","1200");


        final int a2 = Integer.parseInt(jedis.hget("FlowWarnRule", "minFlow1"));
        final int b2 = Integer.parseInt(jedis.hget("FlowWarnRule","maxFlow1"));

        final int c2 = Integer.parseInt(jedis.hget("FlowWarnRule", "minFlow2"));
        final int d2 = Integer.parseInt(jedis.hget("FlowWarnRule", "maxFlow2"));

        final int e2 = Integer.parseInt(jedis.hget("FlowWarnRule", "minFlow3"));
        final int f2 = Integer.parseInt(jedis.hget("FlowWarnRule", "maxFlow3"));

        int flo = 1100;

        //从redis中取出设定规则的数据，并且加以判断
        if(flo >= a2 && flo < b2) {
            System.out.println("流量1级预警");
        }else if(flo >= c2 && flo < d2){
            System.out.println("流量2级预警");
        }else if(flo >= e2 && flo < f2){
            System.out.println("流量3级预警");
        }

        if(null != jedisPool){
            jedisPool.close();
        }
    }


    @Test
    //redis 电压存储预警规则
    public void test04(){
        JedisPoolConfig poolConfig = new JedisPoolConfig();

        JedisPool jedisPool = new JedisPool(poolConfig,"hadoop11",6379);
        System.out.println(jedisPool);
        Jedis jedis = jedisPool.getResource();


        // 1级：1000~1200立方米/s
        jedis.hset("FlowWarnRule","warnType1","0");
        jedis.hset("FlowWarnRule","minFlow1","1200");
        jedis.hset("FlowWarnRule","maxFlow1","1500");

        //  2级：0~300立方米/s
        jedis.hset("FlowWarnRule","warnType2","0");
        jedis.hset("FlowWarnRule","minFlow2","0");
        jedis.hset("FlowWarnRule","maxFlow2","300");

        //3级：1200~1500立方米/s（严重级别最高）
        jedis.hset("FlowWarnRule","warnType3","0");
        jedis.hset("FlowWarnRule","minFlow3","1000");
        jedis.hset("FlowWarnRule","maxFlow3","1200");


        final int a2 = Integer.parseInt(jedis.hget("FlowWarnRule", "minFlow1"));
        final int b2 = Integer.parseInt(jedis.hget("FlowWarnRule","maxFlow1"));

        final int c2 = Integer.parseInt(jedis.hget("FlowWarnRule", "minFlow2"));
        final int d2 = Integer.parseInt(jedis.hget("FlowWarnRule", "maxFlow2"));

        final int e2 = Integer.parseInt(jedis.hget("FlowWarnRule", "minFlow3"));
        final int f2 = Integer.parseInt(jedis.hget("FlowWarnRule", "maxFlow3"));

        int flo = 1100;

        //从redis中取出设定规则的数据，并且加以判断
        if(flo >= a2 && flo < b2) {
            System.out.println("流量1级预警");
        }else if(flo >= c2 && flo < d2){
            System.out.println("流量2级预警");
        }else if(flo >= e2 && flo < f2){
            System.out.println("流量3级预警");
        }

        if(null != jedisPool){
            jedisPool.close();
        }


    }



}
