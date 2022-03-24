import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import Home from "@/views/home/index.vue"
const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    component: Home,
    children: [
      {
        path: '/',
        name: 'fileManager',
        component: () => import('@/views/fileManager/index.vue')
      },
    ]
  },
  {
    path: '/login',
    name:'login',
    component: ()=>import("@/views/login/index.vue"),
   
  },
]
// console.log(import.meta.env.VITE_API_URL)
const router = createRouter({
  history: createWebHistory(),
  routes
})
export default router
