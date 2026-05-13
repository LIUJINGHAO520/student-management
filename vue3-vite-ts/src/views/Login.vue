<script setup lang="ts">
import { ref } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()

// 登录表单数据
const loginUser = ref('')
const loginPwd = ref('')

// 登录处理函数
const handleLogin = () => {
  // 模拟登录验证
  if (loginUser.value === 'admin' && loginPwd.value === '123456') {
    // 保存登录状态到 localStorage
    localStorage.setItem('isLoggedIn', 'true')
    localStorage.setItem('username', loginUser.value)

    // 导航到首页
    router.push('/')
  } else {
    alert('用户名或密码错误')
  }
}
</script>

<template>
  <div class="login-wrapper">
    <div class="login-container">
      <!-- 标题部分 -->
      <div class="login-header">
        <div class="logo-icon">📚</div>
        <h1 class="system-title">学生成绩管理系统</h1>
        <p class="system-subtitle">Student Score Management System</p>
      </div>

      <!-- 登录表单 -->
      <form class="login-form" @submit.prevent="handleLogin">
        <!-- 用户名输入框 -->
        <div class="form-group">
          <label for="username" class="form-label">
            <span class="label-icon">👤</span>
            用户名
          </label>
          <input
              id="username"
              type="text"
              v-model="loginUser"
              placeholder="请输入用户名"
              class="form-input"
              required
          >
        </div>

        <!-- 密码输入框 -->
        <div class="form-group">
          <label for="password" class="form-label">
            <span class="label-icon">🔐</span>
            密码
          </label>
          <input
              id="password"
              type="password"
              v-model="loginPwd"
              placeholder="请输入密码"
              class="form-input"
              required
          >
        </div>

        <!-- 登录按钮 -->
        <button type="submit" class="login-btn">
          <span class="btn-icon">→</span>
          登录
        </button>
      </form>

      <!-- 提示信息 -->
      <div class="login-tips">
        <p class="tips-title">测试账号</p>
        <div class="tips-content">
          <div class="tip-item">
            <span class="tip-label">用户名：</span>
            <span class="tip-value">admin</span>
          </div>
          <div class="tip-item">
            <span class="tip-label">密码：</span>
            <span class="tip-value">123456</span>
          </div>
        </div>
      </div>

      <!-- 底部说明 -->
      <div class="login-footer">
        <p class="footer-text">© 2024 学生管理系统 | 安全登录</p>
      </div>
    </div>
  </div>
</template>

<style scoped>
/* ============== 布局相关 ============== */
.login-wrapper {
  min-height: 100vh;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 20px;
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen',
    'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue',
    sans-serif;
}

.login-container {
  background: white;
  border-radius: 12px;
  box-shadow: 0 20px 60px rgba(0, 0, 0, 0.3);
  width: 100%;
  max-width: 420px;
  padding: 40px;
  animation: slideIn 0.4s ease-out;
}

