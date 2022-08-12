package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.ActiveMQProducer;
import service.UserService;

import java.util.List;

@Controller
public class UserController {

    private UserService userService;
    private final ActiveMQProducer activeMQProducer;

    public UserController(UserService userService, ActiveMQProducer activeMQProducer) {
        this.userService = userService;
        this.activeMQProducer = activeMQProducer;
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

    @RequestMapping(path = "/sendEmail")
    public String sendEmailToRegisteredUser(Model model) throws JsonProcessingException {
        System.out.println("==** Model in sendEmailToRegisteredUser: " + model);

        FakeUserWithoutAttachment fakeUser = (FakeUserWithoutAttachment) model.asMap().get("registeredUser");
        System.out.println("==** Model for sendEmail: " + fakeUser);

        ActiveMQMessage activeMQMessage = new ActiveMQMessage();
        activeMQMessage.setUserName(fakeUser.getUser_name());
        activeMQMessage.setMailTo(fakeUser.getEmail());

        if(model.getAttribute("sentAttachment") != null && model.getAttribute("sentAttachment").equals("EmailWithAttachment")) {
            activeMQMessage.setMessageOption("EmailWithAttachment");
        } else {
            activeMQMessage.setMessageOption("simpleEmail");
        }

        activeMQProducer.sendMessage(activeMQMessage);

        return "redirect:/showUsers";
    }

    @RequestMapping(path = "/fakeUserForm")
    public String saveFakeUserForm() {
        return "fakeUserForm";
    }

    @RequestMapping(path = "/saveFakeUser", method = RequestMethod.POST)
    public String saveFakeUser(@ModelAttribute FakeUser fakeUser1, RedirectAttributes redirectAttributes, Model model) throws JsonProcessingException {
        System.out.println("==** Saving Fake User: " + fakeUser1);

        FakeUserWithoutAttachment fakeUser = new FakeUserWithoutAttachment();
        fakeUser.setUser_name(fakeUser1.getUser_name());
        fakeUser.setEmail(fakeUser1.getEmail());
        fakeUser.setPassword(fakeUser1.getPassword());
        fakeUser.setAbout(fakeUser1.getAbout());

        System.out.println("==** Final FakeUser Model: " + fakeUser);

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
        FakeUserWithoutAttachment fakeUserResponse = gson.fromJson(output, FakeUserWithoutAttachment.class);

        System.out.println("==** FakeUser from Response: " + fakeUserResponse);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", fakeUserResponse);
        modelAndView.setViewName("showFakeUserData");

        redirectAttributes.addFlashAttribute("registeredUser", fakeUserResponse);
        redirectAttributes.addFlashAttribute("sentAttachment", fakeUser1.getSentAttachment()); // this value will add into model

        return "redirect:/sendEmail";
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
