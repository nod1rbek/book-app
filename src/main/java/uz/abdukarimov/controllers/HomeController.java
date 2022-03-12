package uz.abdukarimov.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HomeController {

    @RequestMapping(value = {"/","/home"})
    public String homePage() {
        return "home";
    }
}
