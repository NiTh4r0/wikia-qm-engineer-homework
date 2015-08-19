Configuration:
- "JAVA_HOME" environment variable must point to JDK 1.8
- "webdriver_chrome_driver" environment variable must be set and point to the chromedriver's executable location
- all the data required to perform tests is stored in src/test/resource/wikia_homework_page_test_suite.xml
- to test using chrome set the "useChrome" parameter to "true" in above mentioned file

How to run tests from command line:
gradlew test