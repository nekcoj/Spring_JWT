import Vue from "vue";
import Vuex from "vuex";
import {apiUrl} from "../_helpers"

import { account } from './account.module';

// Vue.config.productionTip = false;

Vue.use(Vuex);

export default new Vuex.Store({

  state: {
    authUser: "",
    categories:[],
    formdata: {},
    gdprConsent: null,
    issues: [],
    issuesLawyer: [],
    issueStatusUser: "",
    lawyers: [],
    postboxPost:{},
    messages: {},
    messageToSend: {},
    selectedCategory: {},
    sortDesc: Boolean,
    temporaryUser: {},
    tokenId: null,
    user:{},
 
  },

  mutations: {
    setCategories(state, value) {
      this.$store.state.categories = value
    },
    // setSelectedMonth(state,value){
    //   this.$store.state.selectedMonth = value
    // },
    
    deleteIssue(state, item){
      state.issues.splice(state.issues.indexOf(item),1)
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
    // setSelectedCategory(state, value) {
    //   this.$store.state.selectedCategory = value
    // },
    // setSearchField(state, value){
    //   this.$store.state.searchField = value
    // },
  },

  actions: {
    newIssue: async function(value) {
      let url =  apiUrl +"/issue/create";
      const response = await fetch(url, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(value.state.formdata),
      });
      const result = await response.json();
      this.state.temporaryUser = Object.assign({}, result);
    },

    login: async function(value) {
      let url = apiUrl + "/login";
      const request = await fetch(url, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(value),
      });
      const result = await request.json();
      return result;
    },

    async getCategories() {
      let response = await fetch(apiUrl + "/category/get-all");
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
      let url = apiUrl + "/issue/get-all";
      const response = await fetch(url, {
        method: "GET",
        headers: await this.dispatch('getAuthenticationHeader')
      });

      const result = await response.json();
      
      this.state.issues = result;
    },
    async getIssuesForLawyer() {
      let url = apiUrl + "/issue/get-all-lawyer";
      const response = await fetch(url, {
        method: "GET",
        headers: await this.dispatch('getAuthenticationHeader')
      });

      const result = await response.json();
      
      this.state.issuesLawyer = result;
    },

    async getLawyers() {
      let url = apiUrl +  "/user/lawyers";
      const response = await fetch(url, {
        method: "GET",
        headers: await this.dispatch('getAuthenticationHeader')
      });

      const result = await response.json();
      
      this.state.lawyers = result;
      
    },

     async deleteItem({commit}, item){
      let id = item.issueId;
      let active = !item.active;
      let tokenId = null;
      if(this.state.tokenId == null){
        let user = await JSON.parse(localStorage.getItem('user'));
        tokenId = user.token;
      } else
       {tokenId = this.state.tokenId}
         await fetch(apiUrl +  "/issue/active/"+id+"/"+active,  {
        method: "POST",
        headers: {'Authorization': 'Bearer ' + tokenId, 'Accept': 'application/json','Content-Type': 'application/json' }
      });
      /**här ska sedan vara en filtrering för att bara visa aktiva issues */
       commit('deleteIssue', item)
     },

    async getIssueStatusForUser() {
      let issueStatus = "";
      let url = apiUrl +  "/issue/user";
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
      let url = apiUrl + "/post/get-user";
      const response = await fetch(url, {
        method: "GET",
        headers: await this.dispatch('getAuthenticationHeader')
      });

      const result = await response.json();
      this.commit("setMessages", result)       
    },

    async sendReply() {
      let url = apiUrl +  "/post/reply";
      fetch(url, {
        method: "POST",
        headers: await this.dispatch('getAuthenticationHeader'),
        body: JSON.stringify(this.state.messageToSend)
      });
    },

    async updateGDPR() {
      let url = apiUrl +  "/user/gdpr-consent"

      var raw = JSON.stringify({"consent":true});

      var requestOptions = {
        method: 'POST',
        headers: await this.dispatch('getAuthenticationHeader'),
        body: raw,
      };

      fetch(url, requestOptions)
        .then(response => response.text())
        .then(result => console.log(result))
        .catch(error => console.log('error', error));
    }
  },

  modules: {
    account
  },

  created() {
    this.categories = []
  }

}
);
