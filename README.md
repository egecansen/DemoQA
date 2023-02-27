# DemoQA Test Automation
In this project, i created a UI automation framework, using _**Selenium & Cucumber**_. I applied _**POM Design pattern**_, creating page objects for each page, and writing step classes which bined the methods inside page objects to the _**Cucumber**_ steps by instantiating the class object of the page. This project is build using basic principles of _**OOP**_, combines _**BDD**_ with _**Selenium**_ to test integrity of the website. Therefore this project is scalible for future tests on this specific website. 

This project is capable of both WebUI and API tests. Different kind of tests can be enabled by usage of scenario tags. Scenarios with `@Web-UI` tag will have a browser initialized while scenarios with `@Authenticate` tag will generate authentication tokens for backend tests.

## Run on terminal

- Go to project directory in terminal

- Run:
    ```yml
    mvn clean test
    ```

- To run a specific scenario use the special scenario tags:
    ```yml
    mvn clean test -q -Dcucumber.filter.tags="@SCN-DemoQA-1"
    ```
- To select a driver type, change the driver tag in scenarios.

- Tests can be run headless by modifying the headless property in _test.properties_.
