package Level;

import java.awt.Color;
import java.awt.image.BufferedImage;
import Engine.GraphicsHandler;
import GameObject.GameObject;
import GameObject.IntersectableRectangle;
import GameObject.Rectangle;
import GameObject.SpriteSheet;
import Utils.Point;

// Represents a map tile in a Map's tile map
public class MapTile extends MapEntity {
    // this determines a tile's properties, like if it's passable or not
    protected TileType tileType;

    // bottom layer of tile
    protected GameObject bottomLayer;

    // middle layer of tile
    protected GameObject midLayer;

    // top layer of tile ("pasted on top of" bottom layer, covers player)
    protected GameObject topLayer;

    private int[] tileIndices = new int[3];

    // Collision bounds allowing for small collision size inside MapTile
    private Rectangle collisionBounds;

    public MapTile(float x, float y, GameObject bottomLayer, GameObject topLayer, GameObject midLayer, TileType tileType, int[] tileIndices) {
        super(x, y);
        this.bottomLayer = bottomLayer;
        this.topLayer = topLayer;
        this.midLayer = midLayer;
        this.tileType = tileType;
        this.tileIndices = tileIndices;
        this.collisionBounds = createCollisionBounds();
    }

    public MapTile(float x, float y, GameObject bottomLayer, GameObject topLayer, TileType tileType, int tileIndex) {
        super(x, y);
        this.bottomLayer = bottomLayer;
        this.topLayer = topLayer;
        this.midLayer = null;
        this.tileType = tileType;
        this.tileIndices[0] = tileIndex;
        this.collisionBounds = createCollisionBounds();
    }

    public MapTile(float x, float y, GameObject bottomLayer, GameObject topLayer, TileType tileType) {
        super(x, y);
        this.bottomLayer = bottomLayer;
        this.topLayer = topLayer;
        this.midLayer = null;
        this.tileType = tileType;
        this.collisionBounds = createCollisionBounds();
    }

    public MapTile(float x, float y, SpriteSheet spriteSheet, TileType tileType) {
        super(x, y);
        this.bottomLayer = loadBottomLayer(spriteSheet);
        this.topLayer = loadTopLayer(spriteSheet);
        this.midLayer = loadMidLayer(spriteSheet);
        this.tileType = tileType;
        this.collisionBounds = createCollisionBounds();
    }

    protected GameObject loadBottomLayer(SpriteSheet spriteSheet) {
        return null;
    }

    protected GameObject loadTopLayer(SpriteSheet spriteSheet) {
        return null;
    }

    protected GameObject loadMidLayer(SpriteSheet spriteSheet) {
        return null;
    }

    public TileType getTileType() {
        return tileType;
    }

    public int getTileIndex() {
        return tileIndices[0];
    }

    public int[] getTileLayerIndices() {
        return tileIndices;
    }

    public GameObject getBottomLayer() { return bottomLayer; }
    public void setBottomLayer(GameObject bottomLayer) { this.bottomLayer = bottomLayer; }

    public GameObject getTopLayer() { return topLayer; }
    public void setTopLayer(GameObject topLayer) { this.topLayer = topLayer; }

    public GameObject getMidLayer() { return midLayer; }
    public void setMidLayer(GameObject midLayer) { this.midLayer = midLayer; }

    // determines if tile is animated or not
    public boolean isAnimated() {
        return (bottomLayer.getCurrentAnimation().length > 1) ||
                (topLayer != null && topLayer.getCurrentAnimation().length > 1 || midLayer != null && midLayer.getCurrentAnimation().length > 1);
    }

