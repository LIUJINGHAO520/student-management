import apiClient from './client'

export interface StudentScore {
  id: number
  studentName: string
  courseName: string
  teacherName: string
  score: number
  semester: string
}

/**
 * 获取所有学生成绩
 */
export const getAllScores = () => {
  return apiClient.get<StudentScore[]>('/score/all')
}

/**
 * 删除学生成绩
 */
export const deleteScore = (id: number) => {
  return apiClient.delete(`/score/delete/${id}`)
}

/**
 * 添加学生成绩
 */
export const addScore = (data: Omit<StudentScore, 'id'>) => {
  return apiClient.post('/score/save', data)
}

/**
 * 搜索学生成绩（可选：后端实现）
 */
export const searchScores = (keyword: string) => {
  return apiClient.get<StudentScore[]>('/score/search', {
    params: { keyword }
  })
}

