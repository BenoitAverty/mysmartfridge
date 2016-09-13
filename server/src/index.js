import express from 'express';
import path from 'path';

import config from '../config';

import getDb from './mongodb-connection'

// Build app
const app = express();

app.use(express.static(`${path.dirname(require.main.filename)}/../../client/build/`));

app.get('/put-data', function (req, res) {
  getDb().then(db => {
    db.collection('documents').insertMany([
        { a: new Date().getTime() }, { b: new Date().getTime() }
      ],
      (err, docs) => {
        if(err !== null) {
          res.send(JSON.stringify(err));
        }
        else {
          res.send(JSON.stringify(docs));
        }
      }
    )
  });
});

app.get('/get-data', function (req, res) {
  getDb().then(db => {
    db.collection('documents').find({}).toArray(function(err, docs) {
      if(err !== null) {
        res.send(JSON.stringify(err));
      }
      else {
        console.log("Found records");
        res.send(JSON.stringify(docs));
      }
    });
  })
  .catch((err) => {
    res.send(JSON.stringify(err));
  });
});

app.listen(config.server.port, function () {
  console.log(`Example app listening on port ${config.server.port}!`);
});
