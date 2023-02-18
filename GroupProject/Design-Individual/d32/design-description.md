## Design Description

### Main Menu
* When user launches the app, they are shown with the main Menu page. 
* The contents of the page are part of UI implementation.
* This page will have 4 buttons redirecting the app to corresponding page when clicked.
* 4 options will be:
  * To get current job details. Enter or edit based on if it is the first time or not. There will be a boolean variable to keep track of it.
  * To enter new job offer details. The user can enter the new job details and it will be saved as a list of jobs.
  * To enter the comparison weights. The user will be able to edit/enter the weights and the score will be calculated accordingly.
  * To compare job offers. This will enable the user to select 2 jobs and compare them.

### Current Job Details
* This page will use the jobOffer class to get all the required values.
* There will be a boolean value to keep track of current job details to fetch.
* This page will have editable fields with the option of saving or cancelling, which would mean returning to the main page.

### Enter Job Details
* This page will use the jobOffer class again to get all the required values.
* There will be a boolean value to keep track of current job details to fetch.
* This page will have editable fields with the option of saving or cancelling, which would mean returning to the main page.
* Clicking on saving would mean that the new job offer will be added as a list along with other jobs.

### Weights Settings
* This page will use the comparisonSettings class again to get all the required values.
* The user will have the option of set the integer weights to all the values. 
* If no values are assigned, equal weightage will be assumed.
* This page will have editable fields with the option of saving or cancelling, which would mean returning to the main page.
* Clicking on saving would mean that weights are saved.

### Compare Jobs
* This page will use the job score calculator to get the scores against each job.
* The list of all the jobs will be shown to the user sorted by the score.
* There will be checkboxes to select 2 jobs to compare.
* Clicking on compareJobs would mean displaying both the jobs side by side in a tabular format.
* Clicking on new comparison will bring back the compare jobs page.
* Exiting would mean going back to the main page.

### Ranking Jobs
* The score is calculated as per the definition using the below equation:
  `AYS + AYB + (RSUA / 4) + RELO + (PCH * AYS / 260)`

*Note* : For simplicity it has been assumed that a single user is using the app at one time.
