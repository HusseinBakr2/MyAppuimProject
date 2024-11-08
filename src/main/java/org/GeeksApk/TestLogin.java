package org.GeeksApk;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.time.Duration;

public class TestLogin {

    private AndroidDriver driver;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        this.driver = DesiredCap.setDriver();

    }

    @Test(priority = 2)
    public void validLoginTest() throws InterruptedException {

        // Wait for the email field to be visible and interact with it
        WebElement userField = driver.findElement(By.id("free.programming.programming:id/editTextEmail"));
        userField.sendKeys("tester1@gmail.com");

        // Wait for the password field to be visible and interact with it
        WebElement passwordField = driver.findElement(By.id("free.programming.programming:id/editTextPassword"));
        passwordField.sendKeys("tester.2025");

        // Wait for the login button to be visible and click it
        WebElement loginButton = driver.findElement(By.id("free.programming.programming:id/logInButton"));
        loginButton.click();

        // Wait for the search icon to be visible after successful login
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Find the search icon using XPath
        WebElement searchIcon = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@resource-id='free.programming.programming:id/navigation_bar_item_small_label_view' and @text='Explore']")));

        // Assert that the search icon is displayed
        Assert.assertTrue(searchIcon.isDisplayed(), "Explore displayed after successful login.");

        // Assert that the search icon is enabled (it can be interacted with)
        Assert.assertTrue(searchIcon.isEnabled(), "Search icon is enabled for interaction.");

        // Optionally, click the search icon to verify if it works after being enabled
        searchIcon.click();  // Try clicking the search icon

        /* If you want to confirm the action after clicking (optional)
        WebElement searchInputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("free.programming.programming:id/searchInputField")));  // Example for a search field that should appear after clicking the icon
        Assert.assertTrue(searchInputField.isDisplayed(), "Search input field  appear after clicking the search icon.");
    */
    }


    @Test(priority = 1)
    public void invalidLoginTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for the email field to be visible and enter invalid credentials
        WebElement userField = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("free.programming.programming:id/editTextEmail")));
        userField.sendKeys("invalidUsername");

        // Wait for the password field to be visible and enter invalid credentials
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("free.programming.programming:id/editTextPassword")));
        passwordField.sendKeys("invalidPassword");

        // Wait for the login button to be clickable and perform login
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("free.programming.programming:id/logInButton")));
        loginButton.click();

        // Wait for the error message to appear
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("free.programming.programming:id/snackbar_text")));

        // Assert that the error message matches the expected text
        Assert.assertNotNull(errorMessage, "Error message is null!");
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message is not displayed!");
        Assert.assertEquals(errorMessage.getText(), "Incorrect Login Credentials", "The error message did not match the expected text.");
    }

    @AfterMethod
    public void logTestResult(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        if (result.isSuccess()) {
            System.out.println(testName + " PASSED");
        } else {
            System.out.println(testName + " FAILED");
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        driver = null;
    }
}
