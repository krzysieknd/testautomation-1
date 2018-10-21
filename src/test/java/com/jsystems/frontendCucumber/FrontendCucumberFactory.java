package com.jsystems.frontendCucumber;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.BeforeClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class FrontendCucumberFactory {

    public WebDriver driver = null;
    public String baseUrl;
//    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    public String id = "0";

    @BeforeClass
    public static void setupClass() {
        System.out.println("================== @BeforeClass Frontend Cucumber =====================");
    }

    public WebDriver setUp() {
        WebDriverManager.chromedriver().setup();
        System.out.println("================== @Before Frontend Cucumber =====================");
        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("--start-maximized");
//        openBaseUrl = "https://wordpress.com/";
        if (driver == null) {
            driver = new ChromeDriver(chromeOptions);
        }
        prepareDriver();
        return driver;
    }

    void prepareDriver(){
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }


    @After
    public void tearDown(Scenario scenario) {

        System.out.println("=========================== @After Cucumber Test  =======================================");
        String status;
        if(!scenario.isFailed()) {
            status = "( ͡° ͜ʖ ͡°)";
//            status = "++++++++++";
            scenario.write("Scenario passed");
        } else {
            status = "(✖╭╮✖)";
//            status = "-------------";
            scenario.embed(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES),"images/png");
            scenario.write("Scenario failed");
        }
        System.out.println("\n"+status+" End of: " + scenario.getName() + " scenario.");
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
            driver.quit();
            driver = null;
        driver.close();
        }
    }
}