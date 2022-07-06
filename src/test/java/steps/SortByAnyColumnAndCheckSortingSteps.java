package steps;

import actions.Actions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.Pages;

public class SortByAnyColumnAndCheckSortingSteps {
    @And("Click heading Username of Players table")
    public void iClickHeadingOfPlayersTable() {
        Pages.playersPage().clickOnUsernameColumn();
    }

    @Then("Check sorting of Username")
    public void iCheckSortingOfUsername() {
        Pages.playersPage().wait(2);
        Assert.assertTrue(Actions.playersActions().isTableSorted(),"Table not sorted");
    }
}
