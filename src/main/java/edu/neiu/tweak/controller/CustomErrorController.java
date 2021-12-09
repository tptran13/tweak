package edu.neiu.tweak.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/error")
public class CustomErrorController implements ErrorController
{
    @GetMapping
    public String handleError(HttpServletRequest request, Model model)
    {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String message = "";

        if(status != null)
        {
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value())
                message = "Sorry, we could not find that page.";
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value())
                message = "Oops, something went wrong. We are fixing it";
            else
                message = "Oops, something went wrong. Please try again later";
        }

        model.addAttribute("errorMsg", message);
        return "error";
    }
}
