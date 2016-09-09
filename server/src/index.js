import express from 'express';
import path from 'path';

const app = express();

app.use(express.static(`${path.dirname(require.main.filename)}/../../client/build/`));

app.get('/hello', function (req, res) {
  console.log('Will send response');
  res.send('Hello World!');
});

app.listen(process.env.PORT || 3001, function () {
  console.log(`Example app listening on port ${process.env.PORT || 3001}!`);
});
