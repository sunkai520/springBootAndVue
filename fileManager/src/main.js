import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import "./config/axios"
import 'element-plus/dist/index.css'
import ElementPlus from 'element-plus'
import zhCn from 'element-plus/es/locale/lang/zh-cn'
import "@/common/commons.css"
createApp(App).use(router).use(ElementPlus,{ locale: zhCn }).mount('#app')
