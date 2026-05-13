<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'

const router = useRouter()

// 定义学生成绩接口
interface StudentScore {
  id: number
  studentName: string
  courseName: string
  teacherName: string
  score: number
  semester: string
}

// 响应式数据
const scores = ref<StudentScore[]>([])
const keyword = ref('')
const showAddForm = ref(false)
const username = ref('')

// 表单数据
const formData = ref({
  studentName: '',
  courseName: '',
  teacherName: '',
  score: 0,
  semester: '2023-1'
})

// 获取所有成绩数据
const fetchScores = async () => {
  try {
    const response = await axios.get('http://localhost:8080/api/1/study/score/all')
    scores.value = response.data
  } catch (error: any) {
    console.dir(error)
    let errorMsg = "获取成绩数据失败"
    if (error.response) {
      errorMsg = `服务器错误: ${error.response.status} - ${error.response.data?.message || "未知错误"}`
    } else if (error.request) {
      errorMsg = "服务器无响应，请检查后端服务是否启动"
    } else {
      errorMsg = `请求错误: ${error.message}`
    }
    alert(errorMsg)
  }
}

// 搜索功能
const searchScores = () => {
  if (!keyword.value) {
    fetchScores()
    return
  }

  const filtered = scores.value.filter(score =>
      score.studentName.includes(keyword.value) ||
      score.courseName.includes(keyword.value) ||
      score.teacherName.includes(keyword.value)
  )

  scores.value = filtered
}

// 删除成绩
const handleDelete = async (id: number) => {
  if (!confirm('确定要删除这条记录吗？')) return

  try {
    await axios.delete(`http://localhost:8080/api/1/study/score/delete/${id}`)
    fetchScores()
  } catch (error: any) {
    console.dir(error)
    let errorMsg = "删除失败"
    if (error.response) {
      errorMsg = `服务器错误: ${error.response.status} - ${error.response.data?.message || "未知错误"}`
    } else if (error.request) {
      errorMsg = "服务器无响应，请检查后端服务是否启动"
    } else {
      errorMsg = `请求错误: ${error.message}`
    }
    alert(errorMsg)
  }
}

// 添加成绩
const handleAdd = async () => {
  try {
    const response = await axios.post('http://localhost:8080/api/1/study/score/save', formData.value)
    if (response.data && response.data.code === 200) {
      showAddForm.value = false
      fetchScores()

      // 重置表单
      formData.value = {
        studentName: '',
        courseName: '',
        teacherName: '',
        score: 0,
        semester: '2023-1'
      }
    } else {
      throw new Error(response.data?.message || "添加失败")
    }
  } catch (error: any) {
    console.dir(error)
    let errorMsg = "添加失败"
    if (error.response) {
      errorMsg = `服务器错误: ${error.response.status} - ${error.response.data?.message || "未知错误"}`
    } else if (error.request) {
      errorMsg = "服务器无响应，请检查后端服务是否启动"
    } else {
      errorMsg = `请求错误: ${error.message}`
    }
    alert(errorMsg)
  }
}

// 退出登录
const handleLogout = () => {
  // 清除登录状态
  localStorage.removeItem('isLoggedIn')
  localStorage.removeItem('username')

  // 导航到登录页
  router.push('/login')
}

// 组件挂载时获取数据和用户信息
onMounted(() => {
  fetchScores()
  username.value = localStorage.getItem('username') || ''
})
</script>

