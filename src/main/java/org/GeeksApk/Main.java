package org.GeeksApk;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.TestNG;

import java.net.MalformedURLException;
import java.net.URI;

public class Main {

    public static void main(String[] args) {

        // Initialize TestNG
        TestNG testng = new TestNG();

        try {
            // Specify the class that contains your @Test methods
            testng.setTestClasses(new Class[]{TestLogin.class});

            // Run TestNG with the TestLogin class
            testng.run();

            System.out.println("Test completed");

        } catch (Exception e) {
            // Catch any exceptions that occur during the TestNG setup or execution
            System.err.println("An error occurred while running tests: " + e.getMessage());
            e.printStackTrace();  // Print the stack trace for more details
        }

    }
}