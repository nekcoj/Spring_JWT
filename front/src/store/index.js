import Vue from "vue";
import Vuex from "vuex";

import { account } from './account.module';

// Vue.config.productionTip = false;

Vue.use(Vuex);

export default new Vuex.Store({

  state: {
    authUser: "",
    categories:[],
    formdata: {},
    gdprConsent: false,
    issues: [],
    issueStatusUser: "",
    lawyers: [],
    messages: {},
    messageToSend: {},
    selectedCategory: {},
    temporaryUser: {},
    tokenId: null,
    user:{}
  },

  mutations: {
    setCategories(state, value) {
      this.$store.state.categories = value;
    },

    setGDPRConsent(state, value){
      this.state.gdprConsent = value;
    },

    setIssueStatusForUser(state, value){
      this.state.issueStatusUser = value;
    },

    setMessages(state, value){
      this.state.messages = value;
    },

    setMessageBody(state, value){
      this.state.messageToSend = value;
    },

    setselectedCategory(state, value) {
      this.$store.state.selectedCategory = value;
    },

    setTempUser(state, value) {
      this.$store.state.temporaryUser = value;
    }
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

      return { 'Authorization': 'Bearer ' + tokenId, 'Content-Type': 'application/json' }
    },

    async getIssues() {
      let url = "http://localhost:9090/issue/get-all";
      const response = await fetch(url, {
        method: "GET",
        headers: await this.dispatch('getAuthenticationHeader')
      });

      const result = await response.json();
      if(!result.ok){       
        Array(result).forEach((element) => {
          console.log(element);
          return;
      });}
      this.state.issues = result;
      console.log("issues: " + this.state.issues);
    },

    async getLawyers() {
      let url = "http://localhost:9090/user/lawyers";
      const response = await fetch(url, {
        method: "GET",
        headers: await this.dispatch('getAuthenticationHeader')
      });

      const result = await response.json();
      if(!result.ok){       
        Array(result).forEach((element) => {
          console.log(element);
          return;
      });}
      this.state.lawyers = result;
      console.log("lawyers: " + this.state.lawyers);
    },

    async getIssueStatusForUser() {
      let issueStatus = "";
      let url = "http://localhost:9090/issue/user";
      const response = await fetch(url, {
        method: "GET",
        headers: await this.dispatch('getAuthenticationHeader')
      });
      
      await response.text().then( function(text){
        issueStatus = text;
      });
      this.commit("setIssueStatusForUser", issueStatus);
    },

    async getMessages() {
      let url = "http://localhost:9090/post/get-user";
      const response = await fetch(url, {
        method: "GET",
        headers: await this.dispatch('getAuthenticationHeader')
      });

      const result = await response.json();
      this.commit("setMessages", result)       
    },

    async sendReply() {
      let url = "http://localhost:9090/post/reply";
      fetch(url, {
        method: "POST",
        headers: await this.dispatch('getAuthenticationHeader'),
        body: JSON.stringify(this.state.messageToSend)
      });
    }

  },

  modules: {
    account
  }
});
