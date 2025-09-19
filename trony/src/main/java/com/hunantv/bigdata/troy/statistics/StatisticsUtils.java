package com.hunantv.bigdata.troy.statistics;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.hunantv.bigdata.troy.Lifecycle;
import com.hunantv.bigdata.troy.configure.AbstractConfigManager;
import com.hunantv.bigdata.troy.configure.LogConfig;
import com.hunantv.bigdata.troy.entity.Message;
import com.hunantv.bigdata.troy.entity.MessageStatus;
import com.hunantv.bigdata.troy.entity.Statistics;
import com.hunantv.bigdata.troy.tools.Constants;
import com.hunantv.bigdata.troy.tools.JsonUtils;
import com.hunantv.bigdata.troy.tools.Utils;

import java.util.Map;

/**
 * 日志统计类
 * Copyright (c) 2014, hunantv.com All Rights Reserved.
 * <p/>
 * User: jeffreywu  MailTo:jeffreywu@sohu-inc.com
 * Date: 15/1/20
 * Time: PM7:16
 */
public class StatisticsUtils implements Lifecycle {

    // 1. 静态成员（存储统计数据，全局共享）
    public static Map<String, Statistics> configedStats; // 已配置的日志统计（key：bid_状态）
    public static Map<String, Statistics> nonConfigedStats; // 未配置的日志统计（无效bid）
    public static Statistics totalStat; // 总统计（所有日志的汇总）

    // 2. 构造方法（初始化统计项）
    public StatisticsUtils(AbstractConfigManager configManager){
        // 从配置管理器获取所有日志配置（LogConfig包含每个bid的配置信息）
        Map<Integer, LogConfig> logConfigs = configManager.getConfigMap();

        // 初始化统计容器（用谷歌Maps工具创建HashMap）
        configedStats = Maps.newHashMap();
        nonConfigedStats = Maps.newHashMap();

        Statistics stat; // 临时统计对象
        String time = Utils.getCurrentTime(); // 当前时间（字符串）
        long timestamp = Utils.getUnixTimeStamp(); // 当前时间戳（毫秒）

        // 遍历所有配置的日志（LogConfig），初始化configedStats
        for(LogConfig config: logConfigs.values()){
            // 为每种状态（StatStatus枚举）创建统计项
            for(StatStatus status : StatStatus.values()){
                stat = new Statistics();
                stat.setBid(config.getBid()); // 绑定日志的bid
                stat.setStartTime(time); // 开始时间=当前初始化时间
                stat.setStartTimeStamp(timestamp); // 开始时间戳=当前时间戳
                stat.setLastTime(time); // 最后更新时间=初始时间
                stat.setLastTimeStamp(timestamp); // 最后更新时间戳=初始时间戳
                stat.setTotalCounter(0); // 初始计数=0
                // 存入configedStats：key是"bid_状态"（如"loginLog_PARSE_ERROR"）
                configedStats.put(config.getBid() + "_" + status, stat);
            }
        }

        // 初始化nonConfigedStats（处理无效bid的日志）
        for(StatStatus status : StatStatus.values()){
            stat = new Statistics();
            stat.setBid(Constants.INVALID_BID); // 无效bid（常量，如"invalid"）
            stat.setStartTime(time);
            stat.setStartTimeStamp(timestamp);
            stat.setLastTime(time);
            stat.setLastTimeStamp(timestamp);
            stat.setTotalCounter(0);
            // 存入nonConfigedStats：key是"无效bid_状态"（如"invalid_PARSE_ERROR"）
            nonConfigedStats.put(Constants.INVALID_BID + "_" + status, stat);
        }

        // 初始化总统计项（所有日志的汇总）
        totalStat = new Statistics();
        totalStat.setBid(Constants.TOTAL_BID); // 总统计标识（如"total"）
        totalStat.setStartTime(time);
        totalStat.setStartTimeStamp(timestamp);
        totalStat.setLastTime(time);
        totalStat.setLastTimeStamp(timestamp);
        totalStat.setTotalCounter(0);
    }

    // 3. 统计消息（核心方法1：接收Message对象，更新统计）
    public static Message stat(Message message) {
        // 调用重载方法，传入消息的bid和状态
        stat(message.getBid(), message.getStatus());
        return message; // 返回消息（不修改消息，仅统计）
    }

    public static void stat(String bid, MessageStatus status) {
        Statistics stat;
        if(!Strings.isNullOrEmpty(bid) && configedStats.get(bid + "_" + status) != null){
            stat = configedStats.get(bid + "_" + status);
        } else {
            stat = nonConfigedStats.get(Constants.INVALID_BID + "_" + status);
        }
        stat.increment();
    }

    public static void statTotal(){
        totalStat.increment();
    }

    public static String getStats(){
        Map<String, Statistics> stats = Maps.newHashMap();
        stats.put(Constants.TOTAL_BID, totalStat);
        stats.putAll(configedStats);
        stats.putAll(nonConfigedStats);
        return JsonUtils.simpleJson(stats);
    }

    private static enum StatStatus {
        BUILD_ERROR, PARSE_ERROR, VALID_SUCC, VALID_ERROR, IS_DEBUG,
    }

    @Override
    public void doStart() {

    }

    @Override
    public void destory() {

    }

}
