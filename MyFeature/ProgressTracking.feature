Feature: Progress Tracking
  As an instructor
  I want to monitor client progress and send motivational reminders
  So that clients stay motivated and achieve their fitness goals

  Scenario: Monitor client progress
    Given I have access to the progress tracking dashboard
    And the following clients exist:
      | Name       | Program         | Completion Rate | Attendance |
      | Amr Jamhour| Weight Loss  | 50%             | 75%        |
      | Ihab Habash| Muscle Gain    | 70%             | 80%        |
    When I review the progress of "Amr Jamhour"
    Then I should see the completion rate as "50%"
    And the attendance rate as "75%"

  Scenario: Send motivational reminders
    Given I have access to the client contact system
    And the following clients exist:
      | Name       | Program       | Completion Rate | Attendance |
      | Amr Jamhour| Weight Loss   | 50%             | 75%        |
      | Ihab Habash| Muscle Gain   | 70%             | 80%        |
    When I send a motivational reminder to "Amr Jamhour"
    Then the reminder should be sent successfully

  Scenario: Recommend actions to improve progress
    Given I have access to the progress tracking dashboard
    And the following clients exist:
      | Name       | Program         | Completion Rate | Attendance |
      | Amr Jamhour| Weight Loss     | 50%             | 75%        |
      | Ihab Habash| Muscle Gain     | 70%             | 80%        |
    When I review the progress of "Amr Jamhour"
    And the completion rate is below "60%"
    Then I should see a recommendation to "Attend more group sessions"
