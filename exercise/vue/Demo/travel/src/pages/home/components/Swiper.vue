<template>
<div class='wrapper'>
	<!-- 这里绑定了一个变量options，其结果为swiperOption;需要在组间里面定义一个变量swiperOption  -->
	<swiper :options="swiperOption" v-if="showSwiper">
    <!-- slides -->
    <swiper-slide v-for='item of list' :key="item.id">
        <img class="swiper-pagimg" :src="item.url">
    </swiper-slide>
    <!-- Optional controls -->
    <div class="swiper-pagination"  slot="pagination"></div>
  </swiper>
</div>
</template>
<script>
	//在子组件中定义数据时，子组件必须是一个函数；
export default{
	name:'HomeSwiper',
    props: {
        list:Array
    },
	data (){
		return {
			swiperOption:{
                // 这里存放的是下方原点的位置
                pagination:'.swiper-pagination',
                loop:true
                // loop表示轮播可以循环播放
            }
		}
	},
    computed:{
        showSwiper(){
            return this.list.length
        }
    }

}

</script>

<style lang='stylus' scoped>
// 因为其原点的样式swiper-pagination指向的是swiper组件里面的.swiper-pagination-bullet-active。在当前目录中直接使用.swiper-pagination-bullet-active会出现问题；要使用 >>> 来表示穿透，其wrapper下面所有只要出现了swiper-pagination-bullet-active样式的，都会执行，不会scoped限制；
.wrapper >>> .swiper-pagination-bullet-active
    background:#fff !important
.wrapper
    overflow:hidden
    width:100%
    height:0
    padding-bottom:30.25%
    .swiper-pagimg
        width:100%


</style>