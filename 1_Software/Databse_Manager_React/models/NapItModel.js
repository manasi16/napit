import mongoose, { Schema } from 'mongoose';

// Define movie schema

var PersonalDetailsSchema = new Schema({
  Age: String,
  Gender: Boolean, // 0 male , 1 female
  Height: Number, //Meters 
  Weight: Number, //Kilograms 
  Location: String,
});

var SensorReadingSchema= new Schema({
  X: Number,
  Y: Number,
  Z: Number,
  T: Date
});


var UserSchema = new Schema({
  username: {
    type: String,
    unique: true,
  },
  password: String,
  //personaldetails: [PersonalDetailsSchema],
  //Sensor: [SensorReadingSchema]
});

// Export Mongoose model
//export default mongoose.model('movie', movieSchema);
var PersonalDetails = mongoose.model('PersonalDetails',PersonalDetailsSchema );
var SensorReading = mongoose.model('SensorReading',SensorReadingSchema);
var User = mongoose.model('User',UserSchema);

export { PersonalDetails , SensorReading, User };