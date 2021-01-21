package com.dtinone.earlywarningrules;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisStore {

    //redis 温度预警规则存储
    public void TemperatureWarnRuleStore(){
        JedisPoolConfig poolConfig = new JedisPoolConfig();

        JedisPool jedisPool = new JedisPool(poolConfig,"hadoop11",6379);
        System.out.println(jedisPool);
        Jedis jedis = jedisPool.getResource();

        //数据以hash的形式存储
        // 1级：-20~0°
        jedis.hset("TemperatureWarnRule","warnType1","gas");
        jedis.hset("TemperatureWarnRule","maxTemprature1","0");
        jedis.hset("TemperatureWarnRule","minTemprature1","-20");

        // 2级：40~-50°
        jedis.hset("TemperatureWarnRule","warnType2","gas");
        jedis.hset("TemperatureWarnRule","maxTemprature2","50");
        jedis.hset("TemperatureWarnRule","minTemprature2","40");

        //3级：51~60°(严重级别最高)
        jedis.hset("TemperatureWarnRule","warnType3","gas");
        jedis.hset("TemperatureWarnRule","maxTemprature3","60");
        jedis.hset("TemperatureWarnRule","minTemprature3","51");

        System.out.println("redis 温度预警规则存储成功！");

        if(null != jedisPool){
            jedisPool.close();
        }
    }

    //redis 压力预警规则存储
    public void PressureWarnRuleStore(){
        JedisPoolConfig poolConfig = new JedisPoolConfig();

        JedisPool jedisPool = new JedisPool(poolConfig,"hadoop11",6379);
        System.out.println(jedisPool);
        Jedis jedis = jedisPool.getResource();

        // 1级：100~150pa
        jedis.hset("PressureWarnRule","warnType1","gas");
        jedis.hset("PressureWarnRule","minPressure1","100");
        jedis.hset("PressureWarnRule","maxPressure1","150");

        // 2级：0~100pa
        jedis.hset("PressureWarnRule","warnType2","gas");
        jedis.hset("PressureWarnRule","minPressure2","0");
        jedis.hset("PressureWarnRule","maxPressure2","100");

        //3级：300~400pa（严重级别最高）
        jedis.hset("PressureWarnRule","warnType3","gas");
        jedis.hset("PressureWarnRule","minPressure3","300");
        jedis.hset("PressureWarnRule","maxPressure3","400");

        System.out.println("redis 压力预警规则存储成功！");

        if(null != jedisPool){
            jedisPool.close();
        }
    }

    //redis 流量预警规则存储
    public void FlowWarnRuleStore(){
        JedisPoolConfig poolConfig = new JedisPoolConfig();

        JedisPool jedisPool = new JedisPool(poolConfig,"hadoop11",6379);
        System.out.println(jedisPool);
        Jedis jedis = jedisPool.getResource();


        // 1级：1000~1200立方米/s
        jedis.hset("FlowWarnRule","warnType1","gas");
        jedis.hset("FlowWarnRule","minFlow1","1200");
        jedis.hset("FlowWarnRule","maxFlow1","1500");

        //  2级：0~300立方米/s
        jedis.hset("FlowWarnRule","warnType2","gas");
        jedis.hset("FlowWarnRule","minFlow2","0");
        jedis.hset("FlowWarnRule","maxFlow2","300");

        //3级：1200~1500立方米/s（严重级别最高）
        jedis.hset("FlowWarnRule","warnType3","gas");
        jedis.hset("FlowWarnRule","minFlow3","1000");
        jedis.hset("FlowWarnRule","maxFlow3","1200");

        System.out.println("redis 流量预警规则存储成功！");

        if(null != jedisPool){
            jedisPool.close();
        }
    }

    @Test
    //redis 电压预警规则存储
    public void test04(){
        JedisPoolConfig poolConfig = new JedisPoolConfig();

        JedisPool jedisPool = new JedisPool(poolConfig,"hadoop11",6379);
        System.out.println(jedisPool);
        Jedis jedis = jedisPool.getResource();


        // 1级：1000~1200立方米/s
        jedis.hset("FlowWarnRule","warnType1","electric");
        jedis.hset("FlowWarnRule","minFlow1","1200");
        jedis.hset("FlowWarnRule","maxFlow1","1500");

        //  2级：0~300立方米/s
        jedis.hset("FlowWarnRule","warnType2","electric");
        jedis.hset("FlowWarnRule","minFlow2","0");
        jedis.hset("FlowWarnRule","maxFlow2","300");

        //3级：1200~1500立方米/s（严重级别最高）
        jedis.hset("FlowWarnRule","warnType3","electric");
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
