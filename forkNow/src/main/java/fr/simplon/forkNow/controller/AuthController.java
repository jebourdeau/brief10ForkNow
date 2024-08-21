package fr.simplon.forkNow.controller;

import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
    
    @Authowired



    @GetMapping("/")
    public String home(Model model, Authentication authentication){
      Optional<User> user = userService.from(authentication);
      if(user.ispresent()){
        model.addAttribute("currentUser", user.get());
      }  return "index";
    }

    @GetMapping("/register")
    public String register(Model model){
        RegisterDto user = new RegisterDto();
        model.addAttribute("user", user); 
    } return "register";

    @GetMapping("/restaurant")
    public String restaurant(){
    }return "restaurant";




}
