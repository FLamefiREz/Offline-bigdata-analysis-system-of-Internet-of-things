package com.bigdata.ETL;

import com.bigdata.hbase.init.CleanInit;
import com.bigdata.hbase.init.HbaseInit;
import com.bigdata.mongodb.data.getDataFromMongo;
import com.bigdata.mongodb.init.MongoInit;
import com.bigdata.Bean.HbaseEquipmentInfo;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.bson.Document;


import java.io.InputStream;
import java.util.Properties;

/**
 * @program: project-parent
 * @description: 数据清洗
 * @author: 钟顺民
 **/
public class DataETL {

    public static void main(String[] args) throws Exception {
//        获取mongoDB登录信息配置文件
        InputStream mongoIn = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream("mongoDB.properties");

//        获取mongoDB登录配置
        Properties mongoProp = new Properties();
        mongoProp.load(mongoIn);
        String host = mongoProp.getProperty("host");
        String port = mongoProp.getProperty("port");
        String databasesName = mongoProp.getProperty("databasesName");
        String collection = mongoProp.getProperty("collection");

        mongoIn.close();

//        加载mongoDB客户端及要用到的表
        MongoInit mongoInit = new MongoInit();
        MongoDatabase init = mongoInit.init(host, Integer.parseInt(port), databasesName);
        MongoCollection<Document> mongoCollection = init.getCollection(collection);

//        读取mongoDB中的清洗规则
        getDataFromMongo dataFromMongo = new getDataFromMongo();
        String minTemperature = dataFromMongo.getMinTemperature(mongoCollection);
        String maxTemperature = dataFromMongo.getMaxTemperature(mongoCollection);
        String minFlow = dataFromMongo.getMinFlow(mongoCollection);
        String maxFlow = dataFromMongo.getMaxFlow(mongoCollection);
        String minPressure = dataFromMongo.getMinPressure(mongoCollection);
        String maxPressure = dataFromMongo.getMaxPressure(mongoCollection);

//        读取hbase登录信息配置文件
        InputStream hbaseInfoIn = Thread
                .currentThread()
                .getContextClassLoader()
                .getResourceAsStream("hbaseInfo.properties");
//        获取hbase登录配置
        Properties hbaseProp = new Properties();
        hbaseProp.load(hbaseInfoIn);
        String infoConfName = hbaseProp.getProperty("confName");
        String infoTableName = hbaseProp.getProperty("tableName");

        //        读取hbase登录信息配置文件
        InputStream hbaseDataIn = Thread
                .currentThread()
                .getContextClassLoader()
                .getResourceAsStream("hbaseData.properties");
//        获取hbase登录配置
        Properties hbaseDataProp = new Properties();
        hbaseProp.load(hbaseDataIn);
        String dataconfName = hbaseProp.getProperty("confName");
        String dataTableName = hbaseProp.getProperty("tableName");
        hbaseDataIn.close();

//        加载hbase客户端
        HbaseInit hbaseInit = new HbaseInit();
        Table table = hbaseInit.init(infoConfName, infoTableName);
        Table data = hbaseInit.init(dataconfName, dataTableName);
//        获取hbase中的info表的数据
        Scan scan = new Scan();
        scan.addFamily(Bytes.toBytes("info"));
        ResultScanner results = table.getScanner(scan);

//        通过row迭代获取hbase中每个rowkey中的数据信息
        for (Result result : results) {
            byte[] row = result.getRow();

//            获取ID
            String id = CleanInit.getHbaseID(table, row);

//            获取类型
            String type = CleanInit.getHbaseType(table, row);

//            获取表地址
            String addr = CleanInit.getHbaseAddr(table, row);

//            获取压力
            String pressure = CleanInit.getHbasePressure(table, row);

//            获取温度
            String temperature = CleanInit.getHbaseTemperature(table, row);

//            获取流速
            String flow = CleanInit.getHbaseFlow(table, row);

//            获取时间string
            String time = CleanInit.getHbaseTime(table, row);

//            获取地区
            String area = CleanInit.getHbaseArea(table, row);

//            获取压力报警等级
            String preLevel = CleanInit.getHbasePreLevel(table, row);

//            获取温度报警等级
            String temLevel = CleanInit.getHbaseTemLevel(table, row);

//            获取流速报警等级
            String floLevel = CleanInit.getHbaseFloLevel(table, row);

//            获取状态值
            String state = CleanInit.getHbaseState(table, row);

//            字段是否都存在
            Boolean hbaseAllExist = CleanInit.getHbaseAllExist(table, row);

//            判断流量、压强、温度是否是正确范围内的字段
            Boolean flowFlag = (Integer.parseInt(flow) < Integer.parseInt(maxFlow)) &
                    (Integer.parseInt(minFlow) < Integer.parseInt(flow));
            Boolean pressureFlag = (Integer.parseInt(pressure) < Integer.parseInt(maxPressure)) &
                    (Integer.parseInt(minPressure) < Integer.parseInt(pressure));
            Boolean temperatureFlag = (Integer.parseInt(temperature) < Integer.parseInt(maxTemperature)) &
                    (Integer.parseInt(minTemperature) < Integer.parseInt(temperature));
            Boolean flag = flowFlag & pressureFlag & temperatureFlag;

            Put put = new Put(row);
//            如果没有误报，则封装并放进mongoDB中
            if ((state != "1") & hbaseAllExist & flag) {
//                将数值封装进对象
                HbaseEquipmentInfo info = new HbaseEquipmentInfo();
                info.setId(Integer.parseInt(id));
                info.setEquipmentType(Integer.parseInt(type));
                info.setAddr(addr);
                info.setPressure(Integer.parseInt(pressure));
                info.setTemperature(Integer.parseInt(temperature));
                info.setFlow(Integer.parseInt(flow));
                info.setTime(time);
                info.setArea(area);
                info.setPressureLevel(Integer.parseInt(preLevel));
                info.setTemperatureLevel(Integer.parseInt(temLevel));
                info.setFlowLevel(Integer.parseInt(floLevel));
                info.setFlag(Integer.parseInt(state));

//                数据添加进hbase中
                put.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("ID"),Bytes.toBytes(id));
                put.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("TYPE"),Bytes.toBytes(type));
                put.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("ADDR"),Bytes.toBytes(addr));
                put.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("PRESSURE"),Bytes.toBytes(pressure));
                put.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("TEMPERATURE"),Bytes.toBytes(temperature));
                put.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("FLOW"),Bytes.toBytes(flow));
                put.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("TIME"),Bytes.toBytes(time));
                put.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("AREA"),Bytes.toBytes(area));
                put.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("PRESSURE_LEVEL"),Bytes.toBytes(preLevel));
                put.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("TEMPERATURE_LEVEL"),Bytes.toBytes(temLevel));
                put.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("FLOW_LEVEL"),Bytes.toBytes(floLevel));
                data.put(put);
            }
            //            关闭客户端
            mongoInit.close();
            hbaseInit.close();
        }
    }
}
