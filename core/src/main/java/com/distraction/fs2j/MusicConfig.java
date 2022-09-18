package com.distraction.fs2j;

public class MusicConfig {

    private final String key;
    private final float volume;
    private final boolean looping;
    private final float start;

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
