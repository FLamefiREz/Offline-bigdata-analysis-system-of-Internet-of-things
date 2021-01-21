package com.dtinone.bigdata;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;


public class ConsumerToHbase1 {
    static Configuration conf = null;

    static {
        conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "hadoop10,hadoop11,hadoop12");
        conf.set("hbase.zookeeper.property.clientport", "2181");
    }

    public static void main(String[] args) throws IOException {

        Properties props = new Properties();
        // 定义kakfa 服务的地址，不需要将所有broker指定上
        props.put("bootstrap.servers", "hadoop10:9092");
        //配置从头消费数据
        // props.put("auto.offset.reset","smallest");
        // 制定consumer group
        props.put("group.id", "ttrt");
        // 是否自动确认offset
        props.put("enable.auto.commit", "true");
        // 自动确认offset的时间间隔
        props.put("auto.commit.interval.ms", "1000");
        // key的序列化类
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        // value的序列化类
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        // 定义consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);

        // 消费者订阅的topic, 可同时订阅多个
        //  consumer.subscribe(Arrays.asList("first", "second","third"));
        consumer.subscribe(Arrays.asList("java1"));
        HBaseAdmin hbaseAdmin = new HBaseAdmin(conf);

        while (true) {
            // 读取数据，读取超时时间为100ms

            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                //Connection connection = ConnectionFactory.createConnection(conf);
                //    Table table = connection.getTable(TableName.valueOf("testtable"));

                HTable table = new HTable(conf, "testtable");
                // value= 600,0,2018-03-21 06:36:32,100
                //压力，类型，时间，设备ID号
                String[] split = record.value().split(",");
                String pressure = split[0];
                String type = split[1];
                String time = split[2];
                String id = split[3];
                String rowkey0 = split[0].substring(7) + time.replaceAll("-", "").substring(0, 6);
                int i = rowkey0.hashCode() % 6;
                String rowkey = i + "_" + rowkey0;
                Put put = new Put(Bytes.toBytes(rowkey0));
                put.add(Bytes.toBytes("info"), Bytes.toBytes("pressure"), Bytes.toBytes(pressure));
                put.add(Bytes.toBytes("info"), Bytes.toBytes("type"), Bytes.toBytes(type));
                put.add(Bytes.toBytes("info"), Bytes.toBytes("time"), Bytes.toBytes(time));
                put.add(Bytes.toBytes("info"), Bytes.toBytes("id"), Bytes.toBytes(id));
                table.put(put);
                table.close();

                // System.out.printf("offset = %d, key = %s, value = %s%n", put);
            }
        }
    }
}
