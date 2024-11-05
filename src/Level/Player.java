package Level;

import java.awt.Color;
import java.awt.Graphics;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import EnhancedMapTiles.InventoryItem;
import GameObject.GameObject;
import GameObject.Rectangle;
import GameObject.SpriteSheet;
import Utils.Direction;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import java.io.File;
import java.io.IOException;

public abstract class Player extends GameObject {
    // values that affect player movement
    // these should be set in a subclass
    protected float walkSpeed = 0;
    protected float runSpeed = 0;
    protected int interactionRange = 1;
    protected Direction currentWalkingXDirection;
    protected Direction currentWalkingYDirection;
    protected Direction lastWalkingXDirection;
    protected Direction lastWalkingYDirection;

    // values used to handle player movement
    protected float moveAmountX, moveAmountY;
    protected float lastAmountMovedX, lastAmountMovedY;

    // values used to keep track of player's current state
    protected PlayerState playerState;
    protected PlayerState previousPlayerState;
    protected Direction facingDirection;
    protected Direction lastMovementDirection;

    // define keys
    protected KeyLocker keyLocker = new KeyLocker();
    private static Key MOVE_LEFT_KEY = Key.A; // Left Movement for A
    private static Key MOVE_RIGHT_KEY = Key.D; // Right Movement for D
    private static Key MOVE_UP_KEY = Key.W; // Up Movement for D
    private static Key MOVE_DOWN_KEY = Key.S; // Down Movement for S
    protected Key INTERACT_KEY = Key.SPACE;

    // attack mode for Sprite
    private static Key ATTACK_UP_KEY = Key.U; // SWORD MOTION
    private boolean isSwordSoundPlayed = false;
    private boolean isDeathSoundPlayed = false;
    private boolean isArrowSoundPlayed = false;
    private boolean isMagicSoundPlayed = false;

    private boolean isActive = false;
    private Clip swordClip;// checking if sword clip is played
    private Clip DeathClip;// checking if death clip is played
    private Clip ArrowClip; // checking if Arrow clip is played
    private Clip MagicClip;// checking if MAgic clip is played
    private static Key ATTACK_DOWN_KEY = Key.J; // DEATH
    private static Key ATTACK_RIGHT_KEY = Key.K; // MAGIC MOTION
    private static Key ATTACK_LEFT_KEY = Key.H; // ARROW MOTION

    private boolean isKeyPressed = false;
    // private SpriteSheet swordSprite;// reference for sword sprite
    // private boolean isWieldingSword = false;// tracking if weapon is there
    /* private boolean isMovingLeft = false; */
    /* private boolean isMovingRight = false; */
    protected boolean isLocked = false;
    private ArrayList<InventoryItem> inventoryArrayList = new ArrayList<>();

    // Player stats
    protected int strength, dexterity, constitution, intelligence;
    public static int health = 5;

    public Player(SpriteSheet spriteSheet, float x, float y, String startingAnimationName) {
        super(spriteSheet, x, y, startingAnimationName);
        facingDirection = Direction.DOWN;
        playerState = PlayerState.STANDING;
        previousPlayerState = playerState;
        this.affectedByTriggers = true;
    }

    public  ArrayList<InventoryItem> getInventoryArrayList(){
        return inventoryArrayList;
    }

    public void update() {
        if (!isLocked) {
            moveAmountX = 0;
            moveAmountY = 0;

            // if player is currently playing through level (has not won or lost)
            // update player's state and current actions, which includes things like
            // determining how much it should move each frame and if its walking or jumping
            do {
                previousPlayerState = playerState;
                handlePlayerState();

            } while (previousPlayerState != playerState);

            // move player with respect to map collisions based on how much player needs to
            // move this frame
            if (getY1() > 0) {
                if (getY2() < map.getEndBoundY()) {
                    lastAmountMovedY = super.moveYHandleCollision(moveAmountY);
                } else {
                    setLocation(getX1(), map.getEndBoundY() - getHeight() - 1);
                }
            } else {
                setLocation(getX1(), 1);
            }

            if (getBounds().getX1() > 0) {
                if (getBounds().getX2() < map.getEndBoundX()) {
                    lastAmountMovedX = super.moveXHandleCollision(moveAmountX);
                } else {
                    setLocation(map.getEndBoundX() - getWidth() + 17, getY1());
                }
            } else {
                setLocation(-16, getY1());
            }

            handlePlayerAnimation();
            // playSoundEffect(currentAnimationName);

            updateLockedKeys();

            if (Keyboard.isKeyDown(Key.U)) {
                handleSwordAttack(); // Sword attack motion if hold down U key
            } else if (Keyboard.isKeyDown(Key.H)) {
                handleArrowAttack(); // Arrow attack
            } else if (Keyboard.isKeyDown(Key.J)) {
                handleDeathAttack(); // Death attack
            } else if (Keyboard.isKeyDown(Key.K)) {
                handleMagicAttack(); // Magic attack
            }
            // update player's animation
            super.update();
        }
    }

