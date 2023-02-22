Feature: BookStore
  Background: Creating a context user
    * Create a new user by following:
      | userName    | password    |
      | zamazingo21 | Sanabana12* |

  @Authenticate
  Scenario: Post a new user
    * Generate token for the given user
    * Authorize user
    * Get user by the given id