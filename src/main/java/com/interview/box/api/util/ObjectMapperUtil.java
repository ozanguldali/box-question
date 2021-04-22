package com.interview.box.api.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import static com.interview.box.api.model.ValidationErrorModel.errorMessage;

public class ObjectMapperUtil {

    public static String beautifyJsonToString(String json) {

        ObjectMapper mapper = new ObjectMapper();
        Object obj = null;
        try {
            obj = mapper.readValue(json, Object.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static String beautifyFormToJson(String form) {

        String decoded;

        try {

            decoded = URLDecoder.decode(form, StandardCharsets.UTF_8.name());

        } catch (UnsupportedEncodingException e) {
            errorMessage = "Unsupported Encoding";
            return null;
        }

        assert decoded != null;
        String[] array = decoded.split( "&" );

        JsonObject jsonObject = new JsonObject();

        for (String s : array) {

            String[] pair = s.split("=");
            String key = pair[0];
            String value;
            if (pair.length < 2) {
                value = "";
            } else {
                value = pair[1];
            }

            jsonObject.addProperty( key, value);

        }

        return jsonObject.toString();

    }

}
