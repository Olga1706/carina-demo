package com.qaprosoft.carina.demo.gui.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import com.qaprosoft.carina.demo.gui.components.compare.LoginForm;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

public class TopMenu extends AbstractUIObject {



    @FindBy(xpath = "//a[contains(@href, 'https://www.instagram.com/gsmarenateam')][1]")
    private ExtendedWebElement instagramTopIcon;

    @FindBy(id = "login-popup2")
    private LoginForm loginForm;

    @FindBy(xpath = "//a[contains(@class, 'login-icon')]")
    private ExtendedWebElement loginIcon;

    @FindBy(xpath = "//a[contains(@href, 'register.php3')]")
    private ExtendedWebElement signUpIcon;

    @FindBy(id = "topsearch-text")
    private ExtendedWebElement searchButtonIcon;

    @FindBy(xpath = "//span[contains(@id, 'login-popup2')]")
    private ExtendedWebElement popLogin;

    @FindBy(xpath = "//a[contains(@id, 'login-active')]")
    private ExtendedWebElement activeLogin;

    @FindBy(id = "nick-submit")
    private ExtendedWebElement loginButton;

    public TopMenu(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public LoginForm pressLoginButton(){
        loginIcon.click();
        return loginForm;
    }

    public boolean isUserLoggedIn(){
        return activeLogin.isPresent();
    }
}
