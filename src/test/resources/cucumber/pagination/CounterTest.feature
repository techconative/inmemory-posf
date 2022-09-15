Feature: Counter Deep nested values

  Scenario: Deep Nested value like counter is 10
    Given Counter Data
    When Key value "counters.[].yearMonth.[].dateCounter.[].likes" is "10"
    Then Result size should be 2

  Scenario: Deep Nested value like counter is 20 with non-existant search term
    Given Counter Data
    When Complex filtering criteria is "counters.[].yearMonth.[].dateCounter.[].likes=10&*=Vega|vegas"
    Then Result size should be 0