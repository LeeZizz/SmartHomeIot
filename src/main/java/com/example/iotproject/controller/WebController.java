package com.example.iotproject.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @GetMapping("/homepage")
    public String index(){
        return "index";
    }
    @GetMapping("/datasensors")
    public String data(){
        return "datasensors";
    }
    @GetMapping("/profile")
    public String profile(){
        return "profile";
    }
    @GetMapping("/action_history")
    public String action_history(){
        return "action_history";
    }
    @GetMapping("/dashboard")
    public String dashboard(){
        return "dashboard";
    }
}
