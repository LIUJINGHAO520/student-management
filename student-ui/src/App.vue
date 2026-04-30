<template>
  <!-- 这个 router-view 是路由容器，登录页和成绩页都会显示在这里 -->
  <router-view></router-view>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
/* 全局样式，确保背景铺满 */
body, html {
  margin: 0;
  padding: 0;
  height: 100%;
}
</style>