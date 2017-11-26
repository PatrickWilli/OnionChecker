/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.willi.onionchecker.util;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author phamm
 */
public class OnionList
{
    private static List<String> onionlist = Collections.synchronizedList(new ArrayList());
    
    public synchronized static List<String> getOnionList()
    {
        return onionlist;
    }
    
    public synchronized static void setList(List list)
    {
        onionlist = list;
    }
}
