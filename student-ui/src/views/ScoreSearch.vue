<template>
  <div id="app">
    <!-- 导航栏 -->
    <nav class="navbar navbar-dark bg-primary mb-4">
      <div class="container">
        <span class="navbar-brand mb-0 h1">学生成绩管理系统 v2.0</span>
      </div>
    </nav>

    <div class="container">
      <!-- 1. 编辑/新增数据卡片 -->
      <div class="card shadow-sm mb-4 border-info">
        <div class="card-body">
          <h5 class="card-title mb-3" :class="isEdit ? 'text-info' : 'text-success'">
            <i :class="isEdit ? 'bi bi-pencil-square' : 'bi bi-plus-circle'"></i>
            {{ isEdit ? '修改成绩记录' : '新增成绩记录' }}
          </h5>
          <div class="row g-2">
            <div class="col-md-2">
              <input v-model="formScore.studentName" type="text" class="form-control" placeholder="学生姓名">
            </div>
            <div class="col-md-2">
              <input v-model="formScore.courseName" type="text" class="form-control" placeholder="课程名称">
            </div>
            <div class="col-md-2">
              <input v-model="formScore.teacherName" type="text" class="form-control" placeholder="教师姓名">
            </div>
            <div class="col-md-2">
              <input v-model.number="formScore.score" type="number" class="form-control" placeholder="分数">
            </div>
            <div class="col-md-2">
              <input v-model="formScore.semester" type="text" class="form-control" placeholder="学期">
            </div>
            <div class="col-md-2">
              <!-- 提交按钮：根据状态切换文字和颜色 -->
              <button v-if="!isEdit" @click="handleSubmit" class="btn btn-success w-100">确认添加</button>
              <div v-else class="btn-group w-100">
                <button @click="handleSubmit" class="btn btn-info text-white">保存修改</button>
                <button @click="cancelEdit" class="btn btn-secondary">取消</button>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- 2. 数据检索与展示卡片 -->
      <div class="card shadow-sm">
        <div class="card-body">
          <h5 class="card-title mb-4">数据检索</h5>
          
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

          <div class="table-responsive">
            <table class="table table-hover align-middle">
              <thead class="table-light">
                <tr>
                  <th scope="col">学生</th>
                  <th scope="col">课程内容</th>
                  <th scope="col">任课教师</th>
                  <th scope="col">最终成绩</th>
                  <th scope="col">所在学期</th>
                  <th scope="col">操作</th>
                </tr>
              </thead>
              <tbody>
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
                  <td>
                    <div class="btn-group">
                      <button @click="startEdit(item)" class="btn btn-outline-primary btn-sm">
                        编辑
                      </button>
                      <button @click="handleDelete(item.id)" class="btn btn-outline-danger btn-sm">
                        删除
                      </button>
                    </div>
                  </td>
                </tr>
                
                <tr v-if="scoreList.length === 0">
                  <td colspan="6" class="text-center py-5 text-muted">
                    暂无相关成绩记录。
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>

      <footer class="mt-5 text-center text-muted pb-4">
        <p>© 2026 Student Management Project - Kyoto Practice</p>
      </footer>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

// 统一配置基础路径，必须与后端 @RequestMapping 对应
const API_BASE = 'http://localhost:8080/api/1/study/score';

export default {
  name: 'ScoreSearch',
  data() {
    return {
      keyword: '',
      scoreList: [],
      isEdit: false, // 标记当前是否为“编辑模式”
      // 表单绑定的对象
      formScore: {
        id: null,
        studentName: '',
        courseName: '',
        teacherName: '',
        score: 0,
        semester: ''
      }
    };
  },
  methods: {
    // 1. 搜索
    async handleSearch() {
      try {
        const response = await axios.get(`${API_BASE}/search`, {
          params: { keyword: this.keyword }
        });
        this.scoreList = response.data;
      } catch (error) {
        console.error("查询失败:", error);
        alert("无法获取数据，请确保后端已启动并配置了 @CrossOrigin");
      }
    },

    // 2. 进入编辑状态
    startEdit(item) {
      this.isEdit = true;
      // 使用解构赋值拷贝数据，避免直接修改列表行
      this.formScore = { ...item };
      // 自动滚动到顶部方便编辑
      window.scrollTo({ top: 0, behavior: 'smooth' });
    },

    // 3. 取消编辑
    cancelEdit() {
      this.isEdit = false;
      this.formScore = { id: null, studentName: '', courseName: '', teacherName: '', score: 0, semester: '' };
    },

    // 4. 提交（新增或修改）
    async handleSubmit() {
      if (!this.formScore.studentName || !this.formScore.courseName) {
        alert("请填写学生姓名和课程名称！");
        return;
      }

      try {
        let response;
        if (this.isEdit) {
          // 修改逻辑：PUT 请求
          response = await axios.put(`${API_BASE}/update`, this.formScore);
        } else {
          // 新增逻辑：POST 请求
          response = await axios.post(`${API_BASE}/save`, this.formScore);
        }

        if (response.data.code === 200) {
          alert(this.isEdit ? '修改成功！' : '添加成功！');
          this.cancelEdit(); // 重置表单
          this.handleSearch(); // 刷新列表
        } else {
          alert('操作失败：' + response.data.message);
        }
      } catch (error) {
        console.error("提交出错:", error);
        alert("提交失败，请检查后端 /save 或 /update 接口。");
      }
    },

    // 5. 删除
    async handleDelete(id) {
      if (!confirm('确定要删除这条成绩记录吗？')) return;
      try {
        const response = await axios.delete(`${API_BASE}/delete/${id}`);
        if (response.data.code === 200) {
          alert('删除成功！');
          this.handleSearch(); 
        }
      } catch (error) {
        alert("删除请求失败。");
      }
    }
  },
  mounted() {
    this.handleSearch();
  }
};
</script>

<style>
body { background-color: #f8f9fa; }
.card { border: none; border-radius: 12px; }
.badge { font-size: 0.9em; padding: 0.5em 0.8em; }
.table-hover tbody tr:hover { background-color: #f1f4f9; }
/* 编辑模式下的卡片边框阴影 */
.border-info { box-shadow: 0 0 10px rgba(13, 202, 240, 0.2); }
</style>