/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JComponent;
import java.awt.Graphics;

/**
 * This class is sub classed to the specific tile view types
 * @author Ryan
 */
public class TileView extends JComponent
{
    //X and Y variables
    protected int x = 0;
    protected int y = 0;
    //Default tile size
    protected int tileSize = 100;
    
    public TileView()
    {
        this.setPreferredSize(new Dimension(100,100));
        this.setBackground(Color.white);
        this.setOpaque(true);
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        //doesn't need to do anything
    }
}
