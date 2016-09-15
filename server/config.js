const deepAssign = require('deep-assign');

const defaultConf = {
};

const production = {
  server: {
    port: process.env.PORT || 3001
  },
  mongo: {
    uri: process.env.MONGODB_URI,
  },
};

const development = {
  server: {
    port: 3001
  },
  mongo: {
    uri: 'mongodb://localhost/my-smart-fridge',
  },
};

const activeConf = (process.env.NODE_ENV === 'production') ? production : development;

module.exports = deepAssign(defaultConf, activeConf);
