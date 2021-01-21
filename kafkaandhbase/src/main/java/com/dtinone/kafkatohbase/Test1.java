package com.dtinone.kafkatohbase;

import java.io.IOException;

public class Test1 {
    public static void main(String[] args) throws IOException {
        new Producer().MyProducer();//生产数据
        new ConsumerToHBase1().MyConsumerToHBase();//消费数据到HBase
    }
}
