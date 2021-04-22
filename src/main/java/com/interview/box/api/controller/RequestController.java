package com.interview.box.api.controller;

import com.interview.box.api.model.ResponseModel;
import com.interview.box.api.model.ValidationErrorModel;
import com.interview.box.api.service.BoxService;
import org.jsoup.nodes.Document;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static com.interview.box.api.model.ValidationErrorModel.errorMessage;
import static com.interview.box.api.util.ObjectMapperUtil.beautifyFormToJson;
import static com.interview.box.api.util.ObjectMapperUtil.beautifyJsonToString;
import static com.interview.box.html.HtmlParser.parseErrorPage;
import static com.interview.box.html.HtmlParser.parseResultPage;
import static util.LoggerUtil.*;

@RestController
public class RequestController {

    private final static String VALID_HTTP_METHOD = "POST";
    private final static String APPLICATION_JSON = "application/json";
    private final static String FORM_POST = "application/x-www-form-urlencoded";
    private final static String TEXT_HTML = "text/html";

    private final
    BoxService boxService;

    public RequestController(BoxService boxService) {
        this.boxService = boxService;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/validatorBrowser")
    public ResponseEntity<String> indexFormPostBrowser(HttpServletRequest request, HttpEntity<String> httpEntity,
                                                       @RequestHeader(value = HttpHeaders.CONTENT_TYPE, defaultValue = FORM_POST) String contentType) {

        errorMessage = null;

        final String payload = httpEntity.getBody();
        String temp_httpResponseBody;

        HttpHeaders headersRequest = new HttpHeaders();

        httpEntity.getHeaders().forEach(
                ( name, value ) -> headersRequest.set( name, String.valueOf( value ) )
        );

        headersRequest.set( HttpHeaders.CONTENT_TYPE, FORM_POST);
        String jsonFormattedPayload = beautifyFormToJson( payload );

        restRequestLogger( request.getMethod(), headersRequest, beautifyJsonToString( jsonFormattedPayload ) );

        if ( !request.getMethod().equals( VALID_HTTP_METHOD ) || jsonFormattedPayload == null) {

            logger.error( "Invalid request with payload { " + jsonFormattedPayload + " }" );
            return new ResponseEntity<>( "Invalid Request", HttpStatus.METHOD_NOT_ALLOWED );

        }

        if ( !contentType.replace(" ", "").toLowerCase().trim().contains(FORM_POST) ){
            logger.error( "Unsupported Media Type: " + contentType );
            return new ResponseEntity<>("Unsupported Media Type", HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        }


        final ResponseModel validResponse = BoxService.getResponse( jsonFormattedPayload );
        temp_httpResponseBody = validResponse.toString();

        if ( errorMessage != null ) {

            final ValidationErrorModel failedResponse = BoxService.getError();
            temp_httpResponseBody = failedResponse.toString();

        }

        Document htmlResponseBody = errorMessage == null ? parseResultPage( temp_httpResponseBody ) : parseErrorPage();

        HttpHeaders headersResponse = new HttpHeaders();
        headersResponse.set( HttpHeaders.CONTENT_TYPE, TEXT_HTML);

        restResponseLogger( beautifyJsonToString( jsonFormattedPayload ), headersResponse, htmlResponseBody.toString() );

        return new ResponseEntity<>(htmlResponseBody.toString(), headersResponse, HttpStatus.OK);

    }

}
