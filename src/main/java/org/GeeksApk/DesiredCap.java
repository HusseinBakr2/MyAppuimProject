package org.GeeksApk;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;

public class DesiredCap {
    private static AndroidDriver driver;

    public static AndroidDriver setDriver() throws MalformedURLException {
        // Set desired capabilities
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("appium:appPackage", "free.programming.programming");  // Replace with actual app package
        cap.setCapability("appium:appActivity", "free.programming.programming.MainActivityNew");  // Replace with actual activity

        cap.setCapability("appium:deviceName", "A71 الخاص بـ Hussein");
        cap.setCapability("appium:platformName", "ANDROID");
        cap.setCapability("appium:platformVersion", "13.0");
        cap.setCapability("appium:udid", "RZ8N11TVH0D");
        cap.setCapability("appium:automationName", "UiAutomator2");

        URI uri = URI.create("http://localhost:4723");
        driver = new AndroidDriver(uri.toURL(), cap);



        WebElement skipButton = driver.findElement(By.id("free.programming.programming:id/skip_btn"));skipButton.click();
        System.out.println("Skip button clicked successfully.");


        return driver;
    }

    public static void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Driver has been closed successfully.");
        }
    }
}