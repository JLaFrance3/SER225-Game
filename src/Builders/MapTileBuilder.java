package Builders;

import GameObject.Frame;
import GameObject.GameObject;
import Level.MapTile;
import Level.TileType;

import java.util.HashMap;

// Builder class to instantiate a MapTile class
public class MapTileBuilder {

    private TileType tileType = TileType.PASSABLE;
    private int[] tileIndices = new int[] {-1, -1, -1};
    private HashMap<String, Frame[]> bottomLayer = new HashMap<>();;
    private HashMap<String, Frame[]> midLayer = new HashMap<>();;
    private HashMap<String, Frame[]> topLayer = new HashMap<>();;

    public MapTileBuilder(Frame bottomLayer) {
        this.bottomLayer.put("DEFAULT", new Frame[] { bottomLayer });
    }

    public MapTileBuilder(Frame[] bottomLayer) {
        this.bottomLayer.put("DEFAULT", bottomLayer);
    }

    public MapTileBuilder(HashMap<String, Frame[]> bottomLayer, int tileIndex) {
        this.bottomLayer = new HashMap<>(bottomLayer);
        this.tileIndices[0] = tileIndex;
    }

    public MapTileBuilder withTileType(TileType tileType) {
        this.tileType = tileType;
        return this;
    }

    public MapTileBuilder withTileIndex(int tileIndex) {
        this.tileIndices[0] = tileIndex;
        return this;
    }

    public MapTileBuilder withBottomLayer(Frame bottomLayer) {
        this.bottomLayer.put("DEFAULT", new Frame[] { bottomLayer });
        return this;
    }

    public MapTileBuilder withBottomLayer(Frame[] bottomLayer) {
        this.bottomLayer.put("DEFAULT", bottomLayer);
        return this;
    }

    public MapTileBuilder withMidLayer(Frame midLayer) {
        this.midLayer.put("DEFAULT", new Frame[] { midLayer });
        return this;
    }

    public MapTileBuilder withMidLayer(Frame[] midLayer) {
        this.midLayer.put("DEFAULT", midLayer);
        return this;
    }

    public MapTileBuilder withTopLayer(Frame topLayer) {
        this.topLayer.put("DEFAULT", new Frame[] { topLayer });
        return this;
    }

    public MapTileBuilder withTopLayer(Frame[] topLayer) {
        this.topLayer.put("DEFAULT", topLayer);
        return this;
    }

    public HashMap<String, Frame[]> getBottomLayer() {
        return bottomLayer;
    }

    public HashMap<String, Frame[]> getMidLayer() {
        return midLayer;
    }

    public HashMap<String, Frame[]> getTopLayer() {
        return topLayer;
    }

    public int getTileIndex() {
        return tileIndices[0];
    }

    public int[] getTileLayerIndices() {
        return tileIndices;
    }

    public TileType getTileType() {
        return tileType;
    }

    public void setTileType(TileType tileType) {
        this.tileType = tileType;
    }

    // Copies bottom layer of a another tile from the tileset and adds to the mid layer
    public void addMidLayer(MapTileBuilder midMapTile, int midIndex) {
        if (midMapTile != null) {
            this.midLayer = midMapTile.getBottomLayer();
            this.tileIndices[1] = midIndex;
            if (midMapTile.getTileType() == TileType.NOT_PASSABLE) {
                this.tileType = TileType.NOT_PASSABLE;
            }
        }
    }

    // Copies bottom layer of a another tile from the tileset and adds to the top layer
    public void addTopLayer(MapTileBuilder topMapTile, int topIndex) {
        if (topMapTile != null) {
            this.topLayer = topMapTile.getTopLayer();
            this.tileIndices[2] = topIndex;
            if (topMapTile.getTileType() == TileType.NOT_PASSABLE) {
                this.tileType = TileType.NOT_PASSABLE;
            }
        }
    }

    // Returns true if there is currently a top layer
    public boolean hasTopLayer() {
        return !topLayer.isEmpty();
    }

    // Clear layers
    public void clearUpperLayers() {
        midLayer.clear();
        tileIndices[1] = -1;
        topLayer.clear();
        tileIndices[2] = -1;
    }

    public HashMap<String, Frame[]> cloneAnimations(HashMap<String, Frame[]> animations) {
        HashMap<String, Frame[]> animationsCopy = new HashMap<>();
        for (String key : animations.keySet()) {
            Frame[] frames = animations.get(key);
            Frame[] framesCopy = new Frame[frames.length];
            for (int i = 0; i < framesCopy.length; i++) {
                framesCopy[i] = frames[i].copy();
            }
            animationsCopy.put(key, framesCopy);
        }
        return animationsCopy;
    }

    public MapTile build(float x, float y) {
        GameObject bottomLayerAnimation = new GameObject(x, y, cloneAnimations(bottomLayer), "DEFAULT");
        GameObject midLayerAnimation = null, topLayerAnimation = null;
        if (!midLayer.isEmpty()) {
            midLayerAnimation = new GameObject(x, y, cloneAnimations(midLayer), "DEFAULT");
        }
        if (!topLayer.isEmpty()) {
            topLayerAnimation = new GameObject(x, y, cloneAnimations(topLayer), "DEFAULT");
        }

        return new MapTile(x, y, bottomLayerAnimation, topLayerAnimation, midLayerAnimation, tileType, tileIndices);
    }
}
