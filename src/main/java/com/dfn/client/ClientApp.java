package com.dfn.client;

import com.dfn.soupbin.SoupBinTCP;
import com.dfn.soupbin.SoupException;
import com.dfn.util.Settings;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.logging.Logger;

public class ClientApp {
   static Logger logger = Logger.getLogger("ClientApp");

    public static void main(String[] args) {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup(10);
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .handler(new NettyClientInitializer());

        Settings.load("configs/config.ini");
        Channel channel = bootstrap.connect(Settings.host, Settings.port).addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
               logger.info("client connected on "+Settings.host+" "+Settings.port);
                channelFuture.channel().writeAndFlush(readInput("configs/input.txt"));
               logger.info("sent from client: "+readInput("configs/input.txt"));
               logger.info("sent from client: "+readInput("configs/input.txt"));
               //channelFuture.channel().writeAndFlush(login(Settings.userID,Settings.password,Settings.sequenceNo));
                //  logger.info("Login Request sent: userID: "+Settings.userID+" Password: "+Settings.password+" SequenceNo: "+Settings.sequenceNo);
            }
        }).channel();
    }

  private static String readInput(String fileName) {

        File file = new File(fileName);
        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedReader  br = new BufferedReader(new InputStreamReader(fis));

            try {
                return br.readLine();
            } finally {
                br.close();
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to read from Filestream", e);
        }
    }

    //SOUPBIN Loginrequest obj sender
  /*  private static SoupBinTCP.LoginRequest sendLogin() {
        SoupBinTCP.LoginRequest loginRequest = new SoupBinTCP.LoginRequest();
        loginRequest.setUsername("test12");
        loginRequest.setPassword("123567890");
        loginRequest.setRequestedSequenceNumber(1);
        loginRequest.setRequestedSession("1");

        return loginRequest;
    }
    private static synchronized ByteBuffer login(final String userId,
                                          final String passwd,
                                          final long sequenceNumber)
            throws SoupException {
        // The Login message is 49 bytes
        ByteBuffer buffer = allocateSendBuffer(userId.length() + passwd.length() + new String(""+sequenceNumber).length() + "".length() + 1);
        buffer.put((byte) 'L');
        writeString(buffer, userId, userId.length());
        writeString(buffer, passwd, passwd.length());
        writeString(buffer, "", "".length());
        writeString(buffer, Long.toString(sequenceNumber), new String(""+sequenceNumber).length());
        logger.info("Sending Login Request: "  + getBufferString(buffer));

       return buffer;
    }
    private static ByteBuffer allocateSendBuffer(final int bufferSize) {
        final ByteBuffer buffer = ByteBuffer.allocate(bufferSize + 2);
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.putShort((short) 0); // write a temporary size
        return buffer;
    }

    private static void writeString(final ByteBuffer buffer, final String text, final int length)
            throws SoupException {
        if (text.length() > length) {
            throw new SoupException("String to long, max length = " + length
                    + ", actual length = " + text.length());
        }
        int n = 0;
        while (n < text.length()) {
            buffer.put((byte) text.charAt(n++));
        }
        while (n++ < length) {
            buffer.put((byte) ' ');
        }
    }

    public static synchronized String getBufferString(final ByteBuffer buffer){
        return new String(buffer.array());
    }*/


   /* private static void runReader(Channel channel) {
        BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));

        String readed;
        while (true) {
            try {
                readed = buff.readLine();

                channel.writeAndFlush(readed);

                System.out.println(readed + " sent to Server!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void readfromFile(Channel channel) {
        String fileName = "input.txt";
        String line;
        File file = new File(fileName);

        try {
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            //  while ((line = br.readLine()) != null) {
            line = br.readLine();
            //process the line
            while (true) {
                channel.writeAndFlush(line);
                System.out.println(line + " sent to Server!");
                br.close();

            }
            //  }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static String readfromFileii() {
        String fileName = "input.txt";
        String line;
        File file = new File(fileName);
        try {
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }*/


}
