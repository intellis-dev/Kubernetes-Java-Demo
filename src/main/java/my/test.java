/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;

/**
 *
 * @author work
 */
public class test {
    public static void main(String args[]) throws  Exception
    {
      /*    String a[]=ntesweb.mixeddetails("02296");
          //System.out.println("output is "+Arrays.asList(s));
         File f1 = new File("C:/Users/work/Desktop/progress.json");
         if(!f1.exists()) {
            f1.createNewFile();
         } 
         FileWriter fileWritter = new FileWriter(f1,false);
        
           JSONObject jwo = new JSONObject();
            JSONArray jc=new JSONArray();
             for (int c = 0; c < a.length; c++)
                    {
                          jc.put(a[c]); 
                    }
        jwo.put("02296",jc);
        
        String ans=jwo.toString();
        ans = ans.replaceAll("\\\\", "");
            fileWritter.write(ans);
            fileWritter.flush();
         fileWritter.close();


*/
     //   System.out.println(ntesweb.formatmnts("days", 3));
        
        String s="CREATE TABLE trains (\n" +
"    trno int,\n" +
"    trname varchar(255),\n" +
"    stnlist varchar(7255),\n" +
"    runson int,\n" +
"    cancelled int\n" +"traintype varchar(40)\n" +
");";
     //   dbhelper.init("file");
     //   dbhelper.run(s);
     
 //    System.out.println(Arrays.asList(optimised.mindetails("02760")));
 
String url="https://enquiry.indianrail.gov.in/ntes/NTES?action=getTrainForDate&trainNo=02760&trainStartDate=28/01/2021";  
 url=url.replaceAll(" ","%20"); //to avoid space bugs
        
        StringBuilder response = new StringBuilder();
        URL u = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) u.openConnection();
         conn.setRequestProperty( "Content-Length","100");
        
        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK)
        {
            BufferedReader input = new BufferedReader(new InputStreamReader(conn.getInputStream()),8192);
            String line = null;
            while ((line = input.readLine()) != null)
            {
                response.append(line);
            } input.close();}
        
        System.out.println(response);
        
         
        
        
        
        
           
    }    
    
    
/*    
   public static void execute() throws SQLException, ClassNotFoundException
   {
        String s="CREATE TABLE trains (\n" +
"    trno varchar(20),\n" +"traintype varchar(40),\n" +"validity varchar(90),\n" +
"    trname varchar(255),\n" +"    runson varchar(40),\n" +
"    stnlist varchar(7255),\n" +
"    cancelled varchar(20),\n" +"coach varchar(300),\n" +"reversal varchar(90)\n" +  
");";
        dbhelper.init("file");
        dbhelper.run(s);
        
   }
    */
 
    
    
    
    
   
}
