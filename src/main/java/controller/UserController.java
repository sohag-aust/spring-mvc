package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import model.FakeUser;
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

        if(user.getEmail().equals("abc@gmail.com")) {
            return "redirect:/home";
        }

        int userId = this.userService.createUser(user);
        model.addAttribute("msg", "user created successfully with id: " + userId);
        return "showFormData";
    }

    @RequestMapping(path = "/fakeUserForm")
    public String saveFakeUserForm() {
        return "fakeUserForm";
    }

    @RequestMapping(path = "/saveFakeUser", method = RequestMethod.POST)
    public String saveFakeUser(@ModelAttribute FakeUser fakeUser) throws JsonProcessingException {
        System.out.println("==** Saving Fake User: " + fakeUser);

        String BASE_URL = "http://localhost:8081/api/users/saveFakeUser";

        ObjectMapper objectMapper = new ObjectMapper();

        String convertObjectToJson = objectMapper.writeValueAsString(fakeUser);
        System.out.println("==** convertObjectToJson: " + convertObjectToJson);

        Client client = Client.create();
        WebResource webResource = client.resource(BASE_URL);

        // POST method
        ClientResponse response = webResource
                .header("Content-Type", "application/json")
                .post(ClientResponse.class, convertObjectToJson);

        System.out.println("==** Response : " + response);

        String output = response.getEntity(String.class);
        System.out.println("==** Output from Response: " + output);

        Gson gson = new Gson();
        FakeUser fakeUserResponse = gson.fromJson(output, FakeUser.class);

        System.out.println("==** FakeUser from Response: " + fakeUserResponse);

        return "showFakeUserData";
    }
}
