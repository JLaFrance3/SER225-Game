package Tilesets;

import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import Level.TileType;
import Level.Tileset;

import java.util.ArrayList;
public class DungeonTileSet extends Tileset{

    public DungeonTileSet() {
        super(ImageLoader.load("DungeonTileSet.png"), 16, 16, 3);
    }

    @Override
    public ArrayList<MapTileBuilder> defineTiles() {
        ArrayList<MapTileBuilder> mapTiles = new ArrayList<>();

        // white brick
        Frame whiteBrickFrame = new FrameBuilder(getSubImage(1, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder whiteBrick = new MapTileBuilder(whiteBrickFrame);
        .withTileType(TileType.NOT_PASSABLE);
       
        mapTiles.add(whiteBrick);


        // clearBrick
        Frame clearBrickFrame = new FrameBuilder(getSubImage(3, 3))
                .withScale(tileScale)
                .build();

        MapTileBuilder clearBrick = new MapTileBuilder(clearBrickFrame);
        
       
        mapTiles.add(clearBrick);

        // blackBrick
        Frame blackBrickFrame = new FrameBuilder(getSubImage(0, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder blackBrick = new MapTileBuilder(blackBrickFrame);
        
       
        mapTiles.add(blackBrick);

        // pillarLow
        Frame pillarLowFrame = new FrameBuilder(getSubImage(7, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder pillarLow = new MapTileBuilder(pillarLowFrame);
        
       
        mapTiles.add(pillarLow);

        // pillarBottome
        Frame pillarBottomFrame = new FrameBuilder(getSubImage(8, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder pillarBottom = new MapTileBuilder(pillarBottomFrame);
        
       
        mapTiles.add(pillarBottom);

        // pillarMId
        Frame pillarMidFrame = new FrameBuilder(getSubImage(6, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder pillarMid = new MapTileBuilder(pillarMidFrame);
        
       
        mapTiles.add(pillarMid);

        // PillarTop
        Frame pillarTopFrame = new FrameBuilder(getSubImage(5, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder pillarTop = new MapTileBuilder(pillarTopFrame);
        
       
        mapTiles.add(pillarTop);

        
       

        // gateTopLeft
        Frame gateTopLeftFrame = new FrameBuilder(getSubImage(6, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder gateTopLeft = new MapTileBuilder(gateTopLeftFrame);
        
       
        mapTiles.add(gateTopLeft);

        // gateMidLeft
        Frame gateBottomLeftFrame = new FrameBuilder(getSubImage(7, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder gateBottomLeft = new MapTileBuilder(gateBottomLeftFrame);
        mapTiles.add(gateBottomLeft);

         // gateTopMid
         Frame gateTopMidFrame = new FrameBuilder(getSubImage(6, 3))
         .withScale(tileScale)
         .build();

        MapTileBuilder gateTopMid = new MapTileBuilder(gateTopMidFrame);
        
       
        mapTiles.add(gateTopMid);

   

    // gateTopMid
    Frame gateMidpMidFrame = new FrameBuilder(getSubImage(7, 3))
    .withScale(tileScale)
    .build();

   MapTileBuilder gateMidMid = new MapTileBuilder(gateMidpMidFrame);
   
  
   mapTiles.add(gateMidMid);


    






        return mapTiles;
    }
    
}
