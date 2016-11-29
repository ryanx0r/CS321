/*
 * CS321 Group Project
 * By: Ryan Manecke, Sarah Pearce, Collin Mitchell
 * Matching Madness Game
 */
package models;

import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This class handles the actual data model for the game.
 * It contains an array of Tile objects.
 * It will swap the virtual location of tile objects within the array.
 * It checks for type matches of greater than 3 in a row vertically and horizontally.
 * 
 * @author Ryan Manecke, Sarah Pearce, Collin Mitchell
 */
public class PlayField
{
    //Number of types of tiles
    private final int tileVariation = 5;
    //Makes the playfield gridSize by gridSize
    private final int gridSize = 7;
    //2D array of tiles for the playing field
    private final Tile[][] tiles = new Tile[gridSize][gridSize];
    //Boolean to check if a tile is currently selected
    private boolean tileHighlighted;
    //Reference to previously clicked tile
    private Tile prevTile;
    
    //Don't allow clicks while working
    private boolean playfieldBusy;
    //If the playfield has all tile slots filled
    private boolean tilesFull;
    
    //Number of deleted tiles on move
    private int numDeleted;
    
    //Current player score
    private int score;
    
    /**
     * This function is the initializer for the playfield
     */
    public PlayField()
    {
        this.tileHighlighted = false;
        this.playfieldBusy = false;
        this.tilesFull = false;
        this.numDeleted = 0;
    }
    
    /**
     * This function returns the size of the grid
     * @return gridSize Returns an integer value for the size of the game grid.
     */
    public int getGridSize()
    {
        return this.gridSize;
    }

    /**
     * This function checks if the playfield is currently busy with processes.
     * @return playfieldBusy A boolean value is returned indicating true if the playfield 
     *                          is currently busy working.
     */
    public boolean checkIfBusy()
    {
        return this.playfieldBusy;
    }
 
    /**
     * This function checks/returns if a current tile has been selected by the user.
     * @return tileHighlighted A boolean value which is true if a tile has been selected.
     */
    public boolean isTileHighlighted()
    {
        return this.tileHighlighted;
    }
    
    /**
     * This function sets the boolean flag of whether or not a tile has been highlighted 
     * by the user.
     * @param tf A boolean value of true or false to set the private variable accordingly.
     */
    public void setTileHighlighted(boolean tf)
    {
        this.tileHighlighted = tf;
    }

    /**
     * This function returns the status of a given tile based on the x and y location.
     * @param y The y value of the tile that we want the status of.
     * @param x the x value of the tile that we want the status of.
     * @return Returns the requested Tile object.
     */
    public Tile tileStatus(int y, int x)
    {
        return this.tiles[y][x];
    }
    
    /**
     * Sets the previously highlighted tile as a marker for checking matches.
     * @param y The y value of the tile that will be marked as previous.
     * @param x The x value of the tile that will be marked as previous.
     */
    public void setPrevTile(int y, int x)
    {
        this.prevTile = this.tiles[y][x];
    }
    
    /**
     * This method returns the current player score.
     * @return Returns the current score variable value.
     */
    public int getScore()
    {
        return this.score;
    }
    
    /**
     * This method resets the score to 0 when a new game is started.
     */
    public void resetScore()
    {
        this.score = 0;
    }
    
    /**
     * This method is what contains the multiplier for the scoring system.
     * An integer is passed in and multiplied by the multiplier and added to the score.
     * @param tmp An integer that is based on the number of tiles that were removed in the last player move.
     */
    public void addToScore(int tmp)
    {
        //Score multiplier based on num deleted on player move
        this.score += tmp*125;
    }
    
    /**
     * This method returns the current number of deleted tiles.
     * @return Returns the number of deleted tiles during the last player move.
     */
    public int getNumDeleted()
    {
        return this.numDeleted;
    }

    /**
     * This method builds the tile set for the playing field.
     * It picks a random tile "type" for each tile and compares it
     * with two tiles to the left and two tiles above to make sure 
     * there are no matches when the game starts.
     */
    public void buildTiles()
    {
        int tileType = 0;
        this.tilesFull = true;
        
        //Add new tiles to the tile array
        for (int i = 0; i < gridSize; i++)
        {
            for (int j = 0; j < gridSize; j++)
            {
                //Pick a random tileVariation
                tileType = (int)(Math.random()*tileVariation);
                //Check in row for > 3 in a row or column > 3 in a row
                //Select a new type if they match, this way there are no matches when the game starts
                while (((j-1 >= 0) && (j-2 >= 0) && (tiles[i][j-1].getTileType() == tileType) && (tiles[i][j-2].getTileType() == tileType)) || ((i-1>=0) && (i-2>=0) && (tiles[i-1][j].getTileType() == tileType) && (tiles[i-2][j].getTileType() == tileType)))
                {
                    tileType = (int)(Math.random()*tileVariation);
                }
                //Give the tile coordinates based on i,j in array and type
                tiles[i][j] = new Tile(tileType, (j*100), (i*100));
            }
        }
    }
    
