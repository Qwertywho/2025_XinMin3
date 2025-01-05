const webpack = require("webpack");
const NodePolyfillPlugin = require("node-polyfill-webpack-plugin");
const HtmlWebpackPlugin = require("html-webpack-plugin");

module.exports = {
  plugins: [
    new HtmlWebpackPlugin({ template: "./public/index.html" }),
    new webpack.HotModuleReplacementPlugin(),
  ],
};

module.exports = function override(config, env) {
  // Add polyfills for Node.js core modules
  config.resolve.fallback = {
    url: require.resolve("url/"),
    http: require.resolve("stream-http"),
    https: require.resolve("https-browserify"),
    stream: require.resolve("stream-browserify"),
    // Add other polyfills as needed
  };

  // Add the NodePolyfillPlugin
  config.plugins.push(new NodePolyfillPlugin());

  return config;
};

module.exports = function override(config) {
  config.resolve.fallback = {
    buffer: require.resolve("buffer/"),
    http: require.resolve("stream-http"),
    https: require.resolve("https-browserify"),
    // Add other modules as needed
  };

  // Optionally add plugins if needed
  config.plugins.push(
    new webpack.ProvidePlugin({
      Buffer: ["buffer", "Buffer"],
    })
  );

  return config;
};

// module.exports = {
//   webpack: {
//     configure: (webpackConfig) => {
//       const scopePluginIndex = webpackConfig.resolve.plugins.findIndex(
//         ({ constructor }) =>
//           constructor && constructor.name === "ModuleScopePlugin"
//       );

//       webpackConfig.resolve.plugins.splice(scopePluginIndex, 1);
//       return webpackConfig;
//     },
//   },
// };
