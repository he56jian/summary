<template>
	<div class="search">
		<input type="text" placeholder="输入城市名或拼音" name="" class="search_input">
	</div>
</template>

<script>
export default {
  name: 'CitySearch',
}
</script>
//stylus就是一个预编译器，就是把css样式写法变得更加简洁
// 在style中使用lang='stylus'表示，样式使用stylus的样式；
//添加了scoped表示只对当前的页面有效
<style lang="stylus" scoped>
	@import '~styles/varibles.styl'
	.search
		padding:0 .1rem
		height:.82rem
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


</style>
