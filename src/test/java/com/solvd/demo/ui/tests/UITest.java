package com.solvd.demo.ui.tests;

import com.solvd.demo.ui.pages.HomePage;
import com.solvd.demo.ui.pages.ElementsPage;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class UITest extends BaseTest {

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
                "Navigation did not go to the expected URL!");
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
        Assert.assertFalse(classAttribute.contains("active"),
                "Gem menu should NOT have 'active' class before clicking!");
    }

    @Test
    public void testGemMenuActiveAfterClicking() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        ElementsPage elementsPage = homePage.clickKnowMore();

        elementsPage.clickGemMenu();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        String classAttribute = elementsPage.getGemMenuClass();
        Assert.assertTrue(classAttribute.contains("active"), "Gem menu should have 'active' class after clicking!");
    }

    @Test
    public void testFormButtonDisabledThenEnabled() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        ElementsPage elementsPage = homePage.clickKnowMore();
        elementsPage.clickGemMenu();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        elementsPage.clickSkillsButton();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        Assert.assertTrue(elementsPage.isFormButtonDisabled(), "Add Skill button should be disabled initially!");

        elementsPage.fillSkillsForm("Java", "10");

        new org.openqa.selenium.support.ui.WebDriverWait(getDriver(), java.time.Duration.ofSeconds(5))
                .until(driver -> elementsPage.isFormButtonEnabled());

        Assert.assertTrue(elementsPage.isFormButtonEnabled(),
                "Add Skill button should be enabled after filling the form!");
    }

    @Test
    public void testScrollButtonBecomesVisible() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        ElementsPage elementsPage = homePage.clickKnowMore();

        boolean isScrollButtonVisible = elementsPage.isScrollButtonVisible();
        Assert.assertTrue(isScrollButtonVisible, "Scroll button should be visible!");
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

    @Test
    public void testKnowMore_WaitsForInnerUrl() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();

        ElementsPage elements = homePage.clickKnowMore();

        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until((ExpectedCondition<Boolean>) d -> d != null &&
                        d.getCurrentUrl().contains("/inner"));

        Assert.assertTrue(getDriver().getCurrentUrl().endsWith("/inner"),
                "Expected to be on /inner after clicking Know more");
    }

    @Test
    public void testGemMenuActiveAfterClickingWithWait() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        ElementsPage elements = homePage.clickKnowMore();

        elements.gem().click();
        boolean becameActive = elements.gem().waitUntilActive(100);
        Assert.assertTrue(becameActive && elements.gem().isActive(), "Gem nav item should become active after click");
    }

    @Test
    public void testHideMenu_WaitsAvatarDisappear() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        ElementsPage elements = homePage.clickKnowMore();

        elements.waitAvatarVisible(100);

        elements.clickHideMenu();
        boolean hidden = elements.waitAvatarHidden(100);
        Assert.assertTrue(hidden, "Avatar should become hidden after Hide Menu");
    }

    @Test
    public void testAddSkill_WaitsBadgeVisible() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        ElementsPage elements = homePage.clickKnowMore();

        String skill = "Java";
        elements.fillSkillsForm(skill, "75");
        elements.clickFormButton();

        boolean appeared = new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(d -> elements.skills().stream()
                        .anyMatch(sb -> {
                            sb.waitUntilPresent(2);
                            return sb.text() != null && sb.text().toLowerCase().contains(skill.toLowerCase());
                        }));

        Assert.assertTrue(appeared, "Expected a skill badge containing: " + skill);
    }

    @Test
    public void testScrollUpButton_WaitsScrollTop() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        ElementsPage elements = homePage.clickKnowMore();

        Assert.assertTrue(elements.isScrollButtonVisible(), "Scroll button should be visible");
        // Scroll down to show the work of scroll button
        ((JavascriptExecutor) getDriver()).executeScript("window.scrollBy(0,1000);");

        elements.clickScrollBtn();

        // Wait until scrollY is near zero
        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until((ExpectedCondition<Boolean>) d -> {
                    Long y = (Long) ((JavascriptExecutor) d).executeScript("return window.scrollY;");
                    return y != null && y <= 10;
                });

        Long y = (Long) ((JavascriptExecutor) getDriver()).executeScript("return window.scrollY;");
        Assert.assertTrue(y <= 10, "Expected to be at top after clicking scroll-up button. Actual Y=" + y);
    }
}
