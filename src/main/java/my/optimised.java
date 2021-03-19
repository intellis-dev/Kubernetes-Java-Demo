/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my;

import static cris.icms.ntes.htmlmain.USER_AGENT;
import static cris.icms.ntes.htmlmain.cleantext;
import static cris.icms.ntes.htmlmain.coach;
import static cris.icms.ntes.htmlmain.ddgurl;
import static cris.icms.ntes.htmlmain.directarray;
import static cris.icms.ntes.htmlmain.directsearchurl;
import static cris.icms.ntes.htmlmain.googleurl;
import static cris.icms.ntes.htmlmain.jsongoogle;
import static cris.icms.ntes.htmlmain.newirflister;
import static cris.icms.ntes.htmlmain.obtainedrake;
import static cris.icms.ntes.htmlmain.obtainedtrname;
import static cris.icms.ntes.htmlmain.obtainedtrno;
import static cris.icms.ntes.htmlmain.rakereversal;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import static my.ntesweb.removejsonarr;
import static my.ntesweb.stringaturl;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author work
 */
public class optimised {
    
  static  int i;
   static  int j;
    
    public static void main(String args[]) throws IOException, InterruptedException, SQLException, ClassNotFoundException, ParseException
    {
  /*     for(int i=0;i<5000;i++){
            
            String s=String.format("%05d", i);
     try{
         System.out.println(Arrays.toString(ntesmin(s)));
         System.out.println(s);
     }catch(Exception e){}
     
       }  */
        
        
  /*      
    
    for(int k=0;k<100;k++)
     {
      threadhelper.does(1000*k); 
        
     }
    
    */
    
     //int mcc=404;int mnc=29;    
  
        for(int k=0;k<400;k++)
     {
       threadhelper.wholedoes(250*k);     
        //   threadhelper.locdoer(mcc,mnc,k);     
     } 
       
    


//optimised.ntesfull("12760", "new");


        
        
        //testing location api for 404 contains 88 mnc
        
     /*   int mncs[]=new int[]{29,25,41,35,37,91,28,42,17,49,40,10,98,96,3,45,95,31,93,90,92,16,2,70,94,97,79,73,38,75,64,57,34,51,62,71,72,81,58,66,77,76,53,59,80,55,54,74,48,68,69,7,4,24,12,82,44,19,78,22,14,87,89,56,21,9,36,18,83,67,50,52,85,13,84,11,5,1,86,46,30,27,20,60,43,15,88};
     
        for(int lac=0;lac<=65533;lac++)
        {
          for(int cellid=0;cellid<=65533;cellid++)
        {
            
        }  
        }
     
      
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
        
    for(int val=i;val<i+1000;val++){
        
    }
                
            }
        }); */
     

      
        
        
        
        
        
        
        
       }
    
    public static String[] mindetails(String trainno) throws  InterruptedException
    {
        String n[]=null;
        try{
        n=ntesmin(trainno);
        }catch(Exception m)
        {
             //trying to fill missing values...
        if(m.toString().toLowerCase().contains("connection")==true || m.toString().toLowerCase().contains("timeout")==true)
        {
            System.out.println("identify "+trainno);
            sleep(40);
            return mindetails(trainno);
            
        }
        }
        
        
        try
        {
            
            
           String ir[]=irfmin(trainno);
           
           if(ir[0].equals("correct"))
           {
               n[2]=ir[1];  //update trname
               n[6]=ir[2]; //add coach
               n[7]=ir[3];  //add reversals      
                  
               n[14]=ir[4];  //add rake data      
           }
           
        }catch(Exception m){
               //trying to fill missing values...
        if(m.toString().toLowerCase().contains("connection")==true || m.toString().toLowerCase().contains("timeout")==true)
        {
            System.out.println("identify in irf "+trainno);
            sleep(40);
            return mindetails(trainno);
            
        }}
        
        
        if(n==null)
        {
             System.out.println("blunder in mindetails "+trainno);
            sleep(40);
            return mindetails(trainno);
        }
        
        return n;
    }
    
    
    
