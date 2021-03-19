<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ page import="cris.icms.ntes.*" %>
<%@ page import="android.util.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>

<%@ page import="my.*" %>
<!DOCTYPE html>


    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="load.css"/> 
        
         <%
    String in = application.getRealPath("/WEB-INF/stationcodes.txt");
    BufferedReader br = new BufferedReader(new FileReader(in));
    String line="";
    String suggest="";
    while ((line = br.readLine()) != null) {
suggest=suggest+line;
}
br.close();

%>
        
        
       <style>
           
           @media only screen and (min-width : 320px) and (max-width : 480px) {
	background: url('mobile.jpg') no-repeat center center fixed;
}
input[type='text'] { font-size: 18px; color:chocolate }
</style>


<!-- hiding based on options -->
<script>
    
    
    function check(that) {
        
                  /*   if (that.value == "none") {
          document.getElementById("time").style.display = "none";
 document.getElementById("source").style.display = "none";
  document.getElementById("destination").style.display = "none";
    document.getElementById("trainno").style.display = "none";
    } */
    if (that.value == "ls") {
          document.getElementById("time").style.display = "block";
 document.getElementById("source").style.display = "block";
  document.getElementById("destination").style.display = "block";
    document.getElementById("trainno").style.display = "none";
     document.getElementById("date").style.display = "none";
     
    } if (that.value == "tti" || that.value == "lts" || that.value == "irf" || that.value == "newirftest" || that.value == "trainweb" || that.value == "fulltrain" || that.value == "mixedweb"  || that.value == "mainweb") {
         document.getElementById("trainno").style.display = "block";
        document.getElementById("time").style.display = "none";
           document.getElementById("source").style.display = "none";
         document.getElementById("destination").style.display = "none";
          document.getElementById("date").style.display = "none";
   
    }
     if (that.value == "lts") {
         document.getElementById("trainno").style.display = "block";
        document.getElementById("time").style.display = "none";
           document.getElementById("source").style.display = "block";
         document.getElementById("destination").style.display = "none";
          document.getElementById("date").style.display = "none";
   
    }
    if (that.value == "tbs"){
          document.getElementById("time").style.display = "none";
        document.getElementById("source").style.display = "block";
         document.getElementById("destination").style.display = "block";
          document.getElementById("trainno").style.display = "none";
           document.getElementById("date").style.display = "none";
    }
    
    if (that.value == "wmtstatus" || that.value == "ntesstatus"){
         document.getElementById("trainno").style.display = "block";
        document.getElementById("time").style.display = "none";
           document.getElementById("source").style.display = "none";
         document.getElementById("destination").style.display = "none";
          document.getElementById("date").style.display = "block";
    }
    
    
    
}
    </script>
    
    <!-- Auto Complete -->
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
 
     
     $( function() {
      
    $("#src").keydown(function(){
        var sp=$(this).val();
        var s="";
       s= sp.substring(
    sp.lastIndexOf("(") + 1, 
    sp.lastIndexOf(")")
);
   $("#htag1").text(s);
   
});


$("#dest").keydown(function(){
        var sp=$(this).val();
        var s="";
       s= sp.substring(
    sp.lastIndexOf("(") + 1, 
    sp.lastIndexOf(")")
);
   $("#htag2").text(s);
   
});



    
         var suggest=<%=suggest%>;
    $( "#src" ).autocomplete({
      source: suggest
    });
     $( "#dest" ).autocomplete({
      source: suggest
    });
  } );
    
 
  
</script>

<style>
    .ui-autocomplete {   list-style: none;
    list-style-type: none;
    width:100px;
        background:whitesmoke;
    color:chocolate; font-size: 16px;font-family:sans-serif; }
    
    .ui-helper-hidden-accessible { display:none; }
	  
	  </style>
   
    <script language="javascript"> 

function init(){ 
   document.getElementById("time").style.display = "none";
 document.getElementById("source").style.display = "none";
  document.getElementById("destination").style.display = "none";
    document.getElementById("trainno").style.display = "none";
     
} 

</script> 
    
    
       
    </head>
    
    <body  onload="init()" style = "background-image: url('newrail2.jpg');  background-repeat: no-repeat;
  background-size:cover; background-attachment: fixed;  overflow:hidden;" >
        
        
        
      
        
        
        
        
         <form   style = "background-image: url('background.jpg');   
            margin: auto; width:fit-content; height:fit-content; display:inline-block; border: 3px solid green;  padding: 10px;
    text-align:center;   color:#339900;font-weight:bold; font-family:sans-serif; align:center;display: block;margin-top:125px; font-size:30px"  action = "test.jsp" method = "GET">
             
             
               <select  name="type" style=" font-size: 24px; color:chocolate" onchange="check(this);">
          <option value="none" selected="selected" hidden="hidden">Choose Option</option>
                   <option value="ls">Live Station</option>
              <option value="tti">Total Train Info</option>
                <option value="lts">Live Train Status</option>
                  <option value="irf">IRF test</option>
                    <option value="newirftest">New IRF test</option>
                    <option value="trainweb"> Train's Total Schedule(WEB) </option>
                       <option value="mixedweb"> Train's Mixed Details(WEB) </option>
                         <option value="mainweb"> Train's Main Details(WEB) </option>
                    <option value="fulltrain">Train's All Station Data(WEB)</option>
                    <option value="wmtstatus">Train's Status (WMT)</option>
                     <option value="ntesstatus">Train's Status (NTES)</option>
              <option value="tbs">Trains between Stations</option>  </select> <br /> <br /> 
           <div id="time" style=" font-size: 18px; color:blue" >   
         Time:  <input type = "radio" name = "time" value="2"  > 2 Hrs
         <input type = "radio" name = "time" value="4"  > 4 Hrs
         <input type = "radio" name = "time" value="8"  > 8 Hrs
         <br /> <br/>  </div>
               <div id="source">   
                   Source:   <br /><p id="htag1" style=" display: inline-block; color:orangered; font-size: 14px; font:sans-serif; " >
                       
                   </p> <input type = "text" name = "src" id="src" placeholder="Enter Source station" />
              <br /> <br/> </div>
               <div id="destination">   
                   Destination:   <br /> <p id="htag2" style=" display: inline-block; color:orangered; font-size: 14px; font:sans-serif; ">
                </p>   <input type = "text" name = "dest" id="dest" placeholder="Enter Destination station" />
          <br />  <br/>  </div> 
               <div id="trainno" style="display:none">   
         Train Number   <br /> <input type = "text" name = "trainno" placeholder="Enter Train Number" />
          <br />  <br/>  </div> 
              
               <div id="date" style="display:none">   
         Start Date   <br /> <input type = "date" name = "date" placeholder="Select Date" />
          <br />  <br/>  </div> 
              
            <input type = "submit" style="border-radius:50px;font-weight:bold; font-family:sans-serif; font-size:20px; border: 1.7px solid green; " value = "Submit" />
 
         
         </form>
       
    </body>

