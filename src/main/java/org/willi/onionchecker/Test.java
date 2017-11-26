/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.willi.onionchecker;

import org.willi.onionchecker.Database.DatabaseConnection;
import org.willi.onionchecker.util.TitleExtractor;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.willi.onionchecker.Database.DatabaseWriter;
import org.willi.onionchecker.util.OnionList;

/**
 *
 * @author phamm
 */
public class Test
{
    public static void main(String[] args) throws InterruptedException, ExecutionException
    {        
        ArrayList<Future<SiteToCheck>> futurelist = new ArrayList<Future<SiteToCheck>>();
        
        ListReader lr = new ListReader("C:\\Users\\phamm\\Desktop\\onion\\new.txt");
        lr.readList();
        
        ThreadHandler th = new ThreadHandler(1);
        
      
        
        
        for(int i = 0; i < OnionList.getOnionList().size(); i++)
        {
            futurelist.add(th.addTask(new SiteToCheck(OnionList.getOnionList().get(i))));
        }
        
        while(!OnionList.getOnionList().isEmpty())
        {
            for(int i = 0; i < futurelist.size(); i++)
            {
                if(futurelist.get(i).isDone() )
                {
                    SiteToCheck s = futurelist.get(i).get();
                    new DatabaseWriter(new DatabaseConnection().getConnection()).wirteToDB(s.getURL(), s.isAlive(), s.getDateChecked(), s.getWebsiteTitle(), s.getIndexHtml());
                    OnionList.getOnionList().remove(s.getURL());
                }
            }
        }
    }
}
