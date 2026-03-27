const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    // Ensure websocket uses secure wss when the page is loaded over HTTPS
    client: {
      webSocketURL: {
        protocol: 'wss',
        hostname: '0.0.0.0',
        port: 8082,
        pathname: '/ws'
      }
    }
  }
})
