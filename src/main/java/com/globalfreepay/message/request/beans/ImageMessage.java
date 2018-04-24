package com.globalfreepay.message.request.beans;

/**
 * 图片消息类
 */
public class ImageMessage extends BaseMessage{

    private String PicUrl;

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }
}
