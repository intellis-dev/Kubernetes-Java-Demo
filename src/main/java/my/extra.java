/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import static my.optimised.irfmin;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author work
 */
public class extra {
    
    public static void main(String args[]) throws Exception
    {
     //   System.out.println(translate("hyderabad Deccan Nampally","bengali"));
        System.out.println(pnrstatus("2640613980"));
            
        
        
    }
    
    public static String pnrstatus(String pnr) throws Exception
    {
           String op="";
           URL o = new URL("https://railsinfo-services.makemytrip.com/api/rails/pnr/currentstatus/v1");
        HttpURLConnection con = (HttpURLConnection) o.openConnection();
        con.setRequestMethod("POST");
         con.setConnectTimeout(60 * 1);
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setDoOutput(true);
        
        String json="{\"pnrID\":\""+pnr+"\",\"trackingParams\":{\"affiliateCode\":\"MMT001\",\"channelCode\":\"ANDROID\"}}";
        json.replaceAll("\"","");
        
 DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(json);
        wr.flush();
        wr.close();
       
         if (con.getResponseCode() != 200) {
     return null;
 }
         
         BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String output;


        StringBuffer response = new StringBuffer();

        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();
        String r=String.valueOf(response);

        if(r.contains("Error"))
        {
            return null;
        }
        JSONObject jo = new JSONObject(r);
        
        
        int k=r.indexOf("Train\":{\"Number\":\"")+18;
        String trno=r.substring(k,k+5);
        op=op+","+trno;
        
         k=r.indexOf("\"Name\":\"",k)+8;
         int l=r.indexOf("\"",k);
         String trname=r.substring(k,l);
        
       try{  String ir[]=irfmin(trno);
          if(ir[0].equals("correct"))  trname=ir[1]; }catch(Exception ee){};
        
          op=op+","+trname;
              
        
       
       k=r.indexOf("BoardingPoint");
       k=r.indexOf("code\":\"",k)+7;
        l=r.indexOf("\"",k);
       String src=r.substring(k,l);
        op=op+","+src;
        
          k=r.indexOf("ReservationUpto");
       k=r.indexOf("code\":\"",k)+7;
        l=r.indexOf("\"",k);
       String dest=r.substring(k,l);
        op=op+","+dest;
        
         k=r.indexOf("DepartureTime\":\"")+16;
         l=r.indexOf("\"",k);
       String dep=r.substring(k,l);
        op=op+","+dep;
        
         k=r.indexOf("ArrivalTime\":\"")+14;
         l=r.indexOf("\"",k);
       String arr=r.substring(k,l);
        op=op+","+arr;
        
         k=r.indexOf("Duration\":\"")+11;
         l=r.indexOf("\"",k);
       String time=r.substring(k,l);
        op=op+","+time;
        
      k=r.indexOf("TrainCancelledFlag\":")+20;
         l=r.indexOf(",",k);
       String canc=r.substring(k,l);
        op=op+","+canc;
        
         k=r.indexOf("\"ChartPrepared\":")+16;
         l=r.indexOf("}",k);
       String chart=r.substring(k,l);
        op=op+","+chart;
        
        
         k=r.indexOf("SourceDoj");
       k=r.indexOf("Epoch\":",k)+7;
        l=r.indexOf("}",k);
       String day=r.substring(k,l);
        op=op+","+day;
        
        
    //    JSONArray ja=jo.getJSONObject("PassengerDetails").getJSONArray("PassengerStatus");
    k=r.indexOf("PassengerStatus\":")+17;
    l=r.indexOf("]", k);
    String js=r.substring(k,l+1);
    System.out.println(js);
    JSONArray ja=new JSONArray(js);
     
        for(int i=0;i<ja.length();i++)
        {
            JSONObject j=ja.getJSONObject(i);
            String curst=j.getString("CurrentStatus");
           // as.add(curst);
           op=op+","+curst;
        }
        if(op.charAt(0)==',')
        {
            op=op.substring(1);
        }
        
       
        return op.trim();
        
    }
    
    
    
  
    
    
    public static String translate(String txt,String lang) throws Exception
    {
          
String data = "word="+txt+"&action=translate&lang="+lang+"&type=from_en";
 URL o = new URL("http://app.wordhippo.com/what-is/process-form.app");
        HttpURLConnection con = (HttpURLConnection) o.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        con.setDoOutput(true);

           DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(data);
        wr.flush();
        wr.close();
      
           BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream(),"UTF-8"));
        String output;


        StringBuffer response = new StringBuffer();

        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();
        
        int i=response.indexOf("<div id=\"dyntranssingle\" >")+26;
        int j=response.indexOf("</div",i);
        String r=response.substring(i,j).replaceAll("<p>", "").replaceAll("</p", "").trim();

        
        return r;
        
    }
    
    
    
    
    
    public static String towloc(int out[]) throws MalformedURLException, ProtocolException, IOException
    {
         double[] op = new double[2];
        URL o = new URL("https://ap1.unwiredlabs.com/v2/process.php");
        HttpURLConnection con = (HttpURLConnection) o.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setDoOutput(true);
        
         int mcc=out[0];
        int mnc=out[1];
        int lac=out[2];
        int cellid=out[3];
       // int psc=out[4];


        JSONArray ja=new JSONArray();
        JSONObject jb=new JSONObject();
        jb.put("cid",cellid); jb.put("lac",lac); //jb.put("psc",psc);
        ja.put(jb);


        JSONObject ji = new JSONObject();
        ji.put("token", "a99cb51b4acdc5");
        ji.put("id", "");
     //   ji.put("radio", "LTE");
        ji.put("mcc", mcc);
        ji.put("mnc", mnc);
        ji.put("address", 0);
        ji.put("cells",ja);
        String json=ji.toString();
        System.out.println(json);


      //  String json = "{\"token\":\"a99cb51b4acdc5\",\"id\":\"\",\"radio\":\"LTE\",\"mcc\":404,\"mnc\":49,\"cells\":[{\"lac\":37312,\"cid\":240708621,\"psc\":137}],\"address\":0}\n";

        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(json);
        wr.flush();
        wr.close();
        int responseCode = con.getResponseCode();

     /* //if server error

       if (responseCode != 200) {

            utils.showsnack(c.findViewById(R.id.train), "Unable to fetch cell tower location");
            utils.showsnack(c.findViewById(R.id.train), "turn on either GPS or Internet on train to provide better location");

        } */

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String output;


        StringBuffer response = new StringBuffer();

        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();
        
        System.out.println(response);

        JSONObject j = new JSONObject(String.valueOf(response));


        op[0] = j.getDouble("lat");
                //Double.parseDouble(j.getString("lat"));
        op[1] =j.getDouble("lon");
                //Double.parseDouble(j.getString("lon"));
        int range=j.getInt("accuracy");
                //Integer.parseInt(j.getString("accuracy"));

        return String.valueOf(op[0]+","+op[1]+","+range);
    }
    
    
    
    public static void dotower() throws Exception
    {
          int ou[]=new int[4];
         ou[0]=404;
         ou[1]=49;
         
         
         for(int cidv=0;cidv<=268435455;cidv++)
         {
              final int cid=cidv;
             new Thread(new Runnable() {
                 @Override
                 public void run() {
                     try{
                          int lac=37312;
                       ou[2]=lac;
                    ou[3]=cid;
                     String op[]=towloc(ou).split(",");
                     String s="insert into tow values("+ou[0]+","+ou[1]+","+lac+","+cid+","+op[0]+","+op[1]+","+op[2]+");";
                     dbhelper.run(s);
                     }catch(Exception e)
                     {
                         
                     }
                 }
             }).start();
            
         
             
         }
         extra.towloc(ou);
    }
    
}
