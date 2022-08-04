package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class RedirectController {

    @RequestMapping("/one")
    public String one() {
        System.out.println("==** Inside Url: /one **==");
        return "redirect:/enjoy"; // redirect prefix is used for redirecting to another url
    }

    // another redirection technique
    @RequestMapping("/two")
    public RedirectView two() {
        System.out.println("==** Inside Url: /two **==");

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("enjoy"); // always put url_name only not with /url_name
        return redirectView; // redirectView object is returned for redirecting to another url
    }

    @RequestMapping("/enjoy")
    public String enjoy() {
        System.out.println("==** Inside Url: /enjoy **==");
        return "home";
    }
}
