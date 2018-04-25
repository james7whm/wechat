package com.globalfreepay.core.impls;

import com.globalfreepay.core.CoreService;

import com.globalfreepay.message.response.beans.TextMessage;
import com.globalfreepay.util.MessageUtil;
;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@Service
public class CoreServiceImpl implements CoreService{
    @Override
    public String processRequest(HttpServletRequest request){
        //XML格式的消息数据
        String respXml = null;
        //默认返回的文本消息内容
        String respContent = "未知的消息类型";

        try {
            //调用parseXML解析request请求
            Map<String, String> requestMap = MessageUtil.parseXML(request);
            //发送方账号
            String fromUserName = requestMap.get("FromUserName");
            //开发者微信号
            String toUserName = requestMap.get("ToUserName");
            //消息类型
            String msgType = requestMap.get("MsgType");

            TextMessage textMessage = new TextMessage();
            textMessage.setFromUsername(fromUserName);
            textMessage.setToUserName(toUserName);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

            switch (msgType){
                case MessageUtil.REQ_MESSAGE_TYPE_TEXT:
                    respContent = "您发送的是文本消息！";
                    break;
                case MessageUtil.REQ_MESSAGE_TYPE_IMAGE:
                    respContent = "您发送的是图片消息！";
                    break;
                case MessageUtil.REQ_MESSAGE_TYPE_VOICE:
                    respContent = "您发送的是语音消息！";
                    break;
                case MessageUtil.REQ_MESSAGE_TYPE_VIDEO:
                    respContent = "您发送的是视频消息！";
                    break;
                case MessageUtil.REQ_MESSAGE_TYPE_LOCATION:
                    respContent = "您发送的是地理位置消息！";
                    break;
                case MessageUtil.REQ_MESSAGE_TYPE_LINK:
                    respContent = "您发送的是链接消息！";
                    break;
                case MessageUtil.REQ_MESSAGE_TYPE_EVENT:
                    String eventType =  requestMap.get("EVENT");
                    if(eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)){
                        respContent = "感谢您的关注！";
                    }else if(eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)){

                    }else if(eventType.equals(MessageUtil.EVENT_TYPE_SCAN)){

                    }else if(eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)){

                    }else if(eventType.equals(MessageUtil.EVENT_TYPE_CLICK)){

                    }
                    break;
            }
            textMessage.setContent(respContent);
            respXml = MessageUtil.messageToXML(textMessage);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return respXml;
    }
}
