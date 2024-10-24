package Tilesets;

import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import Level.TileType;
import Level.Tileset;

import java.awt.Color;
import java.util.ArrayList;

public class IndoorTileset extends Tileset {

    public IndoorTileset() {
        super(ImageLoader.load("IndoorTileset.png", new Color(0, 0, 0, 0)), 32, 32);
    }

    @Override
    public ArrayList<MapTileBuilder> defineTiles() {
        ArrayList<MapTileBuilder> mapTiles = new ArrayList<>();

        // Blank Tile
        Frame blankFrame = new FrameBuilder(getSubImage(9, 0, false))
                .build();
        MapTileBuilder blankTile = new MapTileBuilder(blankFrame);
        mapTiles.add(blankTile);

        // Dark Tile
        Frame darkFrame = new FrameBuilder(getSubImage(8, 0, false))
                .build();
        MapTileBuilder darkTile = new MapTileBuilder(darkFrame)
                        .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(darkTile);

        // Floor tiles
        Frame[] floorFrames = new Frame[72];
        for(int i = 0; i < floorFrames.length; i++) {
            floorFrames[i] = new FrameBuilder(getSubImage(i / 9, i % 9, false))
                    .build();
            MapTileBuilder floorTiles = new MapTileBuilder(floorFrames[i]);

            mapTiles.add(floorTiles);
        }

        // Cabinet doors
        Frame[] cabinetDoorFrames = new Frame[12];
        for(int i = 0; i < cabinetDoorFrames.length; i++) {
            cabinetDoorFrames[i] = new FrameBuilder(getSubImage(i / 8 + 8, i % 8 + 1, false))
                    .build();
            MapTileBuilder cabinetDoorTiles = new MapTileBuilder(cabinetDoorFrames[i]);

            mapTiles.add(cabinetDoorTiles);
        }

        // Indoor items
        Frame[] indoorFrames = new Frame[117];
        for(int i = 0; i < indoorFrames.length; i++) {
            indoorFrames[i] = new FrameBuilder(getSubImage(i / 9 + 10, i % 9, false))
                    .build();
            MapTileBuilder indoorTiles;

            switch (i) {
                case 27, 28, 29, 36, 37, 38, 73, 82:
                        indoorTiles = new MapTileBuilder(blankFrame)
                                        .withTopLayer(indoorFrames[i]);
                        break;
                case 90, 91, 99, 100, 108, 109:
                        indoorTiles = new MapTileBuilder(indoorFrames[i]);
                        break;
            
                default:
                        indoorTiles = new MapTileBuilder(indoorFrames[i])
                                        .withTileType(TileType.NOT_PASSABLE);
                        break;
            }

            mapTiles.add(indoorTiles);
        }

        // Plain Walls
        Frame[] plainWallFrames = new Frame[108];
        for(int i = 0; i < 12; i++) {
            for(int j = 0; j < 9; j++) {
                plainWallFrames[j+(i*9)] = new FrameBuilder(getSubImage(j / 3 + (i/6*3), j % 3 + 9 + (i%6*3), false))
                        .build();

                MapTileBuilder plainWallTiles;
                if (j >= 6) {
                    plainWallTiles = new MapTileBuilder(plainWallFrames[j+(i*9)]);
                }
                else {
                    plainWallTiles = new MapTileBuilder(plainWallFrames[j+(i*9)])
                            .withTileType(TileType.NOT_PASSABLE);
                }

                mapTiles.add(plainWallTiles);
            }
        }

        // Fancy Walls
        Frame[] fancyWallFrames = new Frame[180];
        for(int i = 0; i < 12; i++) {
            for(int j = 0; j < 15; j++) {
                fancyWallFrames[j+(i*15)] = new FrameBuilder(getSubImage(j / 3 + 6 +(i/6*5), j % 3 + 9 + (i%6*3), false))
                        .build();

                MapTileBuilder fancyWallTiles;
                if (j >= 9) {
                    fancyWallTiles = new MapTileBuilder(fancyWallFrames[j+(i*15)]);
                }
                else {
                    fancyWallTiles = new MapTileBuilder(fancyWallFrames[j+(i*15)])
                            .withTileType(TileType.NOT_PASSABLE);
                }

                mapTiles.add(fancyWallTiles);
            }
        }

        // Torch animation
        Frame[] torchFrames = new Frame[4];
        torchFrames[0] = new FrameBuilder(getSubImage(16, 23, false), 120)
                .build();
        torchFrames[1] = new FrameBuilder(getSubImage(16, 24, false), 120)
                .build();
        torchFrames[2] = new FrameBuilder(getSubImage(16, 25, false), 120)
                .build();
        torchFrames[3] = new FrameBuilder(getSubImage(16, 26, false), 120)
                .build();
        MapTileBuilder torchTile = new MapTileBuilder(torchFrames);
        mapTiles.add(torchTile);

        // Candle Animation
        Frame[] candleFrames = new Frame[3];
        candleFrames[0] = new FrameBuilder(getSubImage(17, 23, false), 120)
                .build();
        candleFrames[1] = new FrameBuilder(getSubImage(17, 24, false), 120)
                .build();
        candleFrames[2] = new FrameBuilder(getSubImage(17, 25, false), 120)
                .build();
        MapTileBuilder candleTile = new MapTileBuilder(candleFrames);
        mapTiles.add(candleTile);

        // Unlit Torch
        Frame unlitTorchFrame = new FrameBuilder(getSubImage(17, 26, false))
                .build();
        MapTileBuilder unlitTorchTile = new MapTileBuilder(unlitTorchFrame);
        mapTiles.add(unlitTorchTile);

        //Candelabra Animation
        Frame[] candelabraFrames = new Frame[3];
        candelabraFrames[0] = new FrameBuilder(getSubImage(18, 23, false), 120)
                .build();
                candelabraFrames[1] = new FrameBuilder(getSubImage(18, 24, false), 120)
                .build();
                candelabraFrames[2] = new FrameBuilder(getSubImage(18, 25, false), 120)
                .build();
        MapTileBuilder candelabraTile = new MapTileBuilder(candelabraFrames);
        mapTiles.add(candelabraTile);

        //Stove Animation
        Frame[] stoveFrames = new Frame[2];
        stoveFrames[0] = new FrameBuilder(getSubImage(19, 23, false), 120)
                .build();
                stoveFrames[1] = new FrameBuilder(getSubImage(19, 24, false), 120)
                .build();
        MapTileBuilder stoveTile = new MapTileBuilder(stoveFrames);
        mapTiles.add(stoveTile);

        //Clock Animations
        Frame[][] clockFrames = new Frame[3][3];
        for(int i = 0; i < clockFrames.length; i++) {
            clockFrames[i][0] = new FrameBuilder(getSubImage(20 + i, 23, false), 120)
                    .build();
            clockFrames[i][1] = new FrameBuilder(getSubImage(20 + i, 24, false), 120)
                    .build();
            clockFrames[i][2] = new FrameBuilder(getSubImage(20 + i, 25, false), 120)
                    .build();

            MapTileBuilder clockTile = new MapTileBuilder(clockFrames[i])
                    .withTileType(TileType.NOT_PASSABLE);
            mapTiles.add(clockTile);
        }

        //Large Furnitures
        Frame[] furnitureFrames = new Frame[42];
        for(int i = 0; i < furnitureFrames.length; i++) {
            furnitureFrames[i] = new FrameBuilder(getSubImage(i % 3 + 16, i / 3 + 9, false))
                    .build();
            MapTileBuilder furnitureTiles = new MapTileBuilder(furnitureFrames[i])
                    .withTileType(TileType.NOT_PASSABLE);

            mapTiles.add(furnitureTiles);
        }

        // Cells and shelves
        Frame[] cellAndShelfFrames = new Frame[47];
        for(int i = 0; i < cellAndShelfFrames.length; i++) {
            cellAndShelfFrames[i] = new FrameBuilder(getSubImage(i % 4 + 19, i / 4 + 9, false))
                    .build();
            MapTileBuilder cellAndShelfTiles;

            if (i < 9 && i % 4 == 3) {
                cellAndShelfTiles = new MapTileBuilder(cellAndShelfFrames[i]);
            }
            else if (i <= 24 && i % 4 != 3) {
                cellAndShelfTiles = new MapTileBuilder(blankFrame)
                        .withTopLayer(cellAndShelfFrames[i]);
            }
            else {
                cellAndShelfTiles = new MapTileBuilder(cellAndShelfFrames[i])
                        .withTileType(TileType.NOT_PASSABLE);
            }

            mapTiles.add(cellAndShelfTiles);
        }

        //Shop items
        Frame[] shopItemFrames = new Frame[150];
        for(int i = 0; i < shopItemFrames.length; i++) {
            shopItemFrames[i] = new FrameBuilder(getSubImage(i / 24 + 23, i % 24, false))
                    .build();
            MapTileBuilder shopItemTiles = new MapTileBuilder(blankFrame)
                        .withTopLayer(shopItemFrames[i]);

            mapTiles.add(shopItemTiles);
        }

        //Stairs
        Frame[] stairFrames = new Frame[176];
        for(int i = 0; i < stairFrames.length; i++) {
            stairFrames[i] = new FrameBuilder(getSubImage(i / 8, i % 8 + 27, false))
                    .build();
            MapTileBuilder stairTiles = new MapTileBuilder(stairFrames[i])
                    .withTileType(TileType.NOT_PASSABLE);

            mapTiles.add(stairTiles);
        }

        // Doors
        Frame[] doorFrames = new Frame[4];
        for(int i = 0; i < doorFrames.length; i++) {
                doorFrames[i] = new FrameBuilder(getSubImage(i + 18, 26, false))
                    .build();
            MapTileBuilder doorTiles = new MapTileBuilder(doorFrames[i])
                    .withTileType(TileType.NOT_PASSABLE);

            mapTiles.add(doorTiles);
        }

        return mapTiles;
    }
}
