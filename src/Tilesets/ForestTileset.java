package Tilesets;

import java.util.ArrayList;

import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import Level.TileType;
import Level.Tileset;

public class ForestTileset extends Tileset{
    
    public ForestTileset(){
        super(ImageLoader.load("Forest.png"), 16, 16, 3);
    }

    @Override
    public ArrayList<MapTileBuilder> defineTiles() {

        ArrayList<MapTileBuilder> mapTiles = new ArrayList<>();

        // dark grass
        Frame darkGrassFrame = new FrameBuilder(getSubImage(1, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder darkGrass = new MapTileBuilder(darkGrassFrame);
                  
        
       
        mapTiles.add(darkGrass);

        //something

        Frame somethingFrame = new FrameBuilder(getSubImage(0, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder something = new MapTileBuilder(somethingFrame) 
                .withTileType(TileType.NOT_PASSABLE);                   
        
       
        mapTiles.add(something);

        //grass
        Frame grassFrame = new FrameBuilder(getSubImage(6, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder grass = new MapTileBuilder(grassFrame);                   
        
       
        mapTiles.add(grass);

        //tree 00
        Frame tree00Frame = new FrameBuilder(getSubImage(0, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder tree00 = new MapTileBuilder(tree00Frame) 
                .withTileType(TileType.NOT_PASSABLE);                   
        
       
        mapTiles.add(tree00);

        //tree 01
        Frame tree01Frame = new FrameBuilder(getSubImage(0, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder tree01 = new MapTileBuilder(tree01Frame) 
                .withTileType(TileType.NOT_PASSABLE);                   
        
       
        mapTiles.add(tree01);

        //tree 02
        Frame tree02Frame = new FrameBuilder(getSubImage(0, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder tree02 = new MapTileBuilder(tree02Frame) 
                .withTileType(TileType.NOT_PASSABLE);                   
        
       
        mapTiles.add(tree02);

        //tree 03
        Frame tree03Frame = new FrameBuilder(getSubImage(0, 3))
                .withScale(tileScale)
                .build();

        MapTileBuilder tree03 = new MapTileBuilder(tree03Frame) 
                .withTileType(TileType.NOT_PASSABLE);                   
        
       
        mapTiles.add(tree03);

        //tree 04
        Frame tree04Frame = new FrameBuilder(getSubImage(0, 4))
                .withScale(tileScale)
                .build();

        MapTileBuilder tree04 = new MapTileBuilder(tree04Frame) 
                .withTileType(TileType.NOT_PASSABLE);                   
        
       
        mapTiles.add(tree04);

        //tree 05
        Frame tree05Frame = new FrameBuilder(getSubImage(0, 5))
                .withScale(tileScale)
                .build();

        MapTileBuilder tree05 = new MapTileBuilder(tree05Frame) 
                .withTileType(TileType.NOT_PASSABLE);                   
        
       
        mapTiles.add(tree05);

        //tree 10
        Frame tree10Frame = new FrameBuilder(getSubImage(1, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder tree10 = new MapTileBuilder(tree10Frame) 
                .withTileType(TileType.NOT_PASSABLE);                   
        
       
        mapTiles.add(tree10);

        //tree 11
        Frame tree11Frame = new FrameBuilder(getSubImage(1, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder tree11 = new MapTileBuilder(tree11Frame) 
                .withTileType(TileType.NOT_PASSABLE);                   
        
       
        mapTiles.add(tree11);

        //tree 12
        Frame tree12Frame = new FrameBuilder(getSubImage(1, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder tree12 = new MapTileBuilder(tree12Frame) 
                .withTileType(TileType.NOT_PASSABLE);                   
        
       
        mapTiles.add(tree12);

        //tree 13
        Frame tree13Frame = new FrameBuilder(getSubImage(1, 3))
                .withScale(tileScale)
                .build();

        MapTileBuilder tree13 = new MapTileBuilder(tree13Frame) 
                .withTileType(TileType.NOT_PASSABLE);                   
        
       
        mapTiles.add(tree13);

        //tree 14
        Frame tree14Frame = new FrameBuilder(getSubImage(1, 4))
                .withScale(tileScale)
                .build();

        MapTileBuilder tree14 = new MapTileBuilder(tree14Frame) 
                .withTileType(TileType.NOT_PASSABLE);                   
        
       
        mapTiles.add(tree14);

        //tree 15
        Frame tree15Frame = new FrameBuilder(getSubImage(1, 5))
                .withScale(tileScale)
                .build();

        MapTileBuilder tree15 = new MapTileBuilder(tree15Frame) 
                .withTileType(TileType.NOT_PASSABLE);                   
        
       
        mapTiles.add(tree15);

        //tree 20
        Frame tree20Frame = new FrameBuilder(getSubImage(2, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder tree20 = new MapTileBuilder(tree20Frame) 
                .withTileType(TileType.NOT_PASSABLE);                   
        
       
        mapTiles.add(tree20);

        //tree 21
        Frame tree21Frame = new FrameBuilder(getSubImage(2, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder tree21 = new MapTileBuilder(tree21Frame) 
                .withTileType(TileType.NOT_PASSABLE);                   
        
       
        mapTiles.add(tree21);

        //tree 22
        Frame tree22Frame = new FrameBuilder(getSubImage(2, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder tree22 = new MapTileBuilder(tree22Frame) 
                .withTileType(TileType.NOT_PASSABLE);                   
        
       
        mapTiles.add(tree22);

        //tree 23
        Frame tree23Frame = new FrameBuilder(getSubImage(2, 3))
                .withScale(tileScale)
                .build();

        MapTileBuilder tree23 = new MapTileBuilder(tree23Frame) 
                .withTileType(TileType.NOT_PASSABLE);                   
        
       
        mapTiles.add(tree23);

        //tree 24
        Frame tree24Frame = new FrameBuilder(getSubImage(2, 4))
                .withScale(tileScale)
                .build();

        MapTileBuilder tree24 = new MapTileBuilder(tree24Frame) 
                .withTileType(TileType.NOT_PASSABLE);                   
        
       
        mapTiles.add(tree24);

        //tree 25
        Frame tree25Frame = new FrameBuilder(getSubImage(2, 5))
                .withScale(tileScale)
                .build();

        MapTileBuilder tree25 = new MapTileBuilder(tree25Frame) 
                .withTileType(TileType.NOT_PASSABLE);                   
        
       
        mapTiles.add(tree25);

        //tree 30
        Frame tree30Frame = new FrameBuilder(getSubImage(3, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder tree30 = new MapTileBuilder(tree30Frame) 
                .withTileType(TileType.NOT_PASSABLE);                   
        
       
        mapTiles.add(tree30);

        //tree 31
        Frame tree31Frame = new FrameBuilder(getSubImage(3, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder tree31 = new MapTileBuilder(tree31Frame) 
                .withTileType(TileType.NOT_PASSABLE);                   
        
       
        mapTiles.add(tree31);

        //tree 31
        Frame tree32Frame = new FrameBuilder(getSubImage(3, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder tree32 = new MapTileBuilder(tree32Frame) 
                .withTileType(TileType.NOT_PASSABLE);                   
        
       
        mapTiles.add(tree32);

        //tree 33
        Frame tree33Frame = new FrameBuilder(getSubImage(3, 3))
                .withScale(tileScale)
                .build();

        MapTileBuilder tree33 = new MapTileBuilder(tree33Frame) 
                .withTileType(TileType.NOT_PASSABLE);                   
        
       
        mapTiles.add(tree33);

        //tree 34
        Frame tree34Frame = new FrameBuilder(getSubImage(3, 4))
                .withScale(tileScale)
                .build();

        MapTileBuilder tree34 = new MapTileBuilder(tree34Frame) 
                .withTileType(TileType.NOT_PASSABLE);                   
        
       
        mapTiles.add(tree34);

        //tree 31
        Frame tree35Frame = new FrameBuilder(getSubImage(3, 5))
                .withScale(tileScale)
                .build();

        MapTileBuilder tree35 = new MapTileBuilder(tree35Frame) 
                .withTileType(TileType.NOT_PASSABLE);                   
        
       
        mapTiles.add(tree35);

        //tree 40
        Frame tree40Frame = new FrameBuilder(getSubImage(4, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder tree40 = new MapTileBuilder(tree40Frame) 
                .withTileType(TileType.NOT_PASSABLE);                   
        
       
        mapTiles.add(tree40);

        //tree 41
        Frame tree41Frame = new FrameBuilder(getSubImage(4, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder tree41 = new MapTileBuilder(tree41Frame) 
                .withTileType(TileType.NOT_PASSABLE);                   
        
       
        mapTiles.add(tree41);

        //tree 42
        Frame tree42Frame = new FrameBuilder(getSubImage(4, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder tree42 = new MapTileBuilder(tree42Frame) 
                .withTileType(TileType.NOT_PASSABLE);                   
        
       
        mapTiles.add(tree42);

         //tree 43
        Frame tree43Frame = new FrameBuilder(getSubImage(4, 3))
                .withScale(tileScale)
                .build();

        MapTileBuilder tree43 = new MapTileBuilder(tree43Frame) 
                .withTileType(TileType.NOT_PASSABLE);                   
        
       
        mapTiles.add(tree43);

        //tree 44
        Frame tree44Frame = new FrameBuilder(getSubImage(4, 4))
                .withScale(tileScale)
                .build();

        MapTileBuilder tree44 = new MapTileBuilder(tree44Frame) 
                .withTileType(TileType.NOT_PASSABLE);                   
        
       
        mapTiles.add(tree44);

        //tree 45
        Frame tree45Frame = new FrameBuilder(getSubImage(4, 5))
                .withScale(tileScale)
                .build();

        MapTileBuilder tree45 = new MapTileBuilder(tree45Frame) 
                .withTileType(TileType.NOT_PASSABLE);                   
        
       
        mapTiles.add(tree45);

        //tree 50
        Frame tree50Frame = new FrameBuilder(getSubImage(5, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder tree50 = new MapTileBuilder(tree50Frame) 
                .withTileType(TileType.NOT_PASSABLE);                   
        
       
        mapTiles.add(tree50);

        //tree 51
        Frame tree51Frame = new FrameBuilder(getSubImage(5, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder tree51 = new MapTileBuilder(tree51Frame) 
                .withTileType(TileType.NOT_PASSABLE);                   
        
       
        mapTiles.add(tree51);

        //tree 52
        Frame tree52Frame = new FrameBuilder(getSubImage(5, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder tree52 = new MapTileBuilder(tree52Frame) 
                .withTileType(TileType.NOT_PASSABLE);                   
        
       
        mapTiles.add(tree52);

        //tree 53
        Frame tree53Frame = new FrameBuilder(getSubImage(5, 3))
                .withScale(tileScale)
                .build();

        MapTileBuilder tree53 = new MapTileBuilder(tree53Frame) 
                .withTileType(TileType.NOT_PASSABLE);                   
        
       
        mapTiles.add(tree53);

        //tree 54
        Frame tree54Frame = new FrameBuilder(getSubImage(5, 4))
                .withScale(tileScale)
                .build();

        MapTileBuilder tree54 = new MapTileBuilder(tree54Frame) 
                .withTileType(TileType.NOT_PASSABLE);                   
        
       
        mapTiles.add(tree54);

        //tree 55
        Frame tree55Frame = new FrameBuilder(getSubImage(5, 5))
                .withScale(tileScale)
                .build();

        MapTileBuilder tree55 = new MapTileBuilder(tree55Frame) 
                .withTileType(TileType.NOT_PASSABLE);                   
        
       
        mapTiles.add(tree55);


        




        return mapTiles;

    }
}
