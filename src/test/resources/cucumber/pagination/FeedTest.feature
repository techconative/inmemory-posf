Feature: Filtering on Feed data

  Background:
    Given Feed data

  Scenario: Name is bharath
    When Key value "name" is "bharath"
    Then Result size should be 1

  Scenario: Name is sanjay OR neha
    When Key value "name" is "sanjay|neha"
    Then Result size should be 40

  Scenario: Nested value multiMedia.name is "AAAA"
    When Key value "multiMedia.[].name" is "AAAA"
    Then Result size should be 3

  Scenario: Search for a URL
    When Key value "multiMedia.[].url" is "http://www.youtube.com/embed/TUT2-FEPMdc"
    Then Result size should be 5

  Scenario: Search for "Vega|vegas"
    When Search terms are "Vega|vegas"
    Then Result size should be 15

  Scenario: Filer by partial date
    When Key value "createdAt" is "2020-01-02"
    Then Result size should be 47

  Scenario: Filer by complete date
    When Key value "createdAt" is "2020-01-02T10:54:07.609+00:00"
    Then Result size should be 5

  Scenario: Complex filtering and search in single operation
    When Complex filtering criteria is "multiMedia.[].name=CCCC&*=Vega|vegas&userId=4051"
    Then Result size should be 1