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
 - simplified Storage class (it was too complex) by separating the application 
   and the persistence layers.
   Added UserDao, HospitalDao, and RequiredVaccinesDao classes to handle the tasks,
   related to persistence issues of the respective objects. Also, I indroduced 
   CrudRepository interface, to standartize the methods used by Dao classes.
 
 - added application.properites file to store all application-related properties
   in one place, such as names and locations of the files.
 
 - added RoleEnum class, which lists all user roles, supported by the system. 
   We used to have roles, which were equal to the name of the class
   (Patient or Staff). This link was not necessary and limited our ability to 
   introduce new complex roles (due to Java naming conventions).
   Also, Enum class helps to improve the integrity of the system 
   (otherwise, a typo would not be noticed). This way I eliminated switch statements.

Georgy
 - made sure that proper operands are used in Storage: FetchVaccines function replaced 
   line!="" with !line.equals("")
 
 - deleted unused temp cariable to cleanup the code and elliminate potential error 
   in Credential.java constructor
 
 - Optimized code in Storage by using functional operand in "doesUserExist" function of 
   the Storage (in place of for loop with if statements and two return calls it is down 
   to just one operation of search and return)
       
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

