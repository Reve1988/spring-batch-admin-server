import Vue from 'vue'
import VueMoment from 'vue-moment'
import App from './App'

Vue.use(VueMoment);
new Vue({
  el: '#app',
  components: {App},
  template: '<App/>',
});
