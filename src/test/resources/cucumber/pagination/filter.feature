Feature: Filtering

  Scenario: Name is bharath
    Given Feed Rawdata
    When Filtering name "bharath"
    Then Result size should be 1

  Scenario: Name is sanjay OR neha
    Given Feed Rawdata
    When Filtering name "sanjay|neha"
    Then Result size should be 40

  Scenario: Nested value multiMedia.name is "AAAA"
    Given Feed Rawdata
    When Key value "multiMedia.[].name" is "AAAA"
    Then Result size should be 3

  Scenario: Search for "Vega|vegas"
    Given Feed Rawdata
    When Search terms are "Vega|vegas"
    Then Result size should be 15

  Scenario: Complex filtering and search in single operation
    Given Feed Rawdata
    When Complex filtering criteria is "multiMedia.[].name=CCCC&*=Vega|vegas&userId=4051"
    Then Result size should be 1