import { PersonalDetails , SensorReading, User } from '../models/NapItModel';
//import moment from 'moment';

// Hardcode the days for the sake of simplicity
//const days = [ 'Today', 'Tomorrow', moment().add(2, 'days').format('ddd, MMM D') ];
// Same for the times
//const times = [ '9:00 AM', '11:10 AM', '12:00 PM', '1:50 PM', '4:30 PM', '6:00 PM', '7:10 PM', '9:45 PM' ];

//DEGUG 
var DebugLoginUsername = "";
var DebugPassword = "";

export const getUser = (req, res, next) => {
  // Find all movies and return json response
  User.find().lean().exec((err, UserInfo) => res.json(
    // Iterate through each movie
    { User: UserInfo.map(User => ({
      ...User
    }))}
  ));
};

export const getUserTest = (req, res, next) => {
  // Find all movies and return json response
  console.log(req.body.username);
  console.log(req.body.password)

  // find the user.
  //User.find().lean().exec((err, UserInfo) => res.json(
    // Iterate through each movie
    //{ Login: true }
  //));

  //DEGUG 
  if(req.body.username === DebugLoginUsername)
  {
  	console.log("Correct username");
  	if( req.body.password === DebugPassword)
  	{
  		console.log("Correct Password");
  		res.json({ Login: true });
  	}
  	else 
  	{
  		console.log("WRONG Password");
  		res.json({ Login: false });
  	}
  }
  else
  {
  	console.log("WRONG username");
  	res.json({ Login: false });
  }
  //DEGUG 

};

export const postCreateUser = (req, res, next) => {

	//DEGUG 
	console.log(req.body);
	console.log(req.body.username);
	console.log(req.body.password);

	DebugLoginUsername = req.body.username;
	DebugPassword = req.body.password;

	//DEGUG 
	console.log("Debug username is: " + DebugLoginUsername);
	console.log("Debug password is: " + DebugPassword);

	res.json(

		//account was created successfully 
		{
			AccountConfirmed: true,
			Error: 0 
		}

		// //Account exists 
		// {
		// 	AccountConfirmed: false,
		// 	Error: 1
		// }

		// //General Error
		// {
		// 	AccountConfirmed: false,
		// 	Error: 2
		// }


		);
};

export const postSensorReadings = (req, res, next) => {

	//stroe data in database based on username 
	//DEGUG 
	console.log(req.body.username);
	console.log(req.body.SensorReadings);
	// write to database.
	//Note mongo id has timestamp encoded into it. 
	res.json({Stored: true});


};