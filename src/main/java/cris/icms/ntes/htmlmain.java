/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cris.icms.ntes;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import org.jsoup.internal.StringUtil;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;


/**
 *
 * @author work
 */
public class htmlmain {
     public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.47 Safari/537.36";
   
    
     
     public static String rs(String s) 
     {
         StringBuffer sb=new StringBuffer(s);
         for(int i=1;i<s.length()-1;i++)
         {
             if(s.charAt(i)==' '&&Character.isAlphabetic(s.charAt(i-1))&&Character.isAlphabetic(s.charAt(i+1)))
             {
                 sb.setCharAt(i,'$');
             }
            
         }
         return sb.toString();
     }
     
     
     
     public static String googleurl(String trainno,String dep,String arr) throws MalformedURLException, IOException
    {
     String search="Full timetable for "+trainno+" "+dep+"-"+arr+"+site:https://indiarailinfo.com/train/timetable";
  //old:  
  String url="https://google.com/search?q="+search+"&oq=&aqs=";
  //new requires : String url="http://www.google.com/cse?oe=utf8&ie=utf8&source=uds&safe=off&sort=&cx=009319421611820670793:3ywa42eymsa&start=0&q="+search;
  
 
   final Document doc = Jsoup.connect(url).userAgent(USER_AGENT).maxBodySize(0).header("Accept-Encoding", "gzip, deflate").get();
           String text=doc.toString();
           String f="<a href=\"https://indiarailinfo.com";
        
         /*
           int i=text.indexOf(f)+9;
         
           int j=i;
           while(text.charAt(j)!='\"'){
               j++;
            }
            
          return text.substring(i,j);  */
         
    //trying new:    
    String op=text;
  /*      
String m="href=\"https://indiarailinfo.com/train/timetable/";
int i=op.indexOf(m)+m.length();
int j=op.indexOf('/',i);
int k=op.indexOf('/',j+1);
String found=op.substring(j+1,k);
Boolean isnum=false;

String foundurl="https://indiarailinfo.com/train/timetable/all/"+found;
try{
  int num = Integer.parseInt(found);
  isnum=true;
} catch (NumberFormatException e) {
   //checking if string btwn next / / is int
   j=op.indexOf('/',k);
   int l=op.indexOf('/',j+1);
   String newfound=op.substring(j,l);
   found=newfound;
    try{
   int num = Integer.parseInt(newfound);
  isnum=true;
} catch(NumberFormatException e3)
{
    isnum=false;
}

}
if(!isnum)
{
 //   return "notnumexceptingoogle";
    return found;
}
return foundurl;
  */    
  return converttofull(op);

     
   
    }
    public static void main(String args[]) throws IOException
    {
       
        String url=googleurl("12760","HYB","MAS");
   
        Document doc = Jsoup.connect(url).userAgent(USER_AGENT).maxBodySize(0).header("Accept-Encoding", "gzip, deflate").get();
       // Element essay = doc.select("a").first();
           // String text = essay.text();
           String text=doc.text();
          text =  Jsoup.clean(text, Whitelist.basic());
          String s=" X ";
          Pattern pattern = Pattern.compile(s);
          Matcher matcher = pattern.matcher(text);
          text=matcher.replaceAll(" ");
           s=" O ";
          pattern = Pattern.compile(s);
          matcher = pattern.matcher(text);
          text=matcher.replaceAll(" ");
          s=" XO ";
          pattern = Pattern.compile(s);
          matcher = pattern.matcher(text);
          text=matcher.replaceAll(" ");
         s="Address";
          pattern = Pattern.compile(s);
          matcher = pattern.matcher(text);
          text=matcher.replaceAll("thisisstart\n");
          
          s="[-]?([0-9]*[.])+[0-9]+";
          pattern = Pattern.compile(s);
         
       //   text=matcher.replaceAll("$0kms");
      String state="Andhra Pradesh|Arunachal Pradesh|Assam|Bihar|Chhattisgarh|Goa|Gujarat|Haryana|Himachal Pradesh|Jammu and Kashmir|Jharkhand|Karnataka|Kerala|Madhya Pradesh|Maharashtra|Manipur|Meghalaya|Mizoram|Nagaland|Odisha|Punjab|Rajasthan|Sikkim|Tamil Nadu|Telangana|Tripura|Uttarakhand|Uttar Pradesh|West Bengal|Andaman and Nicobar Islands|Chandigarh|Dadra and Nagar Haveli|Daman and Diu|Delhi|Lakshadweep|Puducherry";
 Pattern p = Pattern.compile(state);
      matcher = p.matcher(text);
    text=matcher.replaceAll("$0\n");
     //    text=text.replace("/==/"," /==/ +\n");
    //    text=text.replace("kms /==/ ","part"); 
       
     pattern = Pattern.compile("[A-Za-z]+\\s+[A-Za-z]");
          matcher = pattern.matcher(text);
         // text=matcher.replaceAll("$0hi");
    
    
    
            System.out.println(rs(text));
            
       
    }
    
    
    
    
    public static String irftest(String trainno,String src,String dest) throws Exception {
    //   String url=googleurl(trainno,src,dest);
  //  String   url=jsongoogle(trainno);
       
       
   //trying new 
   String url=ddgurl(trainno);
   

     
     //  url=url.replace("https://indian","https://amp.indian");
       Document doc = Jsoup.connect(url).maxBodySize(0).header("Accept-Encoding", "gzip, deflate").ignoreContentType(true).get();
    return cleantext(doc.text());
   
//return Arrays.toString(newirftest(trainno,"directarray"));
 //return Arrays.toString(directarray(trainno));
 //return directarray(trainno).toString();
    
    
      
         
    }  
    
    
    
