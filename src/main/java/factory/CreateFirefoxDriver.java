package factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;

import static utils.Constants.FIREFOX_OPTIONS;

public class CreateFirefoxDriver extends DriverFactory {

    public ThreadLocal<WebDriver> createFirefoxDriver() {

        WebDriverManager.firefoxdriver().setup();
        tlDriver.set(new FirefoxDriver(getFirefoxOptions()));

        return tlDriver;
    }

    private FirefoxOptions getFirefoxOptions() {

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        ProfilesIni allProfiles = new ProfilesIni();
        FirefoxProfile myProfile = allProfiles.getProfile("default");
        firefoxOptions.setProfile(myProfile);
        firefoxOptions.addArguments(FIREFOX_OPTIONS);
        myProfile.setAcceptUntrustedCertificates(true);
        myProfile.setAssumeUntrustedCertificateIssuer(false);


        return firefoxOptions;
    }
}
