package com.yzx.message.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.GlobalEventExecutor;
import io.netty.channel.ChannelHandler.Sharable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

@Sharable
@Component
public class MyWebSocketHandler extends SimpleChannelInboundHandler<Object> {

    @Autowired
    private QwenService qwenService;

    @Autowired
    private ObjectMapper objectMapper; // 用于JSON序列化

    private static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private final Map<Channel, VideoUploadState> videoStates = new ConcurrentHashMap<>();

    private static final long MAX_VIDEO_SIZE = 40 * 1024 * 1024;
    private static final long MAX_VIDEO_DURATION = 60 * 1000;
    public static final AttributeKey<String> USER_ID = AttributeKey.valueOf("userId");

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        channels.add(channel);
        channel.writeAndFlush(new TextWebSocketFrame("您好！我是云购电商的AI客服，有什么可以帮助您的吗？"));
        System.out.println("客户端连接：" + channel.remoteAddress() + "，在线人数：" + channels.size());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        videoStates.remove(channel);
        channels.remove(channel);
        System.out.println("客户端断开：" + channel.remoteAddress() + "，在线人数：" + channels.size());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) {
        if (msg instanceof TextWebSocketFrame) {
            handleTextMessage(ctx, (TextWebSocketFrame) msg); // 文本消息（核心修改）
        } else if (msg instanceof BinaryWebSocketFrame) {
            handleVideoMessage(ctx, (BinaryWebSocketFrame) msg); // 视频消息（保持不变）
        }
    }

    // 核心修改：处理文本消息并实现流式输出
    private void handleTextMessage(ChannelHandlerContext ctx, TextWebSocketFrame msg) {
        Channel channel = ctx.channel();
        String userMessage = msg.text();
        System.out.println("收到用户消息：" + userMessage + "（来自：" + channel.remoteAddress() + "）");

        // 1. 生成唯一消息ID（用于前端拼接同一回复的字符）
        String messageId = UUID.randomUUID().toString();

        // 2. 定义字符消费者：收到单个字符后发送到前端
        Consumer<String> charConsumer = charStr -> {
            try {
                // 构建包含消息ID和字符的JSON
                Map<String, String> payload = new HashMap<>(2);
                payload.put("messageId", messageId);
                payload.put("char", charStr);
                String json = objectMapper.writeValueAsString(payload);
                // 发送到当前客户端
                channel.writeAndFlush(new TextWebSocketFrame(json));
            } catch (Exception e) {
                channel.writeAndFlush(new TextWebSocketFrame("消息发送失败：" + e.getMessage()));
            }
        };

        // 3. 调用QwenService的流式方法（核心：启动流式输出）
        try {
            qwenService.chatStream(userMessage, charConsumer);
        } catch (Exception e) {
            channel.writeAndFlush(new TextWebSocketFrame("AI服务异常：" + e.getMessage()));
        }
    }

    // 视频消息处理（保持原有逻辑不变）
    private void handleVideoMessage(ChannelHandlerContext ctx, BinaryWebSocketFrame msg) {
        Channel channel = ctx.channel();
        ByteBuf videoData = msg.content();
        int frameSize = videoData.readableBytes();
        VideoUploadState state = videoStates.computeIfAbsent(channel, k -> new VideoUploadState());
        state.totalSize += frameSize;

        if (state.totalSize > MAX_VIDEO_SIZE) {
            ctx.writeAndFlush(new TextWebSocketFrame("系统：视频超过40MB限制"));
            videoStates.remove(channel);
            return;
        }
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
        ctx.writeAndFlush(new TextWebSocketFrame("视频接收中，当前大小：" + state.totalSize/1024 + "KB"));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        System.err.println("WebSocket异常：" + cause.getMessage());
        ctx.writeAndFlush(new TextWebSocketFrame("连接异常，请刷新页面重试"));
        ctx.close();
    }

    private static class VideoUploadState {
        long startTime;
        long totalSize;
    }
}
