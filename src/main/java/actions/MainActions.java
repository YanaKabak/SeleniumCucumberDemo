package actions;

import base.BaseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Reporter;
import util.Constants;

public class MainActions{

    public void openMainPage() {
        Reporter.log("Opening main page: " + Constants.BASE_URL);
        BaseTest.getDriver().get(Constants.BASE_URL);
    }

    public void clearSession() {
        BaseTest.getDriver().manage().deleteAllCookies();
    }

    public void openPageInNewTab(String url) {
        Reporter.log("Opening page: " + url);
        ((JavascriptExecutor) BaseTest.getDriver()).executeScript("window.open('" + url + "');");
    }

    public int getTabCount() {
        return BaseTest.getDriver().getWindowHandles().size();
    }
}
