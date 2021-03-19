/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my;

import cris.icms.ntes.htmlmain;
import static cris.icms.ntes.htmlmain.USER_AGENT;
import static cris.icms.ntes.htmlmain.cleantext;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import jakarta.servlet.ServletContext;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author work
 */
public class ntesweb {
    
    public static String stlist=null;
    
    public static String validatetrno(String trno) throws IOException
    {
    /*    String url="https://enquiry.indianrail.gov.in/ntes/SearchTrain?trainNo="+trno;
         Document doc = Jsoup.connect(url).maxBodySize(0).header("Accept-Encoding", "gzip, deflate").get();
    String txt=doc.text();
    int i=txt.indexOf("success:")+8;
    int j=txt.indexOf(",",i);
    return txt.substring(i,j).trim();  */
        return "true"; //testing since new use of this
    }
    
    public static String allstationdetails(String trno) throws IOException
    {
    /*    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yyyy");  
   LocalDateTime now = LocalDateTime.now();  
   String d=dtf.format(now);  //can choose any future date also(min as from date) */
   
   String d= findfrom(trno);
   
        String url="https://enquiry.indianrail.gov.in/ntes/RailGeo?action=getTrainSchedule&trainStartDate="+d+"&trainNo="+trno;
  
        
  /*      Document doc = Jsoup.connect(url).maxBodySize(0).header("Accept-Encoding", "gzip, deflate").get();
    String txt=doc.text();  */
    //trying..
    String txt=stringaturl(url);
    
    int i=txt.indexOf("[");
    int j=txt.lastIndexOf("]");
    String jsonarr= txt.substring(i,j+1);
    
    String removals[]={"arrTime","depTime","arrDayCnt","dayCnt"};
     String output=removejsonarr(jsonarr,removals);
     
    return output;
    }
    
    public static String[] maindetails(String trno) throws IOException
    {
        String url="https://enquiry.indianrail.gov.in/ntes/NTES?action=getTrainData&trainNo="+trno;
 /*   Document doc = Jsoup.connect(url).maxBodySize(0).header("Accept-Encoding", "gzip, deflate").get();
    String txt=doc.text();  */
 
 //trying...
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
     
     
      i=txt.indexOf("dayCnt:");
      j=txt.indexOf(",",i+7);
     String daycount=txt.substring(i+7,j).trim();
   
         int c=Integer.parseInt(daycount);
        daycount=String.valueOf(c+1);
    
     
     
     
     
     i=txt.indexOf("stations:[")+9;
     
     i=txt.indexOf("stations:[",i)+9;   //testing for upcoming date so that platform no.s are got
     
     
     
     
     j=txt.indexOf("]",i);
    String jsonarr= txt.substring(i,j+1);
    String r[]={"arrDayCnt"};
   jsonarr=removejsonarr(jsonarr,r);
   
   //cleaning 
    JSONArray ja=new JSONArray(jsonarr);
  int count=ja.length();
    JSONObject obj=null;
     for (int k=0;k<count;k++) 
     {
          obj = ja.getJSONObject(k);
          
          
          
         if(k==0) obj.put("schArrTime", "Start");  //can put - instead  //actual:arrtime
          if(k==count-1) obj.put("schDepTime", "Destination");   //actual:deptime
          int dc=obj.getInt("dayCnt");
          obj.put("dayCnt", dc+1);
             
     }
     jsonarr=ja.toString();
    
     
    
    
    String output[]=new String[7];
    output[0]=jsonarr;
    output[1]=traintype;
    output[2]=trainname;
    output[3]=runson;
    output[4]=validity;
    output[5]=daycount;
    if(!(traintype.contains("SP") || traintype.contains("PEXP")))
    {
     output[6]="Train is Totally Cancelled Because of COVID-19";
    }
    else
    {
        
        
    }
    return output;
    
    }
    
    
    
    
    public static String runningdetails(String startdate,String trainno) throws IOException
    {
         String url="https://enquiry.indianrail.gov.in/ntes/NTES?action=getTrainForDate&trainStartDate="+startdate+"&trainNo="+trainno;
    Document doc = Jsoup.connect(url).maxBodySize(0).header("Accept-Encoding", "gzip, deflate").get();
    String txt=doc.text();
     int i=txt.indexOf("rakes:[")+7;
     int j=txt.indexOf("]",i);
    String jsonarr= txt.substring(i,j+1);
    String r[]={"arrDayCnt"};
 //  jsonarr=removejsonarr(jsonarr,r);
 return jsonarr;
    }
    
    
    
    
    
    
    
    
    
    
    
