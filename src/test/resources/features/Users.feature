Feature: Users API

  As a user
  I want to retrieve user information
  So that I can perform further actions based on user information

  Background:
    Given the base URI is "https://jsonplaceholder.typicode.com"

  @positive @Users
  Scenario Outline: Retrieve specific user information successfully
    When user retrieve the information for "/users/" user with their ID <user_id>
    Then the response status code should be <status_code> and reason  "<status_reason>"
    And the response "json" schema "<schema_name>" should match with specification
    Examples:
      | user_id | status_code | status_reason   | schema_name           |
      | 1       | 200         | HTTP/1.1 200 OK | UserApiResponseSchema |
      | 2       | 200         | HTTP/1.1 200 OK | UserApiResponseSchema |
      | 3       | 200         | HTTP/1.1 200 OK | UserApiResponseSchema |

  @negative @Users
  Scenario Outline: Retrieve user information with invalid ID
    When user retrieve the information for "/users/" user with their invalid ID "<user_id>"
    Then the response status code should be <status_code> and reason  "<status_reason>"

    Examples:
      | user_id | status_code | status_reason          |
      | invalid | 404         | HTTP/1.1 404 Not Found |