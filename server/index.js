const express = require('express');
const app = express();

app.get('/hello', function (req, res) {
  console.log('Will send response');
  res.send('Hello World!');
});

app.listen(3001, function () {
  console.log('Example app listening on port 3001!');
});