    public static String jsongoogle(String trainno) throws IOException
    {
      String search="\""+trainno+"\""+"site:https://indiarailinfo.com/train/timetable";
      
      
     
      String cxvalue="009319421611820670793:3ywa42eymsa";
      //getting json url from cx
      String tempurl="https://cse.google.com/cse.js?cx="+cxvalue;
      String p=Jsoup.connect(tempurl).maxBodySize(0).header("Accept-Encoding", "gzip, deflate").ignoreContentType(true).get().text();
      int i=p.indexOf("\"cselibVersion\": \"")+18;
     String libversion=p.substring(i,p.indexOf("\"",i));
      i=p.indexOf("\"cse_token\": \"")+14;
    String tokenvalue=p.substring(i,p.indexOf("\"",i));
      
      //parsing got json url
      String  url="https://cse.google.com/cse/element/v1?rsz=20&num=20&hl=en&source=gcsc&gss=.com&cselibv="+libversion+"&cx="+cxvalue+"&safe=off&cse_tok="+tokenvalue+"&sort=&exp=csqr,cc&callback=google.search.cse.api4581&q="+search;
        Document doc = Jsoup.connect(url).maxBodySize(0).header("Accept-Encoding", "gzip, deflate").ignoreContentType(true).get();
        String op=doc.text();
        String m1="\"unescapedUrl\":";
        int i1=op.indexOf(m1)+m1.length();
int j1=op.indexOf('"',i1);
int k1=op.indexOf('"',j1+1);
String foundfirsturl=op.substring(j1+1,k1);
//return foundfirsturl;
        
String operatingurl=foundfirsturl;
         

return converttofull(operatingurl);
    }
    
