<template>
  <div class="login-container d-flex align-items-center justify-content-center">
    <div class="card shadow-lg p-4" style="width: 400px; border-radius: 15px;">
      <div class="text-center mb-4">
        <h3 class="fw-bold text-primary">学生管理系统登录</h3>
        <p class="text-muted">请输入您的凭据以访问系统</p>
      </div>
      
      <div class="mb-3">
        <label class="form-label">账号</label>
        <input v-model="loginForm.username" type="text" class="form-control" placeholder="请输入 admin">
      </div>
      
      <div class="mb-3">
        <label class="form-label">密码</label>
        <input v-model="loginForm.password" type="password" class="form-control" placeholder="请输入 123456">
      </div>
      
      <button @click="handleLogin" class="btn btn-primary w-100 btn-lg mb-3">立即登录</button>
      
      <div v-if="errorMsg" class="alert alert-danger py-2 text-center">
        {{ errorMsg }}
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      loginForm: {
        username: '',
        password: ''
      },
      errorMsg: ''
    };
  },
  methods: {
    async handleLogin() {
      try {
        const res = await axios.post('http://localhost:8080/api/login', this.loginForm);
        if (res.data.code === 200) {
          alert('登录成功！');
          // 登录成功后跳转到管理页面
          this.$router.push('/scores');
        } else {
          this.errorMsg = res.data.message;
        }
      } catch (err) {
        this.errorMsg = '服务器连接失败，请检查后端运行状态';
      }
    }
  }
};
</script>

<style scoped>
.login-container {
  height: 100vh;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
}
</style>