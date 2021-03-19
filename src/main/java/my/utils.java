package my;
import cris.icms.ntes.htmlmain;

import jakarta.servlet.ServletContext;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;


public class utils
{
   static boolean flag=false;
   static ServletContext application;
   static org.apache.catalina.core.ApplicationContextFacade sapplication;
  
   
    public  void suggestfetcher(String json) throws IOException
    {
       JSONObject jsonObject = new JSONObject(json);
    JSONObject inn = jsonObject.getJSONObject("TrainsAtStation");
    json=inn.toString();
        
    }
    
  
    public static ServletContext getapp()
    {
        
        return application;
       
            
    }
     public static org.apache.catalina.core.ApplicationContextFacade getsapp()
    {
        
        return sapplication;
            
    }
    public static void setapp(Object app)
    {
        flag=false;
        
        try{
           
          application=(ServletContext)app;  
        }
        catch(Exception e)
        {
            flag=true;
             sapplication=(org.apache.catalina.core.ApplicationContextFacade)app;  
            
        }
        
    }
}