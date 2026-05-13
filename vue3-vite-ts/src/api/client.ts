import axios from 'axios'

const API_BASE_URL = 'http://localhost:8080/api/1/study'

const apiClient = axios.create({
  baseURL: API_BASE_URL,
  timeout: 10000
})

// 错误处理拦截器
apiClient.interceptors.response.use(
  response => response,
  error => {
    console.error('API Error:', error)
    return Promise.reject(error)
  }
)

export default apiClient

