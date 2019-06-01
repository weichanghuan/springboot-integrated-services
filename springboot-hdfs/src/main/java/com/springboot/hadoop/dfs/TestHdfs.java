package com.springboot.hadoop.dfs;

import com.springboot.hadoop.dfs.config.HdfsConfig;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

public class TestHdfs {
    private final static Logger log= LoggerFactory.getLogger(TestHdfs.class);

    private Configuration configuration;

    private FileSystem fileSystem;

    private void init(){
        configuration=new Configuration();

    }

    private void end(){
        try {
            fileSystem.close();
        }catch (Exception ex){
            log.error("Exception is {]",ex.getMessage());
        }
        log.info("excute is end");
    }

    @Test
    public void testMkdir(){
        init();

        try {
             fileSystem=FileSystem.get(new URI( HdfsConfig.path),configuration,HdfsConfig.username);
            fileSystem.copyFromLocalFile(new Path("D:/zhouqiang.txt"),new Path("/"));
        }catch (Exception ex){
            log.error("Exception is {]",ex.getMessage());
        }
        end();
    }
}
