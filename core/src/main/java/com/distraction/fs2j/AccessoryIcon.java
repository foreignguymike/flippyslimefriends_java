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

    private TextureRegion iconImage;
    private Vector2 offset = new Vector2();
    public boolean disabled = false;
    public boolean locked = false;

    // requirements
    private TextureRegion star;
    private TextureRegion diamond;
    private int stars;
    private int diamonds;
    private NumberFont starFont;
    private NumberFont diamondFont;

    public AccessoryIcon(Context context, Customizer type, float x, float y) {
        super(context.getImage("accessoryiconbg"), x, y);
        this.context = context;
        star = context.getImage("starunlock");
        diamond = context.getImage("diamondunlock");
        starFont = new NumberFont(context, true, NumberFont.NumberSize.MEDIUM);
        diamondFont = new NumberFont(context, true, NumberFont.NumberSize.MEDIUM);
        setType(type);
        if (type != null) {
            diamondFont.setNum(type.getDiamonds());
            setDiamonds(type.getDiamonds());
        }
    }

    public void setStars(int stars) {
        this.stars = stars;
        starFont.setNum(stars);
        locked = context.scoreHandler.getNumStars() < stars || context.scoreHandler.getNumDiamonds() < diamonds;
    }

    public void setDiamonds(int diamonds) {
        this.diamonds = diamonds;
        diamondFont.setNum(diamonds);
        locked = context.scoreHandler.getNumStars() < stars || context.scoreHandler.getNumDiamonds() < diamonds;
    }

    public void setType(Customizer type) {
        this.type = type;
        if (type != null) {
            if (type instanceof Face) iconImage = ((Face) type).getSprites(context)[0];
            if (type instanceof Skin) iconImage = ((Skin) type).getSprites(context)[0];
            if (type instanceof AccessoryType)
                iconImage = ((AccessoryType) type).getSprites(context)[0];
            setDiamonds(type.getDiamonds());
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
            if (stars > 0) {
                Utils.drawCentered(sb, star, pos.x, pos.y + 6);
                starFont.render(sb, pos.x, pos.y - 7);
            } else if (diamonds > 0) {
                Utils.drawCentered(sb, diamond, pos.x, pos.y + 6);
                diamondFont.render(sb, pos.x, pos.y - 6);
            }
        } else if (iconImage != null) {
            sb.draw(iconImage, pos.x - iconImage.getRegionWidth() / 2f + offset.x, pos.y - iconImage.getRegionHeight() / 2f + offset.y);
        }
    }
}
