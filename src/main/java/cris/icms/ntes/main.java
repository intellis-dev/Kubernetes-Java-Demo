package cris.icms.ntes;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import static java.lang.System.out;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONArray;
import org.json.JSONObject;

public class main {

   
    public static void main(String[] args) {
       // livestation("WL","8","");
     // schedule("02702");
      currentstatus("02266","WL","TD","");
    }
    
    
    /* methods-list:
    schedule(trno)   //gives more details than traindetails()
    serviceschedule(trno,startdate)  //same as schedule() but requires date
    domaps(trno,startdate)   ~   fullstations(trno,startdate)    ~ all stations list
    livestation(src,timeinhrs,dest)
    currentstatus(trno,start,datetype,flag)  //datetype- TD,YS,TM  ifnot specified it takes default
    trainbtwstns(sc,dest,type)  // default type- All
    traindetails(trno/starting 3 chars of trno or trname)   //just can be used to search trains easily
    
    findstation(stname,type)  //type is C for code and N for name. N should be more than 3 charecters
    */
    
    
    
    
    
    public static String livestation(String station,String timeinhrs,String destination){
        String str;
        
        String output="Enter All the Details Properly";
        //Stationname,time in hrs(2/4/8),destination(optional)
         String[] strArr={station,timeinhrs,destination};
     
            try {
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL(g.b()).openConnection();
                httpsURLConnection.setReadTimeout(15000);
                httpsURLConnection.setConnectTimeout(15000);
                httpsURLConnection.setRequestMethod("POST");
                httpsURLConnection.setDoInput(true);
                httpsURLConnection.setDoOutput(true);
                httpsURLConnection.setUseCaches(false);
                httpsURLConnection.setRequestProperty("Content-Type", "application/json");
                httpsURLConnection.setRequestProperty("charset", "utf-8");
                String e2 = g.e();
                httpsURLConnection.setRequestProperty("meta" + e2, g.c(e2));
                httpsURLConnection.connect();
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("jsonIn", g.d("service=TrainRunningMob&subService=TrainsAtStationJson&jStation=" + strArr[0] + "&nHr=" + strArr[1] + "&jToStation=" + strArr[2]));
                DataOutputStream dataOutputStream = new DataOutputStream(httpsURLConnection.getOutputStream());
                dataOutputStream.writeBytes(jSONObject.toString());
                dataOutputStream.flush();
                dataOutputStream.close();
                if (httpsURLConnection.getResponseCode() == 200) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()));
                    str = "";
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        str = str + readLine;
                    }
                } else {
                    str = "";
                }
                if (!str.equals("")) {
                    String str2 = (String) new JSONObject(str.toString()).get("jsonIn");
                    if (!str2.equals("")) {
                        JSONObject jSONObject2 = new JSONObject(l.b("8EA4DB2CC1EB3DC5", "7DC5EB3BB4DB6EA8", new String(l.d(str2))));
                       
               //   System.out.println(jSONObject2.toString(2));
              output= jSONObject2.toString(2);
               }}
                } catch (Exception e3) {
                   
                    StringWriter sw = new StringWriter();
                e3.printStackTrace(new PrintWriter(sw));
               // e3.printStackTrace();
               // output= "exception"+e3.toString();
               output="exception: "+sw;
                };
 
        
        
        return output;
        
        
    }
    public static String currentstatus(String trainno,String start,String datetype,String flag){
         System.out.println("new tsting...");
         String op="Enter details properly IN API";
        String str;
       StringBuilder sb = new StringBuilder();
            sb.append("service=TrainRunningMob&subService=ShowFullRunJson&trainNo=");
            sb.append(trainno); //trainno
            sb.append("&jStation=");
            sb.append(start); //start
            sb.append("&jDateType=");
            sb.append(datetype);  //day
            sb.append("&arrDepFlag=");
            sb.append(""); //flag
            String sb2 = sb.toString();
            try {
                JSONObject jSONObject = new JSONObject();
                StringBuilder sb3 = new StringBuilder();
                sb3.append(g.a(sb2.trim() + "645fbc1e56e23365f2f3c204ae0899f6").toUpperCase());
                sb3.append("#");
                sb3.append(l.f(l.c("8EA4DB2CC1EB3DC5", "7DC5EB3BB4DB6EA8", sb2.trim()).trim()));
                jSONObject.put("jsonIn", sb3.toString());
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL(g.b()).openConnection();
                httpsURLConnection.setReadTimeout(15000);
                httpsURLConnection.setConnectTimeout(15000);
                httpsURLConnection.setRequestMethod("POST");
                httpsURLConnection.setDoInput(true);
                httpsURLConnection.setDoOutput(true);
                httpsURLConnection.setUseCaches(false);
                httpsURLConnection.setRequestProperty("Content-Type", "application/json");
                httpsURLConnection.setRequestProperty("charset", "utf-8");
                String e2 = g.e();
                httpsURLConnection.setRequestProperty("meta" + e2, g.c(e2));
                httpsURLConnection.connect();
                DataOutputStream dataOutputStream = new DataOutputStream(httpsURLConnection.getOutputStream());
                dataOutputStream.writeBytes(jSONObject.toString());
                dataOutputStream.flush();
                dataOutputStream.close();
                if (httpsURLConnection.getResponseCode() == 200) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()));
                    str = "";
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        str = str + readLine;
                    }
                } else {
                    str = "";
                }
                
                if (!str.equals("")) {
                    String str3 = (String) new JSONObject(str.toString()).get("jsonIn");
                    String str3fake="hg";
                    if (!str3fake.equals("")) {
                       String b2 = l.b("8EA4DB2CC1EB3DC5", "7DC5EB3BB4DB6EA8", new String(l.d(str3)));
                        //Log.d("FULL", b2);
                        JSONObject jSONObject2 = new JSONObject(b2);
                       
     //tryiing new:   JSONObject jSONObject2 = new JSONObject(m.b("8EA4DB2CC1EB3DC5", "7DC5EB3BB4DB6EA8", new String(m.d(str3))));
                        
        op=jSONObject2.toString(2);
       // System.out.println(jSONObject2.toString(2));
               }}
                } catch (Exception e3) {
                e3.printStackTrace();}
    
 return op;
  }
    
   
    public static String schedule(String trainno){
// System.out.println("hi");
String op="unintialised";
      String str;
            StringBuilder sb = new StringBuilder();
            sb.append("service=TrainRunningMob&subService=GetScheduleJson&trainNo=");
            sb.append(trainno);
            String sb2 = sb.toString();
            try {
                JSONObject jSONObject = new JSONObject();
                StringBuilder sb3 = new StringBuilder();
                sb3.append(g.a(sb2.trim() + "645fbc1e56e23365f2f3c204ae0899f6").toUpperCase());
                sb3.append("#");
                sb3.append(l.f(l.c("8EA4DB2CC1EB3DC5", "7DC5EB3BB4DB6EA8", sb2.trim()).trim()));
                jSONObject.put("jsonIn", sb3.toString());
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL(g.b()).openConnection();
                httpsURLConnection.setReadTimeout(15000);
                httpsURLConnection.setConnectTimeout(15000);
                httpsURLConnection.setRequestMethod("POST");
                httpsURLConnection.setDoInput(true);
                  httpsURLConnection.setDoOutput(true);

                httpsURLConnection.setUseCaches(false);
                httpsURLConnection.setRequestProperty("Content-Type", "application/json");
                httpsURLConnection.setRequestProperty("charset", "utf-8");
                String e2 = g.e();
                httpsURLConnection.setRequestProperty("meta" + e2, g.c(e2));
                httpsURLConnection.connect();
                DataOutputStream dataOutputStream = new DataOutputStream(httpsURLConnection.getOutputStream());
                dataOutputStream.writeBytes(jSONObject.toString());
                dataOutputStream.flush();
                dataOutputStream.close();
                if (httpsURLConnection.getResponseCode() == 200) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()));
                    str = "";
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        str = str + readLine;
                    }
                } else {
                    str = "";
                }
              
                ArrayList<String> arrayList = new ArrayList<>();
                ArrayList<Boolean> arrayList2 = new ArrayList<>();
                if (!str.equals("")) {
                    String str2 = (String) new JSONObject(str.toString()).get("jsonIn");
                    if (!str2.equals("")) {
                        JSONObject jSONObject2 = new JSONObject(l.b("8EA4DB2CC1EB3DC5", "7DC5EB3BB4DB6EA8", new String(l.d(str2))));
                 //  System.out.println(jSONObject2.toString(2));
                 op=jSONObject2.toString(2);
               }}
                } catch (Exception e3) {
                e3.printStackTrace();}
            
            return op;
    }
    
    
    
    public static String cleanjson(String s)
    {
        JSONObject ob = new JSONObject(s);
        return ob.toString(4);
    }

     public static String fullstations(String trainno,String startdate){

String op="unintialised fully";
String str;
      
      q0 q0Var = new q0();
            new ArrayList();
            StringBuilder sb = new StringBuilder();
            sb.append("service=TrainRunningMob&subService=GetFullScheduleJson&trainNo=");
            sb.append(trainno);
            sb.append("&startDate=");
            sb.append(startdate);
            String sb2 = sb.toString();
            try {
                JSONObject jSONObject = new JSONObject();
                StringBuilder sb3 = new StringBuilder();
                sb3.append(g.a(sb2.trim() + "645fbc1e56e23365f2f3c204ae0899f6").toUpperCase());
                sb3.append("#");
                sb3.append(m.f(m.c("8EA4DB2CC1EB3DC5", "7DC5EB3BB4DB6EA8", sb2.trim()).trim()));
                jSONObject.put("jsonIn", sb3.toString());
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL(g.b()).openConnection();
                httpsURLConnection.setReadTimeout(15000);
                httpsURLConnection.setConnectTimeout(15000);
                httpsURLConnection.setRequestMethod("POST");
                httpsURLConnection.setDoInput(true);
                httpsURLConnection.setDoOutput(true);
                httpsURLConnection.setUseCaches(false);
                httpsURLConnection.setRequestProperty("Content-Type", "application/json");
                httpsURLConnection.setRequestProperty("charset", "utf-8");
                String e2 = g.e();
                httpsURLConnection.setRequestProperty("meta" + e2, g.c(e2));
                httpsURLConnection.connect();
                DataOutputStream dataOutputStream = new DataOutputStream(httpsURLConnection.getOutputStream());
                dataOutputStream.writeBytes(jSONObject.toString());
                dataOutputStream.flush();
                dataOutputStream.close();
                if (httpsURLConnection.getResponseCode() == 200) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()));
                    str = "";
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        str = str + readLine;
                    }
                } else {
                    str = "";
                }
                if (!str.equals("")) {
                    String str2 = (String) new JSONObject(str.toString()).get("jsonIn");
                   
                    if (!str2.equals("")) {
                        JSONObject jSONObject2 = new JSONObject(m.b("8EA4DB2CC1EB3DC5", "7DC5EB3BB4DB6EA8", new String(m.d(str2))));
                         op=jSONObject2.toString(2);
                    }
                }
           
            } catch (Exception e3) {
                e3.printStackTrace();
            op=e3.toString();
            }
            return op;
        }


