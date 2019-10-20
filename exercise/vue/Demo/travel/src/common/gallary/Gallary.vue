<template>
	<div class="container" @click="handleGallaryClick">
		<div class="wrapper">
			<swiper :options="swiperOption">
		    <swiper-slide v-for='(item,index) in imgs' :key='index'>
		       <img class='gallary-img' :src="item">
		    </swiper-slide>
		    <div class="swiper-pagination"  slot="pagination"></div>
		  </swiper>
		</div>
	</div>
</template>
<script type="text/javascript">
	export default{
		name:'CommonGallary',
		props:{
			imgs:{
				type:Array,
				default(){
					return [
						"//imgs.qunarzz.com/vs_ceph_vs_tts/5958b155-6345-4dc8-afea-881a83d90c60.jpg_r_480x320x90_c9422665.jpg",
						'//imgs.qunarzz.com/vs_ceph_vs_tts/67d500ae-a5c7-42a8-9ca8-2be5da8ba921.jpg_r_480x320x90_4c6597bf.jpg',
						'//imgs.qunarzz.com/p/tts8/1703/e2/149f578bbb9c7302.jpg_r_480x320x90_0df92e9d.jpg'
					]
				}
			}
		},
		methods:{
			handleGallaryClick(){
				this.$emit('close')
			}
		},
		data(){
			return {
				swiperOption:{
					pagination:".swiper-pagination",
					paginationType:'fraction',
					observeParents:true,
					observer:true			//dom元素在出现变化的时候，自我刷新一次
				}
			}
		}
	}
</script>

<style type="text/css" lang='stylus' scoped>
	.container >>> .swiper-container
		overflow:inherit
	.container
		display:flex
		flex-direction:column
		justify-content:center
		z-index:99
		position:fixed
		left:0
		right:0
		bottom:0
		top:0
		background:#000
		.wrapper
			width:100%
			height:0
			padding-bottom:100%
			.gallary-img
				width:100%
			.swiper-pagination
				color:#fff
				bottom:-1rem
</style>	
