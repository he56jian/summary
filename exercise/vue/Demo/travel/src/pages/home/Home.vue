<template>
<div>
	<!-- 传递给子组件其city变量为city的值 -->
<home-header></home-header>
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
import {mapState} from 'vuex'
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
      lastCity:'',
  		recommend:[],
  		iconItem:[],
  		swiperList:[],
  		weekend:[]
  	}
  },
  computed:{
    ...mapState(['city'])
  },
  methods:{
  	getHomeInfo(){
  		axios.get('/api/index.json?city='+this.city)
  			.then(this.getHomeInfoSucc)
  	},
  	getHomeInfoSucc(res){
  		res = res.data
  		if(res.ret && res.data){
  			const data = res.data
  			this.swiperList = data.swiperList
  			this.recommend = data.recommend
  			this.iconItem = data.iconItem
  			this.weekend = data.weekend

  		}
  	}
  },
  //每次加载页面的时候，数据都会被重新渲染，所以当前钩子会重复执行，就会多次请求
  mounted(){
  	this.getHomeInfo()
    this.lastCity = this.city
  },

  //在使用keep-alive标签的时候，生命周期函数中会多一个activated的函数
  activated(){
    //每次页面被显示的时候都执行
    if(this.lastCity !== this.city){
      this.lastCity = this.city
      this.getHomeInfo()
    }
  }
}
</script>

<style>

</style>
