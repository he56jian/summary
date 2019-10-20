// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import fastClick from 'fastClick'
import 'styles/reset.css'
import 'styles/border.css'
//因为每个页面都要引入，所以放到这个页面
import 'styles/iconfont.css'
import VueAwesomeSwiper from 'vue-awesome-swiper'
import store from './store/index'
// require styles
import 'swiper/dist/css/swiper.css'

Vue.use(VueAwesomeSwiper, /* { default global options } */)


Vue.config.productionTip = false
fastClick.attach(document.body)
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
