package com.springboot.hadoop.dfs.config;

/**
 * hdfs 配置
 */
public class HdfsConfig {
    public static   String path;

    public static   String username;


    static {
        path="hdfs://192.168.244.100:9000";
        username="root";
    }

}
