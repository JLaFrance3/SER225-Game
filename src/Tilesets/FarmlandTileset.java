package Tilesets;

import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import Level.TileType;
import Level.Tileset;

import java.awt.Color;
import java.util.ArrayList;

// This class represents a "common" tileset of standard tiles defined in the CommonTileset.png file
public class FarmlandTileset extends Tileset {

    public FarmlandTileset() {
        super(ImageLoader.load("FarmlandTileset.png", new Color(0, 0, 0, 0)), 32, 32);
    }

    @Override
    public ArrayList<MapTileBuilder> defineTiles() {
        ArrayList<MapTileBuilder> mapTiles = new ArrayList<>();

        int tileIndex = 0;

        // Dirt
        Frame[] dirtFrames = new Frame[18];
        for(int i = 0; i < 18; i++) {
                dirtFrames[i] = new FrameBuilder(getSubImage(i / 3, i % 3 + (tileIndex / 18) * 3, false))
                                .build();

                MapTileBuilder dirtTiles = new MapTileBuilder(dirtFrames[i])
                                .withTileIndex(tileIndex);

                tileIndex++;
                mapTiles.add(dirtTiles);
        }


        // Grass tiles
        Frame[] grassFrames = new Frame[18];
        for(int i = 0; i < grassFrames.length; i++) {
                grassFrames[i] = new FrameBuilder(getSubImage(i / 3, i % 3 + (tileIndex / 18) * 3, false))
                                .build();

                MapTileBuilder grassTile = new MapTileBuilder(grassFrames[i])
                                .withTileIndex(tileIndex);

                tileIndex++;
                mapTiles.add(grassTile);
        };

        // Water tiles with animation
        Frame[][] waterFrames = new Frame[15][2];
        for(int i = 0; i < waterFrames.length; i++) {
                waterFrames[i][0] = new FrameBuilder(getSubImage(i / 3, i % 3 + (tileIndex / 18) * 3, false), 65)
                                .build();
                waterFrames[i][1] = new FrameBuilder(getSubImage(i / 3, i % 3 + 3 + (tileIndex / 18) * 3, false), 65)
                                .build();

                MapTileBuilder waterTile = new MapTileBuilder(waterFrames[i])
                                .withTileIndex(tileIndex)
                                .withTileType(TileType.NOT_PASSABLE);

                tileIndex++;
                mapTiles.add(waterTile);
        };

        // Chasm tiles
        Frame[] chasmFrames = new Frame[18];
        for(int i = 0; i < chasmFrames.length; i++) {
                chasmFrames[i] = new FrameBuilder(getSubImage(i / 3, i % 3 + 3 + ((tileIndex + 3) / 18) * 3, false))
                                .build();

                MapTileBuilder chasmTile = new MapTileBuilder(chasmFrames[i])
                                .withTileIndex(tileIndex)
                                .withTileType(TileType.NOT_PASSABLE);

                tileIndex++;
                mapTiles.add(chasmTile);
        };

        // Tall Grass
        Frame[] tallGrassFrames = new Frame[18];
        for(int i = 0; i < tallGrassFrames.length; i++) {
                tallGrassFrames[i] = new FrameBuilder(getSubImage(i / 3, i % 3 + 3 + ((tileIndex + 3) / 18) * 3, false))
                                .build();

                MapTileBuilder tallGrassTile = new MapTileBuilder(tallGrassFrames[i])
                                .withTileIndex(tileIndex);

                tileIndex++;
                mapTiles.add(tallGrassTile);
        };

        // Taller Grass
        Frame[] tallerGrassFrames = new Frame[18];
        for(int i = 0; i < tallerGrassFrames.length; i++) {
                tallerGrassFrames[i] = new FrameBuilder(getSubImage(i / 3, i % 3 + 3 + ((tileIndex + 3) / 18) * 3, false))
                                .build();

                MapTileBuilder tallerGrassTile = new MapTileBuilder(tallerGrassFrames[i])
                                .withTileIndex(tileIndex)
                                .withTileType(TileType.NOT_PASSABLE);

                tileIndex++;
                mapTiles.add(tallerGrassTile);
        };

        // Wheat Tiles
        Frame[] wheatFrames = new Frame[18];
        for(int i = 0; i < wheatFrames.length; i++) {
                wheatFrames[i] = new FrameBuilder(getSubImage(i / 3, i % 3 + 3 + ((tileIndex + 3) / 18) * 3, false))
                                .build();

                MapTileBuilder wheatTile = new MapTileBuilder(wheatFrames[i])
                                .withTileIndex(tileIndex)
                                .withTileType(TileType.NOT_PASSABLE);

                tileIndex++;
                mapTiles.add(wheatTile);
        };

        // Field Tiles
        Frame[] fieldFrames = new Frame[18];
        for(int i = 0; i < fieldFrames.length; i++) {
                fieldFrames[i] = new FrameBuilder(getSubImage(i / 3, i % 3 + 3 + ((tileIndex + 3) / 18) * 3, false))
                                .build();

                MapTileBuilder fieldTile = new MapTileBuilder(fieldFrames[i])
                                .withTileIndex(tileIndex);

                tileIndex++;
                mapTiles.add(fieldTile);
        };

        // Fence tiles
        Frame[] fenceFrames = new Frame[16];
        for(int i = 0; i < fenceFrames.length; i++) {
                fenceFrames[i] = new FrameBuilder(getSubImage(i / 3, i % 3 + 3 + ((tileIndex + 3) / 18) * 3, false))
                                .build();

                MapTileBuilder fenceTile = new MapTileBuilder(fenceFrames[i])
                                .withTileIndex(tileIndex)
                                .withTileType(TileType.NOT_PASSABLE);

                tileIndex++;
                mapTiles.add(fenceTile);
        };

        // Reeds
        Frame[] reedFrames = new Frame[28];
        for(int i = 0; i < reedFrames.length; i++) {
                reedFrames[i] = new FrameBuilder(getSubImage(i / 4 + 6, i % 4, false))
                                .build();

                MapTileBuilder reedTile = new MapTileBuilder(reedFrames[i])
                                .withTileIndex(tileIndex)
                                .withTileType(TileType.NOT_PASSABLE);

                tileIndex++;
                mapTiles.add(reedTile);
        };

        // Straw
        Frame[] strawFrames = new Frame[6];
        for(int i = 0; i < strawFrames.length; i++) {
                strawFrames[i] = new FrameBuilder(getSubImage(i / 2 + 6, i % 2 + 4, false))
                                .build();

                MapTileBuilder strawTile = new MapTileBuilder(strawFrames[i])
                                .withTileIndex(tileIndex);

                tileIndex++;
                mapTiles.add(strawTile);
        };

        // Barrels
        Frame[] barrelFrames = new Frame[12];
        for(int i = 0; i < barrelFrames.length; i++) {
                barrelFrames[i] = new FrameBuilder(getSubImage(i / 4 + 6, i % 4 + 6, false))
                                .build();

                MapTileBuilder barrelTile = new MapTileBuilder(barrelFrames[i])
                                .withTileIndex(tileIndex)
                                .withTileType(TileType.NOT_PASSABLE);

                tileIndex++;
                mapTiles.add(barrelTile);
        };

        // Signs
        Frame[] signFrames = new Frame[11];
        for(int i = 0; i < signFrames.length; i++) {
                signFrames[i] = new FrameBuilder(getSubImage(i % 3 + 6, i / 3 + 10, false))
                                .build();

                MapTileBuilder signTile = new MapTileBuilder(signFrames[i])
                                .withTileIndex(tileIndex)
                                .withTileType(TileType.NOT_PASSABLE);

                tileIndex++;
                mapTiles.add(signTile);
        };

        // Wheel Barrow
        Frame[] barrowFrames = new Frame[12];
        for(int i = 0; i < barrowFrames.length; i++) {
                barrowFrames[i] = new FrameBuilder(getSubImage(i % 2 + 6, i / 2 + 14, false))
                                .build();

                MapTileBuilder barrowTile = new MapTileBuilder(barrowFrames[i])
                                .withTileIndex(tileIndex)
                                .withTileType(TileType.NOT_PASSABLE);

                tileIndex++;
                mapTiles.add(barrowTile);
        };

        // Chests
        Frame[] chestFrames = new Frame[6];
        for(int i = 0; i < chestFrames.length; i++) {
                chestFrames[i] = new FrameBuilder(getSubImage(i / 2 + 9, i % 2 + 4, false))
                                .build();

                MapTileBuilder chestTile = new MapTileBuilder(chestFrames[i])
                                .withTileIndex(tileIndex)
                                .withTileType(TileType.NOT_PASSABLE);

                tileIndex++;
                mapTiles.add(chestTile);
        };

        // Log Stacks
        Frame[] logFrames = new Frame[11];
        for(int i = 0; i < logFrames.length; i++) {
                logFrames[i] = new FrameBuilder(getSubImage(i / 6 + 9, i % 6 + 6, false))
                                .build();

                MapTileBuilder logTile = new MapTileBuilder(logFrames[i])
                                .withTileIndex(tileIndex)
                                .withTileType(TileType.NOT_PASSABLE);

                tileIndex++;
                mapTiles.add(logTile);
        };

        // Produce bags
        Frame[] bagFrames = new Frame[30];
        for(int i = 0; i < bagFrames.length - 10; i++) {
                bagFrames[i] = new FrameBuilder(getSubImage(i / 5 + 9, i % 5 + 12, false))
                                .build();

                MapTileBuilder bagTile = new MapTileBuilder(bagFrames[i])
                                .withTileIndex(tileIndex)
                                .withTileType(TileType.NOT_PASSABLE);

                tileIndex++;
                mapTiles.add(bagTile);
        };
        for(int i = 20; i < bagFrames.length; i++) {
                bagFrames[i] = new FrameBuilder(getSubImage(i / 5 + 5, i % 5 + 17, false))
                                .build();

                MapTileBuilder bagTile = new MapTileBuilder(bagFrames[i])
                                .withTileIndex(tileIndex)
                                .withTileType(TileType.NOT_PASSABLE);

                tileIndex++;
                mapTiles.add(bagTile);
        };

        // Crops
        Frame[] cropFrames = new Frame[9];
        for(int i = cropFrames.length - 1; i >= 0; i--) {
                cropFrames[i] = new FrameBuilder(getSubImage(i / 6 + 11, -(i % 6) + 11, false))
                                .build();

                MapTileBuilder cropTile = new MapTileBuilder(cropFrames[i])
                                .withTileIndex(tileIndex)
                                .withTileType(TileType.NOT_PASSABLE);

                tileIndex++;
                mapTiles.add(cropTile);
        };

        //WIP Tile
        Frame WIPFrame = new FrameBuilder(getSubImage(5, 26, false))
                        .build();
        
        MapTileBuilder WIPTile = new MapTileBuilder(WIPFrame)
                        .withTileIndex(tileIndex);

        mapTiles.add(WIPTile);

        // Tree tops
        Frame[] treeFrames = new Frame[18];
        for(int i = 0; i < treeFrames.length; i++) {
                treeFrames[i] = new FrameBuilder(getSubImage(i % 3 + 13, i / 3, false))
                                .build();

                MapTileBuilder treeTile = new MapTileBuilder(WIPFrame)
                                .withTileIndex(tileIndex)
                                .withTopLayer(treeFrames[i]);

                tileIndex++;
                mapTiles.add(treeTile);
        };

        Frame tipFrame = new FrameBuilder(getSubImage(12, 7, false))
                        .build();

        MapTileBuilder tipTile = new MapTileBuilder(WIPFrame)
                        .withTileIndex(tileIndex)
                        .withTopLayer(tipFrame);
        tileIndex++;
        mapTiles.add(tipTile);

        Frame[] firFrames = new Frame[18];
        for(int i = 0; i < firFrames.length; i++) {
                firFrames[i] = new FrameBuilder(getSubImage(i % 3 + 13, i / 3 + 6, false))
                                .build();

                MapTileBuilder firTile = new MapTileBuilder(WIPFrame)
                                .withTileIndex(tileIndex)
                                .withTopLayer(firFrames[i]);

                tileIndex++;
                mapTiles.add(firTile);
        };

        // Tree Trunks
        Frame[] trunkFrames = new Frame[18];
        for(int i = 0; i < trunkFrames.length; i++) {
                trunkFrames[i] = new FrameBuilder(getSubImage(i / 6 + 13, i % 6 + 12, false))
                                .build();

                MapTileBuilder trunkTile = new MapTileBuilder(trunkFrames[i])
                                .withTileIndex(tileIndex)
                                .withTileType(TileType.NOT_PASSABLE);

                tileIndex++;
                mapTiles.add(trunkTile);
        };

        return mapTiles;
    }
}
