package com.solvd.demo.ui.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NavItem extends AbstractUIObject {

    @FindBy(xpath = ".")
    private ExtendedWebElement root;

    public NavItem(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public void click() {
        root.click();
    }

    public boolean isActive() {
        String cls = root.getAttribute("class");
        return cls != null && cls.contains("active");
    }

    public boolean waitUntilActive(int timeoutSec) {
        try {
            new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutSec))
                    .until(ExpectedConditions.attributeContains(root.getElement(), "class", "active"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

        public boolean waitUntilPresent(int timeoutSec) {
        try {
            new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutSec))
                    .until(driver -> root.isElementPresent());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean waitUntilClickable(int timeoutSec) {
        try {
            new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutSec))
                    .until(ExpectedConditions.elementToBeClickable(root.getElement()));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ExtendedWebElement getRoot() {
        return root;
    }
}
