package edu.neiu.tweak.controller;

import edu.neiu.tweak.data.HackPostRepository;
import edu.neiu.tweak.model.CreateHackPost;
import edu.neiu.tweak.model.CreateProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController
{
    private HackPostRepository postRepository;

    @Autowired
    public HomeController(HackPostRepository postRepository)
    {
        this.postRepository = postRepository;
    }

    @GetMapping
    public String showHackPostInIndex(Model model)
    {
        List<CreateHackPost> posts = (List<CreateHackPost>) this.postRepository.findAll();
        model.addAttribute("hackposts", posts);
        return "index-page";
    }
}
