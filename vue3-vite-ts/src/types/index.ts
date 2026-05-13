/**
 * 学生成绩接口
 */
export interface StudentScore {
  id: number
  studentName: string
  courseName: string
  teacherName: string
  score: number
  semester: string
}

/**
 * 学号输入表单
 */
export interface StudentScoreForm extends Omit<StudentScore, 'id'> {}

/**
 * API 响应
 */
export interface ApiResponse<T = any> {
  code: number
  message: string
  data: T
}

/**
 * 登录状态
 */
export interface LoginState {
  isLoggedIn: boolean
  username: string
}

