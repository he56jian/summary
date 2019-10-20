import Vue from 'vue'
import Vuex from 'vuex'
import state from './state'
import mutations from './mutations'
Vue.use(Vuex)

export default new Vuex.Store({
	state,
	actions:{
		changeCity (ctx,city){
			//执行mutations中的函数；调用mutations函数
			ctx.commit('changeCity',city)
		}
	},
	mutations,
	//根据state中的数据算出新的数据的时候，就可以使用getters来进行处理
	getters:{
		doubleCity(state){
			//
			return 
		}
	},
	module:{
		//复杂数据的拆分
	}
})