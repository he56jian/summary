<template>
	<!-- ref的属性可以帮助我们获取dom -->
	<div class="list" ref='wrapper'>
		<div>
			<div class="area">
				<!-- border-topbottom的类时在stylus这个模块里面的类型，使用的时候是根据&：after和&：before -->
				<div class="title border-topbottom">
					当前城市
				</div>
				<div class="button-list">
					<div class="button-wrapper">
						<div class="button">北京</div>
					</div>
				</div>
			</div>
			<div class="area">
				<div class="title border-topbottom">
					热门城市
				</div>
				<div class="button-list">
					<div class="button-wrapper" v-for="item of hot" :key="item.id">
						<div class="button ">{{item.name}}</div>
					</div>
				</div>
			</div>
			<!-- 对象的v-for,其第二个值为key值； -->
			<div class="area"
			 	v-for="(item,key) of cities"
			  	:key='key'
			  	:ref='key'
			  	>
				<div class="title border-topbottom">
					{{key}}
				</div>
				<div class="button-list">
					<div class="item-list" >
						<div class="item border-bottom"
						  v-for="innerItem of item"
						  :key="innerItem.id"
						  >{{innerItem.name}}</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import Bscroll from 'better-scroll'


export default {
	props:{
		hot:Array,
		cities:Object,
		letter:String
	},
  name: 'CityList',
  // 生命周期函数，会在页面dom挂载完毕后执行
  mounted(){
  	this.scroll = new Bscroll(this.$refs.wrapper)
  },
  //vue中自带的监听器
  watch:{
  	//监听letter的变化，一变化就执行letter
  	letter (){
  		if(this.letter){
  			//使用this.$refs[key],获取其对应元素
  			const element = this.$refs[this.letter][0]
  			this.scroll.scrollToElement(element)
  		}
  	}
  }
}
</script>
//stylus就是一个预编译器，就是把css样式写法变得更加简洁
// 在style中使用lang='stylus'表示，样式使用stylus的样式；
//添加了scoped表示只对当前的页面有效
<style lang="stylus" scoped>
	@import '~styles/varibles.styl'

	.border-topbottom
		&:before
			border-color:#ccc
		&:after
			border-color:#ccc
	.border-bottom
		&:before
			border-color:#ccc
		&:after
			border-color:#ccc
	.list
		overflow:hidden
		position:absolute
		top:1.55rem
		left:0
		right:0
		bottom:0
		.title
			line-height:.54rem
			background:#eee
			padding-left:.2rem
			color:#666
			font-size:.26rem
		.button-list
			overflow:hidden
			padding-right:.6rem
			.button-wrapper
				float:left
				width:33.33%
			.button
				margin:.1rem
				padding:.1rem 0
				text-align:center
				border:.02rem solid #ccc			
				border-radius:.06rem
		.item-list
			.item
				line-height:.76rem
				padding-left:.2rem
</style>
