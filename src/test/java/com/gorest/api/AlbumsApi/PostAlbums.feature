Feature: Add Albums into System via POST method.

    POST method testing

    Background: Assuming POST Method is working as expected
@ValidRequestBody
    Scenario: Provide all minimum data with the request body
    Given Albums API is requested with valid url
    When Test method provide all the valid data with request body
    Then Albums API should return response with 200 OK
    And Albums API should return all details related to new Albums with valid ID