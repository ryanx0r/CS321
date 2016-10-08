/*
 * Written by Ryan Manecke
 * For UAH CS 321
 */
package models;

import java.util.Timer;
import java.util.TimerTask;

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
        Timer tempTime;
        tempTime = new Timer();
        tempTime.scheduleAtFixedRate(new TimerTask() {
            int i = 10;
            @Override
            public void run() {
                currY = currY-10;
                i--;
                if (i < 1)
                {
                    tempTime.cancel();
                }
            }
        }, 0, 10);
    }
    
    //Move the tile down one segment
    //@multiple Used to move down more than one space if needed to fill voids
    public void moveDown(int multiple)
    {
        unHighlight();
        Timer tempTime;
        tempTime = new Timer();
        tempTime.scheduleAtFixedRate(new TimerTask() {
            int i = multiple*10;
            @Override
            public void run() {
                currY = currY+10;
                i--;
                if (i < 1)
                {
                    tempTime.cancel();
                }
            }
        }, 0, 10);
    }
    
    //Without multiple
    public void moveDown()
    {
        unHighlight();
        Timer tempTime;
        tempTime = new Timer();
        tempTime.scheduleAtFixedRate(new TimerTask() {
            int i = 10;
            @Override
            public void run() {
                currY = currY+10;
                i--;
                if (i < 1)
                {
                    tempTime.cancel();
                }
            }
        }, 0, 10);
    }
    
    //Move the tile left one segment
    public void moveLeft()
    {
        unHighlight();
        Timer tempTime;
        tempTime = new Timer();
        tempTime.scheduleAtFixedRate(new TimerTask() {
            int i = 10;
            @Override
            public void run() {
                currX = currX-10;
                i--;
                if (i < 1)
                {
                    tempTime.cancel();
                }
            }
        }, 0, 10);
    }
    
    //Move the tile right one segment
    public void moveRight()
    {
        unHighlight();
        Timer tempTime;
        tempTime = new Timer();
        tempTime.scheduleAtFixedRate(new TimerTask() {
            int i = 10;
            @Override
            public void run() {
                currX = currX+10;
                i--;
                if (i < 1)
                {
                    tempTime.cancel();
                }
            }
        }, 0, 10);
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
