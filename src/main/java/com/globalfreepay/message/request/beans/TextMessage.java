package com.globalfreepay.message.request.beans;

/**
 *文本消息类
 */
public class TextMessage extends BaseMessage {
    //消息内容
    private String Context;

    public String getContext() {
        return Context;
    }

    public void setContext(String context) {
        Context = context;
    }
}