    public static String removejsonarr(String jsonarr,String[] keystoremove)
    {
         JSONArray ja=new JSONArray(jsonarr);
         JSONArray jnew=new JSONArray();
         JSONObject obj=null;
        for (int j=0;j<ja.length();j++) 
     {
        
    obj = ja.getJSONObject(j);
       
    for(String key:keystoremove){
    obj.remove(key);
  //   System.out.println("removed "+key+" at "+j+"of length "+ja.length());
    } 
  jnew.put(obj);
 
     } return jnew.toString(); }
    
    
    
    
    public static String[] fulldetails(String trno) throws Exception
    {
         
                          
        String[] o=maindetails(trno);
        
         String mainjson=o[0];
       
        System.out.println(" main details done & all station details started: "+new Date().toString());
          
        String allstjson=allstationdetails(trno);
        
        
         System.out.println("all station details done and merge started: "+new Date().toString());
          
        
                      
          
        
       o[0]=mergejson(mainjson,allstjson);
        
        System.out.println("merge done and starting fill intermediate: "+new Date().toString());
          
       
        
      o[0]=fillintermediate(mergejson(mainjson,allstjson));
        
        
        System.out.println("ended filling intermediate: "+new Date().toString());
       
        return o;
    }
    
    public static String[] mixeddetails(String trno) throws Exception
    {
        System.out.println(" started: "+new Date().toString());
       
       String[]op=new String[11];   //from 10 to 11
       
       String temp[]=fulldetails(trno);
        for(int i=0;i<7;i++)
        {
            op[i]=temp[i];
        }
       
         String irf[]=htmlmain.newirftest(trno);
         if(irf[5].equals("correct")){
         op[2]=irf[7];  //new trainname from irf
        op[7]=irf[2];  //coach data
        op[8]=irf[3];  //rake reversal
        op[9]=irf[6];  //got by whom? in irf
        
        
         op[10]=irf[8];  //which rake? added newly
         }
        return op;   //op[0] is json,1 is traintype,3 is runson,4 is validity,5 is daycount
    }
    
    
    
    public static String[] analysedmixed(String trno) throws Exception
    {
         String[]op=new String[15];
        for(int i=0;i<10;i++)
        {
            op[i]=mixeddetails(trno)[i];
        }
        //op[10] avg delay,11-speed
        //should add avg delay for each stn of trno
        
        return null;
    }
    
    
    public static  String  mergejson(String main,String all)
    {
        
        JSONArray jm=new JSONArray(main);
        JSONArray jall=new JSONArray(all);
        JSONObject mobj=null;
         JSONObject allobj=null;
        
        for(int i=0;i<jall.length();i++)
        {
             allobj= jall.getJSONObject(i);
             String allstcode=allobj.getString("stnCode").trim();
             
             //default values not in all but in main
              allobj.put("pfNo","-");
               allobj.put("arrTime","-");
                allobj.put("depTime","-");
                allobj.put("dayCnt","-");
                allobj.put("speed","-");
                
             for(int j=0;j<jm.length();j++)
             {
              mobj = jm.getJSONObject(j);
             String mstcode=mobj.getString("stnCode").trim();
            
             if(allstcode.equals(mstcode)){
                 allobj.put("pfNo",mobj.getInt("pfNo"));
                  allobj.put("arrTime",mobj.getString("schArrTime"));  //actual:arrTime
                   allobj.put("depTime",mobj.getString("schDepTime"));   //actual:depTime
                   allobj.put("dayCnt",mobj.getInt("dayCnt"));
             }
             
             }
        }
         
        
        String op=jall.toString();
        return op;
    }
    
