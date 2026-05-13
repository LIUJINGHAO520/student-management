import axios from 'axios'

/**
 * 处理 API 错误并返回用户友好的错误信息
 */
export const handleApiError = (error: any): string => {
  let errorMsg = "操作失败"

  if (error.response) {
    // 服务器响应了错误状态码
    errorMsg = `服务器错误: ${error.response.status} - ${error.response.data?.message || "未知错误"}`
  } else if (error.request) {
    // 请求已发出但没有收到响应
    errorMsg = "服务器无响应，请检查后端服务是否启动"
  } else if (axios.isAxiosError(error)) {
    // Axios 错误
    errorMsg = `请求错误: ${error.message}`
  } else {
    // 其他错误
    errorMsg = `错误: ${error.message || "未知错误"}`
  }

  console.error('Error Details:', error)
  return errorMsg
}

/**
 * 显示错误提示
 */
export const showError = (error: any, defaultMessage: string = "操作失败") => {
  const errorMsg = handleApiError(error) || defaultMessage
  alert(errorMsg)
}

/**
 * 显示成功提示
 */
export const showSuccess = (message: string = "操作成功") => {
  alert(message)
}

