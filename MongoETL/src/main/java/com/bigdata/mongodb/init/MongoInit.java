package com.bigdata.mongodb.init;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

/**
 * @program: project-parent
 * @description: 连接MongoDB的工具类
 * @author: 钟顺民
 **/
public class MongoInit {

    private String host;
    private int port;
    private String databaseName;

    ServerAddress address;
    MongoClient mongoClient;
    MongoDatabase database;

    /**
     * @Description:
     * @Param: []
     * @return:
     * @Author: 钟顺民
     */
    public MongoInit() {
    }

    /**
     * @Description:
     * @Param: [host, port, databaseName]
     * @return:
     * @Author: 钟顺民
     */
    public MongoInit(String host, int port, String databaseName) {


        this.host = host;
        this.port = port;
        this.databaseName = databaseName;
    }

    /**
     * @Description: 连接工具
     * @Param: [host, port, databaseName] host：连接的MongoDB主机IP，port：连接的MongoDB端口号，
     * databaseName：连接的MongoDB的数据库名
     * @return: com.mongodb.client.MongoDatabase
     * @Author: 钟顺民
     */
    public MongoDatabase init(String host,int port,String databaseName){

//        创建客户端连接
        address = new ServerAddress(host,port);
//        final MongoCredential credential = MongoCredential.createCredential("xxx", "project", "123456".toCharArray());
        mongoClient = new MongoClient(address);
//        获取表
        database = mongoClient.getDatabase(databaseName);
//        返回表
        return database;
    }

    /**
     * @Description: 关闭接口
     * @Param: []
     * @return: void
     * @Author: 钟顺民
     */
    public void close(){
        mongoClient.close();
    }

}