    public static String fillintermediate(String all) throws ParseException, IOException
    {
        //filling speed,daycount for intermediates,
          JSONArray jall=new JSONArray(all);
        JSONObject allobj=null;
        
        //trying to fill stops to save time
        
        
        System.out.println("fill init "+new Date().toString());
        
          String stopsdata="";
          for(int i=0;i<jall.length();i++){
              int val = (jall.getJSONObject(i).getBoolean("stop")) ? 1 : 0;
             stopsdata=stopsdata+val;
          }
        
        
        
        
         for(int i=0;i<jall.length();i++)
        {
            
            System.out.println("step "+i+new Date().toString());
            
            allobj= jall.getJSONObject(i);
            // allobj.put("speed","-");
             
             int distance=allobj.getInt("distance");
             
             //trying..
           String c=ntesweb.getstnname(allobj.getString("stnCode"));
             allobj.put("stationname", c);  
          
             
             if(!allobj.getBoolean("stop"))
             {
            String m=jall.getJSONObject(i).getString("stnCode");
            
  //original     
  /*   int[] temp=prevnext(all,i);
           JSONObject prev=jall.getJSONObject(temp[0]);
            JSONObject next=jall.getJSONObject(temp[1]);  */
            
  
            int nextint=stopsdata.indexOf('1',i);
            int prevint=stopsdata.lastIndexOf('1',i);
            
            
            
            System.out.println("step "+i+new Date().toString());
            
   JSONObject prev=jall.getJSONObject(prevint);
              JSONObject next=jall.getJSONObject(nextint);
   
   
              
              
            System.out.println("step "+i+new Date().toString());
            
               SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                SimpleDateFormat onlyday = new SimpleDateFormat("dd");
                SimpleDateFormat dayformat = new SimpleDateFormat("dd:HH:mm");
Date date1 = format.parse(prev.getString("depTime"));
Date date2 = format.parse(next.getString("arrTime"));
long difference = date2.getTime() - date1.getTime()+86400000*(next.getInt("dayCnt")-prev.getInt("dayCnt")); 
int dist=next.getInt("distance")-prev.getInt("distance");

double speed=(double)(dist)/difference;   



            System.out.println("step "+i+new Date().toString());

   
                if(difference!=0){
               allobj.put("speed",String.valueOf((int)(speed*3600000)));}
             
                
                
            System.out.println("step "+i+new Date().toString());
               
               long difftime= (long)((distance-prev.getInt("distance"))/speed);
               
               long reqtime=difftime+date1.getTime()+(prev.getInt("dayCnt")-1)*86400000 ;
               
               Date result = new Date(reqtime); 
               
               
              allobj.put("arrTime",format.format(result));
               allobj.put("depTime",format.format(result)); //can try dayformat.format
               String s=onlyday.format(result);
                allobj.put("dayCnt",Integer.parseInt(s));
                
                
            System.out.println("step "+i+new Date().toString());
            } else
             {
                 //filling speed for stops
                if(i!=jall.length()-1){
                    int j;
                    for(j=i+1;jall.getJSONObject(j).getBoolean("stop")==false;j++){}
                   JSONObject next=jall.getJSONObject(j);
                   JSONObject curr=jall.getJSONObject(i);
                   
                   
            System.out.println("step "+i+new Date().toString());
                   
               SimpleDateFormat format = new SimpleDateFormat("HH:mm");
Date date1 = format.parse(curr.getString("depTime"));
Date date2 = format.parse(next.getString("arrTime"));
long difference = date2.getTime() - date1.getTime()+86400000*(next.getInt("dayCnt")-curr.getInt("dayCnt")); 
int dist=next.getInt("distance")-curr.getInt("distance");
double speed=(double)(dist)/difference;


            System.out.println("step "+i+new Date().toString());

 if(difference!=0){
allobj.put("speed",String.valueOf((int)(speed*3600000)));
 }
                }
              
                 
             }
             
        }
         
           
         String op=jall.toString();
        return op;
    }
    
    
    public static int[] prevnext(String all,int given)
    {
         System.out.println("before "+java.time.LocalTime.now());  
        int[] op=new int[]{-1,-1};
          JSONArray jall=new JSONArray(all);
          int l=jall.length();
        JSONObject allobj=null;
        if(!jall.getJSONObject(given).getBoolean("stop")==true)
        {  
            //next
          int i;
           for(i=given;jall.getJSONObject(i).getBoolean("stop")==false&&i!=l-1;i++){}
             op[1]=i;
           //prev
           for(i=given;jall.getJSONObject(i).getBoolean("stop")==false&&i!=0;i--){}
            op[0]=i;      }
         System.out.println("after "+java.time.LocalTime.now());  
        return op;
        
    }
    
    
    
    
    
    
    
