<template>
  <!-- 单个组件的动画 ，vue会在固定的时间点上往tansition中插入class名字，借助该class实现动画-->
  <transition>
    <!-- 插槽 -->
      <slot></slot>
  </transition>
</template>

<script>

export default {
	//把homeHeader组件变成局部组件
  name: 'Fade',
  
  
}
</script >

<style lang="stylus" scoped>
  .v-enter,.v-leave-to
    opacity:0
  .v-enter-active,.v-leave-active
    transition:opacity .8s
</style>
