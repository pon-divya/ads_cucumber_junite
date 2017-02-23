# ALM Test Case ref :-
# Reviewed by :-		Ashok Natarajan
Feature: qbakbak login page

  @regression2 @smoke2
  Scenario: TS001_verify the Login page
    Given I am logged into the qbak Web Application with User name "data_userName" and Password "data_defaultPwd"
    And I am on HomePage
    Then Verify i am on Wall page

  @regression @smoke
  Scenario: TS001_verify the Login page with Wrong password
    Given Iam on the qbakbak login page
    And I enter the User Name "data_userName" and Password "data_password_wrong" click on login
    Then I should see a Warining message

  @regression @smoke
  Scenario: TS001_verify the Login page by clciking on the sign upbutton
    Given Iam on the qbakbak login page
    When I click on the Sign on btton
    Then I should sign in Page
