package com.solvians;

import com.solvians.service.GenerateCertificateUpdate;
import com.solvians.service.GenerateCertificateUpdateBatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        triggerWithThreadCount(10,100);
    }



    private static void triggerWithThreadCount(int threadCount, int certificateCount) {
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        List<Future<String>> list = new ArrayList<Future<String>>();
        Callable<String> callable = new GenerateCertificateUpdate();
        for(int i=0; i< certificateCount; i++){
            //submit Callable tasks to be executed by thread pool
            Future<String> future = executor.submit(callable);
            //add Future to the list, we can get return value using Future
            list.add(future);
        }
        for(Future<String> fut : list){
            try {
                //print the return value of Future, notice the output delay in console
                // because Future.get() waits for task to get completed
                System.out.println(fut.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();
    }
}
