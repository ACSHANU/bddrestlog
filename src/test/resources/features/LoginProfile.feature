@LoginProfile
Feature: Login Profile
  As an employee of the company
  I want to login my employee profile using my credentials
  In order to collaborate with my colleagues


  Background: User navigates to Company home page
    Given I am on the "Company home" page on URL "www.mycomany.com"
    Then I should see "Log In as Employee" message

  Scenario: Successful login
    When I fill in "Username" with "Test"
    And I fill in "Password" with "123"
    And I click on the "Log In" button
    Then I am on the "My profile" page on URL "www.mycompany.com/myprofile"
    And I should see "Welcome to your profile" message
    And I should see the "Log out" button

