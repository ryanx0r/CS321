package models;

/*
 * CS321 Group Project
 * By: Ryan Manecke, Sarah Pearce, Collin Mitchell
 * Matching Madness Game
 */

/**
 * A class for creating user profiles and storing their data
 * @author Ryan Manecke, Sarah Pearce, Collin Mitchell
 */
public class User {
    
    private String name;
    private int score;
    private int pref;
    
    /**
     * Constructs a user object that can store user name, score, and preference
     */
    public void User()
    {
        name = "";
        score = 0;
        pref = 0;
    }
    
    /**
     * Sets the user name
     * @param n the name of the user
     */
    public void setName(String n)
    {
        this.name = n;
    }
    /**
     * Return the user's name
     * @return the name of the user
     */
    public String getName()
    {
        return this.name;
    }
    
    /**
     * Sets the user's score
     * @param s the user's score
     */
    public void setScore(int s)
    {
        this.score = s;
    }
    /**
     * Return the user's score
     * @return the user's score
     */
    public int getScore()
    {
        return this.score;
    }
    
    /**
     * Sets the user's tile preference
     * @param p the user's tile preference
     */
    public void setPref(int p)
    {
        this.pref = p;
    }
    /**
     * Gets the user's tile preference
     * @return the user's tile preference
     */
    public int getPref()
    {
        return this.pref;
    }
    

}
