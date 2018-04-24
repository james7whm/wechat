package com.globalfreepay.message.response.beans;

public class VoiceMessage extends BaseMessage {
    private Voice voice;

    public Voice getVoice() {
        return voice;
    }

    public void setVoice(Voice voice) {
        this.voice = voice;
    }
}
