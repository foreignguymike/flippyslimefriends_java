package com.distraction.fs2j;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;

public class AnimationSet {

    public HashMap<String, Animation> set = new HashMap<>();
    public String currentAnimationKey = null;
    public Animation currentAnimation = null;

    public void addAnimation(String key, Animation value) {
        set.put(key, value);
    }

    public void setAnimation(String key) {
        if (key.equals(currentAnimationKey)) return;
        Animation animation = set.get(key);
        if (animation != null) {
            currentAnimationKey = key;
            currentAnimation = animation;
            currentAnimation.reset();
        } else {
            throw new IllegalArgumentException("invalid animation");
        }
    }

    public void update(float dt) {
        currentAnimation.update(dt);
    }

    public TextureRegion getImage() {
        return currentAnimation.getImage();
    }

}
