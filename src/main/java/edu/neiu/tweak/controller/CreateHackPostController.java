package edu.neiu.tweak.controller;

import edu.neiu.tweak.data.HackPostRepository;
import edu.neiu.tweak.model.CreateHackPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/viewmypost/{id}")
    public String showMyPosts(@PathVariable Long id, Model model)
    {
        CreateHackPost post = this.postRepo.findById(id).get();
        model.addAttribute("mypost", post);
        return "view-editpost";
    }

    @GetMapping("/deletepost/{id}")
    public String deletePost(@PathVariable long id)
    {
        this.postRepo.deleteById(id);
        return "redirect:/viewposts";
    }

    @PostMapping
    public String handleHackPostForm(@Valid @ModelAttribute("addPost") CreateHackPost post, Errors error)
    {
        if(error.hasErrors())
        {
            return "add-createhackpost";
        }

        this.postRepo.save(post);
        return "redirect:/viewposts";
    }

    @PostMapping("/editpost/{id}")
    public String handleEditHackPost(@PathVariable long id, @Valid @ModelAttribute("mypost") CreateHackPost post, Errors error)
    {
        if(error.hasErrors())
        {
            return "view-editpost";
        }

        CreateHackPost originalPost = this.postRepo.findById(id).get();
        updateOriginalPost(originalPost, post);
        this.postRepo.save(originalPost);
        return "redirect:/viewposts";
    }

    private void updateOriginalPost(CreateHackPost original, CreateHackPost update)
    {
        original.setTitle(update.getTitle());
        original.setDate((update.getDate()));
        original.setDescription(update.getDescription());
    }
}
