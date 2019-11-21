<template>
	<div >
		<div class="item" v-for='(item,index) of list' :key='index'>
			<div class="item-title border-bottom " >
				<span class="item-title-icon">
					<div class="iconfont">&#xe616;</div>
				</span>
				{{item.title}}
			</div>
			<div v-if='item.children' class="item-children">
				<detail-list :list='item.children'></detail-list>
			</div>
		</div>
	</div>
</template>

<script type="text/javascript">

	export default{
		name:"DetailList",
		props:{
			list:Array
		}
	}

</script>

<style lang='stylus' scoped>
	.item-title
		line-height:.8rem
		font-siez:.32rem
		padding:0 .32rem
		.item-title-icon
			position:relative
			right:.1rem
			top:.01rem
			display:inline-block
			width:.36rem
			height:.36rem
			text-align:center
			color:#666
			margin-right:.1rem
			background-size:.4rem 3rem
	.item-children
		padding:0 .32rem
	
</style>