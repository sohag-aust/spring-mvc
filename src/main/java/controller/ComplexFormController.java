package controller;

import model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ComplexFormController {

    @RequestMapping("/complexForm")
    public String showComplexForm() {
        return "complexForm";
    }

    @RequestMapping(path = "/handleComplexForm", method = RequestMethod.POST)
    public String complexFormHandler(@ModelAttribute Student student) {
        System.out.println("==** Student from Complex form: " + student);
        return "showComplexFormData";
    }
}