    // based on player's current state, call appropriate player state handling
    // method
    protected void handlePlayerState() {
        if (Keyboard.isKeyDown(Key.U) || Keyboard.isKeyDown(Key.H) || Keyboard.isKeyDown(Key.J)
                || Keyboard.isKeyDown(Key.K)) {
            // If any attack key is pressed, set player to ATTACK state
            playerState = PlayerState.ATTACK;
        } else if (Keyboard.isKeyDown(MOVE_LEFT_KEY) || Keyboard.isKeyDown(MOVE_RIGHT_KEY)
                || Keyboard.isKeyDown(MOVE_UP_KEY) || Keyboard.isKeyDown(MOVE_DOWN_KEY) || Keyboard.isKeyDown(Key.UP)
                || Keyboard.isKeyDown(Key.DOWN) || Keyboard.isKeyDown(Key.LEFT) || Keyboard.isKeyDown(Key.RIGHT)) {
            // If movement keys are pressed, set player to WALKING state
            playerState = PlayerState.WALKING;
        } else {
            // If no keys are pressed, set player to STANDING state
            playerState = PlayerState.STANDING;
        }

        switch (playerState) {
            case STANDING:

                playerStanding();
                break;
            case WALKING:

                playerWalking();
                break;

            case ATTACK:
                playerAttack();
                break;
        }
    }

    protected void playerAttack() { // Attack Detection U,J, H, K
        if (!keyLocker.isKeyLocked(INTERACT_KEY) && Keyboard.isKeyDown(INTERACT_KEY)) {
            keyLocker.lockKey(INTERACT_KEY);
            map.entityInteract(this);
        }
        moveAmountX = 0;
        moveAmountY = 0;
        // if attack key is pressed, player enter ATTACK STATE
        if (Keyboard.isKeyDown(ATTACK_LEFT_KEY) || Keyboard.isKeyDown(ATTACK_RIGHT_KEY)
                || Keyboard.isKeyDown(ATTACK_UP_KEY) || Keyboard.isKeyDown(ATTACK_DOWN_KEY)) {
            playerState = PlayerState.ATTACK;
        }

        if (Keyboard.isKeyDown(ATTACK_UP_KEY)) {
            handleSwordAttack();

        } else if (Keyboard.isKeyDown(ATTACK_DOWN_KEY)) {
            handleDeathAttack();

        } else if (Keyboard.isKeyDown(ATTACK_LEFT_KEY)) {
            handleArrowAttack();

        } else if (Keyboard.isKeyDown(ATTACK_RIGHT_KEY)) {
            handleMagicAttack();
        } else {
            if (Keyboard.isKeyDown(MOVE_LEFT_KEY) || Keyboard.isKeyDown(MOVE_RIGHT_KEY)
                    || Keyboard.isKeyDown(MOVE_UP_KEY) || Keyboard.isKeyDown(MOVE_DOWN_KEY)
                    || Keyboard.isKeyDown(Key.UP)
                    || Keyboard.isKeyDown(Key.DOWN) || Keyboard.isKeyDown(Key.LEFT) || Keyboard.isKeyDown(Key.RIGHT)) {
                playerState = PlayerState.WALKING; // Move back to WALKING state if movement keys are presse
            } else {
                playerState = PlayerState.STANDING;
                // if no AtTACK KEYS are pressed then revert back to walking
            }
            if (swordClip != null) {
                System.out.println(" SwordClip Stops");
            }
        }
    }

    protected Clip playSoundEffect(String sounndFilePath) {
        try {
            AudioInputStream AIS = AudioSystem.getAudioInputStream(new File(sounndFilePath));

            Clip clip = AudioSystem.getClip();
            clip.open(AIS);
            clip.setFramePosition(0);
            clip.start();
            return clip;
        } catch (Exception e) {
            e.printStackTrace();
            return null;// couldn't find clip
        }
    }

