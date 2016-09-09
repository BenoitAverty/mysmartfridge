const child_process = require('child_process');

if (process.env.NODE_ENV === 'production') {
  child_process.fork('./server/build/index.js');
}
else {
  const server = child_process.spawn("npm", ["start"], { cwd: './server', stdio: 'inherit' });
  const client = child_process.spawn("npm", ["start"], { cwd: './client', stdio: 'inherit' });
}
