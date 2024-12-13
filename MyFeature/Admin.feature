Feature: User Management
  As an admin
  I want to manage user accounts
  So that I can maintain a secure and active user base

  Scenario: Add a new user
    Given I am logged in as an admin
    When I add a new user with the following details:
      | Name          | Role        | Email                     |
      | Amr Jamhour   | Instructor  | amrojamhour4@gmail.com    |
    Then the user "Amr Jamhour" should be added successfully

  Scenario: Update user details
    Given I am logged in as an admin
    And the following users exist:
      | Name          | Role        | Email                     |
      | Amr Jamhour   | Instructor  | amrojamhour4@gmail.com    |
    When I update the details of "Amr Jamhour" to the following:
      | Role       | Email                   |
      | Administrator | admin.amr@gmail.com  |
    Then the details of "Amr Jamhour" should be updated successfully

  Scenario: Deactivate a user
    Given I am logged in as an admin
    And the following users exist:
      | Name        | Role    | Status  |
      | Ameed Diab  | Client  | Active  |
    When I deactivate the user "Ameed Diab"
    Then the status of "Ameed Diab" should be "Deactivated"

  Scenario: Approve new instructor registration
    Given I am logged in as an admin
    And the following registration requests exist:
      | Name        | Email               | Status    |
      | Zahi Qudu   | Zahi.q83@gmail.com  | Pending   |
    When I approve the registration of "Zahi Qudu"
    Then the status of "Zahi Qudu" should be "Approved"
