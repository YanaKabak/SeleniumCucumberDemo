Feature: Opening table of players

  @SortedTable
  Scenario: Open players in admin interface
    Given Open the Chrome and launch the application
    When Enter the Username and Password
    And Click on SignIn Button
    And Select the menu administration
    And Click on the button players
    And Click heading Username of Players table
    Then Check sorting of Username