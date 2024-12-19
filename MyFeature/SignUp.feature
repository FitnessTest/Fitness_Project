Feature:  Sign up

  Scenario:Signing up with an incorrect email format
    Given The user is not logged in
    When the email format is incorrect
    Then Signing up fails

  Scenario Outline:Signing up with an already existing email
    Given The user is not logged in
    When The provided credentials exists, the email is "<Email>"
    Then Signing up fails

    Examples:
      | Email                       |
      | amrojamhour4@gmail.com     |
      | kebab83@gmail.com      |

  Scenario Outline:Signing up with new email account
    Given The user is not logged in
    When The provided credentials exists, the email is not "<Email>"
    Then Signing up succeeds

    Examples:
      | Email                      |
      | invalidamr@gmail.com       |
      | invalidihab@gmail.com      |