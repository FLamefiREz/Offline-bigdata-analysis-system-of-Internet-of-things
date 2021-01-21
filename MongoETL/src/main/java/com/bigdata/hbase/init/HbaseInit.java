package com.bigdata.hbase.init;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;

/**
 * @program: project-parent
 * @description: 连接Hbase的工具类
 * @author: 钟顺民
 **/
public class HbaseInit {

    private String confName;
    private String tableName;

    Table table;
    Connection conn;

    public HbaseInit() { }


    /**
     * @Description: 连接方法
     * @Param: [confName, tableName] confName：传入的配置文件名；tableName：数据来源的表名。
     * @return: org.apache.hadoop.hbase.client.Table
     * @Author: 钟顺民
     */
    public Table init(String confName, String tableName) throws Exception {

        Configuration conf = HBaseConfiguration.create();
        conf.addResource(confName);
        conn = ConnectionFactory.createConnection(conf);
        TableName tableName1 = TableName.valueOf("txx");
        table= conn.getTable(tableName1);
        return table;
    }


    /**
     * @Description: 关闭接口
     * @Param: []
     * @return: void
     * @Author: 钟顺民
     */
    public void close() throws Exception {

        table.close();
        conn.close();
    }
}
