package util;

import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;

public class LoggerUtil {

    public static final Logger logger = Logger.getLogger( LoggerUtil.class );

    public static void restRequestLogger(String method, HttpHeaders headers, String payload) {

        logger.trace( "\n|<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<|\n" );
        logger.trace( String.format(
                "Got a request with\n" +
                        "Method:\t'{ %s }'\n" +
                        "Headers:\t'{ %s }'\n" +
                        "Body:\t' %s '\n",
                method, headers, payload )
        );
        logger.trace( "\n|<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<|\n" );

    }

    public static void restResponseLogger(String payload, HttpHeaders headers, String body) {

        logger.trace( "\n|>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>|\n" );
        logger.trace( String.format(
                "Response of the request ' %s ':\n" +
                        "Headers:\t'{ %s }'\n" +
                        "Body:\t' %s '\n",
                payload, headers, body )
        );
        logger.trace( "\n|>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>|\n" );

    }

}
