Feature: Search For Product

  Background: Navigate to site
    Given the user is on the website 'https://www.amazon.co.uk' using 'remote'

  Scenario: Search for a Product
    When  the user searches for a product 'watch'
    Then the search results should be displayed
    When I click on the product 'casio'
    Then the PDP should be displayed

