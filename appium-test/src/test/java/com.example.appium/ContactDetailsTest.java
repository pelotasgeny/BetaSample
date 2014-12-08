package com.example.appium;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class ContactDetailsTest {

    private static final String TARGET_APP_PATH = new File(System.getProperty("user.dir") + "/../app/build/outputs/apk/app-debug.apk").getAbsolutePath();
    private AndroidDriver wd;

    @Before
    public void setup() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Android");
        capabilities.setCapability("app", TARGET_APP_PATH);

        wd = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void testApp() {
        // find all list elements
        List elements = wd.findElements(By.id("contacts_item_name"));

        // get first element
        AndroidElement firstElement = (AndroidElement) elements.get(0);

        // read user name
        String userName = firstElement.getText();

        // split text for future assertions
        String userFirstName = userName.split(" ")[0];
        String userLastName = userName.split(" ")[1];

        // load details page
        firstElement.click();

        // find text fields for first and last name
        AndroidElement detailsFirstName = (AndroidElement) wd.findElement(By.id("contacts_detail_first_name"));
        AndroidElement detailsLastName = (AndroidElement) wd.findElement(By.id("contacts_detail_last_name"));

        Assert.assertEquals(detailsFirstName.getText(), userFirstName);
        Assert.assertEquals(detailsLastName.getText(), userLastName);
    }

    @After
    public void cleanup() {
        if (wd != null) {
            wd.quit();
        }
    }
}
