module.exports = {
    devServer: {
        proxy: {
            '/api': {
                // http://10.0.6.152:8006
                target: process.env.VUE_APP_BASE_URL,
                changeOrigin: true,
                logLevel: 'debug', //查看请求地址
                xfwd: false,
                pathRewrite: {
                    '^/api': ''
                },
            }
        }
    },
    // css: {
    //     loaderOptions: {
    //         scss: {
    //             prependData: `@import "@/commons/public.scss";`
    //         },
    //     }
    // }

};