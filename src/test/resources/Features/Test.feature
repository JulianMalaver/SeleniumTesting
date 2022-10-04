Feature: Test
  We are going to test the base components

  @Test
  Scenario: Get to the site
    Given I am in the App main site

  @Test
    Scenario: Test
      Given I go to site https://www.spotify.com/co/signup

  @Test
  Scenario: Get Url
    Given I go to site https://www.spotify.com/co/signup
    Then I load the DOM information Test.json
    And  I close the window

  @Test
  Scenario: Test
    Then I load the DOM information Test.json