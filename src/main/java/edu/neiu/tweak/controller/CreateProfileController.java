//****************** User Registration Controller ******************
package edu.neiu.tweak.controller;

import edu.neiu.tweak.data.CreateProfileRepository;
import edu.neiu.tweak.data.HackPostRepository;
import edu.neiu.tweak.model.CreateHackPost;
import edu.neiu.tweak.model.CreateProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/createprofile")
public class CreateProfileController
{
    private CreateProfileRepository profileRepo;
    private BCryptPasswordEncoder passwordEncoder;
    private HackPostRepository postRepo;

    @Autowired
    public CreateProfileController(CreateProfileRepository profileRepo, BCryptPasswordEncoder passwordEncoder, HackPostRepository postRepo)
    {
        this.profileRepo = profileRepo;
        this.passwordEncoder = passwordEncoder;
        this.postRepo = postRepo;
    }

    @GetMapping
    public String getNewProfile(Model model)
    {
        List<CreateHackPost> posts = (List<CreateHackPost>) postRepo.findAll();
        model.addAttribute("myPosts", posts);
        model.addAttribute("addProfile", new CreateProfile());
        return "add-createprofile";
    }

    @GetMapping("/welcome")
    public String displayThankYou(@ModelAttribute("name") Object attr, Model model)
    {
        model.addAttribute("fullname", attr);
        return "/profile-registered";
    }

    private void updateOriginalProfile(CreateProfile update)
    {
        update.setFirstName(update.getFirstName());
        update.setLastName((update.getLastName()));
        update.setEmail(update.getEmail());
    }

    @PostMapping
    public String handleNewProfileForm(@ModelAttribute("addProfile") @Valid CreateProfile profile, Errors error, RedirectAttributes attrs)
    {
        if(error.hasErrors())
        {
            return "add-createprofile";
        }

        try
        {
            //profile to get password and encode it
            profile.setPassword(passwordEncoder.encode(profile.getPassword()));
            profile.setEnabled(true);
            profile.setRoles(Set.of(CreateProfile.Role.ROLE_USER));
            this.profileRepo.save(profile);
        }
        catch(DataIntegrityViolationException err)
        {
            error.rejectValue("email", "invalidEmail", "Email or username is unavailable");
            error.rejectValue("username", "invalidUsername", "Email or username is unavailable");
            return "add-createprofile";
        }

        attrs.addFlashAttribute("name", profile.getFirstName() + " " + profile.getLastName());
        return "redirect:createprofile/welcome";
    }
}


//    @GetMapping
//    public String getCreateProfile(Model model)
//    {
//        model.addAttribute("newProfile", new CreateProfile());
//        return "add-createprofile";
//    }
//
//    @GetMapping("/welcome")
//    public String displayThankYou(@ModelAttribute("name") Object attr, Model model)
//    {
//        model.addAttribute("fullname", attr);
//        return "/profile-registered";
//    }
//
//    @PostMapping
//    public String handleCreateProfileForm(@ModelAttribute("newProfile") CreateProfile profile, RedirectAttributes attrs)
//    {
//        System.out.println("First name: " + profile.getFirstName());
//        System.out.println("Last name: " + profile.getLastName());
//        System.out.println("Date of Birth:" + profile.getDateOfBirth());
//        System.out.println("Username: " + profile.getUsername());
//        System.out.println("Password: " + profile.getPassword());
//        System.out.println("Email: " + profile.getEmail());
//        attrs.addFlashAttribute("name", profile.getFirstName() + " " + profile.getLastName());
//        return "redirect:/createprofile/welcome";
//    }