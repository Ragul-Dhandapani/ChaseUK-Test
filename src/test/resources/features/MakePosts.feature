Feature: Verify the user should be able to make a posts

  Background:
    Given the base URI is "https://jsonplaceholder.typicode.com"

  @Posts
  Scenario Outline: Get posts by ID
    When user send a GET request to "/posts/<id>"
    And the response status code should be <status_code> and reason  "<status_reason>"
    And more then one user's posts should be in response data

    Examples:
      | id  | status_code | status_reason   |
      | 1   | 200         | HTTP/1.1 200 OK |
      | 100 | 200         | HTTP/1.1 200 OK |

  @Posts @NegativeScenario
  Scenario Outline: Attempt to Search an Invalid posts by ID
    When user send a GET request to "/posts/<id>"
    Then the response status code should be <status_code> and reason  "<status_reason>"

    Examples:
      | id  | status_code | status_reason          |
      | 101 | 404         | HTTP/1.1 404 Not Found |

  @Posts
  Scenario Outline: Create new post
    When the user create a "/posts" with userId <user_id>, title "<title>" and body "<body>"
    Then the response status code should be <status_code> and reason  "<status_reason>"
    And the response "json" schema "<schema_name>" should match with specification
    Then all the posts made by the user with userId <user_id> should be returned

    Examples:
      | user_id | title | body        | status_code | status_reason        | schema_name               |
      | 1       | Mr    | Test Body 1 | 201         | HTTP/1.1 201 Created | MakePostApiResponseSchema |
      | 2       | Mrs   | Test Body 2 | 201         | HTTP/1.1 201 Created | MakePostApiResponseSchema |
      | 101     |       | test body   | 201         | HTTP/1.1 201 Created | MakePostApiResponseSchema |
      | 102     | test  |             | 201         | HTTP/1.1 201 Created | MakePostApiResponseSchema |
      | 103     |       |             | 201         | HTTP/1.1 201 Created | MakePostApiResponseSchema |

  @Posts
  Scenario Outline: Update and existing post by ID
    When user updates to existing "/posts/<id>" with userId <user_id>, title "<title>" and body "<body>"
    Then the response status code should be <status_code> and reason  "<status_reason>"

    Examples:
      | id  | user_id | title           | body                  | status_code | status_reason                      |
      | 1   | 1       | Updated Title 1 | Updated Body 1        | 200         | HTTP/1.1 200 OK                    |
      | 2   | 2       | Updated Title 2 | Updated Body 2        | 200         | HTTP/1.1 200 OK                    |
      | 101 | 3       | Updated Title 3 | Updated Body 3 with & | 500         | HTTP/1.1 500 Internal Server Error |
      #API does not have validation to throw an error for negative numbers
      | 2   | -1      | Updated Title 4 | Updated Body 2        | 400         | HTTP/1.1 Bad Request               |

  @Posts
  Scenario Outline: Delete post by ID
    When user wish to DELETE posts "/posts/<id>"
    Then the response status code should be <status_code> and reason  "<status_reason>"

    Examples:
      | id  | status_code | status_reason        |
      | 1   | 200         | HTTP/1.1 200 OK      |
      | 2   | 200         | HTTP/1.1 200 OK      |
      #API by default is returning 200 OK for any post id
      | 101 | 404         | HTTP/1.1 Not Found   |
      | -1  | 400         | HTTP/1.1 Bad Request |
