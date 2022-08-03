package controller;

import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContactController {

    // we can use ModelAttribute inside method level for avoiding setting common data multiple times inside every controller method
    @ModelAttribute
    public void commonDataForModel(Model model) {
        model.addAttribute("title", "Life is too Small");
        model.addAttribute("desc", "Make it simple");
    }

    @RequestMapping("/contact")
    public String showForm() {
        return "contact";
    }

//    @RequestMapping(path = "/processForm", method = RequestMethod.POST)
//    public String getFormData(@RequestParam("email") String email, @RequestParam("password") String password, Model model) { // here key inside RequestParam is linked with name of input inside form
//        System.out.println("==** Get Email from Form: " + email);
//        System.out.println("==** Get Password from Form: " + password);
//
//        model.addAttribute("email", email);
//        model.addAttribute("password", password);
//        return "showFormData";
//    }

    @RequestMapping(path = "/processForm", method = RequestMethod.POST)
    public String getFormDataUsingModelAttribute(@ModelAttribute User user) { // ModelAttribute binds the user object data with form data using user.setter methods, and also it set model.addAttribute, which we have done using (Model model) before

        System.out.println(user);

//        model.addAttribute("user", user);
        return "showFormData";
    }
}
