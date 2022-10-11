Feature: Test
  We are going to test the base components

  Background:
    Given I set UserEmail Value in Data Scenario

  @TestDOM
  Scenario: Get DOM
    Given I go to site https://accounts.spotify.com/en/login
    Then I load the DOM information Test.json
    Then I close the window

  @TestInsertInfo
  Scenario: Do a click
    Given I go to site https://accounts.spotify.com/en/login
    Then  I load the DOM information Test.json
    And I do a click in element Email
    And I set Email with text Testasdfasdf
    Then I close the window

  @TestScenario
  Scenario: Testing functionality
    Given I am in the App main site
    Then I load the DOM information Test.json
    And I save text of Text as Scenario Context
