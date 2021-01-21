package com.dtinone.kafkatohbasetest;

import java.io.IOException;
import java.util.Random;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

public class HBaseUtils {
    public  void put(String string) throws IOException {
        //设置HBase据库的连接配置参数
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum",  "hadoop10:2181,hadoop11:2181,hadoop12:2181"); // Zookeeper的地址
//          conf.set("hbase.zookeeper.property.clientPort", "42182");

        Random random = new Random();
        long a = random.nextInt(1000000000);
        String tableName = "emp";
        String rowkey = "rowkey"+a ;
        String columnFamily = "basicinfo";
        String column = "empname";
        //String value = string;
        HTable table=new HTable(conf, tableName);
        Put put=new Put(Bytes.toBytes(rowkey));
        put.add(Bytes.toBytes(columnFamily), Bytes.toBytes(column), Bytes.toBytes(string));table.put(put);//放入表
        System.out.println("放入成功");
        table.close();//释放资源
    }
}

