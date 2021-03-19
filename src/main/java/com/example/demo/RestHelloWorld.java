package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.text.SimpleDateFormat;
import my.test.*;
import my.dbhelper.*;
import my.utils.*;
import my.utils;
import my.dbhelper;
import my.buildhelper.*;
import my.buildhelper;
import my.optimised;
import java.util.*;







@RestController
public class RestHelloWorld {








String ou="";



public void write(String s)
{
ou=ou+s;
}




	public void dothis() throws Exception{

  Date sd=new Date();
   long init=sd.getTime();
   
    
 //   utils.setapp(application);
    dbhelper.delete("minfile");
    String s="CREATE TABLE IF NOT EXISTS trains (\n" +
"    trno varchar(20),\n" +"traintype varchar(40),\n" +"validity varchar(90),\n" +
"    trname varchar(255),\n" +"    runson varchar(40),\n" +
"    stnlist varchar(7255),\n" +
"    cancelled varchar(20),\n" +"coach varchar(300),\n" +"reversal varchar(90),\n" +"arr TEXT,\n" +"dep TEXT, dist TEXT,pf TEXT,arrexp TEXT,depexp TEXT,rake TEXT" +");";
    
   
        dbhelper.init("minfile");
      dbhelper.run(s);
      
       s="drop table if exists routes;";
      dbhelper.run(s);
      
      s="create table if not exists routes(trno TEXT,stcode TEXT,dist TEXT);";
      dbhelper.run(s);
      
      
     
write(" Started Building...");
write("\n Generating list of trnos...");
ArrayList l=buildhelper.trlist();


write("\n list Size is "+l.size());
buildhelper.optbuild(l);

s="create table t as select * from routes group by trno;drop table routes;alter table t rename to routes;create table t as select * from trains group by trno;drop table trains;alter table t rename to trains;";
dbhelper.run(s); 
dbhelper.run("VACUUM main;");
dbhelper.close();

write("\n min build completed..");  



//System.out.println(Arrays.asList(optimised.ntesmin("02760")));


  
  
  
  
  /*
ArrayList l=new ArrayList();
l.add("12723");
l.add("67266"); 
l.add("02760");
buildhelper.optbuild(l); 
*/


 Date ed=new Date();
   long end=ed.getTime();
   SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy HH:mm");
         format.setTimeZone(TimeZone.getTimeZone("IST"));
     
         int sec=(int)((end-init)/1000);
write("Completed in "+sec+" Seconds \n Started at "+format.format(sd)+"\n ended at "+format.format(ed));



}




        @GetMapping("/")
        public String sayHello() {


String trainno="02760";
String title="";


try{

//dothis();

}catch(Exception e)
{
e.printStackTrace();
}

                return ou;
        }




















}
