package com.yzx.im.service;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 单连接处理文本和视频消息（推荐方案）
 */
public class MyWebSocketHandler extends SimpleChannelInboundHandler<Object> {
    private static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // 视频上传状态跟踪
    private final Map<Channel, VideoUploadState> videoStates = new HashMap<>();
    private static final long MAX_VIDEO_SIZE = 40 * 1024 * 1024; // 40MB
    private static final long MAX_VIDEO_DURATION = 60 * 10000; // 60秒（毫秒）

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        channels.add(channel);
        broadcastSystemMessage(channel.remoteAddress() + " 加入聊天");
        System.out.println("客户端连接：" + channel.remoteAddress() + "，在线人数：" + channels.size());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        videoStates.remove(channel); // 清理视频状态
        broadcastSystemMessage(channel.remoteAddress() + " 离开聊天");
        System.out.println("客户端断开：" + channel.remoteAddress() + "，在线人数：" + channels.size());
    }

    // 核心：区分处理文本和二进制消息
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) {
        if (msg instanceof TextWebSocketFrame) {
            handleTextMessage(ctx, (TextWebSocketFrame) msg);
        } else if (msg instanceof BinaryWebSocketFrame) {
            handleVideoMessage(ctx, (BinaryWebSocketFrame) msg);
        }
    }

    // 处理文本消息
    private void handleTextMessage(ChannelHandlerContext ctx, TextWebSocketFrame msg) {
        Channel channel = ctx.channel();
        String content = msg.text();
        System.out.println("收到文本消息：" + content);
        // 广播文本消息
        for (Channel ch : channels) {
            if (ch == channel) {
                ch.writeAndFlush(new TextWebSocketFrame("[我]：" + content));
            } else {
                ch.writeAndFlush(new TextWebSocketFrame("[" + channel.remoteAddress() + "]：" + content));
            }
        }
    }

    // 处理视频二进制消息
    private void handleVideoMessage(ChannelHandlerContext ctx, BinaryWebSocketFrame msg) {
        Channel channel = ctx.channel();
        ByteBuf videoData = msg.content();
        int frameSize = videoData.readableBytes();

        // 获取当前通道的视频状态
        VideoUploadState state = videoStates.computeIfAbsent(channel, k -> new VideoUploadState());
        state.totalSize += frameSize;

        // 验证大小限制
        if (state.totalSize > MAX_VIDEO_SIZE) {
            ctx.writeAndFlush(new TextWebSocketFrame("系统：视频超过40MB限制"));
            videoStates.remove(channel);
            return;
        }

        // 验证时长限制（首次接收时记录开始时间）
        if (state.startTime == 0) {
            state.startTime = System.currentTimeMillis();
            ctx.writeAndFlush(new TextWebSocketFrame("系统：开始接收视频..."));
        } else {
            long duration = System.currentTimeMillis() - state.startTime;
            if (duration > MAX_VIDEO_DURATION) {
                ctx.writeAndFlush(new TextWebSocketFrame("系统：视频超过1分钟限制"));
                videoStates.remove(channel);
                return;
            }
        }

        // 广播视频数据给其他客户端
        for (Channel ch : channels) {
            if (ch != channel) {
                ch.writeAndFlush(new BinaryWebSocketFrame(videoData.retain())); // 注意retain()复用缓冲区
            }
        }
    }

    // 辅助方法：广播系统消息
    private void broadcastSystemMessage(String message) {
        String systemMsg = "[" + sdf.format(new Date()) + "] 系统消息：" + message;
        channels.writeAndFlush(new TextWebSocketFrame(systemMsg));
    }

    // 视频上传状态类
    private static class VideoUploadState {
        long startTime; // 开始时间戳（毫秒）
        long totalSize; // 累计大小（字节）
    }

    // 异常和空闲事件处理（省略，同之前的实现）
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { /* ... */ }
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) { /* ... */ }
}
