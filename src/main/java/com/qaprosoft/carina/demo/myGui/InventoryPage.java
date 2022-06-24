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

    @FindBy(className = "shopping_cart_link")
    private ExtendedWebElement cart;

    @FindBy(xpath = "//*[@id='header_container']/div[2]/div[2]/span/span")
    private ExtendedWebElement defaultDropdown;

    @FindBy(xpath = "//*[@class=product_sort_container]/option[text()='%s']")
    private ExtendedWebElement dropDownOption;

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

    @FindBy(xpath = "//div[@class='inventory_item_name']")
    private List<ExtendedWebElement> prodNames;

    @FindBy(xpath = "//option[@value='za']")
    private ExtendedWebElement prodSortZToA;

    @FindBy(xpath = "//span[text()='Name (Z to A)']")
    private ExtendedWebElement activeOptionSortZToA;

    public InventoryPage(WebDriver driver) {
        super(driver);
        setPageURL("/inventory.html");
    }

    public boolean isCartPresent() {
        return cart.isElementPresent();
    }

    public String defaultDropdownName() {
        return defaultDropdown.getText();
    }

    public boolean isDropdownOptionByNamePresent(String option) {
        return dropDownOption.format(option).isElementPresent();
    }

    public boolean clickOnDropdown() {
        optionPriceLowToHigh.click();
        return false;
    }

    public String selectOption() {
        return optionPriceLowToHigh.getText();
    }

    public boolean isImageItemPresent() {
        return itemImage.isElementPresent();
    }

    public String inventoryItemName() {
        return inventoryItemName.getText();
    }

    public boolean isInventoryItemDescPresent() {
        return inventoryItemDesc.isElementPresent();
    }

    public String inventoryItemPrice() {
        return inventoryItemPrice.getText();
    }

    public boolean isAddToCartPresent() {
        return addToCart.isElementPresent();
    }

    public boolean filterNameAToZPresent() {
        return optionNameAToZ.isElementPresent();
    }

    public boolean filterNameZToAPresent() {
        return optionNameZToA.isElementPresent();
    }

    public boolean filterNamePriceLowToHighPresent() {
        return optionPriceLowToHigh.isElementPresent();
    }

    public boolean filterNamePriceHighToLowPresent() {
        return optionPriceHighToLow.isElementPresent();
    }

    public boolean checkSortProductsAToZ() {

        List<String> prodNamesList = new ArrayList<>();
        List<String> testList = new ArrayList<>();

        for (ExtendedWebElement prodName : prodNames) {
            prodNamesList.add(prodName.getText());
            LOGGER.info("Added to list: " + prodName.getText());
        }

        testList.addAll(prodNamesList);
        Collections.sort(testList);

        LOGGER.info("Initial array" + prodNamesList.toString());
        LOGGER.info("Sorted array" + testList.toString());

        return testList.equals(prodNamesList);
    }
    public boolean clickSortZToA() {

        prodSortZToA.click();
        LOGGER.info("Product sort (Z-A) is clicked");
        activeOptionSortZToA.isElementPresent();
        LOGGER.info("(Z-A) filter is selected");
        return true;
    }

    public boolean checkSortProductsZToA() {

        List<String> productNamesList = new ArrayList<>();
        List<String> testList = new ArrayList<>();

        for (ExtendedWebElement prodName : prodNames) {
            productNamesList.add(prodName.getText());
            LOGGER.info("Added to list: " + prodName.getText());
        }

        testList.addAll(productNamesList);

        Collections.sort(testList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });

        LOGGER.info("Initial array" + productNamesList.toString());
        LOGGER.info("Sorted array" + testList.toString());

        return testList.equals(productNamesList);
    }

}
