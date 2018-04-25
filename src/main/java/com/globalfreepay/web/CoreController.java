package com.globalfreepay.web;

import com.globalfreepay.core.CoreService;
import com.globalfreepay.util.SignUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CoreController {
    @Resource
    private CoreService coreService;

    @RequestMapping(method = RequestMethod.GET,value = "/")
    @ResponseBody
    String checkWechat(@RequestParam("signature") String signature, @RequestParam("timestamp") String timestamp, @RequestParam("nonce") String nonce, @RequestParam("echostr") String echostr){
        if(SignUtil.checkSignature(signature,timestamp,nonce)){
            return echostr;
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/")
    @ResponseBody
    String message(@RequestParam("signature") String signature, @RequestParam("timestamp") String timestamp, @RequestParam("nonce") String nonce, HttpServletRequest request){
        if(SignUtil.checkSignature(signature,timestamp,nonce)){
            String respXml = coreService.processRequest(request);
        }
        return null;
    }
}
