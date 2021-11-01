module.exports = {
  runtimeCompiler: true,
  productionSourceMap: false,
  publicPath: process.env.NODE_ENV === "production" ? "./" : "/",
  devServer: {
    proxy: {
      '^/api': {
        target: 'http://localhost:3080',
        changeOrigin: true
      },
    }
  }
};
