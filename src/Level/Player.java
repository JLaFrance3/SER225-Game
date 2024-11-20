package Level;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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
import javax.swing.Timer;
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
    private static Key isKeyUp;
    protected Key INTERACT_KEY = Key.SPACE;

    // attack mode for Sprite
    private static Key ATTACK_UP_KEY = Key.U; // SWORD MOTION

    private boolean isActive = false;
    private Clip swordClip;// checking if sword clip is played
    private Clip DeathClip;// checking if death clip is played
    private Clip ArrowClip; // checking if Arrow clip is played
    private Clip MagicClip;// checking if MAgic clip is played
    protected KeyLocker kl = new KeyLocker();// fpr swprd
    protected KeyLocker kh = new KeyLocker(); // for magic
    protected KeyLocker kp = new KeyLocker();// for arrow
    protected KeyLocker kd = new KeyLocker(); // for death

    private int Sframecounter = 6;// sword frames
    private int Aframecounter = 13; // arrow frames
    private int Mframecounter = 7;// magic frame
    private int Dframecounter = 6; // death frame

    private boolean attackisPlaying = false; // sword flag
    private boolean ArrowattackisPlaying = false; // arrow falg
    private boolean MagicattackisPlaying = false; // magic flag
    private boolean DeathAttackisPlaying = false;// death flag

    private Timer timer;// sword
    private Timer Atimer;// arrow
    private Timer Mtimer; // death
    private Timer Dtimer; // death
    private boolean attackKeyHeld;

    boolean[] attackkeysPressed = new boolean[4];
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
        initializeTimers();
    }

    public void initializeTimers() {
        // setupAttackTimer(); // Set up timer in a consturctor
        setupArrowTimer();
    }

    private void setupAttackTimer() {// timer for Sword attack
        // if (timer == null || !timer.isRunning()) {
        System.out.println("Setting up the attack timer...");
        int delay = 300; // how many milliseconds the timer will be
        ActionListener listener = new ActionListener() {// setts what will happen when timer goes off
            public void actionPerformed(ActionEvent e) {// anything in ActionPerfored will happen when timer starts

                if (Sframecounter > 0) {
                    Sframecounter--;
                    // kl.lockKey(Key.U);
                }
                if (Sframecounter == 0) {
                    attackisPlaying = false; // goes off
                    kl.unlockKey(Key.U);
                    System.out.println("Sword attack is Complete");
                    resetAttackState();
                    timer.stop();
                    // playerState = PlayerState.STANDING;
                }
                System.out.println("Sword frames left:" + Sframecounter);

            }

        };
        if (timer != null) {
            timer.stop();
        }

        timer = new Timer(delay, listener); // creating new timer object, using the d
        timer.setRepeats(true);// repeats till counter is zero
        System.out.println("Timer initialized: " + (timer != null));
        timer.start();

        // }
    }

    // this method is for Magic
    private void setupArrowTimer() {// timer for Arrow attack
        int delay = 400; // how many milliseconds the timer will be
        ActionListener listener = new ActionListener() {// setts what will happen when timer goes off
            public void actionPerformed(ActionEvent e) {// anything in ActionPerfored will happen when
                if (Aframecounter > 0) {
                    Aframecounter--;
                    System.out.println("Arrow frames left:" + Aframecounter);
                } else {
                    ArrowattackisPlaying = false; // goes off
                    Atimer.stop();
                    kp.unlockKey(Key.H);
                    System.out.println("Arrow attack is Complete");

                }
            }
        };
        Atimer = new Timer(delay, listener); // creating new timer object, using the d
        Atimer.setRepeats(true);// tell timer to only happen onnce
    }

    public ArrayList<InventoryItem> getInventoryArrayList() {
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

            // if (Keyboard.isKeyDown(Key.U) && !attackisPlaying){

            if (Keyboard.isKeyDown(Key.U) && !attackisPlaying) { // ----> this if Statement is WORKING
                handleSwordAttack(); // start sword attack
            }

            if (Keyboard.isKeyDown(Key.H) && !ArrowattackisPlaying) {
                handleArrowAttack(); // Arrow attack
                // System.out.println("H haas been pressed, ARROW STATE")
            } // else {
              // Atimer.start();
              // System.out.println("Atimer is not initialized ");

            // }
            // }
            // if (ArrowattackisPlaying && Aframecounter > 0) {
            // Aframecounter--;
            // System.out.println("Arrow frames left:" + Aframecounter);
            // } else {
            // ArrowattackisPlaying = false;
            // kp.unlockKey(Key.H);
            // System.out.println(" Arrow attack animation complete ");
            // }

            // if (!ArrowattackisPlaying && ArrowClip != null && !ArrowClip.isRunning()) {
            // ArrowReset();
            // System.out.println("ArrowClip is null and not running");
            // }

            // death
            if (Keyboard.isKeyDown(Key.J)) {
                handleDeathAttack(); // Death attack
                System.out.println("Death State is Played");

                // magic
            } else if (Keyboard.isKeyDown(Key.K)) {
                handleMagicAttack(); // Magic attack
                System.out.println("Magic State is Played");

            }
            // update player's animation
            super.update();
        }
    }

    // private void playSoundandStartAnimation(String attackType) {
    // if (attackisPlaying) {
    // if (!isSwordSoundPlayed) {
    // swordClip =playSoundEffect("Resources/SoundEffects_AttackMotions/Sword.wav");

    // }
    // attackisPlaying = true;
    // startAttackAnimation();
    // timer.start();

    // }
    // }

    // public void lockKey() {
    // attackisPlaying = true;
    // System.out.println("Keys are locked during attack");
    // Attack keys
    // kl.lockKey(Key.U);
    // kl.lockKey(Key.H);
    // kl.lockKey(Key.J);
    // kl.lockKey(Key.K);
    // WASD Movement + Shift(Sprint Key)
    // kl.lockKey(Key.W);
    // kl.lockKey(Key.A);
    // kl.lockKey(Key.S);
    // kl.lockKey(Key.D);
    // kl.lockKey(Key.SHIFT);
    // }

    // public void unlockKey() {
    // attackisPlaying = false;
    // System.out.println(" Keys are unlocked after attack ");
    // Attack keys
    // kl.unlockKey(Key.U);
    // kl.unlockKey(Key.H);
    // kl.unlockKey(Key.J);
    // kl.unlockKey(Key.K);
    // WASD Movement + Shift (Sprinnt Key)
    // kl.unlockKey(Key.W);
    // kl.unlockKey(Key.A);
    // kl.lockKey(Key.S);
    // kl.unlockKey(Key.D);
    // kl.unlockKey(Key.SHIFT);
    // }

    private void resetAttackState() {

        if (!attackisPlaying) {
            if (swordClip != null && swordClip.isRunning()) {
                swordClip.stop();
                System.out.println("Sword clip has stopped ");
            }

            Sframecounter = 0;
            attackisPlaying = false;
            // playerState = PlayerState.WALKING;
            // kl.unlockKey(Key.U);
            System.out.println("Sword state reset");
            // playerState = PlayerState.WALKING;
        }
    }

    public void ArrowReset() {
        if (!ArrowattackisPlaying) {
            if (ArrowClip != null && ArrowClip.isRunning()) {
                ArrowClip.stop(); // Stop arrow animation if needed
                System.out.println("Arrow state reset");
            }
            Aframecounter = 0;
            System.out.println("Arrow state reset");

        }
    }

    // based on player's current state, call appropriate player state handling
    // method
    protected void handlePlayerState() {
        if (attackisPlaying) {
            playerState = PlayerState.ATTACK;
        }
        if (Keyboard.isKeyDown(MOVE_LEFT_KEY) || Keyboard.isKeyDown(MOVE_RIGHT_KEY)
                || Keyboard.isKeyDown(MOVE_UP_KEY) || Keyboard.isKeyDown(MOVE_DOWN_KEY) || Keyboard.isKeyDown(Key.UP)
                || Keyboard.isKeyDown(Key.DOWN) || Keyboard.isKeyDown(Key.LEFT) || Keyboard.isKeyDown(Key.RIGHT)) {
            // If movement keys are pressed, set player to WALKING state
            playerState = PlayerState.WALKING;
        } else if (Keyboard.isKeyDown(Key.U) || Keyboard.isKeyDown(Key.H) || Keyboard.isKeyDown(Key.J)
                || Keyboard.isKeyDown(Key.K)) {
            playerState = PlayerState.ATTACK;

        } else {
            // If no keys are pressed, set player to STANDING state
            playerState = PlayerState.STANDING;
        }
        System.out.println("Current Player State:" + playerState);

        switch (playerState) {
            case STANDING:
                playerStanding();
                System.out.println(" Player in Standing ");
                break;
            case WALKING:
                playerWalking();
                System.out.println("Playing in Walking ");
                break;

            case ATTACK:
                playerAttack();
                System.out.println("Player in Attack");
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
        // move to do ()
        // boolean hasAnimationLooped
        startAttackAnimation();
        // if (!attackisPlaying) {

        // timer.start();
        // attackisPlaying = true; // setting flag back b/c animation started here
        // }
    }

    private void startAttackAnimation() {
        // System.out.println("Player State:" + playerState);
        if (playerState == PlayerState.ATTACK) {
            handlePlayerAnimation();
        }
    }

    protected Clip playSoundEffect(String sounndFilePath) {
        try {
            AudioInputStream AIS = AudioSystem.getAudioInputStream(new File(sounndFilePath));
            System.out.println("Sound has been played:" + sounndFilePath);
            File soundFile = new File(sounndFilePath);
            if (!soundFile.exists()) {
                System.out.println("Sound File not Found: " + sounndFilePath);
                return null;
            }
            Clip clip = AudioSystem.getClip();
            if (clip == null) {
                System.out.println("Failed to Load clip.");

            }
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
        if (!attackisPlaying) {
            attackisPlaying = true;// attack state is active
            Sframecounter = 5;// sword framecounter, number of frames for sword
            kl.lockKey(Key.U);// lock key to prevent repeated triggers
            // handlePlayerAnimation();
            System.out.println("Sword attack has been triggered");

            // System.out.println(" Sword key has been pressed");
            if (swordClip == null || !swordClip.isActive()) {
                swordClip = playSoundEffect("Resources/SoundEffects_AttackMotions/Sword.wav");
                // System.out.println("Failed to play sword sound , swordClip is null");
                System.out.println(" Sword sound played ");
            }
        }

        if (swordClip != null && !swordClip.isActive()) {
            swordClip.setFramePosition(0);
            swordClip.start();
            System.out.println("Sword sound has started");

        }

        if (timer == null || !timer.isRunning()) {
            // System.out.println("Initializing the timer for swod animation");
            setupAttackTimer();

            System.out.println(" Sword Timer has Started  ");
        }
        // System.out.println("Timer state before checking:" + (timer != null ?
        // "Initialized" : "null"));
        // if (timer != null && !timer.isRunning()) {
        // timer.start();
        // System.out.println(" Sword animation timer has started");
        // }

        if (playerState != PlayerState.ATTACK) {
            playerState = PlayerState.ATTACK;

        }
        // if (Sframecounter == 0) { // Sframecounter represents the number of frames
        // left for the sword animation
        // kl.unlockKey(Key.U); // Unlock key after the sword attack is complete
        // System.out.println("Sword attack complete, key unlocked");
        // attackisPlaying = false; // End attack state
        // }
        //
        // handlePlayerAnimation();
        // attackisPlaying = true;
        // timer.restart();
        // System.out.println(" Sword key has been pressed once");

    }
    // attackisPlaying = true;

    private void handleDeathAttack() {
        if (DeathClip == null || !DeathClip.isActive()) {
            DeathClip = playSoundEffect("Resources/SoundEffects_AttackMotions/Player Death.wav");
            // isDeathSoundPlayed = false;
        }
        facingDirection = Direction.DOWN;
        currentAnimationName = "FALL_DOWN";
        if (playerState != PlayerState.ATTACK) {
            playerState = PlayerState.ATTACK;
        }

    }

    private void handleArrowAttack() {
        if (!ArrowattackisPlaying) {
            ArrowattackisPlaying = true;
            Aframecounter = 13;
            kp.lockKey(Key.H);

            if (ArrowClip == null || !ArrowClip.isActive()) {
                ArrowClip = playSoundEffect("Resources/SoundEffects_AttackMotions/Arrow2.wav");
            }
            if (Atimer == null) {
                setupArrowTimer();
            }
            if (!Atimer.isRunning()) {
                Atimer.start();
                System.out.println(" Arrow animation timer has started");
            }
            if (playerState != PlayerState.ATTACK) {
                playerState = PlayerState.ATTACK;
            }

        }
    }

    private void handleMagicAttack() {
        if (!attackisPlaying) {
            if (MagicClip == null || !MagicClip.isActive()) {
                MagicClip = playSoundEffect("Resources/SoundEffects_AttackMotions/Magic.wav");
                // isMagicSoundPlayed = false;
            }
            // if (facingDirection == Direction.UP) {
            // currentAnimationName = "MAGIC_UP";
            // } else if (facingDirection == Direction.DOWN) {
            // currentAnimationName = "MAGIC_DOWN";
            // } else if (facingDirection == Direction.LEFT) {
            // currentAnimationName = "MAGIC_LEFT";
            // } else if (facingDirection == Direction.RIGHT) {
            // currentAnimationName = "MAGIC_RIGHT";
            // }
            if (playerState != PlayerState.ATTACK) {
                playerState = PlayerState.ATTACK;// shouldnt this is walking?
            }

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
        }
        if (!attackisPlaying) {

            switch (this.facingDirection) {
                case UP -> currentAnimationName = "SWORD_UP";
                case LEFT -> currentAnimationName = "SWORD_LEFT";
                case DOWN -> currentAnimationName = "SWORD_DOWN";
                case RIGHT -> currentAnimationName = "SWORD_RIGHT";
            }
            return;
        }
        if (ArrowattackisPlaying) {
            switch (this.facingDirection) {
                case UP -> currentAnimationName = "ARROW_UP";
                case LEFT -> currentAnimationName = "ARROW_LEFT";
                case DOWN -> currentAnimationName = "ARROW_DOWN";
                case RIGHT -> currentAnimationName = "ARROW_RIGHT";
            }
        } else if (false) { // change tp MagicattackisPlaying and Do the same thing for Death

            switch (this.facingDirection) {
                case UP -> currentAnimationName = "MAGIC_UP";
                case LEFT -> currentAnimationName = "MAGIC_LEFT";
                case DOWN -> currentAnimationName = "MAGIC_DOWN";
                case RIGHT -> currentAnimationName = "MAGIC_RIGHT";
            }
        } else if (false) {
            switch (this.facingDirection) {
                case UP -> currentAnimationName = "FALL_DOWN";
                case LEFT -> currentAnimationName = "FALL_DOWN";
                case DOWN -> currentAnimationName = "FALL_DOWN";
                case RIGHT -> currentAnimationName = "FALL_DOWN";
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

    public abstract void unequip(InventoryItem.EQUIP_TYPE equipType);

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
