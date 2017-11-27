/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.willi.onionchecker;

import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.concurrent.Callable;
import org.willi.onionchecker.util.TitleExtractor;
import org.willi.onionchecker.util.RandomInstance;

/**
 *  Diese Klasse übernimmt das prüfen ob die website online ist. Wenn sie nicht Online ist wird eine Exception (Timeout) geworfen.
 *  Wenn sie online ist werden der Titel der Website Extrahiert und die Attribute gesetzt.  
 *  Diese Klasse ist auch gleichzeitig das Future Objekt für den ThreadPool. 
 *
 */
public class SiteToCheck implements Callable<SiteToCheck>
{
    private String url;
    private int isAlive;
    private String index_hmtl;
    private String websiteTitle;
    private int counter = 0;


    public SiteToCheck(String url)
    {
        this.url = url;
    }
    
    public void start() 
    {
        try
        {
            index_hmtl = new OnionProxy(url).visitSite(); 
            setIndexHtml(index_hmtl);
            setWebsiteTitle(TitleExtractor.getPageTitle(index_hmtl));
            setisAlive(1);
            System.out.println(url + " Reachable");
            
        }
        catch (IOException ex)
        {
            setisAlive(0);
            System.out.println(url + " NOT Reachable // " + ex.getMessage());
            //5 mal versuchen
            if(counter == 4)
            {
                System.err.println("Connection refused 5 times");
                return;
            }
            else
            {
                if(!ex.getMessage().toLowerCase().contains("connection refused"))
                {
                    return;
                }
                   try
                  {
                      Thread.sleep(RandomInstance.getInstance().nextInt(3000));
                      counter++;
                      start();
                  }
                  catch(InterruptedException i)
                  {
                      System.err.println("cannot sleep...");
                      counter++;
                      start();

                  }
                
              
            }
        }
 
    }
    
    public String getIndexHtml()
    {
        if(index_hmtl == null)
        {
            return "";
        }
        return index_hmtl;
    }
    
    public String getURL()
    {
        return url;
    }
    
    public int isAlive()
    {
        return isAlive;
    }
    
    private void setisAlive(int isAlive)
    {
        this.isAlive = isAlive;
    }
    
    public String getDateChecked()
    {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
    
    private void setIndexHtml(String html)
    {
        this.index_hmtl = html;
    }
    
    private void setWebsiteTitle(String title)
    {
        this.websiteTitle = title;
    }

    public String getWebsiteTitle()
    {
        if(websiteTitle == null)
        {
            return "";
        }
        return websiteTitle;
    }
    
    @Override
    public SiteToCheck call() throws Exception
    {
        start();
        return this;
    }
    
}
