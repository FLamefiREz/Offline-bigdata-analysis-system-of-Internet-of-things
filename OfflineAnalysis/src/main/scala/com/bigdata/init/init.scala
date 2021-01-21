package com.bigdata.init

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.client.Scan
import org.apache.hadoop.hbase.util.Bytes
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

class init {
  def readDataFromHBase(spark:SparkSession){
    val conf = new Configuration()
    val scan = new Scan()
    scan.addColumn(Bytes.toBytes(LogContant.columnFamily),Bytes.toBytes("city"))
    //    scan.addColumn(Bytes.toBytes(LogContant.columnFamily),Bytes.toBytes("browersName"))
    conf.set(TableInputFormat.SCAN,Base64.encodeBytes(ProtobufUtil.toScan(scan).toByteArray))
    conf.set(TableInputFormat.INPUT_TABLE,TableName.valueOf("accesslog_20190530").getNameAsString)
    conf.set("hbase.zookeeper.quorum", LogContant.zkQuorum)

    val hbaseRDD: RDD[(ImmutableBytesWritable, Result)] = spark.sparkContext.newAPIHadoopRDD(
      conf,
      classOf[TableInputFormat],
      classOf[ImmutableBytesWritable],
      classOf[Result]
    )
}
