import express from 'express';
import path from 'path';

import config from '../config';

const app = express();

app.use(express.static(`${path.dirname(require.main.filename)}/../../client/build/`));

app.get('/hello', function (req, res) {
  res.send(`${config.greeting}${config.test}`);
});

app.listen(process.env.PORT || 3001, function () {
  console.log(`Example app listening on port ${process.env.PORT || 3001}!`);
});
