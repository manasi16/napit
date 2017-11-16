## Table of Contents


## Napit
Code and documentation for Software Engineering Fall 2017 Rutgers

## Demo
See 0_Demo for code used in the Demo on Nov 1st 2017. 

## Install

Install mongodb on your system

#linux - debian flavor

sudo apt install mongo

Install nodejs on your system

sudo apt install node

Unzip the project zip in 
1_Software.zip

Zip is in this directory

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

## Configuration

Change the ServerBackend variable ip address in 1_Software/napit/configuration.js to point to the loction where the backend will be running.

## Run
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

### References 
Other software components and tutorials used in this application
* [LibSVM](https://github.com/yctung/AndroidLibSVM)
* [Database backend tutorial](http://rationalappdev.com/api-backend-with-nodejs-express-and-mongodb-for-react-native-apps/)
* [Database backend code example](https://github.com/rationalappdev/MovieTicketsBackend)
* [Accelorometer](https://docs.expo.io/versions/latest/sdk/accelerometer.html)
* [Networking](https://facebook.github.io/react-native/docs/network.html)
* [Navigation github](https://github.com/react-community/react-navigation)
* [Navigation Tutorial](https://reactnavigation.org/docs/intro/)
* [Restful API Post request](https://www.tutorialspoint.com/react_native/react_native_text_input.htm)
* [Restful API Overview](https://facebook.github.io/react-native/docs/network.html)
