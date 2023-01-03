package org.example.rest.api.controller;

import com.google.gson.Gson;
import io.restassured.response.Response;
import org.example.rest.api.request.impl.AuthorizationRequest;
import org.example.rest.dto.User;

import java.util.Properties;

public class LoginController extends Controller {
    private final String username;
    private final String password;

    public LoginController(){
        Properties properties = getProperties();
        this.baseUrl = properties.getProperty("baseUrl") + "/login/student";
        this.username = properties.getProperty("username");
        this.password = properties.getProperty("password");
    }

    public String getUser() {
        User user = new User(username, password);
        Gson gson = new Gson();
        return gson.toJson(user);
    }

    public String getToken() {
        AuthorizationRequest request = new AuthorizationRequest();
        Response response = request.post(getUser(), baseUrl);
        return response.body().asString();
    }
}
