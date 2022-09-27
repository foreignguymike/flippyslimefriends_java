package com.distraction.fs2j;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import java.util.HashMap;
import java.util.Map;

public class AudioHandler {

    private final Map<String, Music> music;
    private final Map<String, Sound> sounds;

    private MusicConfig currentlyPlaying;

    private boolean muted = false;

    public AudioHandler() {
        music = new HashMap<>();
        addMusic("calm", "music/calm.mp3");
        addMusic("mystery", "music/mystery.mp3");

        sounds = new HashMap<>();
        addSound("select", "sfx/select.wav");
        addSound("selectshort", "sfx/selectshort.wav");
        addSound("activate", "sfx/activate.wav");
        addSound("deactivate", "sfx/deactivate.wav");
        addSound("complete", "sfx/complete.wav");
    }

    private void addMusic(String key, String fileName) {
        music.put(key, Gdx.audio.newMusic(Gdx.files.internal(fileName)));
    }

    private void addSound(String key, String fileName) {
        sounds.put(key, Gdx.audio.newSound(Gdx.files.internal(fileName)));
    }

    public boolean isMuted() {
        return muted;
    }

    public boolean toggleMute() {
        muted = !muted;
        if (muted) {
            for (Music it : music.values()) it.setVolume(0f);
            for (Sound it : sounds.values()) it.stop();
        } else {
            if (currentlyPlaying != null) {
                currentlyPlaying.play();
            }
        }
        return muted;
    }

    public void playMusic(String key, float volume, boolean looping) {
        Music newMusic = music.get(key);
        if (newMusic == null) {
            throw new IllegalArgumentException("music does not exist: " + key);
        }
        if (currentlyPlaying != null && newMusic != currentlyPlaying.getMusic()) {
            stopMusic();
        }
        currentlyPlaying = new MusicConfig(music.get(key), volume, looping);
        if (!muted) currentlyPlaying.play();
    }

    public void stopMusic() {
        if (currentlyPlaying != null) {
            currentlyPlaying.stop();
        }
    }

    public void playSound(String key) {
        playSound(key, 1);
    }

    public void playSound(String key, float volume) {
        if (muted) return;
        for (Map.Entry<String, Sound> entry : sounds.entrySet()) {
            if (entry.getKey().equals(key)) entry.getValue().play(volume);
        }
    }

}