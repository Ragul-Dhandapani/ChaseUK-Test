Feature: Verify user should be able to comment on post and able to see existing comments

  Background:
    Given the base URI is "https://jsonplaceholder.typicode.com"

  @positive
  Scenario Outline: Retrieve comments by valid post ID
    When user send a GET request to "/posts/<post_id>/comments"
    Then the response status code should be <status_code> and reason  "<status_reason>"
    And response for the respective postId <post_id> should be returned along with <expected_count>
    And each comment should have a valid email address

    Examples:
      | post_id | expected_count | status_code | status_reason   |
      | 1       | 5              | 200         | HTTP/1.1 200 OK |
      | 10      | 5              | 200         | HTTP/1.1 200 OK |

  @negative
  Scenario Outline: Retrieve comments by invalid post ID
    When user send a GET request to "/posts/<post_id>/comments"
    Then the response status code should be <status_code> and reason  "<status_reason>"

    Examples: #API does not have functionality to check whether one postId is present or not , due to which this API returns 200 status by default
      | post_id | status_code | status_reason          |
      | 0       | 404         | HTTP/1.1 404 Not Found |
      | 101     | 404         | HTTP/1.1 404 Not Found |
      | abc     | 404         | HTTP/1.1 404 Not Found |


  @positive
  Scenario Outline: Create a new comment
    When user create a new comment "/posts/<post_id>/comments" with "<name>", "<email>", and "<body>" for post ID <post_id>
    Then the response status code should be <status_code> and reason  "<status_reason>"
    And the response "json" schema "<schema_name>" should match with specification

    Examples:
      | name  | email                  | body                   | post_id | status_code | status_reason        | schema_name                     |
      | Rocky | rocky.jane@example.com | This is a test comment | 1       | 201         | HTTP/1.1 201 Created | CommentsApiResponseSchema |
      | Jane  | jane.rocky@example.com | Nice post              | 10      | 201         | HTTP/1.1 201 Created | CommentsApiResponseSchema |

  @negative
  Scenario Outline: Create a new comment with invalid request body
    When user create a new comment for "/posts/<post_id>/comments" with invalid request body "<body>"
    Then the response status code should be <status_code> and reason  "<status_reason>"

    Examples:
      | post_id | body | status_code | status_reason                      |
      | 1       | {}   | 400         | HTTP/1.1 400 Bad Request           |
      | 2       | []   | 400         | HTTP/1.1 400 Bad Request           |
      | 1       | {    | 500         | HTTP/1.1 500 Internal Server Error |
      | 2       | body | 500         | HTTP/1.1 500 Internal Server Error |

  @negative @CommentPosts
  Scenario Outline: User Deletes the Existing Comments by comment id
    When user wish to DELETE comments by "/comments/<comment_id>"
    Then the response status code should be <status_code> and reason  "<status_reason>"

    Examples:
      | comment_id | status_code | status_reason   |
      | 2          | 200         | HTTP/1.1 200 OK |