    private void handleSwordAttack() {
        if (swordClip == null || !swordClip.isActive()) {
            swordClip = playSoundEffect("Resources/SoundEffects_AttackMotions/Sword.wav");
            isSwordSoundPlayed = false;
        }

        if (facingDirection == Direction.UP) {
            currentAnimationName = "SWORD_UP";
        } else if (facingDirection == Direction.DOWN) {
            currentAnimationName = "SWORD_DOWN";
        } else if (facingDirection == Direction.LEFT) {
            currentAnimationName = "SWORD_LEFT";
        } else if (facingDirection == Direction.RIGHT) {
            currentAnimationName = "SWORD_RIGHT";
        }
        if (playerState != PlayerState.ATTACK) {
            playerState = PlayerState.ATTACK;
        }
    }

    private void handleDeathAttack() {
        if (DeathClip == null || !DeathClip.isActive()) {
            DeathClip = playSoundEffect("Resources/SoundEffects_AttackMotions/Player Death.wav");
            isDeathSoundPlayed = false;
        }
        facingDirection = Direction.DOWN;
        currentAnimationName = "FALL_DOWN";
        if (playerState != PlayerState.ATTACK) {
            playerState = PlayerState.ATTACK;
        }

    }

    private void handleArrowAttack() {
        if (ArrowClip == null || !ArrowClip.isActive()) {
            ArrowClip = playSoundEffect("Resources/SoundEffects_AttackMotions/Arrow2.wav");
            isArrowSoundPlayed = false;
        }

        if (facingDirection == Direction.UP) {
            currentAnimationName = "ARROW_UP";
        } else if (facingDirection == Direction.DOWN) {
            currentAnimationName = "ARROW_DOWN";
        } else if (facingDirection == Direction.LEFT) {
            currentAnimationName = "ARROW_LEFT";
        } else if (facingDirection == Direction.RIGHT) {
            currentAnimationName = "ARROW_RIGHT";
        }
        if (playerState != PlayerState.ATTACK) {
            playerState = PlayerState.ATTACK;
        }

    }

    private void handleMagicAttack() {
        if (MagicClip == null || !MagicClip.isActive()) {
            MagicClip = playSoundEffect("Resources/SoundEffects_AttackMotions/Magic.wav");
            isMagicSoundPlayed = false;
        }
        if (facingDirection == Direction.UP) {
            currentAnimationName = "MAGIC_UP";
        } else if (facingDirection == Direction.DOWN) {
            currentAnimationName = "MAGIC_DOWN";
        } else if (facingDirection == Direction.LEFT) {
            currentAnimationName = "MAGIC_LEFT";
        } else if (facingDirection == Direction.RIGHT) {
            currentAnimationName = "MAGIC_RIGHT";
        }
        if (playerState != PlayerState.ATTACK) {
            playerState = PlayerState.ATTACK;
        }

    }

    // player STANDING state logic
    protected void playerStanding() {
        if (!keyLocker.isKeyLocked(INTERACT_KEY) && Keyboard.isKeyDown(INTERACT_KEY)) {
            keyLocker.lockKey(INTERACT_KEY);
            map.entityInteract(this);
        }

        // if a walk key is pressed, player enters WALKING state
        if (Keyboard.isKeyDown(MOVE_LEFT_KEY) || Keyboard.isKeyDown(MOVE_RIGHT_KEY) || Keyboard.isKeyDown(MOVE_UP_KEY)
                || Keyboard.isKeyDown(MOVE_DOWN_KEY) || Keyboard.isKeyDown(Key.UP) || Keyboard.isKeyDown(Key.DOWN)
                || Keyboard.isKeyDown(Key.LEFT) || Keyboard.isKeyDown(Key.RIGHT)) {
            playerState = PlayerState.WALKING;
        }
    }

