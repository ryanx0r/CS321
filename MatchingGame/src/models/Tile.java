/*
 * CS321 Group Project
 * By: Ryan Manecke, Sarah Pearce, Collin Mitchell
 * Matching Madness Game
 */
package models;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ScheduledExecutorService;

/**
 * This class handles the data model for each tile contained on the play field.
 * Handles the virtual movement of itself.
 * @author Ryan Manecke, Sarah Pearce, Collin Mitchell
 */
public class Tile
{
    //Class for tiles on the playing field
    
    //X and Y variables
    private int currX = 0;
    private int currY = 0;

    private int alpha;
    
    //Delay time for the movements
    private int animDelay;
    
    //Index of the tile type
    private int tileType;
    
    //Boolean that shows whether or not this tile will be removed
    private boolean flaggedForDelete;

    /**
     * This method is the Default constructor to set x y and other values
     * @param type An integer specifying the type of this tile (used for checking for matches).
     * @param x The X location of this tile
     * @param y The Y location of this tile
     */
    public Tile (int type, int x, int y)
    {
        this.currX = x;
        this.currY = y;
        this.alpha = 127;
        this.tileType = type;
        this.flaggedForDelete = false;
        this.animDelay = 30;
    }

    /**
     * This method returns the status of the tile.
     * This allows the playfield to determine if this tile was 
     * flagged for removal.
     * @return Returns the status of this tile (whether or not the tile needs to be deleted)
     */
    public boolean getStatus()
    {
        return this.flaggedForDelete;
    }

    /**
     * This method Sets status of the tile for removal.
     * It will be deleted on the next deletion move.
     */
    public void setStatus()
    {
        this.flaggedForDelete = true;
    }

    /**
     * This method returns the type of this tile.
     * This is used to determine if a match is made 
     * with surrounding tiles.
     * @return Returns the type of this tile.
     */
    public int getTileType()
    {
        return this.tileType;
    }

    /**
     * This method returns the X location of the tile.
     * @return Returns the X location of the tile
     */
    public int getX()
    {
        return this.currX;
    }

    /** 
     * This method returns the Y location of the tile.
     * @return Returns the Y location of the tile.
     */
    public int getY()
    {
        return this.currY;
    }

    /**
     * This method sets the X location of the tile
     * @param x The value that X will be set to.
     */
    public void setX(int x)
    {
        this.currX = x;
    }

    /**
     * This method sets the Y location of the tile.
     * @param y The value that Y will be set to.
     */
    public void setY(int y)
    {
        this.currY = y;
    }

    /**
     * This method returns the current alpha value for drawing.
     * This value is used to make the tile appear to highlight 
     * when it is selected by the player.
     * @return Returns the alpha value of the tile.
     */
    public int getAlpha()
    {
        return this.alpha;
    }

    /**
     * This method creates a thread to animate movement of the tile up one segment.
     */
    public void moveUp()
    {
        unHighlight();
        //Create a scheduled thread
        ScheduledExecutorService schedule = Executors.newScheduledThreadPool(1);
        //Create a new runnable to run in the thread that moves the tile
        Runnable movement = new Runnable() {
            int i = 4;
            @Override
            public void run() {
                currY = currY-25;
                i--;
                if (i < 1)
                {
                    schedule.shutdownNow();
                }
            }
        };
        //Start the thread with fixed delay on a schedule
        schedule.scheduleWithFixedDelay(movement, 0, this.animDelay, TimeUnit.MILLISECONDS);
    }
    
    /**
     * This method creates a thread to animate movement of the tile down one segment.
     * @param multiple Multiplier used to move down more than one space if needed to fill voids.
     */
    public void moveDown(int multiple)
    {
        unHighlight();
        //Create a scheduled thread
        ScheduledExecutorService schedule = Executors.newScheduledThreadPool(1);
        //Create a new runnable to run in the thread that moves the tile
        Runnable movement = new Runnable() {
            int i = 4*multiple;
            @Override
            public void run() {
                currY = currY+25;
                i--;
                if (i < 1)
                {
                    schedule.shutdownNow();
                }
            }
        };
        //Start the thread with fixed delay on a schedule
        schedule.scheduleWithFixedDelay(movement, 0, this.animDelay, TimeUnit.MILLISECONDS);
    }

    /**
     * This method creates a thread to animate movement of the tile down one segment.
     */
    public void moveDown()
    {
        unHighlight();
        //Create a scheduled thread
        ScheduledExecutorService schedule = Executors.newScheduledThreadPool(1);
        //Create a new runnable to run in the thread that moves the tile
        Runnable movement = new Runnable() {
            int i = 4;
            @Override
            public void run() {
                currY = currY+25;
                i--;
                if (i < 1)
                {
                    schedule.shutdownNow();
                }
            }
        };
        //Start the thread with fixed delay on a schedule
        schedule.scheduleWithFixedDelay(movement, 0, this.animDelay, TimeUnit.MILLISECONDS);
    }

    /**
     * This method creates a thread to animate movement of the tile left one segment.
     */
    public void moveLeft()
    {
        unHighlight();
        //Create a scheduled thread
        ScheduledExecutorService schedule = Executors.newScheduledThreadPool(1);
        //Create a new runnable to run in the thread that moves the tile
        Runnable movement = new Runnable() {
            int i = 4;
            @Override
            public void run() {
                currX = currX-25;
                i--;
                if (i < 1)
                {
                    schedule.shutdownNow();
                }
            }
        };
        //Start the thread with fixed delay on a schedule
        schedule.scheduleWithFixedDelay(movement, 0, this.animDelay, TimeUnit.MILLISECONDS);
    }

    /**
     * This method creates a thread to animate movement of the tile right one segment.
     */
    public void moveRight()
    {
        unHighlight();
        //Create a scheduled thread
        ScheduledExecutorService schedule = Executors.newScheduledThreadPool(1);
        //Create a new runnable to run in the thread that moves the tile
        Runnable movement = new Runnable() {
            int i = 4;
            @Override
            public void run() {
                currX = currX+25;
                i--;
                if (i < 1)
                {
                    schedule.shutdownNow();
                }
            }
        };
        //Start the thread with fixed delay on a schedule
        schedule.scheduleWithFixedDelay(movement, 0, this.animDelay, TimeUnit.MILLISECONDS);
    }

    /**
     * This method Highlights the clicked tile by changing the alpha value.
     */
    public void highlight()
    {
        this.alpha = 255;
    }

    /**
     * This method gets rid of the highlight on the tile by lowering the alpha value.
     */
    public void unHighlight()
    {
        this.alpha = 127;
    }

    /**
     * This method is for checking to see if a mouse click from the player 
     * occurred within this tile.
     * @param x The X value of where the mouse click occurred.
     * @param y The Y value of where the mouse click occurred.
     * @return Returns true if click was on the tile, false if not.
     */
    public boolean contains(int x, int y)
    {
        //See if the click was within the bounds
        if ((x <= (this.currX+100)) && (y <= (this.currY+100)) && (x >= this.currX) && (y >= this.currY))
        {
            return true;
        }
        else
        {
            return false;
        }        
    }
}
