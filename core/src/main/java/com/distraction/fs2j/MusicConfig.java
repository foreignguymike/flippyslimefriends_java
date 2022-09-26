package com.distraction.fs2j;

import com.badlogic.gdx.audio.Music;

public class MusicConfig {

    private final Music music;
    private final float volume;
    private final boolean looping;
    private final float start;

    public MusicConfig(Music music, float volume, boolean looping, float start) {
        this.music = music;
        this.volume = volume;
        this.looping = looping;
        this.start = start;
        if (start > 0 && looping) {
            music.setOnCompletionListener(it -> {
                it.play();
                it.setPosition(start);
            });
        }
    }

    public Music getMusic() {
        return music;
    }

    public void play() {
        music.setVolume(volume);
        music.setLooping(looping && start <= 0);
        music.play();
    }

    public void stop() {
        music.setVolume(1);
        music.setLooping(false);
        music.setOnCompletionListener(null);
        music.stop();
    }
}
