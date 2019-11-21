<template>
  <div id="app">
  	<!-- vue自带的组件，路由内容被加载过一次之后，就放到内存中，再次重新加载时，会直接从内存中调取数据，不会重新发起请求;exclude表示不被缓存，每次进入detail都会重新加载 -->
  	<keep-alive exclude='Detail'>
    	<router-view/>
    </keep-alive>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>

</style>
