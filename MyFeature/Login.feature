Feature: Login

  Scenario Outline: Valid credentials
    Given The user is not logged in
    When the credentials is valid email is "<Email>" and password is "<Password>"
    Then User logs in successfully

    Examples:
      | Email                      | Password |
      | kebab83@gmail.com          | Ihab    |
      | amrojamhour4@gmail.com     | Amr     |

  Scenario Outline: Invalid email
    Given The user is not logged in
    When The email is invalid email is "<Email>" and password is "<Password>"
    Then User failed in log in

    Examples:
      | Email                       | Password |
      | invalidkebab@gmail.com      | Ihab    |
      |                             | Amr    |

  Scenario Outline: Invalid password
    Given The user is not logged in
    When The password is invalid email is "<Email>" and password is "<Password>"
    Then User failed in log in

    Examples:
      | Email                       | Password |
      | kebab83@gmail.com           | 0000     |
      | amrojamhour4@gmail.com      |          |

  Scenario Outline: Invalid credentials
    Given The user is not logged in
    When The credentials is invalid, email is "<Email>" and password is "<Password>"
    Then User failed in log in

    Examples:
      | Email                       | Password |
      | amrojamhour4@gmail.com      | b1b1     |
      |                             |          |
