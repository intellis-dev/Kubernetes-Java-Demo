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



@Controller
public class AppController {




@RequestMapping("/test")
public String test() {
    return "test.jsp";
}



@RequestMapping("/build")
public String t() {
    return "build.jsp";
}



@RequestMapping("/index")
public String ind() {
    return "index.jsp";
}


}





















