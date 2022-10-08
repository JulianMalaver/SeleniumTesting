Feature: Test
  We are going to test the base components


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
