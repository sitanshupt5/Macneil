package com.macneil.testbase;

import com.macneil.pages.HomePage;
import io.cucumber.java.Before;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseUtils {

    public WebDriver driver;
    public Properties properties;
    public HomePage homePage;
    Logger logger = LogManager.getLogger(BaseUtils.class);

    public void initializeDriver() throws IOException {
        properties = new Properties();
        FileInputStream projectConfigFile= new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\properties\\project_config.properties");
        properties.load(projectConfigFile);
        if(properties.getProperty("browser").equals("chrome"))
        {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+properties.getProperty("chromedriver_path"));
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
    }

    public void openWebsite() throws IOException {
        initializeDriver();
        driver.get("https://macneilwashecommerce.dvlpsite.com/");
        homePage = new HomePage(driver);
        logger.info(driver.getCurrentUrl() +"site has been opened");
    }

    public void closeBrowser()
    {
        driver.close();
        driver.quit();
    }


}
