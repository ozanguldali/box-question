package com.interview.box.api.service;

import com.interview.box.api.model.RequestModel;
import com.interview.box.api.model.ResponseModel;
import com.interview.box.api.model.ValidationErrorModel;
import org.springframework.stereotype.Service;

import static util.LoggerUtil.logger;

@Service
public class BoxService {

    public static RequestModel requestModel;

    public static ResponseModel getResponse(String payload) {

        requestModel = new RequestModel( payload );

        String boxValue;
        String message;

        try {

            boxValue = requestModel.getBoxValue();

        } catch (Exception e) {

            e.printStackTrace();

            return new ResponseModel( e.getMessage() );

        }

        if ( boxValue != null && !boxValue.equals("")) {

            if (boxValue.startsWith("0") && boxValue.length() > 1)
                return new ResponseModel( "no" );
            else
                boxValue = boxValue.trim();

            try {
                float value = Float.parseFloat(boxValue);

                if (0 <= value && value <= 9) {

                    logger.trace("Value has been set as: [" + value + "]");
                    message = "yes";

                } else {

                    logger.trace("Value [" + value + "] is not matched, hence default error will be performed.");
                    message = "no";

                }
            } catch (Exception e) {
                logger.trace( "Value is not matched, hence default error will be performed." );
                message = "no";
            }

        } else {
            logger.trace( "Value is not matched, hence default error will be performed." );
            message = "no";
        }

        return new ResponseModel( message );

    }

    public static ValidationErrorModel getError() {
        return new ValidationErrorModel();
    }

}
