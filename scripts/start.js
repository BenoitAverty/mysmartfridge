const child_process = require('child_process');
const mongodb_prebuilt = require('mongodb-prebuilt');
const mkdirp = require('mkdirp');

if (process.env.NODE_ENV === 'production') {
  child_process.fork('./server/build/index.js');
}
else {
  // start mongo
  const dbpath = './mongo_data/db';
  mkdirp(dbpath, (err) => {
    if (err) {
        console.error('DB dir creation failed:', err);
        process.exit(1);
    }

    mongodb_prebuilt.start_server(
      {
        auto_shutdown: true,
        args: {
          dbpath,
        },
      },
      function(err) {
        if (err) {
            console.error('mongod didnt start:', err);
            process.exit(1);
        } else {
            console.log('mongod is started');
        }
      }
    );
  });

  // npm start in both modules
  const server = child_process.spawn("npm", ["start"], { cwd: './server', stdio: 'inherit' });
  const client = child_process.spawn("npm", ["start"], { cwd: './client', stdio: 'inherit' });
}
