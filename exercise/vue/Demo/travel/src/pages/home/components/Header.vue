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
		{{this.city}}
		<span class="iconfont">&#xe64a;</span>
	</div>
</router-link>
</div>
</template>

<script>
export default {
  name: 'HomeHeader',

  //父组件传递进来的city变量，定义其为字符串类型
  props:{
  	city:String
  }
}
</script>
//stylus就是一个预编译器，就是把css样式写法变得更加简洁
// 在style中使用lang='stylus'表示，样式使用stylus的样式；
//添加了scoped表示只对当前的页面有效
<style lang="stylus" scoped>
@import '~styles/varibles.styl'
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
		width:1.24rem
		float:right
		padding:0 .1rem
		text-align:center
		font-size:.3rem
		color:#fff
</style>