<template>
  <div class="student-wrapper">
    <!-- 导航栏 -->
    <nav class="navbar">
      <div class="navbar-container">
        <div class="navbar-brand">
          <span class="brand-icon">📚</span>
          <h1 class="brand-title">学生成绩管理</h1>
        </div>
        <div class="navbar-user">
          <span v-if="username" class="user-welcome">👤 {{ username }}</span>
          <button class="logout-btn" @click="handleLogout">退出登录</button>
        </div>
      </div>
    </nav>

    <!-- 主容器 -->
    <div class="main-container">
      <!-- 搜索和操作栏 -->
      <div class="action-bar">
        <div class="search-section">
          <div class="search-wrapper">
            <input
                type="text"
                class="search-input"
                placeholder="🔍 搜索学生、课程或教师..."
                v-model="keyword"
                @input="searchScores"
            >
          </div>
        </div>
        <div class="action-buttons">
          <button
              class="add-btn"
              @click="showAddForm = !showAddForm"
          >
            <span class="btn-icon">{{ showAddForm ? '✕' : '+' }}</span>
            {{ showAddForm ? '取消' : '添加成绩' }}
          </button>
        </div>
      </div>

      <!-- 添加表单 -->
      <div v-if="showAddForm" class="add-form-container">
        <div class="form-card">
          <div class="form-header">
            <h2 class="form-title">📝 添加新成绩</h2>
            <p class="form-subtitle">请填写以下信息</p>
          </div>
          <form class="form-content" @submit.prevent="handleAdd">
            <div class="form-row">
              <div class="form-group">
                <label class="form-label">学生姓名</label>
                <input
                    type="text"
                    class="form-input"
                    v-model="formData.studentName"
                    placeholder="请输入学生姓名"
                    required
                >
              </div>
              <div class="form-group">
                <label class="form-label">课程名称</label>
                <input
                    type="text"
                    class="form-input"
                    v-model="formData.courseName"
                    placeholder="请输入课程名称"
                    required
                >
              </div>
            </div>

            <div class="form-row">
              <div class="form-group">
                <label class="form-label">教师姓名</label>
                <input
                    type="text"
                    class="form-input"
                    v-model="formData.teacherName"
                    placeholder="请输入教师姓名"
                    required
                >
              </div>
              <div class="form-group">
                <label class="form-label">成绩</label>
                <input
                    type="number"
                    class="form-input"
                    v-model.number="formData.score"
                    min="0"
                    max="100"
                    placeholder="0-100"
                    required
                >
              </div>
            </div>

            <div class="form-row">
              <div class="form-group full-width">
                <label class="form-label">学期</label>
                <select class="form-select" v-model="formData.semester">
                  <option value="2023-1">2023 第一学期</option>
                  <option value="2023-2">2023 第二学期</option>
                  <option value="2024-1">2024 第一学期</option>
                </select>
              </div>
            </div>

            <div class="form-actions">
              <button type="submit" class="submit-btn">💾 保存</button>
              <button
                  type="button"
                  class="cancel-btn"
                  @click="showAddForm = false"
              >
                ✕ 取消
              </button>
            </div>
          </form>
        </div>
      </div>

      <!-- 成绩表格 -->
      <div class="table-container">
        <div class="table-header">
          <h2 class="table-title">📊 成绩列表</h2>
          <p class="table-count" v-if="scores.length > 0">共 {{ scores.length }} 条记录</p>
        </div>

        <div class="table-wrapper">
          <table class="scores-table">
            <thead>
            <tr>
              <th class="col-id">ID</th>
              <th class="col-name">学生姓名</th>
              <th class="col-course">课程名称</th>
              <th class="col-teacher">教师姓名</th>
              <th class="col-score">成绩</th>
              <th class="col-semester">学期</th>
              <th class="col-action">操作</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="score in scores" :key="score.id" class="table-row">
              <td class="col-id">{{ score.id }}</td>
              <td class="col-name">{{ score.studentName }}</td>
              <td class="col-course">{{ score.courseName }}</td>
              <td class="col-teacher">{{ score.teacherName }}</td>
              <td class="col-score">
                <span class="score-badge" :class="`score-${Math.floor(score.score / 10) * 10}`">
                  {{ score.score }}
                </span>
              </td>
              <td class="col-semester">{{ score.semester }}</td>
              <td class="col-action">
                <button class="delete-btn" @click="handleDelete(score.id)">
                  🗑️ 删除
                </button>
              </td>
            </tr>
            <tr v-if="scores.length === 0" class="empty-row">
              <td colspan="7" class="empty-message">
                <div class="empty-state">
                  <p class="empty-icon">📭</p>
                  <p class="empty-text">暂无学生成绩数据</p>
                  <p class="empty-hint">点击"添加成绩"按钮添加新记录</p>
                </div>
              </td>
            </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