public static String domaps(String trainno,String startdate)
{
    String op="nothing initial";
    String str;
            q0 q0Var = new q0();
            new ArrayList();
            StringBuilder sb = new StringBuilder();
            sb.append("service=TrainRunningMob&subService=GetFullScheduleJson&trainNo=");
            sb.append(trainno);
            sb.append("&startDate=");
            sb.append(startdate);
            String sb2 = sb.toString();
            try {
                JSONObject jSONObject = new JSONObject();
                StringBuilder sb3 = new StringBuilder();
                sb3.append(g.a(sb2.trim() + "645fbc1e56e23365f2f3c204ae0899f6").toUpperCase());
                sb3.append("#");
                sb3.append(m.f(m.c("8EA4DB2CC1EB3DC5", "7DC5EB3BB4DB6EA8", sb2.trim()).trim()));
                jSONObject.put("jsonIn", sb3.toString());
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL(g.b()).openConnection();
                httpsURLConnection.setReadTimeout(15000);
                httpsURLConnection.setConnectTimeout(15000);
                httpsURLConnection.setRequestMethod("POST");
                httpsURLConnection.setDoInput(true);
                httpsURLConnection.setDoOutput(true);
                httpsURLConnection.setUseCaches(false);
                httpsURLConnection.setRequestProperty("Content-Type", "application/json");
                httpsURLConnection.setRequestProperty("charset", "utf-8");
                String e2 = g.e();
                httpsURLConnection.setRequestProperty("meta" + e2, g.c(e2));
                httpsURLConnection.connect();
                DataOutputStream dataOutputStream = new DataOutputStream(httpsURLConnection.getOutputStream());
                dataOutputStream.writeBytes(jSONObject.toString());
                dataOutputStream.flush();
                dataOutputStream.close();
                if (httpsURLConnection.getResponseCode() == 200) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()));
                    str = "";
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        str = str + readLine;
                    }
                } else {
                    str = "";
                }
                if (!str.equals("")) {
                    String str2 = (String) new JSONObject(str.toString()).get("jsonIn");
                    if (!str2.equals("")) {
                        JSONObject jSONObject2 = new JSONObject(m.b("8EA4DB2CC1EB3DC5", "7DC5EB3BB4DB6EA8", new String(m.d(str2))));
                         op=jSONObject2.toString(2);
                        
                    }
                }
               
            } catch (Exception e3) {
                e3.printStackTrace();
               
            }
            return op;
        }

