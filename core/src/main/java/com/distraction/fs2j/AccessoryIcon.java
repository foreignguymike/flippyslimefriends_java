package com.distraction.fs2j;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.distraction.fs2j.tilemap.player.AccessoryType;
import com.distraction.fs2j.tilemap.player.Customizer;
import com.distraction.fs2j.tilemap.player.Face;
import com.distraction.fs2j.tilemap.player.Skin;

public class AccessoryIcon extends ImageButton {

    private final Context context;
    private final int numStars;
    private final int numDiamonds;

    private TextureRegion iconImage;
    private final Vector2 offset = new Vector2();
    public boolean locked = false;

    // requirements
    private int requiredStars;
    private int requiredDiamonds;
    private final TextureRegion star;
    private final TextureRegion diamond;
    private final TextFont starFont;
    private final TextFont diamondFont;

    public AccessoryIcon(Context context, Customizer type, float x, float y, int numStars, int numDiamonds) {
        super(context.getImage("accessoryiconbg"), x, y);
        this.context = context;
        this.numStars = numStars;
        this.numDiamonds = numDiamonds;
        star = context.getImage("starunlock");
        diamond = context.getImage("diamondunlock");
        starFont = new TextFont(context, TextFont.FontType.NORMAL2, "", true, pos.x, pos.y - 12);
        diamondFont = new TextFont(context, TextFont.FontType.NORMAL2, "", true, pos.x, pos.y - 11);
        setType(type);
        if (type != null) {
            diamondFont.setText(Integer.toString(type.getDiamonds()));
            setRequiredDiamonds(type.getDiamonds());
        }
    }

    public void setRequiredStars(int requiredStars) {
        this.requiredStars = requiredStars;
        starFont.setText(Integer.toString(requiredStars));
        locked = numStars != -1 && numStars < requiredStars || numDiamonds < requiredDiamonds;
    }

    public void setRequiredDiamonds(int requiredDiamonds) {
        this.requiredDiamonds = requiredDiamonds;
        diamondFont.setText(Integer.toString(requiredDiamonds));
        locked = numStars != -1 && numStars < requiredStars || numDiamonds < requiredDiamonds;
    }

    public void setType(Customizer type) {
        if (type != null) {
            if (type instanceof Face) iconImage = ((Face) type).getSprites(context)[0];
            if (type instanceof Skin) iconImage = ((Skin) type).getSprites(context)[0];
            if (type instanceof AccessoryType)
                iconImage = ((AccessoryType) type).getSprites(context)[0];
            setRequiredDiamonds(type.getDiamonds());
        } else {
            iconImage = null;
        }
    }

    public void setOffset(float x, float y) {
        offset.set(x, y);
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
        if (!enabled || locked) sb.setColor(0.5f, 0.5f, 0.5f, 1f);
        else sb.setColor(1, 1, 1, 1);

        if (locked) {
            sb.setColor(1, 1, 1, 1);
            if (requiredStars > 0) {
                Utils.drawCentered(sb, star, pos.x, pos.y + 6);
                starFont.render(sb);
            } else if (requiredDiamonds > 0) {
                Utils.drawCentered(sb, diamond, pos.x, pos.y + 6);
                diamondFont.render(sb);
            }
        } else if (iconImage != null) {
            sb.draw(iconImage, pos.x - iconImage.getRegionWidth() / 2f + offset.x, pos.y - iconImage.getRegionHeight() / 2f + offset.y);
        }
    }
}
