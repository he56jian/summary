<template>
	<div>
		<div class="banner" @click='handleBannerClick'>
			<img src="//imgs.qunarzz.com/vs_ceph_vs_tts/5958b155-6345-4dc8-afea-881a83d90c60.jpg_r_480x320x90_c9422665.jpg" class="banner-img">
			<!-- <img src="//imgs.qunarzz.com/vs_ceph_vs_tts/67d500ae-a5c7-42a8-9ca8-2be5da8ba921.jpg_r_480x320x90_4c6597bf.jpg" class="banner-img">
			<img src="//imgs.qunarzz.com/p/tts8/1703/e2/149f578bbb9c7302.jpg_r_480x320x90_0df92e9d.jpg" class="banner-img">
			<img src="//imgs.qunarzz.com/p/tts1/1608/17/13c00ead103a8802.jpg_r_480x320x90_0a613ba0.jpg" class="banner-img"> -->

			<div class="banner-info">
				<div class="banner-title">深圳-海口 5天跟团游</div>
				<div class="banner-number "><span class="iconfont banner-icon">&#xe600;</span>39</div>
			</div>
		</div>
		<!-- 刚开始轮播图处于隐藏状态，再次让他显示，其宽度会出现问题 -->
		<common-gallary 
		:imags='imags' 
		v-show='showGallary'
		@close='handleGallaryClose'
		></common-gallary>
	</div>
</template>

<script type="text/javascript">

import CommonGallary from 'common/gallary/Gallary'

	export default{
		name:"DetailBanner",
		components:{
			CommonGallary
		},
		data(){
			return {
				imags:["//imgs.qunarzz.com/vs_ceph_vs_tts/5958b155-6345-4dc8-afea-881a83d90c60.jpg_r_480x320x90_c9422665.jpg",
						'//imgs.qunarzz.com/vs_ceph_vs_tts/67d500ae-a5c7-42a8-9ca8-2be5da8ba921.jpg_r_480x320x90_4c6597bf.jpg',
						'//imgs.qunarzz.com/p/tts8/1703/e2/149f578bbb9c7302.jpg_r_480x320x90_0df92e9d.jpg'],
				showGallary:false
					}
		},
		methods:{
			handleBannerClick(){
				this.showGallary = true;
			},
			handleGallaryClose(){
				this.showGallary = false;
			}
		}
	}

</script>

<style lang='stylus' scoped>
	.banner
		position:relative
		overflow:hidden
		heigth:0
		.banner-img
			width:100%
		.banner-info
			display:flex
			position:absolute
			left:0
			right:0
			bottom:0
			line-height:.6rem
			color:#fff
			background-image:linear-gradient(top,rgba(0,0,0,0),rgba(0,0,0,.8))
			.banner-title
				flex:1
			.banner-number
				padding:0 .2rem
				height:.32rem
				margin-top:.14rem
				line-height:.32rem
				border-radius:.2rem
				background:rgba(0,0,0,.8)
				font-size:.24rem
				.banner-icon
					font-size:.24rem
					padding:0 .1rem
</style>