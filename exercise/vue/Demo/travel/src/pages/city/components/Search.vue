<template>
	<div>
		<div class="search">
			<!-- v-model为双向绑定 -->
			<input v-model='keyword' type="text" placeholder="输入城市名或拼音" name="" 
			class="search_input">
		</div>
		<div class="search-content" ref='search'
			v-show='keyword'
		>
			<ul>
				<li class='search-item border-bottom' v-for='item of list' 
				:key='item.id'>{{item.name}}</li>
				<li class='search-item border-bottom' v-show='hasNoData'>
					没有找到匹配数据
				</li>
			</ul>
		</div>
	</div>
</template>

<script>
import Bscroll from 'better-scroll'
export default {
  name: 'CitySearch',
  props:{
  	cities:Object
  },
  data(){
  	return {
  		keyword:'',		//和输入的内容双向绑定
  		list:[],
  		timer:null
  	}
  },
  computed:{
  	hasNoData(){
  		return !this.list.length
  	}

  },

  watch:{
  		//监听keyword的改变
  		keyword(){
  			if(this.timer){
  				clearTimeout(this.timer)
  			}
  			if(!this.keyword){
  				this.list = []
  				return 
  			}
  			this.timer = setTimeout(()=>{
  				const result = []

  				for(let i in this.cities){
  					this.cities[i].forEach((value)=>{
  						//判断是否能在value中搜索到keyword关键词
  						if(value.spell.indexOf(this.keyword)>-1 ||
  							value.name.indexOf(this.keyword)>-1
  							){
  							result.push(value)
  						}
  					})
  				}
  				this.list = result
  			},100)
  		}
  },
  mounted (){
  	//Bscroll其构造函数要传一个元素或者选择器进来
  	this.scroll = new Bscroll(this.$refs.search)
  }
}
</script>
//stylus就是一个预编译器，就是把css样式写法变得更加简洁
// 在style中使用lang='stylus'表示，样式使用stylus的样式；
//添加了scoped表示只对当前的页面有效
<style lang="stylus" scoped>
	@import '~styles/varibles.styl'
	.search
		padding:0 .1rem
		height:.70rem
		background:$bgColor
		.search_input
			box-sizing:border-box
			padding:0 .2rem
			width:100%
			height:.62rem
			text-align:center
			border:none
			border-radius:.06rem
			color:#666
	.search-content
		z-index:1
		overflow:hidden
		position:absolute
		top:1.58rem
		left:0
		right:0
		bottom:0
		.search-item
			line-height:.63rem
			padding-left:.2rem
			background:#fff
			color:#666
</style>
