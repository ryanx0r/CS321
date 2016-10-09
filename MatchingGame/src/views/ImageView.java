/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.AlphaComposite;

/**
 *
 * @author Ryan
 */
public class ImageView extends TileView
{
    //Image for this tile type
    private Image theImage;
    private int alphaType;
    private float alpha;
    private AlphaComposite composite;
    
    public ImageView(int alpha, int x, int y, int tileSize, Image img)
    {
        this.x = x;
        this.y = y;
        //Convert alpha to decimal alpha
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
    
    //Override paintcomponent to draw the tile
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //Set the alpha appropriately
        this.composite = AlphaComposite.getInstance(this.alphaType, this.alpha);
        g2d.setComposite(this.composite);
        //Draw the image
        g2d.drawImage(this.theImage, this.x, this.y, this.tileSize, this.tileSize, null);
    }
}
