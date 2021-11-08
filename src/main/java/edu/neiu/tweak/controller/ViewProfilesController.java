package edu.neiu.tweak.controller;

import edu.neiu.tweak.data.CreateProfileRepository;
import edu.neiu.tweak.model.CreateProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/profilerecords")
public class ViewProfilesController
{
    private CreateProfileRepository profilesRepo;

    @Autowired
    public ViewProfilesController(CreateProfileRepository profilesRepo)
    {
        this.profilesRepo = profilesRepo;
    }

    @GetMapping
    public String showProfile(Model model)
    {
        List<CreateProfile> profiles = (List<CreateProfile>) this.profilesRepo.findAll();
        model.addAttribute("newProfiles", profiles);
        return "display-createprofiles";
    }
}
