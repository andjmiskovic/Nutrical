const { defineConfig } = require('@vue/cli-service')
module.exports = {
    devServer: {
        port: 3000,
        proxy: {
            '/api': {
                target: 'http://localhost:8085',
                ws: true,
                changeOrigin: true
            }
        }
    },
    pluginOptions: {},
}