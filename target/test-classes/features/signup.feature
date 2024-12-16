Feature: Signup Flow

  Scenario: User signs up successfully and verifies login
    Given the user is on the Magento homepage
    When the user navigates to the signup page
    And the user enters valid signup details
    And clicks on the "Create an Account" button
    Then the user should be successfully signed up
    And the user should see a welcome message
    When the user logs out
    And the user logs in with the newly created account
    Then the user should see their account dashboard

  Scenario: User attempts to sign up with an already registered email
    Given the user is on the Magento homepage
    When the user navigates to the signup page
    And the user enters an already registered email
    And clicks on the "Create an Account" button
    Then the user should see an error message about the email being already registered

  Scenario: User attempts to sign up with mismatched passwords
    Given the user is on the Magento homepage
    When the user navigates to the signup page
    And the user enters mismatched passwords
    And clicks on the "Create an Account" button
    Then the user should see an error message about password mismatch
