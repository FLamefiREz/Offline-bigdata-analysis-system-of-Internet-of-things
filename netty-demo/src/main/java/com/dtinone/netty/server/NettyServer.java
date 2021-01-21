package com.dtinone.netty.server;

import com.dtinone.netty.server.handler.NettyServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * netty服务器
 */
public class NettyServer {

    public static void main(String[] args) throws Exception{
        //1.创建一个线程组：用来处理网络事件（接受客户端连接）
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //2.创建一个线程组：用来处理网络事件（处理通道 IO 操作）
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        //3.创建服务器端启动助手来配置参数
        ServerBootstrap server = new ServerBootstrap();
        server.group(bossGroup, workerGroup) //4.设置两个线程组 EventLoopGroup
                .channel(NioServerSocketChannel.class) //5.使用 NioServerSocketChannel 作为服务器端通道实现
                .option(ChannelOption.SO_BACKLOG, 128) //6.设置线程队列中等待连接的个数
                .childOption(ChannelOption.SO_KEEPALIVE, true) //7.保持活动连接状态
                .childHandler(new ChannelInitializer<SocketChannel>() { //8.创建一个通道初始化对象
                    public void initChannel(SocketChannel sc) { //9.往 Pipeline 链中添加自定义的业务处理 handler
//                        sc.pipeline().addLast(new IdleStateHandler(5,6,10));
                        sc.pipeline().addLast(new NettyServerHandler()); //服务器端业务处理类
                        System.out.println(".......Server is ready.......");
                    }
                });
        //10.启动服务器端并绑定端口，等待接受客户端连接(非阻塞)
        ChannelFuture cf = server.bind(9999).sync();
        System.out.println("......Server is Starting......");
        //11.关闭通道，关闭线程池
        cf.channel().closeFuture().sync();
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }


}