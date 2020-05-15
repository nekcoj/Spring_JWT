import Vue from "vue";
import Vuex from "vuex";

// Vue.config.productionTip = false;

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    formdata: {},
    temporaryUser: {},
    catagories:[],
    selectedCategory: 0
  },
  mutations: {
    setTempUser(state, value) {
      this.$store.state.temporaryUser = value;
    },

    setCategory(state, value) {
      this.$store.state.catagory = value;
    },

    setCategories(state, value) {
      this.$store.state.catagories = value;
    },

    setselectedCetagory(state, value) {
      state.selectedCatagory = value;
    },
  },
  actions: {
    newIssue: async function(value) {
      let url = "http://localhost:9090/send";
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

    async getCategories({ commit }) {
      let response = await fetch("localhost:9090/category");
      response = await response.json();
      commit("setCategory", response);
    },
  },
  modules: {},
});
