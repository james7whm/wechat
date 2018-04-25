package com.globalfreepay;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages = {"com.globalfreepay"})
public class WechatApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(WechatApplication.class, args);
    }
}
