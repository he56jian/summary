<template>
<div>
<home-header></home-header>
</div>
</template>

<script>
import HomeHeader from './components/Header'
export default {
	//把homeHeader组件变成局部组件
  name: 'Home',
  components:{
  	HomeHeader
  }
}
</script>

<style>

</style>
