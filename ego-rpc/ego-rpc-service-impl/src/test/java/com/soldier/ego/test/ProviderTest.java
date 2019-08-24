package com.soldier.ego.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @ProjectName: ego
 * @author: soldier
 * @Email: 583403411@qq.com
 * @create 19-8-21 上午11:55
 * @Describe: 发布 RPC 服务
 **/
public class ProviderTest {

    public static void main(String[] args) {
        /**
         * 加载spring容器，完成服务发布
         */
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(
                "spring/applicationContext-dao.xml",
                "spring/applicationContext-service.xml",
                "spring/applicationContext-tx.xml",
                "spring/applicationContext-dubbo.xml");
        classPathXmlApplicationContext.start();

        //阻塞程序运行
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        classPathXmlApplicationContext.stop();
    }
}