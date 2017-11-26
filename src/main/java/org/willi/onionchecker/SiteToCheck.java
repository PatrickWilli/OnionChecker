/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.willi.onionchecker;

import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.silvertunnel_ng.netlib.api.util.TcpipNetAddress;
import org.silvertunnel_ng.netlib.util.HttpUtil;
import org.willi.onionchecker.util.TitleExtractor;

/**
 *
 * @author phamm
 */
public class SiteToCheck implements Callable<SiteToCheck>
{
    private String url;
    private TcpipNetAddress httpServerNetAddress;
    private String title;
    private int isAlive;
    private String index_hmtl;
    private String websiteTitle;


    public SiteToCheck(String url)
    {
        this.url = url;
    }
    
    public void start()
    {
        httpServerNetAddress = new TcpipNetAddress(url, 80);
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
            System.out.println(url + " NOT Reachable");
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
