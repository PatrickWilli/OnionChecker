/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.willi.onionchecker;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author phamm
 */
public class ThreadHandler
{
    private ExecutorService executor;
    public ThreadHandler(int nThreads)
    {
        executor = Executors.newFixedThreadPool(nThreads);
    }
    
    public Future addTask(Callable c)
    {
        return executor.submit(c);
    }
}