    // player WALKING state logic
    protected void playerWalking() {
        if (!keyLocker.isKeyLocked(INTERACT_KEY) && Keyboard.isKeyDown(INTERACT_KEY)) {
            keyLocker.lockKey(INTERACT_KEY);
            map.entityInteract(this);
        }
        // if walk up key is pressed, move player up
        if (Keyboard.isKeyDown(MOVE_UP_KEY) || Keyboard.isKeyDown(Key.UP)) {
            if (Keyboard.isKeyDown(Key.SHIFT)) {
                moveAmountY -= runSpeed;
            } else {
                moveAmountY -= walkSpeed;
            }
            facingDirection = Direction.UP;
            currentWalkingYDirection = Direction.UP;
            lastWalkingYDirection = Direction.UP;
        }
        // if walk down key is pressed, move player down
        else if (Keyboard.isKeyDown(MOVE_DOWN_KEY) || Keyboard.isKeyDown(Key.DOWN)) {
            if (Keyboard.isKeyDown(Key.SHIFT)) {
                moveAmountY += runSpeed;
            } else {
                moveAmountY += walkSpeed;
            }
            facingDirection = Direction.DOWN;
            currentWalkingYDirection = Direction.DOWN;
            lastWalkingYDirection = Direction.DOWN;
        }

        // if walk left key is pressed, move player to the left
        if (Keyboard.isKeyDown(MOVE_LEFT_KEY) || Keyboard.isKeyDown(Key.LEFT)) {
            if (Keyboard.isKeyDown(Key.SHIFT)) {
                moveAmountX -= runSpeed;
            } else {
                moveAmountX -= walkSpeed;
            }
            facingDirection = Direction.LEFT;
            currentWalkingXDirection = Direction.LEFT;
            lastWalkingXDirection = Direction.LEFT;
        }
        // if walk right key is pressed, move player to the right
        else if (Keyboard.isKeyDown(MOVE_RIGHT_KEY) || Keyboard.isKeyDown(Key.RIGHT)) {
            if (Keyboard.isKeyDown(Key.SHIFT)) {
                moveAmountX += runSpeed;
            } else {
                moveAmountX += walkSpeed;
            }
            facingDirection = Direction.RIGHT;
            currentWalkingXDirection = Direction.RIGHT;
            lastWalkingXDirection = Direction.RIGHT;
        } else {
            currentWalkingXDirection = Direction.NONE;
        }

        if (moveAmountX != 0 || moveAmountY != 0) {
            lastWalkingXDirection = currentWalkingXDirection;
            lastWalkingYDirection = currentWalkingYDirection;
        }

        if ((currentWalkingXDirection == Direction.RIGHT || currentWalkingXDirection == Direction.LEFT)
                && currentWalkingYDirection == Direction.NONE) {
            lastWalkingYDirection = Direction.NONE;
        }

        if ((currentWalkingYDirection == Direction.UP || currentWalkingYDirection == Direction.DOWN)
                && currentWalkingXDirection == Direction.NONE) {
            lastWalkingXDirection = Direction.NONE;
        }

        if (Keyboard.isKeyUp(MOVE_LEFT_KEY) && Keyboard.isKeyUp(MOVE_RIGHT_KEY) && Keyboard.isKeyUp(MOVE_UP_KEY)
                && Keyboard.isKeyUp(MOVE_DOWN_KEY) && Keyboard.isKeyUp(Key.UP) && Keyboard.isKeyUp(Key.DOWN)
                && Keyboard.isKeyUp(Key.LEFT) && Keyboard.isKeyUp(Key.RIGHT)) {
            playerState = PlayerState.STANDING;
        }
    }

    protected void updateLockedKeys() {
        if (Keyboard.isKeyUp(INTERACT_KEY) && !isLocked) {
            keyLocker.unlockKey(INTERACT_KEY);
        }
    }

    // anything extra the player should do based on interactions can be handled here
    protected void handlePlayerAnimation() {
        if (playerState == PlayerState.STANDING) {
            // sets animation to a STAND animation based on which way player is facing
            switch (this.facingDirection) {
                case UP -> currentAnimationName = "STAND_UP";
                case LEFT -> currentAnimationName = "STAND_LEFT";
                case DOWN -> currentAnimationName = "STAND_DOWN";
                case RIGHT -> currentAnimationName = "STAND_RIGHT";
            }
        }
        // sets animation to a WALK animation based on which way player is facing
        else if (playerState == PlayerState.RUNNING) {
            switch (this.facingDirection) {
                case UP -> currentAnimationName = "RUN_UP";
                case LEFT -> currentAnimationName = "RUN_LEFT";
                case DOWN -> currentAnimationName = "RUN_DOWN";
                case RIGHT -> currentAnimationName = "RUN_RIGHT";
            }
        } else {
            switch (this.facingDirection) {
                case UP -> currentAnimationName = "WALK_UP";
                case LEFT -> currentAnimationName = "WALK_LEFT";
                case DOWN -> currentAnimationName = "WALK_DOWN";
                case RIGHT -> currentAnimationName = "WALK_RIGHT";
            }
        }
    }

