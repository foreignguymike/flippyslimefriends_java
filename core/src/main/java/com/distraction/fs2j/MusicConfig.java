package com.distraction.fs2j;

import com.badlogic.gdx.audio.Music;

public class MusicConfig {

    private String key;
    private float volume;
    private boolean looping;
    private float start;

    public MusicConfig(String key, float volume, boolean looping, float start) {
        this.key = key;
        this.volume = volume;
        this.looping = looping;
        this.start = start;
    }

    public String getKey() {
        return key;
    }

    public float getVolume() {
        return volume;
    }

    public boolean isLooping() {
        return looping;
    }

    public float getStart() {
        return start;
    }
}
