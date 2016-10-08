/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;

/**
 * Sub class of tile view.  This type draws ellipses as the tile type.
 * @author Ryan
 */
public class EllipseView extends TileView
{
    //Ellipse shape for this tile
    private Ellipse2D.Double theTile;

    //Variables for r/g/b colors for this tile type
    private int colr;
    private int colg;
    private int colb;
    private int alpha;
    
    //Constructor
    //pass in rgb color values, alpha value and x/y
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
    
    //Override paintcomponent to draw the tile
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // Set paint color
        g2d.setPaint(new Color(colr,colg,colb,alpha));
        // Fill the figure
        g2d.draw(theTile);
        g2d.fill(theTile);
    }
}