    @Override
    public void onEndCollisionCheckX(boolean hasCollided, Direction direction, GameObject entityCollidedWith) {
    }

    @Override
    public void onEndCollisionCheckY(boolean hasCollided, Direction direction, GameObject entityCollidedWith) {
    }

    public PlayerState getPlayerState() {
        return playerState;
    }

    public void setPlayerState(PlayerState playerState) {
        this.playerState = playerState;
    }

    public Direction getFacingDirection() {
        return facingDirection;
    }

    public void setFacingDirection(Direction facingDirection) {
        this.facingDirection = facingDirection;
    }

    public Rectangle getInteractionRange() {
        return new Rectangle(
                getBounds().getX1() - interactionRange,
                getBounds().getY1() - interactionRange,
                getBounds().getWidth() + (interactionRange * 2),
                getBounds().getHeight() + (interactionRange * 2));
    }

    public Key getInteractKey() {
        return INTERACT_KEY;
    }

    public Direction getCurrentWalkingXDirection() {
        return currentWalkingXDirection;
    }

    public Direction getCurrentWalkingYDirection() {
        return currentWalkingYDirection;
    }

    public Direction getLastWalkingXDirection() {
        return lastWalkingXDirection;
    }

    public Direction getLastWalkingYDirection() {
        return lastWalkingYDirection;
    }

    public void lock() {
        isLocked = true;
        playerState = PlayerState.STANDING;
        switch (this.facingDirection) {
            case UP -> currentAnimationName = "STAND_UP";
            case LEFT -> currentAnimationName = "STAND_LEFT";
            case DOWN -> currentAnimationName = "STAND_DOWN";
            case RIGHT -> currentAnimationName = "STAND_RIGHT";
        }
    }

    public void unlock() {
        isLocked = false;
        playerState = PlayerState.STANDING;
        switch (this.facingDirection) {
            case UP -> currentAnimationName = "STAND_UP";
            case LEFT -> currentAnimationName = "STAND_LEFT";
            case DOWN -> currentAnimationName = "STAND_DOWN";
            case RIGHT -> currentAnimationName = "STAND_RIGHT";
        }
    }

    // used by other files or scripts to force player to stand
    public void stand(Direction direction) {
        playerState = PlayerState.STANDING;
        facingDirection = direction;
        switch (this.facingDirection) {
            case UP -> currentAnimationName = "STAND_UP";
            case LEFT -> currentAnimationName = "STAND_LEFT";
            case DOWN -> currentAnimationName = "STAND_DOWN";
            case RIGHT -> currentAnimationName = "STAND_RIGHT";
        }
    }

    // used by other files or scripts to force player to walk
    public void walk(Direction direction, float speed) {
        playerState = PlayerState.WALKING;
        facingDirection = direction;
        switch (this.facingDirection) {
            case UP:
                currentAnimationName = "WALK_UP";
                moveY(-speed);
                break;
            case LEFT:
                currentAnimationName = "WALK_LEFT";
                moveX(-speed);
                break;
            case DOWN:
                currentAnimationName = "WALK_DOWN";
                moveY(speed);
                break;
            case RIGHT:
                currentAnimationName = "WALK_RIGHT";
                moveX(speed);
                break;
        }
    }

    public abstract void equip(InventoryItem item);
    public abstract void setStrength(int strength);
    public abstract void setDexterity(int dexterity);
    public abstract void setConstitution(int constitution);
    public abstract void setIntelligence(int intelligence);
    public abstract int getStrength();
    public abstract int getDexterity();
    public abstract int getConstitution();
    public abstract int getIntelligence();
    public abstract String getPlayerClass();
    public abstract String getPlayerName();

    // Uncomment this to have game draw player's bounds to make it easier to
    // visualize
    /*
     * public void draw(GraphicsHandler graphicsHandler) {
     * super.draw(graphicsHandler);
     * drawBounds(graphicsHandler, new Color(255, 0, 0, 100));
     * }
     */
}