public static String serviceschedule(String trainno,String startdate)
{
         String op="notin";  
         String str;
            StringBuilder sb = new StringBuilder();
            sb.append("service=TrainRunningMob&subService=GetServiceScheduleJson&trainNo=");
            sb.append(trainno);
            sb.append("&startDate=");
            sb.append(startdate);
            String sb2 = sb.toString();
            try {
                JSONObject jSONObject = new JSONObject();
                StringBuilder sb3 = new StringBuilder();
                sb3.append(g.a(sb2.trim() + "645fbc1e56e23365f2f3c204ae0899f6").toUpperCase());
                sb3.append("#");
                sb3.append(m.f(m.c("8EA4DB2CC1EB3DC5", "7DC5EB3BB4DB6EA8", sb2.trim()).trim()));
                jSONObject.put("jsonIn", sb3.toString());
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL(g.b()).openConnection();
                httpsURLConnection.setReadTimeout(15000);
                httpsURLConnection.setConnectTimeout(15000);
                httpsURLConnection.setRequestMethod("POST");
                httpsURLConnection.setDoInput(true);
                httpsURLConnection.setDoOutput(true);
                httpsURLConnection.setUseCaches(false);
                httpsURLConnection.setRequestProperty("Content-Type", "application/json");
                httpsURLConnection.setRequestProperty("charset", "utf-8");
                String e2 = g.e();
                httpsURLConnection.setRequestProperty("meta" + e2, g.c(e2));
                httpsURLConnection.connect();
                DataOutputStream dataOutputStream = new DataOutputStream(httpsURLConnection.getOutputStream());
                dataOutputStream.writeBytes(jSONObject.toString());
                dataOutputStream.flush();
                dataOutputStream.close();
                if (httpsURLConnection.getResponseCode() == 200) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()));
                    str = "";
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        str = str + readLine;
                    }
                } else {
                    str = "";
                }
                q0 q0Var = new q0();
                ArrayList<h0> arrayList = new ArrayList<>();
                if (!str.equals("")) {
                    String str2 = (String) new JSONObject(str.toString()).get("jsonIn");
                    if (!str2.equals("")) {
                        JSONObject jSONObject2 = new JSONObject(m.b("8EA4DB2CC1EB3DC5", "7DC5EB3BB4DB6EA8", new String(m.d(str2))));
                         op=jSONObject2.toString(2);
                        }
                    }
                
            
            } catch (Exception e3) {
              op=e3.toString();
            
            }
            return op;
        }




