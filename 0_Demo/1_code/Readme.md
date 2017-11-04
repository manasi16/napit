*Code used in demo on Nov 1st 2017

*Install

Install mongodb on your system

#linux - debian flavor

sudo apt install mongo

Install nodejs on your system

sudo apt install node


Unzip the project 7z compressed archive in 
See: [7 Zip] (http://www.7-zip.org/)
1_Software.7z.001
1_Software.7z.002

7z archive is in this directory

Install node packages for backend

Navigate to 1_Software/Databse_Manager
Execute: 
npm install


Install node packages for frontend

Navigate to 1_Software/napit
Execute: 
npm install

Install the Expo app on your phone
Connect your phone to the same network that the backend will be running on.

*Configuration

Change the ServerBackend variable ip address in 1_Software/napit/configuration.js to point to the loction where the backend will be running.

*Run

Start backend 
Note Mongo must be running on localhost

Navigate to 1_Software/Databse_Manager
Execute: 
npm start

Start Frontend
Navigate to 1_Software/napit
Execute: 
npm start

In the Expo app scan the 2D bar code that appears in your terminal







