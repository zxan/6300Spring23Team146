# Design Description

1. When the app is started, the user is presented with the main menu, which allows the user to (1) enter or edit current job details, (2) enter job offers, (3) adjust the comparison settings, or (4) compare job offers (disabled if no job offers were entered yet).   

A: To realize this requirement, I introduced the JobCompareService class as the entry point to the application.  
Then I added displayMenu method to show the choices to the user.  
After that, I added another method enterChoice to help the user to enter the input step by step for intuitive and convenience.  

2. When choosing to enter current job details, a user will:
   * Be shown a user interface to enter (if it is the first time) or edit all the details of their current job, which consist of:
     * Title
     * Company
     * Location (entered as city and state)
     * Cost of living in the location (expressed as an index)
     * Yearly salary
     * Yearly bonus
     * Restricted Stock Unit Award (expressed as a lump sum vested over 4 years)
     * Relocation stipend (A single value from $0 to $25,000)
     * Personal Choice Holidays (A single overall number of days from 0 to 20)
   * Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.  
   
A: To realize this requirement, I created a new class Job and added currentJob attribute to the JobCompareService.  
So when user choose to enter of edit current job details, the system will first check if currentJob attribute is null.  
If currentJob is null, then enter a new one by calling the method createNewJob(call the construciton method in Job class) and setCurrentJob.
Else if currentJob is not null, then I added editJob method to the Job class to resolve this.
Besides, the createNewJob method can be reused in the next step for normal other offers.

3. When choosing to enter job offers, a user will:
   * Be shown a user interface to enter all the details of the offer, which are the same ones listed above for the current job.
   * Be able to either save the job offer details or cancel.
   * Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer (if they saved it) with the current job details (if present).

A: The enter new job offer requirement can be realized by reusing the createNewJob method in last step.  
After that, I added a new method compareWithAnotherJob in the Job class.  
To compare the offer with current job, the system will pass the currentJob as a parameter, and it will return the compare result.

4. When adjusting the comparison settings, the user can assign integer weights to:
   * Yearly salary
   * Yearly bonus
   * Restricted Stock Unit Award
   * Relocation stipend
   * Personal Choice Holidays  
If no weights are assigned, all factors are considered equal.

A: To realize this requirement, I introduced a new class Weight to store the weight information.  
All parameters within this class will be static, and could be used by all job instances.  
And it will have default 1 value and ready to be used once start the system.  
The user could use set method to modify the private parameters.

5. When choosing to compare job offers, a user will:
   * Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.
   * Select two jobs to compare and trigger the comparison.
   * Be shown a table comparing the two jobs, displaying, for each job:
     * Title
     * Company
     * Location
     * Yearly salary adjusted for cost of living
     * Yearly bonus adjusted for cost of living
     * Restricted Stock Unit Award
     * Relocation stipend
     * Personal Choice Holidays
   * Be offered to perform another comparison or go back to the main menu.

A: To realize this requirement, I introduced a score attribute and getScore method in the Job class using the static Weight info.  
When creating a new job instance, it will calculate a score based on the current Weight info and save it.
And once the changeWeight, the updateScore method will be called to update the score for all jobs.
In the JobCompareService entry point, the displayJobsByRank method will sort the jobs based on the score.  

6. When ranking jobs, a job’s score is computed as the weighted sum of:  
AYS + AYB + (RSUA / 4) + RELO + (PCH * AYS / 260)  
where:
   * AYS = yearly salary adjusted for cost of living
   * AYB = yearly bonus adjusted for cost of living
   * RSU = restricted stock unit award
   * RELO = relocation stipend
   * PCH = personal choice holidays  
     For example, if the weights are 2 for the yearly salary, 2 for relocation stipend and 1 for all other factors, the score would be computed as:  
   2/7 * AYS + 1/7 * AYB + 1/7 * (RSUA / 4) + 2/7 * RELO + 1/7 * (PCH * AYS / 260)

A: This score can be calculated by introducing the getScore method in the Job class.  
The using the static Weight parameter, the method could calcuate an up-to-date score for a job instance.
The score will be calculated when a job instance is created, and when the weight is changed, to make sure updated.

7. The user interface must be intuitive and responsive.

A: To realize this requirement, the displayMenu and enterChoice method will be well designed.  
The main idea is to provide enough notes to help the user to enter the input one by one, instead of all at once, which will be more user friendly.
Besides, it will examine the format, and once examine failed, it will give the user another chance to enter a new input.  
For example, once a number is required, but receive a String, the system will remind the user to enter a number again.

8. For simplicity, you may assume there is a single system running the app (no communication or saving between devices is necessary).

A: Currently, no user and authentication/authorization features introduced. But the system is open to the extension if needed later.
