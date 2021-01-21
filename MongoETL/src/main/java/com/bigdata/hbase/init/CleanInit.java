package com.bigdata.hbase.init;

import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.List;

/**
 * @program: project-parent
 * @description: 从Hbase导出的数据工具类
 * @author: 钟顺民
 **/
public class CleanInit {


    /**
     * @Description: 获取设备ID
     * @Param: [table, row]
     * @return: java.lang.String ：ID
     * @Author: 钟顺民
     */
    public static String getHbaseID(Table table,byte[] row) throws IOException {
        Get getID = new Get(row);
        getID.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("id"));
        Result resultID = table.get(getID);
        List<Cell> cells = resultID.listCells();
        String s = null;
        for (Cell cell : cells) {
            s = Bytes.toString(CellUtil.cloneValue(cell));
        }
        return s;
    }


    /**
     * @Description: 检验ID字段是否存在
     * @Param: [table, row]
     * @return: java.lang.Boolean
     * @Author: 钟顺民
     */
    public static Boolean getHbaseIDExist(Table table,byte[] row) throws IOException {
         Get getID = new Get(row);
        getID.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("id"));
        Result resultID = table.get(getID);
        Boolean hbaseIDExists = resultID.getExists();
        return hbaseIDExists;
    }


    /**
     * @Description: 获取表类型
     * @Param: [table, row]
     * @return: java.lang.String ：type
     * @Author: 钟顺民
     */
    public static String getHbaseType(Table table,byte[] row) throws IOException {
        Get getHbaseType = new Get(row);
        getHbaseType.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("type"));
        Result resultID = table.get(getHbaseType);
        List<Cell> cells = resultID.listCells();
        String s = null;
        for (Cell cell : cells) {
            s = Bytes.toString(CellUtil.cloneValue(cell));
        }
        return s;
    }


    /**
     * @Description: 检验Type字段是否存在
     * @Param: [table, row]
     * @return: java.lang.Boolean
     * @Author: 钟顺民
     */
    public static Boolean getHbaseTypeExist(Table table,byte[] row) throws IOException {
        Get getHbaseType = new Get(row);
        getHbaseType.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("type"));
        Result resultType = table.get(getHbaseType);
        Boolean hbaseTypeExists = resultType.getExists();
        return hbaseTypeExists;
    }


    /**
     * @Description: 获取地址
     * @Param: [table, row]
     * @return: java.lang.String ：addr
     * @Author: 钟顺民
     */
    public static String getHbaseAddr(Table table,byte[] row) throws IOException {
        Get getHbaseAddr = new Get(row);
        getHbaseAddr.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("addr"));
        Result resultAddr = table.get(getHbaseAddr);
        List<Cell> cells = resultAddr.listCells();
        String s = null;
        for (Cell cell : cells) {
            s = Bytes.toString(CellUtil.cloneValue(cell));
        }
        return s;
    }


    /**
     * @Description: 检验Addr字段是否存在
     * @Param: [table, row]
     * @return: java.lang.Boolean
     * @Author: 钟顺民
     */
    public static Boolean getHbaseAddrExist(Table table,byte[] row) throws IOException {
        Get getHbaseAddr = new Get(row);
        getHbaseAddr.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("addr"));
        Result resultAddr = table.get(getHbaseAddr);
        Boolean hbaseAddrExists = resultAddr.getExists();
        return hbaseAddrExists;
    }


    /**
     * @Description: 获取压力信息
     * @Param: [table, row]
     * @return: java.lang.String ：pressure
     * @Author: 钟顺民
     */
    public static String getHbasePressure(Table table,byte[] row) throws IOException {
        Get getHbasePressure = new Get(row);
        getHbasePressure.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("pressure"));
        Result resultPressure = table.get(getHbasePressure);
        List<Cell> cells = resultPressure.listCells();
        String s = null;
        for (Cell cell : cells) {
            s = Bytes.toString(CellUtil.cloneValue(cell));
        }
        return s;
    }


    /**
     * @Description: 检验pressure字段是否存在
     * @Param: [table, row]
     * @return: java.lang.Boolean
     * @Author: 钟顺民
     */
    public static Boolean getHbasePressureExist(Table table,byte[] row) throws IOException {
        Get getHbasePressure = new Get(row);
        getHbasePressure.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("pressure"));
        Result resultPressure = table.get(getHbasePressure);
        Boolean hbasePressureExists = resultPressure.getExists();
        return hbasePressureExists;
    }


    /**
     * @Description: 获取温度信息
     * @Param: [table, row]
     * @return: java.lang.String ：temperature
     * @Author: 钟顺民
     */
    public static String getHbaseTemperature(Table table,byte[] row) throws IOException {
        Get getHbaseTemperature = new Get(row);
        getHbaseTemperature.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("temperature"));
        Result resultTemperature = table.get(getHbaseTemperature);
        List<Cell> cells = resultTemperature.listCells();
        String s = null;
        for (Cell cell : cells) {
            s = Bytes.toString(CellUtil.cloneValue(cell));
        }
        return s;
    }


    /**
     * @Description: 检验temperature字段是否存在
     * @Param: [table, row]
     * @return: java.lang.Boolean
     * @Author: 钟顺民
     */
    public static Boolean getHbaseTemperatureExist(Table table,byte[] row) throws IOException {
        Get getHbaseTemperature = new Get(row);
        getHbaseTemperature.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("temperature"));
        Result resultTemperature = table.get(getHbaseTemperature);
        Boolean hbaseTemperatureExists = resultTemperature.getExists();
        return hbaseTemperatureExists;
    }


    /**
     * @Description: 获取流量
     * @Param: [table, row]
     * @return: java.lang.String ：flow
     * @Author: 钟顺民
     */
    public static String getHbaseFlow(Table table,byte[] row) throws IOException {
        Get getHbaseFlow = new Get(row);
        getHbaseFlow.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("flow"));
        Result resultFlow = table.get(getHbaseFlow);
        List<Cell> cells = resultFlow.listCells();
        String s = null;
        for (Cell cell : cells) {
            s = Bytes.toString(CellUtil.cloneValue(cell));
        }
        return s;
    }


    /**
     * @Description: 检验flow字段是否存在
     * @Param: [table, row]
     * @return: java.lang.Boolean
     * @Author: 钟顺民
     */
    public static Boolean getHbaseFlowExist(Table table,byte[] row) throws IOException {
        Get getHbaseFlow = new Get(row);
        getHbaseFlow.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("flow"));
        Result resultAddr = table.get(getHbaseFlow);
        Boolean hbaseAddrExists = resultAddr.getExists();
        return hbaseAddrExists;
    }


    /**
     * @Description: 获取时间
     * @Param: [table, row]
     * @return: java.lang.String ：time
     * @Author: 钟顺民
     */
    public static String getHbaseTime(Table table,byte[] row) throws IOException {
       Get getHbaseTime = new Get(row);
        getHbaseTime.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("time"));
        Result resultTime = table.get(getHbaseTime);
        List<Cell> cells = resultTime.listCells();
        String s = null;
        for (Cell cell : cells) {
            s = Bytes.toString(CellUtil.cloneValue(cell));
        }
        return s;
    }


    /**
     * @Description: 检验time字段是否存在
     * @Param: [table, row]
     * @return: java.lang.Boolean
     * @Author: 钟顺民
     */
    public static Boolean getHbaseTimeExist(Table table,byte[] row) throws IOException {
        Get getHbaseTime = new Get(row);
        getHbaseTime.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("time"));
        Result resultTime = table.get(getHbaseTime);
        Boolean hbaseTimeExists = resultTime.getExists();
        return hbaseTimeExists;
    }


    /**
     * @Description: 获取地区信息
     * @Param: [table, row]
     * @return: java.lang.String ：area
     * @Author: 钟顺民
     */
    public static String getHbaseArea(Table table,byte[] row) throws IOException {
        Get getHbaseArea = new Get(row);
        getHbaseArea.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("area"));
        Result resultArea = table.get(getHbaseArea);
        List<Cell> cells = resultArea.listCells();
        String s = null;
        for (Cell cell : cells) {
            s = Bytes.toString(CellUtil.cloneValue(cell));
        }
        return s;
    }


    /**
     * @Description: 检验area字段是否存在
     * @Param: [table, row]
     * @return: java.lang.Boolean
     * @Author: 钟顺民
     */
    public static Boolean getHbaseAreaExist(Table table,byte[] row) throws IOException {
        Get getHbaseArea = new Get(row);
        getHbaseArea.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("area"));
        Result resultArea = table.get(getHbaseArea);
        Boolean hbaseAreaExists = resultArea.getExists();
        return hbaseAreaExists;
    }


    /**
     * @Description: 获取压力报警级别
     * @Param: [table, row]
     * @return: java.lang.String ：preLevel
     * @Author: 钟顺民
     */
    public static String getHbasePreLevel(Table table,byte[] row) throws IOException {
        Get getHbasePreLevel = new Get(row);
        getHbasePreLevel.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("preLevel"));
        Result resultPreLevel = table.get(getHbasePreLevel);
        List<Cell> cells = resultPreLevel.listCells();
        String s = null;
        for (Cell cell : cells) {
            s = Bytes.toString(CellUtil.cloneValue(cell));
        }
        return s;
    }


    /**
     * @Description: 检验preLevel字段是否存在
     * @Param: [table, row]
     * @return: java.lang.Boolean
     * @Author: 钟顺民
     */
    public static Boolean getHbasePreLevelExist(Table table,byte[] row) throws IOException {
        Get getHbasePreLevel = new Get(row);
        getHbasePreLevel.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("preLevel"));
        Result resultPreLevel = table.get(getHbasePreLevel);
        Boolean hbasePreLevelExists = resultPreLevel.getExists();
        return hbasePreLevelExists;
    }


    /**
     * @Description: 获取温度报警级别
     * @Param: [table, row]
     * @return: java.lang.String ： temLevel
     * @Author: 钟顺民
     */
    public static String getHbaseTemLevel(Table table,byte[] row) throws IOException {
        Get getHbaseTemLevel = new Get(row);
        getHbaseTemLevel.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("temLevel"));
        Result resultTemLevel = table.get(getHbaseTemLevel);
        List<Cell> cells = resultTemLevel.listCells();
        String s = null;
        for (Cell cell : cells) {
            s = Bytes.toString(CellUtil.cloneValue(cell));
        }
        return s;
    }


    /**
     * @Description: 检验temLevel字段是否存在
     * @Param: [table, row]
     * @return: java.lang.Boolean
     * @Author: 钟顺民
     */
    public static Boolean getHbaseTemLevelExist(Table table,byte[] row) throws IOException {
        Get getHbaseTemLevel = new Get(row);
        getHbaseTemLevel.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("temLevel"));
        Result resultTemLevel = table.get(getHbaseTemLevel);
        Boolean hbaseTemLevelExists = resultTemLevel.getExists();
        return hbaseTemLevelExists;
    }


    /**
     * @Description: 获取流量报警级别
     * @Param: [table, row]
     * @return: java.lang.String ：floLevel
     * @Author: 钟顺民
     */
    public static String getHbaseFloLevel(Table table,byte[] row) throws IOException {
        Get getHbaseFloLevel = new Get(row);
        getHbaseFloLevel.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("floLevel"));
        Result resultFloLevel = table.get(getHbaseFloLevel);
        List<Cell> cells = resultFloLevel.listCells();
        String s = null;
        for (Cell cell : cells) {
            s = Bytes.toString(CellUtil.cloneValue(cell));
        }
        return s;
    }


    /**
     * @Description: 检验floLevel字段是否存在
     * @Param: [table, row]
     * @return: java.lang.Boolean
     * @Author: 钟顺民
     */
    public static Boolean getHbaseFloLevelExist(Table table,byte[] row) throws IOException {
        Get getHbaseFloLevel = new Get(row);
        getHbaseFloLevel.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("floLevel"));
        Result resultFloLevel = table.get(getHbaseFloLevel);
        Boolean hbaseFloLevelExists = resultFloLevel.getExists();
        return hbaseFloLevelExists;
    }


    /**
     * @Description: 获取状态信息，是否误报警。
     * @Param: [table, row]
     * @return: java.lang.String ：state
     * @Author: 钟顺民
     */
    public static String getHbaseState(Table table,byte[] row) throws IOException {
        Get getHbaseState = new Get(row);
        getHbaseState.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("state"));
        Result resultState = table.get(getHbaseState);
        List<Cell> cells = resultState.listCells();
        String s = null;
        for (Cell cell : cells) {
            s = Bytes.toString(CellUtil.cloneValue(cell));
        }
        return s;
    }


    /**
     * @Description: 检验State字段是否存在
     * @Param: [table, row]
     * @return: java.lang.Boolean
     * @Author: 钟顺民
     */
    public static Boolean getHbaseStateExist(Table table,byte[] row) throws IOException {
        Get getHbaseState = new Get(row);
        getHbaseState.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("state"));
        Result resultState = table.get(getHbaseState);
        Boolean hbaseStateExists = resultState.getExists();
        return hbaseStateExists;
    }


    /**
     * @Description: 检验s所有字段是否都存在
     * @Param: [table, row]
     * @return: java.lang.Boolean
     * @Author: 钟顺民
     */
    public static Boolean getHbaseAllExist(Table table,byte[] row) throws IOException{
        boolean hbaseAllExist = getHbaseAddrExist(table, row) & getHbaseAreaExist(table, row) &
                getHbaseFloLevelExist(table, row) & getHbaseFlowExist(table, row) &
                getHbaseIDExist(table, row) & getHbasePreLevelExist(table, row) &
                getHbasePressureExist(table, row) & getHbaseStateExist(table, row) &
                getHbaseStateExist(table, row) & getHbaseTemLevelExist(table, row) &
                getHbaseTemperatureExist(table, row) & getHbaseTimeExist(table, row) &
                getHbaseTypeExist(table, row);
        return hbaseAllExist;
    }
}
