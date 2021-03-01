package com.srj.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.logging.Logger;

public class ServerApp {

    public static void main(String[] args) {
        Logger logger = Logger.getLogger("ServerApp");
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(10);

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ServerChannelInitializer());


            bootstrap.bind("127.0.0.1",9902).channel();
            logger.info(" socket listening on 127.0.0.1:9902");
        } catch (Exception e) {
            logger.info("Error creating socket connection"+ e);

        }
    }
}
