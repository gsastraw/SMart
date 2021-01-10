# SMart: A simple way to manage your projects!

Welcome to the SMart project management tool! The goal of SMart is to allow you to be able to manage your projects in a clear, simple, and efficient way. The program features tools such as time tracking, built in issue management, customizable user profiles, and administrative tools for simple management of your user base.

A team of Group 3 programmers worked on the current version of the program for a little above two months.

# Install 

Installing SMart is very simple. Clone or pull the project from the repository, and run the program from your preferred IDE under **se/gu/smart/Main**.   If you would like to run the program outside of an IDE, you can use Maven to run the program from the terminal:

```
./mvnw clean javafx:run
```

# Accounts

Accounts are used to access projects that the account is a member of, access and edit timesheets, create, delete and mark issues as complete. Your account is your own, and should not be shared with somebody else.

## Creating Your Personalized Account

If you would like to create your own account, you will have to log in as an admin. The admin details are:
    
    Username: admin
    Password: pass

Once in the admin panel, clicck "Create User" and type in a username, full name, and password of your choice. If you would like to create your own admin account, simply click on "Admin User" and make sure it is checked. 

Your account is now created, and you can now log in. Log out of the admin panel when everything is to your liking.

## Logging In

In order to log in, simply type in your username, and your password. The accounts from the development team are listed below if you would like to access preloaded user data during the timespan of the development of this project: 

    Username: edvin
    Password: pass

    Username: eyass
    Password: pass

    Username: gregory
    Password: pass

    Username: kevin
    Password: pass

    Username: klara
    Password: pass

    Username: mislav
    Password: pass

    Username: vlad
    Password: pass


## Deleting accounts

To delete an account, log in as an admin. Click on manage users, and left click on the user you want to delete. Afterwards, click on delete user.


# Projects

SMart tracks the development progress of your project through organizing the development process and the issues involved within the development of the project.

## Creating a project

To create a project, log in as a user. Click on the "New Project" button on the bottom right of the dashboard screen. Fill in the project name, description, start date, deadline. To add members to the project, left click on a user within the left-most column and click on the "+ Add Member" button under the column. The users included within the project are displayed in the right-most column. Click on the "Done" button to confirm your settings. 

## Editing a project

To edit a project, make sure you log in as the owner of the project. Non-owners cannot edit projects. Left click on the project on the user dashboard and click on the "View Project" button. Click the "Edit Project" button. Change the values you wish to change and click on "Done" to save your changes.

## Viewing your profile

To view your profile, simply click on "View Profile" in the left-most sidebar.

## Editing your profile

To edit your profile, click on "View Profile" in the sidebar. Afterwards, click the `Edit` button on the bottom right. Fill in your desired display name and bio, and click the "Done" button.

## Viewing other user's profiles as a user

# Issues 

Issues are development milestones such as features, bug fixes or bureaucratic tasks.

## Viewing issues

Issues can be accessed by going to the user dashboard and clicking on the desired project. After clicking "View Project", click the "Issues" button for a list of all the issues within the project.

## Creating issues

All members within a project can create an issue. To create an issue, left click on a project in the user dashboard and click the "View Project" button. Click the "Issues" button towards the bottom. Afterwards, click on the "Create" button. Fill in the issue number, type, name and description. Click the "Done" button.

## Editing issues

All members within a project can edit an issue. To create an issue, left click on a project in the user dashboard and click the "View Project" button. Click the "Issues" button towards the bottom. Double click on the element of the issue you want to change, and type in the new text and press the enter key. 

## Deleting issues

All members within a project can delete an issue, even if they are not the one who created it. To delete an issue, left click on a project in the user dashboard and click the "View Project" button. Click the "Issues" button towards the bottom. Left click on the issue you want to delete, and click the delete button. 

## Declaring issues as complete

To declare an issue as complete, navigate to the issues menu.

## Declaring issues as incomplete

# Tickets

Tickets are reports sent to the admin, such as bug fixes within the software itself or account issues.

## Submitting a ticket to an admin

# Timesheets

Timesheets are used to moderate and manage the schedule of developers. Your timesheet is personal to you. A seperate timesheet exists for each and every project.

## Viewing your timesheet

To view your timesheet within a certain project, log in as a user. Afterwards, click the "Timesheet" button in the sidebar. A list with 

## Adding a timesheet entry

To add an entry into the timesheet, navigate to the timesheet of a project and click the "Check in" button. When done with your work, press the "Check out" button. To add notes to the entry, write down the note in the text box next to the "Check out" button and click the "Add" button before clicking the "Check out" button.

# Admin Capabilties

## Viewing other users' profiles as an Admin

To view a user's profile as an admin, log in as an admin. The landing page for admins is the manage users page, which is where you would navigate normally to view a user's profile. Left click on a user within the list, and click on the "View User" button on the bottom left.

## Editing other users' profiles as an Admin

After viewing a user's profile, click the "Edit" button on the bottom right. Fill in the changes to the display name and/or bio, and click the "Done" button.

## Reviewing reports as an admin

Log in as an admin and click the "Reports" button to the left-most sidebar. After resolving the report, left click the report in the list and click the "Switch Status" button. The status column shall display a "Resolved" in the cell corresponding to the report.

## Reseting the system

Reseting the system involves deleting everything in storage. To reset the system, log in as an admin and click on the "Reset System" button on the left-most sidebar. Click the large "RESET SYSTEM" button. A text box will appear. Type "CONFIRM" using capital letters and click the "RESET SYSTEM" button again to confirm. 