package com.spring.framework.automation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Duvvuri on 16/01/2019.
 */

@Component
public class BrowserManagement {
    static final Logger logger = LogManager.getLogger(BrowserManagement.class.getName());
    WebDriver driver;

    @Value("${browser}")
    String browser;

    @Value("${${country}.constituents.url}")
    String constituents_url;

    @Value("${${country}.wait.element}")
    String wait_element;

    @Value("${${country}.prefix.element}")
    String prefix_element;

    @Value("${${country}.historic.url}")
    String historic_url;

    @Value("${${country}.element.wait.date}")
    String element_wait_date;

    @Value("${${country}.xpath.today}")
    String xpath_today;

    @Value("${${country}.db}")
    String db;

    public String getDb() {
        return db;
    }

    public String getXpath_today() {
        return xpath_today;
    }

    public String getConstituents_url() {
        return constituents_url;
    }

    public String getWait_element() {
        return wait_element;
    }

    public String getPrefix_element() {
        return prefix_element;
    }

    public String getHistoric_url() {
        return historic_url;
    }

    public String getElement_wait_date() {
        return element_wait_date;
    }

    public BrowserManagement() {

        logger.debug("BrowserManagement is being initialized" + browser);
    }

    WebDriver openBrowser(String url , String browser)
    {
        if(browser.equalsIgnoreCase("ff"))
        {
            System.setProperty("webdriver.gecko.driver","geckodriver.exe");
            driver = new FirefoxDriver();
        }else if(browser.equalsIgnoreCase("chrome"))
        {
            System.setProperty("webdriver.chrome.driver","chromedriver.exe");
            driver = new ChromeDriver();
        }else if(browser.equalsIgnoreCase("ie"))
        {
            DesiredCapabilities cap = new DesiredCapabilities();
            cap.setCapability("ignoreZoomSetting", true);
            System.setProperty("webdriver.ie.driver","IEDriverServer.exe");
            driver =  new InternetExplorerDriver(cap);
        }else if(browser.equalsIgnoreCase("hunit"))
        {
            driver = new HtmlUnitDriver();
        }
        else
        {
            throw new RuntimeException("No matching browser found");
        }
            driver.get(url);
            return driver;
    }

    void closeBrowser()
    {
        driver.close();
    }

}
