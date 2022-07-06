package pages;

import base.BasePage;
import locators.ID;
import locators.Locator;
import locators.Name;
import locators.XPath;

public class LoginPage extends BasePage {

    private final Locator usernameInput = new ID("UserLogin_username");
    private final Locator passwordInput = new ID("UserLogin_password");
    private final Locator adminPanel = new XPath("//p[contains(text(),'Tickets')]");

    private final Locator singInButton = new Name("yt0");

    public void inputLogin(String username) {
        waitForElementVisibility(usernameInput);
        waitForElementToBeClickable(usernameInput);
        type("Вводим пользователя: " + username, username, usernameInput);
    }

    public void inputPassword(String password) {
        waitForElementVisibility(passwordInput);
        waitForElementToBeClickable(passwordInput);
        type("Вводим пароль: " + password, password, passwordInput);
    }

    public void clickOnSignInButton(){
        waitForElementVisibility(singInButton);
        waitForElementToBeClickable(singInButton);
        click("Нажимаем на кнопку авторизации: ", singInButton);
    }

    public boolean isAdminPanelIsVisible(){
        return isElementVisible(adminPanel);
    }
}
