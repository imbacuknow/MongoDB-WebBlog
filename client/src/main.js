import Vue from 'vue'
import App from './App.vue'
import router from './router'
import vuetify from './plugins/vuetify';

Vue.config.productionTip = false

new Vue({
  // inject the router to make whole app router-aware
  router,

  vuetify,
  render: h => h(App)
}).$mount('#app')
