package com.globalfreepay.util;


import com.globalfreepay.message.response.beans.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageUtil {
    //请求消息类型：文本
    public static final String REQ_MESSAGE_TYPE_TEXT = "text";
    //请求消息类型：图片
    public static final String REQ_MESSAGE_TYPE_IMAGE = "image";
    //请求消息类型：语音
    public static final String REQ_MESSAGE_TYPE_VOICE = "voice";
    //请求消息类型：视频
    public static final String REQ_MESSAGE_TYPE_VIDEO = "video";
    //请求消息类型：地理位置
    public static final String REQ_MESSAGE_TYPE_LOCATION = "location";
    //请求消息类型：链接
    public static final String REQ_MESSAGE_TYPE_LINK = "link";
    //请求消息类型：事件推送
    public static final String REQ_MESSAGE_TYPE_EVENT = "event";


    //事件类型：subscribe（订阅）
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
    //事件类型：unsubscribe（取消订阅）
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
    //事件类型：scan（关注用户扫描带参数二维码）
    public static final String EVENT_TYPE_SCAN = "scan";
    //事件类型：LOCATION（上报地理位置）
    public static final String EVENT_TYPE_LOCATION = "LOCATION";
    //事件类型：click（自定义菜单）
    public static final String EVENT_TYPE_CLICK = "click";


    //响应消息类型：文本
    public static final String RESP_MESSAGE_TYPE_TEXT = "text";
    //响应消息类型：图片
    public static final String RESP_MESSAGE_TYPE_IMAGE = "image";
    //响应消息类型：语音
    public static final String RESP_MESSAGE_TYPE_VOICE = "voice";
    //响应消息类型：视频
    public static final String RESP_MESSAGE_TYPE_VIDEO = "video";
    //响应消息类型：音乐
    public static final String RESP_MESSAGE_TYPE_MUSIC = "music";
    //响应消息类型：图文
    public static final String RESP_MESSAGE_TYPE_NEWS = "news";

    public static Map<String, String> parseXML(HttpServletRequest request) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        InputStream inputStream = request.getInputStream();
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        Element root = document.getRootElement();
        List<Element> elementList = root.elements();
        for (Element e:
             elementList) {
            map.put(e.getName(),e.getText());
        }
        inputStream.close();
        return map;
    }

    private static XStream xStream = new XStream(new XppDriver(){
        @Override
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out){
                boolean cdata = true;
                public void startNode(String name, Class clazz){
                    super.startNode(name, clazz);
                }
                protected void writeText(QuickWriter writer, String text){
                    if(cdata){
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    }else {
                        writer.write(text);
                    }
                }
            };
        }
    });

    public static String messageToXML(TextMessage textMessage){
        xStream.alias("xml",textMessage.getClass());
        return xStream.toXML(textMessage);
    }

    public static String messageToXML(ImageMessage imageMessage){
        xStream.alias("xml",imageMessage.getClass());
        return xStream.toXML(imageMessage);
    }

    public static String messageToXML(VideoMessage videoMessage){
        xStream.alias("xml",videoMessage.getClass());
        return xStream.toXML(videoMessage);
    }

    public static String messageToXML(MusicMessage musicMessage){
        xStream.alias("xml",musicMessage.getClass());
        return xStream.toXML(musicMessage);
    }

    public static String messageToXML(NewsMessage newsMessage){
        xStream.alias("xml",newsMessage.getClass());
        return xStream.toXML(newsMessage);
    }
}
