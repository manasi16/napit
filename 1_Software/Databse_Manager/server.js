import express from 'express';
import morgan from 'morgan';
//import mongoose from 'mongoose';
import router from './router';
import bodyParser from'body-parser';


// Connect to MongoDB
//mongoose.connect('mongodb://localhost/movies');

// Initialize http server
const app = express();

app.use(bodyParser.urlencoded({ extended: true })); // to support URL-encoded bodies
app.use( bodyParser.json() );       // to support JSON-encoded bodies

//app.use(express.json());       // to support JSON-encoded bodies
//app.use(express.urlencoded()); // to support URL-encoded bodies

// Logger that outputs all requests into the console
app.use(morgan('combined'));
// Use v1 as prefix for all API endpoints


app.use('/v1', router);

const server = app.listen(3002, () => {
  const { address, port } = server.address();
  console.log(`Listening at http://${address}:${port}`);
});