    /**
     * This method Swaps the places of selected tiles.
     * The prevTile private variable must be set for tiles to be swapped.
     * The method checks to which side of the prevTile that the next tile was 
     * selected (above, below, to the left, or to the right) and swaps the tiles.
     * A swap is not performed on tiles that are not adjacent.
     * @param i Y value of the newly selected tile
     * @param j X value of the newly selected tile
     */
    public void swapTiles(int i, int j)
    {
        if (this.playfieldBusy)
        {
            return;
        }
        this.numDeleted = 0;
        this.playfieldBusy = true;
        Tile tempTile;
        this.prevTile.unHighlight();
        //Move tiles around after selection
        //Check if selected tile to the right
        if ((prevTile.getX()-100 == tiles[i][j].getX()) && (prevTile.getY() == tiles[i][j].getY()))
        {
            tiles[i][j].moveRight();
            tiles[i][j+1].moveLeft();
            //Move tiles around in the array to match their new location
            tempTile = tiles[i][j];
            tiles[i][j] = tiles[i][j+1];
            tiles[i][j+1] = tempTile;
            tempTile = null;
            //Wait a moment then scan the tiles after swapping to make sure they have moved all the way
            new Timer().schedule( 
                new TimerTask() {
                @Override
                public void run() {
                    if (testscanForMatch())
                    {
                        tileHighlighted = false;
                        scanForMatch();
                    }
                    else if (numDeleted == 0)
                    {
                        Tile tempTile;
                        tiles[i][j].moveRight();
                        tiles[i][j+1].moveLeft();
                        //Move tiles around in the array to match their new location
                        tempTile = tiles[i][j];
                        tiles[i][j] = tiles[i][j+1];
                        tiles[i][j+1] = tempTile;
                        tempTile = null;
                        playfieldBusy = false;
                        tileHighlighted = false;
                    }
                }
                },400
            );
            this.prevTile = null;
        }
        //Else check if tile to the left
        else if ((prevTile.getX()+100 == tiles[i][j].getX()) && (prevTile.getY() == tiles[i][j].getY()))
        {
            tiles[i][j].moveLeft();
            prevTile.moveRight();
            //Move tiles around in the array to match their new location
            tempTile = tiles[i][j];
            tiles[i][j] = tiles[i][j-1];
            tiles[i][j-1] = tempTile;
            tempTile = null;
            //Wait a moment then scan the tiles after swapping to make sure they have moved all the way
            new Timer().schedule( 
                new TimerTask() {
                @Override
                public void run() {
                    if (testscanForMatch())
                    {
                        tileHighlighted = false;
                        scanForMatch();
                    }
                    else if (numDeleted == 0)
                    {
                        Tile tempTile;
                        tiles[i][j].moveLeft();
                        tiles[i][j-1].moveRight();
                        //Move tiles around in the array to match their new location
                        tempTile = tiles[i][j];
                        tiles[i][j] = tiles[i][j-1];
                        tiles[i][j-1] = tempTile;
                        tempTile = null;
                        playfieldBusy = false;
                        tileHighlighted = false;
                    }
                }
                },400
            );
            this.prevTile = null;
        }
        //Else check tile above was clicked after previous tile
        else if ((prevTile.getX() == tiles[i][j].getX()) && (prevTile.getY()-100 == tiles[i][j].getY()))
        {
            tiles[i][j].moveDown();
            prevTile.moveUp();
            //Move tiles around in the array to match their new location
            tempTile = tiles[i][j];
            tiles[i][j] = tiles[i+1][j];
            tiles[i+1][j] = tempTile;
            tempTile = null;
            //Wait a moment then scan the tiles after swapping to make sure they have moved all the way
            new Timer().schedule( 
                new TimerTask() {
                @Override
                public void run() {
                    if (testscanForMatch())
                    {
                        tileHighlighted = false;
                        scanForMatch();
                    }
                    else if (numDeleted == 0)
                    {
                        Tile tempTile;
                        tiles[i][j].moveDown();
                        tiles[i+1][j].moveUp();
                        //Move tiles around in the array to match their new location
                        tempTile = tiles[i][j];
                        tiles[i][j] = tiles[i+1][j];
                        tiles[i+1][j] = tempTile;
                        tempTile = null;
                        playfieldBusy = false;
                        tileHighlighted = false;
                    }
                }
                },400
            );
            this.prevTile = null;
        }
        //Else check tile below was clicked after previous tile
        else if ((prevTile.getX() == tiles[i][j].getX()) && (prevTile.getY()+100 == tiles[i][j].getY()))
        {
            this.tiles[i][j].moveUp();
            this.prevTile.moveDown();
            //Move tiles around in the array to match their new location
            tempTile = this.tiles[i][j];
            this.tiles[i][j] = this.tiles[i-1][j];
            this.tiles[i-1][j] = tempTile;
            tempTile = null;
            //Wait a moment then scan the tiles after swapping to make sure they have moved all the way
            new Timer().schedule( 
                new TimerTask() {
                @Override
                public void run() {
                    if (testscanForMatch())
                    {
                        tileHighlighted = false;
                        scanForMatch();
                    }
                    else if (numDeleted == 0)
                    {
                        Tile tempTile;
                        tiles[i][j].moveUp();
                        tiles[i-1][j].moveDown();
                        //Move tiles around in the array to match their new location
                        tempTile = tiles[i][j];
                        tiles[i][j] = tiles[i-1][j];
                        tiles[i-1][j] = tempTile;
                        tempTile = null;
                        playfieldBusy = false;
                        tileHighlighted = false;
                    }
                }
                },400
            );
            this.prevTile = null;
        }
        //Else the tiles aren't next to eachother
        else
        {
            this.prevTile = null;
            this.playfieldBusy = false;
        }
    }
      
