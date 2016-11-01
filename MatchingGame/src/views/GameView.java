/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * @author Ryan
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
    
    private int[][] colors;
    private Image[] images;
    
    private Image bgImage;
    
    //Constructor
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
    
    //Set tile type (ellipse = 0, image = 1)
    public void setTileType(int type)
    {
        this.tileType = type;
        if (type == 1)
        {
            setBg();
            setImages(1);
        }
        else if (type == 2)
        {
            setImages(2);
        }
    }
    
    //Add a tile
    public void addTile(int[] tileData)
    {
        //ellipse tile
        if (tileType == 0)
        {
            //Create a new tile based on the array passed in
            //r,g,b,alpha,x,y,tilesize
            tiles.add(new EllipseView(colors[tileData[0]][0],colors[tileData[0]][1],colors[tileData[0]][2],tileData[1],tileData[2],tileData[3], tileSize));
        }
        else if ((tileType == 1) || (tileType == 2))
        {
            tiles.add(new ImageView(tileData[1], tileData[2], tileData[3], tileSize, images[tileData[0]]));
        }
    }
    
    //Remove the tiles
    public void clearGame()
    {
        tiles.clear();
    }
    
    //Create listing of colors if colored tiles are used
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
    
    //Set up background image for planets tile set
    public void setBg()
    {
        bgImage = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/bg.jpg"));
    }
    
    //reset the bg image to null
    public void resetBg()
    {
        bgImage = null;
    }
    
    //Create a listing of images for planets tile set
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
        //Create a temporary arraylist to prevent exceptions
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
