package com.example.msorder;

import com.example.mscommon.error.ErrorConfig;
import com.example.mscommon.error.feign.FeignErrorDecoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableFeignClients
//@SpringBootApplication(scanBasePackages = {
//                                            "com.training.spring",
//                                            "com.ms.common.error"
//})
@ServletComponentScan
@Import({ErrorConfig.class, FeignErrorDecoder.class})
public class MsOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsOrderApplication.class, args);
    }

}
