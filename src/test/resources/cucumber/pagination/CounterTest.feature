Feature: Counter Deep nested values

  Background:
    Given Counter Data

  Scenario: Deep Nested value like counter is 10
    When Key value "counters.[].yearMonth.[].dateCounter.[].likes" is "10"
    Then Result size should be 2

  Scenario: Deep Nested value like counter is 20 with non-existent search term
    When Complex filtering criteria is "counters.[].yearMonth.[].dateCounter.[].likes=10&*=Vega|vegas"
    Then Result size should be 0