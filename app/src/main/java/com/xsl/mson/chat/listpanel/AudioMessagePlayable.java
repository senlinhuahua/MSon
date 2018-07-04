package com.xsl.mson.chat.listpanel;

import com.netease.nimlib.sdk.msg.attachment.AudioAttachment;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.xsl.mson.chat.viewhodler.BaseAudioControl;

public class AudioMessagePlayable implements BaseAudioControl.Playable {

    private IMMessage message;

    public IMMessage getMessage() {
        return message;
    }

    public AudioMessagePlayable(IMMessage playableMessage) {
        this.message = playableMessage;
    }

    @Override
    public long getDuration() {
        return ((AudioAttachment) message.getAttachment()).getDuration();
    }

    @Override
    public String getPath() {
        return ((AudioAttachment) message.getAttachment()).getPath();
    }

    @Override
    public boolean isAudioEqual(BaseAudioControl.Playable audio) {
        if (AudioMessagePlayable.class.isInstance(audio)) {
            return message.isTheSame(((AudioMessagePlayable) audio).getMessage());
        } else {
            return false;
        }
    }


}
