# Test Plan

**Author**: Disha

## 1 Testing Strategy

### 1.1 Overall strategy

The whole test plan will be finalized before any testing begins. The test plan needs to cover more than 90% functionality and should have all the details necessary. There will be unit testing to test the individual modules, integration testing to see how different modules work together, system testing to see how the whole system works and regression testing will be implemented multiple times to makes sure major functionalities are not impacted due to any code change.

### 1.2 Test Selection

#### 1.2.1 Unit Testing
* Each class created as part of the development will be tested as a single unit.
* Junit will be used to create the unit tests using different assertions.
* Plan is to have at least 90% coverage through these test cases.

#### 1.2.2 System Testing
* The whole app needs to be tested together as a user.
* The idea is to cover the major functionalities both functional or UI changes.
* The backend data checking will also be a part of this.
* Plan is to utilize espresso for testing this.

#### 1.2.3 Integration Testing
* Different class interactions will be tested as part of this.
* Similar to Unit testing, Junit will be used.
* UI testing will not be a part of this.

#### 1.2.4 Regression Testing
* This set of tests will include a set of defined test cases which tests the system as a whole.
* Only major functionalities will be included as part of this.
* These tests will be run everytime there is change anywhere in the code base.

### 1.3 Adequacy Criterion

* The inbuilt coverage results can be used to understand the overall coverage in the android studio.
* Testing the app as a user will make sure that all the functionalities are working as expected. The app will only be finalized once it is accepted by a user.
* Making sure all the corner cases are covered as well which will be added periodically as we see the bugs.
* There will be some manual testing as well to make sure the test cases are working as expected.

### 1.4 Bug Tracking

* Utilizing Jira to track the bugs and doing the testing to make sure it gets done.
* Each developer will be assigned bugs depending on the modules they are responsible for.
* Code commenting will be a must thing for all developers which will make sure the code readability and keep a track of the changes as part of a bug.

### 1.5 Technology

* Junit.
* Espresso.

## 2 Test Cases


| Test Case                                   | Description                                                                          | Steps to run                                            | Expected Result                                                    | Actual Result                                    | Pass/Fail | Additional Comments |
|---------------------------------------------|--------------------------------------------------------------------------------------|---------------------------------------------------------|--------------------------------------------------------------------|--------------------------------------------------|-----------| ----------- |
| Current Job Module UI                       | Current job page should be displayed                                                 | Click on enter/edit button on the main menu.            | Current job Page should be presented                               | Current Job page is presented                    | Pass      | |
| New Job Offer UI                            | New job offer page should be displayed                                               | Click on enter job offer on the main menu.              | New job Page should be presented                                   | New job Page is presented                        | Pass      | |
| Comparison Settings Module UI               | Comparison Settings page should be displayed                                         | Click on Comparison Settings button on the main menu.   | Comparison Settings Page should be presented                       | Comparison Settings Page is presented            | Pass      | |
| Compare Job offers Module UI                | Compare Job offers page should be displayed                                          | Click on Compare Job offers button on the main menu.    | Compare Job offers Page should be presented                        | Compare Job offers Page is presented             | Pass      | |
| Current Job data save                       | Validate that when the data entries are as per requirements the values are saved     | Enter each value and click on save.                     | The values should be saved successfully                            | Values are saved successfully                    | Pass      | |
| Validate new job offer saving               | Validate that when the data entries are as per requirements the values are saved     | Enter each value and click on save.                     | The values should be saved successfully                            | Values are saved successfully                    | Pass          | |
| Validate Comparison settings saving         | Validate that when the data entries are as per requirements the values are saved     | Enter each value and click on save.                     | The values should be saved successfully                            | Values are saved successfully                    | Pass          | |
| Validate copare jobs selected and displayed | Validate that when the data entries are as per requirements the values are saved     | Enter each value and click on save.                     | The values should be saved successfully                            | Values are saved successfully                    | Pass          | |
| Error scenarios on current job data         | Appropriate errors are thrown when the data entry is not as per requirements.        | Enter the wrong data and click on save                  | Appropriate errors are thrown                                      | Errors are thrown when fields are empty.         | Pass      | |
| Error scenarios on new job data             | Appropriate errors are thrown when the data entry is not as per requirements.        | Enter the wrong data and click on save                  | Appropriate errors are thrown                                      | Errors are thrown when fields are empty.         | Pass          | |
| Error scenarios on comparison settings data | Appropriate errors are thrown when the data entry is not as per requirements.        | Enter the wrong data and click on save                  | Appropriate errors are thrown                                      | Errors are thrown when fields are empty.         | Pass          | |
| Error scenarios on compare jobs data        | Appropriate errors are thrown when the data entry is not as per requirements.        | Enter the wrong data and click on save                  | Appropriate errors are thrown                                      | Errors are thrown when fields are empty.         |  Pass         | |
| Cancel button on current job page           | The mainmenu page should be displayed and no data should be saved.                   | Click on Cancel/exit button                             | MainMenu Page should be presented and data should not be saved     | MainMenu Page is presented and data is not saved | Pass      | |
| Cancel button on new job page               | The mainmenu page should be displayed and no data should be saved.                   | Click on Cancel/exit button                             | MainMenu Page should be presented and data should not be saved     | MainMenu Page is presented and data is not saved | Pass      | |
| Cancel button on comparison settings page   | The mainmenu page should be displayed and no data should be saved.                   | Click on Cancel/exit button                             | MainMenu Page should be presented and data should not be saved     | MainMenu Page is presented and data is not saved | Pass      | |
| Cancel button on compare jobs page          | The mainmenu page should be displayed and no data should be saved.                   | Click on Cancel/exit button                             | MainMenu Page should be presented and data should not be saved     | MainMenu Page is presented and data is not saved | Pass      | |
| Job offers listing                          | Jobs should be sorted and presented as per the ranking score.                        | Select 2 jobs and click on compare.                     | Jobs should be presented in the right order.                       | Jobs are in correct order                        |  Pass         | |
| Ranking score verification                  | Check the job score created by app is equal to the expected one(manually calculated) | Include an assert statement in the code to verify this. | The assert statement passes the check and code is run succesfully. | Running successfully end to end                  |  Pass         | |
| No weights assigned                         | Include a scenario where no weights are assigned and equal weightage is taken.       | Include an assert statement in the code to verify this. | The assert statement passes the check and code is run succesfully. | Running successfully end to end                  |  Pass         | |
| Only 2 jobs Selection                       | Verify that the app only allows selecting 2 jobs no more or less                     | Select 2 checkboxes against the list of jobs.           | System allows only 2 selection, not 1 or 3.                        | Only 2 selections are allowed                    |  Pass         | |
| DB checks for current job module            | Tests to check if the data is saved successfully in DB                               | Click on save and retrieve the data from DB             | Retrieved data is as expected.                                     | Data is retrieved as expected                    |  Pass         | |
| DB checks for new job module                | Tests to check if the data is saved successfully in DB                               | Click on save and retrieve the data from DB             | Retrieved data is as expected.                                     | Data is retrieved as expected                                                 |  Pass         | |
| DB checks for comparison settings module    | Tests to check if the data is saved successfully in DB                               | Click on save and retrieve the data from DB             | Retrieved data is as expected.                                     | Data is retrieved as expected                                                 |  Pass         | |
| DB checks for compare jobs module           | Tests to check if the right jobs are retrieved.                                      | Click on save and retrieve the data from DB             | Retrieved data is as expected.                                     | Data is retrieved as expected                                                 |  Pass         | |



