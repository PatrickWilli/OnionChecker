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
 * Baut eine Verbindung zu 127.0.0.1:9050 (der lokale TOR port) auf, und versucht die .onion URL zu erreichen. 
 * Wenn Sie erreichbar ist, wird die gesamte index.html eingelesen, als String konvertiert und zurückgegeben.  
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
        
        SocketAddress addr = new InetSocketAddress("127.0.0.1", 9050);
        Proxy proxy = new Proxy(Proxy.Type.SOCKS, addr);
        
        Socket socket = new Socket(proxy);
        InetSocketAddress dest = new InetSocketAddress(url, 80);
        //13 * 10 -> nur 13 anstatt 70 sekunden timeout
        socket.connect(dest, 13 * 1000);
        InputStreamReader is = new InputStreamReader(socket.getInputStream());
        
        /*
        *   GET Anfrage das wir die index seite zurück bekommen
        */
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
        //fucking ressourcen freigeben
        socket.close();
        br.close();
        pw.close();
        is.close();
        return response;
          
        
     
        
    }
}
