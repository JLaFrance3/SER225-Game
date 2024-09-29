package Maps;

import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.ImageEffect;
import GameObject.Sprite;
import Level.Map;
import Tilesets.CommonTileset;
import Tilesets.FarmlandTileset;
import Utils.Colors;
import Utils.Point;

// Represents the map that is used as a background for the main menu and credits menu screen
public class TitleScreenMap extends Map {

    private Sprite player;

    public TitleScreenMap() {
        super("title_screen_map.txt", new FarmlandTileset());
        Point playerLocation = getMapTile(11, 8).getLocation().subtractX(6).subtractY(7);
        player = new Sprite(ImageLoader.loadSubImage("Doug.png", Colors.MAGENTA, 0, 128, 63, 63));
        player.setLocation(playerLocation.x, playerLocation.y);
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
        player.draw(graphicsHandler);
    }
}
