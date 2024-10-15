package Level;

import java.awt.Color;

import Engine.GraphicsHandler;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import GameObject.GameObject;
import GameObject.Rectangle;
import GameObject.SpriteSheet;
import Utils.Direction;

public abstract class Player extends GameObject {
    // values that affect player movement
    // these should be set in a subclass
    protected float walkSpeed = 0;
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
    private static Key ATTACK_DOWN_KEY = Key.J; // DEATH
    private static Key ATTACK_RIGHT_KEY = Key.K; // MAGIC MOTION
    private static Key ATTACK_LEFT_KEY = Key.H; // ARROW MOTION

    // private SpriteSheet swordSprite;// reference for sword sprite
    // private boolean isWieldingSword = false;// tracking if weapon is there
    /* private boolean isMovingLeft = false; */
    /* private boolean isMovingRight = false; */
    protected boolean isLocked = false;

    public Player(SpriteSheet spriteSheet, float x, float y, String startingAnimationName) {
        super(spriteSheet, x, y, startingAnimationName);
        facingDirection = Direction.DOWN;
        playerState = PlayerState.STANDING;
        previousPlayerState = playerState;
        this.affectedByTriggers = true;
    }

    public void update() {

        // System.out.println("Udpate Method- CurrentState: " + playerState);

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
            lastAmountMovedY = super.moveYHandleCollision(moveAmountY);
            lastAmountMovedX = super.moveXHandleCollision(moveAmountX);

            // Applying Movements
            this.x += moveAmountX;
            this.y += moveAmountY;

            handlePlayerAnimation();

            updateLockedKeys();

            if (Keyboard.isKeyDown(Key.U)) {
                handleSwordAttack(); // Sword attack
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
                || Keyboard.isKeyDown(MOVE_UP_KEY) || Keyboard.isKeyDown(MOVE_DOWN_KEY)) {
            // If movement keys are pressed, set player to WALKING state
            playerState = PlayerState.WALKING;
        } else {
            // If no keys are pressed, set player to STANDING state
            playerState = PlayerState.STANDING;
        }
        // System.out.println("Transitioned to PlayerState: " + playerState);
        // After deciding the state, call the appropriate method for the state

        switch (playerState) {
            case STANDING:

                playerStanding();
                break;
            case WALKING:

                playerWalking();
                break;

            case ATTACK:
                // System.out.println("Player is in ATTACK");
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
                    || Keyboard.isKeyDown(MOVE_UP_KEY) || Keyboard.isKeyDown(MOVE_DOWN_KEY)) {
                playerState = PlayerState.WALKING; // Move back to WALKING state if movement keys are presse

            } else {
                playerState = PlayerState.STANDING;
                // if no AtTACK KEYS are pressed then revert back to walking
            }
        }
    }

    private boolean isAttacking = false;

    private void handleSwordAttack() {

        if (facingDirection == Direction.UP) {
            currentAnimationName = "SWORD_UP";
            System.out.println("Player isin SWORD_UP STATE");

        } else if (facingDirection == Direction.DOWN) {
            currentAnimationName = "SWORD_DOWN";
            System.out.println("Player is in SWORD_DOWN STATE");

        } else if (facingDirection == Direction.LEFT) {
            currentAnimationName = "SWORD_LEFT";
            System.out.println("Player is in SWORD_LEFT STATE");

        } else if (facingDirection == Direction.RIGHT) {
            currentAnimationName = "SWORD_RIGHT";
            System.out.println("Player is in SWORD_RIGHT STATE");

        }
        if (playerState != PlayerState.ATTACK) {
            playerState = PlayerState.ATTACK;

        }

    }

    private void handleDeathAttack() {

        if (facingDirection == Direction.DOWN) {
            currentAnimationName = "FALL_DOWN";
            System.out.println("Player is in FALL_DOWN STATE");

        }
        if (playerState != PlayerState.ATTACK) {
            playerState = PlayerState.ATTACK;
        }

    }

    private void handleArrowAttack() {

        if (facingDirection == Direction.UP) {
            currentAnimationName = "ARROW_UP";
            System.out.println("Player is in ARROW_UP STATE");

        } else if (facingDirection == Direction.DOWN) {
            currentAnimationName = "ARROW_DOWN";
            System.out.println("Player is in ARROW_DOWN STATE");

        } else if (facingDirection == Direction.LEFT) {
            currentAnimationName = "ARROW_LEFT";
            System.out.println("Player is in ARROW_LEFT STATE");

        } else if (facingDirection == Direction.RIGHT) {
            currentAnimationName = "ARROW_RIGHT";
            System.out.println("Player is in ARROW_RIGHT STATE");

        }
        if (playerState != PlayerState.ATTACK) {
            playerState = PlayerState.ATTACK;
        }

    }

    private void handleMagicAttack() {

        if (facingDirection == Direction.UP) {
            currentAnimationName = "MAGIC_UP";
            System.out.println("Player is in MAGIC_UP STATE");

        } else if (facingDirection == Direction.DOWN) {
            currentAnimationName = "MAGIC_DOWN";
            System.out.println("Player is in MAGIC_DOWN STATE");

        } else if (facingDirection == Direction.LEFT) {
            currentAnimationName = "MAGIC_LEFT";
            System.out.println("Player is in MAGIC_LEFT STATE");

        } else if (facingDirection == Direction.RIGHT) {
            currentAnimationName = "MAGIC_RIGHT";
            System.out.println("Player is in MAGIC_RIGHT STATE");

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
                || Keyboard.isKeyDown(MOVE_DOWN_KEY)) {
            playerState = PlayerState.WALKING;
        }
    }

    // player WALKING state logic
    protected void playerWalking() {
        if (!keyLocker.isKeyLocked(INTERACT_KEY) && Keyboard.isKeyDown(INTERACT_KEY)) {
            keyLocker.lockKey(INTERACT_KEY);
            map.entityInteract(this);
        }
        moveAmountX = 0;
        moveAmountY = 0;
        // if walk up key is pressed, move player up
        if (Keyboard.isKeyDown(MOVE_UP_KEY)) {
            moveAmountY -= walkSpeed;
            facingDirection = Direction.UP;
            currentWalkingYDirection = Direction.UP;
            lastWalkingYDirection = Direction.UP;
        }

        // if walk left key is pressed, move player to the left
        if (Keyboard.isKeyDown(MOVE_LEFT_KEY)) {
            moveAmountX -= walkSpeed;
            facingDirection = Direction.LEFT;
            currentWalkingXDirection = Direction.LEFT;
            lastWalkingXDirection = Direction.LEFT;
        }

        // if walk down key is pressed, move player down
        if (Keyboard.isKeyDown(MOVE_DOWN_KEY)) {
            moveAmountY += walkSpeed;
            facingDirection = Direction.DOWN;
            currentWalkingYDirection = Direction.DOWN;
            lastWalkingYDirection = Direction.DOWN;
        }

        // if walk right key is pressed, move player to the right
        if (Keyboard.isKeyDown(MOVE_RIGHT_KEY)) {
            moveAmountX += walkSpeed;
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
                && Keyboard.isKeyUp(MOVE_DOWN_KEY)) {
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
        else {
            switch (this.facingDirection) {
                case UP -> currentAnimationName = "WALK_UP";
                case LEFT -> currentAnimationName = "WALK_LEFT";
                case DOWN -> currentAnimationName = "WALK_DOWN";
                case RIGHT -> currentAnimationName = "WALK_RIGHT";
            }
        } // debug
          // System.out.println("CurrentANimation:" + currentAnimationName);
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

    // Uncomment this to have game draw player's bounds to make it easier to
    // visualize
    /*
     * public void draw(GraphicsHandler graphicsHandler) {
     * super.draw(graphicsHandler);
     * drawBounds(graphicsHandler, new Color(255, 0, 0, 100));
     * }
     */
}
