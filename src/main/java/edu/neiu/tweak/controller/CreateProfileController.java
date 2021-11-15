package edu.neiu.tweak.controller;

import edu.neiu.tweak.data.CreateProfileRepository;
import edu.neiu.tweak.model.CreateProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
@RequestMapping("/createprofile")
public class CreateProfileController
{
    private CreateProfileRepository profileRepo;

    @Autowired
    public CreateProfileController(CreateProfileRepository profileRepo)
    {
        this.profileRepo = profileRepo;
    }

    @GetMapping
    public String getNewProfile(Model model)
    {
        model.addAttribute("addProfile", new CreateProfile());
        return "add-createprofile";
    }

    @GetMapping("/welcome")
    public String displayThankYou(@ModelAttribute("name") Object attr, Model model)
    {
        model.addAttribute("fullname", attr);
        return "/profile-registered";
    }

    @PostMapping
    public String handleNewProfileForm(@Valid @ModelAttribute("addProfile") CreateProfile profile, RedirectAttributes attrs, Errors error)
    {
        if(error.hasErrors())
        {
            return "add-createprofile";
        }

        try
        {
            this.profileRepo.save(profile);
        }
        catch (DataIntegrityViolationException err)
        {
            error.rejectValue("email", "invalidEmail", "Email is taken, please try another one.");
            return "redirect:add-createprofile";
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