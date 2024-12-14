Feature: Account Management

  As a user
  I want to create and customize my profile
  So that I can manage my personal details and dietary preferences

  Scenario: Create a new user profile
    Given the user opens the account management page
    When the user enters their name as "Amr Jamhor"
    And the user enters their age as "21"
    And the user selects "Weight Loss" as their fitness goal
    And the user selects "Gluten-Free" and "Low-Carb" as their dietary preferences
    Then the profile should be created with the following details:
      | Name        | Age | Fitness Goal  | Dietary Preferences      |
      | Amr Jamhor  | 21  | Weight Loss   | Gluten-Free, Low-Carb    |

  Scenario: Update existing user profile
    Given the user has an existing profile with the following details:
      | Name        | Age | Fitness Goal  | Dietary Preferences      |
      | Amr Jamhor  | 21  | Weight Loss   | Gluten-Free, Low-Carb    |
    When the user updates their age to "22"
    And the user updates their fitness goal to "Muscle Gain"
    Then the profile should be updated with the following details:
      | Name        | Age | Fitness Goal  | Dietary Preferences      |
      | Amr Jamhor  | 22  | Muscle Gain   | Gluten-Free, Low-Carb    |
