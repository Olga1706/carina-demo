package com.qaprosoft.carina.demo;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import com.qaprosoft.carina.demo.gui.gsmServices.Login;
import com.qaprosoft.carina.demo.gui.gsmServices.User;
import com.qaprosoft.carina.demo.gui.gsmServices.UserService;
import com.qaprosoft.carina.demo.gui.pages.*;
import com.zebrunner.agent.core.annotation.TestLabel;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WebGSM implements IAbstractTest {
    @Test()
    @MethodOwner(owner = "ol")
    @TestLabel(name = "feature", value = {"web", "acceptance"})
    public void testTools() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened!");

        ToolsPage toolsPage = homePage.getFooterMenu().openToolsPage();
        Assert.assertTrue(toolsPage.isPageOpened(), "Tools page is not opened!");

        Assert.assertEquals(toolsPage.readH3Compare(), "Compare");
    }

    @Test
    @MethodOwner(owner = "ol")
    public void verifyIphoneSearch(){
        UserService userService = new UserService();
        User user = userService.getUser();
        Login login = new Login();
        login.login(user.getEmail(), user.getPassword());
        HomePage homePage = new HomePage(getDriver());
        BrandModelsPage brandModelsPage = homePage.getPhoneFinderMenu().openModelSearch("Apple");
        brandModelsPage.pressPopularityButton();
        ModelInfoPage modelInfoPage = brandModelsPage.selectModel(1);
    }
}
