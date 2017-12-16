## Table of Contents
* [Install](#Install)
* [Run](#Run)
* [Run Tests](#Run_Tests)
* [Important Classes](#Important_Classes)
* [References](#References)

## Napit
Code and documentation for Software Engineering Fall 2017 Rutgers

## Demo
See 0_Demo for code used in the Demo on Nov 1st 2017. 
See 1_Software/napit for code used in demo 2 Final

## Install
Requires Android studio, Android SDK, Android NDK

clone repo https://github.com/napIT-Rutgers-SE-Fall-2017/napit.git
In android studio navigate to 1_Software/napit

	open project

In build menu select 

	make project


## Run
To run the app on hardware see 
[https://developer.android.com/studio/run/device.html](https://developer.android.com/studio/run/device.html)

If everything is setup properly 
under Run menu -> 

	Run 'java in app'

No runnable file can be included due to the many differences between Android versions and hardware. Please compile for your device.
No input parameters are required to start the application

On first run a new user will need to be created. Select Register at the bottom of the screen and create a new account.

Example user account:
	User Test1
	Email Test1
	Password Test1

Known issue: App may need to be restarted after creating a new account since database may not exist.

## Run_Tests 
Unit testing and integration testing was done in Android studio. 

	Unit test are located in the source code at napit/app/src/test/java/.
	Integration test or "instrumented tests" are located in the source code at napit/app/src/androidTest/java/.
	Unit testing is done within Android Studio 
	Integration Testing will require an Android device. 

See Run tests at [https://developer.android.com/studio/test/index.html#run_a_test](https://developer.android.com/studio/test/index.html#run_a_test)

Further testing was done manually by interacting with the app and employing AndroidDatabaseManager to examine the database during runtime


### Important_Classes

AndroidDatabaseManager

	A debugging tool used for looking at the contents of the sqllite database while running on the phone 

BackgroundEventMonitor

	Main background service that controls the StepCounter and Sleep Monitor.
	Triggers the step counter to reset and store values at 12am 

ContactUs

	Displays contact information

DashboardActivity

	The main point of navigation in the application. Only available after the user logs in. Launches the BackgroundEventMonitor

DisplayReading

	Displays the results of sleep analysis 

EditPersonalDetails

	Allows a logged in user to change their personal details

Exercise
	
	Displays information about the user's exercise habits. 

FAQ

	A screen to show answers to some frequently asked questions

MainActivity

	The main screen the user is presented with prior to logging in. 

PersonalDetails

	Displays the user's personal details as stored in the database

RegisterActivity

	Allows a new user to register a new account with the app

Session

	Class to store session information such as current logged in user, email, etc 

ShowMore

	Displays more information to the user

SleepActivity

	Displays information about the user's sleep 

SleepMonitor

	Service that records sensor readings during sleep and stores them in a database.

SQLiteHelper

	Class to handle interactions with the database

StepCounter

	Service that records steps via the accelerometer and stores the values in the database.  

Suggestions

	Gives the user feedback to correct issues found during analysis  

SVM

	Service that trains and runs the Support Vector Machine (SVM) to analyze the user's sleep.

ViewExercise

	View exercise details 

ViewList

	Support class to format data displayed to the screen

ViewProfile

	View the user's profile 


### References 
Other software components and tutorials used in this application