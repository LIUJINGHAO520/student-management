// 在类型名称前面加上 type 关键字
import {
    createRouter,
    createWebHistory,
    type RouteRecordRaw,
    type NavigationGuardNext,
    type RouteLocationNormalized
} from 'vue-router'
import Login from '../views/Login.vue'
import Student from '../views/Student.vue'

// 定义路由
const routes: RouteRecordRaw[] = [
  {
    path: '/login',
    name: 'Login',
    component: Login,
    meta: { requiresAuth: false }
  },
  {
    path: '/',
    name: 'Student',
    component: Student,
    meta: { requiresAuth: true }
  },
  {
    path: '/:pathMatch(.*)*',
    redirect: '/'
  }
]

// 创建路由实例
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

// 全局路由守卫
router.beforeEach((to: RouteLocationNormalized, from: RouteLocationNormalized, next: NavigationGuardNext) => {
  // 获取登录状态（从 localStorage 读取）
  const isLoggedIn = localStorage.getItem('isLoggedIn') === 'true'

  // 如果目标路由需要认证
  if (to.meta.requiresAuth && !isLoggedIn) {
    // 未登录，重定向到登录页
    next('/login')
  } else if (to.path === '/login' && isLoggedIn) {
    // 已登录且试图访问登录页，重定向到首页
    next('/')
  } else {
    // 允许导航
    next()
  }
})

export default router

