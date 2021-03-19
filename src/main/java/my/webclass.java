/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author work
 */
public class webclass {
     public static String fillintermediate(String all) throws ParseException, IOException
    {
        //filling speed,daycount for intermediates,
          JSONArray jall=new JSONArray(all);
        JSONObject allobj=null;
        
        
        
        
        
        
        
        //trying to fill stops to save time
        
          String stopsdata="";
          for(int i=0;i<jall.length();i++){
              int val = (jall.getJSONObject(i).getBoolean("stop")) ? 1 : 0;
             stopsdata=stopsdata+val;
          }
        
        
        
        
         for(int i=0;i<jall.length();i++)
        {
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
            
   JSONObject prev=jall.getJSONObject(prevint);
              JSONObject next=jall.getJSONObject(nextint);
   
   
            
               SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                SimpleDateFormat onlyday = new SimpleDateFormat("dd");
                SimpleDateFormat dayformat = new SimpleDateFormat("dd:HH:mm");
Date date1 = format.parse(prev.getString("depTime"));
Date date2 = format.parse(next.getString("arrTime"));
long difference = date2.getTime() - date1.getTime()+86400000*(next.getInt("dayCnt")-prev.getInt("dayCnt")); 
int dist=next.getInt("distance")-prev.getInt("distance");

double speed=(double)(dist)/difference;   
   
                if(difference!=0){
               allobj.put("speed",String.valueOf((int)(speed*3600000)));}
             
               
               long difftime= (long)((distance-prev.getInt("distance"))/speed);
               
               long reqtime=difftime+date1.getTime()+(prev.getInt("dayCnt")-1)*86400000 ;
               
               Date result = new Date(reqtime); 
               
               
              allobj.put("arrTime",format.format(result));
               allobj.put("depTime",format.format(result)); //can try dayformat.format
               String s=onlyday.format(result);
                allobj.put("dayCnt",Integer.parseInt(s));
            } else
             {
                 //filling speed for stops
                if(i!=jall.length()-1){
                    int j;
                    for(j=i+1;jall.getJSONObject(j).getBoolean("stop")==false;j++){}
                   JSONObject next=jall.getJSONObject(j);
                   JSONObject curr=jall.getJSONObject(i);
                   
               SimpleDateFormat format = new SimpleDateFormat("HH:mm");
Date date1 = format.parse(curr.getString("depTime"));
Date date2 = format.parse(next.getString("arrTime"));
long difference = date2.getTime() - date1.getTime()+86400000*(next.getInt("dayCnt")-curr.getInt("dayCnt")); 
int dist=next.getInt("distance")-curr.getInt("distance");
double speed=(double)(dist)/difference;

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
    
}
