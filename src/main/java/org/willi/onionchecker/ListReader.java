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
 *  Liest die Liste mit .onion domains ein. Regex um die domains rauszufiltern könnte noch etwas präziser sein. 
 */
public class ListReader
{
    private String path;
    private ArrayList<String> onionlist = new ArrayList<String>();
    
    /*
    *   @param path Der Pfad zur datei
    */
    public ListReader(String path)
    {
        this.path = path;
    }
    
    /*
    *   Liest die Liste Zeile für Zeile ein, und ersetzt alles was nicht einer .onion domain entspricht.
    *   Wenn fertig mit einlesen, wird die ArrayList der OnionList Klasse übergeben
    */
    public void readList()
    {
        try(BufferedReader br = new BufferedReader(new java.io.FileReader(path)))
        {
            String line;
            while((line = br.readLine()) != null)
            {
                /*
                * ^ -> verneinen
                * [A-Za-z0-9. ] -> sollte klar sein. 
                * Also alles ersetzten was komisch ist. 
                * Kann man definitiv verbessern.
                */
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
