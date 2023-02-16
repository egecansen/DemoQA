Feature: DemoQA
  Scenario: Submit form
    * Navigate to https://demoqa.com/
    * Select the card named Forms on the landing page
    * Select Practice Form from the selected card menu
    * Submit the user info by following:
      | First Name    | Egecan                 |
      | Last Name     | Sen                    |
      | Mail          | benegecansen@gmail.com |
      | Mobile Number | 5554443321             |
    * Gender: Male
    * Day: 04 Month: July Year: 1995
    * Subject: Arts
    * Hobby: Sports
    * Upload picture from: src/test/resources/pictures/lala.jpeg
    * Address: Lale street, Leyla apartment, No: 7
    * State: Haryana
    * City: Panipat
    * Click Submit button
    * Verify the submitted value of Name on the submission modal
    * Verify the submitted value of Email on the submission modal
    * Verify the submitted value of Gender on the submission modal
    * Verify the submitted value of Mobile Number on the submission modal
    * Verify the submitted value of Date Of Birth on the submission modal
    * Verify the submitted value of Subjects on the submission modal
    * Verify the submitted value of Hobby on the submission modal
    * Verify the submitted value of Picture on the submission modal
    * Verify the submitted value of Address on the submission modal
    * Verify the submitted value of State and City on the submission modal
    * Wait 3 seconds