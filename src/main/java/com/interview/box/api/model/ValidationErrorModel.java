package com.interview.box.api.model;

import static com.interview.box.api.util.ObjectMapperUtil.beautifyJsonToString;

public class ValidationErrorModel {

    public static String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        ValidationErrorModel.errorMessage = errorMessage;
    }

    @Override
    public String toString() {

        final String str = "{" +
                "\"message\":\"" + errorMessage + "\"" +
                "}";

        return beautifyJsonToString(str);

    }

}
