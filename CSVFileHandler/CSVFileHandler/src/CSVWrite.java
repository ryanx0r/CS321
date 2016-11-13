
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * A class for writing user information to a CSV file
 * @author Ryan Manecke, Sarah Pearce, Collin Mitchell
 */
public class CSVWrite {
    File data = null;
    private ArrayList<User> userlist;
    
    /**
     * Constructs a CSVWrite file that can delete, create, and write to a CSV file
     */
    public CSVWrite()
    {
        data = new File(System.getProperty("user.dir") + "\\data.txt");
        userlist = new ArrayList();
    }
    
    /**
     * Write the user data from an ArrayList to a CSV file
     * @param u the ArrayList of users that need to be written to the data file
     */
    public void writeFile(ArrayList<User> u)
    {
        userlist = u;

        BufferedWriter out = null;
        
        try{
            data.delete();
            data.createNewFile();
            
            out = new BufferedWriter(new FileWriter(data, false));
            
            
            for(User temp : userlist)
            {
                
                out.write(temp.getName() + "," + temp.getScore() + "," + temp.getPref() + System.lineSeparator());
            }
            
            out.close();
            
        }catch(Exception e){};
    }
            
}
