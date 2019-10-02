Feature: Check men footbal shoes section

  Scenario: Check filters
    Given we open "sportland.lv" link
    When we push menu
    And we choose "PRODUKTI" section
    And we select "Futbols" under "APAVI" section in "ZĒNI" tab
    And we sort products by "Izpārdošana"
    And we select filters:
      | Futbola apavi cietam segumam |
      | Nike                         |
    Then on page appeared only "Nike" brand shoes
    And all products are on sale
    And we create txt file with with info about products
    And we create json file with the same information
    And we close browser



