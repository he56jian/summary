<template>
<div>
	<!-- 传递给子组件其city变量为city的值 -->
<home-header :city="city"></home-header>
<home-swiper :list="swiperList"></home-swiper>
<home-icons :iconItem="iconItem"></home-icons>
<home-recommand :recommend="recommend"></home-recommand>
<home-weekend :weekend="weekend"></home-weekend>
</div>
</template>

<script>
import HomeHeader from './components/Header'
import HomeSwiper from './components/Swiper'
import HomeIcons from './components/Icons'
import HomeRecommand from './components/Recommand'
import HomeWeekend from './components/Weekend'
import axios from 'axios'
export default {
	//把homeHeader组件变成局部组件
  name: 'Home',
  components:{
  	HomeHeader,
  	HomeSwiper,
  	HomeIcons,
  	HomeRecommand,
  	HomeWeekend
  },
  data () {
  	return {
  		city:'',
  		recommend:[],
  		iconItem:[],
  		swiperList:[],
  		weekend:[]
  	}
  },
  methods:{
  	getHomeInfo(){
  		axios.get('/api/index.json')
  			.then(this.getHomeInfoSucc)
  	},
  	getHomeInfoSucc(res){
  		res = res.data
  		if(res.ret && res.data){
  			const data = res.data
  			this.city = data.city
  			this.swiperList = data.swiperList
  			this.recommend = data.recommend
  			this.iconItem = data.iconItem
  			this.weekend = data.weekend

  		}
  		console.log(res)
  	}
  },
  mounted(){
  	this.getHomeInfo()
  }
}
</script>

<style>

</style>
