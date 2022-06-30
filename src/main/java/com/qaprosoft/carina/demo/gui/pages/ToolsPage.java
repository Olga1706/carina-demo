package com.qaprosoft.carina.demo.gui.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import com.qaprosoft.carina.demo.gui.components.NewsItem;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ToolsPage extends AbstractPage {
    @FindBy(css = "ul#lab-list li a h3")
    private ExtendedWebElement h3Compare;


    public ToolsPage(WebDriver driver) {
        super(driver);
        setPageURL("/tools.php3");
    }
    public String readH3Compare() {
        assertElementPresent(h3Compare);
        return h3Compare.getText();
    }

}


