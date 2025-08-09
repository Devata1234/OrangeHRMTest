# OrangeHRMTest
Selenium + TestNG Maven project to automate OrangeHRM demo:
https://opensource-demo.orangehrmlive.com/web/index.php/auth/login

## Structure
- pom.xml
- testng.xml
- src/main/java/pages
  - LoginPage.java
  - PIMPage.java
- src/test/java/base
  - BaseTest.java
- src/test/java/tests
  - OrangeHRMTest.java

## How to run
1. Install Java 11+ and Maven.
2. From project root run:
```
mvn clean test
```
This will open Chrome (managed by WebDriverManager), run the test, and close the browser.

Note: The demo site can be slow; implicit waits or explicit waits may be required for stability.
