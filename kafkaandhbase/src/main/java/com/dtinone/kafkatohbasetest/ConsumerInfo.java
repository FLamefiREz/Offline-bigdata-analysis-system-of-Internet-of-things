package com.dtinone.kafkatohbasetest;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.io.IOException;
import java.util.*;

public class ConsumerInfo {
    public String Message(){
        String info1 = null;
        Properties prop = new Properties();
        prop.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "hadoop10:9092,hadoop11:9092,hadoop12:9092");
        prop.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        prop.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        prop.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        prop.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        prop.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "hadoop02");
        org.apache.kafka.clients.consumer.KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(prop);
        consumer.subscribe(Arrays.asList("Test02"));

        try {
            boolean isbool = true;
            while (isbool) {
                ConsumerRecords<String, String> records = consumer.poll(3000);

                for (ConsumerRecord<String, String> record : records) {
//                    System.out.println("topic== " + record.topic());
//                    System.out.println("partition== " + record.partition());
//                    System.out.println("offset== " + record.offset());
                    System.out.println("value== " + record.value());
                    info1 = record.value();
                    //consumer.commitAsync();//异步提交
                    //执行业务逻辑，然后入库成功才算消费成功。此时才提交offset
                }
            }
        } finally {
            //consumer.commitSync();//同步提交
            consumer.close();
        }

        return info1;
    }

    public  void ToHBase(String message) throws IOException {
        System.out.println("-------");
            // value= 600,0,2018-03-21 06:36:32,100
            //压力，类型，时间，设备ID号
            String[] split = message.split(",");
            String pressure = split[0];
            String type = split[1];
            String time = split[2];
            String id = split[3];
            String rowkey0 = split[3] + time.replaceAll("-", "").substring(0, 6);

        //设置HBase据库的连接配置参数
        Configuration conf = HBaseConfiguration.create();
       // conf.set("hbase.zookeeper.quorum",  "hadoop10:2181,hadoop11:2181,hadoop12:2181"); // Zookeeper的地址
       // conf.set("hbase.zookeeper.property.clientPort", "42182");

        conf.addResource("hdfs-site.xml");
        final Connection conn = ConnectionFactory.createConnection(conf);

        //String tableName = "emp";
        String rowkey = "rowkey" ;  //行
        String columnFamily = "basicinfo";  //列族名

 /*       已经过时
        HTable table=new HTable(conf, tableName);
        Put put=new Put(Bytes.toBytes(rowkey));
        put.add(Bytes.toBytes(columnFamily), Bytes.toBytes(column), Bytes.toBytes(string));table.put(put);//放入表
        System.out.println("放入成功");
        table.close();//释放资源
 */

        //创建Table客户端
        final TableName tableName = TableName.valueOf("info");  //表名
        final Table table = conn.getTable(tableName);
        List<Put> list = new ArrayList<Put>();
        //压力，类型，时间，设备ID号
        for (int i = 0; i < 1 ;i++) {
            //将一行的数据封装成Put对象
            final Put put = new Put(Bytes.toBytes(rowkey)); //rowkey
            put.addColumn(Bytes.toBytes(columnFamily),Bytes.toBytes("pressure"),Bytes.toBytes(pressure));
            put.addColumn(Bytes.toBytes(columnFamily),Bytes.toBytes("type"),Bytes.toBytes(type));
            put.addColumn(Bytes.toBytes(columnFamily),Bytes.toBytes("time"),Bytes.toBytes(time));
            put.addColumn(Bytes.toBytes(columnFamily),Bytes.toBytes("id"),Bytes.toBytes(id));
            list.add(put);
        }
        table.put(list);
        table.close();
    }
}
