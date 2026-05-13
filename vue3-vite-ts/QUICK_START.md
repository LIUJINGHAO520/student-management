# 🚀 快速启动指南

## 前置条件
- Node.js >= 16.0.0
- npm >= 8.0.0

## 安装步骤

### 1️⃣ 安装依赖
```bash
cd vue3-vite-ts
npm install
```

### 2️⃣ 启动开发服务器
```bash
npm run dev
```

浏览器会自动打开 `http://localhost:5173`

### 3️⃣ 登录
使用以下测试账号登录：
- **用户名**: `admin`
- **密码**: `123456`

## 📌 主要功能

### ✨ 登录页面 (`/login`)
- 用户身份验证
- 使用本地测试账号
- 成功登录后跳转到首页

### 📊 学生成绩管理 (`/`)
- 查看所有学生成绩
- 搜索学生（学生名、课程、教师）
- 添加新的成绩记录
- 删除成绩记录

## 🔐 路由守卫

应用实现了自动的路由守卫：
- ✅ **未登录用户**：自动重定向到 `/login`
- ✅ **已登录用户**：可以访问 `/` 和其他受保护的路由
- ✅ **已登录后访问 `/login`**：自动重定向到首页 `/`

## 📁 项目结构

```
┌─ src/
│  ├─ api/              # API 接口层
│  ├─ components/       # 可复用组件
│  ├─ router/           # 路由配置
│  ├─ types/            # TypeScript 类型
│  ├─ utils/            # 工具函数
│  ├─ views/            # 页面视图
│  ├─ App.vue          # 根组件
│  └─ main.ts          # 应用入口
├─ .env.development     # 开发环境配置
└─ PROJECT_STRUCTURE.md # 详细结构说明
```

详见 `PROJECT_STRUCTURE.md` 文档

## 🛠️ 常用命令

```bash
# 开发模式（热模块刷新）
npm run dev

# 生产���建
npm run build

# 预览构建结果
npm run preview
```

## 🐛 常见问题

### Q: 后端服务无响应
**A**: 确保后端服务已启动，地址为 `http://localhost:8080`

### Q: CORS 错误
**A**: 确保后端配置了正确的 CORS 头，允许来自 `http://localhost:5173` 的请求

### Q: 登录后无法访问首页
**A**: 检查浏览器的 localStorage 是否启用，以及���发者工具 Console 是否有错误

## 📖 详细文档

- **项目架构**：查看 `PROJECT_STRUCTURE.md`
- **重构总结**：查看 `REFACTOR_SUMMARY.md`

## 💡 开发建议

### 本地开发环境变量
创建 `.env.local` 文件覆盖开发配置：

```env
VITE_API_BASE_URL=http://your-api-server:8080/api/1/study
```

### 浏览器调试
1. 打开 Vue DevTools 扩展
2. 查看 Router 标签页了解当前路由状态
3. 使用 Network 标签页查看 API 请求

## 🎓 学习资源

- [Vue 3 文档](https://vuejs.org/)
- [Vue Router 文档](https://router.vuejs.org/)
- [Vite 文档](https://vitejs.dev/)
- [TypeScript 文档](https://www.typescriptlang.org/)

## 📞 需要帮助？

如果你遇到问题，请检查：
1. ✅ Node.js 版本是否足够新
2. ✅ 是否正确运行了 `npm install`
3. ✅ 后端服务是否正常运行
4. ✅ 浏览器控制台中的错误信息
5. ✅ 端口 5173 是否已被其他应用占用

## 🎯 下一步

1. 完成项目初始化后，可以开始开发新功能
2. 遵循 `PROJECT_STRUCTURE.md` 中的规范
3. 根据 `REFACTOR_SUMMARY.md` 中的建议进行改进

