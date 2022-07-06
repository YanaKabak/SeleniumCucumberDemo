package util.reporter;

import util.Formatter;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;

import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Reporter {

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("H:mm:ss:SSS");
    private static final Logger LOGGER = Logger.getLogger(Reporter.class.getName());
    private static boolean outEnabled = true;

    public static void log(String s) {
        String message = "[" + FORMAT.format(System.currentTimeMillis()) + "]: " + Formatter.escapeCharacters(s) + "<br/>\n";
        org.testng.Reporter.log(message);
        printStdOut(message);
    }

    private static void printStdOut(Object message) {
        if (outEnabled) {
            LOGGER.log(Level.INFO, message.toString());
        }
    }

}