    //Check if tiles are in a row/col greater than 3 matching
    //Run after moving tiles

    /**
     * This method Checks if tiles are in a row/col greater than 3 matching.
     * This is run after the player has made a successful swap.
     * If it finds any matches it marks the tiles for deletion.
     */
    public void scanForMatch()
    {
        this.playfieldBusy = true;
        boolean matchFound = false;
        for (int i = 0; i < gridSize; i++)
        {
            for (int j = 0; j < gridSize; j++)
            {
                //This part checks the rows for matches
                //matchCount counts the number matching the currently being checked tile
                int matchCount = 1;
                while ((matchCount+j<gridSize) && (tiles[i][j] != null) && (tiles[i][j+matchCount] != null) &&(tiles[i][j].getTileType() == tiles [i][j+matchCount].getTileType()))
                {
                    matchCount++;
                }
                //if 3 or more found in row the same
                if (matchCount > 2)
                {
                    matchFound = true;
                    matchCount--;
                    //Loop back from where the last matching tile was found and mark for deletion
                    while (matchCount >= 0)
                    {
                        tiles[i][j+matchCount].setStatus();
                        tiles[i][j+matchCount].highlight();
                        matchCount--;
                    }
                }
                //This part checks the columns for matches (just swap the i/j around
                matchCount = 1;
                while ((matchCount+j<gridSize) && (tiles[j][i] != null) && (tiles[j+matchCount][i] != null) &&(tiles[j][i].getTileType() == tiles [j+matchCount][i].getTileType()))
                {
                    matchCount++;
                }
                //if 3 or more found in row the same
                if (matchCount > 2)
                {
                    matchFound = true;
                    matchCount--;
                    //Loop back from where the last matching tile was found and mark for deletion
                    while (matchCount >= 0)
                    {
                        tiles[j+matchCount][i].setStatus();
                        tiles[j+matchCount][i].highlight();
                        matchCount--;
                    }
                }
            }
        }
                
        if (matchFound)
        {
            //Wait a moment then adjust the tiles after deletions
            try {Thread.sleep(150);} catch (Exception e) {}
            deleteMarkedTiles();
            try {Thread.sleep(150);} catch (Exception e) {}
            adjustTiles();
        }
        //playfieldBusy = false;
    }

