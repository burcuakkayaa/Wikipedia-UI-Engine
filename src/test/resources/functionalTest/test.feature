Feature: Wikipedia Search Engine Test Cases

  @chrome
  Scenario: Wikipedia Search - Happy Path Search Case
    Given user is on the Search Page
    Then the Advanced Search bar should be present
    And the Search button should be present
    And the Search In bar should be present
    And the Search Result title should be present
    And the Wikipedia Logo should be displayed
    When user writes "Turkiye" to the search bar
    And user clicks the Search button
    Then user should see the search result info message
    And All results should be displayed
    And The url should contain the search value





