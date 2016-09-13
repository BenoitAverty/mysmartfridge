import mongodb from 'mongodb';

import config from '../config';

const dbPromise = new Promise((resolve, reject) => {
  // Build mongo connection
  mongodb.MongoClient.connect(config.mongo.uri, (err, db) => {
    if(err !== null) {
      reject(err);
    }

    resolve(db);
  });
});

export default function getDb() {
  return dbPromise;
}