    /**
     * This method is a test scan, same as other scanForMatch but doesn't make modifications.
     * Used to move tiles back after swap if no matches were found.
     * @return
     */
    public boolean testscanForMatch()
    {
        this.playfieldBusy = true;
        for (int i = 0; i < gridSize; i++)
        {
            for (int j = 0; j < gridSize; j++)
            {
                //This part checks the rows for matches
                //matchCount counts the number matching the currently being checked tile
                int matchCount = 1;
                while ((matchCount+j<gridSize) && (tiles[i][j] != null) && (tiles[i][j+matchCount] != null) &&(tiles[i][j].getTileType() == tiles [i][j+matchCount].getTileType()))
                {
                    matchCount++;
                }
                //if 3 or more found in row the same
                if (matchCount > 2)
                {
                    return true;
                }
                //This part checks the columns for matches (just swap the i/j around
                matchCount = 1;
                while ((matchCount+j<gridSize) && (tiles[j][i] != null) && (tiles[j+matchCount][i] != null) &&(tiles[j][i].getTileType() == tiles [j+matchCount][i].getTileType()))
                {
                    matchCount++;
                }
                //if 3 or more found in row the same
                if (matchCount > 2)
                {
                    return true;
                }
            }
        }
        this.playfieldBusy = false;
        return false;
    }

    /**
     * This method Removes the specified tile from the grid.
     * @param y The Y value of the tile to remove.
     * @param x The X value of the tile to remove.
     */
    public void deleteTile(int y, int x)
    {
        tiles[y][x] = null;
    }

    /**
     * This method Removes tiles that are marked for deletion.
     * The tiles were marked by the scanForMatch method if a match was found.
     */
    public void deleteMarkedTiles()
    {
        //Loop through and set tiles marked for deletion to null
        for (int y = 0; y < gridSize; y++)
        {
            for (int x = 0; x < gridSize; x++)
            {
                //Remove each tile marked for removal
                if ((tiles[y][x] != null) && tiles[y][x].getStatus())
                {
                    this.tilesFull = false;
                    this.numDeleted++;
                    deleteTile(y,x);
                }
            }
        }
        addToScore(this.numDeleted);
    }

    /**
     * This method moves tiles down the playfield to fill in voids 
     * left by deleted tiles.  It starts at the bottom and works its way 
     * up to figure out how many new tiles need to be added and calls 
     * addTiles.
     */
    public void adjustTiles()
    {
        this.playfieldBusy = true;
        int tilesMoved = 0;
        int maxMovement = 1;
        for (int x = gridSize-1; x >= 0; x--)
        {
            for (int y = gridSize-1; y >= 0; y--)
            {
                //see if the tiles above are null
                //k is an offset
                int k = 1;
                while ((y-k >=0 ) && (tiles[y][x] == null) && (tiles[y-k][x] == null))
                {
                    k++;
                    if (k > maxMovement)
                    {
                        maxMovement = k;
                    }
                }
                if ((y-k >=0 ) && (k > 0) && (tiles[y-k][x] != null) && (tiles[y][x] == null))
                {
                    tiles[y-k][x].moveDown(k);
                    tiles[y][x] = tiles[y-k][x];
                    deleteTile(y-k,x);
                    tilesMoved++;
                }
            }
        }
        //Add new tiles if needed
        try {Thread.sleep(150);} catch (Exception e) {}
        addTiles();
        if (this.tilesFull)
        {
            //try {Thread.sleep(maxMovement*200);} catch (Exception e) {}
            scanForMatch();
        }
        this.playfieldBusy = false;
    }
    
    /**
     * This method Adds new tiles for ones that were deleted with matches.
     * It only adds tiles to the empty spots in the top row then calls 
     * adjustTiles to move them down the playfield.
     */
    public void addTiles()
    {
        this.playfieldBusy = true;
        //While there are any empty tile slots
        while (Arrays.asList(tiles[0]).contains(null))
        {
            this.tilesFull = false;
            //Add tiles to any empty spots at the top
            for (int j = 0; j < gridSize; j++)
            {
                //Add tiles to the first row
                if (tiles[0][j] == null)
                {
                    int tileType = (int)(Math.random()*this.tileVariation);
                    tiles[0][j] = new Tile(tileType, (j*100), 0);
                }
            }
            //Push the new tiles down
            adjustTiles();
        }
        if (!Arrays.asList(tiles[0]).contains(null))
        {
            this.tilesFull = true;
        }
        //playfieldBusy = false;
    }

    /**
     * This method erases the playfield and clears out variables 
     * at the end of a game.
     */
    public void clearPlayfield()
    {
        this.prevTile = null;
        //Loop through and null all tiles
        for (int i = 0; i < gridSize; i++)
        {
            for (int j = 0; j < gridSize; j++)
            {
                tiles[i][j] = null;
            }
        }
        this.tilesFull = false;
        this.tileHighlighted = false;
        this.playfieldBusy = false;
        this.numDeleted = 0;
        this.score = 0;
    }
}
