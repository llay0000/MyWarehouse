package org.example.mywarehouseclient.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.time.LocalDate;

public class JsonService {
    private final Gson gson;

    public JsonService() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter());
        this.gson = gsonBuilder.create();
    }

    public <T> T getObject(String response, Type type) {
        System.out.println(response);
        return gson.fromJson(response, type);
    }

    public <T> String getJson(T data) {
        return gson.toJson(data);
    }
}

