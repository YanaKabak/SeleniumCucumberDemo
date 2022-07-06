package steps;

import actions.Actions;
import base.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.Pages;
import util.Constants;

public class LoginSteps {

    @Given("Open the Chrome and launch the application")
    public void openTheChromeAndLaunchTheApplication()
    {
        Actions.mainActions().openMainPage();
    }

    @When("Enter the Username and Password")
    public void enterTheUsernameAndPassword()
    {
        Pages.loginPage().inputLogin(Constants.USERNAME);
        Pages.loginPage().inputPassword(Constants.PASSWORD);
    }

    @And("Click on SignIn Button")
    public void clickOnSignInButton()
    {

        Pages.loginPage().clickOnSignInButton();
    }

    @Then("Check admin panel Visible")
    public void checkAdminPanelVisible()
    {
        Assert.assertTrue(Pages.loginPage().isAdminPanelIsVisible(), "Панель администратора не загрузилась");
    }

}
