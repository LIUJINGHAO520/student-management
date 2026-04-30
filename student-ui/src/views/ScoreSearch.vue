<template>
  <div id="app">
    <!-- 导航栏：提升页面专业感 -->
    <nav class="navbar navbar-dark bg-primary mb-4">
      <div class="container">
        <span class="navbar-brand mb-0 h1">学生成绩管理系统 v2.0</span>
      </div>
    </nav>

    <div class="container">
      <div class="card shadow-sm">
        <div class="card-body">
          <h5 class="card-title mb-4">数据检索</h5>
          
          <!-- 搜索区域 -->
          <div class="row g-3 mb-4">
            <div class="col-md-9">
              <input 
                v-model="keyword" 
                @keyup.enter="handleSearch"
                type="text" 
                class="form-control form-control-lg" 
                placeholder="搜索学生、课程或老师的姓名..."
              >
            </div>
            <div class="col-md-3">
              <button @click="handleSearch" class="btn btn-primary btn-lg w-100">
                <i class="bi bi-search"></i> 立即查询
              </button>
            </div>
          </div>

          <!-- 成绩表格 -->
          <div class="table-responsive">
            <table class="table table-hover align-middle">
              <thead class="table-light">
                <tr>
                  <th scope="col">学生</th>
                  <th scope="col">课程内容</th>
                  <th scope="col">任课教师</th>
                  <th scope="col">最终成绩</th>
                  <th scope="col">所在学期</th>
                </tr>
              </thead>
              <tbody>
                <!-- 如果有数据，循环显示 -->
                <tr v-for="item in scoreList" :key="item.id">
                  <td class="fw-bold text-primary">{{ item.studentName }}</td>
                  <td>{{ item.courseName }}</td>
                  <td>{{ item.teacherName }}</td>
                  <td>
                    <span :class="['badge', item.score >= 60 ? 'bg-success' : 'bg-danger']">
                      {{ item.score }}
                    </span>
                  </td>
                  <td><small class="text-muted">{{ item.semester }}</small></td>
                </tr>
                <!-- 如果没查到数据 -->
                <tr v-if="scoreList.length === 0">
                  <td colspan="5" class="text-center py-5 text-muted">
                    暂无相关成绩记录，请尝试输入关键词搜索。
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>

      <!-- 页脚 -->
      <footer class="mt-5 text-center text-muted pb-4">
        <p>© 2026 Student Management Project - Kyoto Practice</p>
      </footer>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'App',
  data() {
    return {
      keyword: '',   // 用户输入的搜索词
      scoreList: []  // 后端返回的数据列表
    };
  },
  methods: {
    // 核心函数：调用 Java 后端 API
    async handleSearch() {
      try {
        // 注意：这里调用的是你 IDEA 中启动的 Java 端口 (默认 8080)
        // 确保你的 Controller 路径是 /api/1/study/score/search
        const response = await axios.get('http://localhost:8080/api/1/study/score/search', {
          params: {
            keyword: this.keyword
          }
        });
        
        // 将获取的数据交给页面显示
        this.scoreList = response.data;
        console.log("数据加载成功:", response.data);
      } catch (error) {
        console.error("连接后端失败:", error);
        alert("无法获取数据，请确认：\n1. IDEA 后端项目是否已启动\n2. 后端是否添加了 @CrossOrigin 注解");
      }
    }
  },
  // 页面生命周期钩子：初次进入页面时自动加载一次全部数据
  mounted() {
    this.handleSearch();
  }
};
</script>

<style>
/* 简单的全局样式美化 */
body {
  background-color: #f8f9fa;
}
.card {
  border: none;
  border-radius: 12px;
}
.badge {
  font-size: 0.9em;
  padding: 0.5em 0.8em;
}
</style>