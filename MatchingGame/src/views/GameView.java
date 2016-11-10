/*
 * CS321 Group Project
 * By: Ryan Manecke, Sarah Pearce, Collin Mitchell
 * Matching Madness Game
 */
package views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;
import java.util.ArrayList;
import javax.swing.BorderFactory;

/**
 * This class handles the actual drawing of the game panel.
 * It contains an array list of TileView objects that represent actual tiles on the screen.
 * It is a subclass of JPanel.
 * @author Ryan Manecke, Sarah Pearce, Collin Mitchell
 */
public class GameView extends JPanel
{
    //Create an arraylist of tileviews
    private ArrayList<TileView> tiles;
    
    //Default tile size
    private int tileSize = 100;
    
    //How many different images or colors to use
    private int tileVariation = 5;
    
    //Tile type defaulted to 0
    private int tileType = 0;
    
    //Arrays of colors and image objects, depending on tile type selected
    private int[][] colors;
    private Image[] images;
    
    private Image bgImage;
    
    //Constructor
    /**
     * This is the default constructor the the GameView.
     * It sets the basic JPanel properties.
     * It also sets up randomized colors.
     */
    public GameView()
    {        
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setBackground(Color.black);
        tiles = new ArrayList();
        //Set up colors for tiles that use them
        colors = new int[tileVariation][3];
        //Set up images for tiles that use them
        images = new Image[tileVariation];
        setColors();
    }
    
    /**
     * This method sets the tile type that will be used on the game.
     * Type 0 (not passed in) is for the ellipse view type.
     * Type 1..n use different images for the image view type.
     * @param type An integer passed in that will decide what image style to use.
     */
    public void setTileType(int type)
    {
        this.tileType = type;
        //If the tile type was 1
        if (type == 1)
        {
            setBg();
            setImages(1);
        }
        //If the tile type was 2
        else if (type == 2)
        {
            setImages(2);
        }
    }
    
    /**
     * This method adds a tile to the GameView panel.
     * It will be passed an array of data that comes from the PlayField model.
     * @param tileData An array of data pulled from the PlayField model containing location and type information for the tile.
     */
    public void addTile(int[] tileData)
    {
        //Ellipse tile
        if (tileType == 0)
        {
            //Create a new tile based on the array passed in
            //r,g,b,alpha,x,y,tilesize
            tiles.add(new EllipseView(colors[tileData[0]][0],colors[tileData[0]][1],colors[tileData[0]][2],tileData[1],tileData[2],tileData[3], tileSize));
        }
        //ImageView tile types
        else if ((tileType == 1) || (tileType == 2))
        {
            tiles.add(new ImageView(tileData[1], tileData[2], tileData[3], tileSize, images[tileData[0]]));
        }
    }
    
    /**
     * This method removes all of the tiles from the view by clearing them from the ArrayList.
     */
    public void clearGame()
    {
        tiles.clear();
    }
    
    /**
     * This method creates an array of RGB color values from 0 to 255.
     * This array is only used for the EllipseView tile type, or other non-image tile types.
     */
    public void setColors()
    {
        //Fill colors array with randomly generated rgb values
        for (int i = 0; i < tileVariation; i++)
        {
                colors[i][0] = (int)(Math.random()*255);
                colors[i][1] = (int)(Math.random()*255);
                colors[i][2] = (int)(Math.random()*255);
        }
    }
    
    /**
     * This method sets the background image for the specific tile type selected.
     */
    public void setBg()
    {
        bgImage = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/bg.jpg"));
    }
    
    /**
     * This method resets the background image at the end of a game.
     */
    public void resetBg()
    {
        bgImage = null;
    }
    
    //
    /**
     * This method creates a listing of images for the specified tile type.
     * It loads them all into the images array.
     * @param type An integer argument passed in that will be used to load the proper images.
     */
    public void setImages(int type)
    {
        //Planets images
        if (type == 1)
        {
            for (int i = 0; i < tileVariation; i++)
            {
                images[i] = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/planet" + i + ".png"));
            }
        }
        //Fruit images
        else if (type == 2)
        {
            for (int i = 0; i < tileVariation; i++)
            {
                images[i] = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/fruit" + i + ".png"));
            }
        }
    }
    
    /**
     * This function does the actual drawing of the panel to the screen.
     * @param g A graphics component passed in by Java swing when paintComponent or repaint is called.
     */
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        //Paint the background image if required
        if (tileType == 1)
        {
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(bgImage, 0, 0, null);
        }
        
        //Paint each tile
        //Create a temporary arraylist to help prevent exceptions
        ArrayList<TileView> toPaint = new ArrayList();
        for (TileView temp : tiles) 
        {
            toPaint.add(temp);
        }
        for (TileView tile : toPaint)
        {
            tile.paintComponent(g);
        }
    }
}
