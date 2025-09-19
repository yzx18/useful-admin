package com.hunantv.bigdata.troy.entity;

import com.hunantv.bigdata.troy.tools.Utils;

/**
 * Created by wuxinyong on 15-1-26.
 */
public class Statistics {

    // 1. 核心字段
    private String bid; // 日志唯一标识（业务ID，用于区分不同类型的日志）
    private MessageStatus status; // 日志处理状态（如成功、解析错误等，关联Message的状态）
    private String startTime; // 统计开始时间（格式化字符串，如"2025-09-09 10:00:00"）
    private long startTimeStamp; // 统计开始时间戳（毫秒级，方便计算时长）
    private String lastTime; // 最后一次更新时间（格式化字符串）
    private volatile long totalCounter; // 总计数（volatile保证多线程下的可见性）
    private long lastTimeStamp; // 最后一次更新时间戳（毫秒级）

    // 2. 计数自增方法（核心功能）
    public void increment(){
        totalCounter++; // 计数+1（统计该状态的日志总数量）
        setLastTime(Utils.getCurrentTime()); // 更新最后一次统计时间（字符串格式）
        setLastTimeStamp(Utils.getUnixTimeStamp()); // 更新最后一次统计时间戳（毫秒）
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public long getTotalCounter() {
        return totalCounter;
    }

    public void setTotalCounter(long totalCounter) {
        this.totalCounter = totalCounter;
    }

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }

    public long getStartTimeStamp() {
        return startTimeStamp;
    }

    public void setStartTimeStamp(long timeStamp) {
        this.startTimeStamp = timeStamp;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public long getLastTimeStamp() {
        return lastTimeStamp;
    }

    public void setLastTimeStamp(long lastTimeStamp) {
        this.lastTimeStamp = lastTimeStamp;
    }
}
