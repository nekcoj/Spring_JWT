import Vue from "vue";
import Vuex from "vuex";

// Vue.config.productionTip = false;

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    formdata: {},
    temporaryUser: {},
    categories:[],
    selectedCategory: {}
    
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
    login: async function(username, password) {
      let url = "http://localhost:9090/login";
      const requestOptions = {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(username, password),
      };
      return await fetch(url, requestOptions);
    },

    async getCategories() {
      let response = await fetch("http://localhost:9090/category/get-all");
      response = await response.json();
      this.state.categories = Object.assign({}, response);
      
      
    },
  },
  modules: {},
});
