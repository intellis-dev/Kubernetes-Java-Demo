/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author work
 */
public class execute {
      
      
      static JSONObject jwo = new JSONObject();
                 public static void dothis(int i,int j)  
                 {
                    
                   for(int count=i;count<=j;count++){
               String formatstr = String.format("%05d", count);
               try{
               if(ntesweb.validatetrno(formatstr).equals("true"))
               {
                   Date d=new Date();
                    System.out.println("current count: "+formatstr+" "+d.toString());
                    String[] a=ntesweb.mixeddetails(formatstr);
                    JSONArray jc=new JSONArray();
                    for (int c = 0; c < a.length; c++)
                    {
                          jc.put(a[c]); 
                    }
                    
                    jwo.put(formatstr,jc.toString());
                   
                     
                 }
               
                   }catch(Exception e){System.out.println(e.toString() +" for "+formatstr);}
                 }
                 }
                 
                 
                 public static void main(String args[]) throws IOException, InterruptedException
                 {
                     
              Thread  t1 = new Thread(new Runnable() {
                         @Override
                         public void run() {
                             dothis(0,1000);
                              }
                     });
               Thread  t2 = new Thread(new Runnable() {
                         @Override
                         public void run() {
                              dothis(1001,2000);
                              }
                     });
                Thread  t3 = new Thread(new Runnable() {
                         @Override
                         public void run() {
                              dothis(2001,3000);
                              }
                     });
                 Thread  t4 = new Thread(new Runnable() {
                         @Override
                         public void run() {
                             
                              dothis(3001,4000);
                              }
                     });
                  Thread  t5 = new Thread(new Runnable() {
                         @Override
                         public void run() {
                              dothis(4001,5000);
                              }
                     });
                   Thread  t6 = new Thread(new Runnable() {
                         @Override
                         public void run() {
                              dothis(5001,6000);
                              }
                     });
                    Thread  t7 = new Thread(new Runnable() {
                         @Override
                         public void run() {
                              dothis(6001,7000);
                              }
                     });
                     Thread  t8 = new Thread(new Runnable() {
                         @Override
                         public void run() {
                              dothis(7001,8000);
                              }
                     });
                     
                       Thread t9 = new Thread(new Runnable() {
                         @Override
                         public void run() {
                              dothis(8001,9000);
                              }
                     });
                           Thread  t10 = new Thread(new Runnable() {
                         @Override
                         public void run() {
                              dothis(9001,10000);
                              }
                     });
               /*              Thread  t11 = new Thread(new Runnable() {
                         @Override
                         public void run() {
                             
                              }
                     });
                               Thread  t12 = new Thread(new Runnable() {
                         @Override
                         public void run() {
                             
                              }
                     });
                                 Thread  t13 = new Thread(new Runnable() {
                         @Override
                         public void run() {
                             
                              }
                     });
                                   Thread  t14 = new Thread(new Runnable() {
                         @Override
                         public void run() {
                             
                              }
                     });
                                     Thread  t15 = new Thread(new Runnable() {
                         @Override
                         public void run() {
                             
                              }
                     });
                                       Thread  t16 = new Thread(new Runnable() {
                         @Override
                         public void run() {
                             
                              }  
                     });   */
                       
                   t1.start();
                    t2.start();
                    t3.start();
                    t4.start();
                    t5.start();
                    t6.start();
                    t7.start();
                    t8.start();
                    t9.start();
                    t10.start(); 
                    
                   t1.join();
                    t2.join();
                    t3.join();
                    t4.join();
                    t5.join();
                    t6.join();
                    t7.join();
                    t8.join();
                    t9.join();
                    t10.join();
                    
              
         try{
                        String data=jwo.toString();     
          File f1 = new File("C:/Users/work/Desktop/10kprogress.json");
         if(!f1.exists()) {
            f1.createNewFile();
         } 
         FileWriter fileWritter = new FileWriter(f1,false);
         System.out.println("Writting file...");
        String ans=data;
        ans = ans.replaceAll("\\\\", "");
            fileWritter.write(ans);
            fileWritter.flush();
         fileWritter.close();
           
          System.out.println("Wrote");}catch(Exception e){ System.out.println("failed"+e.toString());}
                       }
                    
                 }
    
    
    
                 
                 
                 
           
             
    
