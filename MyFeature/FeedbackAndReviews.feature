Feature: Feedback and Reviews

  As a user
  I want to rate and review completed programs and submit improvement suggestions
  So that I can share my experience and help improve the programs

  Scenario: Rate and review a completed program
    Given the user has completed the "Yoga for Flexibility" program
    When the user rates the program with a score of "4 stars"
    And the user writes a review saying "Great program for beginners, helped improve flexibility!"
    Then the program should have a review with:
      | Rating  | Review                                      |
      | 4 stars | Great program for beginners, helped improve flexibility! |

  Scenario: Submit an improvement suggestion to the instructor
    Given the user has completed the "Muscle Building Basics" program
    When the user submits a suggestion saying "Include more advanced exercises for intermediate learners"
    Then the instructor should receive the suggestion with the following content:
      | Suggestion                                           |
      | Include more advanced exercises for intermediate learners |
