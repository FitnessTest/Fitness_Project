Feature: Subscription Management
  As an admin
  I want to manage subscription plans for clients and instructors
  So that I can offer appropriate plans (e.g., Basic, Premium)

  Scenario: Assign a subscription plan to a new user
    Given I am logged in as an admin
    When I assign a "Basic" subscription plan to the following user:
      | Name         | Role        | Subscription Plan |
      | Amr Jamhour  | Client      | Basic             |
    Then the user "Amr Jamhour" should have the "Basic" subscription plan

  Scenario: Upgrade a user's subscription plan
    Given I am logged in as an admin
    And the following users exist:
      | Name         | Role        | Subscription Plan |
      | Ihab Habash  | Instructor  | Basic             |
    When I upgrade the subscription plan of "Ihab Habash " to "Premium"
    Then the subscription plan of "Ihab Habash " should be "Premium"

  Scenario: Downgrade a user's subscription plan
    Given I am logged in as an admin
    And the following users exist:
      | Name         | Role        | Subscription Plan |
      | Ameed Diab   | Client      | Premium           |
    When I downgrade the subscription plan of "Ameed Diab" to "Basic"
    Then the subscription plan of "Ameed Diab" should be "Basic"
