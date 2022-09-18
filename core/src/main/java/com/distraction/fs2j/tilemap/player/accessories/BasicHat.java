package com.distraction.fs2j.tilemap.player.accessories;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.fs2j.AnimationSet;
import com.distraction.fs2j.tilemap.player.AccessoryType;
import com.distraction.fs2j.tilemap.player.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Holds all infos about basic hats.
 */
public class BasicHat extends Accessory {

    private static final Map<AccessoryType, int[]> offsetMap;
    private static final int[] BAMBOO_HAT_OFFSETS_DEFAULT = new int[]{0, 13, 12, 10, 17};

    static {
        offsetMap = new HashMap<>();
        offsetMap.put(AccessoryType.WIZARD_HAT, new int[]{-1, 14, 13, 10, 18});
        offsetMap.put(AccessoryType.HALO, new int[]{0, 25, 25, 21, 27});
        offsetMap.put(AccessoryType.BAMBOO_HAT, BAMBOO_HAT_OFFSETS_DEFAULT);
        offsetMap.put(AccessoryType.BAMBOO_HAT_GREEN, BAMBOO_HAT_OFFSETS_DEFAULT);
        offsetMap.put(AccessoryType.BAMBOO_HAT_BLUE, BAMBOO_HAT_OFFSETS_DEFAULT);
        offsetMap.put(AccessoryType.BAMBOO_HAT_RED, BAMBOO_HAT_OFFSETS_DEFAULT);
    }

    private final TextureRegion image;
    private final int[] offsets;

    public BasicHat(Player player, AccessoryType type) {
        super(player);
        image = type.getSprites(player.context)[0];
        offsets = offsetMap.get(type);
        offset.x = offsets[0];
    }

    public void update(float dt) {
        AnimationSet animationSet = player.playerRenderer.animationSet;
        switch (animationSet.currentAnimationKey) {
            case Player.IDLE:
                offset.y = animationSet.currentAnimation.currentFrame() == 0 ? offsets[1] : offsets[2];
                break;
            case Player.CROUCH:
                offset.y = offsets[3];
                break;
            case Player.JUMP:
                offset.y = offsets[4];
                break;
        }
    }

    @Override
    public void renderFront(SpriteBatch sb) {
        normalRender(sb, image);
    }

    @Override
    public void renderBehind(SpriteBatch sb) {
        normalRender(sb, image);
    }

}
