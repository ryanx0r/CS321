/*
 * CS321 Group Project
 * By: Ryan Manecke, Sarah Pearce, Collin Mitchell
 * Matching Madness Game
 */
package views;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.AlphaComposite;

/**
 * This is a subclass of TileView.
 * This class draws images as its type of tile.
 * @author Ryan Manecke, Sarah Pearce, Collin Mitchell
 */
public class ImageView extends TileView
{
    //Image settings for this tile type
    private Image theImage;
    private int alphaType;
    private float alpha;
    private AlphaComposite composite;
    
    /**
     * This is the default constructor for the ImageView tile type.
     * It simply sets the values accordingly.
     * 
     * @param alpha The alpha value for the tile to give highlight illusion.
     * @param x The current X location of the tile.
     * @param y The current Y location of the tile.
     * @param tileSize The default tile size of the tile.
     * @param img The image object that will be drawn for this tile.
     */
    public ImageView(int alpha, int x, int y, int tileSize, Image img)
    {
        this.x = x;
        this.y = y;
        
        //Convert alpha to decimal alpha for using AlphaComposite
        if (alpha == 127)
        {
            this.alpha = 0.6f;
        }
        else if (alpha == 255)
        {
            this.alpha = 1f;
        }
        this.tileSize = tileSize;
        this.alphaType = AlphaComposite.SRC_OVER; 
        //Set up the image object
        this.theImage = img;
    }
    
    /**
     * This function does the actual drawing of the object to the screen.
     * @param g A graphics component passed in by Java swing when paintComponent or repaint is called.
     */
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        //Convert the graphics object to graphics2d
        Graphics2D g2d = (Graphics2D) g;
        //Set antialiasing settings
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //Set the alpha appropriately for the image
        this.composite = AlphaComposite.getInstance(this.alphaType, this.alpha);
        g2d.setComposite(this.composite);
        //Draw the image to the screen
        g2d.drawImage(this.theImage, this.x, this.y, this.tileSize, this.tileSize, null);
    }
}
