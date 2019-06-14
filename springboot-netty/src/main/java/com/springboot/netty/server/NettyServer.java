package com.springboot.netty.server;

import com.sun.corba.se.internal.CosNaming.BootstrapServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Author: wch
 * @Description: netty服务端
 * @Date: 2019-06-15 00:40
 */
@Service
@ChannelHandler.Sharable
public class NettyServer {

    private Logger logger = LoggerFactory.getLogger(NettyServer.class);


    private static final NioEventLoopGroup bossGroup = new NioEventLoopGroup();

    private static final NioEventLoopGroup workerGroup = new NioEventLoopGroup();

    private ChannelFuture sync;

    @PostConstruct
    public void init() throws Exception{
        // 1. 初始化bossGroup and workerGroup
        // 2. 创建ServerBootstrap对象 添加线程组
        // 3. 设置为nio模式
        // 4. 添加入站和出战的handler 推荐使用ChannelDuplexHandler 这个采用适配器模式 既有出站也有入站
        // 5. 绑定端口

        ServerBootstrap serverBootstrap = new ServerBootstrap();
         sync = serverBootstrap.group(bossGroup, workerGroup).
                channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline()
                                .addLast(new StringDecoder())
                                .addLast(new StringEncoder())
                                .addLast(new ChannelDuplexHandler() {
                                    // 连接成功会调用
                                    @Override
                                    public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                        logger.info("连接成功");
                                        super.channelActive(ctx);
                                    }

                                    // 入站会调用
                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        String in = (String) msg;
                                        logger.info("入站消息为={}", in);
                                        ctx.writeAndFlush(new String("统一回复"));


                                    }

                                    // 有异常会调用
                                    @Override
                                    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                                        logger.info("有异常产生", cause);
                                        ctx.close();
                                    }

                                    // 出站写入会调用
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
                .bind(11223).syncUninterruptibly();

         // ChannelFuture 的 介绍https://donald-draper.iteye.com/blog/2388297

         if(sync.isSuccess()){
             logger.info(" netty服务启动完毕 ");
         }


    }


    @PreDestroy
    public void stop(){
        if(sync!=null){
            sync.channel().close().addListener(ChannelFutureListener.CLOSE);
            sync.awaitUninterruptibly();
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
            sync=null;
            logger.info(" netty服务关闭 ");
        }
    }







}
