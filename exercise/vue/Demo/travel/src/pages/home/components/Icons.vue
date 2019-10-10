<template>
	<div class="icons">
		<swiper :options="swiperOption" >
			<swiper-slide v-for="page of pages" :key="page.id">
			    <div class="icon" v-for="item of page" :key="item.id">
					<div class="icon-img">
						<img class='icon-img-content' :src="item.src">
					</div>
					<p class="icon-desc">{{item.text}}</p> 
				</div>
			</swiper-slide>
			<div class="swiper-pagination"  slot="pagination"></div>
 		</swiper>
	</div>
</template>

<script type="text/javascript">
	
export default{
	name:'HomeIcons',
	props:{
		iconItem:Array
	},
	data (){
		return {
			swiperOption:{
                // 这里存放的是下方原点的位置
                pagination:'.swiper-pagination',
                autoplay:false			//轮播图不自动滚动
            }
		}
	},
	// 计算属性，用于计算
	computed:{
		pages (){
			const pages = []
			this.iconItem.forEach((item,index)=>{

				//前面8个的page都为1，即pages[1]保存了前八个item;
				const page = Math.floor(index/8)
				if(!pages[page]){
					pages[page]=[]
				}
				pages[page].push(item)
			})
			return pages
			//返回的是[[{1},{2},{3}...{8}],[{1}]
		}
	}
}
</script>

<style type="text/css" lang="stylus" scoped>
	@import '~styles/minins.styl'
	@import '~styles/varibles.styl'
	.icons
		height:0
		padding-bottom:50%
		overflow:hidden
		margin-top:.1rem
		.icon
			position:relative
			float:left
			width:25%
			height:0
			padding-bottom:25%
			.icon-img
				position:absolute
				top:0
				left:0
				right:0
				bottom:.44rem
				text-align:center
				box-sizing:border-box
				padding:.1rem 
				.icon-img-content
					display:block
					margin:0 auto
					height:100%
			.icon-desc
				position:absolute
				text-align:center
				left:0
				right:0
				bottom:0
				height:.44rem
				line-height:.44rem
				color:$darkTextColor 
				ellipsis()
</style>