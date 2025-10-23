package com.solvd.demo.ui.pages;

import com.solvd.demo.ui.components.NavItem;
import com.solvd.demo.ui.components.SkillBadge;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ElementsPage extends AbstractPage {

    @FindBy(xpath = "//button[@class='hide-btn']")
    private ExtendedWebElement hideMenu;

    @FindBy(xpath = "//li[text()='Skills']")
    private NavItem gemNavItem;

    @FindBy(xpath = "//button[contains(@class,'skills-btn')]")
    private ExtendedWebElement skillsButton;

    @FindBy(xpath = "//button[@class='inner-btn']")
    private ExtendedWebElement innerButton;

    @FindBy(xpath = "//button[@class='scroll-btn']")
    private ExtendedWebElement scrollButton;

    @FindBy(xpath = "//input[@id='skillName']")
    private ExtendedWebElement skillNameInput;

    @FindBy(xpath = "//input[@id='skillRange']")
    private ExtendedWebElement skillRangeInput;

    @FindBy(xpath = "//button[contains(@class,'form-button')]")
    private ExtendedWebElement formButton;

    @FindBy(xpath = "//img[@class='avatar']")
    private ExtendedWebElement avatar;

    @FindBy(xpath = "//div[@class='skill-type']")
    private List<SkillBadge> skillBadges;

    public ElementsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isAvatarVisible() {
        return avatar.isElementPresent() && avatar.isVisible();
    }

    public boolean waitAvatarVisible(int timeoutSec) {
        try {
            new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutSec))
                    .until(ExpectedConditions.visibilityOf(avatar.getElement()));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean waitAvatarHidden(int timeoutSec) {
        try {
            new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutSec))
                    .until(ExpectedConditions.invisibilityOf(avatar.getElement()));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickHideMenu() {
        hideMenu.click();
    }

    public void clickSkillsButton() {
        skillsButton.click();
    }

    public NavItem gem() {
        return gemNavItem;
    }

    public String getGemMenuClass() {
        gemNavItem.waitUntilPresent(10);
        return gemNavItem.getRoot().getAttribute("class");
    }

    public void clickGemMenu() {
        gemNavItem.waitUntilClickable(10);
        gemNavItem.click();
    }

    public void fillSkillsForm(String skillName, String skillRange) {
        skillNameInput.clear();
        skillNameInput.type(skillName);

        skillRangeInput.clear();
        skillRangeInput.type(skillRange);
    }

    public void clickFormButton() {
        formButton.click();
    }

    public boolean isFormButtonDisabled() {
        String cls = formButton.getAttribute("class");
        String disabledAttr = formButton.getAttribute("disabled");
        boolean hasDisabledClass = cls != null && cls.contains("disabled");
        boolean hasDisabledAttr = disabledAttr != null;
        return hasDisabledClass || hasDisabledAttr;
    }

    public boolean isFormButtonEnabled() {
        return !isFormButtonDisabled();
    }

    public String checkNewSkill() {
        if (skillBadges == null || skillBadges.isEmpty())
            return "";
        StringBuilder sb = new StringBuilder();
        for (SkillBadge badge : skillBadges) {
            if (sb.length() > 0)
                sb.append(" | ");
            sb.append(badge.text());
        }
        return sb.toString();
    }

    public List<SkillBadge> skills() {
        return skillBadges;
    }

    public void clickInnerBtn() {
        innerButton.click();
    }

    public boolean isScrollButtonVisible() {
        return scrollButton.isElementPresent() && scrollButton.isVisible();
    }

    public void clickScrollBtn() {
        scrollButton.click();
    }
}
