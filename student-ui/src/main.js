import { createApp } from 'vue'
import App from './App.vue'
import { createRouter, createWebHashHistory } from 'vue-router'
import 'bootstrap/dist/css/bootstrap.min.css'

// 1. 定义路由规则
import Login from './views/Login.vue'
import ScoreSearch from './views/ScoreSearch.vue'

const routes = [
  { path: '/', component: Login },
  { path: '/scores', component: ScoreSearch }
]

// 2. 创建路由实例
const router = createRouter({
  history: createWebHashHistory(),
  routes
})

// 3. 挂载
const app = createApp(App)
app.use(router)
app.mount('#app')