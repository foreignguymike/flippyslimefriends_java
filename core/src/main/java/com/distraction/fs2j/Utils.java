package com.distraction.fs2j;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.lang.reflect.Array;

public class Utils {

    public static void takeScreenshot() {
//        String name = "FS2 " + UUID.randomUUID();
//        byte[] pixels = ScreenUtils.getFrameBufferPixels(0, 0, Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight(), true);
//        for (int i = 4; i < pixels.length; i += 4) {
//            pixels[i - 1] = (byte) 255;
//        }
//        Pixmap pixmap = new Pixmap(Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight(), Pixmap.Format.RGBA8888);
//        pixmap.getPixels().clear();
//        pixmap.getPixels().put(pixels);
//        PixmapIO.writePNG(Gdx.files.external(name + ".png"), pixmap);
//        pixmap.dispose();
    }

    public static int mod(int divided, int divisor) {
        return (divided % divisor + divisor) % divisor;
    }

    public static <T> boolean contains(T[] arr, T val) {
        for (T it : arr) if (it == val) return true;
        return false;
    }

    public static void setColor(SpriteBatch sb, Color c, float a) {
        sb.setColor(c.r, c.g, c.b, a);
    }

    public static float dist(float x1, float y1, float x2, float y2) {
        return (float) Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }

    public static Vector3 lerp(Vector3 v, float x, float y, float z, float amount) {
        v.x += amount * (x - v.x);
        v.y += amount * (y - v.y);
        v.z += amount * (z - v.z);
        return v;
    }

    public static void moveTo(Vector3 v, Vector3 pdest, float dist) {
        if (v.x < pdest.x) {
            v.x += dist;
            if (v.x > pdest.x) {
                v.x = pdest.x;
            }
        }
        if (v.x > pdest.x) {
            v.x -= dist;
            if (v.x < pdest.x) {
                v.x = pdest.x;
            }
        }
        if (v.y < pdest.y) {
            v.y += dist;
            if (v.y > pdest.y) {
                v.y = pdest.y;
            }
        }
        if (v.y > pdest.y) {
            v.y -= dist;
            if (v.y < pdest.y) {
                v.y = pdest.y;
            }
        }
    }

    /**
     * Move v toward pdest at dist amount.
     * The dist is applied to the direction of pdest.
     */
    public static void moveToLinear(Vector3 v, Vector3 pdest, float dist) {
        float dx = pdest.x - v.x;
        float dy = pdest.y - v.y;
        float totalDist = (float) Math.sqrt(dx * dx + dy * dy);
        float tx = (totalDist - dist) * (pdest.x - v.x) / totalDist;
        float ty = (totalDist - dist) * (pdest.y - v.y) / totalDist;
        if (v.x < pdest.x) {
            v.x += tx;
            if (v.x > pdest.x) {
                v.x = pdest.x;
            }
        }
        if (v.x > pdest.x) {
            v.x += tx;
            if (v.x < pdest.x) {
                v.x = pdest.x;
            }
        }
        if (v.y < pdest.y) {
            v.y += ty;
            if (v.y > pdest.y) {
                v.y = pdest.y;
            }
        }
        if (v.y > pdest.y) {
            v.y += ty;
            if (v.y < pdest.y) {
                v.y = pdest.y;
            }
        }
    }

    public static float diff(Vector2 v1, Vector2 v2) {
        return Math.abs(v1.x - v2.x) + Math.abs(v1.y - v2.y);
    }

    public static void setAlpha(SpriteBatch sb, float a) {
        Color c = sb.getColor();
        sb.setColor(c.r, c.g, c.b, a);
    }

    public static void drawCentered(SpriteBatch sb, TextureRegion image, float x, float y) {
        drawCentered(sb, image, x, y, image.getRegionWidth(), image.getRegionHeight());
    }

    public static void drawCentered(SpriteBatch sb, TextureRegion image, float x, float y, float width, float height) {
        sb.draw(image, x - image.getRegionWidth() / 2f, y - image.getRegionHeight() / 2f, width, height);
    }

    public static void drawHFlip(SpriteBatch sb, TextureRegion image, float x, float y) {
        drawHFlip(sb, image, x, y, image.getRegionWidth(), image.getRegionHeight());
    }

    public static void drawHFlip(SpriteBatch sb, TextureRegion image, float x, float y, float w, float h) {
        sb.draw(image, x, y, -w, h);
    }

    public static void drawVFlip(SpriteBatch sb, TextureRegion image, float x, float y) {
        sb.draw(image, x, y, image.getRegionWidth(), -image.getRegionHeight());
    }

    public static void drawVHFlip(SpriteBatch sb, TextureRegion image, float x, float y) {
        sb.draw(image, x, y, -image.getRegionWidth(), -image.getRegionHeight());
    }

    public static void drawPadded(SpriteBatch sb, TextureRegion image, float x, float y) {
        drawPadded(sb, image, x, y, 0.01f);
    }

    public static void drawPadded(SpriteBatch sb, TextureRegion image, float x, float y, float padding) {
        sb.draw(image, x, y, image.getRegionWidth() + padding, image.getRegionHeight() + padding);
    }

    public static void drawAlpha(SpriteBatch sb, TextureRegion image, float x, float y, float a) {
        float ca = sb.getColor().a;
        setAlpha(sb, a);
        sb.draw(image, x, y);
        setAlpha(sb, ca);
    }

    public static void drawRotated(SpriteBatch sb, TextureRegion image, float x, float y, float deg) {
        drawRotated(sb, image, x, y, deg, image.getRegionWidth() / 2f, image.getRegionHeight() / 2f, 1f);
    }

    public static void drawRotated(SpriteBatch sb, TextureRegion image, float x, float y, float deg, float originx, float originy, float scale) {
        sb.draw(
                image,
                x - image.getRegionWidth() / 2f,
                y - image.getRegionHeight() / 2f,
                originx,
                originy,
                1f * image.getRegionWidth(),
                1f * image.getRegionHeight(),
                scale,
                scale,
                deg
        );
    }

    public static void clearScreen(Color color) {
        Gdx.gl.glClearColor(color.r, color.g, color.b, color.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

}
