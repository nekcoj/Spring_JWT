import Vue from "vue";
import Vuex from "vuex";

// Vue.config.productionTip = false;

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    formdata: {},
    temporaryUser: {},
    categories:[],
    selectedCategory: {},
    tokenId: "",
    getIssues: {},
 
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
    },
    getAuthenticationHeader: function(){
      return { 'Authorization': 'Bearer ' + this.state.tokenId,
        'Content-Type': 'application/json' }
    },
    async getIssues() {
      let response = await fetch("http://localhost:9090/issue/get-all");
      response = await response.json();
      this.state.categories = Object.assign({}, response);
    },


  },
  modules: {},
});
