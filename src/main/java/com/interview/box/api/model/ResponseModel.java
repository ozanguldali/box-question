package com.interview.box.api.model;


import static com.interview.box.api.util.ObjectMapperUtil.beautifyJsonToString;

public class ResponseModel {

    private ResponseModel responseModel;

    private String message;

    public ResponseModel() {}


    public ResponseModel(String str) {

        responseModel = new ResponseModel();

        message = str;
        responseModel.setMessage( str );

    }

    public void setResponseModel(ResponseModel responseModel) {
        this.responseModel = responseModel;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    @Override
    public String toString() {

        final String str = "{\"message\":\"" + message + "\"}";

        return beautifyJsonToString(str);

    }

}
