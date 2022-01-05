import { createApp } from 'vue'
import App from './App.vue'
import './registerServiceWorker'
import router from './router'
import store from './store'
import 'element-plus/dist/index.css'
import ElementPlus from 'element-plus'
import "@/assets/css/commons.css"
import '@/assets/iconfont/iconfont.css'
import zhCn from 'element-plus/es/locale/lang/zh-cn'
import Highlight from "@/highJs/index.js"
import '@/config/axios'
const app = createApp(App).use(store).use(router).use(ElementPlus,{ locale: zhCn }).use(Highlight).mount("#app");

