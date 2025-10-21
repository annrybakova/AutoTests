package com.solvd.demo.ui.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ElementsPage extends AbstractPage {

    @FindBy(xpath = "//button[class='hide-btn']")
    private ExtendedWebElement hideMenu;

    @FindBy(xpath = "//img[class='avatar']")
    private ExtendedWebElement checkAvatar;

    @FindBy(xpath = "//li[data-icon='gem']")
    private ExtendedWebElement gemMenu;

    @FindBy(xpath = "//button[class='skills-btn']")
    private ExtendedWebElement skillsButton;

    @FindBy(xpath = "//input[@id='skillName']")
    private ExtendedWebElement skillNameInput;

    @FindBy(xpath = "//input[@id='skillRange']")
    private ExtendedWebElement skillRangeInput;

    @FindBy(xpath = "//button[class='form-btn']")
    private ExtendedWebElement formButton;

    @FindBy(xpath = "//div[class='skill-type']")
    private List<ExtendedWebElement> skillType;

    @FindBy(xpath = "//button[class='inner-btn']")
    private ExtendedWebElement innerButton;

    @FindBy(xpath = "//button[class='scroll-btn']")
    private ExtendedWebElement scrollButton;


    public ElementsPage(WebDriver driver) {
        super(driver);
    }

    public String getGemMenuClass() {
        String cls = gemMenu.getAttribute("class");
        return cls == null ? "" : cls;
    }

    public void clickGemMenu() {
        gemMenu.click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        String classAttribute = gemMenu.getAttribute("class");
        if (classAttribute != null && classAttribute.contains("active")) {
            System.out.println("Gem menu is active!");
        } else {
            System.out.println("Gem menu is not active.");
        }
    }

    public void clickHideMenu() {
        hideMenu.click();
    }

   public boolean isAvatarVisible() {
        return checkAvatar.isDisplayed();
    }

    public void fillSkillsForm(String skillName, String skillRange) {
        skillNameInput.type(skillName);
        skillRangeInput.type(skillRange);
    }

    public void clickFormButton() {
        formButton.click();
    }

    public boolean isFormButtonDisabled() {
        String disabled = formButton.getAttribute("disabled");
        // Some UIs set disabled="true" or just presence of the attribute, and sometimes disable via class + isEnabled()
        return (disabled != null && !disabled.isEmpty()) || !formButton.getElement().isEnabled();
    }

    public String checkNewSkill() {
        return skillType.toString();
    }

    public void clickInnerBtn() {
        scrollButton.click();
    }

    public boolean isScrollButtonVisible() {
        return scrollButton.isElementPresent() && scrollButton.isVisible();
    }

    public void clickScrollBtn() {
        innerButton.click();
    }

}

