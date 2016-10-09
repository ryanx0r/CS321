/*
 * Written by Ryan Manecke
 * For UAH CS 321
 */
package models;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Handles the data model for each tile contained on the play field.
 * @author Ryanx0r
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
    
    //Default constructor to set x y and other values
    public Tile (int type, int x, int y)
    {
        this.currX = x;
        this.currY = y;
        this.alpha = 127;
        this.tileType = type;
        this.flaggedForDelete = false;
        this.animDelay = 30;
    }
    
    //See if this tile should be removed
    public boolean getStatus()
    {
        return this.flaggedForDelete;
    }
    
    //Set status for removal
    public void setStatus()
    {
        this.flaggedForDelete = true;
    }
    
    //Get the index of the tile type
    public int getTileType()
    {
        return this.tileType;
    }
    
    //Get the x value
    public int getX()
    {
        return this.currX;
    }
    
    //Get the y value
    public int getY()
    {
        return this.currY;
    }
    
    //Set the x value
    public void setX(int x)
    {
        this.currX = x;
    }
    
    //Set the y value
    public void setY(int y)
    {
        this.currY = y;
    }
    
    //Return the current alpha value for drawing
    public int getAlpha()
    {
        return this.alpha;
    }
    
    //Move the tile up one segment
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
    
    //Move the tile down one segment
    //@multiple Used to move down more than one space if needed to fill voids
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
    //Without multiple
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
    
    //Move the tile left one segment
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
    
    //Move the tile right one segment
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
    
    //Highlight the clicked tile
    public void highlight()
    {
        this.alpha = 255;
    }
    
    //Get rid of the highlight on the tile
    public void unHighlight()
    {
        this.alpha = 127;
    }
    
    //Contains method to check if a mouse click is on the tile
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
