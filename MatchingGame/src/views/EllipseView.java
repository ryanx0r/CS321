/*
 * CS321 Group Project
 * By: Ryan Manecke, Sarah Pearce, Collin Mitchell
 * Matching Madness Game
 */
package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;

/**
 * Sub class of tile view. 
 * This type draws ellipses as the tile type.
 * @author Ryan Manecke, Sarah Pearce, Collin Mitchell
 */
public class EllipseView extends TileView
{
    //Ellipse shape for this tile
    private Ellipse2D.Double theTile;

    //Variables for r/g/b colors for this tile type
    private final int colr;
    private final int colg;
    private final int colb;
    private final int alpha;
    
    //Constructor
    //pass in rgb color values, alpha value and x/y
    /**
     * This method is the constructor for the ellipse tile type.
     * @param r Integer value for the red color.
     * @param g Integer value for the green color.
     * @param b Integer value for the blue color.
     * @param a Integer value for the alpha value.
     * @param x Integer value of the current X location of the tile.
     * @param y Integer value of the current Y location of the tile.
     * @param tileSize Integer value for the size of the tile.
     */
    public EllipseView(int r, int g, int b, int a, int x, int y, int tileSize)
    {
        colr = r;
        colg = g;
        colb = b;
        alpha = a;
        this.x = x;
        this.y = y;
        this.setPreferredSize(new Dimension(100,100));
        this.setBackground(Color.white);
        theTile = new Ellipse2D.Double(this.x, this.y, tileSize, tileSize);
        this.setOpaque(true);
    }
    
    /**
     * Override paintComponent to draw the tile to the screen.
     * @param g A graphics component passed in by Java swing when paintComponent or repaint is called.
     */
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        //convert the graphics object to graphics2d
        Graphics2D g2d = (Graphics2D) g;
        //Set antialiasing settings
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // Set paint color
        g2d.setPaint(new Color(colr,colg,colb,alpha));
        //Draw and fill the shape
        g2d.draw(theTile);
        g2d.fill(theTile);
    }
}
