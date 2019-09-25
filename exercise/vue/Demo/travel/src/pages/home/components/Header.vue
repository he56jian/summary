<template>
<div class="header">
<div class="header-left">返回</div>
<div class="header-input">输入城市、景点、游玩主题</div>
<div class="header-right">城市</div>
</div>
</template>

<script>
export default {
  name: 'HomeHeader'
}
</script>
// 在style中使用lang='stylus'表示，样式使用stylus的样式；
//添加了scoped表示只对当前的页面有效
<style lang="stylus" scoped>
.header
	display:flex
	line-height:.86rem
	background:#00bcd4
	color:#fff
	.header-left
		width:.64rem
		float:left
	.header-input
		flex:1
		margin:.12rem .2rem
		heitht:.64rem
		line-heitht:.64rem
		background:#fff
		border-radius:.1rem
		color:#ccc
	.header-right
		width:1.24rem
		float:right
		text-align:center
</style>
