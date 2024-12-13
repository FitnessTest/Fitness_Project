Feature: Content Management
  As an admin
  I want to manage wellness content and user feedback
  So that I can maintain high-quality and relevant content for users

  Scenario: Approve a pending article
    Given the following articles are pending approval:
      | Title              | Type      | Status   |
      | Healthy Eating 101 | Wellness  | Pending  |
      | Yoga for Beginners | Tips      | Pending  |
    When I approve the article "Healthy Eating 101"
    Then the status of the article "Healthy Eating 101" should be "Approved"

  Scenario: Reject a pending article
    Given the following articles are pending approval:
      | Title              | Type      | Status   |
      | Fitness Myths      | Tips      | Pending  |
    When I reject the article "Fitness Myths"
    Then the status of the article "Fitness Myths" should be "Rejected"

  Scenario: Resolve a user feedback
    Given the following feedbacks exist:
      | User       | Message                 | Status  |
      | Amr Jamhour| Slow response times     | Open    |
      | Ihab Habash| Missing recipe details  | Open    |
    When I resolve the feedback from "Amr Jamhour"
    Then the status of the feedback from "Amr Jamhour" should be "Resolved"
