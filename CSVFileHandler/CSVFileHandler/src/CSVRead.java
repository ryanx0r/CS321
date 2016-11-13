
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * A class for reading CSV files
 * @author Ryan Manecke, Sarah Pearce, Collin Mitchell
 */
public class CSVRead {
    
    private final File data;
    private String csvSplit = "";
    private String line = "";
    private BufferedReader br = null;
    private User usertemp = null;
    
    /**
     * Constructs a CSVRead class that can read in a CSV file and store the user
     * information
     */
    public CSVRead()
    {
        data = new File(System.getProperty("user.dir") + "\\data.txt");
        csvSplit = ",";
        line = "";
        br = null;
        usertemp = new User();
    }
    
    /**
     * Open and read the CSV file and store each user in an ArrayList
     * @return an ArrayList containing all of the users read in from the file
     */
    public ArrayList<User> readFile()
    {
        ArrayList<User> users = new ArrayList();
        
        try{
            if(!data.exists())
            {
                data.createNewFile();
                /*
                if(data.createNewFile())
                    System.out.println("File created!!!");
                else
                    System.out.println("NOPE");
                */
            }
            /*
            else
                System.out.println("File already exists");
            */
        }catch(Exception e){};
        
        try{
            br = new BufferedReader(new FileReader(data));
            while((line = br.readLine()) != null)
            {
                if("".equals(line))
                {
                }
                else{
                    //System.out.println(line);
                    String[] userinfo = line.split(csvSplit);
                    usertemp = new User();
                    usertemp.setName(userinfo[0]);
                    //System.out.println(usertemp.getName());
                    usertemp.setScore(Integer.parseInt(userinfo[1]));
                    //System.out.println(usertemp.getScore());
                    usertemp.setPref(Integer.parseInt(userinfo[2]));
                    //System.out.println(usertemp.getPref());
                    users.add(usertemp);
                }
            }
            if(br != null)
                br.close();
            
        }catch(IOException e){}
        
        return users;
    }
    
}
