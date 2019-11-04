<template>
	<div>
		<router-link tag='div' to='/' class="header-abs" v-show='showAbs'>
			<div class="iconfont header-abs-back">&#xe624;</div>
		</router-link>
		<div class="header-fixed" v-show='!showAbs'
			:style='opacityStyle'
		>
			<router-link to="/">
				<div class="iconfont header-fixed-back" >&#xe624;</div>
			</router-link>
			景点详情
		</div>
	</div>
</template>

<script type="text/javascript">
	export default{
		name:'DetailHeader',
		
		data(){
			return {
				showAbs:true,
				opacityStyle:{
					opcity:0
				}
			}
		},
		methods:{
			handleScroll(){
				const top = document.documentElement.scrollTop
				if(top > 60 ){
					this.showAbs = false
					let opacity = top / 140 
					opacity = opacity > 1 ?1:opacity
					this.opacityStyle ={opacity} 
				}else{
					this.showAbs = true
				}
			}
		},
		activated () {
			//页面一被展示，就执行
			window.addEventListener('scroll',this.handleScroll)
		},
		deactivated(){
			//当页面被隐藏或者即将被关闭前执行
			window.removeEventListener('scroll',this.handleScroll)
			//解除绑定，防止去其他的页面也触发该事件
		}
	}
</script>

<style lang='stylus' scoped>
	@import '~styles/varibles.styl'
	.header-abs
		position:absolute
		left:.2rem
		top:.2rem
		width:.8rem
		height:.8rem
		line-height:.8rem 
		border-radius:.4rem
		background:rgba(0,0,0,0.8)
		text-align:center
		.header-abs-back
			color:#fff
			font-size:.4rem
	.header-fixed
		z-index:2
		position:fixed
		top:0
		left:0
		right:0
		height:.86rem
		line-height:.86rem
		text-align:center
		color:#fff
		background:$bgColor
		font-size:.32rem
		.header-fixed-back
			position:absolute
			top:0
			left:0
			width:.64rem
			text-align:center
			font-size:.4rem
			color:#fff
</style>