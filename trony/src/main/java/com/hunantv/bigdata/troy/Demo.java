package com.hunantv.bigdata.troy;


import org.apache.commons.cli.*;

/**
 * @className: Demo
 * @author: yzx
 * @date: 2025/9/8 7:50
 * @Version: 1.0
 * @description:
 */
public class Demo {
    public static void main(String[] args) throws ParseException {
        CommandLineParser commandLineParser = new BasicParser();
        Options options = new Options();
        options.addOption("start", true, "开始");
        options.addOption("end", true, "结束");
        options.addOption("flag", false, "标志");
        CommandLine commandLine = commandLineParser.parse(options, args);
        if (commandLine.hasOption("flag")) {
            System.out.println("0000000000");
        }
        System.out.println(commandLine.getOptionValue("start"));
        System.out.println(commandLine.getOptionValue("end"));

    }
}