    public static String getstnname(String scode) throws  IOException
    {
        String json;
        if(stlist==null){

        
String url="https://intellis.herokuapp.com/stations.json" ;
    /*      Document doc = Jsoup.connect(url).maxBodySize(0).header("Accept-Encoding", "gzip, deflate").ignoreContentType(true).get();
     stlist=doc.text(); */
    stlist=stringaturl(url);
        }
        
        
json=stlist;
        
    JSONObject jo=new JSONObject(json);
    String op="-";
    try{
       JSONObject ans=jo.getJSONObject(scode);
       op=ans.getString("stationname");
     
       
    }catch(Exception e)
    {
        System.out.println(e.toString());
       
    }
          return op;      

    }
   
    
    
    public static String[] ntesstatus(String trno,String startdate) throws IOException, ParseException
    {
        String url="https://enquiry.indianrail.gov.in/ntes/NTES?action=getTrainForDate&trainNo="+trno+"&trainStartDate="+startdate;
       String s=stringaturl(url);
       
       String op[]=new String[12];
       
          String q="curStn:\"";
          int i=s.indexOf(q)+q.length();
          String stn=s.substring(i,s.indexOf("\"", i)).trim(); op[0]=stn;
          
         q="departed:";
          i=s.indexOf(q)+q.length();
         String started=s.substring(i,s.indexOf(",", i)).trim(); op[1]=started;
         
           q="terminated:";
          i=s.indexOf(q)+q.length();
         String ended=s.substring(i,s.indexOf(",", i)).trim(); op[2]=ended;
         
         q="lastUpdated:\"";
         i=s.indexOf(q)+q.length();
          String updatetime=s.substring(i,s.indexOf("\"", i)).trim(); op[3]=updatetime;
          
          q="cncldFrmStn:\"";
         i=s.indexOf(q)+q.length();
          String cancelledfrom=s.substring(i,s.indexOf("\"", i)).trim(); op[4]=cancelledfrom;
          
           q="cncldToStn:\"";
         i=s.indexOf(q)+q.length();
          String cancelledto=s.substring(i,s.indexOf("\"", i)).trim(); op[5]=cancelledto;
          
          q="stnCode:\""+stn;
          i=s.indexOf(q);
          String search=s.substring(i,s.indexOf("}", i)).trim(); q="arr:";
          i=search.indexOf(q)+q.length();
          String crntarr=search.substring(i,search.indexOf(",", i)).trim(); op[6]=crntarr; q="dep:";
          i=search.indexOf(q)+q.length();
          String crntdep=search.substring(i,search.indexOf(",", i)).trim(); op[7]=crntdep;
          
          
           q="delayArr:";
          i=search.indexOf(q)+q.length();
         String delayarr=search.substring(i,search.indexOf(",", i)).trim(); op[8]=delayarr;
         
         q="delayDep:";
          i=search.indexOf(q)+q.length();
         String delaydep=search.substring(i,search.indexOf(",", i)).trim(); op[9]=delaydep;
         
         String uptext="";
         if(crntdep.equals("true"))
         {
             uptext="Departed "+stn;
         }
         else
         {
             uptext="Arrived "+stn;
         }
          
         SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy HH:mm");
         format.setTimeZone(TimeZone.getTimeZone("IST"));
        
          Date dupdt=format.parse(updatetime); Date date = new Date();
        long updt= TimeUnit.MILLISECONDS.toMinutes(date.getTime()-dupdt.getTime());
        op[11]=String.valueOf(updt);
        String updttext="";
        
        
        if(updt==0)
        {
            updttext="Updated Just Now";
        }
        else 
        {
            
           updttext="Updated "+formatmnts("days",updt)+" ago";}
        
         uptext=uptext+" ("+updttext+")";
         op[10]=uptext;
         
         
          
          
          
          
         
        
        
        return op;
    }
    
    
    public static String[] wmtstatus(String trno,String startdate) throws IOException, ParseException
    {
        
        
        String url="https://whereismytrain.in/cache/live_status?date="+startdate+"&appVersion=1.1&from_day=1&wid=&train_no="+trno+"&lang=en&user=g&sb_version=0&flow=refresh";
       String op[]=new String[8];
         String s=stringaturl(url);
         if(s.contains("\"lastUpdated"))
         {
             
         String q="\"curStn\": \"";
         int i=s.indexOf(q)+q.length();
          String stn=s.substring(i,s.indexOf("\"", i)).trim();
          
          q="departedCurStn\": ";
          i=s.indexOf(q)+q.length();
          String departed=s.substring(i,s.indexOf(",", i)).trim();
          
           q="curStnDistance\": ";
          i=s.indexOf(q)+q.length();
          String distance=s.substring(i,s.indexOf(",", i)).trim();
          
           q="\"lastUpdated\": \"";
         i=s.indexOf(q)+q.length();
          String updatetime=s.substring(i,s.indexOf("\"", i)).trim();
        
        op[1]=stn;op[2]=departed;op[3]=distance;op[4]=updatetime;
        
        
        
        
        
         q="\"station_code\": \""+stn;
         i=s.indexOf(q);
         int f=s.lastIndexOf("{",i); int l=s.indexOf("}], \"sb\"",i);
         String search=s.substring(f,l);
         q="actual_departure_time\": \"";
          System.out.println(f+"my"+l);
         i=search.indexOf(q)+q.length();
         String actdep=search.substring(i,search.indexOf("\"", i)).trim();
         
         
         
         
          q="actual_arrival_time\": \"";
         i=search.indexOf(q)+q.length();
         String actarr=search.substring(i,search.indexOf("\"", i)).trim();
         q="sch_departure_time\": \"";
         i=search.indexOf(q)+q.length();
         String schdep=search.substring(i,search.indexOf("\"", i)).trim();
         q="sch_arrival_time\": \"";
         i=search.indexOf(q)+q.length();
         String scharr=search.substring(i,search.indexOf("\"", i)).trim();
         
         q="actual_departure_date\": \"";
         i=search.indexOf(q)+q.length();
         String actdepdate=search.substring(i,search.indexOf("\"", i)).trim();
          q="actual_arrival_date\": \"";
         i=search.indexOf(q)+q.length();
         String actarrdate=search.substring(i,search.indexOf("\"", i)).trim();
         q="sch_departure_date\": \"";
         i=search.indexOf(q)+q.length();
         String schdepdate=search.substring(i,search.indexOf("\"", i)).trim();
         q="sch_arrival_date\": \"";
         i=search.indexOf(q)+q.length();
         String scharrdate=search.substring(i,search.indexOf("\"", i)).trim();
         
         SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy HH:mm");
         format.setTimeZone(TimeZone.getTimeZone("IST"));
         
        
         
         Date dactdep = format.parse(actdepdate+" "+actdep);
         Date dschdep = format.parse(schdepdate+" "+schdep);
        // op[6]= String.valueOf(TimeUnit.MILLISECONDS.toMinutes(dactdep.getTime()-dschdep.getTime()));
        //trying only actual instead of delay bcs of wmt errors
        op[6]=format.format(dactdep);
        
         Date dactarr = format.parse(actarrdate+" "+actarr);
         Date dscharr = format.parse(scharrdate+" "+scharr);
       //  op[5]= String.valueOf(TimeUnit.MILLISECONDS.toMinutes(dactarr.getTime()-dscharr.getTime()));
          op[5]=format.format(dactarr);
        
        
        Date dupdt=format.parse(updatetime); Date date = new Date();
        long updt= TimeUnit.MILLISECONDS.toMinutes(date.getTime()-dupdt.getTime());
        String updttext;
        
        op[7]=String.valueOf(updt);
        
        if(updt==0)
        {
            updttext="Updated Just Now";
        }
        else 
        {
            
           updttext="Updated "+formatmnts("days",updt)+" ago";
           
          // updttext=String.valueOf(updt);
        }
        
        
                
                
            if(departed.equals("false"))
            {
                op[0]="Arrived "+stn+"  ("+updttext+")";
            }
             if(distance.equals("0.0") && departed.equals("true"))
            {
                 op[0]="Departed "+stn+"  ("+updttext+")";
            }
              if(!distance.equals("0.0") && departed.equals("true"))
            {
                //trying.. 
               distance=String.format("%.02f",Float.valueOf(distance));
                
                
                
                 op[0]=distance+" Kms From "+stn+"  ("+updttext+")";
            }
             
        
         }
         else
         {
             op[0]="not available";
         }
         return op;
        // return op[0];
    }
    
    
    
    
    public static String stringaturl(String url) throws IOException {
      
        
        url=url.replaceAll(" ","%20"); //to avoid space bugs
        
      /*  StringBuilder response = new StringBuilder();
        URL u = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) u.openConnection();
        
        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK)
        {
            BufferedReader input = new BufferedReader(new InputStreamReader(conn.getInputStream()),8192);
            String line = null;
            while ((line = input.readLine()) != null)
            {
                response.append(line);
            } input.close();}
        
         
        
        return response.toString(); */


        
        //trying..
    
    Document doc = Jsoup.connect(url).sslSocketFactory(socketFactory()).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.190 Safari/537.36").maxBodySize(0).header("Accept-Encoding", "gzip, deflate").timeout(10000).get();
    String txt=doc.text(); 
    return txt;
    
    
    }
    
    
    
    
      private static SSLSocketFactory socketFactory() {
    TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
      public java.security.cert.X509Certificate[] getAcceptedIssuers() {
        return null;
      }

      public void checkClientTrusted(X509Certificate[] certs, String authType) {
      }

      public void checkServerTrusted(X509Certificate[] certs, String authType) {
      }
    }};

    try {
      SSLContext sslContext = SSLContext.getInstance("TLS");
      sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
      return sslContext.getSocketFactory();
    } catch (Exception e) {
      throw new RuntimeException("Failed to create a SSL socket factory", e);
    }
  }
    
    public static String formatmnts(String type,long value)
    {
        String op=null;
        if(type.toLowerCase().equals("minutes"))
        {
           // System.out.println("Im called for "+value);
            
            if(value==0) op="";
            else if(value==1) op="1 Minute";
            else op=String.valueOf(value%60)+" Minutes";
        }
        
         if(type.toLowerCase().equals("hours"))
        {
             if(value/60==0) op=formatmnts("minutes",value);
             else if(value/60==1) op="1 Hour"+" "+formatmnts("minutes",value);
             else 
               {
                   long x=(value/60)%24;
                if(x>0) op=String.valueOf(x+" Hours"+" "+formatmnts("minutes",value));
                else op=formatmnts("minutes",value);
             }
        }
         
          if(type.toLowerCase().equals("days"))
        {
             if(value/1440==0) op=formatmnts("hours",value);
             else if(value/1440==1) op="1 Day"+" "+formatmnts("hours",value);
             else op=String.valueOf(value/1440)+" Days"+" "+formatmnts("hours",value);
        }
        
        return op.trim();
    }
    
    public static String livestn(String src,String dest,int hrs) throws Exception
    {
        if(hrs==0) hrs=8;
        
     String url="https://enquiry.indianrail.gov.in/ntes/NTES?action=getTrainsViaStn&viaStn="+src+"&toStn="+dest+"&withinHrs="+hrs+"&trainType=ALL";
        
        String s=stringaturl(url);
        int i=s.indexOf("allTrains:[");  //int j=s.indexOf("]");
        s=s.substring(i);
        
        JSONArray ja=new JSONArray();
        
        
        int c=0;
        while(s.substring(c).contains("trainNo:"))
        {
            int c1=s.indexOf("trainNo:",c)+9;
            int c2=s.indexOf("\"",c1);
            String trno=s.substring(c1,c2);
            
            
            c1=s.indexOf("startDate:",c)+11;
            c2=s.indexOf("\"",c1);
            String start=s.substring(c1,c2);
             SimpleDateFormat nnf = new SimpleDateFormat("dd MMM yyyy");
              SimpleDateFormat fn = new SimpleDateFormat("dd-MM-yyyy");
             
             
              nnf.setTimeZone(TimeZone.getTimeZone("IST"));
              
               Date result = nnf.parse(start);
                String st=fn.format(result);
               
               
               Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("IST"));
               Date now=cal.getTime();
               String n=nnf.format(now);
               now=nnf.parse(n);
               
               
              long  dms=(now.getTime()-result.getTime());
               
    int d=(int)(dms/86400000);
               
              if(d==0) start="Today";
              else if(d==-1) start="Tomorrow";
              else if(d==1) start="Yesterday";
              else  if(d==2) start="Day Before Yesterday";
              else  if(d==-2) start="Day After Tomorrow";
              else start=d+" Days back";  
            
            
            
             c1=s.indexOf("pfNo:",c)+6;
            c2=s.indexOf("\"",c1);
            String pfno=s.substring(c1,c2);
            if(pfno.equals("0")) pfno="-";
            
            
           // String lastupdt=optimisedstatus(trno,st)[5];
            
            JSONObject jo=new JSONObject();
            jo.put("trno",trno);
            jo.put("st",start);
            jo.put("pf",pfno);
            jo.put("stint",st);
           //  jo.put("lastupdt",lastupdt);
             ja.put(jo);
            
            
            c=c2;
               
        }
        
       
        
        
        
        
        return ja.toString();
        
        
        
        
        
        
    }
    public static String findindex(String t,String scode,String d) throws IOException
    {
        
         String url="https://enquiry.indianrail.gov.in/ntes/RailGeo?action=getTrainSchedule&trainStartDate="+d+"&trainNo="+t;
   
     String txt=Jsoup.connect(url).userAgent(USER_AGENT).maxBodySize(0).header("Accept-Encoding", "gzip, deflate").get().toString();
    
     int i=txt.indexOf(",stnCode:\""+scode+"\"");
     int j=txt.lastIndexOf("sr:",i)+4;
      return txt.substring(j-1,i);
     
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
    
  
    
  
    
    
    
    
    
    
    
    
    
    
    
    
    public static String[] optimisedstatus(String trainno,String date) throws Exception
    {
        
        
          /*         SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                    Date result = formater.parse(date);
             
       
       SimpleDateFormat newFormater = new SimpleDateFormat("dd-MM-yyyy");
               date=newFormater.format(result); */
                   String op[]=new String[6];
                   SimpleDateFormat f = new SimpleDateFormat("dd MM yyyy hh:mm");
                     SimpleDateFormat nf = new SimpleDateFormat("dd-MM-yyyy");
                     SimpleDateFormat nnf = new SimpleDateFormat("dd MMM yyyy");
                     SimpleDateFormat h = new SimpleDateFormat("dd/MM/yyyy");
                     
                       Date result = nf.parse(date);
                   date= h.format(result);
                     
                String ntes[]=ntesweb.ntesstatus(trainno,date);
                  
               // String ndate=nnf.format(result);
               String wmt[]=wmt =ntesweb.wmtstatus(trainno,nf.format(result));
              
              /*  Date n=f.parse(ntes[3]); 
                 Date w=f.parse(wmt[4]);
                 
                 op[0]=wmt[0];
                if(n.getTime()>w.getTime())
                {
                    op
                    
                } */
             int n=Integer.valueOf(ntesweb.findindex(trainno,ntes[0],nnf.format(result)));
             int w=0;
             try{
              w=Integer.valueOf(ntesweb.findindex(trainno,wmt[1],nnf.format(result)));
             }catch(Exception e){
             System.out.println("explicit error at "+trainno+nnf.format(result));
             };
              
            //   System.out.println(Float.parseFloat(wmt[3]));
            
               if(w>n)
              {
                  op[0]=wmt[0];
                  op[1]=wmt[1];
                // op[1]=String.valueOf(w);
                   op[2]=wmt[3];
                   
                   
                   System.out.println("wmt");
                   op[5]=wmt[7];
              }
               else if(w==n && Float.parseFloat(wmt[3])>0)
              {
               op[0]=wmt[0];
                op[1]=wmt[1];
           //     op[1]=String.valueOf(w);
                 op[2]=wmt[3];
                System.out.println("wmt");
                op[5]=wmt[7];
                
              }else
              {
                  op[0]=ntes[10];
                   op[1]=ntes[0];
                 //  op[1]=String.valueOf(n);
                   if(ntes[7].equals("false"))
                    op[2]="0";
                   else
                    op[2]="onlyarr";
                    System.out.println("ntes");
                    op[5]=ntes[11];
              }
                op[3]=ntes[4];
                op[4]=ntes[5];
                
                
                return op;
    }
        
        
        
        
    



  public static String getdelayatstn(String trno,String src,String lastupdt)
    {
        
        
        
return null;        
    }
  
  public static String findtimeat(String stn,String trno,String startdate)
  {
      
      return  null;
      
  }

    
    }



    
    
    

