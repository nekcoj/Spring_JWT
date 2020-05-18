import Vue from "vue";
import Vuex from "vuex";

import { account } from './account.module';

// Vue.config.productionTip = false;

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    formdata: {},
    temporaryUser: {},
    categories:[],
    selectedCategory: {},
    tokenId: null,
    user:{},
    issues: {},
    lawyers: []
 
  },
  mutations: {
    setTempUser(state, value) {
      this.$store.state.temporaryUser = value;
    },

    setCategories(state, value) {
      this.$store.state.categories = value;
    },

    setselectedCategory(state, value) {
      this.$store.state.selectedCategory = value;
    },
  },
  actions: {
    newIssue: async function(value) {
      let url = "http://localhost:9090/issue/create";
      const response = await fetch(url, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(value.state.formdata),
      });
      const result = await response.json();
      this.state.temporaryUser = Object.assign({}, result);
    },
    login: async function(value) {
      let url = "http://localhost:9090/login";
      const request = await fetch(url, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(value),
      });
      const result = await request.json();
      return result;
    },

    async getCategories() {
      let response = await fetch("http://localhost:9090/category/get-all");
      response = await response.json();
      this.state.categories = Object.assign({}, response);
      console.log("categories: ",response)
    },
    
    getAuthenticationHeader: function(){
      let tokenId = null;
      if(this.state.tokenId == null){
        let user = JSON.parse(localStorage.getItem('user'));
        tokenId = user.token;
      } else {tokenId = this.state.tokenId}

      let header = "'Authorization': 'Bearer "
      header += tokenId;
      header += ", 'Content-Type': 'application/json'"
      console.log("header in getAuthHeader: " + header);
      
      return header
    },


    async getIssues() {
      const header = await this.dispatch('getAuthenticationHeader');
      console.log("header  :"+header);
      
      let url = "http://localhost:9090/issue/get-all";
      const response = await fetch(url, {
        method: "GET",
        headers: {header}
      });

      const result = await response.json();
      if(result.ok){
        this.state.issues = Object.assign({}, result);
        console.log("Issues fetched!");
      } else {       
        Array(result).forEach((element) => {
          console.log(element);
      });}
      
    },



    async getLawyers() {
      console.log('försöker hämta alla lawyers. ')
      let response = await fetch('http://localhost:9090/user/lawyers')
      let lawyers = await response.json()
      console.log('såhär gick det: lawyers ',lawyers)
      this.state.lawyers = Object.assign({}, lawyers)
    }


  },
  modules: {
    account
}
});
