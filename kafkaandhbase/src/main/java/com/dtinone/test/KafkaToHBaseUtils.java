package com.dtinone.test;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;

import java.io.IOException;


public class KafkaToHBaseUtils {
    public static Configuration configuration; // 管理Hbase的配置信息
    public static Connection connection; // 管理Hbase连接
    public static Admin admin; // 管理Hbase数据库的信息
    public  void ToHBase(String message) throws IOException {

 /**
  * * id:设备ID号
  * type:类型
  * addr:地址
  * pressure(pa):压力
  * temperature(℃):温度
  * flow(m³/s):流量
  * time:采集时间
  * area:区域
  * 例如：
        id,type,addr,pressure(pa),temperature(℃),flow(m³/s),time,area
        1001,gas,成都市双流区,30,300,1200,2018-03-21 06:36:32,双流区
  */
        String message1 = message;
        String[] split = message1.split(",");
        String id = split[0];
        String type = split[1];
        String addr = split[2];
        String pressure = split[3];
        String temperature = split[4];
        String flow = split[5];
        String time = split[6];
        String area = split[7];

        String[] time1 = time.split("\\s+");
        String time2 = time1[0].replace("-", "");
        String time3 = time1[1].replace(":", "");
        String rowkey0 = id + time2 + time3;//行键：设备id号+时间


        init(); //连接
        String colF[] ={"info"};
        createTable("detail",colF); // 建表 detail:水电气明细表
        insertData("detail",rowkey0,"info","id",id);
        insertData("detail",rowkey0,"info","type",type);
        insertData("detail",rowkey0,"info","addr",addr);
        insertData("detail",rowkey0,"info","pressure",pressure);
        insertData("detail",rowkey0,"info","temperature",temperature);
        insertData("detail",rowkey0,"info","flow",flow);
        insertData("detail",rowkey0,"info","time",time);
        insertData("detail",rowkey0,"info","area",area);

        System.out.println("将kafka的数据写入HBase成功！");
        //getData("detail",rowkey0,"info","id");
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