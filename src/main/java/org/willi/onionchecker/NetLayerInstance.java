/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.willi.onionchecker;

import org.silvertunnel_ng.netlib.api.NetLayer;

/**
 *
 * @author phamm
 */
public class NetLayerInstance
{
    private static final NetLayer netlayer = new BootstrapTOR().startBootstratp();
    
    public static NetLayer getInstance()
    {
        while(netlayer == null)
        {
        }
        return netlayer;
    }
    
//    public static void setInstance(NetLayer layer)
//    {
//        netlayer = layer;
//    }
}
