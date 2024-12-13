Feature: Program Monitoring
  As an admin
  I want to monitor programs
  So that I can view statistics, generate reports, and track program statuses

  Scenario: View most popular programs by enrollment
    Given the following programs exist:
      | Name          | Enrollment | Revenue | Status     |
      | Yoga Basics   | 50         | 500.0   | Active     |
      | Advanced Yoga | 30         | 300.0   | Completed  |
      | Pilates Pro   | 70         | 700.0   | Active     |
      | Zumba Party   | 20         | 200.0   | Active     |
    When I view the statistics for the most popular programs
    Then the most popular program should be "Pilates Pro"

  Scenario: Generate report on revenue and attendance
    Given the following programs exist:
      | Name          | Enrollment | Revenue | Status     |
      | Yoga Basics   | 50         | 500.0   | Active     |
      | Advanced Yoga | 30         | 300.0   | Completed  |
      | Pilates Pro   | 70         | 700.0   | Active     |
      | Zumba Party   | 20         | 200.0   | Active     |
    When I generate a report on revenue and attendance
    Then the total revenue should be 1700.0
    And the total enrollment should be 170

  Scenario: Track active and completed programs
    Given the following programs exist:
      | Name          | Enrollment | Revenue | Status     |
      | Yoga Basics   | 50         | 500.0   | Active     |
      | Advanced Yoga | 30         | 300.0   | Completed  |
      | Pilates Pro   | 70         | 700.0   | Active     |
      | Zumba Party   | 20         | 200.0   | Completed  |
    When I track the active and completed programs
    Then there should be 2 active programs and 2 completed programs
