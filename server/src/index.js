import express from 'express';
import path from 'path';
import mongoose from 'mongoose';

import config from '../config';

mongoose.connect(config.mongo.uri);
const db = mongoose.connection;
db.on('error', console.error.bind(console, 'connection error:'));
db.once('open', function() {
  console.log("connected !");
});

const app = express();

app.use(express.static(`${path.dirname(require.main.filename)}/../../client/build/`));

app.get('/hello', function (req, res) {
  res.send(JSON.stringify(config));
});

app.listen(process.env.PORT || 3001, function () {
  console.log(`Example app listening on port ${process.env.PORT || 3001}!`);
});