public static String trainbtwstns(String src,String dest,String type)
{
    String op = "nothing";
    String str;
            o0 o0Var = new o0();
            StringBuilder sb = new StringBuilder();
            sb.append("service=TrainRunningMob&subService=TrainBtwStnJson&stnFrom=");
            sb.append(src);
            sb.append("&stnTo=");
            sb.append(dest);
            sb.append("&trainType=");
            sb.append(type);
            String sb2 = sb.toString();
            try {
                JSONObject jSONObject = new JSONObject();
                StringBuilder sb3 = new StringBuilder();
                sb3.append(g.a(sb2.trim() + "645fbc1e56e23365f2f3c204ae0899f6").toUpperCase());
                sb3.append("#");
                sb3.append(m.f(m.c("8EA4DB2CC1EB3DC5", "7DC5EB3BB4DB6EA8", sb2.trim()).trim()));
                jSONObject.put("jsonIn", sb3.toString());
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL(g.b()).openConnection();
                httpsURLConnection.setReadTimeout(15000);
                httpsURLConnection.setConnectTimeout(15000);
                httpsURLConnection.setRequestMethod("POST");
                httpsURLConnection.setDoInput(true);
                httpsURLConnection.setDoOutput(true);
                httpsURLConnection.setUseCaches(false);
                httpsURLConnection.setRequestProperty("Content-Type", "application/json");
                httpsURLConnection.setRequestProperty("charset", "utf-8");
                String e2 = g.e();
                httpsURLConnection.setRequestProperty("meta" + e2, g.c(e2));
                httpsURLConnection.connect();
                DataOutputStream dataOutputStream = new DataOutputStream(httpsURLConnection.getOutputStream());
                dataOutputStream.writeBytes(jSONObject.toString());
                dataOutputStream.writeBytes(URLEncoder.encode(jSONObject.toString(), "UTF-8"));
                dataOutputStream.flush();
                dataOutputStream.close();
                if (httpsURLConnection.getResponseCode() == 200) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()));
                    str = "";
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        str = str + readLine;
                    }
                } else {
                    str = "";
                }
                if (!str.equals("")) {
                    String str2 = (String) new JSONObject(str.toString()).get("jsonIn");
                   if (!str2.equals("")) {
                        JSONObject jSONObject2 = new JSONObject(m.b("8EA4DB2CC1EB3DC5", "7DC5EB3BB4DB6EA8", new String(m.d(str2))));
                       
                         op=jSONObject2.toString(2);
                        
                   }
                   
                    }
                
             
            } catch (Exception e3) {
                e3.printStackTrace();
             op=e3.toString();
            }
            return op;

}



