package edu.neiu.tweak.controller;

import edu.neiu.tweak.model.CreateProfile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/createprofile")
public class CreateProfileController
{
    @GetMapping
    public String getCreateProfile(Model model)
    {
        model.addAttribute("newProfile", new CreateProfile());
        return "add-createprofile";
    }

    @PostMapping
    public String handleCreateProfileForm(@ModelAttribute("newProfile") CreateProfile profile)
    {
        System.out.println("First name: " + profile.getFirstName());
        System.out.println("Last name: " + profile.getLastName());
        System.out.println("Date of Birth:" + profile.getDateOfBirth());
        System.out.println("Username: " + profile.getUsername());
        System.out.println("Password: " + profile.getPassword());
        System.out.println("Email: " + profile.getEmail());

        return "redirect:/";
    }
}
