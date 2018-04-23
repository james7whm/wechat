package com.globalfreepay.hello;


import com.globalfreepay.util.SignUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
public class SampleController {

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

    @RequestMapping(method = RequestMethod.GET,value = "/checkwechat")
    @ResponseBody
    String checkWechat(@RequestParam("signature") String signature,@RequestParam("timestamp") String timestamp,@RequestParam("nonce") String nonce,@RequestParam("echostr") String echostr){
        if(SignUtil.checkSignature(signature,timestamp,nonce)){
            return echostr;
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleController.class, args);
    }
}
