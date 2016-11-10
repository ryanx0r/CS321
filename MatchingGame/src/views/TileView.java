/*
 * CS321 Group Project
 * By: Ryan Manecke, Sarah Pearce, Collin Mitchell
 * Matching Madness Game
 */
package views;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JComponent;
import java.awt.Graphics;

/**
 * This class is sub classed to the specific tile view types.
 * It is an abstract class so that GameView can use one ArrayList for any of the sub-classes.
 * @author Ryan Manecke, Sarah Pearce, Collin Mitchell
 */
abstract class TileView extends JComponent
{
    //X and Y variables for any tile type
    protected int x = 0;
    protected int y = 0;
    //Default tile size for any tile type
    protected int tileSize = 100;
    
    /**
     * The default constructor for the view.
     */
    public TileView()
    {
        this.setPreferredSize(new Dimension(100,100));
        this.setBackground(Color.white);
        this.setOpaque(true);
    }
    
    /**
     * This function does the actual drawing of the object to the screen.
     * @param g A graphics component passed in by Java swing when paintComponent or repaint is called.
     */
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        //doesn't need to do anything, redefined by sub classes
    }
}
