package utils;

public class Constants {

    public static final String BASE_URL = "https://en.wikipedia.org/w/index.php?search";
    public static final String IMPLICIT_WAIT_DURATION = "20";
    public static final String PAGE_LOAD_TIMEOUT = "180000";
    public static final String[] CHROME_OPTIONS = {
            "--disable-cache"
            , "--incognito"
            , "start-maximized"
            , "--no-sandbox"
            , "--disable-gpu"
            , "--disable-dev-shm-usage"
            , "--disable-infobars"
            , "--disable-notifications"
            , "--enable-automation"
            , "--ignore-certificate-errors"
            , "--ignore-ssl-errors"
            , "--disable-cache"
    };

    public static final String[] FIREFOX_OPTIONS = {
            "--ignore-certificate-errors"
            , "--ignore-ssl-errors"
            , "--disable-notifications"
            , "--disable-infobars"
            , "--incognito"
    };

}
