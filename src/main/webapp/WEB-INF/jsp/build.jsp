
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="my.optimised"%>
<%@page import="my.test"%>
<%@page import="my.dbhelper"%>
<%@page import="my.utils"%>
<%@page import="my.buildhelper"%>
<%@page import="java.util.*"%>
<%
    
   Date sd=new Date();
   long init=sd.getTime();
   
    
    utils.setapp(application);
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
      
      
     
out.write(" Started Building...");
out.write("\n Generating list of trnos...");

/*
ArrayList l=buildhelper.trlist();


out.write("\n list Size is "+l.size());
buildhelper.optbuild(l);


*/

ArrayList l=buildhelper.newlist();
System.out.println("\n list Size is "+l.size());
out.write("\n list Size is "+l.size());
buildhelper.optbuild(l);





s="create table t as select * from routes group by trno;drop table routes;alter table t rename to routes;create table t as select * from trains group by trno;drop table trains;alter table t rename to trains;";
dbhelper.run(s); 
dbhelper.run("VACUUM main;");
dbhelper.close();

out.write("\n min build completed..");  




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
out.write("Completed in "+sec+" Seconds \n Started at "+format.format(sd)+"\n ended at "+format.format(ed));




%>