import express from 'express';
import path from 'path';
import mongodb from 'mongodb';

import config from '../config';

// Build app
const app = express();

app.use(express.static(`${path.dirname(require.main.filename)}/../../client/build/`));

app.get('/put-data', function (req, res) {

  // Build mongo connection
  mongodb.MongoClient.connect(config.mongo.uri, (err, db) => {
    if(err !== null) {
      console.error(`Could not connect to mongodb : ${err}`);
      process.exit(1);
    }

    console.log('Connected successfully to mongodb.');

    db.collection('documents').insertMany([
      {a : 1}, {a : 2}, {a : 3}
    ], function(err, result) {
      if(err === null) {
        console.log("Inserted 3 documents into the collection");
        res.send(JSON.stringify(result));
      }
      else {
        console.log(err);
        res.send(JSON.stringify(err));
      }

      db.close();
    });
  });
});

app.get('/get-data', function (req, res) {

  // Build mongo connection
  mongodb.MongoClient.connect(config.mongo.uri, (err, db) => {
    if(err !== null) {
      console.error(`Could not connect to mongodb : ${err}`);
      process.exit(1);
    }

    console.log('Connected successfully to mongodb.');

    db.collection('documents').find({}).toArray(function(err, docs) {
      if(err !== null) {
        res.send(JSON.stringify(err));
      }
      else {
        console.log("Found records");
        res.send(JSON.stringify(docs));
      }
    });
  });
});

app.get('/hello', (req, res) => {
  res.send(JSON.stringify(config));
});

app.listen(config.server.port, function () {
  console.log(`Example app listening on port ${config.server.port}!`);
});
