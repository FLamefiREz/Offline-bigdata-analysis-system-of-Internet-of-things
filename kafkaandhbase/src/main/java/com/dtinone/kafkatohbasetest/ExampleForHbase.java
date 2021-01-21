package com.dtinone.kafkatohbasetest;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import java.io.IOException;


public class ExampleForHbase {
    public static Configuration configuration; // 管理Hbase的配置信息
    public static Connection connection; // 管理Hbase连接
    public static Admin admin; // 管理Hbase数据库的信息
    public static void main(String[] args) throws IOException {
        init(); //连接
        String colF[] ={"score"};
        createTable("student",colF); // 建表
        insertData("student","zhangsan","score","English","69");
        insertData("student","zhangsan","score","Math","86");
        insertData("student","zhangsan","score","Computer","77");
        getData("student","zhangsan","score","Computer");
    }

    // 操作数据库之前,建立连接
    public static void init(){
        configuration = HBaseConfiguration.create();
        configuration.set("hbase.rootdir","hdfs://hadoop10:9000/hbase");
        configuration.set("hbase.zookeeper.quorum","hadoop10,hadoop11,hadoop12"); // 设置zookeeper节点
        configuration.set("hbase.zookeeper.property.clientPort","2181"); // 设置zookeeper节点
        try{
            connection = ConnectionFactory.createConnection(configuration);
            admin = connection.getAdmin();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    // 创建表
    /*
     * @param myTableName 表名
     * @param colFamily 列族数组
     * @throws Exception
     * */
    public static void createTable(String myTableName,String[] colFamily) throws IOException{
        TableName tableName = TableName.valueOf(myTableName);
        if(admin.tableExists(tableName)){
            System.out.println("Table exists");
        }else {
            HTableDescriptor hTableDescriptor = new HTableDescriptor(tableName);
            for(String str:colFamily){
                HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(str);
                hTableDescriptor.addFamily(hColumnDescriptor);
            }
            admin.createTable(hTableDescriptor);
        }
    }


    // 添加单元格数据
    /*
     * @param tableName 表名
     * @param rowKey 行键
     * @param colFamily 列族
     * @param col 列限定符
     * @param val 数据
     * @thorws Exception
     * */
    public static void insertData(String tableName,String rowKey,String colFamily,String col,String val) throws IOException{
        Table table = connection.getTable(TableName.valueOf(tableName));
        Put put = new Put(rowKey.getBytes());
        put.addColumn(colFamily.getBytes(),col.getBytes(),val.getBytes());
        table.put(put);
        table.close();
    }

    //浏览数据
    /*
     * @param tableName 表名
     * @param rowKey 行
     * @param colFamily 列族
     * @param col 列限定符
     * @throw IOException
     * */
    public static void getData(String tableName,String rowKey,String colFamily,String col) throws IOException{
        Table table = connection.getTable(TableName.valueOf(tableName));
        Get get = new Get(rowKey.getBytes());
        get.addColumn(colFamily.getBytes(),col.getBytes());
        Result result =table.get(get);
        System.out.println(new String(result.getValue(colFamily.getBytes(),col==null?null:col.getBytes())));
        table.close();
    }



    // 操作数据库之后，关闭连接
    public static void close(){
        try{
            if(admin!=null){
                admin.close(); // 退出用户
            }
            if(null != connection){
                connection.close(); // 关闭连接
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}