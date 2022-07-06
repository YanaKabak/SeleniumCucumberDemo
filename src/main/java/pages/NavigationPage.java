package pages;

import base.BasePage;
import locators.Locator;
import locators.XPath;

public class NavigationPage extends BasePage {

    private final Locator usersMenuButton = new XPath("//a[@data-target='#s-menu-users']");
    private final Locator playersMenuButton = new XPath("//a[text()='Players']");

    private final Locator playersTable = new XPath("//table");

    public void clickOnUsersButton(){
        waitForElementVisibility(usersMenuButton);
        waitForElementToBeClickable(usersMenuButton);
        click("Нажимаем на кнопку пользователи: ", usersMenuButton);
    }

    public void clickOnPlayersButton(){
        waitForElementVisibility(playersMenuButton);
        waitForElementToBeClickable(playersMenuButton);
        click("Нажимаем на кнопку игроки: ", playersMenuButton);
    }

    public boolean isTableVisible(){
        return isElementVisible(playersTable);
    }
}