     public static String[] newirftest(String trainno)
     {
         String a[]=null;
         try{
            a= newirftest(trainno,"directarray");
         }
         catch(Exception e)
         {
              try{
            a= newirftest(trainno,"jsongoogle");
         }
         catch(Exception e1)
         {
              try{
            a= newirftest(trainno,"google");
         }
         catch(Exception e2)
         {
             try{
            a= newirftest(trainno,"ddg");
         }
         catch(Exception e4)
         {
             a=new String[7];
            
             a[5]=" exception : "+e4.toString();
             a[6]="none found this trno";
         };
         };
         };
         };
         
         return a;
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
          //   int c=1;
             for(String curr:al)
             {
                 String[] s=listedurl(curr,trainno,searchengine);
                 
                 if( s[5].equals("correct"))
                 {
                     url=curr;
                     return s;
                   
                  //  String n[]=new String[2];  n[0]=url;   n[1]=String.valueOf(c); 
                    
                 }
               //  c++;
             }
         }
         
         
      return listedurl(url,trainno,searchengine);
         
    }  
     
     
     public static String newirflister(String text)
     {
         
        String op="";
      //  op="error i couldnt do";
         Scanner sc=new Scanner(text);
         String line;
          JSONArray ja=new JSONArray();
          double initialkm=0.0;
          int count=0;
          boolean first=true;
         while(sc.hasNextLine())
         {
             JSONObject js = new JSONObject();
            line=sc.nextLine().trim();
  //filtering lines of table only
  if(Character.isDigit(line.charAt(0)))
  {
            
             try{
                 //station name
               int i=line.indexOf('$');
                i++;
                int j=line.indexOf(" ", i);
               String stname=line.substring(i,j).replace('$',' ');
                 //   op=op+stname+"\n";
                 js.put("stname",stname);
                 
                 //station code
                 String str=line.substring(0,i-1);
                 int k=str.lastIndexOf(" ");
                String stcode=str.substring(k+1,i-1);
                 js.put("stcode",stcode);
               
                 
                
                //kilometers
             String txt=line.substring(k);
             // Pattern p = Pattern.compile("[0-9]*\\.?[0-9]+");   //finds any int/float
              Pattern p = Pattern.compile("\\d*\\.\\d+");        //finds only float
                 Matcher m = p.matcher(txt);
                if(m.find())
                {
                    
                    Double d=Double.valueOf(m.group());
                    if(first)
                    {
                        first=false;
                        initialkm=d;
                    }
                            
                    js.put("kms",d-initialkm);
                }
                
                
                //index and type of station
                 p = Pattern.compile("[0-9]*\\.?[0-9]+"); 
                m = p.matcher(line);
                
                if(m.find()){
                    count++;
       Double d=Double.valueOf(m.group());
        
   //avoid it as it removes precision: js.put("index",d);
            js.put("index",m.group());
                  js.put("totalindex",count);
                
               if(d%1==0)
                {
                     js.put("stationtype","stop");
                }
                else
                {
                    js.put("stationtype","intermediate");
                } 
                    
                    
                }
                        
                        
                
                
                ja.put(js);
                 
                 
             
             } catch(Exception e){
             /*   StringWriter sw = new StringWriter();
PrintWriter pw = new PrintWriter(sw);
e.printStackTrace(pw);
             return sw.toString();     */ }    
         }
  
  
  
  
  
  
         }
        
       //  return op;
       
       
     //  return ja.toString(2);
     return ja.toString();
         
     }
    
    
    
 /*   public static String irfcoach(String trainno,String src,String dest) throws Exception {
        String url=url(trainno,src,dest);
      
     //url=url.replace("https://indian","https://amp.indian");
  // url="http://amp.indiarailinfo.com/train/timetable/all/269/834/35";
        Document doc = Jsoup.connect(url).maxBodySize(0).header("Accept-Encoding", "gzip, deflate").get()
       String txt= cleantext(doc.text());
       txt=strbtw(txt,"Coach$Position","$");
       
    return txt;
          
            
    } */

     
     
     public static String coach(String txt)
     {
       String main=txt;
        txt=strbtw(txt,"Coach$Position","$");
        txt=txt.trim();
        String sp[]=txt.split(" ");
      //  Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
          
        ArrayList al=new ArrayList();
        String op;
        
        for(String s:sp)
        {
            try{
                int i=Integer.parseInt(s);
            }
            catch(Exception e)
            {
                if(!rakereversal(main).contains(s) && !s.contains("News") && !s.contains("First"))
                al.add(s);
            }
          
           }
        op=al.toString();
        op=op.trim();
        op.replaceAll("\\s*,\\s*", ",");
       
    return op.trim();
   
     }
     
    
    
    
    
    
    public static String cleantext(String text)
    {
        text =  Jsoup.clean(text, Whitelist.basic());
          String s=" X ";
          Pattern pattern = Pattern.compile(s);
          Matcher matcher = pattern.matcher(text);
          text=matcher.replaceAll(" ");
           s=" O ";
          pattern = Pattern.compile(s);
          matcher = pattern.matcher(text);
          text=matcher.replaceAll(" ");
          s=" XO ";
          pattern = Pattern.compile(s);
          matcher = pattern.matcher(text);
          text=matcher.replaceAll(" ");
         s="Address";
          pattern = Pattern.compile(s);
          matcher = pattern.matcher(text);
          text=matcher.replaceAll("thisisstart\n");
          
          s="[-]?([0-9]*[.])+[0-9]+";
          pattern = Pattern.compile(s);
         
       //   text=matcher.replaceAll("$0kms");
      String state="Andhra Pradesh|Arunachal Pradesh|Assam|Bihar|Chhattisgarh|Goa|Gujarat|Haryana|Himachal Pradesh|Jammu and Kashmir|Jharkhand|Karnataka|Kerala|Madhya Pradesh|Maharashtra|Manipur|Meghalaya|Mizoram|Nagaland|Odisha|Punjab|Rajasthan|Sikkim|Tamil Nadu|Telangana|Tripura|Uttarakhand|Uttar Pradesh|West Bengal|Andaman and Nicobar Islands|Chandigarh|Dadra and Nagar Haveli|Daman and Diu|Lakshadweep|Puducherry|Delhi NCT";
 Pattern p = Pattern.compile(state);
      matcher = p.matcher(text);
    text=matcher.replaceAll("$0\n");
     //    text=text.replace("/==/"," /==/ +\n");
    //    text=text.replace("kms /==/ ","part"); 
       
     pattern = Pattern.compile("[A-Za-z]+\\s+[A-Za-z]");
          matcher = pattern.matcher(text);
         // text=matcher.replaceAll("$0hi");
     return rs(text);
     
    
    
          
    }
    
    public static String strbtw( String text,
    String textFrom,
    String textTo) {

    String result = "";

    // Cut the beginning of the text to not occasionally meet a      
    // 'textTo' value in it:
    result =
      text.substring(
        text.indexOf(textFrom) + textFrom.length(),
        text.length());

    // Cut the excessive ending of the text:
    result =
      result.substring(
        0,
        result.indexOf(textTo));

    return result;
  }
    
    
    public static String ddgurl(String trno) throws IOException
    {
        String search=trno+" site:indiarailinfo.com/train/timetable";
         String url="https://html.duckduckgo.com/html?q="+search;
 
   final Document doc = Jsoup.connect(url).userAgent(USER_AGENT).maxBodySize(0).get();
           String op=doc.toString();
        
        
String m="href=\"https://indiarailinfo.com/train/timetable/";
int i=op.indexOf(m)+m.length();
int j=op.indexOf('/',i);
int k=op.indexOf('/',j+1);
String found=op.substring(j+1,k);
Boolean isnum=false;

String foundurl="https://indiarailinfo.com/train/timetable/all/"+found;
try{
  int num = Integer.parseInt(found);
  isnum=true;
} catch (NumberFormatException e) {}
if(!isnum)
{
    System.out.println("ddg couldnt search:"+trno+foundurl);
 //   return "error";
 //trying new:
 return "https://facebook.com";
}
return foundurl;
    }

    
    public static String rakereversal(String text) {
     
        ArrayList out=new ArrayList();
       /* String m="Loco Reversals ";
        int i=text.indexOf(m);
        int j=i+m.length();
        int k=text.indexOf("/",i);
        
        return text.substring(j,k).trim();  */
       //new:
       String m="Reversals$";
       if(text.contains(m)){
        int i=text.indexOf(m);
         int end=text.indexOf("$Rake",i);  //previously ICF/LHB i.e loco type
      //  String use=text.substring(i,end);
        int j=i+m.length();
        for(int p=i;p<end;p++)
        {
        /*//old:
            int k=text.indexOf("/",i);
            out.add(text.substring(j,k));  */
            if(text.charAt(p)=='/')
            {
                //substring(1) removes firstchar i.e unecessary '$' also
                out.add(text.substring(text.lastIndexOf("$",p),p).substring(1).trim());
            }
                    
        }
       }
       else
       {
           out.add("no reversals");
       }
       return out.toString();
  }
    
    public static String obtainedtrno(String text)
    {
        int i=text.indexOf(":");
        return text.substring(i-5,i).trim();
    }
    
     public static String directsearchurl(String trainno) throws IOException {
     
       String url="https://m.indiarailinfo.com/trains?date=undefined&drev=undefined&arev=undefined&q="+trainno;
       
   final Document doc = Jsoup.connect(url).userAgent(USER_AGENT).maxBodySize(0).header("Accept-Encoding", "gzip, deflate").get();
           String op=doc.toString();
           
String m="href=\"/train/";

int i=op.indexOf(m)+m.length();
int j=op.indexOf('\"',i);
String found=op.substring(i,j);
String foundurl="https://indiarailinfo.com/train/timetable/all/"+found+"/";
return foundurl;
  }
     
     public static ArrayList directarray(String trainno) throws IOException {
     
       String url="https://m.indiarailinfo.com/trains?date=undefined&drev=undefined&arev=undefined&q="+trainno;
       
   final Document doc = Jsoup.connect(url).userAgent(USER_AGENT).maxBodySize(0).header("Accept-Encoding", "gzip, deflate").get();
           String op=doc.toString();
           
String m="href=\"/train/";

ArrayList output=new ArrayList();

for(int start=0;op.indexOf(m,start)!=-1;)
{
    
int i=op.indexOf(m,start)+m.length();
int j=op.indexOf('\"',i);
String found=op.substring(i,j);
String foundurl="https://indiarailinfo.com/train/timetable/all/"+found+"/";
start=j;
output.add(foundurl);

}
return output;


  }
    
     
     
     public static String converttofull(String operatingurl)
     {
                
String m="href=\"https://indiarailinfo.com/train/timetable/";
int i=operatingurl.indexOf(m)+m.length();
int j=operatingurl.indexOf('/',i);
int k=operatingurl.indexOf('/',j+1);
String found=operatingurl.substring(j+1,k);
Boolean isnum=false;

String foundurl="https://indiarailinfo.com/train/timetable/all/"+found;
try{
  int num = Integer.parseInt(found);
  isnum=true;
} catch (NumberFormatException e) {
   //checking if string btwn next / / is int
   j=operatingurl.indexOf('/',k);
   int l=operatingurl.indexOf('/',j+1);
   String newfound=operatingurl.substring(j,l);
   found=newfound;
    try{
   int num = Integer.parseInt(newfound);
  isnum=true;
} catch(NumberFormatException e3)
{
    isnum=false;
}

}
if(!isnum)
{
   //return "notnumexceptingoogle";
    return found;
}
return foundurl;
      

     }
     
     
     public static String[] listedurl(String url,String trainno,String searchengine) throws IOException
     {
            
         Document doc = Jsoup.connect(url).userAgent(USER_AGENT).maxBodySize(0).header("Accept-Encoding", "gzip, deflate").get();
      //return cleantext(doc.text());
      String op[]=new String[9];  //9 from 8 trying..
      op[0]=url;
      
      
      op[1]= newirflister(cleantext(doc.text()));
      
      
    //BUGS TEST: op[1]=cleantext(doc.text());
      
      op[2]=coach(cleantext(doc.text()));
   
      op[3]=rakereversal(cleantext(doc.text()));
      
      if(op[2]=="" || op[2]=="[]")
      {
          op[2]="[not available]";
      }
       if(op[3]=="" || op[3]=="[]")
      {
          op[3]="not cleaned reversal properly";
      }
      
       op[4]=obtainedtrno(cleantext(doc.text()));
       
       boolean norunning=false;
       
    //   if(doc.text().contains("WARNING: NOT RUNNING"))  norunning=true;
       
       
       if(op[4].equals(trainno))
       {
          op[5]="correct" ;
          
          // if(norunning)    op[5]="wrong because this train is not runnning";
           
       }
       else
       {
         
          op[5]="wrong"+" by "+searchengine;
          
             
       }
       op[6]=searchengine;
       
       op[7]=obtainedtrname(doc.text(),trainno);
       
       op[8]=obtainedrake(doc.text());
      return op;
     }

    
     
     public static String obtainedrake(String txt)
     {
       /*  try{
         txt=cleantext(txt);
        
         int i=txt.indexOf("$Rake$Rake/Coach$Position");
         i--;
         int j=txt.lastIndexOf("$",i)+1;
         System.out.println(i+" "+j);
         return txt.substring(j,i+1).trim();
         }catch(Exception e)
         {
          return "error";   
         }*/
         
         try{
            
      
         int i=txt.indexOf(" Rake Rake/Coach Position");
         i--;
          int j=txt.lastIndexOf(" ",i)+1;
      
         
         return txt.substring(j,i+1).trim();
         }catch(Exception e)
         {
             
             e.printStackTrace();
          return "error";   
         }
         
             
         
         
         
         
     }
     
     
     
     public static String obtainedtrname(String txt,String trno)
     {
           // System.out.println(txt);
         
         int i=txt.indexOf("trainName\": \"")+21;
         int j=txt.indexOf("/"+trno,i);
         String op= txt.substring(i,j).trim();
         
         int n=op.length()-1;
         
         if(op.charAt(n)==')')
         {
             int l=op.lastIndexOf("(",n);
             op=op.substring(0,l).trim();
         }
         
         return op;
     }
     
     
     
     
     
     
     
     
     public static String oldobtainedtrname(String txt,String trno) {
        
      //  System.out.println(txt);
       
        int i=txt.indexOf("for$")+4;
        int j=txt.indexOf("/"+trno,i);
        String op=txt.substring(i,j);
        op=op.replace('$',' ');
        if(op.contains("("))
        {
            
            int k=op.indexOf("(");
            op=op.substring(0,k);
        }
        
        op=op.replaceAll("[\\n]", "");  //fixes bug for some trains
        
       // System.out.println(op.trim());
       
       
       ///trying to fix error for some sublocal trains with short names
       int n=op.indexOf("/")+1;
       op=op.substring(n);
       
      // return txt;
      op=op.replaceAll("&nbsp;","");
       return op.trim();
            
    }
    
    
    
    
}
