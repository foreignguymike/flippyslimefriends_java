package com.distraction.fs2j;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;
import java.util.Map;

public class AudioButton extends IconButton {

    private static final Map<AudioHandler.AudioState, String> audioStateMap;

    static {
        audioStateMap = new HashMap<>();
        audioStateMap.put(AudioHandler.AudioState.ON, "musicsfxicon");
        audioStateMap.put(AudioHandler.AudioState.MUSIC, "musicicon");
        audioStateMap.put(AudioHandler.AudioState.SFX, "sfxicon");
        audioStateMap.put(AudioHandler.AudioState.OFF, "officon");
    }

    private final Context context;

    private AudioHandler.AudioState state;

    public AudioButton(Context context, AudioHandler.AudioState state, float x, float y, float padding) {
        super(context.getImage("pixel"), context.getImage("iconbuttonbg"), x, y, padding);
        this.context = context;
        setState(state);
    }

    public void setState(AudioHandler.AudioState state) {
        this.state = state;
        setIconImage(getStateImage(state));
    }

    public void nextState() {
        if (state == AudioHandler.AudioState.ON) {
            setState(AudioHandler.AudioState.MUSIC);
        } else if (state == AudioHandler.AudioState.MUSIC) {
            setState(AudioHandler.AudioState.SFX);
        } else if (state == AudioHandler.AudioState.SFX) {
            setState(AudioHandler.AudioState.OFF);
        } else {
            setState(AudioHandler.AudioState.ON);
        }
    }

    private TextureRegion getStateImage(AudioHandler.AudioState state) {
        return context.getImage(audioStateMap.get(state));
    }

}
