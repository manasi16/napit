import mongoose from 'mongoose';
import { User,PersonalDetails, SensorReading } from './models/NapItModel';

const Users = [
  {
    username: 'McTesty',
    password: 'REALLYBADPASSWORD'
  },
  {
    username: 'TESTDUDE',
    password: 'ALSOAREALLYBADPASSWORD'
  }
];

// Connect to MongoDB
mongoose.connect('mongodb://localhost/NapIT');

// Go through each movie
Users.map(data => {
  // Initialize a model with movie data
  const NapItUsers = new User(data);
  // and save it into the database
  NapItUsers.save();
});



