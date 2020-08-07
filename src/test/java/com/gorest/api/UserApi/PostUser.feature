
Feature: Add User into System via POST method.

    POST method testing

    Background: Assuming POST Method is working as expected
@ValidRequestBody
    Scenario: Provide all minimum data with the request body
    Given User API is requested with valid url
    When Test method provide all the valid data with request body
    Then User API should return response with 200 OK
    And User API should return all details related to new User with valid ID

