Feature: Program Exploration and Enrollment

  As a user
  I want to browse fitness programs by filters and enroll in them
  So that I can find suitable programs for my goals and view schedules

  Scenario: Browse programs by difficulty level
    Given the user is on the program exploration page
    When the user selects "Beginner" as the difficulty level filter
    Then the user should see programs that are labeled as "Beginner"

  Scenario: Browse programs by focus area
    Given the user is on the program exploration page
    When the user selects "Muscle Building" as the focus area filter
    Then the user should see programs that focus on "Muscle Building"

  Scenario: Browse programs by multiple filters
    Given the user is on the program exploration page
    When the user selects "Intermediate" as the difficulty level filter
    And the user selects "Flexibility" as the focus area filter
    Then the user should see programs that are "Intermediate" and focus on "Flexibility"

  Scenario: Enroll in a program
    Given the user has selected a program with the following details:
      | Program Name    | Difficulty Level | Focus Area      |
      | Yoga for Flexibility | Beginner        | Flexibility     |
    When the user clicks the "Enroll" button
    Then the user should be enrolled in "Yoga for Flexibility" program

  Scenario: View program schedule
    Given the user has enrolled in the "Yoga for Flexibility" program
    When the user views the program schedule
    Then the user should see the program's schedule with available days and times
