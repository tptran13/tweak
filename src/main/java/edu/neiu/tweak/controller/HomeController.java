package edu.neiu.tweak.controller;

import edu.neiu.tweak.model.CreateProfile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController
{
    @GetMapping
    public String getHomePage()
    {
        return "index-page";
    }
}
