/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.willi.onionchecker;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.willi.onionchecker.util.OnionList;

/**
 *
 * @author phamm
 */
public class ListReader
{
    private String path;
    private ArrayList<String> onionlist = new ArrayList<String>();
    
    public ListReader(String path)
    {
        this.path = path;
    }
    
    public void readList()
    {
        try(BufferedReader br = new BufferedReader(new java.io.FileReader(path)))
        {
            String line;
            while((line = br.readLine()) != null)
            {
                line = line.replaceAll("[^A-Za-z0-9. ]", "");
                if(line.equals(""))
                {
                    
                }
                else
                {
                    onionlist.add(line);
                }
             
            }
            
            OnionList.setList(onionlist);
        }
        catch (FileNotFoundException ex)
        {
            Logger.getLogger(ListReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex)
        {
            Logger.getLogger(ListReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
