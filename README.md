# Selenide vs Selenium: Testing Comparison

## Overview
This project demonstrates parallel test execution using both Selenide and Selenium. It covers the basic login functionality and file download scenarios on The Internet Herokuapp to compare the capabilities of Selenide and Selenium in a parallel execution environment.

## Technologies Used
**Selenium**: A widely-used open-source tool for automating browsers. It provides a suite of tools to automate web browsers across many platforms.

**Selenide**: A wrapper around Selenium that simplifies the process of writing automated tests. It provides a more user-friendly API and adds additional features such as automatic waiting.

**TestNG**: A testing framework inspired by JUnit, used for running tests in parallel.

## Advantages and Disadvantages
### Selenide Advantages:
- `Transparent WebDriver` : You don’t need to operate with WebDriver directly. Selenide will start and shut down the browser automatically whenever it’s needed.


- `Convenience methods`: Selenide provides concise API for that makes your tests shorter and more readable. Selenide has convenient methods for operating controls like textfield, radiobutton, selectbox, searching elements by texts and so on.
```sh
@Test
public void canFillComplexForm() {
  open("/client/registration");
  $(By.name("user.name")).val("johny");
  $(By.name("user.gender")).selectRadio("male");
  $("#user.preferredLayout").selectOption("plain");
  $("#user.securityQuestion").selectOptionByText("What is my first car?");
}
```
- `Waiting`: Selenide has built-in methods for waiting.
```sh
 $("#topic").should(appear);
 $("#topic").shouldBe(visible);

 $("#topic").should(disappear);
 $("h1").shouldHave(text("Hello"));
 $(".message").shouldNotHave(text("Wait for loading..."));
 $(".password").shouldNotHave(cssClass("errorField"));
 
 $(".error").should(disappear);
```
- `File downloading via Selenium Grid`: By default, when running tests on a remote WebDriver session (e.g., via Selenium Grid), downloaded files are typically stored on the machine where the browser session is running (the node), not on your local machine. However, it is possible to configure Selenium to download files to a specific directory on the local machine.


- `Automated screenshots`: When your test fail, Selenide will automatically take screenshot. You do not need to do anything for it.

### Disadvantages:
- `Size and Performance`: Adding Selenide to a project increases the number of dependencies and can slow down the initial project load.


- `Single Browser Configuration per Test Run`: Selenide is designed to run tests on a single browser per execution by default. This means that during a single build or test run, you can typically only run tests on one browser at a time. If you want to run the same test suite on multiple browsers (e.g., Chrome, Firefox, and Safari), you would need to run separate test executions, each configured for a different browser.


## Test Class Documentation

### 1. **Selenide File Download Test**
#### Class: `SelenideGridDownloadDemo`

**Description**:
This test class demonstrates how to download a file using Selenium within a Selenium Grid environment. The test ensures that the file some-file.txt is successfully downloaded and is not empty.

### Key Advantages of Selenide Over Selenium:

- `Local File Downloads in Remote Execution`: Selenide simplifies file download handling by allowing files to be downloaded directly to the local machine from which the tests are executed, even when running tests on a remote machine via Selenium Grid. Selenium, on the other hand, downloads files to the remote machine by default, making local access to these files more complex and requiring additional setup.
- `Automatic Waiting`: Selenide automatically handles waiting for elements to become visible or interactable, making tests more robust and less prone to timing issues. In contrast, Selenium requires manual waiting mechanisms, such as Thread.sleep, which can lead to less efficient and more brittle tests.
- `Concise and Readable Code`: Selenide reduces boilerplate code by providing a higher-level API that abstracts away many repetitive tasks, such as element selection and interaction. This makes Selenide tests shorter, more readable, and easier to maintain compared to the more verbose and detailed Selenium code required to achieve the same functionality.
- `Simplified File Download Handling`: Selenide's approach to file downloads is straightforward and integrated within its API, allowing for easier management of downloaded files. Selenium requires additional configuration and more code to handle file downloads, especially when using different browsers or operating systems.


### 2. **Selenide Login Test**
#### Class: `SelenideLoginDemo`

**Description**:
This test class demonstrates how to perform a login test using Selenide. It automates the login process on the "The Internet Herokuapp" website, verifying that the login is successful by checking the success message displayed after logging in.

### Key Advantages of Selenide Over Selenium:

- `Concise Code`: The Selenide test is more concise and readable. By using Selenide's selector methods like $ and concise actions like setValue(), click(), and shouldHave(), the code is significantly shorter and easier to understand compared to Selenium.
- `Automatic Waiting`: Selenide automatically waits for elements to be visible and ready for interaction. In this test, the $("#flash").shouldHave(text("You logged into a secure area!")); line waits for the success message to appear, which makes the test less flaky and more reliable. In contrast, Selenium requires explicit waits or can suffer from issues if elements are not immediately available.
- `Reduced Boilerplate`: Selenide abstracts many of the lower-level details, such as managing waits and browser interactions, resulting in cleaner and less error-prone code. The Selenide setup involves minimal configuration, while Selenium requires more setup, including explicit waits and WebDriver management.
- `Element Interaction`: Selenide's $ method simplifies element interaction by reducing the need for multiple lines of code to find and interact with web elements. In contrast, Selenium's approach is more verbose, requiring separate steps to locate and interact with elements.

### 3. **Selenide Parallel Testing**
#### Class: `SelenideParallelTests`

**Description**:
This test class demonstrates how to execute parallel login tests using Selenide. The tests check both valid and invalid login scenarios on The Internet Herokuapp. Selenide simplifies the process of running these tests in parallel, ensuring that configurations are handled seamlessly.

### Key Advantages of Selenide Over Selenium for Parallel Testing:

- `No Configuration Conflicts`: Selenide manages WebDriver instances automatically, ensuring that each test has its own isolated environment. This eliminates the risk of configuration overwrites or conflicts that can occur in Selenium when running tests in parallel. With Selenium, care must be taken to ensure that the configuration set up in one test does not interfere with another test, which could lead to conflicts and false test failures.
- `Automatic Browser Management`: Selenide abstracts away the complexity of browser management. Each test is provided with a fresh browser instance, which is automatically handled and closed after the test execution. Selenium requires explicit management of browser instances, increasing the risk of resource leaks and complicating parallel execution.
- `Simplified Parallel Execution`: Selenide's design inherently supports parallel execution without requiring additional setup or configuration. In Selenium, extra steps are needed to ensure that WebDriver instances are properly managed and that tests do not interfere with each other. This can lead to increased code complexity and maintenance efforts in Selenium-based projects.
