Feature: Filtering on Feed data

  Background:
    Given Feed data
    And Default Criteria

  Scenario Outline: Check if key <key> has <value> atleast once
    When <key> has <value>
    Then Result size should be <size>
    And <objId> present in <expectedObj>

    Examples:
      | key                  | value                                      | size | objId | expectedObj                                                                                                                                                                                                                                  |
      | "name"               | "bharath"                                  | 1    | "id"  | "2137"                                                                                                                                                                                                                                       |
      | "name"               | "sanjay\|neha"                             | 40   | "id"  | "2128,2127,2125,2124,2122,2121,2108,2105,2128,2127,2125,2124,2122,2121,2108,2105,2128,2127,2125,2124,2122,2121,2108,2105,2128,2127,2125,2124,2122,2121,2108,2105,2128,2127,2125,2124,2122,2121,2108,2105,"                                   |
      | "multiMedia.[].name" | "AAAA"                                     | 3    | "id"  | "2137,2136,2135"                                                                                                                                                                                                                             |
      | "multiMedia.[].url"  | "http://www.youtube.com/embed/TUT2-FEPMdc" | 5    | "id"  | "2138,2138,2138,2138,2138"                                                                                                                                                                                                                   |
      | "createdAt"          | "2020-01-02"                               | 47   | "id"  | "2140,2139,2138,2137,2136,2135,2134,2133,2132,2140,2139,2138,2137,2136,2135,2134,2133,2132,2131,2130,2140,2139,2138,2137,2136,2135,2134,2133,2132,2140,2139,2138,2137,2136,2135,2134,2133,2132,2140,2139,2138,2137,2136,2135,2134,2133,2132" |
      | "createdAt"          | "2020-01-02T10:54:07.609+00:00"            | 5    | "id"  | "2139,2139,2139,2139,2139"                                                                                                                                                                                                                   |


  Scenario Outline: Complex filter criteria in Feed data
    When Complex filtering criteria is <criteria>
    Then Result size should be <expectedSize>
    And <objId> present in <expectedObjs>

    Examples:
      | criteria                                            | expectedSize | objId | expectedObjs                                                                 |
      | "*=Vega\|vegas"                                     | 15           | "id"  | "1096,1096,1096,1096,1096,2101,2101,2101,2101,2101,2137,2137,2137,2137,2137" |
      | "multiMedia.[].name=CCCC&*=Vega\|vegas&userId=4051" | 1            | "id"  | "2137"                                                                       |
