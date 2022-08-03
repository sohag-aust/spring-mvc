package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContactController {

    @RequestMapping("/contact")
    public String showForm() {
        return "contact";
    }

    @RequestMapping(path = "/processForm", method = RequestMethod.POST)
    public String getFormData(@RequestParam("email") String email, @RequestParam("password") String password, Model model) { // here key inside RequestParam is linked with name of input inside form
        System.out.println("==** Get Email from Form: " + email);
        System.out.println("==** Get Password from Form: " + password);

        model.addAttribute("email", email);
        model.addAttribute("password", password);
        return "showFormData";
    }
}
