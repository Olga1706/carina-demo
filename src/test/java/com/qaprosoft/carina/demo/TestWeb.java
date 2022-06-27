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
        Assert.assertFalse(homePage.isPageOpened(), "Home page is opened!");
        softAssert.assertTrue(inventoryPage.isImageItemPresent(), "There is no image");
        softAssert.assertTrue(inventoryPage.isInventoryItemDescPresent(), "Item description is not present");
        softAssert.assertTrue(inventoryPage.isAddToCartPresent(), "There is no 'Add to cart' btn");
        softAssert.assertEquals(inventoryPage.getInventoryItemName(), expectedItemName, "Wrong inventory item name");
        softAssert.assertEquals(inventoryPage.getInventoryItemPrice(), expectedPrice, "Incorrect inventory item price");
        softAssert.assertAll();
    }

    @Test
    public void testDropDownFilterMenu() {
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened!");
        homePage.typeUsername(userNameValid);
        homePage.typePassword(passwordValid);
        InventoryPage inventoryPage = homePage.clickLoginBtn();

        SoftAssert softAssert = new SoftAssert();
        Assert.assertTrue(inventoryPage.isPageOpened(), "Inventory/Products page is not opened!");
        inventoryPage.clickOnDropdownMenu();
        softAssert.assertEquals(inventoryPage.isFilterNameAToZPresent(), "Name (A to Z)", "Filter Name A to Z is Lost");
        softAssert.assertEquals(inventoryPage.isFilterNameZToAPresent(), "Name (Z to A)", "Filter Name Z to A is Lost");
        softAssert.assertEquals(inventoryPage.isFilterNamePriceLowToHighPresent(), "Price (low to high)", "Filter Price Low to High Lost");
        softAssert.assertEquals(inventoryPage.isFilterNamePriceHighToLowPresent(), "Price (high to low)", "Filter Price High to Low Lost");
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
        Assert.assertEquals(inventoryPage.getActiveDropdownName(), "NAME (A TO Z)", "Default dropdown name is incorrect");
        Assert.assertEquals(inventoryPage.getSelectOption(), "Price (low to high)", "Option doesn't selected");
    }

    @Test
    public void testLocationElements() {
        homePage.open();
        SoftAssert softAssert = new SoftAssert();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened");
        softAssert.assertTrue(homePage.isCorrectLocationField(), "Location of field failed");
        softAssert.assertAll();
    }

    @Test
    public void testDropDownFilterMenu1() {
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened!");
        homePage.typeUsername(userNameValid);
        homePage.typePassword(passwordValid);
        InventoryPage inventoryPage = homePage.clickLoginBtn();

        SoftAssert softAssert = new SoftAssert();
        Assert.assertTrue(inventoryPage.isPageOpened(), "Inventory/Products page is not opened!");

        softAssert.assertEquals(inventoryPage.isDropdownOptionByNamePresent("Name (A to Z)"), "NAME (A TO Z)", "Filter Name A to Z is Lost");
        softAssert.assertEquals(inventoryPage.isDropdownOptionByNamePresent("Name (Z to A)"), "[NAME (A TO Z)]", "Filter Name Z to A is Lost");
        softAssert.assertEquals(inventoryPage.isDropdownOptionByNamePresent("Price (low to high)"), "Price (low to high)", "Filter Price Low to High Lost");
        softAssert.assertEquals(inventoryPage.isDropdownOptionByNamePresent("Price (high to low)"), "Price (high to low)", "Filter Price High to Low Lost");
        softAssert.assertAll();
    }
}
