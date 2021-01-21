package com.bigdata.mongodb.data;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;


/**
 * @program: project-parent
 * @description: 从MongoDB中获取清洗规则
 * @author: 钟顺民
 **/
public class getDataFromMongo {

    /**
     * @Description: 查询最小温度
     * @Param: [mongoCollection]
     * @return: java.lang.String
     * @Author: 钟顺民
     */
    public String getMinTemperature(MongoCollection<Document> mongoCollection){
        FindIterable<Document> documents = mongoCollection.find();
        String minTemperature=null;
        for (Document document : documents) {
            minTemperature = document.get("min_temperature").toString();
        }
        return minTemperature;
    }


    /**
     * @Description: 查询最大温度
     * @Param: [mongoCollection]
     * @return: java.lang.String
     * @Author: 钟顺民
     */
    public String getMaxTemperature(MongoCollection<Document> mongoCollection){
        FindIterable<Document> documents = mongoCollection.find();
        String maxTemperature=null;
        for (Document document : documents) {
            maxTemperature = document.get("max_temperature").toString();
        }
        return maxTemperature;
    }


    /**
     * @Description: 查询最小流量
     * @Param: [mongoCollection]
     * @return: java.lang.String
     * @Author: 钟顺民
     */
    public String getMinFlow(MongoCollection<Document> mongoCollection){
        FindIterable<Document> documents = mongoCollection.find();
        String minFlow=null;
        for (Document document : documents) {
            minFlow = document.get("min_Flow").toString();
        }
        return minFlow;
    }


    /**
     * @Description: 查询最大流量
     * @Param: [mongoCollection]
     * @return: java.lang.String
     * @Author: 钟顺民
     */
    public String getMaxFlow(MongoCollection<Document> mongoCollection){
        FindIterable<Document> documents = mongoCollection.find();
        String maxFlow=null;
        for (Document document : documents) {
            maxFlow = document.get("max_Flow").toString();
        }
        return maxFlow;
    }


    /**
     * @Description: 查询最小压强
     * @Param: [mongoCollection]
     * @return: java.lang.String
     * @Author: 钟顺民
     */
    public String getMinPressure(MongoCollection<Document> mongoCollection){
        FindIterable<Document> documents = mongoCollection.find();
        String minPressure=null;
        for (Document document : documents) {
            minPressure = document.get("min_Pressure").toString();
        }
        return minPressure;
    }


    /**
     * @Description: 查询最大压强
     * @Param: [mongoCollection]
     * @return: java.lang.String
     * @Author: 钟顺民
     */
    public String getMaxPressure(MongoCollection<Document> mongoCollection){
        FindIterable<Document> documents = mongoCollection.find();
        String maxPressure=null;
        for (Document document : documents) {
            maxPressure = document.get("max_Pressure").toString();
        }
        return maxPressure;
    }
}