    public static String[] irfmin(String trainno)
     {
         String a[]=null;
         try{
            a= newirftest(trainno,"directarray");
         }
         catch(Exception e)
         {
              try{
                   System.out.println(e.toString());
            a= newirftest(trainno,"jsongoogle");
            
         }
         catch(Exception e1)
         {
             if(e1.toString().contains("Status=403"))
             {
                 
                 try {
                     Thread.sleep(2500);
                      a= newirftest(trainno,"jsongoogle");
                 } catch (Exception ex) {
                     System.out.println("Im waiting");
                     }
             }
             
              try{
                    System.out.println(e1.toString());
            a= newirftest(trainno,"google");
           
         }
         catch(Exception e2)
         {
             try{
            a= newirftest(trainno,"ddg");
         }
         catch(Exception e4)
         {
             a=new String[4];
             a[0]="none found this trno";
           //  System.out.println(e4.toString());
         };
         };
         };
         };
         
         return a;
     }
    
    
    public static String[] ntesmin(String trno) throws IOException, SQLException, Exception
    {
        
        //trying..
         String a[]=optimised.route(trno);
        String st="insert into routes values('"+trno+"','"+a[0]+"','"+a[1]+"');";
           dbhelper.run(st);
           
           
           
            
         String url="https://enquiry.indianrail.gov.in/ntes/FutureTrain?action=getTrainMasterData&trainNo="+trno;
      
         String txt=stringaturl(url);
    
     int i=txt.indexOf("runsOn:");
     String runson= txt.substring(i+8,i+15).trim();
     
     i=txt.indexOf("trainType:\"");
     int j=txt.indexOf("\"",i+11);
     String traintype=txt.substring(i+11,j).trim();
     
     i=txt.indexOf("vldFrm:\"");
      j=txt.indexOf("\"",i+8);
     String validfrom=txt.substring(i+8,j).trim();
     
     i=txt.indexOf("vldTo:\"");
      j=txt.indexOf("\"",i+7);
     String validto=txt.substring(i+7,j).trim(); String validity;
     if(!validto.equals("-"))
     {
        validity="Valid Between "+validfrom+" - "+validto; 
     }
     else
     {
          validity="Valid From "+validfrom;
     }
     
     i=txt.indexOf("trainName:\"");
      j=txt.indexOf("\"",i+11);
     String trainname=txt.substring(i+11,j).trim();
     
     
    
     i=txt.indexOf("stations:[")+9;
     
   //  i=txt.indexOf("stations:[",i)+9;   //testing for upcoming date so that platform no.s are got
     
     j=txt.indexOf("]",i);
    String json= txt.substring(i,j+1);
    ArrayList<String> al=new ArrayList();
    
    //trying..  
    ArrayList arrt=new ArrayList();
    ArrayList dept=new ArrayList();
    
   
    //for(int c=0;json.substring(c).contains("stnCode:");)
  /*  while(json.substring(c).contains("stnCode:"))
    {
        i=json.indexOf("stnCode:\"", c)+9;
        j=json.indexOf("\"", i);   int next=j;
        
        al.add(json.substring(i,j).trim());
      
        
        i=json.indexOf("arrTime:\"", c)+9;
        j=json.indexOf("\"", i);
        String arr=json.substring(i,j).trim();

        i=json.indexOf("depTime:\"", c)+9;
        j=json.indexOf("\"", i);
        String dep=json.substring(i,j).trim();
        
        
         i=json.indexOf("arrDayCnt:", c)+10;
        j=json.indexOf(",", i);
        String arrday=json.substring(i,j).trim();

        i=json.indexOf("dayCnt:", c)+7;
        j=json.indexOf(",", i);
        String depday=json.substring(i,j).trim();
        
        
        dept.add(depday+" "+dep);
        
        arrt.add(arrday+" "+arr);
       //else arrt.add("-");
     
       
        
        c=next;
    }
    */
  //int tc=0; String tmps;
    
    String jsonparts[]=json.split("distance:");
    
    
    ArrayList<String> dist=new ArrayList<String>();
    for(int co=0;co<jsonparts.length ;co++)
    { 
        
        String part=jsonparts[co];
        if( jsonparts[co].contains("stnCode:"))
        {
          i=part.indexOf("stnCode:\"")+9;
        j=part.indexOf("\"", i); 
        
        al.add(part.substring(i,j).trim());
      
        
        i=part.indexOf("arrTime:\"")+9;
        j=part.indexOf("\"", i);
        String arr=part.substring(i,j).trim();

        i=part.indexOf("depTime:\"")+9;
        j=part.indexOf("\"", i);
        String dep=part.substring(i,j).trim();
        
        
         i=part.indexOf("arrDayCnt:")+10;
        j=part.indexOf(",", i);
        String arrday=part.substring(i,j).trim();

        i=part.indexOf("dayCnt:")+7;
        j=part.indexOf(",", i);
        String depday=part.substring(i,j).trim();
        
        
        dept.add(depday+" "+dep);
        
       if(co!=0) arrt.add(arrday+" "+arr);
       else arrt.add("-");
     
       
      
       
        }
         if(part.contains("}"))
       {
           int k=part.indexOf("}");
           dist.add(part.substring(0,k).trim());
          
          //  System.out.println(part.substring(0,k).trim());
           
       }
        
    }
    dept.remove(dept.size()-1);
    dept.add("-");
   
    
  
     
    
    
    String output[]=new String[15];  //trying as 10 from 8  //newly 11
    
    output[4]=al.toString().replace(", ", ",").trim();
    output[0]=traintype.trim();
    output[2]=trainname.trim();
    output[3]=runson.trim();
    output[1]=validity.trim();
   
    if((traintype.contains("SP") || traintype.contains("PEXP") || validity.contains("2021")))
    {
     output[5]="maybe";
    }
    else
    {
        output[5]="covid";
    }
   
    
    output[6]="[not available]"; 
    output[7]="[not available]"; 
    
    
    //trying..
    output[8]=arrt.toString().replace(", ", ",").trim();
    output[9]=dept.toString().replace(", ", ",").trim();
    
    
    
    output[10]=dist.toString().replace(", ", ",").trim();
    
    
    //trying...
    
      url="https://enquiry.indianrail.gov.in/ntes/NTES?action=getTrainData&trainNo="+trno;
        txt=stringaturl(url);
        if(!txt.contains("Invalid"))
       {
         //getting pfdata 
    /*   HashMap<String,Integer> pfmap=pfdata(txt);
       Collection<Integer> values = pfmap.values(); 
      ArrayList<Integer> listOfValues = new ArrayList<>(values); 
      output[11]=listOfValues.toString().replace(", ", ",").trim();
       
       
       HashMap<String,Integer> arr[]=analysed(trno);
         //getting delayexparr 
       HashMap<String,Integer> delayarr=arr[0];
       values = delayarr.values(); 
      listOfValues = new ArrayList<>(values); 
      output[12]=listOfValues.toString().replace(", ", ",").trim();
      
        //getting delayexpdep 
        HashMap<String,Integer> delaydep=arr[1];
       values = delaydep.values(); 
      listOfValues = new ArrayList<>(values); 
      output[13]=listOfValues.toString().replace(", ", ",").trim();
       
           */
           
            HashMap<String,Integer> pfmap=pfdata(txt);
              HashMap<String,Integer> arr[]=analysed(trno);  
             HashMap<String,Integer> delayarr=arr[0];
              HashMap<String,Integer> delaydep=arr[1];
              
              ArrayList<Integer>plist=new ArrayList<Integer>();
               ArrayList<Integer>dalist=new ArrayList<Integer>();
                ArrayList<Integer>ddlist=new ArrayList<Integer>();
                
                if(delayarr.size()>0){
            
           for(String stcode:al)
           {
               plist.add(pfmap.get(stcode));
               dalist.add(delayarr.get(stcode));
               ddlist.add(delaydep.get(stcode));
           }
                }
           output[11]=plist.toString().replace(", ", ",").trim();
            output[12]=dalist.toString().replace(", ", ",").trim();
             output[13]=ddlist.toString().replace(", ", ",").trim();
             
             
             //rake-data
               output[14]="[not available]";
               
               
               //trying...
               if(!output[12].equals("[]"))
               {
                      output[5]="maybe";
               }
               
               
       }
    
    return output;
        
    }
    
    
    
