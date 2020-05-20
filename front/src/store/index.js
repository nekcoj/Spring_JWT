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
    // selectedCategory: {},
    // selectedMonth:{},
    // searchField: "",
    tokenId: null,
    authUser: "",
    user:{},
    issues: [],
    lawyers: [],
    postboxPost:{},
    sortDesc: Boolean,
    
 
  },
  mutations: {
    setTempUser(state, value) {
      this.$store.state.temporaryUser = value;
    },

    setCategories(state, value) {
      this.$store.state.categories = value
    },
    // setSelectedMonth(state,value){
    //   this.$store.state.selectedMonth = value
    // },

    setselectedCategory(state, value) {
      this.$store.state.selectedCategory = value;
    },
    deleteIssue(state, item){
      state.issues.splice(
        state.issues.indexOf(item),1
      )
    }
    // setSelectedCategory(state, value) {
    //   this.$store.state.selectedCategory = value
    // },
    // setSearchField(state, value){
    //   this.$store.state.searchField = value
    // },
  },
  actions: {
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
    },
    
    getAuthenticationHeader: function(){
      let tokenId = null;
      if(this.state.tokenId == null){
        let user = JSON.parse(localStorage.getItem('user'));
        tokenId = user.token;
      } else {tokenId = this.state.tokenId}

      return { 'Authorization': 'Bearer ' + tokenId, 'Content-Type': 'application/json' }
    },


    async getIssues() {
      let url = "http://localhost:9090/issue/get-all";
      const response = await fetch(url, {
        method: "GET",
        headers: await this.dispatch('getAuthenticationHeader')
      });

      const result = await response.json();
      
      this.state.issues = result;
    },

    async getLawyers() {
      let url = "http://localhost:9090/user/lawyers";
      const response = await fetch(url, {
        method: "GET",
        headers: await this.dispatch('getAuthenticationHeader')
      });

      const result = await response.json();
      
      this.state.lawyers = result;
      console.log("lawyers: " + this.state.lawyers);
    },

    /*Ej färdig, men tänker mig att det blir något sådant för backend */
    async deleteIssue({commit}, item){
      let response = await fetch("http://localhost:9090/issue/inactivate/${issueId}");
      response = await response.json();
      this.state.issues = Object.assign({}, response);
      commit('deleteIssue', item)
    }
  },
  modules: {
    account
  },
  created() {
    this.categories = []
  }
});
