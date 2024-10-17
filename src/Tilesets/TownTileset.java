package Tilesets;

import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import Level.TileType;
import Level.Tileset;
import java.util.ArrayList;

// This class represents a "common" tileset of standard tiles defined in the CommonTileset.png file
public class TownTileset extends Tileset {

    public TownTileset() {
        super(ImageLoader.load("TownTileset.png", true), 32, 32);
    }

    @Override
    public ArrayList<MapTileBuilder> defineTiles() {
        ArrayList<MapTileBuilder> mapTiles = new ArrayList<>();

        // Terrain tiles
        Frame[] terrainFrames = new Frame[54];
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < terrainFrames.length / 3; j++) {
                terrainFrames[j+(i*18)] = new FrameBuilder(getSubImage(j / 3, j % 3 + (i*3), false))
                        .build();
        
                MapTileBuilder terrainTiles;
                if (i >= 2 && j < 15) {
                    terrainTiles = new MapTileBuilder(terrainFrames[j+(i*18)])
                            .withTileType(TileType.NOT_PASSABLE);;
                }
                else {
                    terrainTiles = new MapTileBuilder(terrainFrames[j+(i*18)]);
                }

                mapTiles.add(terrainTiles);
            }
        }

        // Animated water tiles
        Frame[][] waterFrames = new Frame[15][2];
        for(int i = 0; i < waterFrames.length; i++) {
            waterFrames[i][0] = new FrameBuilder(getSubImage(i / 3, i % 3 + 9, false), 65)
                    .build();
            waterFrames[i][1] = new FrameBuilder(getSubImage(i / 3, i % 3 + 12, false), 65)
                    .build();

            MapTileBuilder waterTile = new MapTileBuilder(waterFrames[i])
                    .withTileType(TileType.NOT_PASSABLE);

            mapTiles.add(waterTile);
        };
        Frame[] waterFrames2 = new Frame[3];
        waterFrames2[0] = new FrameBuilder(getSubImage(5, 9, false), 95)
                .build();
        waterFrames2[1] = new FrameBuilder(getSubImage(5, 10, false), 95)
                .build();
        waterFrames2[2] = new FrameBuilder(getSubImage(5, 11, false), 95)
                .build();
        MapTileBuilder waterTile = new MapTileBuilder(waterFrames2)
                .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(waterTile);

        // Building walls
        Frame[] wallFrames = new Frame[27];
        for(int i = 0; i < wallFrames.length; i++) {
            wallFrames[i] = new FrameBuilder(getSubImage(i % 3 + 6, i / 3, false))
                                .build();
                
            MapTileBuilder wallTiles;
            if (i % 3 == 2) {
                wallTiles = new MapTileBuilder(wallFrames[i]);
            }
            else {
                wallTiles = new MapTileBuilder(wallFrames[i])
                        .withTileType(TileType.NOT_PASSABLE);
            }

            mapTiles.add(wallTiles);
        }

        //Blank Tile
        Frame blankFrame = new FrameBuilder(getSubImage(4, 28, false))
                .build();
        MapTileBuilder blankTile = new MapTileBuilder(blankFrame);
        mapTiles.add(blankTile);

        // Roofs
        Frame[] roofFrames = new Frame[84];
        for(int i = 0; i < roofFrames.length; i++) {
            roofFrames[i] = new FrameBuilder(getSubImage(i % 4 + 6, i / 4 + 9, false))
                                .build();
                MapTileBuilder roofTiles = new MapTileBuilder(blankFrame)
                                .withTopLayer(roofFrames[i]);

                mapTiles.add(roofTiles);
        }

        // Odd roofs
        Frame[] roofsFrames = new Frame[10];
        for(int i = 0; i < roofsFrames.length; i++) {
            roofsFrames[i] = new FrameBuilder(getSubImage(5, i + 20, false))
                                .build();
                
            MapTileBuilder roofsTiles = new MapTileBuilder(blankFrame)
                    .withTopLayer(roofsFrames[i]);

            mapTiles.add(roofsTiles);
        }

        // Bridges
        Frame[] bridgeFrames = new Frame[25];
        for(int i = 0; i < bridgeFrames.length; i++) {
            bridgeFrames[i] = new FrameBuilder(getSubImage(i / 5 + 5, i % 5 + 30, false))
                                .build();
                
            MapTileBuilder bridgeTiles = new MapTileBuilder(bridgeFrames[i]);

            mapTiles.add(bridgeTiles);
        }

        // Nicer buildings
        Frame[] niceBuildingFrames = new Frame[54];
        for(int i = 0; i < niceBuildingFrames.length; i++) {
                if (i < 9) {
                    niceBuildingFrames[i] = new FrameBuilder(getSubImage(i / 3 + 5, i % 3 + 35, false))
                            .build();
                }
                else if (i < 24){
                    niceBuildingFrames[i] = new FrameBuilder(getSubImage(i / 6 + 6, i % 6 + 35, false))
                            .build();
                }
                else if (i < 49){
                    niceBuildingFrames[i] = new FrameBuilder(getSubImage((i-6) / 9 + 8, (i-6) % 9 + 32, false))
                            .build();
                }
                else {
                    niceBuildingFrames[i] = new FrameBuilder(getSubImage(5, i - 37, false))
                            .build();
                }
                
                MapTileBuilder niceBuildingTiles;
                switch (i) {
                        case 3, 4, 5:
                                niceBuildingTiles = new MapTileBuilder(niceBuildingFrames[i]);
                                break;
                        case 18, 19, 27, 28:
                                niceBuildingTiles = new MapTileBuilder(roofFrames[62])
                                                .withTopLayer(niceBuildingFrames[i])
                                                .withTileType(TileType.NOT_PASSABLE);
                                break;
                        default:
                                niceBuildingTiles = new MapTileBuilder(niceBuildingFrames[i])
                                                .withTileType(TileType.NOT_PASSABLE);
                                break;
                }

                mapTiles.add(niceBuildingTiles);
        }

        // Building decor
        Frame[] buildingDecorFrames = new Frame[78];
        for(int i = 0; i < buildingDecorFrames.length; i++) {
            if (i < 36) {
                buildingDecorFrames[i] = new FrameBuilder(getSubImage(i % 4 + 9, i / 4, false))
                        .build();
            }
            else {
                buildingDecorFrames[i] = new FrameBuilder(getSubImage(i % 3 + 10, i / 3 - 3, false))
                        .build();
            }
                
            MapTileBuilder buildingDecorTiles;
            switch (i) {
                case 16, 17, 20, 21, 72, 73, 74, 75, 76, 77:
                        buildingDecorTiles = new MapTileBuilder(buildingDecorFrames[i]);
                        break;
                default:
                        buildingDecorTiles = new MapTileBuilder(buildingDecorFrames[i])
                                .withTileType(TileType.NOT_PASSABLE);
                        break;
            }

            mapTiles.add(buildingDecorTiles);
        }

        // Town decor
        Frame[] decorFrames = new Frame[158];
        for(int i = 0; i < decorFrames.length; i++) {
            if (i < 115) {
                decorFrames[i] = new FrameBuilder(getSubImage(i % 5, i / 5 + 15, false))
                        .build();
            }
            else {
                decorFrames[i] = new FrameBuilder(getSubImage((i-3) % 8, (i-3) / 8 + 24, false))
                        .build();
            }
                
            MapTileBuilder decorTiles;
            
            switch (i) {
                case 6, 7:
                        decorTiles = new MapTileBuilder(decorFrames[i]);
                        break;
                case 0, 5, 10, 75, 76, 80, 81, 85, 86:
                        decorTiles = new MapTileBuilder(blankFrame)
                                        .withTopLayer(decorFrames[i]);
                        break;
                default:
                        if (i >= 115 && (i-3) % 8 > 2) {
                                decorTiles = new MapTileBuilder(decorFrames[i]);
                        }
                        else {
                                decorTiles = new MapTileBuilder(decorFrames[i])
                                                .withTileType(TileType.NOT_PASSABLE);
                        }
                        break;
            }

            mapTiles.add(decorTiles);
        }

        // Cobble terrain
        Frame[] cobbleFrames = new Frame[20];
        for(int i = 0; i < cobbleFrames.length; i++) {
            cobbleFrames[i] = new FrameBuilder(getSubImage(i / 3 + 13, i % 3 + 34, false))
                    .build();
                
            MapTileBuilder cobbleTiles = new MapTileBuilder(cobbleFrames[i]);

            mapTiles.add(cobbleTiles);
        }

        // Trees
        Frame[] treeFrames = new Frame[72];
        for(int i = 0; i < treeFrames.length; i++) {
            if (i < 30) {
                treeFrames[i] = new FrameBuilder(getSubImage(i % 3 + 17, i / 3, false))
                    .build();
            }
            else if (i == 30) {
                treeFrames[i] = new FrameBuilder(getSubImage(20, 0, false))
                    .build();
            }
            else if (i < 59) {
                treeFrames[i] = new FrameBuilder(getSubImage((i-3) % 4 + 17, (i-3) / 4 + 3, false))
                    .build();
            }
            else {
                treeFrames[i] = new FrameBuilder(getSubImage((i+1) / 6 + 11, (i+1) % 6 + 12, false))
                    .build();
            }
                
            MapTileBuilder treeTiles;
            switch (i) {
                case 0, 3, 6:
                        treeTiles = new MapTileBuilder(blankFrame)
                                        .withTopLayer(treeFrames[i]);
                        break;
                default:
                        treeTiles = new MapTileBuilder(treeFrames[i])
                                        .withTileType(TileType.NOT_PASSABLE);
                        break;
            }

            mapTiles.add(treeTiles);
        }

        // Tree tops
        Frame[] treeTopFrames = new Frame[37];
        for(int i = 0; i < treeTopFrames.length; i++) {
            if (i == 36) {
                treeTopFrames[i] = new FrameBuilder(getSubImage(20, 7, false))
                    .build();
            }
            else {
                treeTopFrames[i] = new FrameBuilder(getSubImage(i % 3 + 21, i / 3, false))
                    .build();
            }
                
            MapTileBuilder treeTopTiles = new MapTileBuilder(blankFrame)
                    .withTopLayer(treeTopFrames[i]);

            mapTiles.add(treeTopTiles);
        }

        // Torch animation
        Frame[] torchFrames = new Frame[4];
        torchFrames[0] = new FrameBuilder(getSubImage(23, 14, false), 65)
                .build();
        torchFrames[1] = new FrameBuilder(getSubImage(23, 15, false), 65)
                .build();
        torchFrames[2] = new FrameBuilder(getSubImage(23, 16, false), 65)
                .build();
        torchFrames[3] = new FrameBuilder(getSubImage(23, 17, false), 65)
                .build();
        MapTileBuilder torchTile = new MapTileBuilder(torchFrames);
        mapTiles.add(torchTile);

        // Fence
        Frame[] fenceFrames = new Frame[16];
        for(int i = 0; i < fenceFrames.length; i++) {
            fenceFrames[i] = new FrameBuilder(getSubImage(i / 3 + 17, i % 3 + 18, false))
                    .build();
                
            MapTileBuilder fenceTiles = new MapTileBuilder(fenceFrames[i])
                    .withTileType(TileType.NOT_PASSABLE);

            mapTiles.add(fenceTiles);
        }

        // City walls
        Frame[] wallsFrames = new Frame[92];
        for(int i = 0; i < wallsFrames.length; i++) {
            wallsFrames[i] = new FrameBuilder(getSubImage(i % 4 + 13, i / 4, false))
                    .build();
                
            MapTileBuilder wallsTiles;
            if (i % 4 == 0 && i < 52 || i == 51 || i == 55 || i == 59) {
                wallsTiles = new MapTileBuilder(blankFrame)
                        .withTopLayer(wallsFrames[i]);
            }
            else if (i % 4 == 3) {
                wallsTiles = new MapTileBuilder(wallsFrames[i]);
            }
            else if (i == 53 || i == 57) {
                wallsTiles = new MapTileBuilder(wallsFrames[i]);
            }
            else {
                wallsTiles = new MapTileBuilder(wallsFrames[i])
                        .withTileType(TileType.NOT_PASSABLE);
            }

            mapTiles.add(wallsTiles);
        }

        // Building wall variations
        Frame[] buildingFrames = new Frame[72];
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 9; j++) {
                buildingFrames[j+(i*9)] = new FrameBuilder(getSubImage(j / 3 + 10 + (i/2*3), j % 3 + 23 + (i%2*3), false))
                        .build();

                MapTileBuilder buildingTiles;
                if (j >= 6) {
                        buildingTiles = new MapTileBuilder(buildingFrames[j+(i*9)]);
                }
                else {
                        buildingTiles = new MapTileBuilder(buildingFrames[j+(i*9)])
                                .withTileType(TileType.NOT_PASSABLE);
                }

                mapTiles.add(buildingTiles);
            }
        }

        // Animal feed
        Frame[] feedFrames = new Frame[7];
        for(int i = 0; i < feedFrames.length; i++) {
                feedFrames[i] = new FrameBuilder(getSubImage(i / 2 + 17, i % 2 + 21, false))
                    .build();
                
            MapTileBuilder feedTiles = new MapTileBuilder(feedFrames[i])
                    .withTileType(TileType.NOT_PASSABLE);

            mapTiles.add(feedTiles);
        }

        //Signs
        Frame[] signFrames = new Frame[11];
        for(int i = 0; i < signFrames.length; i++) {
                signFrames[i] = new FrameBuilder(getSubImage(i / 3 + 10, i % 3 + 29, false))
                                .build();

                MapTileBuilder signTiles;
                if (i == 2 || i == 5 || i == 10) {
                        signTiles = new MapTileBuilder(signFrames[i])
                                        .withTileType(TileType.NOT_PASSABLE);
                }
                else {
                        signTiles = new MapTileBuilder(blankFrame)
                                        .withTopLayer(signFrames[i]);
                }
                mapTiles.add(signTiles);
        }

        return mapTiles;
    }
}
