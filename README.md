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
- `Automated screenshots`: When your test fail, Selenide will automatically take screenshot. You do not need to do anything for it.

### Disadvantages:
- `Size and Performance`: Adding Selenide to a project increases the number of dependencies and can slow down the initial project load.
- `Single Browser Configuration per Test Run`: Selenide is designed to run tests on a single browser per execution by default. This means that during a single build or test run, you can typically only run tests on one browser at a time. If you want to run the same test suite on multiple browsers (e.g., Chrome, Firefox, and Safari), you would need to run separate test executions, each configured for a different browser.