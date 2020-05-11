import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    formdata: {},
    temporaryUser: {}
  },
  mutations: {
    setTempUser(state, value){
      this.$store.state.temporaryUser = value;
    }
  },
  actions: {
    newIssue: async function(value){
      let url = "http://localhost:9090/send"
      console.log("store newIssue: "+JSON.stringify(value.state.formdata));

      const response = await fetch(url, {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(value.state.formdata)
      })    
      const result = await response.json();
      this.state.temporaryUser = Object.assign({}, result);      
    }
  },
  modules: {
  }
})
