Feature: Filter on Student data

  Scenario: Retrieve mailId of a student
    Given Student Data
    When Key value "Email" is "Student11@gmail.com"
    Then Result size should be 1