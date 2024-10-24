package Maps;

import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Sprite;
import Level.Map;
import Tilesets.FarmlandTileset;
import Utils.Colors;
import Utils.Point;

// Represents the map that is used as a background for the main menu and credits menu screen
public class TitleScreenMap extends Map {

    private Sprite player;

    public TitleScreenMap() {
        super("title_screen_map.txt", new FarmlandTileset());
        Point playerLocation = getMapTile(10, 8).getLocation().subtractX(7).subtractY(8);
        player = new Sprite(ImageLoader.loadSubImage("Doug.png", 0, 128, 64, 64, true));
        player.setLocation(playerLocation.x, playerLocation.y);
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
        player.draw(graphicsHandler);
    }
}