public static String traindetails(String tr)
{
    String op="noo";
    
    String str;
            g0 g0Var = new g0();
            try {
               tr = URLEncoder.encode(tr, "UTF-8");
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            }
            String str2 = "service=TrainRunningMob&subService=FindTrainJson&trainNo=" + tr;
            try {
                JSONObject jSONObject = new JSONObject();
                StringBuilder sb = new StringBuilder();
                sb.append(g.a(str2.trim() + "645fbc1e56e23365f2f3c204ae0899f6").toUpperCase());
                sb.append("#");
                sb.append(m.f(m.c("8EA4DB2CC1EB3DC5", "7DC5EB3BB4DB6EA8", str2.trim()).trim()));
                jSONObject.put("jsonIn", sb.toString());
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL(g.b()).openConnection();
                httpsURLConnection.setReadTimeout(15000);
                httpsURLConnection.setConnectTimeout(15000);
                httpsURLConnection.setRequestMethod("POST");
                httpsURLConnection.setDoInput(true);
                httpsURLConnection.setDoOutput(true);
                httpsURLConnection.setUseCaches(false);
                httpsURLConnection.setRequestProperty("Content-Type", "application/json");
                httpsURLConnection.setRequestProperty("charset", "utf-8");
                String e3 = g.e();
                httpsURLConnection.setRequestProperty("meta" + e3, g.c(e3));
                httpsURLConnection.connect();
                DataOutputStream dataOutputStream = new DataOutputStream(httpsURLConnection.getOutputStream());
                dataOutputStream.writeBytes(jSONObject.toString());
                dataOutputStream.flush();
                dataOutputStream.close();
                if (httpsURLConnection.getResponseCode() == 200) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()));
                    str = "";
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        str = str + readLine;
                    }
                } else {
                    str = "";
                }
                if (!str.equals("")) {
                    String str3 = (String) new JSONObject(str.toString()).get("jsonIn");
                    if (!str3.equals("")) {
                        JSONObject jSONObject2 = new JSONObject(m.b("8EA4DB2CC1EB3DC5", "7DC5EB3BB4DB6EA8", new String(m.d(str3))));
                      op=jSONObject2.toString(2);
                        
                     
                        }
                    }
                
               
            } catch (Exception e4) {
                e4.printStackTrace();
                op=e4.toString();
               
            }
            return op;

}


