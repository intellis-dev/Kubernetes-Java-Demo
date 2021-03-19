
package my;

import java.util.Arrays;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class threadhelper
{
    
    public static void does(int i) throws InterruptedException
    {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
  // should be val<i+500 whrn executing individual      
    for(int val=i;val<i+500;val++){
                
        String s=String.format("%05d", val);
     try{
        String inp[]=optimised.mindetails(s);
        if(inp!=null){
        String sql="INSERT INTO trains\n" +"VALUES ('"+s+"','"+inp[0]+"','"+inp[1]+"','"+inp[2]+"','"+inp[3]+"','"+inp[4]+"','"+inp[5]+"','"+inp[6]+"','"+inp[7]+"');" ;
       
        dbhelper.run(sql);}
       //  System.out.println(sql);
        
        // System.out.println(Arrays.toString(optimised.ntesmin(s)));
          
        System.out.println(s);
   
//above is for all train index building by irf .below is for total details.
        
//    optimised.ntesfull(s,"new");
        
       
        
        
        
        
         
     }catch(Exception e){
         
   System.out.println("exception"+e.toString()+" at "+s);
     }
     
    }
 }
        });
       
        t.start();
       
       
        
        
    }
    
    
    
     public static void wholedoes(int i) throws InterruptedException
    {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {    
    for(int val=i;val<i+250;val++){
                
        String s=String.format("%05d", val);
     try{
          optimised.ntesfull(s,"new");
        
     }catch(Exception e){
   System.out.println("exception"+e.toString()+" at "+s);
     }
     
    }
 }
        });
       
        t.start();
       
       
        
        
    }
    
    
    public static void locdoer(int mcc,int mnc,int lac)
    {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
        
    for(int cell=0;cell<65533;cell++){  try{
       
        System.out.println("Currently at"+lac+" "+cell);
        
         String url="https://whereismytrain.in/get_ltlg?cellinfo="+mcc+"_"+mnc+"_"+lac+"_"+cell;
         
       
      String txt=ntesweb.stringaturl(url);
      
        int i=txt.indexOf("\"lat\": ")+7;
        String lat=txt.substring(i,txt.indexOf(",",i));
        i=txt.indexOf("\"lng\": ")+7;
        String lang=txt.substring(i,txt.indexOf("}",i));
        
          String sql="INSERT INTO newcell\n" +
"VALUES ('"+lat+"','"+lang+"','"+mcc+"','"+mnc+"','"+lac+"','"+cell+"');" ;
       
             dbhelper.run(sql);
             
             
     }catch(Exception e){
         if(!e.toString().contains("java.lang.StringIndexOutOfBoundsException")){
     System.out.println(e.toString()+" at "+cell);}
     }
     
    }
 }
        });
       
        t.start();
       
       
        
    }
    
    
}