Feature: Filter on Student data

  Scenario: Retrieve mailId of a student
    Given Student Data
    And Default Criteria
    When "Email" has "Student11@gmail.com"
    Then Result size should be 1
    And "ID" present in "11"