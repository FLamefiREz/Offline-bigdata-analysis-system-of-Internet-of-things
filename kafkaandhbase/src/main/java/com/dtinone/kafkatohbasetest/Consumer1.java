package com.dtinone.kafkatohbasetest;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Arrays;
import java.util.Properties;

public class Consumer1 {
    public static void main(String[] args) {
        Properties prop = new Properties();
        prop.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"hadoop10:9092,hadoop11:9092,hadoop12:9092");
        prop.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        prop.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        prop.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,"false");
        prop.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
        prop.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "hadoop");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String,String>(prop);
        consumer.subscribe(Arrays.asList("Test01"));
        
        try {
            boolean isbool = true;
            while (isbool){
                ConsumerRecords<String, String> records = consumer.poll(3000);

                for (ConsumerRecord<String, String> record : records) {
                    System.out.println("topic== " + record.topic());
                    System.out.println("partition== " + record.partition());
                    System.out.println("offset== " + record.offset());
                    System.out.println("value== " + record.value());

                    //consumer.commitAsync();//异步提交
                    //执行业务逻辑，然后入库成功才算消费成功。此时才提交offset
                }
            }
        } finally {
             //consumer.commitSync();//同步提交
              consumer.close();
        }
    }


}
