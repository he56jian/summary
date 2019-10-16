<template>
  <!-- 返回的标签必须有一个模板包裹 -->
    <div>
    <!-- 传递给子组件其city变量为city的值 -->
      <city-header></city-header>
      <city-search :cities='cities'></city-search>
      <city-list :cities="cities" :hot='hotCities'
        :letter="letter"
      ></city-list>
      <!-- 执行了change,之后执行了handleLetterChange -->
      <city-alphabet :cities="cities"
        @change='handleLetterChange'
      ></city-alphabet>
    </div>
</template>

<script>
import axios from 'axios'
import CityHeader from './components/Header'
import CitySearch from './components/Search'
import CityList from './components/List'
import CityAlphabet from './components/Alphabet'


export default {
	//把homeHeader组件变成局部组件
  name: 'City',
  components:{
  	CityHeader,
    CitySearch,
    CityList,
    CityAlphabet
  },
  data () {
  	return {
  		cities:{},    //a，b，c,d城市的名字
      hotCities:[],  //存放热门城市
      letter:''   //这个是标识
  	}
  },
  methods:{
    getCityInfo(){
      axios.get('/api/city.json')
        .then(this.handleGetCityInfoSucc)
    },
    handleGetCityInfoSucc(res){
      res = res.data
      if(res.ret && res.data){
        const data = res.data
        this.cities = data.cities
        this.hotCities = data.hotCities
      }
    },
    handleLetterChange(letter){
      this.letter = letter
    }
  },
  // 会在页面dom元素挂载完成后执行
  mounted(){
  	this.getCityInfo()
  }
  
}
</script >

<style lang="stylus" scoped>

</style>
