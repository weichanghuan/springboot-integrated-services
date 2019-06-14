package com.springboot.netty.client;

import com.springboot.netty.server.NettyServer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: wch
 * @Description: netty客户端
 * @Date: 2019-06-15 01:04
 */
public class NettyClient {

    private Logger logger = LoggerFactory.getLogger(NettyServer.class);


    private static final NioEventLoopGroup workerGroup = new NioEventLoopGroup();



    public void init() {
        // 1.初始化工作线程池
        // 2.创建客户端Bootstrap
        // 3.设置nio
        // 4.出站和入站handler
        // 5.指定端口和ip


        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast(new StringDecoder())
                                .addLast(new StringEncoder())
                                .addLast(new ChannelDuplexHandler(){
                                    @Override
                                    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                                        super.exceptionCaught(ctx, cause);
                                    }


                                    @Override
                                    public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                        logger.info("连接成功");
                                        ctx.writeAndFlush("初始化发送");
                                        super.channelActive(ctx);
                                    }


                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        String in = (String) msg;
                                        logger.info("收到服务端消息为={}",in);

                                       // ctx.writeAndFlush("客户端统一回复");
                                        super.channelRead(ctx, msg);
                                    }

                                    @Override
                                    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                        super.write(ctx, msg, promise);
                                    }

                                    @Override
                                    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
                                        ctx.flush();
                                    }
                                });

                    }
                })
                .connect("127.0.0.1", 11223).syncUninterruptibly();
    }


    public static void main(String[] args) {
        NettyClient nettyClient=new NettyClient();
        nettyClient.init();
    }
}
