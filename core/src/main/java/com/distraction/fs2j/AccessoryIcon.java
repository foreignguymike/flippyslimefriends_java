package com.distraction.fs2j;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AccessoryIcon extends ImageButton {

   private TextureRegion iconBg;

   public AccessoryIcon(Context context, TextureRegion image, float x, float y) {
      super(image, x, y);
      iconBg = context.getImage("accessoryiconbg");
   }

   @Override
   public void render(SpriteBatch sb) {
      sb.draw(iconBg, pos.x - width / 2, pos.y - height / 2);
      super.render(sb);
   }
}
