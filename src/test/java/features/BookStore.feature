Feature: BookStore

  @Authenticate
  Scenario: Create a user and post books
    * Assign all books from bookstore published by No Starch Press to user in context
    * Get user in context
    * Post selected books for user in context
      | isbn          |
      | 9781449325862 |
      | 9781449331818 |
      | 9781449337711 |
    * Get user in context


  @Web-UI
  Scenario: Verify user
    * Navigate to https://demoqa.com/
    * Select the card named Book Store Application on the landing page
    * Select Login from the selected card menu
    * Submit with the user in context
    * Wait 3 seconds

