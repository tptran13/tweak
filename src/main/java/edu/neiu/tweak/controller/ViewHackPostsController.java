//********************** Retrieve All Posts from Repository **********************
package edu.neiu.tweak.controller;

import edu.neiu.tweak.data.HackPostRepository;
import edu.neiu.tweak.model.CreateHackPost;
import edu.neiu.tweak.model.CreateProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/viewposts")
public class ViewHackPostsController
{
    private HackPostRepository postRepository;

    @Autowired
    public ViewHackPostsController(HackPostRepository postRepository)
    {
        this.postRepository = postRepository;
    }

    @GetMapping
    public String showHackPost(Model model, @AuthenticationPrincipal CreateProfile profile)
    {
        Set<CreateHackPost> posts = this.postRepository.findAllByProfile(profile);
        model.addAttribute("hackposts", posts);
        return "display-createhackposts";
    }
}
