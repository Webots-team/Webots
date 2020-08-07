package com.jscorp.webots.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.petersamokhin.bots.sdk.clients.Group;
import com.petersamokhin.bots.sdk.objects.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

/**
 * @author natalya_ezhkova@mail.ru
 */
public class BotController  {
    private RestTemplate restTemplate;

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();


    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("7430646")
    private String clientId;

    @Value("ekGpA32ix03Qix5Og7hz")
    private String clientSecret;

    private static final String VK_KEY = "ecd56cc4cd929e6738e9560e19a01ec6a7e464d6d7407afb7c3738fd319ca180be9cea68c74bfc086f9cb";


    public static void main(String[] args) {



        Group group = new Group(195175028, VK_KEY);
        group.onSimpleTextMessage(message ->
                new Message()
                        .from(group)
                        .to(message.authorId())
                        .text("Тестируется новое приложение API VK.")
                        .send()
        );
    }

}


