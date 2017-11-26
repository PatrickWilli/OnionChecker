/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.willi.onionchecker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketAddress;


/**
 *
 * @author phamm
 */
public class OnionProxy
{
    private String url;
    private String response = "";
    
    public OnionProxy(String url)
    {
        this.url = url;
    }
    
    public String visitSite() throws IOException
    {
        //System.getProperties().put("proxySet", "true");
        //System.getProperties().put("proxyHost", "127.0.0.1");
        //System.getProperties().put("proxyPort", "9050");
        SocketAddress addr = new InetSocketAddress("127.0.0.1", 9051);
        Proxy proxy = new Proxy(Proxy.Type.SOCKS, addr);
        
        Socket socket = new Socket(proxy);
        InetSocketAddress dest = new InetSocketAddress(url, 80);
  
        socket.connect(dest, 13 * 1000);
        InputStreamReader is = new InputStreamReader(socket.getInputStream()) {};

        PrintWriter pw = new PrintWriter(socket.getOutputStream());
        pw.println("GET / HTTP/1.0");
        pw.println();
        pw.flush();
        BufferedReader br = new BufferedReader(is);
        String line = "";
        while((line = br.readLine()) != null)
        {
            response += line;
        }

        return response;
          
        
     
        
    }
}
