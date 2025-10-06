Feature: Store

  As a user
  I want to make a requests and compare them to expected data
  So that i can easily check that the system works properly

  @psapi-1
  @store @inventory
  Scenario: Verify GET store inventory request
    Given send a GET request to "/inventory" endpoint
    Then status code should be 200
    And response should match to "store_inventory" schema

  @psapi-2
  @store @order
  Scenario: Verify POST store order request | Successful
    Given send a POST request to "/order" endpoint:
      | Field    | Value                    |
      | petId    | 7                        |
      | quantity | 1                        |
      | shipDate | 2025-10-05T11:25:11.609Z |
      | status   | placed                   |
      | complete | true                     |
    Then status code should be 200
    And response should match to "order" schema

  @psapi-3
  @store @order
  Scenario: Verify POST store order request | Incorrect type
    Given send a POST request to "/order" endpoint:
      | Field    | Value                    |
      | petId    | abs                      |
      | quantity | 1                        |
      | shipDate | 2025-10-05T11:25:11.609Z |
      | status   | placed                   |
      | complete | true                     |
    Then status code should be 500
    And message should be "something bad happened"

  @psapi-4
  @store @order
  Scenario: Verify POST store order request | Incorrect value
    Given send a POST request with invalid body to "/order" endpoint
    Then status code should be 400
    And message should be "bad input"

  @psapi-5
  @store @order
  Scenario: Verify GET store by id request
    Given send a GET request to "/order" endpoint with id 1903
    Then status code should be 200
    And response should match to "order" schema

  @psapi-6
  @store @order
  Scenario: Verify GET store by id request | Id not found
    Given send a GET request to "/order" endpoint with id 2874
    Then status code should be 404
    And message should be "Order not found"

  @psapi-7
  @store @order
  Scenario Outline: Verify DELETE store by id request | Successful
    Given send a POST request to "/order" endpoint:
      | Field    | Value                    |
      | id       | <id>                     |
      | petId    | <id>                     |
      | quantity | 1                        |
      | shipDate | 2025-10-05T11:25:11.609Z |
      | status   | placed                   |
      | complete | true                     |
    When send a DELETE request to "/order" endpoint with id <id>
    Then status code should be 200
    And message should be "<id>"
    Examples:
      | id   |
      | 2222 |

  @psapi-8
  @store @order
  Scenario: Verify DELETE store by id request | Order not found
    Given send a DELETE request to "/order" endpoint with id -1
    Then status code should be 404
    And message should be "Order Not Found"