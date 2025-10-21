package com.solvd.demo.ui.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractPage {

    @FindBy(xpath = "//h1[text()='Rybakova Anna']")
    private ExtendedWebElement ownerName;

    @FindBy(xpath = "//button[text()='Know more']")
    private ExtendedWebElement knowMoreButton;

    public HomePage(WebDriver driver) {
        super(driver);
        setPageAbsoluteURL("https://anna-rybakova.42web.io");
    }

    public boolean isOwnerNameDisplayed() {
        return ownerName.isElementPresent();
    }

    public ElementsPage clickKnowMore() {
        knowMoreButton.click();
        return new ElementsPage(getDriver());
    }
}

