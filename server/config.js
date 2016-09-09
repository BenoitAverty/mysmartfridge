const common = {
  greeting: "Hello, ",
}

const production = {
  test: 'prod',
};

const development = {
  test: 'dev',
};

const activeConf = (process.env.NODE_ENV === 'production') ? production : development;
module.exports = Object.assign({}, common, activeConf);
