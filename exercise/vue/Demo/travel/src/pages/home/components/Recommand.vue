<template>
	<div>
		<div class='recommend-title'>热销推荐</div>
		<ul >

			<!-- <router-link to='/detail'> 这个相当于a标签，要添加成tag=‘li'把它当成li标签 -->
			<router-link 
			tag='li'
			:to="'/detail/' + item.id"
			class="recommend-item border-bottom" v-for="item of recommend" :key="item.id" >
				<img :src=item.img class="item-img">
				<div class="item-info">
					<p class="item-title">{{item.title}}</p>
					<p class="item-desc">{{item.desc}}</p>
					<button class="item-button">查看详情</button>
				</div>
			</router-link>
		</ul>
	</div>
</template>

<script type="text/javascript">
	
	export default{
		name:'HomeRecommand',
		props:{
			recommend:Array
		}
	}

</script>

<style type="text/css" lang='stylus' scoped>
@import '~styles/minins.styl'
	.recommend-title
		margin-top:.2rem
		margin-bottom:.1rem
		line-height:.8rem
		background:#eee
		text-indent:.2rem
	.recommend-item
		overflow:hidden
		display:flex
		height:1.9rem
		.item-img
			height:90%
			padding:.1rem .2rem
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
			.item-button
				line-height:.44rem
				margin-top:.16rem
				background:#ff9300
				border-radius:.06rem
				color:white
				padding:0 .12rem
</style>
