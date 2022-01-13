import { createApp } from 'vue'
import App from './App.vue'
import './registerServiceWorker'
import router from './router'
import store from './store'
import 'element-plus/dist/index.css'
import ElementPlus from 'element-plus'
import "@/assets/css/commons.css"
//引入字体图标库
import '@/assets/iconfont/iconfont.css'
import zhCn from 'element-plus/es/locale/lang/zh-cn'
import '@/config/axios'
const app = createApp(App).use(store).use(router).use(ElementPlus,{ locale: zhCn }).mount("#app");

