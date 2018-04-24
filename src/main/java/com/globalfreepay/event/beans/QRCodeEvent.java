package com.globalfreepay.event.beans;

/**
 * 扫描带参数二维码事件
 */
public class QRCodeEvent extends BaseEvent {
    //事件key值
    private String EventKey;
    //用于换取二维码图片
    private String Ticket;
}
