import express from 'express';

import { getRecipes } from './application';

const controller = express.Router();

controller.get('/', (req, res) => {
  res.send(getRecipes());
});

export default controller;
