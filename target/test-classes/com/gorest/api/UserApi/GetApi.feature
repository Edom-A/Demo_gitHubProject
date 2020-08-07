
Feature: User API Testing for GET method.

    Below are the test scenarios for GET User api.

    Background: Assuming that User API is up and running on test server.
    @200OK
    Scenario: API Health check to ensure that api is returning 200 OK status.
        Given Users api url(endpoint) is correct
        When  test method call Users api GET method
        Then  Users api should return 200 OK http status code
    #should must be returns

    @ResponseWithData
    Scenario: User Api should return response with valid body and data.
        Given the GET Users api url(endpoint) is validated by the consumer
        When Test method call Users api with GET method
        Then Users api should return response with valid body with data
    @ResponseById
    Scenario: User Api should return only specific data by ID.
        Given Users api requests url with a valid id number as path parameter
        When Test method call Users api with Get method and url with id
        Then Users api should return only response data with the given id
    @ResponseForInvalidId
    Scenario: User API should return data not found when passing invalid ID.
        Given Users api requests url with an invalid ID as path parameter
        When Test method call Users api with Get method with an invalid ID
        Then Users api should return data not found
    @RequestWithoutToken
    Scenario: User API should return unauthorized code when passing invalid or emtpy token
        Given Users api requests url has an invalid authorization code or empty code
        When Test method call Users api with Get method and url
        Then Users api should return unauthorized code 401 with message Authentication failed.
        And User Api should also return result with code 401 message "your request was made with invalid credential"
    @VerifyFirstName
    Scenario: User Api should return all matching response by first_name
        Given Users api requests url with a first_name as path parameter
        When Test method call Users api with Get method with first_name
        Then Users api should return all matching response
    @VerifyLastName
    Scenario: User Api should return all matching response by last_name
        Given Users api requests url with a last_name as path parameter
        When Test method call Users api with Get method with last_name
        Then Users api should return all matching response
    @VerifyEmail
    Scenario: User Api should return all matching response by email
        Given Users api requests url with an email as path parameter
        When Test method call Users api with Get method with an email
        Then Users api should only return the matching response
    @VerifyGender
    Scenario: User Api should return all matching response by gender
        Given Users api requests url with gender as path parameter
        When Test method call users api with Get method with gender
        Then Users api should return all matching response by the specific gender type
    @VerifyPageCount
    Scenario: User Api should return all matching response by page count
        Given Users api requests url with page count as path parameter
        When Test method call users api with Get method with page count
        Then Users api should return all matching response by page count




