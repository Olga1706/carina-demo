package com.qaprosoft.carina.demo.myGui;


import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class InventoryPage extends AbstractPage {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @FindBy(id = "shopping_cart_container")
    private ExtendedWebElement cart;

    @FindBy(xpath = "//*[@id='header_container']/div[2]/div[2]/span/span")
    private ExtendedWebElement activeDropdown;

    @FindBy(xpath = "//option[@value='%s']")
    private ExtendedWebElement dropDownOption;


    @FindBy(xpath = "//*[@class='select_container']//option[text()='%s']")
    private ExtendedWebElement dropDownMenu;
    //select[@class='product_sort_container']

    @FindBy(className = "inventory_item_img")
    private ExtendedWebElement itemImage;

    @FindBy(className = "inventory_item_name")
    private ExtendedWebElement inventoryItemName;

    @FindBy(className = "inventory_item_desc")
    private ExtendedWebElement inventoryItemDesc;

    @FindBy(className = "inventory_item_price")
    private ExtendedWebElement inventoryItemPrice;

    @FindBy(id = "add-to-cart-sauce-labs-bike-light")
    private ExtendedWebElement addToCart;

    @FindBy(xpath = "//*[@id='header_container']/div[2]/div[2]/span/select/option[1]")
    private ExtendedWebElement optionNameAToZ;

    @FindBy(xpath = "//*[@id='header_container']/div[2]/div[2]/span/select/option[2]")
    private ExtendedWebElement optionNameZToA;

    @FindBy(xpath = "//*[@id='header_container']/div[2]/div[2]/span/select/option[3]")
    private ExtendedWebElement optionPriceLowToHigh;

    @FindBy(xpath = "//*[@id='header_container']/div[2]/div[2]/span/select/option[4]")
    private ExtendedWebElement optionPriceHighToLow;

    public InventoryPage(WebDriver driver) {
        super(driver);
        setPageURL("/inventory.html");
    }

    public boolean isCartPresent() {
        return cart.isElementPresent();
    }

    public String getActiveDropdownName() {
        return activeDropdown.getText();
    }

   public boolean isDropdownOptionByNamePresent(String option) {
        return dropDownMenu.format(option).isElementPresent();
    }

    public InventoryPage clickOnDropdownMenu() {
        dropDownMenu.click(5);
        return new InventoryPage(getDriver());
    }
   public void clickOnDropdown() {
        optionPriceLowToHigh.click();
    }

    public String getSelectOption() {
        return optionPriceLowToHigh.getText();
    }

    public boolean isImageItemPresent() {
        return itemImage.isElementPresent();
    }

    public String getInventoryItemName() {
        return inventoryItemName.getText();
    }

    public boolean isInventoryItemDescPresent() {
        return inventoryItemDesc.isElementPresent();
    }

    public String getInventoryItemPrice() {
        return inventoryItemPrice.getText();
    }

    public boolean isAddToCartPresent() {
        return addToCart.isElementPresent();
    }

    public String isFilterNameAToZPresent() {
       return optionNameAToZ.getText();
    }

    public String isFilterNameZToAPresent() {
        return optionNameZToA.getText();
    }

    public String isFilterNamePriceLowToHighPresent() {
        return optionPriceLowToHigh.getText();
    }

    public String isFilterNamePriceHighToLowPresent() {
        return optionPriceHighToLow.getText();
    }

}
