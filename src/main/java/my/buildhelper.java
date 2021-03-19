/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.Thread.sleep;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;


import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author work
 */
public class buildhelper {
  static  int errcount;
   static ArrayList<String> at=new ArrayList();
    static int count=200;
    
    
    
    
    static int threadval=150;
    
    
    
    
    
    static int tsize;

   
   
   
   public static void main(String[] a) throws Exception
   {
      // System.out.print("Started");
      ArrayList<String> all= trlist();
      minbuild(all);
      
   }
    
    
    public static ArrayList<String> trlist() throws InterruptedException
    {
       ArrayList<String> al=new ArrayList();
         Set<String> as = new LinkedHashSet<>(); 
        ArrayList<String> err=new ArrayList();
        ArrayList<Thread> at=new ArrayList();
              
        
       
        
        for(int i=0;i<100000;i=i+threadval){
             
            final int c=i;
            Thread t= new Thread(new Runnable(){
            @Override
            public void run() {
                
               
                
                for(int j=c;j<c+threadval;j++)
                {
                   
                     String s=String.format("%05d", j);
                    
                     
                    
                    try {
                        
                       
                        String txt=ntesweb.stringaturl("https://enquiry.indianrail.gov.in/ntes/NTES?action=getTrainData&trainNo="+s);
                      
                         
                        if(txt.contains("trainDataFound"))
                        {
                            al.add(s);
                          //  break;
                        }
                        else if(txt.contains("Invalid train No"))
                        {
                            
                              for(int k=0;k<3;k++){
                                  
                                 txt=ntesweb.stringaturl("https://enquiry.indianrail.gov.in/ntes/NTES?action=getTrainData&trainNo="+s);
                      
                         
                        if(txt.contains("trainDataFound"))
                        {
                            al.add(s);
                           // break;
                        }
                              
                              }
                          
                            
                        }
                        else
                        {
                             System.out.println(s+" see "+txt);
                            err.add(s);
                           // break;
                        }
                    } catch (Exception ex) {
                        System.out.println("err at "+s+ex.toString()+" collected till now: "+al.size()+" with errors: "+err.size());
                         err.add(s);}
                    
                      
                       
                       
                       
        
                    
                    
                }
                
                
                
                 
            }
            });
            at.add(t);
            t.start();
         
 

        
    }
        
        
        
      
       
      for(Thread t:at)
      {
          t.join();
          
      }
      
      
      
      
      
    
        
            System.out.println("act size: "+al.size()+" err size: "+err.size() );
            
            
            
    
            
       
            
            //original:
   /*  
    for(String s:err) {
        
      
       //  String s=iter.next();
          
          for(int k=0;k<4;k++){
           String txt;
            try {
                txt = ntesweb.stringaturl("https://enquiry.indianrail.gov.in/ntes/NTES?action=getTrainData&trainNo="+s);
                
               
                if(txt.contains("trainDataFound"))
                        {
                            al.add(s);
                            break;
                            
                        }
                else if(txt.contains("Invalid train No"))
                {
                   
                    if(s.equals("100000") || s.equals("0"))
                        break;
                    
                }
                else
                {
                    
                    System.out.println(s+" see "+txt);
                    break;
                }
                
               
             
               
            } catch (Exception ex){
            
            System.out.println(ex.toString());
            };
          
             
               
      }
      
      System.out.println("Im trying inloop with "+err.toString());
                       
      }
    
    
    
    */
    
    
   
   
   
   
    
    
    
    
    
    
  
    //  for(String s:al){     System.out.println("print"+s); }
   
    as.addAll(al);
    al.clear();
    al.addAll(as);
    
    
      
      
      
      System.out.println("finally act size: "+al.size()+" err size: "+err.size() );
      
      
      
     
        
        
         return al;
    }
    
    
    
    
    
    
    
    
    
    
    public static void minbuild(ArrayList<String> al) throws Exception
    {
        System.out.println("min build started...");
        
         ArrayList<String> err=new ArrayList();
        ArrayList<Thread> at=new ArrayList();
        
        int jc=al.size()/200;
         int rem=al.size()%200;
        
        for(int i=0;i<200;i++)
        {
           final int ci=i;
            
            Thread t=new Thread(new Runnable() {
                @Override
                public void run() {
                    
                    
                    for(int j=ci;j<ci+jc;j++){
                        System.out.println("see this:"+j);
                    
                    
                    String s=al.get(j);
                    System.out.println("im at "+s+" in thread-"+ci);
                    
   try{
        String inp[]=optimised.mindetails(s);
        if(inp!=null){
            
        String sql="INSERT INTO trains\n" +"VALUES ('"+s+"','"+inp[0]+"','"+inp[1]+"','"+inp[2]+"','"+inp[3]+"','"+inp[4]+"','"+inp[5]+"','"+inp[6]+"','"+inp[7]+"','"+inp[8]+"','"+inp[9]+",'"+inp[10]+"');" ;
       
        dbhelper.run(sql);}
          
     }catch(Exception e){
         e.printStackTrace();
         
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
        String inp[]=optimised.mindetails(s);
        if(inp!=null){
        String sql="INSERT INTO trains\n" +"VALUES ('"+s+"','"+inp[0]+"','"+inp[1]+"','"+inp[2]+"','"+inp[3]+"','"+inp[4]+"','"+inp[5]+"','"+inp[6]+"','"+inp[7]+"','"+inp[8]+"','"+inp[9]+"');" ;
       
        dbhelper.run(sql);}
          
     }catch(Exception e){
         
   err.add(s);
     }
         }
         
         
           System.out.println("Started fixing errors "+err.size());
         
         for(String s:err)
         {
              System.out.println("im fixing error at "+s);
              String inp[]=optimised.mindetails(s);
        if(inp!=null){
        String sql="INSERT INTO trains\n" +"VALUES ('"+s+"','"+inp[0]+"','"+inp[1]+"','"+inp[2]+"','"+inp[3]+"','"+inp[4]+"','"+inp[5]+"','"+inp[6]+"','"+inp[7]+"','"+inp[8]+"','"+inp[9]+"');" ;
       
        dbhelper.run(sql);}
        
         }
         
         
         
         
         dbhelper.close();
         System.out.println("Completed....");
         
          
    
      
     
             
             
             
         }
    
    
    
    
    
    public static void optbuild(ArrayList <String> al) throws InterruptedException
    {
         System.out.println("optim build started...for size: "+al.size());
         Set<String> as = new LinkedHashSet<>(); 
        as.addAll(al);
    al.clear();
    al.addAll(as);
        
        
        System.out.println("size after reducing.."+al.size());
        
         ArrayList<String> err=new ArrayList();
        ArrayList<Thread> at=new ArrayList();
        
        
       int ac=al.size();

           int rem=ac%50;
        int thc=ac/50;

	 
	  
	  for(int ii=1;ii<=ac/50;ii++)
	  {
              final int i=ii;
              Thread t=new Thread(new Runnable() {
                  @Override
                  public void run() {
                      for(int j=(i-1)*50;j<i*50;j++)
              {
                   System.out.println("im at index"+j);
                  String s=al.get(j);
                    try{
        String inp[]=optimised.mindetails(s);
      String sql="replace INTO trains\n" +"VALUES ('"+s+"','"+inp[0]+"','"+inp[1]+"','"+inp[2]+"','"+inp[3]+"','"+inp[4]+"','"+inp[5]+"','"+inp[6]+"','"+inp[7]+"','"+inp[8]+"','"+inp[9]+"','"+inp[10]+"','"+inp[11]+"','"+inp[12]+"','"+inp[13]+"','"+inp[14]+"');" ;
         dbhelper.run(sql); 
                    
        
         
                    
                    }catch(Exception e){err.add(s);  }
                  
              }
                  }
              });
              
              at.add(t);
              t.start();
              
	      
	      
	      
	     
	  }
	  
	  
	  for(int j=thc*50;j<ac;j++)
	  {
	      System.out.println("im at index"+j);
                  String s=al.get(j);
                    try{
        String inp[]=optimised.mindetails(s);
      String sql="replace INTO trains\n" +"VALUES ('"+s+"','"+inp[0]+"','"+inp[1]+"','"+inp[2]+"','"+inp[3]+"','"+inp[4]+"','"+inp[5]+"','"+inp[6]+"','"+inp[7]+"','"+inp[8]+"','"+inp[9]+"','"+inp[10]+"','"+inp[11]+"','"+inp[12]+"','"+inp[13]+"','"+inp[14]+"');" ;
        dbhelper.run(sql);
                   
                    
                    
                    }catch(Exception e){err.add(s);  }
	  }
	  
          
          
           for(Thread t:at)
      {
          t.join();
          
      }
          
          
          
          
          
          
          
          
           
          tsize=err.size();
           
               sleep(300);
	 errcount=err.size();
          for(String s:err)
          {
               
                   
              
                        new Thread(new Runnable()
                        {
                            @Override
                        public void run() {
                             System.out.println("im in errors of size: "+tsize+" at "+s);
                  
                              try{
                                  
                                 // sleep(50);
                
                             String inp[]=optimised.mindetails(s);
       String sql="replace INTO trains\n" +"VALUES ('"+s+"','"+inp[0]+"','"+inp[1]+"','"+inp[2]+"','"+inp[3]+"','"+inp[4]+"','"+inp[5]+"','"+inp[6]+"','"+inp[7]+"','"+inp[8]+"','"+inp[9]+"','"+inp[10]+"','"+inp[11]+"','"+inp[12]+"','"+inp[13]+"','"+inp[14]+"');" ;
       dbhelper.run(sql);
        tsize--;
                    
                    }catch(Exception e){System.out.println("got error again at "+s); 
                    e.printStackTrace();}
                    
                    }
                            
                        }).start();
       
              
          }
          
                     
          
          System.out.println("completed from size:"+al.size()+"with errors size:"+errcount);

          
        
        
        
        
        
    }
         
         
         
         
        
        
        
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
   public static void sfn(String s)
{




}
   
   public static void routedo(ArrayList<String> l) throws Exception
   {
       for(String s:l)
       {
           String a[]=optimised.route(s);
         
           String scode=a[0];
           String dist=a[1];
         
      /*   String sel="SELECT * FROM routes WHERE stcode LIKE '%"+scode+"%';";
         String trno= dbhelper.result(sel,"trno").get(0);
      //   System.out.println(sel);
         if(!trno.equals("ResultSet is empty"))
         {
              String distprev=dbhelper.result(sel,"dist").get(0);
             String stprev=dbhelper.result(sel,"stcode").get(0);
             String st="replace into routes values('"+trno+","+s+"','"+stprev+"','"+distprev+"');";
             dbhelper.run(st);
         }
         else
         {
             System.out.println("empty results"+s); */
             String st="insert into routes values('"+s+"','"+a[0]+"','"+a[1]+"');";
             dbhelper.run(st);
        // }
         
           
           
       }
       
   }
   
   
   
   
   
   public static ArrayList<String> newlist() throws InterruptedException
   {
       
       ArrayList<String> opal=new ArrayList();
        ArrayList<String> operr=new ArrayList(); 
        Set<String> opas = new LinkedHashSet<>(); 
       
       ArrayList<String> nums=new ArrayList();
       
       for(int i=0;i<100000;i++){
           nums.add(String.format("%05d", i));
       }
       
       
       helper hm=new helper(nums,600){
                 @Override
                 void method(String s) throws Exception
                 {
                     
                   String txt=ntesweb.stringaturl("https://enquiry.indianrail.gov.in/ntes/NTES?action=getTrainData&trainNo="+s);
                      
                         
                        if(txt.contains("trainDataFound"))
                        {
                           opal.add(s);
                          //  break;
                        }
                        else if(txt.contains("Invalid train No"))
                        {
                            
                              for(int k=0;k<2;k++){
                                  
                                  
                                  
                                new Thread(new Runnable() {
                         @Override
                         public void run() {
                            
                            
                             try {
                                String txt=ntesweb.stringaturl("https://enquiry.indianrail.gov.in/ntes/NTES?action=getTrainData&trainNo="+s);
                                 
                                 
                                 if(txt.contains("trainDataFound"))
                                 {
                                     opal.add(s);
                                     // break;
                                 }    } catch (IOException ex) {
                                     ex.printStackTrace();
                                 
                             }
                              }
                     });
                                  
                                  
                               
                              }
                          
                            
                        }
                        else
                        {
                          /*  // System.out.println(s+" see "+txt);
                             operr.add(s);
                            throw new Exception();
                           
                           // break; */
                        }
                     
                     
                 }
                 
                 
                 
                   @Override
                 void oncomplete()
                 {
                   // oncomplete();
                       opas.addAll(opal);
                     opal.clear();
                         opal.addAll(opas);
    
                      System.out.println("finally act size: "+opal.size()+" err size: "+operr.size() );
                     
                     
                 }
             };
       
       
      
               return opal;
        
         
         
   }
    
    
    
}
    
    
    

