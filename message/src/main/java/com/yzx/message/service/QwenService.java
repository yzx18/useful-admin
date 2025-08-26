package com.yzx.message.service;

import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatClient;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import java.util.UUID;
import java.util.function.Consumer;

@Service
public class QwenService {

    // Spring AI 自动配置的 Ollama 客户端
    @Autowired
    private OllamaChatClient ollamaChatClient;

    // 原有同步方法（保留）
    public String chat(String message) {
        // 构建提示词并设置模型参数
        Prompt prompt = new Prompt(message, createOptions());
        return ollamaChatClient.call(prompt).getResult().getOutput().getContent();
    }
    // 流式输出核心方法：基于 Spring AI 的 stream() 方法
    public String chatStream(String userMessage, Consumer<String> charConsumer) {
        String messageId = UUID.randomUUID().toString();

        // 1. 构建提示词（含模型参数配置）
        Prompt prompt = new Prompt(userMessage, createOptions());

        // 2. 调用 OllamaChatClient 的 stream() 方法，自动开启流式响应
        Flux<ChatResponse> responseFlux = ollamaChatClient.stream(prompt);

        // 3. 订阅流式响应，逐字输出
        responseFlux.subscribe(
                chatResponse -> {
                    String chunk = chatResponse.getResult().getOutput().getContent();
                    if (chunk != null && !chunk.isEmpty()) {
                        // 拆分为单个字符并通过回调传递给前端
                        chunk.codePoints()
                                .mapToObj(Character::toString)
                                .forEach(charConsumer);
                    }
                },
                error -> charConsumer.accept("流式调用异常：" + error.getMessage()),
                () -> System.out.println("流式输出完成") // 流式结束回调
        );

        return messageId;
    }

    // 构建模型参数（使用 withXxx() 链式方法，替代 setXxx()）
    private OllamaOptions createOptions() {
        return OllamaOptions.create()
                .withTemperature(0.7f) // 设置温度
                .withTopK(50)          // 设置 topK
                .withTopP(0.9f)        // 设置 topP
                .withNumPredict(2048); // 设置最大输出 tokens
    }
}