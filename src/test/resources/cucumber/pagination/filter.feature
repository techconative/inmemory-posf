Feature: Filtering
  Scenario: Name is bharath
    Given Feed Rawdata
    When Filtering name "bharath"
    Then Result size should be 1
  Scenario: Nested value multiMedia/name is "AAAA"
    When Nested value multiMedia/name is "AAAA"
    Then Result size should be 3