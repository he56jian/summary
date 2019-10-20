<template>
<div class="header">
<div class="header-left">
	<div class="iconfont back-icon">&#xe624;</div>
</div>
<div class="header-input">
	<span class="iconfont search">&#xe632;</span>
输入城市、景点、游玩主题</div>
<!-- 用于内置的页面跳转组件，d点击该组件，跳转到to属性值的地址 -->
<router-link to='/city'>
	<div class="header-right">
		<!-- 获取其vuex中的store中的state公用数据的city -->
			{{this.city}}
		<span class="iconfont">&#xe64a;</span>
	</div>
</router-link>
</div>
</template>

<script>
import {mapState} from 'vuex'

export default {
  name: 'HomeHeader',
  computed:{
  	//把mapState里的数据映射到当前组件的computed的计算属性中；已当前格式书写就是把city映射到city之中
  	...mapState(['city'])
  }
}
</script>
//stylus就是一个预编译器，就是把css样式写法变得更加简洁
// 在style中使用lang='stylus'表示，样式使用stylus的样式；
//添加了scoped表示只对当前的页面有效
<style lang="stylus" scoped>
@import '~styles/varibles.styl'
@import '~styles/minins.styl'
.header
	display:flex
	// padding:.12rem 0
	line-height:.86rem
	background:$bgColor
	color:#fff

	.header-left
		width:.64rem
		padding:0 .1rem
		float:left
		.back-icon
			text-align:center
			font-size:.3rem
	.header-input
		flex:1
		height:.64rem
		margin:auto
		line-height:.64rem
		background:#fff
		border-radius:.1rem
		color:#ccc
		.search 
			padding:0 .1rem
	.header-right
		min-width:1.04rem
		float:right
		padding:0 .1rem
		text-align:center
		font-size:.3rem
		color:#fff
		.arrow-icon
			margin-left:-.04rem
			font-size:.24rem
</style>
