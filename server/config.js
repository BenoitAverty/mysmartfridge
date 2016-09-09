const deepAssign = require('deep-assign');

const defaultConf = {
};

const production = {
  mongo: {
    uri: process.env.MONGODB_URI,
  },
};

const development = {
  mongo: {
    uri: 'mongodb://localhost/my-smart-fridge',
  },
};

const activeConf = (process.env.NODE_ENV === 'production') ? production : development;

module.exports = deepAssign(defaultConf, activeConf);
