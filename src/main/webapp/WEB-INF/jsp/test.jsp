

<%@page import="my.optimised"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="my.utils"%>
<%@page import="my.ntesweb"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ page import="cris.icms.ntes.*" %>
<%@ page import="android.util.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="org.json.JSONObject"%>
<%@ page import="org.json.JSONArray"%>

<!DOCTYPE html>

<html>

    <head>
        
        
   <style>
      body {
         
          /*
         //  background: 	#FFF0F5; 
          background-attachment: local; background-repeat: no-repeat;
          background-image:url('background.jpg');  */
      } 
table, th{
  border: 3px solid green;
  border-style: solid;
  padding: 8px;
      border-collapse: collapse;
  border-spacing: 0;
  align:center;
   margin-left:auto;
  margin-right: auto;
  
}
td {
    text-align:center;
    border: 3px solid green;
  border-style: solid;
   padding: 2;
   align:center
  
}
</style>
    
  
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="load.css"/> 

  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  
 <script src="table.js"></script>
 

    </head>

  <h1 id="title" style="color:green; font-family: Arial" ></h1>
    <body style = "
         
           font-family:sans-serif; size:20px" >


        <% 
            try{
            utils.setapp(application);
            }catch(Exception e){};
            
           
            
           //  ou=main.serviceschedule(trainno,"18-Nov-2020");
            //   ou=main.domaps("02296","11/17/2020"); //same as full schedule
              //   ou=main.fullstations("02296","11/17/2020") ;   //random near real date
              //  ou=main.schedule("02296");
         //     ou=main.trainbtwstns("SC","WL","ALL");
          //  ou=main.traindetails(trainno); 
          
            
            response.setIntHeader("Refresh",1500);
            //show loading
            out.write(" <div class=\"lds-ring\"><div></div><div></div><div></div><div></div></div>");
            
           
            String src=request.getParameter("src").toUpperCase();
             String time=request.getParameter("time");
               String date=request.getParameter("date");
            String dest=request.getParameter("dest").toUpperCase();
              String type=request.getParameter("type");
               String trainno=request.getParameter("trainno");
            
            
            
               
            //convert text to station codes and some declarations
            String s=src;
            String d=dest;
            String ou="nill";  String title="nill"; String json="nill"; String alert="No alerts Defined";String check="nill";
      
            
            
            try{
           src=src.substring(src.lastIndexOf("(") + 1, src.lastIndexOf(")"));}catch(Exception e){src=s;};
          try{dest= dest.substring(dest.lastIndexOf("(") + 1, dest.lastIndexOf(")"));  }catch(Exception e){dest=d;};
        
           
            //live-station
             main m=new main();
             if(type.equals("ls")){
                        ou=main.livestation(src, time,dest);
                         title="Showing Live Trains for "+time+" hrs between  "+src+" and "+dest;
                                  if(dest.equals("")){ title="Showing Live Trains for "+time+" hrs from "+src;} 
              //json to table convert
             json=ou; alert=null;check="yes";
            try{
           JSONObject jsonObject = new JSONObject(json);
          JSONArray inn = jsonObject.getJSONArray("TrainsAtStation");
           alert=jsonObject.getString("AlertMsg").toString();
            if(alert==""){ alert="No alerts to show";}else{  alert="Alert: "+alert; }
             json=inn.toString();
           }catch(Exception e){check="no";};
             
             
             } 
              
             if(type.equals("tti")){
                 
              ou=main.schedule(trainno);
            
             //json to table convert
             json=ou;  alert=null; check="yes";
             try{
           JSONObject jsonObject = new JSONObject(json);
          JSONArray inn = jsonObject.getJSONArray("stations");
          alert=jsonObject.getString("AlertMsg").toString();
            if(alert==""){ alert="No alerts to show";}else{  alert="Alert: "+alert; }
             json=inn.toString();
           title="<pre>Train Name: "+jsonObject.getString("TrainName").toString()+"<br />Train Type: "+jsonObject.getString("TrainTypeDesc").toString()+"<br />Train No:   "+trainno+"<br />Schedule:</pre>";
           }catch(Exception e){check="no";};
             
             
             }
             
              if(type.equals("lts")){
               
                  ou=main.currentstatus(trainno,src,"TD","");
            
             //json to table convert 
             json=ou;  alert=null; check="yes";
             try{
           JSONObject jsonObject = new JSONObject(json);
          JSONArray inn = jsonObject.getJSONArray("STNS");
          alert=jsonObject.getString("AlertMsg").toString();
            if(alert==""){ alert="No alerts to show";}else{  alert="Alert: "+alert; }
            //trying:
            json=inn.toString();
          
             //should update names and codes ,note:remove last n's and convert from json to stnname(stncode)
                title="<pre>Train Name: "+jsonObject.getString("TNM").toString()+"<br />Train No : "+trainno+"<br />Last Update Time: "+jsonObject.getString("LUPDT").toString();
                title=title+"<br />Last Updated Station: "+jsonObject.getString("LSTNN").toString();
                title=title+"<br />Next Station: "+jsonObject.getString("NSTNN").toString();
                 title=title+"<br />Next Train Stop: "+jsonObject.getString("NPSTNN").toString();
                  title=title+"<br />Latest Delay: "+jsonObject.getString("LDEL").toString()+ " Mins";

             
             }catch(Exception e){check="no";
             ou=e.toString();};
             
             
             }
              
              
               if(type.equals("tbs")){
               //  ou=main.findstation("S","C");
                  ou=main.trainbtwstns(src,dest,"ALL");
           
             //json to table convert 
             json=ou;  alert=null; check="yes";
             try{
           JSONObject jsonObject = new JSONObject(json);
          JSONArray inn = jsonObject.getJSONArray("Trains");
          alert=jsonObject.getString("AlertMsg").toString();
            if(alert==""){ alert="No alerts to show";}else{  alert="Alert: "+alert; }
             json=inn.toString();
          
             //should update names and codes , i.e convert from json to stnname(stncode)
                title="<pre>Trains between "+jsonObject.getString("StationTo").toString()+" and "+jsonObject.getString("StationFrom").toString();
                title=title+" for "+jsonObject.getString("TrainTypeDesc").toString();
               
             
             }catch(Exception e){check="no";
             ou=e.toString();};
             
             
             }
              
              
              
             
             
              if(type.equals("irf")){
               htmlmain hm=new htmlmain();
           ou=htmlmain.irftest(trainno, src, dest);
                out.write("<pre style=\"font-family:sans-serif; color:#009B77;font-size:14px; font-weight:bold; \"\">");
            out.write(ou);
               
           out.write("</pre>"); 
           title="Testing Json";
         
              }
              
              
               if(type.equals("train")){
               //    ou=main.serviceschedule(trainno,"18-Nov-2020");
                
                  ou=main.fullstations(trainno,"01/01/2020") ; 
               out.write("<pre style=\"font-family:sans-serif; color:#009B77;font-size:14px; font-weight:bold; \"\">");
         //   out.write(ou);
           out.write("</pre>");
               }


  if(type.equals("fullschedule")){
                ou=main.domaps(trainno,"11/17/2020");
               out.write("<pre style=\"font-family:sans-serif; color:#009B77;font-size:14px; font-weight:bold; \"\">");
         //   out.write(ou);
           out.write("</pre>");
               }
               
               
               
               
               if(type.equals("newirftest")){
             //  ou=newirftest.fullcontent(trainno,"HYB","MAS") ;
            
             String arr[]=htmlmain.newirftest(trainno);
             title="Testing details for "+"Given train no: "+trainno+" <br />Fetched At: "+arr[0];
             title=title+"<br/>Coach Data is: "+arr[2]+"<br/> Train Rakes will be reversed At: "+arr[3]+"<br/> Obtained Train No: "+arr[4];
             title=title+"<br/>Evaluation : "+arr[5];
              title=title+"<br/>Got By : "+arr[6];
              title=title+"<br/>Train Name : "+arr[7];
               title=title+"<br/>Rake type : "+arr[8];
              
                     
             ou=arr[1];
             
             json=ou;check="yes";
             
             
             
            
             
               out.write("<pre style=\"font-family:sans-serif; color:#009B77;font-size:14px; font-weight:bold; \"\">");
           // out.write(ou);
           out.write("</pre>");}
              
               
               
                if(type.equals("fulltrain")){
                
           json=ntesweb.allstationdetails(trainno);
            check="yes";
            }
                
                
                  if(type.equals("mainweb")){
                
             String arr[]=ntesweb.maindetails(trainno);
             title="Testing details for "+"Given train no: "+trainno;
             title=title+"<br/>Train type is: "+arr[1]+"<br/> Train Name is: "+arr[2]+"<br/> Train Runs on: "+arr[3];
             title=title+"<br/>Validity : "+arr[4];
             title=title+"<br/>Day Count : "+arr[5];
               title=title+"<br/>Cancelled Status : "+arr[6];
           
          ou=arr[0];  json=ou; check="yes";
            }
                
               
               
            if(type.equals("trainweb")){
                 String arr[]=ntesweb.fulldetails(trainno);
             title="Testing details for "+"Given train no: "+trainno;
             title=title+"<br/>Train type is: "+arr[1]+"<br/> Train Name is: "+arr[2]+"<br/> Train Runs on: "+arr[3];
             title=title+"<br/>Validity : "+arr[4];
             title=title+"<br/>Day Count : "+arr[5];
              title=title+"<br/>Cancelled Status : "+arr[6];
           
          ou=arr[0];  json=ou; check="yes";
       //  out.write(ou);
       
            }
            
            
             if(type.equals("mixedweb")){
                 String arr[]=ntesweb.mixeddetails(trainno);
               
             title="Testing details for "+"Given train no: "+trainno;
             title=title+"<br/>Train type is: "+arr[1]+"<br/> Train Name is: "+arr[2]+"<br/> Train Runs on: "+arr[3];
             title=title+"<br/>Validity : "+arr[4];
             title=title+"<br/>Day Count : "+arr[5];
               title=title+"<br/>Cancelled Status : "+arr[6];
              title=title+"<br/>Coach Data : "+arr[7];
               title=title+"<br/>Rake  Reversal : "+arr[8];
                title=title+"<br/>Got IRF By : "+arr[9];
                 title=title+"<br/>Rake Type : "+arr[10];
           
          ou=arr[0];  json=ou; check="yes";
       // out.write(ou);
            }
            
             
               if(type.equals("wmtstatus")){
                   
                   //convert date from html format   
                SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                    Date result = formater.parse(date);
              SimpleDateFormat newFormater = new SimpleDateFormat("dd-MM-yyyy");
                date=newFormater.format(result);
                   
               ou=Arrays.toString(ntesweb.wmtstatus(trainno,date));
            check="yes";
            out.write(ou);
            
            }
                if(type.equals("ntesstatus")){
                   
                   //convert date from html format   
                SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                    Date result = formater.parse(date);
              SimpleDateFormat newFormater = new SimpleDateFormat("dd/MM/yyyy");
                date=newFormater.format(result);
                   
               ou=Arrays.toString(ntesweb.ntesstatus(trainno,date));
            check="yes";
            out.write(ou);
            
            }
             
            
     
            
            
            
            
           //irf test
         /*  
           htmlmain hm=new htmlmain();
           String ou=htmlmain.irftest("12760", "WL", "SC"); */
          
            out.write("<pre style=\"font-family:sans-serif; color:#009B77;font-size:14px; font-weight:bold; \"\">");
      //  out.write(ou);
           out.write("</pre>");
           
           

      //   out.write(ou);
           
           //hide loading
            out.println(" <script> document.getElementsByClassName('lds-ring')[0].style.display = 'none'; </script> ");
          
        %>
        <div id="table"></div>
