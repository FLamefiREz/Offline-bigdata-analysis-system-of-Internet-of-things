package com.dtinone.kafkatohbasetest;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        //new Producer1().myproducer();
        final String message = new ConsumerInfo().Message();
        new ConsumerInfo().ToHBase(message);

    }
}
