import express, { Router } from 'express';
// Import index action from movies controller
import { index } from './controllers/movies';
import { getUser } from './controllers/NapItController';

// Initialize the router
const router = Router();

// Handle /movies.json route with index action from movies controller
//router.route('/movies.json')
  //.get(index);
router.get('/movies',index);
//Account actions
router.get('/User', getUser);

router.post('/User', function (req, res) 
{
	res.send('Post request /USER')
});

router.get('/Sensor', function (req, res) 
{
	res.send('GET request /Sensor')
});

router.post('/Sensor', function (req, res) 
{
	res.send('POST request /Sensor')
});
// Sensors
//router.route('/SensorReadings').post();


export default router;