Feature: Notifications and Updates
  As a system administrator
  I want to notify clients about changes and updates
  So that they stay informed about program schedules and offers

  Scenario: Notify clients about program schedule changes
    Given the following program schedules exist:
      | Program Name     | Date       | Time   |
      | Yoga Class       | 2024-12-20 | 10:00 AM |
    When the schedule for "Yoga Class" changes to:
      | Date       | Time   |
      | 2024-12-22 | 2:00 PM |
    Then clients enrolled in "Yoga Class" should receive a notification about the schedule change

  Scenario: Announce new programs
    Given no prior notification has been sent
    When a new program "Pilates Workshop" is added with the following details:
      | Date       | Time     | Description           |
      | 2024-12-25 | 3:00 PM  | Introduction to Pilates |
    Then clients should receive a notification about the new program

  Scenario: Notify clients about special offers
    Given the following special offer exists:
      | Offer Name         | Discount | Valid Until |
      | Winter Wellness    | 20%      | 2024-12-31  |
    When the offer "Winter Wellness" is activated
    Then all clients should receive a notification about the "Winter Wellness" special offer
