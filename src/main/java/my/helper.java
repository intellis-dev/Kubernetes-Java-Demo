/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 *
 * @author work
 */
public abstract class helper {
    public ArrayList<String> al;
    
    int threadnum;
     int done=0;
     int errors=0;
    
    public helper(ArrayList a,int n) throws InterruptedException
    {
        al=a; threadnum=n;
        callme();
    }
    
    
    abstract void method(String ss) throws Exception;
    
    
    abstract void oncomplete();
    
    
    
    
    
    public void callme() throws InterruptedException
    {
         ArrayList<String> err=new ArrayList();
        ArrayList<Thread> at=new ArrayList();
        
       
        
        int jc=al.size()/threadnum;
         int rem=al.size()%threadnum;
        
        for(int i=0;i<threadnum;i++)
        {
           final int ci=i;
           
           
           
           
            
            Thread t=new Thread(new Runnable() {
                @Override
                public void run() {
                    
                    
                    for(int j=ci;j<ci+jc;j++){
                      //  System.out.println("see this:"+j);
                    
                    
                    String s=al.get(j);
                  //  System.out.println("im at "+s+" in thread-"+ci);
                    
   try{
        method(s);
        done++;
          
     }catch(Exception e){
         errors++;
        // e.printStackTrace();
        System.out.println("error at "+s+e.toString()+" collected till now: "+done+" with errors: "+err.size()+"errors variable: "+errors);
                       
         
   err.add(s);
     }
                    
                     
                    }
                }
            });
            
            t.start();
            at.add(t);
            
        }
        
        
         for(Thread t:at)
      {
          t.join();
          
      }
         
         
         
           for(int i=200*jc+1;i<al.size();i++)
         {
             String s=al.get(i);
              System.out.println("sextra:"+i);
               System.out.println("im  at "+s+" in  extras ");
               try{
        method(s);
          
     }catch(Exception e){
         e.printStackTrace();
         
   err.add(s);
     }
         }
           
           
           
            Set<String> opas = new LinkedHashSet<>(); 
            opas.addAll(err);
               err.clear();
              err.addAll(opas);
           
           
           
           
           
           
            System.out.println("Started fixing errors "+err.size());
         
         for(String s:err)
         {
            
             
             System.out.println("im fixing error at "+s);
           
             /*try{
        method();
          
     }catch(Exception e){
         e.printStackTrace();
         
   err.add(s);
     } */
             
             helper hh=new helper(err,threadnum){
                 @Override
                 void method(String ss)
                 {
                     method(ss);
                     
                 }
                 
                 @Override
                 void oncomplete()
                 {
                    oncomplete();
                     
                 }
             };
        
         }
         
         
         
         
         
         
         
         oncomplete();
         
         
    }
    
    
    
    
    
    
       

   
    
}
