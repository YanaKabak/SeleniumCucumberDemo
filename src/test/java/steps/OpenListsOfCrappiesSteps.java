package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.Pages;

public class OpenListsOfCrappiesSteps {

    @And("Select the menu administration")
    public void SelectTheMenuAdministration() {
        Pages.navigationPage().clickOnUsersButton();
    }

    @And("Click on the button players")
    public void clickOnTheButtonPlayers() {
        Pages.navigationPage().clickOnPlayersButton();
    }

    @Then("Table of players should be visible")
    public void tableOfPlayersShouldBeVisible() {
        Assert.assertTrue(Pages.navigationPage().isTableVisible(), "TAble with players not visible");
    }
}
