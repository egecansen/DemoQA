# DemoQA Automation Sample
In this project, i created a UI automation framework, using _**Selenium & Cucumber**_. I applied _**POM Design pattern**_, creating page objects for each page, and writing step classes which bined the methods inside page objects to the _**Cucumber**_ steps by instantiating the class object of the page. This project is build using basic principles of _**OOP**_, combines _**BDD**_ with Selenium to test integrity of the website. Therefore this project is scalible for future tests on this specific website.

## Run on terminal

- Go to project directory in terminal

- Run:
    ```yml
    mvn clean test
    ```
- To run a specific scenario you can use the scenario tags:
    ```yml
    mvn clean test @SCN-DemoQA-1
    ```
- To select a driver type, change the driver tags in scenarios.
- Tests can be run _headless_ by modifying the headless property in test.properties.
