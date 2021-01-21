package com.dtinone.kafkatohbasetest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

public class KafkaConsumer extends Thread{

    private final ConsumerConnector consumer;
    private final String topic;

    public KafkaConsumer(String topic) {
        consumer = kafka.consumer.Consumer
                .createJavaConsumerConnector(createConsumerConfig());
        this.topic = topic;
    }

    private static ConsumerConfig createConsumerConfig() {
        Properties props = new Properties();
        //props.put("zookeeper.connect", KafkaProperties.zkConnect);
        //props.put("group.id", KafkaProperties.groupId1);
        props.put("zookeeper.session.timeout.ms", "40000");  //zookeeper 与 region server 的链接超时时间
        props.put("zookeeper.sync.time.ms", "200");
        props.put("auto.commit.interval.ms", "1000");   //props.put("auto.offset.reset", "smallest");//可以读取旧数据，默认不读取
        return new ConsumerConfig(props);
    }

    @Override
    public void run() {

        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(topic, new Integer(1));
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer
                .createMessageStreams(topicCountMap);
        KafkaStream<byte[], byte[]> stream = consumerMap.get(topic).get(0);
        ConsumerIterator<byte[], byte[]> it = stream.iterator();
        HBaseUtils hbase = new HBaseUtils();
        while (it.hasNext()) {  //相当于加了一把锁，一直返回true
//              System.out.println("3receive：" + it.next().message());
            try {
                System.out.println("11111");
                hbase.put(new String(it.next().message()));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

//            try {
//                sleep(300);    // 每条消息延迟300ms
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }

}
