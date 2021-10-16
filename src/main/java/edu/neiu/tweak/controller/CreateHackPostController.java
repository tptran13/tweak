package edu.neiu.tweak.controller;

import edu.neiu.tweak.model.CreateHackPost;
import edu.neiu.tweak.model.CreateProfile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/createpost")
public class CreateHackPostController
{
    @GetMapping
    public String getCreateHackPost(Model model)
    {
        model.addAttribute("addPost", new CreateHackPost());
        return "add-createhackpost";
    }

    @PostMapping
    public String handleCreateProfileForm(@ModelAttribute("addPost") CreateHackPost post)
    {
        System.out.println("Title: " + post.getTitle());
        System.out.println("Date Created: " + post.getDate());
        System.out.println("Description:" + "\n" + post.getDescription());

        return "redirect:/";
    }
}
