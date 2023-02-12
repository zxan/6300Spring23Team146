1. When the app is started, the user is presented with the main menu, which allows the user to (1) enter or edit current job details, (2) enter job offers, (3) adjust the comparison settings, or 
(4) compare job offers (disabled if no job offers were entered yet). 

This requirement will be completed by the gui. The application will require a user login.After logging in, the user will be brought to a gui displaying the options. Under each option, will consist a functionality to navigate the user to the correct gui. 

2. When choosing to enter current job details, a user will:Be shown a user interface to enter (if it is the first time) or edit all the details of their current job, which consist of:

To complete this requirement, the application will run a getter function to retrieve the job details if the user has an existing job. If the user is new, then the function create_job will accept the 
entries from the gui as parameters and save the information into a hashmap based on the username. For editting, it is the same process. User will input changes and the function edit_job will take 
the parameters and update the values in the hashmap with the associated username key. All values that are null from the user input will be handled by the function

3.When choosing to enter job offers, a user will:
A. Be shown a user interface to enter all the details of the offer, which are the same ones listed above for the current job.
B. Be able to either save the job offer details or cancel.
C. Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer (if they saved it) with the current job details (if present).

To satisfy this requirement, we will use the same concept as create job from the previous question, but the funnction will be create_job in the Job_offers class. It will take the user inputs as parameters and function will execute when user 
decides to click save.Upon saving and cancelling, the user input will be cleared from the input boxes.If the compare job options is selected, then the function that will be called is display_offers. 

4.When choosing to compare job offers, a user will:
A. Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.
B. Select two jobs to compare and trigger the comparison.
C. Be shown a table comparing the two jobs, displaying, for each job:
	Title
	Company
	Location 
	Yearly salary adjusted for cost of living
	Yearly bonus adjusted for cost of living
	Restricted Stock Unit Award 
	Relocation stipend 
Personal Choice Holidays 
D. Be offered to perform another comparison or go back to the main menu.

To capture the 4 requirement when displaying offers, we use the class compare_jobs. The function display_offers will display the current job and offers ranked by the weighted sum calculations. To achieve this the display_offers will call the calc_job_score 
function to return the weighted average. The weighted average, title, and company will be strored in an interior arrayList as string. From there, the jobs will ranked  based on the weight returned and stored  in a tree map based on rankings. The key being 
numeric integer value greater than 1, and the value being the company and title. the function will return the tree map as the data to display to the front end. 

To capture the compare requirement, the app captures the user selection and uses that information to pass it into compare. The values will be used inside of compare jobs to find the jobs information based off of the title and a getter for job offers.
The function will return a list with each job as a seperate entry. 

6.When ranking jobs, a job’s score is computed as the weighted sum of:

AYS + AYB + (RSUA / 4) + RELO + (PCH * AYS / 260)

Requirement six is solved by the calc_job_score. The user will have the ability on the gui to input the integer weights for each category listed in the calculation. If not number is entered, all values default to 1. The user input or one is passed into 
calc_job_score has parameters. The function returns a decimal for the calucalted value. This value is used in the displa_offers to sort jobs.