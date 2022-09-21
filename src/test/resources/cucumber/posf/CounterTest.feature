Feature: Counter Deep nested values

  Background:
    Given Counter Data
    And Default Criteria

  Scenario Outline: Deep Nested filtering test in counter data
    When Complex filtering criteria is <criteria>
    Then Result size should be <expectedSize>
    And Object identifier <objID> is present in <expectedObjsList>

    Examples:
      | criteria                                                                                               | expectedSize | objID | expectedObjsList |
      | "counters.[].yearMonth.[].dateCounter.[].likes=10&counters.[].yearMonth.[].dateCounter.[].dislikes=20" | 2            | "id"  | "1347,1348"      |
      | "counters.[].yearMonth.[].dateCounter.[].likes=10&*=Vega\|vegas"                                       | 0            | "id"  | ""               |
