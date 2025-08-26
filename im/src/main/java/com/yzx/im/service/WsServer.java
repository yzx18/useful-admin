package com.yzx.im.service;

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

public class WsServer {

    public void run() throws InterruptedException {
        EventLoopGroup bossGroup = null;
        EventLoopGroup wordGroup = null;
        try {
            bossGroup = new NioEventLoopGroup();
            wordGroup = new NioEventLoopGroup();
            ServerBootstrap serverBootStrap = new ServerBootstrap();
            serverBootStrap.group(bossGroup, wordGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler())
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            //使用http的编码和解码器
                            pipeline.addLast(new HttpServerCodec());
                            //是以块方式写,添加chunkerwritehandler 处理器
                            pipeline.addLast(new ChunkedWriteHandler());
                            /**
                             * 说明
                             * 1.http数据在传输过程中是分段,httpObjectAggregator , 就是可以将多个段聚合起来
                             */
                            pipeline.addLast(new HttpObjectAggregator(8192));
                            //对于websocket 它的数据是以帧(frame)形式传递
                            //WebSocketServerProtocolHandler 核心功能是将http协议升级为ws协议,保持长连接
                            pipeline.addLast(new WebSocketServerProtocolHandler("/hello"));
                            //自定义handler
                            pipeline.addLast(new MyWebSocketHandler());
                        }
                    });
            ChannelFuture sync = serverBootStrap.bind(7000).sync();
            sync.channel().closeFuture().sync();
        } finally {
            wordGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WsServer wsServer = new WsServer();
        wsServer.run();
    }
}
