package com.distraction.fs2j.tilemap.player;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.distraction.fs2j.Animation;
import com.distraction.fs2j.AnimationSet;
import com.distraction.fs2j.BreathingImage;
import com.distraction.fs2j.Context;
import com.distraction.fs2j.Utils;
import com.distraction.fs2j.tilemap.Tile;
import com.distraction.fs2j.tilemap.TileMap;
import com.distraction.fs2j.tilemap.data.Direction;
import com.distraction.fs2j.tilemap.player.accessories.Accessory;
import com.distraction.fs2j.tilemap.tileobjects.Arrow;
import com.distraction.fs2j.tilemap.tileobjects.Bubble;
import com.distraction.fs2j.tilemap.tileobjects.Ice;
import com.distraction.fs2j.tilemap.tileobjects.SuperJump;
import com.distraction.fs2j.tilemap.tileobjects.Teleport;
import com.distraction.fs2j.tilemap.tileobjects.TileObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Player extends TileObject implements Tile.TileMoveListener {

    public static final int SPRITE_WIDTH = 30;
    public static final int SPRITE_HEIGHT = 30;
    public static final float BASELINE = -3f;
    public static final float BUBBLE_HEIGHT = 40f;
    public static final float BUBBLE_HEIGHT_SPEED = 50f;
    public static final float BUBBLE_DROP_SPEED = 300f;
    public static final float TELEPORT_TIME_LIMIT = 0.75f;

    public static final float SUPER_JUMP_MULTIPLIER = 2f;
    public static final float SLIDING_MULTIPLIER = 2.5f;

    public static final String IDLE = "idle";
    public static final String JUMP = "jump";
    public static final String CROUCH = "crouch";

    public interface MoveListener {
        void onMoved();

        void onIllegal();
    }

    public List<Player> players = new ArrayList<>();

    private MoveListener moveListener;

    public PlayerRenderer playerRenderer = new PlayerRenderer();

    private float jumpHeight = 40f;
    public float totalDist = 0f;
    public boolean moving = false;
    private boolean sliding = false;
    private boolean superjump = false;
    private boolean teleporting = false;
    private boolean justTeleported = false;
    private float teleportSpeed = 0f;
    private Direction direction = Direction.RIGHT;
    private float teleportTimer = 0f;

    private boolean selected = false;
    private float selectedTimer = 0f;

    public boolean bubbling = false;
    public boolean dropping = false;
    public boolean canDrop = false;

    public Player(Context context, TileMap tileMap, MoveListener moveListener, int row, int col, boolean bubbling) {
        super(context, tileMap);
        if (tileMap == null) return;

        this.moveListener = moveListener;
        this.bubbling = bubbling;
        setPositionFromTile(row, col);
        if (!bubbling) tileMap.toggleTile(row, col);
        p.z = BASELINE;
        pdest.set(p);
        speed = TileMap.TILE_SIZE * 1.85f;

        currentTile = tileMap.getTile(row, col);
        if (currentTile != null) currentTile.lock = false;
        for (Tile it : tileMap.map) {
            if (it != null && it.isMovingTile()) {
                it.moveListeners.add(this);
            }
        }
    }

    public void showSelected(boolean selected) {
        this.selected = selected;
        selectedTimer = 0f;
    }

    private void updateCanDrop() {
        canDrop = !moving
                && bubbling
                && p.z == BASELINE + BUBBLE_HEIGHT
                && tileMap.isValidTile(row, col)
                && !isTileBlocked(row, col);
    }

    public void dropBubble() {
        if (canDrop && atDestination()) {
            Tile tile = tileMap.getTile(row, col);
            if (tile != null) tile.lock = true;
            dropping = true;
        }
    }

    // handle movement
    public void moveTile(int drow, int dcol) {
        // ignore movement while still moving to destination
        if (moving) return;

        // if in bubble, ignore everything, just move
        if (!bubbling) {

            // ignore movement to invalid tiles
            // only valid for manual movement, players can still slide off the map
            if (!sliding && !superjump && !tileMap.isValidTile(row + drow, col + dcol)) return;

            // ignore while on moving tile
            if (currentTile != null && currentTile.moving) return;

            // ignore if the tile is blocked
            // but allow it if super jumping or teleporting
            if (!superjump && !teleporting && isTileBlocked(row + drow, col + dcol)) {
                sliding = false;
                return;
            }
        } else {
            // wait until finish rising in bubble
            if (p.z < BASELINE + BUBBLE_HEIGHT) return;

            // don't get far out of map bounds
            if (row + drow >= tileMap.numRows + 1 || row + drow < -1
                    || col + dcol >= tileMap.numCols + 1 || col + dcol < -1
            ) {
                return;
            }

            // cannot run into other floating slimes
            for (Player it : getPlayers(row + drow, col + dcol)) {
                if (it.bubbling) return;
            }
        }

        // valid tiles start here

        // update direction
        if (!teleporting) {
            if (dcol > 0) direction = Direction.RIGHT;
            if (dcol < 0) direction = Direction.LEFT;
            if (drow > 0) direction = Direction.DOWN;
            if (drow < 0) direction = Direction.UP;
        }

        // update tile position and set destination
        row += drow;
        col += dcol;
        tileMap.toPosition(row, col, pdest);

        totalDist = getRemainingDistance();
        moving = true;
        justTeleported = false;

        // lock the destination tile to prevent the tile from moving while the player is moving towards it
        if (!bubbling) {
            Tile tile = tileMap.getTile(row, col);
            if (tile != null) tile.lock = true;
        }
    }

    /**
     * Tile is blocked or there is another player in the way.
     */
    private boolean isTileBlocked(int row, int col) {
        Tile tile = tileMap.getTile(row, col);
        if (tile != null) {
            if (tile.isBlocked()) return true;
            for (Player it : getPlayers(row, col)) {
                if (it != this && !it.bubbling && !it.teleporting) return true;
            }
        }
        return false;
    }

    private List<Player> getPlayers(int row, int col) {
        return players.stream().filter(it -> it.row == row && it.col == col).collect(Collectors.toList());
    }

    public float getRemainingDistance() {
        return Utils.dist(pdest.x, pdest.y, p.x, p.y);
    }

    public boolean atDestination() {
        return p.x == pdest.x && p.y == pdest.y;
    }

    private void resetMovement() {
        moving = false;
        sliding = false;
        superjump = false;
        teleporting = false;
    }

    /**
     * Function to handle that the player has just landed on a tile.
     */
    private void handleJustMoved(int row, int col) {
        if (!bubbling) {
            moveListener.onMoved();
            tileMap.toggleTile(row, col);
        }

        // reset all movement flags
        resetMovement();

        // set the current tile
        // if the destination tile is locked, unlock it
        currentTile = tileMap.getTile(row, col);
        if (currentTile != null) currentTile.lock = false;
    }

    /**
     * If player has landed on a tile that contains tile objects,
     * this function will handle how the player will react to those objects.
     */
    private void handleTileObjects(int row, int col) {
        Tile tile = tileMap.getTile(row, col);
        if (tile != null) {
            for (TileObject it : tile.objects) {
                if (it instanceof Bubble) {
                    if (!((Bubble) it).bubbleBase.resetting) {
                        bubbling = true;
                        ((Bubble) it).bubbleBase.setResetting(true);
                    }
                } else if (it instanceof Arrow) {
                    sliding = true;
                    direction = ((Arrow) it).direction;
                } else if (it instanceof SuperJump) {
                    superjump = true;
                } else if (it instanceof Ice) {
                    if (!dropping && !justTeleported) sliding = true;
                } else if (it instanceof Teleport && !justTeleported) {
                    teleportTimer = 0f;
                    teleportSpeed = Math.max(
                            Math.abs(p.y - tileMap.toPosition(((Teleport) it).row2)),
                            Math.abs(p.x - tileMap.toPosition(((Teleport) it).col2))
                    ) * 1.5f;
                    teleporting = true;
                    moveTile(((Teleport) it).row2 - row, ((Teleport) it).col2 - col);
                    justTeleported = true;
                }
            }
        }

        // making these mutually exclusive
        if (superjump) sliding = false;

        // set tile for sliding and superjump, teleport is handled up there
        if (sliding || superjump) {
            int rx = 0;
            int cx = 0;
            int dist2 = superjump ? 2 : 1;
            if (direction == Direction.UP) rx = -dist2;
            if (direction == Direction.LEFT) cx = -dist2;
            if (direction == Direction.RIGHT) cx = dist2;
            if (direction == Direction.DOWN) rx = dist2;
            moving = false;
            moveTile(rx, cx);
        }
    }

    private void updateBounceHeight(float dt) {
        if (superjump) p.z = BASELINE + jumpHeight * 1.5f * getArc();
        else if (sliding) p.z = BASELINE;
        else if (dropping) p.z = Math.max(p.z - dt * BUBBLE_DROP_SPEED, BASELINE);
        else if (bubbling) {
            if (p.z < BASELINE + BUBBLE_HEIGHT) {
                p.z += dt * BUBBLE_HEIGHT_SPEED;
                if (p.z > BASELINE + BUBBLE_HEIGHT) {
                    p.z = BASELINE + BUBBLE_HEIGHT;
                    updateCanDrop();
                }
            }
        } else p.z = BASELINE + jumpHeight * getArc();
    }

    private float getArc() {
        return MathUtils.sin(MathUtils.PI * getRemainingDistance() / totalDist);
    }

    private void handleReachedDestination() {
        if (!bubbling) {
            // landed on illegal tile or another player
            if (!tileMap.isValidTile(row, col) || isTileBlocked(row, col)) {
                resetMovement();
                moveListener.onIllegal();
                return;
            }
        }
        handleJustMoved(row, col);
        if (!bubbling) handleTileObjects(row, col);
        dropping = false;
        for (Player it : players) it.updateCanDrop();
    }

    @Override
    public void onTileStartMove(Tile tile, int oldRow, int oldCol, int newRow, int newCol) {
        if (row == oldRow && col == oldCol) updateCanDrop();
    }

    @Override
    public void onTileEndMove(Tile tile, int oldRow, int oldCol, int newRow, int newCol) {
        // stick player to moving tile if not bubbling and player is not already moving
        if (!moving && !bubbling) {
            if (currentTile == tile) {
                setPositionFromTile(newRow, newCol);
                pdest.set(p);
            }
        }
        if (row == newRow && col == newCol) updateCanDrop();
    }

    public boolean forward() {
        return direction == Direction.RIGHT || direction == Direction.DOWN;
    }

    public boolean right() {
        return direction == Direction.RIGHT || direction == Direction.UP;
    }

    @Override
    public void update(float dt) {
        if (tileMap == null) {
            playerRenderer.update(dt);
            return;
        }
        selectedTimer += dt;

        // handle dropping
        if (dropping && p.z == BASELINE) {
            bubbling = false;
            handleReachedDestination();
        }

        // handle arrows while blocks
        if (atDestination() && !bubbling) {
            Tile tile = tileMap.getTile(row, col);
            if (tile != null) {
                for (TileObject it : tile.objects) {
                    if (it instanceof Arrow) {
                        handleTileObjects(row, col);
                    }
                }
            }
        }

        // stick the player on moving tiles if the player is not in bubble or not moving
        if (!bubbling) {
            if (currentTile != null) {
                if (!moving && currentTile.moving) {
                    p.set(currentTile.p.x, currentTile.p.y, p.z);
                    pdest.set(p);
                    playerRenderer.update(dt);
                    return;
                }
            }
        }

        // move towards destination by dist amount
        if (!atDestination()) {
            float dist = dt * ((teleporting && justTeleported) ? teleportSpeed : speed);
            float multiplier = superjump ? SUPER_JUMP_MULTIPLIER : sliding ? SLIDING_MULTIPLIER : 1f;
            if (teleporting) {
                teleportTimer += dt;
                if (teleportTimer > TELEPORT_TIME_LIMIT) {
                    p.x = pdest.x;
                    p.y = pdest.y;
                } else {
                    Utils.moveToLinear(p, pdest, dist * multiplier);
                }
            } else {
                Utils.moveTo(p, pdest, dist * multiplier);
            }
        }

        // handle logic for player just finished moving
        if (atDestination() && moving) {
            handleReachedDestination();
        }

        updateBounceHeight(dt);
        playerRenderer.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        playerRenderer.render(sb);
    }

    public class PlayerRenderer {

        private TextureRegion pointerImage = context.getImage("slimepointer");
        private BreathingImage bubble = new BreathingImage(context.getImage("bubble"), 0, 0, 0, 2f, 0.03f);
        private BreathingImage bubblex = new BreathingImage(context.getImage("bubbledropx"));
        private BreathingImage bubbleo = new BreathingImage(context.getImage("bubbledropo"));

        public AnimationSet animationSet = new AnimationSet();
        public AnimationSet faceSet = new AnimationSet();

        public Skin skin;
        public Face face;
        public List<AccessoryType> accessoryTypes = new ArrayList<>();
        public List<Accessory> accessories = new ArrayList<>();

        public PlayerRenderer() {
            setSkin(context.playerDataHandler.skin);
            setFace(context.playerDataHandler.face);
            setAccessories(context.playerDataHandler.accessories);

            setAnimation(IDLE);
        }

        /**
         * Need an easy way to apply complete customization to a slime.
         * Use an int array:
         * custom[0] = skin ordinal
         * custom[1] = face ordinal
         * custom[2+] = accessories ordinal
         */
        public void setCustomization(int[] custom) {
            setSkin(Skin.values()[custom[0]]);
            setFace(Face.values()[custom[1]]);
            if (custom.length > 2) {
                List<AccessoryType> types = new ArrayList<>();
                for (int i = 2; i < custom.length; i++) {
                    types.add(AccessoryType.values()[custom[i]]);
                }
                setAccessories(types);
            }
        }

        public int[] getCustomization() {
            int[] custom = new int[2 + accessoryTypes.size()];
            custom[0] = skin.ordinal();
            custom[1] = face.ordinal();
            for (int i = 0; i < accessoryTypes.size(); i++) {
                custom[i + 2] = accessoryTypes.get(i).ordinal();
            }
            return custom;
        }

        public void setSkin(Skin skin) {
            this.skin = skin;
            TextureRegion[] skins = skin.getSprites(context);
            String key = animationSet.currentAnimationKey;
            animationSet.clear();
            animationSet.addAnimation(IDLE, new Animation(new TextureRegion[]{skins[0], skins[1]}, 0.5f));
            animationSet.addAnimation(JUMP, new Animation(skins[2], 0.1f, 1));
            animationSet.addAnimation(CROUCH, new Animation(skins[3], 0.1f));
            animationSet.setAnimation(key);
        }

        public void setFace(Face face) {
            this.face = face;
            TextureRegion[] faces = face.getSprites(context);
            String key = faceSet.currentAnimationKey;
            faceSet.clear();
            faceSet.addAnimation(IDLE, new Animation(faces[0]));
            faceSet.addAnimation(JUMP, new Animation(faces[1]));
            faceSet.addAnimation(CROUCH, new Animation(faces[2]));
            faceSet.setAnimation(key);
        }

        public void setAccessories(List<AccessoryType> accessoryTypes) {
            this.accessoryTypes = accessoryTypes;
            accessories = AccessoryType.loadAccessories(Player.this, accessoryTypes);
        }

        private void setAnimation(String key) {
            animationSet.setAnimation(key);
            faceSet.setAnimation(key);
        }

        private void updateAnimations(float dt) {
            String key = animationSet.currentAnimationKey;
            if (sliding) setAnimation(CROUCH);
            else if (dropping) setAnimation(JUMP);
            else if (atDestination()) {
                if (key.equals(JUMP)) setAnimation(CROUCH);
                else if (animationSet.currentAnimation.hasPlayedOnce()) setAnimation(IDLE);
            } else {
                if (key.equals(IDLE)) setAnimation(CROUCH);
                else if (key.equals(CROUCH)) setAnimation(JUMP);
            }
            animationSet.update(dt);
            faceSet.update(dt);
            for (Accessory it : accessories) it.update(dt);
        }

        public void update(float dt) {
            updateAnimations(dt);
            if (bubbling) {
                bubble.update(dt);
                bubbleo.update(dt);
                bubblex.update(dt);
            }
        }

        public void render(SpriteBatch sb) {
            if (tileMap != null) tileMap.toIsometric(p.x, p.y, isop);
            if (!teleporting) {
                if (bubbling) {
                    sb.setColor(1, 1, 1, 1);
                    if (!dropping) {
                        bubble.setPosition(isop.x, isop.y + p.z + 10);
                        bubble.render(sb);
                    }
                    if (p.z == BASELINE + BUBBLE_HEIGHT) {
                        if (canDrop) {
                            bubbleo.setPosition(isop.x, isop.y);
                            bubbleo.render(sb);
                        } else {
                            bubblex.setPosition(isop.x, isop.y);
                            bubblex.render(sb);
                        }
                    }
                }

                // draw accessories behind player first
                // these have to go in reverse order since accessories are ordered by distance to center of player
                sb.setColor(1, 1, 1, 1);
                if (forward()) for (int i = accessories.size() - 1; i >= 0; i--)
                    accessories.get(i).renderBehind(sb);
                else for (int i = accessories.size() - 1; i >= 0; i--)
                    accessories.get(i).renderFront(sb);

                // draw player with face
                sb.setColor(1, 1, 1, 1);
                if (!skin.isReversible() || !forward()) {
                    sb.draw(
                            animationSet.getImage(),
                            isop.x - animationSet.getImage().getRegionWidth() / 2f,
                            isop.y + p.z
                    );
                } else {
                    Utils.drawHFlip(sb,
                            animationSet.getImage(),
                            isop.x + animationSet.getImage().getRegionWidth() / 2f,
                            isop.y + p.z
                    );
                }
                if (forward()) {
                    float y = animationSet.currentAnimationKey.equals(IDLE) && animationSet.currentAnimation.currentFrame() == 1 ? -1 : 0;
                    if (right()) {
                        sb.draw(
                                faceSet.getImage(),
                                isop.x - faceSet.getImage().getRegionWidth() / 2f,
                                isop.y + p.z + y
                        );
                    } else {
                        Utils.drawHFlip(sb,
                                faceSet.getImage(),
                                isop.x + faceSet.getImage().getRegionWidth() / 2f,
                                isop.y + p.z + y
                        );
                    }
                }

                // draw accessories in front of player
                sb.setColor(1, 1, 1, 1);
                if (forward()) for (Accessory it : accessories) it.renderFront(sb);
                else for (Accessory it : accessories) it.renderBehind(sb);

                if (selected && selectedTimer < 3 && (int) (selectedTimer * 10) % 5 < 3) {
                    sb.draw(
                            pointerImage,
                            isop.x - pointerImage.getRegionWidth() / 2f,
                            isop.y + p.z - 20f + 2 * MathUtils.sin(3 * selectedTimer)
                    );
                }
            }
        }

    }

}
