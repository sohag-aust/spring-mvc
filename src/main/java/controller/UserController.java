package controller;

import model.UserOrm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.UserService;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute
    public void commonDataForModel(Model model) {
        model.addAttribute("title", "Life is too Small");
        model.addAttribute("desc", "Make it simple");
    }

    @RequestMapping(path = "/processForm", method = RequestMethod.POST)
    public String getFormDataUsingModelAttribute(@ModelAttribute UserOrm user, Model model) { // ModelAttribute binds the user object data with form data using user.setter methods, and also it set model.addAttribute, which we have done using (Model model) before

        System.out.println("user from contact page: " + user);

        int userId = this.userService.createUser(user);
        model.addAttribute("msg", "user created successfully with id: " + userId);
        return "showFormData";
    }
}