    public static void ntesfull(String trno,String tag)
    {
        try{
        newntesfull(trno,tag);
        }catch(Exception e){
        
        if(e.toString().toLowerCase().contains("timed")) {
       try {
           Thread.sleep(100);
           ntesfull(trno,tag);
       } catch (Exception ex) {
          System.out.println("got exception again at "+trno);
       }
       
    }
        };
        
    }
    
    
    
    
    
    
    public static  void optntesfull(String trno) throws Exception
    {
         String url="https://enquiry.indianrail.gov.in/ntes/NTES?action=getTrainData&trainNo="+trno;
       String txt=stringaturl(url);
       System.out.println("Started for "+trno);
       if(txt.contains("Invalid"))
       {
           return ;
       }
        
       HashMap<String,Integer> pfmap=pfdata(txt);
      HashMap<String,Integer> arr[]=analysed(trno);
       HashMap<String,Integer> delayarr=arr[0];
       HashMap<String,Integer> delaydep=arr[1];
        i=txt.indexOf("stations:[")+9;  j=txt.indexOf("]",i);
        String json=txt.substring(i,j+1);
        
         JSONArray ja=new JSONArray(json);
  int count=ja.length();
    JSONObject obj=null;
       SimpleDateFormat format = new SimpleDateFormat("HH:mm");
      SimpleDateFormat onlyday = new SimpleDateFormat("dd");
    SimpleDateFormat dayformat = new SimpleDateFormat("dd:HH:mm");
        
    }
    
    
    
    
    
    
    
