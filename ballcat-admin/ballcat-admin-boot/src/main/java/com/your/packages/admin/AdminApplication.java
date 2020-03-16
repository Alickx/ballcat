package com.your.packages.admin;

import com.hccake.ballcat.commom.log.access.annotation.EnableAccessLog;
import com.hccake.ballcat.common.job.annotation.EnableXxlJob;
import com.hccake.ballcat.common.swagger.annotation.EnableSwagger2Aggregator;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author Hccake
 */
@EnableXxlJob
@EnableSwagger2Aggregator
@EnableAccessLog
@ServletComponentScan("com.hccake.ballcat.admin.oauth.filter")
@SpringBootApplication(scanBasePackages = {"com.hccake.ballcat.admin", "com.your.packages.admin"})
@MapperScan(basePackages = {"com.hccake.ballcat.**.mapper", "com.your.packages.**.mapper"})
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }

}