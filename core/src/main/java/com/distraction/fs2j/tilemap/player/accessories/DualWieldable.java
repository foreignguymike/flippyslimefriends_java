package com.distraction.fs2j.tilemap.player.accessories;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.distraction.fs2j.tilemap.data.GameColor;
import com.distraction.fs2j.tilemap.player.AccessoryType;
import com.distraction.fs2j.tilemap.player.Player;

public class DualWieldable extends Wieldable {

   public DualWieldable(Player player, AccessoryType type, float xoffset, float yoffset) {
      super(player, type, xoffset, yoffset);
   }

   @Override
   public void update(float dt) {
      super.update(dt);
      offset.x = player.right() ? -xoffset : xoffset + image.getRegionWidth();
   }

   @Override
   public void renderFront(SpriteBatch sb) {
      sb.setColor(GameColor.WHITE);
      Vector3 isop = player.isop;
      sb.draw(
              player.forward() ? image : image2 != null ? image2 : image,
              isop.x + offset.x - image.getRegionWidth() / 2f,
              isop.y + player.p.z + offset.y - image.getRegionHeight() / 2f,
              image.getRegionWidth() / 2f,
              image.getRegionHeight() / 2f,
              (player.right() ? 1f : -1f) * image.getRegionWidth(),
              1f * image.getRegionHeight(),
              1f,
              1f,
              deg
      );
   }

   @Override
   public void renderBehind(SpriteBatch sb) {
      sb.setColor(GameColor.WHITE);
      Vector3 isop = player.isop;
      sb.draw(
              player.forward() ? image : image2 != null ? image2 : image,
              isop.x - (player.right() ? offset.x : image.getRegionWidth() / 2f) - image.getRegionWidth() / 2f,
              isop.y + player.p.z + offset.y - image.getRegionHeight() / 2f + (player.forward() ? 2 : -2),
              image.getRegionWidth() / 2f,
              image.getRegionHeight() / 2f,
              (player.right() ? 1f : -1f) * image.getRegionWidth(),
              1f * image.getRegionHeight(),
              1f,
              1f,
              deg
      );
   }

}