    // Sets bounds automatically based on RGB values in layers
    // Does not currently work with magenta transparency
    public Rectangle createCollisionBounds() {
        Rectangle newBounds;
        if (tileType == TileType.NOT_PASSABLE) {
            BufferedImage image;
            int width, height, top, bottom, left, right;

            // Get collision image information from toplayer
            if (topLayer != null) {
                image = topLayer.getAnimations().get("DEFAULT")[0].getImage();
                width = image.getWidth();
                height = image.getHeight();
                top = height / 2;
                bottom = top;
                left = width / 2;
                right = left;
            }
            else {
                // If no toplayer, get image information from midlayer
                if (midLayer != null) {
                    image = midLayer.getAnimations().get("DEFAULT")[0].getImage();
                    width = image.getWidth();
                    height = image.getHeight();
                    top = height / 2;
                    bottom = top;
                    left = width / 2;
                    right = left;
                }
                else {
                    newBounds = getBounds();
                    return newBounds;
                }
            }
            // Calculate bounds based on rgb values
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    if (image.getRGB(i, j) != 0) {
                        top = Math.min(top, j);
                        bottom = Math.max(bottom, j);
                        left = Math.min(left, i);
                        right = Math.max(right, i);
                    }
                }
            }
            // Create new bounding rectangle for collision area
            newBounds = new Rectangle(
                    getBounds().getX1() + left,
                    getBounds().getY1() + top,
                    right - left + 1,
                    bottom - top + 1
            );
        }
        else {newBounds = getBounds();}

        return newBounds;
    }

    public Rectangle getCollisionBounds() {
        return collisionBounds;
    }

    // set this game object's map to make it a "part of" the map, allowing calibrated positions and collision handling logic to work
    // note that both the bottom layer and the top layer need the map reference
    public void setMap(Map map) {
        this.map = map;
        this.bottomLayer.setMap(map);
        if (midLayer != null) {
            this.midLayer.setMap(map);
        }
        if (topLayer != null) {
            this.topLayer.setMap(map);
        }
    }

    @Override
    public void update() {
        bottomLayer.update();
        if (midLayer != null) {
            midLayer.update();
        }
        if (topLayer != null) {
            topLayer.update();
        }
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        bottomLayer.draw(graphicsHandler);
        if (midLayer != null) {
            midLayer.draw(graphicsHandler);
        }
        if (topLayer != null) {
            topLayer.draw(graphicsHandler);
        }
    }

    public void drawBottomLayer(GraphicsHandler graphicsHandler) {
        bottomLayer.draw(graphicsHandler);

        // uncomment this to draw collision bounds for map tiles(useful for debugging)
        // if (tileType == TileType.NOT_PASSABLE) {
        //     Rectangle scaledCalibratedBounds = new Rectangle(
        //         Math.round(collisionBounds.getX1()) - Math.round(map.getCamera().getX()),
        //         Math.round(collisionBounds.getY1()) - Math.round(map.getCamera().getY()),
        //         Math.round(collisionBounds.getWidth()),
        //         Math.round(collisionBounds.getHeight())
        //     );
        //     scaledCalibratedBounds.setColor(new Color(0, 0, 255, 100));
        //     scaledCalibratedBounds.draw(graphicsHandler);
        // }
    }

    public void drawMidLayer(GraphicsHandler graphicsHandler) {
        midLayer.draw(graphicsHandler);

        // uncomment this to draw bounds of all non passable tiles (useful for debugging)
        // if (tileType == TileType.NOT_PASSABLE) {
        //     drawBounds(graphicsHandler, new Color(0, 0, 255, 100));
        // }
    }

    public void drawTopLayer(GraphicsHandler graphicsHandler) {
        topLayer.draw(graphicsHandler);

        // uncomment this to draw bounds of all non passable tiles (useful for debugging)
        // if (tileType == TileType.NOT_PASSABLE) {
        //     drawBounds(graphicsHandler, new Color(0, 0, 255, 100));
        // }
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public float getX1() { return bottomLayer.getX(); }

    @Override
    public float getY1() { return bottomLayer.getY(); }

    @Override
    public float getX2() { return bottomLayer.getX2(); }

    @Override
    public float getY2() { return bottomLayer.getY2(); }

    @Override
    public Point getLocation() {
        return new Point(x, y);
    }

    @Override
    public Rectangle getIntersectRectangle() {
        return bottomLayer.getIntersectRectangle();
    }

    @Override
    public int getWidth() {
        return bottomLayer.getWidth();
    }

    @Override
    public int getHeight() {
        return bottomLayer.getHeight();
    }

    @Override
    public Rectangle getBounds() {
        if (midLayer != null) {
            float x, y;
            int x1, y1;

            x = bottomLayer.getX() < midLayer.getX() ? bottomLayer.getX() : midLayer.getX();
            y = bottomLayer.getY() < midLayer.getY() ? bottomLayer.getY() : midLayer.getY();
            x1 = bottomLayer.getWidth() > midLayer.getWidth() ? bottomLayer.getWidth() : midLayer.getWidth();
            y1 = bottomLayer.getHeight() > midLayer.getHeight() ? bottomLayer.getHeight() : midLayer.getHeight();
            return new Rectangle(x, y, x1, y1);
        }
        else {
            return bottomLayer.getBounds();
        }
    }

    @Override
    public void setX(float x) {
        this.x = x;
        bottomLayer.setX(x);
        if (midLayer != null) {
            midLayer.setX(x);
        }
        if (topLayer != null) {
            topLayer.setX(x);
        }
    }

    @Override
    public void setY(float y) {
        this.y = y;
        bottomLayer.setY(y);
        if (midLayer != null) {
            midLayer.setY(y);
        }
        if (topLayer != null) {
            topLayer.setY(y);
        }
    }

    @Override
    public void setLocation(float x, float y) {
        this.setX(x);
        this.setY(y);
    }

    @Override
    public void moveX(float dx) {
        this.x += dx;
        bottomLayer.moveX(dx);
        if (midLayer != null) {
            midLayer.moveX(x);
        }
        if (topLayer != null) {
            topLayer.moveX(dx);
        }
    }

    @Override
    public void moveRight(float dx) {
        this.x += dx;
        bottomLayer.moveRight(dx);
        if (midLayer != null) {
            midLayer.moveRight(dx);
        }
        if (topLayer != null) {
            topLayer.moveRight(dx);
        }
    }

    @Override
    public void moveLeft(float dx) {
        this.x -= dx;
        bottomLayer.moveLeft(dx);
        if (midLayer != null) {
            midLayer.moveLeft(dx);
        }
        if (topLayer != null) {
            topLayer.moveLeft(dx);
        }
    }

    @Override
    public void moveY(float dy) {
        this.y += dy;
        bottomLayer.moveY(dy);
        if (midLayer != null) {
            midLayer.moveY(y);
        }
        if (topLayer != null) {
            topLayer.moveY(dy);
        }
    }

    @Override
    public void moveDown(float dy) {
        this.y += dy;
        bottomLayer.moveDown(dy);
        if (midLayer != null) {
            midLayer.moveDown(dy);
        }
        if (topLayer != null) {
            topLayer.moveDown(dy);
        }
    }

    @Override
    public void moveUp(float dy) {
        this.y -= dy;
        bottomLayer.moveUp(dy);
        if (midLayer != null) {
            midLayer.moveUp(dy);
        }
        if (topLayer != null) {
            topLayer.moveUp(dy);
        }
    }

    @Override
    public boolean intersects(IntersectableRectangle other) {
        return bottomLayer.intersects(other);
    }

    @Override
    public boolean touching(IntersectableRectangle other) { return bottomLayer.touching(other); }

    @Override
    public float getAreaOverlapped(IntersectableRectangle other) { return bottomLayer.getAreaOverlapped(other); }
}
