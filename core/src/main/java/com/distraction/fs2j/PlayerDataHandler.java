package com.distraction.fs2j;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.distraction.fs2j.tilemap.player.AccessoryType;
import com.distraction.fs2j.tilemap.player.Face;
import com.distraction.fs2j.tilemap.player.Skin;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles player customization persistence.
 */
public class PlayerDataHandler {

    public static final String SKIN_KEY = "skin";
    public static final String FACE_KEY = "face";
    public static final String ACCESSORIES_KEY = "accessories";

    public Skin skin = Skin.GREEN;
    public Face face = Face.NORMAL;
    public List<AccessoryType> accessories = new ArrayList<>();

    public PlayerDataHandler() {
        load();
    }

    private Preferences getPrefs() {
        return Gdx.app.getPreferences("player");
    }

    private void initialize() {
        Preferences prefs = getPrefs();
        prefs.putString(SKIN_KEY, Skin.GREEN.key);
        prefs.putString(FACE_KEY, Face.NORMAL.key);
        prefs.putString(ACCESSORIES_KEY, "");
        prefs.flush();
        load();
    }

    private void load() {
        Preferences prefs = getPrefs();
        String skinKey = prefs.getString(SKIN_KEY);
        if (skinKey == null || skinKey.isEmpty()) {
            initialize();
            return;
        }
        skin = Skin.find(skinKey);
        String faceKey = prefs.getString(FACE_KEY);
        face = Face.find(faceKey);
        String accessoryKey = prefs.getString(ACCESSORIES_KEY);
        accessories.clear();
        if (accessoryKey != null && !accessoryKey.isEmpty()) {
            String[] accessoryKeys = accessoryKey.split(",");
            for (String it : accessoryKeys) {
                accessories.add(AccessoryType.find(it));
            }
        }
        System.out.println("loaded skin " + skin.key);
        System.out.println("loaded face " + face.key);
        System.out.println("loading accessories [" + accessories.stream().map(it -> it.key).collect(Collectors.joining(",")) + "]");
    }

    public void save(Skin skin) {
        Preferences prefs = getPrefs();
        prefs.putString(SKIN_KEY, skin.key);
        System.out.println("saving skin " + skin.key);
        prefs.flush();
        this.skin = skin;
    }

    public void save(Face face) {
        Preferences prefs = getPrefs();
        prefs.putString(FACE_KEY, face.key);
        System.out.println("saving face " + face.key);
        prefs.flush();
        load();
        this.face = face;
    }

    public void save(List<AccessoryType> accessoryTypes) {
        Preferences prefs = getPrefs();
        StringBuilder keys = new StringBuilder();
        for (int i = 0; i < accessoryTypes.size(); i++) {
            if (accessoryTypes.get(i) != null) {
                keys.append(accessoryTypes.get(i).key);
                if (i < accessoryTypes.size() - 1) keys.append(",");
            }
        }
        prefs.putString(ACCESSORIES_KEY, keys.toString());
        System.out.println("saving accessories [" + keys + "]");
        prefs.flush();
        accessories.clear();
        accessories.addAll(accessoryTypes);
    }
}
