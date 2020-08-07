
Feature: Albums API Testing for GET method.

    Below are the test scenarios for GET Albums api.

    Background: Assuming that Albums API is up and running on test server.
    @200OK
    Scenario: API Health check to ensure that api is returning 200 OK status.
        Given Albums api url(endpoint) is correct
        When  test method call Albums api GET method
        Then  Albums api should return 200 OK http status code
    #should must be returns

    @ResponseWithData
    Scenario: Albums Api should return response with valid body and data.
        Given the GET Albums api url(endpoint) is validated by the consumer
        When Test method call Albums api with GET method
        Then Albums api should return response with valid body with data
    @ResponseById
    Scenario: Albums Api should return only specific data by ID.
        Given Albums api requests url with a valid id number as path parameter
        When Test method call Albums api with Get method and url with id
        Then Albums api should return only response data with the given id
    @ResponseForInvalidId
    Scenario: Albums API should return data not found when passing invalid ID.
        Given Albums api requests url with an invalid ID as path parameter
        When Test method call Albums api with Get method with an invalid ID
        Then Albums api should return data not found
    @RequestWithoutToken
    Scenario: Albums API should return unauthorized code when passing invalid or emtpy token
        Given Albums api requests url has an invalid authorization code or empty code
        When Test method call Albums api with Get method and url
        Then Albums api should return unauthorized code 401 with message Authentication failed.
        And Albums Api should also return result with code 401 message "your request was made with invalid credential"

    @VerifyPageCount
    Scenario: Albums Api should return all matching response by page count
        Given Albums api requests url with page count as path parameter
        When Test method call Albums api with Get method with page count
        Then Albums api should return all matching response by page count

    @RequestResponseWithTitle
    Scenario: Albums Api should return the matching response by the specific title
        Given Albums api requests url with title as path parameter
        When Test method call Albums api with Get method with title
        Then Albums api should return the matching response by the specific title

    @RequestResponseFor_links.self.href
    Scenario: Albums Api should return the matching response by the specific link
        Given Albums api requests url with link as path parameter
        When Test method call Albums api with Get method with link
        Then Albums api should return the matching response by the specific link