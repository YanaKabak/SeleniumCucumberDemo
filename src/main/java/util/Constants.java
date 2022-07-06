package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Constants {
    // Properties
    public static final String DEFAULT_LIB_DIR = System.getProperty("lib.dir");
    public static final String BASE_URL = System.getProperty("baseurl");


    public static final int ELEMENT_TIMEOUT_SECONDS = 360;
    public static final int ELEMENT_MICRO_TIMEOUT_SECONDS = 2;

    public static final String USERNAME = "admin1";
    public static final String PASSWORD = "[9k<k8^z!+$$GkuP";

    public static String CURRENT_TIME;

    static {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime now = LocalDateTime.now();

        CURRENT_TIME = dtf.format(now);
    }

}
