<template>
  <!-- 返回的标签必须有一个模板包裹 -->
  <ul class="list">
    <li class="item" v-for='item of letters' :key="item"
    :ref='item'
    @click="handleLetterClick"
    @touchstart='handleTouchStart'
    @touchmove='handleTouchMove'
    @touchend='handleTouchEnd'
    >{{item}}</li>
  </ul>
</template>

<script>

export default {
  props:{
    cities:Object
  },
  computed:{
    letters(){
      const letters = []
      for (let i in this.cities) {
        letters.push(i)
      }
       return letters
       // [a,b,c,d]
    }
  },
  data(){
    return {
      touchStatus:false,
      startY:0,
      timer:null
    }
  },
  //在当前页面的cities修改时，执行
  //在页面的数据更新的时候，同时页面完成了渲染后执行
  //当前组件重新渲染之后，updated被执行
  updated (){
    this.startY = this.$refs['A'][0].offsetTop  //获取A距离顶部的长度
  },

	//把homeHeader组件变成局部组件
  name: 'CityAlphabet',
  methods:{
    handleLetterClick(e){
      this.$emit('change',e.target.innerHTML) //执行了change事件，
    },
    handleTouchStart(){
      this.touchStatus = true
    },
    handleTouchMove(e){
      if(this.touchStatus){

        if(this.timer){
          clearTimeout(this.timer)
        }
        this.timer = setTimeout(()=>{
     //获取手指的距离最顶的高度 - 像素
            const touchY = e.touches[0].clientY - 78
            //20是每个字母的高度
            const index = Math.floor((touchY - this.startY)/20)
            if(index >= 0 && index < this.letters.length){
              this.$emit('change',this.letters[index])
            }
        },15)
      }
    },
    handleTouchEnd(){
      this.touchStatus = false
    }
  }

}
</script >

<style lang="stylus" scoped>
  @import '~styles/varibles.styl'
  .list
    display:flex
    flex-direction:column
    justify-content:center
    position:absolute
    top:1.58rem
    right:0
    bottom:0
    width:.4rem
    .item
      line-height:.4rem
      text-align:center
      color:$bgColor
</style>
