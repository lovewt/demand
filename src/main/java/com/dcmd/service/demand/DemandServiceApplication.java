package com.dcmd.service.demand;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@MapperScan(basePackages = {"com.dcmd.service.demand.mapper"})
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
@EnableHystrix
@EnableHystrixDashboard
@Configuration
@EnableCaching
@ComponentScan(basePackages = {"com.dcmd.service.demand", "com.dcmd.common.core"})
public class DemandServiceApplication {
 
    private final static Logger logger = LoggerFactory.getLogger(com.dcmd.service.demand.DemandServiceApplication.class);

    public static void main(String[] args) {

        logger.info("服务启动开始");
        try {
            SpringApplication.run(com.dcmd.service.demand.DemandServiceApplication.class, args);
        } catch (Exception e) {

            logger.info("服务启动异常，异常原因：", e);
            return;
        }
        logger.info("服务启动结束");

    }

    /**
     * 配置文件上传大小
     * @return

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大 KB,MB
        factory.setMaxFileSize("10240KB");
        /// 设置总上传数据总大小
        factory.setMaxRequestSize("102400KB");
        return factory.createMultipartConfig();
    }
     */
}
