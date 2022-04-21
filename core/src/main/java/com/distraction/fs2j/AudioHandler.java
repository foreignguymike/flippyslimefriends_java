package com.distraction.fs2j;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import java.util.HashMap;
import java.util.Map;

public class AudioHandler {

    private Map<String, Music> music;
    private Map<String, Sound> sounds;

    public AudioHandler() {
        sounds = new HashMap<>();
        sounds.put("activate", Gdx.audio.newSound(Gdx.files.internal("activate.wav")));
    }

    public void playMusic(String key) {
        playMusic(key, true);
    }

    public void playMusic(String key, boolean looping) {
        for (Map.Entry<String, Music> entry : music.entrySet()) {
            if (entry.getKey().equals(key)) {
                entry.getValue().play();
                entry.getValue().setLooping(true);
            }
        }
    }

    public void stopMusic(String key) {
        for (Map.Entry<String, Music> entry : music.entrySet()) {
            if (entry.getKey().equals(key)) entry.getValue().stop();
        }
    }

    public void playSound(String key) {
        for (Map.Entry<String, Sound> entry : sounds.entrySet()) {
            if (entry.getKey().equals(key)) entry.getValue().play();
        }
    }

}
