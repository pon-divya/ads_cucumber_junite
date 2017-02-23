# ALM Test Case ref :-
# Reviewed by :-		Ashok Natarajan
Feature: qbakbak Home page

  @regression @smoke @bvts
  Scenario: TS001_verify the Home page
    Given I am logged into the qbak Web Application with User name "data_userName" and Password "data_defaultPwd"
    Then Verify i am on Wall page