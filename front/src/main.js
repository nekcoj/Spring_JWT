import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import { DropdownPlugin, TablePlugin, FormPlugin, FormInputPlugin, FormSelectPlugin,
  FormCheckboxPlugin, FormDatepickerPlugin, FormGroupPlugin, InputGroupPlugin , ButtonPlugin, 
  CalendarPlugin} from 'bootstrap-vue'
  Vue.use(FormPlugin)
  Vue.use(FormGroupPlugin)
  Vue.use(FormDatepickerPlugin)
  Vue.use(FormCheckboxPlugin)
  Vue.use(FormSelectPlugin)
  Vue.use(FormInputPlugin)
  Vue.use(InputGroupPlugin)
  Vue.use(ButtonPlugin)
  Vue.use(DropdownPlugin)
  Vue.use(TablePlugin)
  Vue.use(CalendarPlugin)
  
Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
