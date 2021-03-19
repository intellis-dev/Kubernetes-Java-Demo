/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my;

import static cris.icms.ntes.htmlmain.jsongoogle;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import static my.ntesweb.stringaturl;
import static my.optimised.analysed;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author work
 */
public class temptester {
    public static void main(String args[]) throws Exception
    {
        
     //   System.out.println(Arrays.toString(optimised.ntesfull("02798","new")));
        
      /*  String url="https://whereismytrain.in/get_ltlg?cellinfo=405_854_34_5394724";
          Document doc = Jsoup.connect(url).maxBodySize(0).header("Accept-Encoding", "gzip, deflate").ignoreContentType(true).get();
        String txt=doc.text();
        int i=txt.indexOf("\"lat\": ")+7;
        String lat=txt.substring(i,txt.indexOf(",",i));
        i=txt.indexOf("\"lng\": ")+7;
        String lang=txt.substring(i,txt.indexOf("}",i));
       
       System.out.println(lat+" "+lang); */
       // System.out.println(ntesweb.findindex("12760", "WL", "20 Nov 2020"));
        
   
    /*    String txt=stringaturl("https://enquiry.indianrail.gov.in/ntes/NTES?action=getTrainData&trainNo=00121");
        HashMap h=optimised.pfdata(txt);
        if(h.size()==0)
    System.out.println("im");
        System.out.println(Arrays.asList(h)); */
       // analysed("02760");
        
       
    }
}
