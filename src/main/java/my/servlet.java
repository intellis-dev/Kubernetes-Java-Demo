/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my;
import javax.servlet.http.*;  
import javax.servlet.*;  
import java.io.*;  
public class servlet implements jakarta.servlet.Servlet{  
public void doGet(HttpServletRequest req,HttpServletResponse res)  
throws ServletException,IOException  
{  
res.setContentType("text/html");//setting the content type  
PrintWriter pw=res.getWriter();//get the stream to write the data  
  
//writing html in the stream  
pw.println("<html><body>");  
pw.println("Welcome to servlet");  
pw.println("</body></html>");  
  
pw.close();//closing the stream  
}

    @Override
    public void init(jakarta.servlet.ServletConfig sc) throws jakarta.servlet.ServletException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public jakarta.servlet.ServletConfig getServletConfig() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void service(jakarta.servlet.ServletRequest sr, jakarta.servlet.ServletResponse sr1) throws jakarta.servlet.ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getServletInfo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void destroy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}  