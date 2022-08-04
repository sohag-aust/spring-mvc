package controller;

import model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
    public String complexFormHandler(@ModelAttribute("studentModel") Student student, BindingResult bindingResult) { // BindingResult is used for handling the exceptions
        System.out.println("==** Student from Complex form: " + student);

        if(bindingResult.hasErrors()) {
            return "complexForm";
        }
        return "showComplexFormData";
    }
}
