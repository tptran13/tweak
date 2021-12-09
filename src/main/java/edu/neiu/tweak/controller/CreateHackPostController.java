//******************** Post/Blog Controller ********************
package edu.neiu.tweak.controller;

import com.cloudinary.utils.ObjectUtils;
import edu.neiu.tweak.config.CloudinaryConfig;
import edu.neiu.tweak.data.CreateProfileRepository;
import edu.neiu.tweak.data.HackPostRepository;
import edu.neiu.tweak.model.CreateHackPost;
import edu.neiu.tweak.model.CreateProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/createpost")
public class CreateHackPostController
{
    private HackPostRepository postRepo;
    private CloudinaryConfig cloudConfig;

    @Autowired
    public CreateHackPostController(HackPostRepository postRepo, CloudinaryConfig cloudConfig)
    {
        this.postRepo = postRepo;
        this.cloudConfig = cloudConfig;
    }

    @GetMapping
    public String getCreateHackPost(Model model)
    {
        model.addAttribute("addPost", new CreateHackPost());
        return "add-createhackpost";
    }

    @GetMapping("/viewmypost/{id}")
    public String showPosts(@PathVariable Long id, Model model)
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
    public String handleHackPostForm(@Valid @ModelAttribute("addPost") CreateHackPost post, Errors error,
                                     @RequestParam("imageFile")MultipartFile imageFile, @AuthenticationPrincipal CreateProfile profile)
    {
        if(error.hasErrors())
        {
            return "add-createhackpost";
        }

        //import ObjectUtils from cloudinary util
        try
        {

            Map<String, Object> uploaded = this.cloudConfig.upload(imageFile.getBytes(), ObjectUtils.asMap("resource", "auto"));
            if(profile == null)
                return "add-createhackpost";

            post.setImage(uploaded.get("url").toString());
            post.setProfile(profile);
            this.postRepo.save(post);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            error.rejectValue("image", "invalidImage", "There is an error with the image, please try uploading again.");
            return "add-createhackpost";
        }

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
        update.onUpdate();
        original.setDate((update.getModified().toString()));
        original.setDescription(update.getDescription());
    }
}

//        if(imageFile.isEmpty())
//        {
//            error.rejectValue("image", "invalidImage", "Please upload an image");
//            return "add-createhackpost";
//        }
