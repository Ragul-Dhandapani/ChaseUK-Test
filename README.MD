## About the Project

- This is a backend rest assured api based project which includes below:
  - JAVA 11
  - CUCUMBER WITH JUNIT
  - CUCUMBER WITH TESTNG
  - ALLURE REPORT
  - GRADLE
  - REST ASSURED
- All the APIs being used from **https://jsonplaceholder.typicode.com**
- src/main => It has all the framework type of methods and classes which is very useful for testing or to utilise in scenarios
- src/test => It has all the Features & Scenarios which test and determines the outcome of API functionality

## To run this project
- gradle clean build --refresh-dependencies
- com.jpmorgan.chase.cucumbertests.definitions.MasterRunner with VM Options as "-Dallure.results.directory=build/allure-results"
- After execution completed
  - gradle allureServe or gradle allureReport

