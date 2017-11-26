/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.willi.onionchecker;

import org.silvertunnel_ng.netlib.api.NetFactory;
import org.silvertunnel_ng.netlib.api.NetLayer;
import org.silvertunnel_ng.netlib.api.NetLayerIDs;

/**
 *
 * @author phamm
 */
public class BootstrapTOR
{
    public NetLayer startBootstratp()
    {
        
        NetLayer lowerNetLayer = NetFactory.getInstance().getNetLayerById(NetLayerIDs.TOR_OVER_TLS_OVER_TCPIP);
        lowerNetLayer.waitUntilReady();
       // NetLayerInstance.setInstance(lowerNetLayer);
       return lowerNetLayer;
    }
}
