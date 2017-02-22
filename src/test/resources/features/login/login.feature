# ALM Test Case ref :-
# Reviewed by :-		Ashok Natarajan
Feature: qbakbak login page

  @regression1 @smoke1 @bvts
  Scenario: TS001_verify the Login page
    Given I am logged into the qbak Web Application with User name "data_userName" and Password "data_defaultPwd"
    Then Verify i am on Wall page

  @regression @smoke @bvts
  Scenario: TS001_verify the Login page with Wrong password
    Given I am logged into the qbak Web Application with User name "data_userName" and Password "data_password_wrong"
    Then I should see a Warining message

  @regression2 @smoke2 @bvts2
  Scenario: TS001_verify the Login page by clciking on the sign upbutton
    Given Iam on the qbakbak login page
    When I should be able to click on signup button
    Then verify iam on the Sign up page