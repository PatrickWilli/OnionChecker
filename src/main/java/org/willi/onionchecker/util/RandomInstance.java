/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.willi.onionchecker.util;

import java.util.Random;

/**
 *
 * @author phamm
 */
public class RandomInstance
{
    private static final Random r = new Random();
    
    public static synchronized Random getInstance()
    {
        return r;
    }
}
