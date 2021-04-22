package com.interview.box.api.model;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import static com.interview.box.api.model.ValidationErrorModel.errorMessage;
import static util.LoggerUtil.logger;

public class RequestModel {

    private final static JsonParser jsonParser = new JsonParser();

    public RequestModel(String payload) {

        errorMessage = null;

        JsonObject jsonObject = new JsonObject();

        try {

            jsonObject = jsonParser.parse( payload ).getAsJsonObject();

        } catch (Exception e) {

            logger.error( "Request is not a valid Json Object:\t" + e.getMessage() );
            errorMessage = "Request is not a valid Json Object";

        }

        try {

            boxValue = jsonObject.get( "boxValue" ).getAsString();

        } catch (Exception e) {

            logger.trace( "Could not get value." );

        }

    }

    private String boxValue;

    public String getBoxValue() {
        return String.valueOf(boxValue);
    }

    private void setBoxValue(String boxValue) {
        this.boxValue = boxValue;
    }

}
