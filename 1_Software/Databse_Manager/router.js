import express, { Router } from 'express';
// Import index action from movies controller
import { index } from './controllers/movies';

// Initialize the router
const router = Router();

// Handle /movies.json route with index action from movies controller
router.route('/movies.json')
  .get(index);

  //router.route('/AddUser').get();

export default router;