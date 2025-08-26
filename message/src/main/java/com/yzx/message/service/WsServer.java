package com.yzx.message.service;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class WsServer implements CommandLineRunner {

    // 直接注入单实例的Handler（已标记@Sharable）
    @Autowired
    private MyWebSocketHandler myWebSocketHandler;

    public void runServer() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler())
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new HttpServerCodec());
                            pipeline.addLast(new ChunkedWriteHandler());
                            pipeline.addLast(new HttpObjectAggregator(8192));
                            pipeline.addLast(new WebSocketServerProtocolHandler("/hello"));
                            // 直接添加单实例Handler（因为已标记@Sharable）
                            pipeline.addLast(myWebSocketHandler);
                        }
                    });

            ChannelFuture future = bootstrap.bind(7000).sync();
            System.out.println("WebSocket服务器启动成功，端口：7000");
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            System.err.println("服务器中断：" + e.getMessage());
            Thread.currentThread().interrupt();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    @Override
    public void run(String... args) throws Exception {
        new Thread(() -> {
            try {
                runServer();
            } catch (Exception e) {
                System.err.println("线程执行异常：" + e.getMessage());
            }
        }).start();
    }
}
