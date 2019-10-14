<template>
<div class="header">
	城市选择
	<router-link to="/">
		<div class="iconfont header-back" >&#xe624;</div>
	</router-link>
</div>
</template>

<script>
export default {
  name: 'CityHeader',
}
</script>
//stylus就是一个预编译器，就是把css样式写法变得更加简洁
// 在style中使用lang='stylus'表示，样式使用stylus的样式；
//添加了scoped表示只对当前的页面有效
<style lang="stylus" scoped>
	@import '~styles/varibles.styl'
	.header
		position:relative
		overflow:hidden
		height:.86rem
		line-height:.86rem
		text-align:center
		color:#fff
		background:$bgColor
		font-size:.32rem
		.header-back
			position:absolute
			top:0
			left:0
			width:.64rem
			text-align:center
			font-size:.4rem
			color:#fff
</style>