public static String findstation(String station,String type)
{
    String op="noo";
    String str;
            f0 f0Var = new f0();
            try {
                station = URLEncoder.encode(station, "UTF-8");
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            }
            String str2 = "service=TrainRunningMob&subService=FindStationListJson&stnStr=" + station + "&searchType=" + type;
            try {
                JSONObject jSONObject = new JSONObject();
                StringBuilder sb = new StringBuilder();
                sb.append(g.a(str2.trim() + "645fbc1e56e23365f2f3c204ae0899f6").toUpperCase());
                sb.append("#");
                sb.append(m.f(m.c("8EA4DB2CC1EB3DC5", "7DC5EB3BB4DB6EA8", str2.trim()).trim()));
                jSONObject.put("jsonIn", sb.toString());
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL(g.b()).openConnection();
                httpsURLConnection.setReadTimeout(15000);
                httpsURLConnection.setConnectTimeout(15000);
                httpsURLConnection.setRequestMethod("POST");
                httpsURLConnection.setDoInput(true);
                httpsURLConnection.setDoOutput(true);
                httpsURLConnection.setUseCaches(false);
                httpsURLConnection.setRequestProperty("Content-Type", "application/json");
                httpsURLConnection.setRequestProperty("charset", "utf-8");
                String e3 = g.e();
                httpsURLConnection.setRequestProperty("meta" + e3, g.c(e3));
                httpsURLConnection.connect();
                DataOutputStream dataOutputStream = new DataOutputStream(httpsURLConnection.getOutputStream());
                dataOutputStream.writeBytes(jSONObject.toString());
                dataOutputStream.flush();
                dataOutputStream.close();
                if (httpsURLConnection.getResponseCode() == 200) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()));
                    str = "";
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        str = str + readLine;
                    }
                } else {
                    str = "";
                }
                if (!str.equals("")) {
                    String str3 = (String) new JSONObject(str.toString()).get("jsonIn");
                    if (!str3.equals("")) {
                        JSONObject jSONObject2 = new JSONObject(m.b("8EA4DB2CC1EB3DC5", "7DC5EB3BB4DB6EA8", new String(m.d(str3))));
                         op=jSONObject2.toString(2);
                    }
                }
               
            } catch (Exception e4) {
                e4.printStackTrace();
            op=e4.toString();
            }


return op;


    
}




}