<br/>
<div style="color:red ;font-size:25px" id="alert"></div>
     

<script type="text/javascript">
  
  var e="<%=check%>"; 
if(e=="yes"){  
   var alert="<%=alert%>"; 
  var title="<%=title%>"; 
 var data=<%=json%>;
  
   
 $('#title').html(title);
 $('#alert').append(alert);
 //try catch must because of waste lib else next steps fail
try{ $('#table').createTable(data);}catch(err){}

//removing hindi 
 /* $('#mytab th:nth-child(9), #mytab td:nth-child(9)').remove();
  $('#mytab th:nth-child(10), #mytab td:nth-child(10)').remove();
$('#mytab th:nth-child(14), #mytab td:nth-child(14)').remove(); */
   
 var nums=new Array();
  $( "#mytab thead tr th" ).each(function( index ) {
        var $posit=index+1;
      
 if($( this ).text().toLowerCase().includes("hindi") ||$( this ).text().toLowerCase().includes("shn") )
   {
      console.log( index + ": " + $( this ).text() );
      nums.push($posit)  }
 
});
nums.sort(function(a, b){return b - a});
    console.log(nums);
for (var i = 0; i < nums.length; i++) {
 $posit=nums[i];
  $('#mytab th:nth-child('+$posit+'), #mytab td:nth-child('+$posit+')').remove();
}
    
    
  
}
else
{
     $('#title').text("Enter all the details properly");
}

</script>

    </body> 

</html> 