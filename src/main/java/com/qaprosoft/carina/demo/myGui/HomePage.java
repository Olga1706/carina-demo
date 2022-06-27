package com.qaprosoft.carina.demo.myGui;


import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractPage {

    @FindBy(className = "bot_column")
    private ExtendedWebElement bot;

    @FindBy(id = "user-name")
    private ExtendedWebElement userNameField;

    @FindBy(id = "password")
    private ExtendedWebElement passwordField;

    @FindBy(id = "login-button")
    private ExtendedWebElement loginButton;

    @FindBy(className = "error-button")
    private ExtendedWebElement errorButton;

    @FindBy(className = "error_icon")
    private ExtendedWebElement errorIcon;

    @FindBy(xpath = "//*[@id='login_button_container']/div/form/div[3]/h3")
    private ExtendedWebElement errorMessage;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isBotColumnPresent() {
        return bot.isElementPresent();
    }

    public boolean isUserNameFieldPresent() {
        return userNameField.isElementPresent();
    }

    public boolean isPasswordFieldPresent() {
        return passwordField.isElementPresent();
    }

    public boolean isLoginButtonPresent() {
        return loginButton.isElementPresent();
    }

    public boolean isErrorButtonPresent() {
        return errorButton.isElementPresent();
    }

    public boolean isErrorIconPresent() {
        return errorIcon.isElementPresent();
    }

    public InventoryPage clickLoginBtn() {
        loginButton.click();
        return new InventoryPage(getDriver());
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

    public void typeUsername(String username) {
        userNameField.type(username);
    }

    public void typePassword(String password) {
        passwordField.type(password);
    }

    public boolean isCorrectLocationField() {
        Point userNameFieldPoint = userNameField.getLocation();
        Point passwordFieldPoint = passwordField.getLocation();
        Point loginButtonPoint = loginButton.getLocation();
        return userNameFieldPoint.getY() < passwordFieldPoint.getY() && passwordFieldPoint.getY() < loginButtonPoint.getY();
    }
}
