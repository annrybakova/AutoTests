package com.solvd.demo.ui.tests;

import com.solvd.demo.ui.pages.HomePage;
import com.solvd.demo.ui.pages.ElementsPage;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UITest extends BaseTest {

    @org.testng.annotations.Test
    public void sanity() {
        org.testng.Assert.assertTrue(true, "TestNG is running this test.");
}

    @Test
    public void testHomePageLoading() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        Assert.assertTrue(homePage.isOwnerNameDisplayed(), "Owner name is not displayed!");
    }

    @Test
    public void testKnowMoreButtonClickNavigatesToInner() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();

        ElementsPage elementsPage = homePage.clickKnowMore();
        String currentUrl = getDriver().getCurrentUrl();

        Assert.assertEquals(currentUrl, "https://anna-rybakova.42web.io/inner",
                "Navigation did not go to the expected Elements page URL!");
    }

    @Test
    public void testAvatarVisibilityBeforeHideMenu() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        ElementsPage elementsPage = homePage.clickKnowMore();

        boolean isAvatarVisible = elementsPage.isAvatarVisible();
        Assert.assertTrue(isAvatarVisible, "Avatar should be visible on the page before hiding menu!");
    }

    @Test
    public void testAvatarVisibilityAfterHideMenu() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        ElementsPage elementsPage = homePage.clickKnowMore();


        elementsPage.clickHideMenu();
        boolean isAvatarVisible = elementsPage.isAvatarVisible();
        Assert.assertFalse(isAvatarVisible, "Avatar should NOT be visible after hiding menu!");
    }

    @Test
    public void testGemMenuActiveBeforeClicking() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        ElementsPage elementsPage = homePage.clickKnowMore();

        String classAttribute = elementsPage.getGemMenuClass();
        Assert.assertFalse(classAttribute.contains("active"), "Gem menu should NOT have 'active' class before clicking!");
    }

    @Test
    public void testGemMenuActiveAfterClicking() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        ElementsPage elementsPage = homePage.clickKnowMore();

        elementsPage.clickGemMenu();
        String classAttribute = elementsPage.getGemMenuClass();
        Assert.assertTrue(classAttribute.contains("active"), "Gem menu should have 'active' class after clicking!");
    }

    @Test
    public void testFormButtonDisabledThenEnabled() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        ElementsPage elementsPage = homePage.clickKnowMore();

        boolean isFormButtonDisabledBefore = elementsPage.isFormButtonDisabled();
        Assert.assertTrue(isFormButtonDisabledBefore, "Form button should be disabled initially!");

        elementsPage.fillSkillsForm("Java", "10");
        elementsPage.clickFormButton();

        boolean isFormButtonDisabledAfter = elementsPage.isFormButtonDisabled();
        Assert.assertFalse(isFormButtonDisabledAfter, "Form button should be enabled after filling out the form!");
    }

    @Test
    public void testScrollButtonBecomesVisible() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        ElementsPage elementsPage = homePage.clickKnowMore();

        ((JavascriptExecutor) getDriver()).executeScript("window.scrollBy(0, 500);");

        boolean isScrollButtonVisible = elementsPage.isScrollButtonVisible();
        Assert.assertTrue(isScrollButtonVisible, "Scroll button should be visible after scrolling down!");
    }

    @Test
    public void testScrollButtonScrollsUp() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        ElementsPage elementsPage = homePage.clickKnowMore();

        ((JavascriptExecutor) getDriver()).executeScript("window.scrollBy(0, 800);");
        Assert.assertTrue(elementsPage.isScrollButtonVisible(), "Scroll button should be visible before clicking!");

        elementsPage.clickScrollBtn();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Long scrollY = (Long) ((JavascriptExecutor) getDriver()).executeScript("return window.scrollY;");
        Assert.assertTrue(scrollY < 200, "Page should scroll back up after clicking scroll button!");
    }
}
