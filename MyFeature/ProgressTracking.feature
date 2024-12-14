Feature: Progress Tracking

  As a user
  I want to track my personal fitness milestones and view achievements
  So that I can monitor my progress and celebrate my accomplishments

  Scenario: Track personal fitness milestones
    Given the user is on their progress tracking page
    When the user logs their weight as "70 kg"
    And the user logs their BMI as "22.5"
    And the user logs their attendance as "3 days per week"
    Then the user's fitness milestones should be updated with:
      | Metric   | Value     |
      | Weight   | 70 kg     |
      | BMI      | 22.5      |
      | Attendance | 3 days/week |

  Scenario: View achievements for completing programs
    Given the user has completed the "Yoga for Flexibility" program
    When the user views their achievements
    Then the user should see the following achievement:
      | Achievement     | Description                              |
      | "Yoga Beginner" | Completed the "Yoga for Flexibility" program |

  Scenario: Earn badges for completing programs
    Given the user has completed the "Muscle Building Basics" program
    When the user views their badges
    Then the user should see the following badge:
      | Badge           | Description                              |
      | "Muscle Builder"| Completed the "Muscle Building Basics" program |
