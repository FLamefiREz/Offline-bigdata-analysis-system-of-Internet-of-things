package com.dtinone.kafkatohbasetest;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
/**
 * equipment:设备
 * id:设备ID号
 * type:类型
 * addr:地址
 * pressure(pa):压力
 * temperature(℃):温度
 * flow(m³/s):流量
 * datetime:采集时间
 * area:区域
 * // value= 13690470615,18122332823,2018-03-21 06:36:32,0578
 * // value= 600,0,2018-03-21 06:36:32,100
 * //温度，类型，时间，设备ID号
 */
public class Producer1 {
  public void myproducer(){
        Properties prop = new Properties();
       // prop.setProperty("bootstrap.server","hadoop10:9092,hadoop11:9092,hadoop12:9092");
        prop.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"hadoop10:9092,hadoop11:9092,hadoop12:9092");
        prop.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        prop.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String,String> producer = new KafkaProducer<String,String>(prop);
        for(int i = 1;i < 2;i++){
            ProducerRecord<String,String> record = new ProducerRecord<String,String>("Test02","600,0,2018-03-21 06:36:32,100"+i);
            producer.send(record);
        }
        producer.flush();
        producer.close();
 }
}
