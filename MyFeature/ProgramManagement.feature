Feature: Program Management

  Scenario: Create a fitness program
    Given I am logged in as an instructor
    When I create a new program with the following details:
      | Title      | Duration | Difficulty | Goals               | Price |
      | Yoga Basics| 4 weeks  | Beginner   | Flexibility & Relax | $50   |
    Then the program "Yoga Basics" should be created successfully

  Scenario: Update a fitness program
    Given I am logged in as an instructor
    When I create a new program with the following details:
      | Title      | Duration | Difficulty | Goals               | Price |
      | Yoga Basics| 4 weeks  | Beginner   | Flexibility & Relax | $50   |
    And I update the program details of "Yoga Basics" to the following:
      | Duration | Difficulty | Goals                     | Price |
      | 6 weeks  | Intermediate| Flexibility & Mindfulness | $75   |
    Then the details of the program "Yoga Basics" should be updated successfully

  Scenario: Delete a fitness program
    Given I am logged in as an instructor
    When I create a new program with the following details:
      | Title      | Duration | Difficulty | Goals               | Price |
      | Yoga Basics| 4 weeks  | Beginner   | Flexibility & Relax | $50   |
    And I delete the program "Yoga Basics"
    Then the program "Yoga Basics" should be removed successfully
