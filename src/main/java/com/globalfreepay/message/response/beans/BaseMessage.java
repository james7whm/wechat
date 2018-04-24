package com.globalfreepay.message.response.beans;

public class BaseMessage {
    // 接收方账号（收到的openid）
    private String ToUserName;
    //开发者微信号
    private String FromUsername;
    //消息创建时间（整型）
    private long CreateTime;
    //消息类型（text/music/news）
    private String MsgType;

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUsername() {
        return FromUsername;
    }

    public void setFromUsername(String fromUsername) {
        FromUsername = fromUsername;
    }

    public long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }
}
