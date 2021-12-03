// ********************** User Account Page Controller **********************
package edu.neiu.tweak.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/useraccount")
public class UserAccountController
{
    @GetMapping
    public String showUserAccount()
    {
        return "view-useraccount";
    }
}
