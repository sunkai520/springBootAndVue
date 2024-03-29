import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import Home from '../views/home/index.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'home',
    component: Home,
    children: [
      {
        path: '/',
        name: 'imsList',
        component: () => import('@/views/imsList/index.vue'),
        meta:{
          keepAlive:true
        }
      },
      {
        path: "/detail",
        name: "detail",
        component: () => import('@/views/detail/index.vue')
      },

      {
        path: '/myCurriculum',
        name: 'myCurriculum',
        component: () => import('@/views/myCurriculum/index.vue')
      },
      {
        path: '/aboutMe',
        name: 'aboutMe',
        component: () => import('@/views/aboutMe/index.vue')
      }
    ]
  },
  {
    path: '/echarts',
    name: 'echarts',
    component:() => import("@/views/demos/echarts/index.vue")
  },
  {
    path: '/others',
    name: 'others',
    component:() => import("@/views/demos/other/index.vue")
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})
// router.beforeEach((to, from, next) => {
//   store.commit("SET_ROUTERURL", to.name)
//   if (to.query.access_token && to.query.app_token) {
//       // 零信任登录回来
//       const data = {
//           access_token: to.query.access_token,
//           app_token: to.query.app_token
//       }
//       verifyToken(data)
//           .then(res => {
//               localStorage.setItem("token", to.query.access_token as any);
//               localStorage.setItem(
//                   "userInfo",
//                   JSON.stringify(res.data)
//               );
//               store.commit("SET_USERINFO", res.data);
//               const menus = getAuthPower(
//                   res.data.auth_menu,
//                   "front"
//               );
//               store.commit("SET_MENUS", menus);
//               const afterMenus = getAuthPower(
//                   res.data.auth_menu,
//                   "after"
//               );
//               store.commit("SET_AFTERMENUS", afterMenus);
//               if (menus.length == 0) {
//                   ElMessage({
//                       type: "warning",
//                       message: `此账号没有任何权限!`,
//                   });
//                   return;
//               }
//               next()
//           })
//           .catch(() => {
//               ElMessage.warning('用户令牌格式错误')
//               localStorage.removeItem('token');
//               localStorage.removeItem("userInfo");
//               store.commit("SET_USERINFO", {});
//               window.open(`${process.env.VUE_APP_ZERO_TRUST_URL}/enduser/sp/sso/app_20210521095609501868`, '_self')
//           })
//   }
//   if (to.path== "/login") {
//       next()
//   }
//   const userInfo= JSON.parse(localStorage.getItem("userInfo") as any);


//   if (to.path != "/login") {
//       const bool = validateToken();
//       if (!bool || !userInfo) {
//           next({ path: "/login" })
//       }
//   }
//   const power = to.meta.power;
//   if (power) {
//       const index = userInfo.auth_menu.findIndex((pow: any) => {
//           return pow == power
//       })
//       if (index == -1) {
//           ElMessage({
//               type: 'error',
//               message: `页面加载失败,无权限访问!`,
//           });
//           next(false)
//           return
//       }
//   }

//   next()
// })
export default router
