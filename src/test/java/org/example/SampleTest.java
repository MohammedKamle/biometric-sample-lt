package org.example;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.JavascriptExecutor;
import java.net.URL;
import java.util.HashMap;
import org.testng.annotations.Test;

public class SampleTest {

    String userName = System.getenv("LT_USERNAME") == null ? "mohammadk" : System.getenv("LT_USERNAME");
    String accessKey = System.getenv("LT_ACCESS_KEY") == null ? "gkrzT0iFKjDjehXpMTznxM1lHIZXSYjV3H8Ntk0s2rCUJJO3WU" : System.getenv("LT_ACCESS_KEY");

    public String gridURL = "@mobile-hub.lambdatest.com/wd/hub";

    private IOSDriver driver;


    @Test
    public void biometricTest() {

        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            HashMap<String, Object> ltOptions = new HashMap<String, Object>();
            ltOptions.put("w3c", true);
            ltOptions.put("platformName", "ios");
            ltOptions.put("deviceName", "iPhone.*");
            ltOptions.put("platformVersion", "16");
            ltOptions.put("app", "lt://APP10160531951700650605624458");
            ltOptions.put("isRealMobile", true);
            ltOptions.put("build", "biometric-sample");
            ltOptions.put("enableBiometricsAuthentication", true);
            capabilities.setCapability("lt:options", ltOptions);


            String hub = "https://" + userName + ":" + accessKey + gridURL;
            driver =  new IOSDriver(new URL(hub), capabilities);
            ((JavascriptExecutor)driver).executeScript("lambda-name=passing-test");

            Thread.sleep(3000);
            driver.findElement(By.name("Log In")).click();
            Thread.sleep(3000);

            // passing the authentication
            //driver.executeScript("lambda-biometric-injection=pass");

            driver.quit();


        } catch (Exception e) {
            e.printStackTrace();
            try{
                driver.quit();
            }catch(Exception e1){
                e.printStackTrace();
            }
        }
    }


}
