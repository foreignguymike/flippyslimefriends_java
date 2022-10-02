package com.distraction.fs2j;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import java.util.HashMap;
import java.util.Map;

public class AudioHandler {

    public enum AudioState {
        ON,
        MUSIC,
        SFX,
        OFF
    }

    private final Map<String, Music> music;
    private final Map<String, Sound> sounds;

    private MusicConfig currentlyPlaying;

    private AudioState audioState = AudioState.ON;
    private boolean musicMuted = false;
    private boolean sfxMuted = false;

    public AudioHandler() {
        music = new HashMap<>();
        addMusic("calm", "music/calm.mp3");
        addMusic("mystery", "music/mystery.mp3");
        addMusic("ruins", "music/ruins.mp3");
        addMusic("tundra", "music/tundra.mp3");

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

    public AudioState getAudioState() {
        return audioState;
    }

    public AudioState nextAudioState() {
        if (audioState == AudioHandler.AudioState.ON) {
            audioState = AudioState.MUSIC;
            setMuteState(false, true);
        } else if (audioState == AudioHandler.AudioState.MUSIC) {
            audioState = AudioState.SFX;
            setMuteState(true, false);
        } else if (audioState == AudioHandler.AudioState.SFX) {
            audioState = AudioState.OFF;
            setMuteState(true, true);
        } else {
            audioState = AudioState.ON;
            setMuteState(false, false);
        }
        return audioState;
    }

    private void setMuteState(boolean musicMuted, boolean sfxMuted) {
        this.musicMuted = musicMuted;
        this.sfxMuted = sfxMuted;
        if (currentlyPlaying != null) {
            if (musicMuted) {
                currentlyPlaying.mute();
            } else {
                currentlyPlaying.play();
            }
        }
        if (sfxMuted) {
            for (Sound it : sounds.values()) it.stop();
        }
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
        if (!musicMuted) currentlyPlaying.play();
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
        if (sfxMuted) return;
        for (Map.Entry<String, Sound> entry : sounds.entrySet()) {
            if (entry.getKey().equals(key)) entry.getValue().play(volume);
        }
    }

}