<template>
	<div>
		<div class='weekend-title'>周末去哪儿</div>
		<ul >
			<li class="weekend-item border-bottom" v-for="item of weekend" :key="item.id" >
				<div class="item-img-wrapper">
					<img :src=item.img class="item-img">
				</div>
				
				<div class="item-info">
					<p class="item-title">{{item.title}}</p>
					<p class="item-desc">{{item.desc}}</p>
				</div>
			</li>
		</ul> 
	</div>
</template>

<script type="text/javascript">
	export default{
		name:'HomeWeekend',
		props:{
			weekend:Array
		}
	}

</script>

<style type="text/css" lang='stylus' scoped>
@import '~styles/minins.styl'
	.weekend-title
		margin-top:.1rem
		margin-bottom:.1rem
		line-height:.8rem
		background:#eee
		text-indent:.2rem
	.weekend-item
		padding-bottom:1%
	.item-img-wrapper
		height:50%
		overflow:hidden
		.item-img
			width:100%
	.item-info
		flex:1
		padding:.1rem
		min-width:0
		.item-title
			line-height:.54rem
			font-size:.32rem
			ellipsis()
		.item-desc
			ellipsis()
			line-height:.4rem
			color:#ccc
</style>