    public static void newntesfull(String trno,String tag) throws Exception
    {
        String url="https://enquiry.indianrail.gov.in/ntes/NTES?action=getTrainData&trainNo="+trno;
       String txt=stringaturl(url);
       System.out.println("Started for "+trno);
       if(txt.contains("Invalid"))
       {
           return ;
       }
       
       
       //getting pfdata 
       HashMap<String,Integer> pfmap=pfdata(txt);
     
       
       HashMap<String,Integer> arr[]=analysed(trno);
         //getting delayexparr 
       HashMap<String,Integer> delayarr=arr[0];
        //getting delayexpdep 
       HashMap<String,Integer> delaydep=arr[1];
       
       
        String t=trno;
        i=txt.indexOf("stations:[")+9;  j=txt.indexOf("]",i);
        String json=txt.substring(i,j+1);
           JSONArray ja=new JSONArray(json);
  int count=ja.length();
    JSONObject obj=null;
       SimpleDateFormat format = new SimpleDateFormat("HH:mm");
      SimpleDateFormat onlyday = new SimpleDateFormat("dd");
    SimpleDateFormat dayformat = new SimpleDateFormat("dd:HH:mm");
          trno=tag+trno;
    String s="CREATE TABLE IF NOT EXISTS "+trno+" (sr SMALLINT,"+"scode varchar(12),"+"stop TINYINT,"+
  "arr varchar(10),"+"dep varchar(10),"+"pfno TINYINT,"+"day TINYINT,"+"depday TINYINT,"+"dist SMALLINT,"+"speed float,"+"deldarr SMALLINT,"+"deldep SMALLINT"+");";
     dbhelper.run(s);
        
        
     
       for (int k=0;k<count;k++) 
     {
          obj = ja.getJSONObject(k);
          
          int srno=obj.getInt("sr");
         double speed=0.0;
          String scode=obj.getString("stnCode");
          String scharr=obj.getString("arrTime");    if(k==0) scharr="Start";
          String schdep=obj.getString("depTime");    if(k==count-1) schdep="End";
       //  int pfno=obj.getInt("pfNo");
       
     //trying..
    int pfno=0; if(pfmap.containsKey(scode))pfno= pfmap.get(scode);
    Integer delarr=null; if(delayarr.containsKey(scode))delarr= delayarr.get(scode);
    Integer deldep=null; if(delaydep.containsKey(scode))deldep= delaydep.get(scode);
       
       
       
       
       
         int day=obj.getInt("arrDayCnt")+1;
         int depday=obj.getInt("dayCnt")+1;
         
          int dist=obj.getInt("distance");
          
          
if(k!=count-1){           
Date date1 = format.parse(schdep);
Date date2 = format.parse(ja.getJSONObject(k+1).getString("arrTime"));
long difference = date2.getTime() - date1.getTime()+86400000*(ja.getJSONObject(k+1).getInt("arrDayCnt")+1-depday); 
int distval=ja.getJSONObject(k+1).getInt("distance")-dist;
speed=(double)(distval)/difference;  


speed=speed*3600000;}

          
          
         
     String sql="INSERT INTO "+trno+" VALUES ('"+srno+"','"+scode+"',1,'"+scharr+"','"+schdep+"','"+pfno+"','"+day+"','"+depday+"','"+dist+"','"+speed+"','"+delarr+"','"+deldep+"');" ;
 //trying..forsmall  String sql="INSERT INTO fulldetails"+" VALUES ('"+trno+"','"+srno+"','"+scode+"',1,'"+scharr+"','"+schdep+"','"+pfno+"','"+day+"','"+dist+"','"+speed+"');" ;
  
  if(srno!=0) dbhelper.run(sql);
    
     
     }
   
 /*   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yyyy");  
   LocalDateTime now = LocalDateTime.now();  
   String d=dtf.format(now);  */
 String d=findfrom(t);
   
     url="https://enquiry.indianrail.gov.in/ntes/RailGeo?action=getTrainSchedule&trainStartDate="+d+"&trainNo="+t;
    
     String temps="nothing";
     //try{
     txt=Jsoup.connect(url).userAgent(USER_AGENT).maxBodySize(0).header("Accept-Encoding", "gzip, deflate").get().toString();
      temps=txt;
      if(txt.contains("Page Not Found"))
      {
           dbhelper.run("INSERT INTO errors VALUES('"+t+"');");
           dbhelper.run("drop table "+trno+";");
           return ;
      }
      
               
        i=txt.indexOf("[");
       j=txt.lastIndexOf("]");
       json= txt.substring(i,j+1);
       
       
      
       
         ja=new JSONArray(json); count=ja.length();   
         
         for (int k=0;k<count;k++) 
         {
         obj=ja.getJSONObject(k);
         Boolean b=ja.getJSONObject(k).getBoolean("stop");
        
         if(b==false){
           int srno=obj.getInt("sr");
         String scode=obj.getString("stnCode");
          int dist=obj.getInt("distance");
         // System.out.println(srno);
       String sql="INSERT INTO "+trno+"(sr,scode,stop,dist) VALUES ('"+srno+"','"+scode+"','0','"+dist+"');" ;
 
//   String sql="INSERT INTO fulldetails"+"(trno,sr,scode,stop,dist) VALUES ('"+trno+"','"+srno+"','"+scode+"','0','"+dist+"');" ;
 
   
   if(srno!=0) dbhelper.run(sql);
            }
         
  
     }
  
  
        
 /*
         dbhelper.run("create table "+temp+" as select * from "+trno+" ORDER BY sr;\n" +
"drop table "+trno+";" +
"alter table "+temp+" rename to "+trno); */
 
      System.out.println("Ended Successfully for "+t);  
     
     
     /*}catch(Exception e)
     {
         System.out.println("Fatal Exception At "+t+" "+e.toString()+"error is for json: "+temps);
         e.printStackTrace();
         dbhelper.run("INSERT INTO newerrors VALUES('"+t+"');");
          dbhelper.run("drop table "+trno+";");
     } */
  
     
     
     
     
     
     
        
    }
    
    
    
    
    public static void oldntesfull(String trno,String tag) throws IOException, SQLException, ParseException, InterruptedException
    {
       String url="https://enquiry.indianrail.gov.in/ntes/NTES?action=getTrainData&trainNo="+trno;
       String txt=stringaturl(url);
       System.out.println("Started for "+trno);
       if(txt.contains("Invalid"))
       {
           return ;
       }
       
       String t=trno;
        i=txt.indexOf("stations:[")+9; 
         
        int plati=txt.indexOf("stations:[",i)+9;  
         if(plati!=8)
         {
             i=plati; //trying to fill missed trains with no running data
         }
         
        j=txt.indexOf("]",i);
         String json= txt.substring(i,j+1);
        
         
          JSONArray ja=new JSONArray(json);
  int count=ja.length();
    JSONObject obj=null;
   /*  DateFormat d = new SimpleDateFormat("HH:mm");
      DateFormat f = new SimpleDateFormat("dd HH:mm"); */
   SimpleDateFormat format = new SimpleDateFormat("HH:mm");
      SimpleDateFormat onlyday = new SimpleDateFormat("dd");
    SimpleDateFormat dayformat = new SimpleDateFormat("dd:HH:mm");
    
   trno=tag+trno;
    String s="CREATE TABLE "+trno+" (sr SMALLINT,"+"scode varchar(12),"+"stop TINYINT,"+
  "arr varchar(10),"+"dep varchar(10),"+"pfno TINYINT,"+"day TINYINT,"+"dist SMALLINT,"+"speed float"+");";
   
  /*
        String s="create TABLE fulldetails (trno SMALLINT,sr SMALLINT,"+"scode varchar(12),"+"stop TINYINT,"+
  "arr varchar(10),"+"dep varchar(10),"+"pfno TINYINT,"+"day TINYINT,"+"dist SMALLINT,"+"speed float"+");";

    */
   dbhelper.run(s);
     
     for (int k=0;k<count;k++) 
     {
          obj = ja.getJSONObject(k);
          
          int srno=obj.getInt("sr");
         double speed=0.0;
          String scode=obj.getString("stnCode");
          String scharr=obj.getString("schArrTime");    if(k==0) scharr="Start";
          String schdep=obj.getString("schDepTime");    if(k==count-1) schdep="End";
         int pfno=obj.getInt("pfNo");
         int day=obj.getInt("schDayCnt")+1;
          int dist=obj.getInt("distance");
          
          
if(k!=count-1){           
Date date1 = format.parse(schdep);
Date date2 = format.parse(ja.getJSONObject(k+1).getString("schArrTime"));
long difference = date2.getTime() - date1.getTime()+86400000*(ja.getJSONObject(k+1).getInt("schDayCnt")-day+1); 
int distval=ja.getJSONObject(k+1).getInt("distance")-dist;
speed=(double)(distval)/difference;  


speed=speed*3600000;}

          
          
         
     String sql="INSERT INTO "+trno+" VALUES ('"+srno+"','"+scode+"',1,'"+scharr+"','"+schdep+"','"+pfno+"','"+day+"','"+dist+"','"+speed+"');" ;
 //trying..forsmall  String sql="INSERT INTO fulldetails"+" VALUES ('"+trno+"','"+srno+"','"+scode+"',1,'"+scharr+"','"+schdep+"','"+pfno+"','"+day+"','"+dist+"','"+speed+"');" ;
  
  if(srno!=0) dbhelper.run(sql);
    
     
     }
   
 /*   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yyyy");  
   LocalDateTime now = LocalDateTime.now();  
   String d=dtf.format(now);  */
 String d=findfrom(t);
   
     url="https://enquiry.indianrail.gov.in/ntes/RailGeo?action=getTrainSchedule&trainStartDate="+d+"&trainNo="+t;
    
     String temps="nothing";
     try{
     txt=Jsoup.connect(url).userAgent(USER_AGENT).maxBodySize(0).header("Accept-Encoding", "gzip, deflate").get().toString();
      temps=txt;
      if(txt.contains("Page Not Found"))
      {
           dbhelper.run("INSERT INTO errors VALUES('"+t+"');");
           dbhelper.run("drop table "+trno+";");
           return ;
      }
      
               
        i=txt.indexOf("[");
       j=txt.lastIndexOf("]");
       json= txt.substring(i,j+1);
       
       
      
       
         ja=new JSONArray(json); count=ja.length();   
         
         for (int k=0;k<count;k++) 
         {
         obj=ja.getJSONObject(k);
         Boolean b=ja.getJSONObject(k).getBoolean("stop");
        
         if(b==false){
           int srno=obj.getInt("sr");
         String scode=obj.getString("stnCode");
          int dist=obj.getInt("distance");
          System.out.println(srno);
       String sql="INSERT INTO "+trno+"(sr,scode,stop,dist) VALUES ('"+srno+"','"+scode+"','0','"+dist+"');" ;
 
//   String sql="INSERT INTO fulldetails"+"(trno,sr,scode,stop,dist) VALUES ('"+trno+"','"+srno+"','"+scode+"','0','"+dist+"');" ;
 
   
   if(srno!=0) dbhelper.run(sql);
            }
         
  
     }
  
  
        
 /*
         dbhelper.run("create table "+temp+" as select * from "+trno+" ORDER BY sr;\n" +
"drop table "+trno+";" +
"alter table "+temp+" rename to "+trno); */
 
      System.out.println("Ended Successfully for "+t);  
     }catch(Exception e)
     {
         System.out.println("Fatal Exception At "+t+" "+e.toString()+"error is for json: "+temps);
         e.printStackTrace();
         dbhelper.run("INSERT INTO newerrors VALUES('"+t+"');");
          dbhelper.run("drop table "+trno+";");
     }
  
       
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static String[] newirftest(String trainno,String searchengine) throws Exception {
         
          String url="nullurl";
        if(searchengine.equals("google")){
            url=googleurl(trainno,"","");  }
        if(searchengine.equals("ddg")){
         url=ddgurl(trainno);
        }
         if(searchengine.equals("direct")){
         url=directsearchurl(trainno);
        }
         if(searchengine.equals("jsongoogle")){
         url=jsongoogle(trainno);
        }
          if(searchengine.equals("directurl")){
         url=trainno;
        }
         if(searchengine.equals("directarray"))
         {
             ArrayList<String> al=directarray(trainno);
           for(String curr:al)
             {
                 String[] s=listedurl(curr,trainno,searchengine);
                 
                 if( s[0].equals("correct"))
                 {
                     url=curr;
                     return s; }
              
             }
         }
         
         
      return listedurl(url,trainno,searchengine);
         
    }  
     
     public static String[] listedurl(String url,String trainno,String searchengine) throws IOException
     {
            
         Document doc = Jsoup.connect(url).userAgent(USER_AGENT).maxBodySize(0).header("Accept-Encoding", "gzip, deflate").get();
     
         String ans[]=new String[5];
        
        String txt=cleantext(doc.text());
        
       if(obtainedtrno(txt).equals(trainno))
       {
          ans[0]="correct";
          
         //  if(doc.text().contains("WARNING: NOT RUNNING"))  ans[0]="wrong"; 
               
       
       
           
       }
       else
       {
         ans[0]="wrong";
       }
        
       ans[1]=obtainedtrname(doc.text(),trainno).trim();
    
        ans[2]=coach(txt).trim();
   
        ans[3]=rakereversal(txt).trim();
      
      if(ans[2]=="" || ans[2]=="[]")
      {
          ans[2]="[not available]";
      }
       if(ans[3]=="" || ans[3]=="[]")
      {
          ans[3]="not cleaned reversal properly";
      }
      
       ans[4]=obtainedrake(doc.text()).trim();
      return ans;
       
 }
     
     
      public static String findfrom(String trno) throws IOException
    {
     String url="https://enquiry.indianrail.gov.in/ntes/FutureTrain?action=getTrainMasterData&trainNo="+trno;
      
         String txt=stringaturl(url);
        
     int i=txt.indexOf("vldFrm:\"");
     int  j=txt.indexOf("\"",i+8);
     String validfrom=txt.substring(i+8,j).trim();
     return validfrom;
    }
      
      
      public static HashMap<String,Integer> pfdata(String txt)
      {
          HashMap<String,Integer>hm=new HashMap();;
          
          try{
         
          
        
          i=txt.indexOf("stations:[")+9; 
          i=txt.indexOf("stations:[",i)+9; 
          j=txt.indexOf("]",i);
         String json= txt.substring(i,j+1);
         
         int pos=0; 
        while(json.substring(pos).contains("stnCode:"))
         {
             
             
            int tempi=json.indexOf("stnCode:",pos)+8;
            int  tempj=json.indexOf("\"",tempi+1);
             
             String stn=json.substring(tempi+1,tempj);  
             
             
             
             tempi=json.indexOf("pfNo:",pos)+5;
            
              tempj=json.indexOf("}",tempi);
             Integer pf=Integer.valueOf(json.substring(tempi,tempj));
             pos=tempj;
             
           hm.put(stn,pf);
         
         }
        
          }catch(Exception e){};
         
         
         
         return hm;
          
      }

      
      
      
      public static HashMap <String,Integer>[] analysed(String trno) throws Exception
      {
          
          HashMap<String,Integer> op[]=new HashMap[2];
          
           HashMap<String,Integer>hmarr=new HashMap();
            HashMap<String,Integer>hmdep=new HashMap();
           String startdate="";
          
            SimpleDateFormat h = new SimpleDateFormat("dd/MM/yyyy");
            h.setTimeZone(TimeZone.getTimeZone("IST"));
            
            
               Calendar cal  = Calendar.getInstance();
            
             int count=0;
            for(int i=0;i<15;i++)
            {
                
                 
                startdate= h.format(new Date(cal.getTimeInMillis()-i*86400000));
          
                String url="https://enquiry.indianrail.gov.in/ntes/NTES?action=getTrainForDate&trainNo="+trno+"&trainStartDate="+startdate;
                String txt=stringaturl(url);
              
                if(txt.contains("terminated:true"))
                {
                   // System.out.println(url);
                    
                    
          int newi=txt.indexOf("stations:[")+9; 
         
          int newj=txt.indexOf("]",newi);
         String json= txt.substring(i,newj+1);
         
         int pos=0; 
        
        while(json.substring(pos).contains("stnCode:"))
         {
             
             
            int tempi=json.indexOf("stnCode:",pos)+8;
            int  tempj=json.indexOf("\"",tempi+1);
             
             String stn=json.substring(tempi+1,tempj);  
             
             
             
             tempi=json.indexOf("delayArr:",pos)+9;
             tempj=json.indexOf(",",tempi);
             Integer darr=Integer.valueOf(json.substring(tempi,tempj));
             int optdarr=darr;
             
             if(hmarr.containsKey(stn))  {
            //      System.out.println("i got "+hmarr.get(stn)+" for "+stn+" and cnt was"+count);
                 optdarr=(count*hmarr.get(stn)+darr)/(count+1);
             }
             
             hmarr.put(stn,optdarr);
             
             // System.out.println(stn+" "+darr);
              
             
             
              tempi=json.indexOf("delayDep:",pos)+9;
             tempj=json.indexOf(",",tempi);
             Integer ddep=Integer.valueOf(json.substring(tempi,tempj));
             int optddep=ddep;
             
              if(hmdep.containsKey(stn))
              {
                 // System.out.println("i got "+hmdep.get(stn)+" for "+stn+" and cnt was"+count);
                  optddep=(count*hmdep.get(stn)+ddep)/(count+1);
              }
              
             hmdep.put(stn,optddep);
           //   System.out.println(stn+" "+ddep);
           
           
           
           pos=tempj;
           
           
           
         
         }
                 count++; 
                    
                }
                
       
       
            }
             op[0]=hmarr;
             op[1]=hmdep;
          
           //  System.out.println(Arrays.asList(op));
            
       return op;
       
       
       
      }
      
      
      public static String[] route(String trno) throws IOException, SQLException
      {
        //  List<String> op=new ArrayList();
          String ops[]=new String[2];
           String d=findfrom(trno);
           
           String oscode="";
           String odist="";
   
     String url="https://enquiry.indianrail.gov.in/ntes/RailGeo?action=getTrainSchedule&trainStartDate="+d+"&trainNo="+trno;
    
     String txt=Jsoup.connect(url).userAgent(USER_AGENT).maxBodySize(0).header("Accept-Encoding", "gzip, deflate").get().toString();
      int i=txt.indexOf("[");
       int j=txt.lastIndexOf("]");
       String json= txt.substring(i,j+1);
       
       //fSystem.out.println(json);
       
        JSONArray ja=new JSONArray(json); int count=ja.length();    
        JSONObject obj;
         for (int k=0;k<count;k++) 
         {
         obj=ja.getJSONObject(k);
       /*  Boolean b=ja.getJSONObject(k).getBoolean("stop");
        
         if(b==false){
           int srno=obj.getInt("sr");
         String scode=obj.getString("stnCode");
          int dist=obj.getInt("distance");
         // System.out.println(srno);
       String sql="INSERT INTO "+trno+"(sr,scode,stop,dist) VALUES ('"+srno+"','"+scode+"','0','"+dist+"');" ;
        if(srno!=0) dbhelper.run(sql);
            }  */
       
                 String scode=obj.getString("stnCode");
          int dist=obj.getInt("distance");
        //  ops=ops+scode+":"+dist+",";
        oscode=oscode+scode+",";
        odist=odist+dist+",";
        
        
        
        
         }
         
       //  if(ops.substring(ops.length() - 1).equals(":"))   ops=ops.substring(0,ops.length()-1);
         
  
     ops[0]=oscode;
        ops[1]=String.valueOf(odist);
       
      return ops;
      }
      



}


