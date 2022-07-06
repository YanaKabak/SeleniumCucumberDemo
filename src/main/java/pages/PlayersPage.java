package pages;

import base.BasePage;
import locators.Locator;
import locators.XPath;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PlayersPage extends BasePage {

    private final Locator usernameColumn = new XPath("//a[text()='Username']");
    private final Locator listOfUsername = new XPath("//tbody//tr/child::node()[3]");

    public void clickOnUsernameColumn(){
        waitForElementVisibility(usernameColumn);
        waitForElementToBeClickable(usernameColumn);
        click("Нажимаем на заголовок Username: ", usernameColumn);
    }

    public List<WebElement> tableSorted(){
        return getElements(listOfUsername);
    }

}
