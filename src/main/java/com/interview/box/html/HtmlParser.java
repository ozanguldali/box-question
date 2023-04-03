package com.interview.box.html;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;

import static util.LoggerUtil.logger;

public class HtmlParser {

    private static final String PROJECT_DIR = System.getProperty("user.dir");
    private static final String SLASH = System.getProperty("file.separator");
    private static final String BOX_RESULT_HTML_FILE = PROJECT_DIR + SLASH + "src" + SLASH + "main" + SLASH + "webapp" + SLASH;
    private static final String ERROR_PAGE              = PROJECT_DIR + SLASH + "src" + SLASH + "main" + SLASH + "webapp" + SLASH + "errorPage.html";


    public static Document parseResultPage(String jsonString) {

        JsonObject jsonObject = new JsonParser().parse( jsonString ).getAsJsonObject();
        String message = jsonObject.get("message").getAsString();
        String htmlString = convertHtmlFileToString( BOX_RESULT_HTML_FILE + "boxResult.html");

        if (message.equals("yes")) {
            htmlString = htmlString.replace( "background-color:white", "background-color:lightgreen" ).
                                    replace( "<h1></h1>",  "<h1>TRUE</h1>" );
        } else {
            htmlString = htmlString.replace( "background-color:white", "background-color:red" ).
                    replace( "<h1></h1>",  "<h1>FALSE</h1>" );
        }

        return Jsoup.parse( htmlString );

    }

    public static Document parseErrorPage() {

        logger.error( "Parsing Error Page\t" );

        String htmlString = convertHtmlFileToString( ERROR_PAGE );

        return Jsoup.parse( htmlString );

    }

    private static String convertHtmlFileToString( final String fileName) {


        Document htmlFile = null;

        String htmlString;

        try {

            htmlFile = Jsoup.parse( new File( fileName ),  "ISO-8859-1" );

        } catch (IOException e) {

            e.printStackTrace();
        }

        assert htmlFile != null;
        htmlString = htmlFile.toString();

        return htmlString;

    }

}
