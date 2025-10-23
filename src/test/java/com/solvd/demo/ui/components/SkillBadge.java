package com.solvd.demo.ui.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SkillBadge extends AbstractUIObject {

    @FindBy(xpath = ".")
    private ExtendedWebElement root;

    public SkillBadge(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public String text() {
        return root.getText();
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

    public boolean waitUntilVisible(int timeoutSec) {
        try {
            new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutSec))
                    .until(ExpectedConditions.visibilityOf(root.getElement()));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isVisible() {
        return root.isElementPresent() && root.isVisible();
    }
}
