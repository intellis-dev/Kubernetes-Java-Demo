/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my;
import javax.servlet.*;  
import java.sql.*;  
  
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
public class listener implements ServletContextListener {

    private ScheduledExecutorService scheduler;
    
        TimerTask task = new TimerTask() {
        @Override
        public void run() {
            
           System.out.println("I repeated see...");
            System.out.println("second");
            
            
       }
    };
        
    long gap=5;
    long delay=0;
    

    
    @Override
    public void contextDestroyed(ServletContextEvent event) {
        System.out.println("im destroyed");
    }

        //Run this before web application is started
    @Override
    public void contextInitialized(ServletContextEvent event) {
        System.out.println("im just started");	
        
          scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(task, delay, gap, TimeUnit.SECONDS);
 
    }
}
