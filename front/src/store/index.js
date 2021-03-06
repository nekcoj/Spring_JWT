import Vue from "vue"
import Vuex from "vuex"

import { account } from './account.module';
import {apiUrl} from '../_helpers/config.js'

// Vue.config.productionTip = false;

Vue.use(Vuex)

export default new Vuex.Store({

  state: {
    addRemoveCategory: null,
    authUser: "",
    categories: [],
    changeStatus: {},
    fetchResponse: "",
    fileToDownload: {},
    formdata: {},
    gdprConsent: null,
    issues: [],
    issuesLawyer: [],
    issueStatusUser: "",
    issueToAssign: {},
    issueToChangeCategoryFor: {},
    item: {},
    lawyers: [],
    postboxPost: {},
    messages: {},
    messageToSend: {},
    newCategory: {},
    selectedCategory: {},
    sortDesc: Boolean,
    statuses: [],
    temporaryUser: {},
    tokenId: null,
    user: {},

  },

  mutations: {
    setItem(state, value){
      this.state.item = value
    },
    setCategories(state, value) {
      this.state.categories = value
    },
    // setSelectedMonth(state,value){
    //   this.$store.state.selectedMonth = value
    // },

    deleteIssue(state, item) {
      state.issues.splice(state.issues.indexOf(item), 1)
    },

    setGDPRConsent(state, value) {
      this.state.gdprConsent = value
    },

    setIssueStatusForUser(state, value) {
      this.state.issueStatusUser = value
    },
    setIssueToChangeCategoryFor(state,value) {
      this.state.issueToChangeCategoryFor = value
    },
    setMessages(state, value) {
      this.state.messages = value
    },

    setMessageBody(state, value) {
      this.state.messageToSend = value
    },
    setNewCategory(state,value){
      this.state.newCategory = value
    },

    setselectedCategory(state, value) {
      this.$store.state.selectedCategory = value
    },

    setTempUser(state, value) {
      this.$store.state.temporaryUser = value
    },

    setIssueToAssign(state, value) {
      state.issueToAssign = value
    },

    setFetchResponse(state, value){
      state.fetchResponse = value;
    },

    setChangeStatusBody(state, value){
      state.changeStatus = value;
    },

    setFileToDownload(state, value){
      state.fileToDownload = value;
    },

    setCategoryToAddRemove(state, value){
      state.addRemoveCategory = value;
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
      let url = `${apiUrl}/issue/create`;
      const response = await fetch(url, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(value.state.formdata),
      })
      const result = await response.json()
      this.state.temporaryUser = Object.assign({}, result)
    },
    assignIssue: async function () {
      let val = JSON.stringify(this.state.issueToAssign)
      let url = `${apiUrl}/issue/assign`
      await fetch(url, {
        method: "POST",
        headers: await this.dispatch('getAuthenticationHeader'),
        body: val
      })

    
    },
    
    async issueChangeCategory(){
        
        let issueId = this.state.issueToChangeCategoryFor.issueId
      
        //val.categoryId = this.newCategory
        let url = `${apiUrl}/issue/change-category/`
        let newCategory = this.state.newCategory
        
        await fetch(url + issueId + "/" + newCategory,{
        method:"POST",
        headers: await this.dispatch('getAuthenticationHeader')
      })
    },

    login: async function(value) {
      let url = `${apiUrl}/login`;
      const request = await fetch(url, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(value),
      })
      const result = await request.json()
      return result
    },

    async getCategories() {
      let response = await fetch(`${apiUrl}/category/get-all`);
      response = await response.json();
      this.state.categories = Object.assign({}, response);
    },

    async getStatuses() {
      let url = `${apiUrl}/issue-status/get-all`;
      const response = await fetch(url, {
        method: "GET",
        headers: await this.dispatch('getAuthenticationHeader')
      })
      let result = await response.json();
      this.state.statuses = Object.assign({}, result);
    },

    getAuthenticationHeader: function () {
      let tokenId = null
      if (this.state.tokenId == null) {
        let user = JSON.parse(localStorage.getItem('user'))
        tokenId = user.token
      } else { tokenId = this.state.tokenId }

      return { 'Authorization': 'Bearer ' + tokenId, 'Content-Type': 'application/json' }
    },


    async getIssues() {
      let url = `${apiUrl}/issue/get-all`;
      const response = await fetch(url, {
        method: "GET",
        headers: await this.dispatch('getAuthenticationHeader')
      })

      const result = await response.json()

      this.state.issues = result
    },

    async getIssuesForLawyer() {
      let url = `${apiUrl}/issue/get-all-lawyer`;
      const response = await fetch(url, {
        method: "GET",
        headers: await this.dispatch('getAuthenticationHeader')
      })

      const result = await response.json()

      this.state.issuesLawyer = result
    },

    async getLawyers() {
      let url = `${apiUrl}/user/lawyers`;
      const response = await fetch(url, {
        method: "GET",
        headers: await this.dispatch('getAuthenticationHeader')
      })

      const result = await response.json()
      this.state.lawyers = result

    },

     async deleteItem(){
      let id = this.state.item.issueId;
      console.log("id i deleteItem: ",id)
      let tokenId = null;
      if(this.state.tokenId == null){
        let user = await JSON.parse(localStorage.getItem('user'));
        tokenId = user.token;
      } else
       {tokenId = this.state.tokenId}
         await fetch(`${apiUrl}/issue/active/`+id+"/"+false,  {
        method: "POST",
        headers: { 'Authorization': 'Bearer ' + tokenId, 'Accept': 'application/json', 'Content-Type': 'application/json' }
      })
    
    },

    async getIssueStatusForUser() {
      let issueStatus = "";
      let url = `${apiUrl}/issue/user`;
      const response = await fetch(url, {
        method: "GET",
        headers: await this.dispatch('getAuthenticationHeader')
      })

      await response.text().then(function (text) {
        issueStatus = text
      })
      this.commit("setIssueStatusForUser", issueStatus)
    },

    async getMessages() {
      let url = `${apiUrl}/post/get-user`;
      const response = await fetch(url, {
        method: "GET",
        headers: await this.dispatch('getAuthenticationHeader')
      })

      const result = await response.json()
      this.commit("setMessages", result)
    },

    async sendReply() {
      let url = `${apiUrl}/post/reply`;
      fetch(url, {
        method: "POST",
        headers: await this.dispatch('getAuthenticationHeader'),
        body: JSON.stringify(this.state.messageToSend)
      })
    },

    async updateGDPR() {
      let url = `${apiUrl}/user/gdpr-consent`;

      var raw = JSON.stringify({ "consent": true })

      var requestOptions = {
        method: 'POST',
        headers: await this.dispatch('getAuthenticationHeader'),
        body: raw,
      }

      fetch(url, requestOptions)
        .then(response => response.text())
        // .then(result => console.log(result))
        // .catch(error => console.log('error', error));
    },

    async getMessagesForLawyer() {
      let url = `${apiUrl}/post/get-lawyer`;
      const response = await fetch(url, {
        method: "GET",
        headers: await this.dispatch('getAuthenticationHeader')
      });

      const result = await response.json();
      this.commit("setMessages", result)       
    },
    
    async sendMessageToUser() {
      let url = `${apiUrl}/post/send`;
      let response = await fetch(url, {
        method: "POST",
        headers: await this.dispatch('getAuthenticationHeader'),
        body: JSON.stringify(this.state.messageToSend)
      })
      let result = "";
      await response.text().then( function (text) {
        result = text;
      });
      this.commit("setFetchResponse", result) 
    },

    async changeStatus(){
      let url = `${apiUrl}/issue/change-status`;
      await fetch(url, {
        method: "POST",
        headers: await this.dispatch('getAuthenticationHeader'),
        body: JSON.stringify(this.state.changeStatus)
      })
    },

    async getFileForIssue(){      
      let url = `${apiUrl}/download/`+ this.state.fileToDownload.issueId;
      await fetch(url, {
        method: "GET",
        headers: await this.dispatch('getAuthenticationHeader')
      }).then(response => response.blob())
      .then(blob => {
          var url = window.URL.createObjectURL(blob);
          var a = document.createElement('a');
          a.href = url;
          a.download = this.state.fileToDownload.filename;
          document.body.appendChild(a); // we need to append the element to the dom -> otherwise it will not work in firefox
          a.click();    
          a.remove();  //afterwards we remove the element again         
      });
    },

    async removeCategory() {
      let url = `${apiUrl}/category/remove`;
      let response = await fetch(url, {
        method: "POST",
        headers: await this.dispatch('getAuthenticationHeader'),
        body: JSON.stringify(this.state.addRemoveCategory)
      })
      let result = "";
      await response.text().then( function (text) {
        result = text;
      });
      this.commit("setFetchResponse", result) 
    },

    async addCategory () {      
      let url = `${apiUrl}/category/add`;
      let response = await fetch(url, {
        method: "POST",
        headers: await this.dispatch('getAuthenticationHeader'),
        body: JSON.stringify(this.state.addRemoveCategory)
      })
      let result = "";
      await response.text().then( function (text) {
        result = text;
      });
      this.commit("setFetchResponse", result) 
    }

  },

  modules: {
    account
  },

  created() {
    this.categories = []
  }

}
)
