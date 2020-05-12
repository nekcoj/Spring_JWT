import Vue from "vue";
import Vuex from "vuex";
import { library } from "@fortawesome/fontawesome-svg-core";
import { faUserSecret, faSearch } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";

library.add(faUserSecret, faSearch);

Vue.component("font-awesome-icon", FontAwesomeIcon);

Vue.config.productionTip = false;

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    formdata: {},
    temporaryUser: {},
  },
  mutations: {
    setTempUser(state, value) {
      this.$store.state.temporaryUser = value;
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
  },
  modules: {},
});
