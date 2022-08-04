package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import model.JsonPlaceHolderData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class SearchController {

    @RequestMapping("/search")
    public RedirectView searchPage(@RequestParam("querybox") String query) {
        String url = "https://www.google.com/search?q=" + query;  // searching using google

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(url);
        return redirectView;
    }

    @RequestMapping("/fakeData")
    public ModelAndView fakeData() throws JsonProcessingException {
        String fakeUrl = "https://jsonplaceholder.typicode.com/todos/1";

        Client client = Client.create();
        WebResource webResource = client.resource(fakeUrl);
        ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);

        String output = response.getEntity(String.class);

        System.out.println("==** Data in string : " + output);

        ObjectMapper objectMapper = new ObjectMapper();

        JsonPlaceHolderData jsonPlaceHolderData = objectMapper.readValue(output, new TypeReference<JsonPlaceHolderData>(){});

        System.out.println("==** Data from fake JsonPlaceholder: " + jsonPlaceHolderData);


        ModelAndView modelAndView = new ModelAndView();
        // setting the data
        modelAndView.addObject("jsonFakeApiData", jsonPlaceHolderData);

        // setting the jsp page name
        modelAndView.setViewName("externalApiData");

        return modelAndView;
    }
}
