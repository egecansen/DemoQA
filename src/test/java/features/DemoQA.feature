Feature: DemoQA
  @Web-UI @Chrome @SCN-DemoQA-1
  Scenario: Submit Form Test
    * Adjust window size to 480, 5000 and navigate to https://demoqa.com/
    * Select the card named Forms on the landing page
    * Select Practice Form section from the selected card menu
    * Submit the user info by following:
      | First Name    | Egecan                 |
      | Last Name     | Sen                    |
      | Mail          | benegecansen@gmail.com |
      | Mobile Number | 5554443321             |
    * Gender: Male
    * Day: 04 Month: July Year: 1995
    * Subject: Arts
    * Hobby: Sports
    * Upload picture from: src/test/resources/files/pictures/di444.JPG
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

  @Web-UI @SCN-DemoQA-2
  Scenario: Slider interaction
    * Navigate to https://demoqa.com/
    * Select the card named Widgets on the landing page
    * Select Slider section from the selected card menu
    * Set window size to 10000, 10000
    * Slide the slider to 44

  @Web-UI @Chrome @SCN-DemoQA-3
  Scenario: Download and Upload interaction
    Project downloads directory adjusted for chrome only.
    * Navigate to https://demoqa.com/
    * Select the card named Elements on the landing page
    * Select Upload and Download section from the selected card menu
    * Click on download button from the selected section
    * Upload the downloaded file from downloads folder and clear directory
    * Wait 3 seconds