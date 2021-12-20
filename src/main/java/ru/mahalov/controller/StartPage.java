package ru.mahalov.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartPage {

    @GetMapping()
    public String redirectToStartPage(){
        return "redirect:/person";
    }

}
