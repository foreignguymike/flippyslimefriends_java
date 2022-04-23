package com.distraction.fs2j;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AudioHandler {

    private Map<String, Music> music;
    private Map<String, Sound> sounds;

    private Set<MusicConfig> currentlyPlaying;

    private boolean muted = false;

    public AudioHandler() {
        music = new HashMap<>();
        addMusic("calm", "music/calm.mp3");
        addMusic("mystery", "music/mystery.mp3");

        sounds = new HashMap<>();
        addSound("select", "sfx/select.wav");
        addSound("activate", "sfx/activate.wav");
        addSound("deactivate", "sfx/deactivate.wav");
        addSound("complete", "sfx/complete.wav");

        currentlyPlaying = new HashSet<>();
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
            for (MusicConfig it : currentlyPlaying) {
                for (Map.Entry<String, Music> entry : music.entrySet()) {
                    if (entry.getKey().equals(it.getKey())) {
                        Music m = entry.getValue();
                        m.setVolume(it.getVolume());
                        m.play();
                    }
                }
            }
        }
        return muted;
    }

    public void playMusic(String key) {
        playMusic(key, 1f, true);
    }

    public void playMusic(String key, float volume) {
        playMusic(key, volume, true);
    }

    public void playMusic(String key, float volume, boolean looping) {
        for (Map.Entry<String, Music> entry : music.entrySet()) {
            if (entry.getKey().equals(key)) {
                Music music = entry.getValue();
                currentlyPlaying.add(new MusicConfig(key, volume, looping, -1));
                music.setVolume(volume);
                music.setLooping(looping);
                if (!muted) music.play();
            }
        }
    }

    public void playMusicLooped(String key, float volume, float start) {
        for (Map.Entry<String, Music> entry : music.entrySet()) {
            if (entry.getKey().equals(key)) {
                Music music = entry.getValue();
                music.setVolume(volume);
                if (start < 0) {
                    music.setLooping(true);
                } else {
                    music.setLooping(false);
                    music.setOnCompletionListener(it -> {
                        it.play();
                        it.setPosition(start);
                    });
                }
                currentlyPlaying.add(new MusicConfig(key, volume, music.isLooping(), start));
                if (!muted) music.play();
            }
        }
    }

    public void stopMusic(String key) {
        for (Map.Entry<String, Music> entry : music.entrySet()) {
            if (entry.getKey().equals(key)) {
                Music music = entry.getValue();
                music.stop();
                music.setOnCompletionListener(null);
                music.setVolume(1);
                music.setLooping(false);
                currentlyPlaying.removeIf(it -> it.getKey().equals(key));
            }
        }
    }

    public void stopAllMusic() {
        for (MusicConfig it : currentlyPlaying) {
            Music m = music.get(it.getKey());
            m.stop();
            m.setOnCompletionListener(null);
            m.setVolume(1);
            m.setLooping(false);
        }
        currentlyPlaying.clear();
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