/* ============== 全局样式 ============== */
.student-wrapper {
  min-height: 100vh;
  background: linear-gradient(135deg, #f5f7fb 0%, #f0f4ff 100%);
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen',
    'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue',
    sans-serif;
}

/* ============== 导航栏 ============== */
.navbar {
  background: white;
  border-bottom: 1px solid #e5e7eb;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
  position: sticky;
  top: 0;
  z-index: 100;
}

.navbar-container {
  max-width: 1400px;
  margin: 0 auto;
  padding: 16px 24px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.navbar-brand {
  display: flex;
  align-items: center;
  gap: 12px;
}

.brand-icon {
  font-size: 28px;
}

.brand-title {
  font-size: 20px;
  font-weight: 600;
  color: #333;
  margin: 0;
}

.navbar-user {
  display: flex;
  align-items: center;
  gap: 16px;
}

.user-welcome {
  font-size: 14px;
  color: #666;
  font-weight: 500;
}

.logout-btn {
  padding: 8px 16px;
  background: #f3f4f6;
  color: #333;
  border: 1px solid #e5e7eb;
  border-radius: 6px;
  font-size: 14px;
  font-weight: 500;
  cursor: pointer;
  transition: all 0.3s ease;
}

.logout-btn:hover {
  background: #e5e7eb;
  border-color: #d1d5db;
}

/* ============== 主容器 ============== */
.main-container {
  max-width: 1400px;
  margin: 0 auto;
  padding: 24px;
}

/* ============== 操作栏 ============== */
.action-bar {
  display: flex;
  gap: 16px;
  margin-bottom: 24px;
  flex-wrap: wrap;
}

.search-section {
  flex: 1;
  min-width: 200px;
}

.search-wrapper {
  position: relative;
}

.search-input {
  width: 100%;
  padding: 12px 16px;
  border: 2px solid #e5e7eb;
  border-radius: 8px;
  font-size: 14px;
  color: #333;
  background: white;
  transition: all 0.3s ease;
}

.search-input:focus {
  outline: none;
  border-color: #667eea;
  box-shadow: 0 0 0 3px rgba(102, 126, 234, 0.1);
}

.action-buttons {
  display: flex;
  gap: 12px;
}

.add-btn {
  padding: 12px 20px;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
  border: none;
  border-radius: 8px;
  font-size: 14px;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.3s ease;
  display: flex;
  align-items: center;
  gap: 8px;
  white-space: nowrap;
}

.add-btn:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 15px rgba(102, 126, 234, 0.4);
}

.btn-icon {
  font-weight: bold;
  font-size: 16px;
}

/* ============== ���加表单 ============== */
.add-form-container {
  margin-bottom: 24px;
  animation: slideDown 0.3s ease-out;
}

@keyframes slideDown {
  from {
    opacity: 0;
    transform: translateY(-10px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

.form-card {
  background: white;
  border-radius: 12px;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.08);
  overflow: hidden;
}

.form-header {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
  padding: 24px;
}

.form-title {
  font-size: 18px;
  font-weight: 600;
  margin: 0 0 8px 0;
}

.form-subtitle {
  font-size: 13px;
  color: rgba(255, 255, 255, 0.9);
  margin: 0;
}

.form-content {
  padding: 24px;
}

.form-row {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 20px;
  margin-bottom: 20px;
}

.form-group {
  display: flex;
  flex-direction: column;
}

.form-group.full-width {
  grid-column: 1 / -1;
}

.form-label {
  font-size: 14px;
  font-weight: 500;
  color: #333;
  margin-bottom: 8px;
}

.form-input,
.form-select {
  padding: 12px 14px;
  border: 2px solid #e5e7eb;
  border-radius: 6px;
  font-size: 14px;
  color: #333;
  background: #f9fafb;
  transition: all 0.3s ease;
  font-family: inherit;
}

.form-input:focus,
.form-select:focus {
  outline: none;
  border-color: #667eea;
  background: white;
  box-shadow: 0 0 0 3px rgba(102, 126, 234, 0.1);
}

.form-actions {
  display: flex;
  gap: 12px;
  margin-top: 24px;
  padding-top: 24px;
  border-top: 1px solid #e5e7eb;
}

.submit-btn,
.cancel-btn {
  padding: 11px 20px;
  border: none;
  border-radius: 6px;
  font-size: 14px;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.3s ease;
}

.submit-btn {
  background: linear-gradient(135deg, #10b981 0%, #059669 100%);
  color: white;
  flex: 1;
}

.submit-btn:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(16, 185, 129, 0.3);
}

.cancel-btn {
  background: #f3f4f6;
  color: #666;
  border: 1px solid #e5e7eb;
}

.cancel-btn:hover {
  background: #e5e7eb;
}

/* ============== 表格容器 ============== */
.table-container {
  background: white;
  border-radius: 12px;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.08);
  overflow: hidden;
}

.table-header {
  padding: 24px;
  border-bottom: 1px solid #e5e7eb;
}

.table-title {
  font-size: 18px;
  font-weight: 600;
  color: #333;
  margin: 0 0 8px 0;
}

.table-count {
  font-size: 13px;
  color: #999;
  margin: 0;
}

.table-wrapper {
  overflow-x: auto;
}

.scores-table {
  width: 100%;
  border-collapse: collapse;
  font-size: 14px;
}

.scores-table thead tr {
  background: #f9fafb;
  border-bottom: 2px solid #e5e7eb;
}

.scores-table th {
  padding: 12px 16px;
  text-align: left;
  font-weight: 600;
  color: #333;
  background: #f9fafb;
}

.scores-table tbody tr {
  border-bottom: 1px solid #f3f4f6;
  transition: all 0.2s ease;
}

.scores-table tbody tr:hover {
  background: #fafbfc;
}

.scores-table td {
  padding: 14px 16px;
  color: #333;
}

.col-id {
  width: 60px;
  color: #999;
  font-weight: 500;
}

.col-score {
  width: 80px;
}

.score-badge {
  display: inline-block;
  padding: 4px 12px;
  border-radius: 20px;
  font-weight: 600;
  font-size: 13px;
  color: white;
}

.score-90,
.score-100 {
  background: linear-gradient(135deg, #10b981 0%, #059669 100%);
}

.score-80 {
  background: linear-gradient(135deg, #3b82f6 0%, #2563eb 100%);
}

.score-70 {
  background: linear-gradient(135deg, #f59e0b 0%, #d97706 100%);
}

.score-60 {
  background: linear-gradient(135deg, #ef4444 0%, #dc2626 100%);
}

.col-action {
  width: 100px;
}

.delete-btn {
  padding: 6px 12px;
  background: #ffebee;
  color: #d32f2f;
  border: 1px solid #ffcdd2;
  border-radius: 6px;
  font-size: 12px;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.3s ease;
}

.delete-btn:hover {
  background: #ef5350;
  color: white;
  border-color: #ef5350;
}

/* ============== 空状态 ============== */
.empty-row {
  background: white;
}

.empty-message {
  padding: 60px 24px !important;
  text-align: center;
}

.empty-state {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 12px;
}

.empty-icon {
  font-size: 48px;
  margin: 0;
}

.empty-text {
  font-size: 16px;
  font-weight: 500;
  color: #333;
  margin: 0;
}

.empty-hint {
  font-size: 13px;
  color: #999;
  margin: 0;
}

/* ============== 响应式设计 ============== */
@media (max-width: 1024px) {
  .main-container {
    padding: 16px;
  }

  .action-bar {
    flex-direction: column;
  }

  .search-section {
    width: 100%;
  }

  .form-row {
    grid-template-columns: 1fr;
  }

  .scores-table {
    font-size: 13px;
  }

  .scores-table th,
  .scores-table td {
    padding: 10px 12px;
  }
}

@media (max-width: 768px) {
  .navbar-container {
    padding: 12px 16px;
  }

  .brand-title {
    font-size: 16px;
  }

  .navbar-user {
    gap: 8px;
  }

  .user-welcome {
    display: none;
  }

  .main-container {
    padding: 12px;
  }

  .form-content {
    padding: 16px;
  }

  .table-header {
    padding: 16px;
  }

  .scores-table {
    font-size: 12px;
  }

  .scores-table th,
  .scores-table td {
    padding: 8px 10px;
  }

  .col-id {
    width: 50px;
  }

  .col-course,
  .col-teacher {
    display: none;
  }

  .delete-btn {
    padding: 4px 8px;
    font-size: 11px;
  }
}

@media (max-width: 480px) {
  .navbar-container {
    padding: 12px 12px;
    gap: 8px;
  }

  .brand-icon {
    font-size: 24px;
  }

  .brand-title {
    font-size: 14px;
  }

  .logout-btn {
    padding: 6px 12px;
    font-size: 13px;
  }

  .main-container {
    padding: 8px;
  }

  .add-btn {
    padding: 10px 14px;
    font-size: 13px;
  }

  .form-header {
    padding: 16px;
  }

  .form-content {
    padding: 12px;
  }

  .form-row {
    gap: 12px;
    margin-bottom: 12px;
  }

  .table-header {
    padding: 12px;
  }

  .empty-message {
    padding: 40px 16px !important;
  }

  .empty-icon {
    font-size: 36px;
  }

  .empty-text {
    font-size: 14px;
  }

  .scores-table {
    font-size: 11px;
  }

  .scores-table th,
  .scores-table td {
    padding: 6px 8px;
  }

  .col-id {
    width: 40px;
  }

  .col-semester {
    display: none;
  }
}
</style>

