package com.dfn.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.util.logging.Logger;

public class ServerChannelHandler extends SimpleChannelInboundHandler<String> {
    Logger logger = Logger.getLogger("ServerChannelHandler");
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String text) throws Exception {
        logger.info("message received "+text);
        channelHandlerContext.channel().writeAndFlush(text.toUpperCase());
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
       logger.info("channel active called!");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
      logger.info("channel inactive called!");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        logger.info("exception caught!!" + cause.getMessage());
    }

}
