package com.hunantv.bigdata.troy.dispatcher;

import com.hunantv.bigdata.troy.configure.AbstractConfigManager;
import com.hunantv.bigdata.troy.configure.LocalConfigManager;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * Copyright (c) 2014, hunantv.com All Rights Reserved.
 * <p/>
 * User: jeffreywu  MailTo:jeffreywu@sohu-inc.com
 * Date: 15/1/20
 * Time: PM6:40
 */
public class DispatcherChannelInitializer extends ChannelInitializer<SocketChannel> {

    private AbstractConfigManager configManager;

    public DispatcherChannelInitializer(AbstractConfigManager configManager) {
        this.configManager = configManager;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        //讲二进制翻译成java对象
        pipeline.addLast("http-decoder", new HttpRequestDecoder());
        /**
         * HttpObjectAggregator 和 ChunkedWriteHandler：
         * 它们是处理 “大二进制数据” 的工具：
         * 比如客户端发了一个 10MB 的大请求，二进制流会被分成很多小块，HttpObjectAggregator 负责把这些小块合并成一个完整的请求对象。
         * 服务器要返回一个 20MB 的大文件，ChunkedWriteHandler 负责把大文件的二进制流拆成小块，一块一块发，避免一次性占用太多内存。
         */
        pipeline.addLast("http-aggregater", new HttpObjectAggregator(1048576));
        //将java对象翻译成二进制
        pipeline.addLast("http-encoder", new HttpResponseEncoder());
        pipeline.addLast("http-chunked", new ChunkedWriteHandler());
        pipeline.addLast("log-service", new DispatcherHandler(this.configManager));
    }
}
