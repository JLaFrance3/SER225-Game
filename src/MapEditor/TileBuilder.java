package MapEditor;

import Engine.GraphicsHandler;
import Level.*;
import Utils.Colors;

import javax.swing.*;

import Builders.MapTileBuilder;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

public class TileBuilder extends JPanel {
    private Map map;
    private MapTile hoveredMapTile, pressedMapTile;
    private SelectedTileIndexHolder controlPanelHolder;
    private GraphicsHandler graphicsHandler = new GraphicsHandler();
    private JLabel hoveredTileIndexLabel;
    private boolean showNPCs;
    private boolean showEnhancedMapTiles;
    private boolean showTriggers;

    public TileBuilder(SelectedTileIndexHolder controlPanelHolder, JLabel hoveredTileIndexLabel) {
        setBackground(Colors.MAGENTA);
        setLocation(0, 0);
        setPreferredSize(new Dimension(585, 562));
        this.controlPanelHolder = controlPanelHolder;
        this.hoveredTileIndexLabel = hoveredTileIndexLabel;
        addMouseListener(new MouseListener() {
            @Override
            public void mouseExited(MouseEvent e) {
                hoveredMapTile = null;
                pressedMapTile = null;
                hoveredTileIndexLabel.setText("");
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                tileSelected(e.getPoint());
                pressedMapTile = getHoveredTile(e.getPoint());
            }

            @Override
            public void mouseClicked(MouseEvent e) { }

            @Override
            public void mouseReleased(MouseEvent e) { }

            @Override
            public void mouseEntered(MouseEvent e) { }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                tileHovered(e.getPoint());
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                tileHovered(e.getPoint());

                if (!(hoveredMapTile.equals(pressedMapTile))) {
                    tileSelected(e.getPoint());
                    pressedMapTile = getHoveredTile(e.getPoint());
                }
            }
        });
    }

    public void setMap(Map map) {
        this.map = map;
        setPreferredSize(new Dimension(map.getWidthPixels(), map.getHeightPixels()));
        repaint();
    }

    public void draw() {
        for (MapTile tile : map.getMapTiles()) {
            tile.draw(graphicsHandler);
        }

        if (showEnhancedMapTiles) {
            for (EnhancedMapTile enhancedMapTile : map.getEnhancedMapTiles()) {
                enhancedMapTile.draw(graphicsHandler);
            }
        }

        if (showNPCs) {
            for (NPC npc : map.getNPCs()) {
                npc.draw(graphicsHandler);
            }
        }

        if (showTriggers) {
            for (Trigger trigger : map.getTriggers()) {
                trigger.draw(graphicsHandler, new Color(255, 0, 255, 100));
            }
        }

        if (hoveredMapTile != null) {
            graphicsHandler.drawRectangle(
                    Math.round(hoveredMapTile.getX()) + 2,
                    Math.round(hoveredMapTile.getY()) + 2,
                    hoveredMapTile.getWidth() - 5,
                    hoveredMapTile.getHeight() - 5,
                    Color.YELLOW,
                    5
            );
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        graphicsHandler.setGraphics((Graphics2D) g);
        draw();
    }

    public void tileSelected(Point selectedPoint) {
        int selectedTileIndex = getSelectedTileIndex(selectedPoint);
        if (selectedTileIndex > -1) {
            MapTile oldMapTile = map.getMapTiles()[selectedTileIndex];
            int[] layerIndices = oldMapTile.getTileLayerIndices();
            MapTileBuilder newTileBuilder;


            System.out.println("SelectedTileIndex: " + selectedTileIndex);
            System.out.println("OldTile: " + oldMapTile.getTileIndex() + " OldMidTile: " + oldMapTile.getTileLayerIndices()[1] + " OldTopTile: " + oldMapTile.getTileLayerIndices()[2]);

            //If old map tile bottom layer is empty
            if (layerIndices[0] < 0) {
                newTileBuilder = map.getTileset().getTile(controlPanelHolder.getSelectedTileIndex());
                System.out.println("1");
            }
            //If selected tile is the same as current bottom layer
            else if (layerIndices[0] == controlPanelHolder.getSelectedTileIndex()) {
                newTileBuilder = map.getTileset().getTile(controlPanelHolder.getSelectedTileIndex());
                System.out.println("Clear!");
                newTileBuilder.clearUpperLayers();  // Reset tile
            }
            else {
                MapTileBuilder selectedTileBuilder = map.getTileset().getTile(controlPanelHolder.getSelectedTileIndex());
                int[] oldTileIndices = oldMapTile.getTileLayerIndices();

                //If old map tile has middle layer but selected tile has no top layer
                if (layerIndices[1] >= 0 && selectedTileBuilder.getTopLayer().isEmpty()) {
                    newTileBuilder = map.getTileset().getTile(controlPanelHolder.getSelectedTileIndex());
                    System.out.println("2");
                }
                else {       
                    newTileBuilder = new MapTileBuilder(map.getTileset().getTile(oldTileIndices[0]).getBottomLayer(), oldTileIndices[0]);
                    
                    // If selected tile has top layer
                    if (!selectedTileBuilder.getTopLayer().isEmpty()) {
                        newTileBuilder.addTopLayer(selectedTileBuilder, controlPanelHolder.getSelectedTileIndex());
                        if (layerIndices[1] >= 0) {
                            newTileBuilder.addMidLayer(map.getTileset().getTile(oldTileIndices[1]), oldTileIndices[1]);
                        }
                    }
                    else {
                        newTileBuilder.addMidLayer(selectedTileBuilder, controlPanelHolder.getSelectedTileIndex());
                    }
                    System.out.println("3");
                }
            }
            MapTile newMapTile = newTileBuilder.build(oldMapTile.getX(), oldMapTile.getY());
            System.out.println("NewTile: " + newMapTile.getTileIndex() + " NewMidTile: " + newMapTile.getTileLayerIndices()[1] + " NewTopTile: " + newMapTile.getTileLayerIndices()[2]);
            newMapTile.setMap(map);
            map.getMapTiles()[selectedTileIndex] = newMapTile;
        }
        repaint();
    }

    public void tileHovered(Point hoveredPoint) {
        this.hoveredMapTile = getHoveredTile(hoveredPoint);
        if (this.hoveredMapTile != null) {
            int hoveredIndexX = Math.round(this.hoveredMapTile.getX()) / map.getTileset().getScaledSpriteWidth();
            int hoveredIndexY = Math.round(this.hoveredMapTile.getY()) / map.getTileset().getScaledSpriteHeight();
            hoveredTileIndexLabel.setText("X: " + hoveredIndexX + ", Y: " + hoveredIndexY);
            repaint();
        }
    }

    protected MapTile getHoveredTile(Point mousePoint) {
        for (MapTile mapTile : map.getMapTiles()) {
            if (isPointInTile(mousePoint, mapTile)) {
                return mapTile;
            }
        }
        return null;
    }

    protected int getSelectedTileIndex(Point mousePoint) {
        MapTile[] mapTiles = map.getMapTiles();
        for (int i = 0; i < mapTiles.length; i++) {
            if (isPointInTile(mousePoint, mapTiles[i])) {
                return i;
            }
        }
        return -1;
    }

    protected boolean isPointInTile(Point point, MapTile tile) {
        return (point.x >= tile.getX() && point.x <= tile.getX() + tile.getWidth() &&
                point.y >= tile.getY() && point.y <= tile.getY() + tile.getHeight());
    }

    public boolean getShowNPCs() {
        return showNPCs;
    }

    public void setShowNPCs(boolean showNPCs) {
        this.showNPCs = showNPCs;
        repaint();
    }

    public boolean getShowEnhancedMapTiles() {
        return showEnhancedMapTiles;
    }

    public void setShowEnhancedMapTiles(boolean showEnhancedMapTiles) {
        this.showEnhancedMapTiles = showEnhancedMapTiles;
        repaint();
    }

    public boolean getShowTriggers() {
        return showTriggers;
    }

    public void setShowTriggers(boolean showTriggers) {
        this.showTriggers = showTriggers;
        repaint();
    }
}
