Feature: BookStore

  @Authenticate @Web-UI
  Scenario: Bookstore post book test

  This test creates a user using book stores back end, then adds selected books to user in context
  Verifies the books exists on book store application front end for the user in context

    * Assign all books published by No Starch Press from bookstore to user in context
    * Get user in context
    * Navigate to https://demoqa.com/
    * Select the card named Book Store Application on the landing page
    * Select Login from the selected card menu
    * Submit with the user in context
    * Verify book details for the books of user in context
