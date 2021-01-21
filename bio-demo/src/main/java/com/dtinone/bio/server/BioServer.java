package com.dtinone.bio.server;


import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class BioServer {

    public static void main(String[] args) throws Exception  {

        ServerSocket server = new ServerSocket(8888);
        while (true){
            Socket socket = server.accept();
            InputStream inputStream = socket.getInputStream();
            byte[] buf = new byte[1024];
            inputStream.read(buf);
            System.out.println(new String(buf).trim());
        }

    }


}
