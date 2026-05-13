# Vue3 + Vite + TypeScript 学生管理系统 - 项目结构说明

## 📁 目录结构

```
src/
├── api/                     # API 接口层
│   ├── client.ts           # 接口请求客户端（axios 配置）
│   └── score.ts            # 学生成绩相关 API
├── assets/                 # 静态资源（图片、字体等）
├── components/             # 可复用的通用组件
├── hooks/                  # 自定义 Vue hooks（待扩展）
├── layout/                 # 布局组件（待扩展）
├── router/                 # 路由配置
│   └── index.ts           # 路由定义及路由守卫
├── store/                  # 状态管理（待扩展，可用 Pinia）
├── types/                  # TypeScript 类型定义
│   └── index.ts           # 全局类型定义
├── utils/                  # 工具函数
│   └── errorHandler.ts    # 错误处理工具
├── views/                  # 页面视图组件
│   ├── Login.vue          # 登录页
│   └── Student.vue        # 学生管理页
├── App.vue                # 根组件（路由出口）
├── main.ts                # 应用入口文件
└── style.css              # 全局样式
```

## 🚀 快速开始

### 1. 安装依赖
```bash
npm install
```

### 2. 本地开发
```bash
npm run dev
```

### 3. 生产构建
```bash
npm run build
```

### 4. 预览构建结果
```bash
npm run preview
```

## 📋 目录规范说明

### `api/` - API 层
- 集中管理所有 HTTP 请求
- 每个模块一个文件（如 `score.ts`）
- 使用 Axios 实例统一配置

### `views/` - 页面视图
- 对应路由的页面组件
- 包含相应的业务逻辑

### `components/` - 可复用组件
- 存放通用的、可复用的 Vue 组件
- 不应包含特定业务逻辑

### `types/` - 类型定义
- TypeScript 类型定义
- 接口定义
- 避免在各个文件中重复定义

### `utils/` - 工具函数
- 错误处理
- 数据格式化
- 公共方法

### `router/` - 路由配置
- 路由定义
- 路由守卫
- 导航逻辑

## 🔐 认证流程

1. **登录**：用户在 Login.vue 登录，成功后保存登录状态到 localStorage
2. **路由守卫**：router/index.ts 的全局前置守卫检查登录状态
3. **未认证用户**：自动重定向到登录页 `/login`
4. **已认证用户**：可以访问 `/` （Student.vue）

## 📝 最佳实践

### 1. API 请求
```typescript
// ✅ 推荐：使用 api 模块
import { getAllScores } from '@/api/score'

const fetchScores = async () => {
  try {
    const response = await getAllScores()
    scores.value = response.data
  } catch (error) {
    showError(error, '获取成绩列表失败')
  }
}
```

### 2. 错误处理
```typescript
// ✅ 推荐：使用统一的错误处理工具
import { showError } from '@/utils/errorHandler'

try {
  // 操作
} catch (error) {
  showError(error, '操作失败')
}
```

### 3. 类型定义
```typescript
// ✅ 推荐：使用集中管理的类型
import type { StudentScore } from '@/types'

const scores = ref<StudentScore[]>([])
```

## 🔄 后续改进建议

1. **状态管理**：集成 Pinia 进行全局状态管理
2. **HTTP 拦截器**：添加请求和响应拦截器，处理 token 等
3. **国际化**：集成 i18n 支持多语言
4. **UI 组件库**：使用专业的 Vue3 组件库替代 Bootstrap
5. **日志系统**：集成日志系统用于调试和监测
6. **单元测试**：添加 Vitest 进行单元测试
7. **环境变量**：使用 `.env.*` 文件管理环境配置

## 📚 参考资源

- [Vue 3 官方文档](https://vuejs.org/)
- [Vue Router 官方文档](https://router.vuejs.org/)
- [Vite 官方文档](https://vitejs.dev/)
- [TypeScript 官方文档](https://www.typescriptlang.org/)
- [Axios 官方文档](https://axios-http.com/)

