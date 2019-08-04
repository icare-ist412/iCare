Source code for iCare project

Note: For the purposes of building the software, use case 1 and 3 
were swapped from the original design timeline.


---------- This Submission's Details: ---------------------------

This submission is for Use Case 2: View Immunization History. 

There are two views: Staff and Patient (login info below) both 
offering separate experiences for the user, with Staff having
more functionality. 

The "View Medical Record"/"My Medical Record", "Schedule Appointment",
and "View Appointments"/"My Appointments" buttons are 
all disabled purposely while under development for the next
deliverable.

The following refactors were implemented as per last week's
refactoring plan:

David
 - Simplified new user input form validation in AddUserViewController.
    More specifically, in the validateUserInput() method.
 - Simplified authentication method in LoginViewController.
    More specifically, in the performAuth() method.
        
Jake
 - Modified the level of access to member fields of Treatment, Hospital, and Address classes.  
   Member fields were changed from the default "package / protected" State to "private".
 - Broke up long ActionEvent methods into smaller, more accessible, chunks that can be re-used
   to manipulate the data similarly for other ActionEvents.

Dmitry
 -
 
 -
 
 -

Georgy
 - made sure that proper operands are used in Storage: FetchVaccines function replaced line!="" with !line.equals("")
 
 - deleted unused temp cariable to cleanup the code and elliminate potential error in Credential.java constructor
 
 - Optimized code in Storage by using functional operand in "doesUserExist" function of the Storage (in place of for loop with if statements and two return calls it is down to just one operation of search and return)
        
        
        
        
        

-----------------------------------------------------------------


---------- Submission History: ----------------------------------
07/14/19: Use case 3 - Hospital reception desk adding patients
08/04/19: Use case 2 - View Immunization History
-----------------------------------------------------------------


---------- Backlog: ---------------------------------------------
: Use case 1 - View and edit a patient’s medical records
: Use case 4 - Manage an appointment
-----------------------------------------------------------------


---------- Logins for testing -----------------------------------
Role: Patient
User ID: gatesst
Password: password

Role: Staff
User ID: jobsbi
Password: pass123
-----------------------------------------------------------------




