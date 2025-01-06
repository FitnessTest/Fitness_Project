Feature: Client Interaction

  As an instructor,
  I want to communicate with enrolled clients via messaging or discussion forums,
  So that I can provide feedback or progress reports to help them achieve their goals.

  Scenario: Send a message to an enrolled client
    Given I am logged in as an instructor
    And the client "Amr Jamhour" is enrolled in the program "Yoga Basics"
    When I send the following message to "Amr Jamhour":
      """
      Hi Amr, great progress this week! Keep up the good work.
      Let me know if you have any questions.
      """
    Then the client "Amr Jamhour" should receive the message successfully

  Scenario: Participate in a discussion forum for a program
    Given I am logged in as an instructor
    And there is a discussion forum for the program "Yoga Basics"
    When I post the following message to the forum:
      """
      Welcome everyone to the Yoga Basics program!
      Feel free to share your experiences or ask any questions.
      """
    Then the message should appear in the forum successfully

  Scenario: Provide feedback to a client
    Given I am logged in as an instructor
    And the client "Ihab Habash" is enrolled in the program "Advanced Pilates"
    When I provide the following feedback for "Ihab Habash":
      """
      Your balance and flexibility have improved significantly.
      Focus on your breathing techniques for better results.
      """
    Then the feedback should be recorded and visible to "Ihab Habash"

  Scenario: Generate a progress report for a client
    Given I am logged in as an instructor
    And the client "Ameed Diab" is enrolled in the program "Strength Training"
    When I generate a progress report for "Ameed Diab"
    Then the report should include the following details:
      | Program Name      | Completion Rate | Feedback                                |
      | Strength Training | 75%             | Excellent improvement in strength       |
