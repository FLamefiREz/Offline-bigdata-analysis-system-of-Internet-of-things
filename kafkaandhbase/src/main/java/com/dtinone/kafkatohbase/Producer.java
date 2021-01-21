package com.dtinone.kafkatohbase;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
   生产数据：字段为以下所有
 * id:设备ID号
 * type:类型
 * addr:地址
 * pressure(pa):压力
 * temperature(℃):温度
 * flow(m³/s):流量
 * datetime:采集时间
 * area:区域
 *

 * example(例如以下数据格式):
 * id,type,addr,pressure(pa),temperature(℃),flow(m³/s),time,area
 * 1001,gas,成都市双流区机场路,-10,300,1200,2018-03-21 06:36:30,双流区
 * 1002,water,成都市武侯区天府三街,5,200,1000,2019-10-15 17:36:32,武侯区
 * 1003,electric,成都市锦江区天府广场,30,100,100,2020-11-14 09:36:32,锦江区
 *
 */
public class Producer {
  public void MyProducer(){
        Properties prop = new Properties();
       // prop.setProperty("bootstrap.server","hadoop10:9092,hadoop11:9092,hadoop12:9092");
        prop.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"hadoop10:9092,hadoop11:9092,hadoop12:9092");
        prop.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        prop.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String,String> producer = new KafkaProducer<String,String>(prop);

            ProducerRecord<String,String> record1 = new ProducerRecord<String,String>("message","1001,gas,成都市双流区,30,300,1200,2018-03-21 06:36:32,双流区");
            ProducerRecord<String,String> record2 = new ProducerRecord<String,String>("message","1002,water,成都市武侯区天府三街,5,200,1000,2019-10-15 17:36:32,武侯区");
            ProducerRecord<String,String> record3 = new ProducerRecord<String,String>("message","1003,electric,成都市锦江区天府广场,30,100,100,2020-11-14 09:36:32,锦江区");
            ProducerRecord<String,String> record4 = new ProducerRecord<String,String>("message","1004,gas,成都市双流区,30,300,1200,2018-03-21 06:36:32,双流区");
            ProducerRecord<String,String> record5 = new ProducerRecord<String,String>("message","1005,water,成都市武侯区天府三街,5,200,1000,2019-10-15 17:36:32,武侯区");
            ProducerRecord<String,String> record6 = new ProducerRecord<String,String>("message","1006,electric,成都市锦江区天府广场,30,100,100,2020-11-14 09:36:32,锦江区");
            producer.send(record1);
            producer.send(record2);
            producer.send(record3);
            producer.send(record4);
            producer.send(record5);
            producer.send(record6);

        producer.flush();
        producer.close();
 }

}
