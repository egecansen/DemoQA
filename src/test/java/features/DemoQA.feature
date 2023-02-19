Feature: DemoQA
  @Web-UI @Chrome @SCN-DemoQA-1
  Scenario: Submit form
    * Navigate to https://demoqa.com/
    * Select the card named Forms on the landing page
    * Select Practice Form from the selected card menu
    * Set window size to 720, 8000
    * Submit the user info by following:
      | First Name    | Egecan                 |
      | Last Name     | Sen                    |
      | Mail          | benegecansen@gmail.com |
      | Mobile Number | 5554443321             |
    * Gender: Male
    * Day: 04 Month: July Year: 1995
    * Subject: Arts
    * Hobby: Sports
    * Upload picture from: src/test/resources/pictures/di444.JPG
    * Address: Lale street, Leyla apartment, No: 7
    * State: Haryana
    * City: Panipat
    * Click Submit button
    * Verify the submitted value of Student Name on the submission modal
    * Verify the submitted value of Student Email on the submission modal
    * Verify the submitted value of Gender on the submission modal
    * Verify the submitted value of Mobile on the submission modal
    * Verify the submitted value of Date of Birth on the submission modal
    * Verify the submitted value of Subjects on the submission modal
    * Verify the submitted value of Hobbies on the submission modal
    * Verify the submitted value of Picture on the submission modal
    * Verify the submitted value of Address on the submission modal
    * Verify the submitted value of State and City on the submission modal
    * Wait 3 seconds

  Scenario: Drag drop
    * Navigate to https://demoqa.com/
    * Select the card named Widgets on the landing page
    * Select Slider from the selected card menu
    * Slide to 60
    * Wait 5 seconds
