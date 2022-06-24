package com.qaprosoft.carina.demo;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import com.qaprosoft.carina.demo.myGui.HomePage;
import com.qaprosoft.carina.demo.myGui.InventoryPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.lang.invoke.MethodHandles;



public class TestWeb implements IAbstractTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    HomePage homePage = new HomePage(getDriver());
    InventoryPage inventoryPage = new InventoryPage(getDriver());
    final String userNameValid = "standard_user";
    final String passwordValid = "secret_sauce";
    final String expErrorMessageUserName = "Epic sadface: Username is required";
    final String expErrorMessagePassword = "Epic sadface: Password is required";
    final String expNewErrorMessage = "Epic sadface: Username and password do not match any user in this service";


    @Test
    @MethodOwner(owner = "olga")
    public void testWebElementsOnHomePage() {
        homePage.open();

        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened!");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(homePage.isBotColumnPresent(), "Bot column is absent");
        softAssert.assertTrue(homePage.isUserNameFieldPresent(), "User name field is absent");
        softAssert.assertTrue(homePage.isPasswordFieldPresent(), "Password field is absent");
        softAssert.assertTrue(homePage.isLoginButtonPresent(), "Login button is absent");
        softAssert.assertAll();
    }

    @Test
    @MethodOwner(owner = "olga")
    public void testLoginPageCorrectValues() {
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened!");
        homePage.typeUsername(userNameValid);
        homePage.typePassword(passwordValid);
        inventoryPage = homePage.clickLoginBtn();

        Assert.assertFalse(homePage.isPageOpened(), "Home page is opened!");
        Assert.assertTrue(inventoryPage.isCartPresent(), "Cart icon is not present");
    }

    @Test
    @MethodOwner(owner = "olga")
    public void testLoginEmptyFields() {
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened!");
        homePage.clickLoginBtn();

        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened!");
        Assert.assertTrue(homePage.isErrorButtonPresent(), "Error button is absent");
        Assert.assertTrue(homePage.isErrorIconPresent(), "Error icon is absent");
        String actualErrorMessage = homePage.getErrorMessage();
        Assert.assertEquals(actualErrorMessage, expErrorMessageUserName, "Actual and Expected results are different");
    }

    @Test
    @MethodOwner(owner = "olga")
    public void testLoginValidUserName() {
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened!");
        String correctUserName = "problem_user";
        homePage.typeUsername(correctUserName);
        homePage.clickLoginBtn();

        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened!");
        Assert.assertTrue(homePage.isErrorButtonPresent(), "Error button is absent");
        Assert.assertTrue(homePage.isErrorIconPresent(), "Error icon is absent");
        String actualErrorMessage = homePage.getErrorMessage();
        Assert.assertEquals(actualErrorMessage, expErrorMessagePassword, "Actual and Expected results are different");
    }

    @Test
    @MethodOwner(owner = "olga")
    public void testLoginValidPassword() {
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened!");
        String correctPassword = "secret_sauce";
        homePage.typePassword(correctPassword);
        homePage.clickLoginBtn();

        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened!");
        Assert.assertTrue(homePage.isErrorButtonPresent(), "Error button is absent");
        Assert.assertTrue(homePage.isErrorIconPresent(), "Error icon is absent");
        String actualErrorMessage = homePage.getErrorMessage();
        Assert.assertEquals(actualErrorMessage, expErrorMessageUserName, "Actual and Expected results are different");
    }

    @Test
    @MethodOwner(owner = "olga")
    public void testLoginInvalidValues() {
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened!");
        String incorrectUserName = "igorIgor";
        String incorrectPassword = "igorIgor";
        homePage.typeUsername(incorrectUserName);
        homePage.typePassword(incorrectPassword);
        homePage.clickLoginBtn();

        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened!");
        Assert.assertTrue(homePage.isErrorButtonPresent(), "Error button is absent");
        Assert.assertTrue(homePage.isErrorIconPresent(), "Error icon is absent");
        String actualErrorMessage = homePage.getErrorMessage();
        Assert.assertEquals(actualErrorMessage, expNewErrorMessage, "Wrong credentials");
    }

    @Test
    @MethodOwner(owner = "olga")
    public void testProductItemCard() {
        String expectedItemName = "Sauce Labs Backpack";
        String expectedPrice = "$29.99";
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened!");
        homePage.typeUsername(userNameValid);
        homePage.typePassword(passwordValid);
        InventoryPage inventoryPage = homePage.clickLoginBtn();

        SoftAssert softAssert = new SoftAssert();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is opened!");
        softAssert.assertTrue(inventoryPage.isImageItemPresent(), "There is no image");
        softAssert.assertTrue(inventoryPage.isInventoryItemDescPresent(), "Item description is not present");
        softAssert.assertTrue(inventoryPage.isAddToCartPresent(), "There is no 'Add to cart' btn");
        softAssert.assertEquals(inventoryPage.inventoryItemName(), expectedItemName, "Wrong inventory item name");
        softAssert.assertEquals(inventoryPage.inventoryItemPrice(), expectedPrice, "Incorrect inventory item price");
        softAssert.assertAll();

    }
    @Test
    @MethodOwner(owner = "olga")
    public void testDropdownFilterMenu() {
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened!");
        homePage.typeUsername(userNameValid);
        homePage.typePassword(passwordValid);
        inventoryPage = homePage.clickLoginBtn();

       /* Assert.assertTrue(inventoryPage.filterNameZToAPresent(), "(Z-A) filter isn`t present");
        Assert.assertTrue(inventoryPage.filterNameAToZPresent(), "(A-Z) filter isn`t present");*/
        Assert.assertTrue(inventoryPage.isPageOpened(), "Inventory/Products page is not opened!");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(inventoryPage.filterNameAToZPresent(), "Filter 'Name (A to Z)' is not present");
        softAssert.assertTrue(inventoryPage.filterNameZToAPresent(), "Filter 'Name (Z to A)' is not present");
        softAssert.assertTrue(inventoryPage.filterNamePriceLowToHighPresent(), "Filter 'Price (low to high)' is not present");
        softAssert.assertTrue(inventoryPage.filterNamePriceHighToLowPresent(), "Filter 'Price (high to low)' is not present");
        softAssert.assertAll();
    }

    @Test
    @MethodOwner(owner = "olga")
    public void testDropdownElementFilterMenu() {
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened!");
        homePage.typeUsername(userNameValid);
        homePage.typePassword(passwordValid);
        inventoryPage = homePage.clickLoginBtn();

        Assert.assertTrue(inventoryPage.isPageOpened(), "Inventory/Products page is not opened!");
        Assert.assertEquals(inventoryPage.defaultDropdownName(), "NAME (A TO Z)", "Default dropdown name is incorrect");
        inventoryPage.clickOnDropdown();
        Assert.assertEquals(inventoryPage.selectOption(), "Price (low to high)", "Option doesn't selected");
    }


}
