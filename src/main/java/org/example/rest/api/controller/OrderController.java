package org.example.rest.api.controller;

import com.google.gson.Gson;
import io.restassured.response.Response;
import org.example.rest.api.request.impl.OrderRequest;
import org.example.rest.dto.Order;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class OrderController extends Controller {

    private final Gson gson = new Gson();
    private final Map<String, String> headers = new HashMap<>();

    public OrderController(String token) {
        Properties properties = getProperties();
        this.baseUrl = properties.getProperty("baseUrl") + "/orders";
        headers.put("Content-type", "application/json");
        headers.put("Authorization", "Bearer " + token);
    }

    public Order postNewOrder(Order body) {
        OrderRequest request = new OrderRequest(headers);
        String stringRequestBody = gson.toJson(body);
        Response response = request.post(stringRequestBody, baseUrl);
        return gson.fromJson(response.body().asString(), Order.class);
    }

    public Order getOrderById(String orderId) {
        OrderRequest request = new OrderRequest(headers);
        Response responseOrderById = request.get(baseUrl + "/" + orderId);
        return gson.fromJson(responseOrderById.body().asString(), Order.class);
    }

    public Boolean deleteOrderById(String orderId) {
        OrderRequest request = new OrderRequest(headers);
        Response response = request.delete(baseUrl + "/" + orderId);
        return Boolean.valueOf(response.body().asString());
    }
}
