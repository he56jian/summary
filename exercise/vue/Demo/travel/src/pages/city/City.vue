<template>
  <!-- 返回的标签必须有一个模板包裹 -->
    <div>
    <!-- 传递给子组件其city变量为city的值 -->
      <city-header></city-header>
      <city-search></city-search>
    </div>
</template>

<script>
import CityHeader from './components/Header'
import CitySearch from './components/Search'
export default {
	//把homeHeader组件变成局部组件
  name: 'City',
  components:{
  	CityHeader,
    CitySearch
  },
  data () {
  	return {
  		
  	}
  },
  methods:{
  	
  },
  
}
</script >

<style lang="stylus" scoped>

</style>
