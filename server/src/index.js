import express from 'express';
import path from 'path';

import config from '../config';

import recipesController from './recipes/controller';


// Build app
const app = express();

app.use(express.static(`${path.dirname(require.main.filename)}/../../client/build/`));

app.use('/api/recipes', recipesController);

app.listen(config.server.port, function () {
  console.log(`Example app listening on port ${config.server.port}!`);
});
