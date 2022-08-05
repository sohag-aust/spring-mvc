package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import model.FakeUser;
import model.FakeUserModel;
import model.JsonPlaceHolderData;
import model.UserOrm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import service.UserService;

import java.util.List;

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
    public ModelAndView saveFakeUser(@ModelAttribute FakeUser fakeUser) throws JsonProcessingException {
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

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", fakeUserResponse);
        modelAndView.setViewName("showFakeUserData");

        return modelAndView;
    }

    @RequestMapping("/showUsers")
    public ModelAndView getAllUsers() throws JsonProcessingException {
        String BASE_URL = "http://localhost:8081/api/users/";

        Client client = Client.create();
        WebResource webResource = client.resource(BASE_URL);
        ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);

        String output = response.getEntity(String.class);

        ObjectMapper objectMapper = new ObjectMapper();

        List<FakeUserModel> users = objectMapper.readValue(output, new TypeReference<List<FakeUserModel>>(){});
        System.out.println("==** All Users Data: " + users);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", users);
        modelAndView.setViewName("showAllUser");

        return modelAndView;
    }

    @RequestMapping("/editUserById/{userId}")
    public ModelAndView getUserById(@PathVariable Long userId) throws JsonProcessingException {
        String BASE_URL = "http://localhost:8081/api/users/"+userId;

        Client client = Client.create();
        WebResource webResource = client.resource(BASE_URL);
        ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);

        String output = response.getEntity(String.class);

        ObjectMapper objectMapper = new ObjectMapper();

        FakeUserModel user = objectMapper.readValue(output, new TypeReference<FakeUserModel>(){});
        System.out.println("==** User Data by Id: " + user);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userById", user);
        modelAndView.setViewName("editUserForm");
        return modelAndView;
    }

    @RequestMapping(path = "/editUserById/updateFakeUser", method = RequestMethod.POST)
    public String updateFakeUser(@ModelAttribute FakeUserModel fakeUserModel) throws JsonProcessingException {

        System.out.println("==** Updating Starts **==");
        System.out.println("==** ModelAttribute FakeUserModel: " + fakeUserModel);

        FakeUser fakeUser = new FakeUser();
        fakeUser.setUser_name(fakeUserModel.getUser_name());
        fakeUser.setEmail(fakeUserModel.getEmail());
        fakeUser.setPassword(fakeUserModel.getPassword());
        fakeUser.setAbout(fakeUserModel.getAbout());

        System.out.println("==** Updating Fake User: " + fakeUser);

        String BASE_URL = "http://localhost:8081/api/users/updateFakeUser/"+fakeUserModel.getId();

        ObjectMapper objectMapper = new ObjectMapper();

        String convertObjectToJson = objectMapper.writeValueAsString(fakeUser);
        System.out.println("==** convertObjectToJson: " + convertObjectToJson);

        Client client = Client.create();
        WebResource webResource = client.resource(BASE_URL);

        // POST method
        ClientResponse response = webResource
                .header("Content-Type", "application/json")
                .put(ClientResponse.class, convertObjectToJson);

        System.out.println("==** Response : " + response);

        String output = response.getEntity(String.class);
        System.out.println("==** Output from Response: " + output);

        Gson gson = new Gson();
        FakeUser fakeUserResponse = gson.fromJson(output, FakeUser.class);

        System.out.println("==** FakeUser from Response: " + fakeUserResponse);

        return "redirect:/showUsers"; // redirect to showUsers URL
    }


    @RequestMapping(path = "/deleteUserById/{userId}") // by default delete works on GET method from jsp
    public String delete(@PathVariable Long userId) {

        System.out.println("==** Inside Delete Method **==");
        String BASE_URL = "http://localhost:8081/api/users/"+userId;

        Client client = Client.create();
        WebResource webResource = client.resource(BASE_URL);
        ClientResponse response = webResource.delete(ClientResponse.class);
        System.out.println("==** Response for delete: " + response);

        return "redirect:/showUsers";
    }
}