@keyframes slideIn {
  from {
    opacity: 0;
    transform: translateY(-20px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

/* ============== 标题部分 ============== */
.login-header {
  text-align: center;
  margin-bottom: 30px;
}

.logo-icon {
  font-size: 48px;
  margin-bottom: 15px;
  display: block;
}

.system-title {
  font-size: 24px;
  font-weight: 600;
  color: #333;
  margin: 0 0 8px 0;
  letter-spacing: 0.5px;
}

.system-subtitle {
  font-size: 12px;
  color: #999;
  margin: 0;
  text-transform: uppercase;
  letter-spacing: 1px;
  font-weight: 500;
}

/* ============== 表单部分 ============== */
.login-form {
  margin-bottom: 20px;
}

.form-group {
  margin-bottom: 20px;
}

.form-label {
  display: flex;
  align-items: center;
  font-size: 14px;
  font-weight: 500;
  color: #333;
  margin-bottom: 8px;
  cursor: pointer;
}

.label-icon {
  margin-right: 6px;
  font-size: 16px;
}

.form-input {
  width: 100%;
  padding: 12px 16px;
  border: 2px solid #e5e7eb;
  border-radius: 8px;
  font-size: 14px;
  color: #333;
  background-color: #f9fafb;
  transition: all 0.3s ease;
  box-sizing: border-box;
}

.form-input:focus {
  outline: none;
  border-color: #667eea;
  background-color: #fff;
  box-shadow: 0 0 0 3px rgba(102, 126, 234, 0.1);
}

.form-input::placeholder {
  color: #999;
}

/* ============== 按钮样式 ============== */
.login-btn {
  width: 100%;
  padding: 12px 24px;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
  border: none;
  border-radius: 8px;
  font-size: 15px;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.3s ease;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 8px;
  letter-spacing: 0.5px;
  box-shadow: 0 4px 15px rgba(102, 126, 234, 0.4);
}

.login-btn:hover {
  transform: translateY(-2px);
  box-shadow: 0 6px 20px rgba(102, 126, 234, 0.6);
}

.login-btn:active {
  transform: translateY(0);
}

.btn-icon {
  font-size: 16px;
  transition: transform 0.3s ease;
}

.login-btn:hover .btn-icon {
  transform: translateX(4px);
}

/* ============== 提示部分 ============== */
.login-tips {
  background: #f0f4ff;
  border-left: 4px solid #667eea;
  border-radius: 6px;
  padding: 16px;
  margin-bottom: 20px;
}

.tips-title {
  font-size: 12px;
  font-weight: 700;
  color: #667eea;
  margin: 0 0 10px 0;
  text-transform: uppercase;
  letter-spacing: 0.5px;
}

.tips-content {
  display: flex;
  flex-direction: column;
  gap: 8px;
}

.tip-item {
  display: flex;
  align-items: center;
  font-size: 13px;
  color: #555;
}

.tip-label {
  color: #999;
  min-width: 60px;
}

.tip-value {
  color: #333;
  font-weight: 600;
  font-family: 'Courier New', monospace;
  letter-spacing: 0.5px;
}

/* ============== 底部 ============== */
.login-footer {
  text-align: center;
  padding-top: 10px;
  border-top: 1px solid #e5e7eb;
}

.footer-text {
  font-size: 12px;
  color: #999;
  margin: 0;
  letter-spacing: 0.5px;
}

/* ============== 响应式设计 ============== */
/* 平板设备 */
@media (max-width: 768px) {
  .login-container {
    max-width: 100%;
    padding: 30px 20px;
  }

  .system-title {
    font-size: 20px;
  }

  .login-header {
    margin-bottom: 25px;
  }

  .form-input {
    padding: 11px 14px;
    font-size: 16px; /* iOS 自动缩放防止 */
  }

  .login-btn {
    padding: 11px 20px;
    font-size: 14px;
  }
}

/* 小型手机 */
@media (max-width: 480px) {
  .login-wrapper {
    padding: 10px;
  }

  .login-container {
    padding: 24px 16px;
    border-radius: 10px;
  }

  .logo-icon {
    font-size: 40px;
    margin-bottom: 12px;
  }

  .system-title {
    font-size: 18px;
    margin-bottom: 6px;
  }

  .system-subtitle {
    font-size: 11px;
  }

  .login-header {
    margin-bottom: 20px;
  }

  .form-group {
    margin-bottom: 16px;
  }

  .form-label {
    font-size: 13px;
    margin-bottom: 6px;
  }

  .form-input {
    padding: 10px 12px;
    font-size: 16px;
    border-radius: 6px;
  }

  .login-tips {
    padding: 12px;
    margin-bottom: 16px;
  }

  .tip-item {
    font-size: 12px;
  }

  .footer-text {
    font-size: 11px;
  }
}

/* 大屏幕优化 */
@media (min-width: 1200px) {
  .login-container {
    max-width: 450px;
    padding: 50px;
  }

  .system-title {
    font-size: 28px;
    margin-bottom: 12px;
  }

  .form-input {
    padding: 14px 18px;
    font-size: 15px;
  }

  .login-btn {
    padding: 14px 28px;
    font-size: 16px;
  }
}
</style>

