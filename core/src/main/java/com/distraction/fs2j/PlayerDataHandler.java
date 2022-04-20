package com.distraction.fs2j;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.distraction.fs2j.tilemap.player.AccessoryType;
import com.distraction.fs2j.tilemap.player.Face;
import com.distraction.fs2j.tilemap.player.Skin;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import de.golfgl.gdxgamesvcs.leaderboard.ILeaderBoardEntry;

/**
 * Handles player customization persistence.
 */
public class PlayerDataHandler {

    public static final String NAME_KEY = "name";
    public static final String SKIN_KEY = "skin";
    public static final String FACE_KEY = "face";
    public static final String ACCESSORIES_KEY = "accessories";

    private Context context;

    public Skin skin = Skin.GREEN;
    public Face face = Face.NORMAL;
    public List<AccessoryType> accessories = new ArrayList<>();

    public String name;
    public List<List<ILeaderBoardEntry>> leaderboards = new ArrayList<>();

    public PlayerDataHandler(Context context) {
        this.context = context;
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
        name = prefs.getString(NAME_KEY, "");
        int diamonds = context.scoreHandler.getNumDiamonds();
        String skinKey = prefs.getString(SKIN_KEY);
        if (skinKey == null || skinKey.isEmpty()) {
            initialize();
            return;
        }
        try {
            skin = Skin.find(skinKey);
            if (diamonds < skin.getDiamonds()) save(Skin.GREEN);
        } catch (Exception e) {
            save(Skin.GREEN);
        }
        String faceKey = prefs.getString(FACE_KEY);
        try {
            face = Face.find(faceKey);
            if (diamonds < face.getDiamonds()) save(Face.NORMAL);
        } catch (Exception e) {
            save(Face.NORMAL);
        }
        String accessoryKey = prefs.getString(ACCESSORIES_KEY);
        accessories.clear();
        if (accessoryKey != null && !accessoryKey.isEmpty()) {
            String[] accessoryKeys = accessoryKey.split(",");
            for (String it : accessoryKeys) {
                try {
                    AccessoryType accessoryType = AccessoryType.find(it);
                    if (diamonds < accessoryType.getDiamonds()) {
                        save(new ArrayList<>());
                        break;
                    } else {
                        accessories.add(AccessoryType.find(it));
                    }
                } catch (Exception e) {
                    save(new ArrayList<>());
                    break;
                }
            }
        }
        Logging.info("loaded skin " + skin.key);
        Logging.info("loaded face " + face.key);
        Logging.info("loading accessories [" + accessories.stream().map(it -> it.key).collect(Collectors.joining(",")) + "]");
    }

    public void save(String name) {
        Preferences prefs = getPrefs();
        prefs.putString(NAME_KEY, name);
        Logging.info("saving name " + name);
        prefs.flush();
        this.name = name;
    }

    public void save(Skin skin) {
        Preferences prefs = getPrefs();
        prefs.putString(SKIN_KEY, skin.key);
        Logging.info("saving skin " + skin.key);
        prefs.flush();
        this.skin = skin;
    }

    public void save(Face face) {
        Preferences prefs = getPrefs();
        prefs.putString(FACE_KEY, face.key);
        Logging.info("saving face " + face.key);
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
        Logging.info("saving accessories [" + keys + "]");
        prefs.flush();
        accessories.clear();
        accessories.addAll(accessoryTypes);
    }

    public String serialize() {
        StringBuilder sb = new StringBuilder();
        sb.append(skin).append(",").append(face);
        for (AccessoryType it : accessories) sb.append(",").append(it);
        return sb.toString();
    }

    public int[] deserialize(String tag) {
        String[] parse = tag.split(",");
        int[] custom = new int[parse.length];
        custom[0] = Skin.valueOf(parse[0]).ordinal();
        custom[1] = Face.valueOf(parse[1]).ordinal();
        for (int i = 2; i < parse.length; i++)
            custom[i] = AccessoryType.valueOf(parse[i]).ordinal();
        return custom;
    }
}
