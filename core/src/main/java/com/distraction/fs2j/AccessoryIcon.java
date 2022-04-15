package com.distraction.fs2j;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.distraction.fs2j.tilemap.player.AccessoryType;
import com.distraction.fs2j.tilemap.player.Customizer;
import com.distraction.fs2j.tilemap.player.Face;
import com.distraction.fs2j.tilemap.player.Skin;

public class AccessoryIcon extends ImageButton {

    private Context context;
    private Customizer type;
    private int numStars;
    private int numDiamonds;

    private TextureRegion iconImage;
    private Vector2 offset = new Vector2();
    public boolean disabled = false;
    public boolean locked = false;

    // requirements
    private TextureRegion star;
    private TextureRegion diamond;
    private int requiredStars;
    private int requiredDiamonds;
    private NumberFont starFont;
    private NumberFont diamondFont;

    public AccessoryIcon(Context context, Customizer type, float x, float y, int numStars, int numDiamonds) {
        super(context.getImage("accessoryiconbg"), x, y);
        this.context = context;
        this.numStars = numStars;
        this.numDiamonds = numDiamonds;
        star = context.getImage("starunlock");
        diamond = context.getImage("diamondunlock");
        starFont = new NumberFont(context, true, NumberFont.NumberSize.MEDIUM);
        diamondFont = new NumberFont(context, true, NumberFont.NumberSize.MEDIUM);
        setType(type);
        if (type != null) {
            diamondFont.setNum(type.getDiamonds());
            setRequiredDiamonds(type.getDiamonds());
        }
    }

    public void setRequiredStars(int requiredStars) {
        this.requiredStars = requiredStars;
        starFont.setNum(requiredStars);
        locked = numStars != -1 && numStars < requiredStars || numDiamonds < requiredDiamonds;
    }

    public void setRequiredDiamonds(int requiredDiamonds) {
        this.requiredDiamonds = requiredDiamonds;
        diamondFont.setNum(requiredDiamonds);
        locked = numStars != -1 && numStars < requiredStars || numDiamonds < requiredDiamonds;
    }

    public void setType(Customizer type) {
        this.type = type;
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
        if (disabled || locked) sb.setColor(0.5f, 0.5f, 0.5f, 1f);
        else sb.setColor(1, 1, 1, 1);
        super.render(sb);

        if (locked) {
            sb.setColor(1, 1, 1, 1);
            if (requiredStars > 0) {
                Utils.drawCentered(sb, star, pos.x, pos.y + 6);
                starFont.render(sb, pos.x, pos.y - 7);
            } else if (requiredDiamonds > 0) {
                Utils.drawCentered(sb, diamond, pos.x, pos.y + 6);
                diamondFont.render(sb, pos.x, pos.y - 6);
            }
        } else if (iconImage != null) {
            sb.draw(iconImage, pos.x - iconImage.getRegionWidth() / 2f + offset.x, pos.y - iconImage.getRegionHeight() / 2f + offset.y);
        }
    }
}
