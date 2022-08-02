package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @RequestMapping("/home")
    public String home(Model model) {
        System.out.println("==** This is Home Page **==");
        model.addAttribute("name", "Md.Ashraful Islam Shohag");
        return "home";
    }

    @RequestMapping("/about")
    public String about(Model model) {
        System.out.println("==** This is About Page **==");

        List<String> info = new ArrayList<>();
        info.add("Name: " + "Md.Ashraful Islam Shohag");
        info.add("Id: " + "019");
        info.add("Friend Name: " + "Ashik");

        model.addAttribute("info", info);
        return "about";
    }
}
