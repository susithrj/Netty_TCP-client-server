package com.srj.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.io.*;
import java.util.logging.Logger;

public class NettyClientStringHandler extends SimpleChannelInboundHandler<String> {
    Logger logger = Logger.getLogger("ClientApp");

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        logger.info("read from server " + s);
    }

    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        logger.info("channel active called in Client");
       // ctx.channel().writeAndFlush("Hello  Server! I'm your Netty Client! Send from Channel Active!");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel inactive called in Client");
        super.channelInactive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("exception caught in Client");
        super.exceptionCaught(ctx, cause);
    }
}
