package edu.neiu.tweak.controller;

import edu.neiu.tweak.data.HackPostRepository;
import edu.neiu.tweak.model.CreateHackPost;
import edu.neiu.tweak.model.CreateProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/createpost")
public class CreateHackPostController
{
    private HackPostRepository postRepo;

    @Autowired
    public CreateHackPostController(HackPostRepository postRepo)
    {
        this.postRepo = postRepo;
    }

    @GetMapping
    public String getCreateHackPost(Model model)
    {
        model.addAttribute("addPost", new CreateHackPost());
        return "add-createhackpost";
    }

    @PostMapping
    public String handleCreateProfileForm(@Valid @ModelAttribute("addPost") CreateHackPost post, Errors error)
    {
        if(error.hasErrors())
        {
            return "add-createhackpost";
        }

        this.postRepo.save(post);
        return "redirect:viewpostrecords";
    }
}
