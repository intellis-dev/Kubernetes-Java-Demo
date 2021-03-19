/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author work
 */
public class dbhelper {
    
    static Statement stmt;
   static Connection conn;
     public static void init(String filename) throws ClassNotFoundException, SQLException
    {
       
        String path =null;
         if(utils.flag==false)
        path=utils.getapp().getRealPath("data/"+filename+".db");
         else
          path=utils.getsapp().getRealPath("data/"+filename+".db"); 
        Class.forName("org.sqlite.JDBC");
        conn= DriverManager.getConnection("jdbc:sqlite:"+path);
        stmt = conn.createStatement();
        
        System.out.println("Connection Done");
        
      }
     
     public static void delete(String filename)
     {
        String path =null;
         if(utils.flag==false)
        path=utils.getapp().getRealPath("data/"+filename+".db");
         else
          path=utils.getsapp().getRealPath("data/"+filename+".db"); 
         File f=new File(path);
         if(f.exists())f.delete();
     }
     
     public static void close() throws SQLException
     {
         conn.close();
          System.out.println("Connection Closed");
     }
     
     public static void run(String s) throws SQLException
     {
         stmt.executeUpdate(s);
          
     }
     
     public static ArrayList<String> result(String sql,String get) throws SQLException
     {
        ArrayList op=new ArrayList();
          ResultSet rs = stmt.executeQuery(sql);
      
        int i=0;
      if (rs.next() == false) {
        op.add("ResultSet is empty");
         
      } 
      else
      {

        do {
          String data = rs.getString(get);
          op.add(data);
        } while (rs.next());
      }
      
      return op;
         
     }
     
     
     
     
    
}
