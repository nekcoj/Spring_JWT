<template>
  <div class="text-left">
    <div role="tablist">
      <b-form-group label="Filtrera på månad" label-for="select-month">
        <b-form-select class id="select-month" v-model="selectedMonth">
          <b-form-select-option
            v-for="month in months"
            :key="month.id"
            :value="month"
          >{{ month.name }}</b-form-select-option>
        </b-form-select>
      </b-form-group>

      <b-form-group label="Filtrera på status" label-for="select-status">
        <b-form-select id="select-status" v-model="selectedStatus">
          <b-form-select-option
            v-for="status in this.$parent.statuses"
            :key="status.id"
            :value="status"
          >{{ status.status }}</b-form-select-option>
        </b-form-select>
      </b-form-group>

      <div class="search-parent">
        <div class="search-bar">
          <b-form-input
            type="text"
            v-model="searchfield"
            :value="searchfield"
            placeholder="Fritextsökning"
          ></b-form-input>
          <span class="search-icon">
            <font-awesome-icon icon="search"></font-awesome-icon>
          </span>
        </div>
      </div>
      <div id="counter-and-filter-remover">
        <span id="searchCounter">
          <span v-if="searchCounter > 0">Antal ärenden:<strong> {{searchCounter}}</strong></span>
          <span v-else>Inga ärenden matchade din sökning</span>
        </span>
        <span id="show-closed"><span for="checkbox-show-closed">Visa stängda ärenden </span><b-checkbox @change="{changeShowClosed}" v-model="showClosed" id="checkbox-show-closed"></b-checkbox></span>
      </div>
      <div class="btns-to-the-right" >
        <b-btn variant="secondary"  class="btn-filter" v-on:click="changeIssueOrder">
          <span v-if="ascSorting">Sortera fallande</span>
          <span v-else>Sortera stigande</span>
        </b-btn>
      </div>
      <div class="btns-to-the-right">
        <b-btn class="btn-filter" v-on:click="clearFilters">
          <span>Rensa filter</span>
        </b-btn>
      </div>

    <font-awesome-icon icon="square" class="squareAssigned text-info"></font-awesome-icon><span class="text-square"> Assigned </span>
    <font-awesome-icon icon="square" class="squareOpen text-secondary"></font-awesome-icon><span class="text-square"> Open </span>
    <font-awesome-icon icon="square" class="squareClose text-dark"></font-awesome-icon><span class="text-square"> Closed </span>

      <b-card no-body class="mb-1 text-left" v-for="item in filterIssues" :key="item.issueId">
        <b-card-header header-tag="header" class="p-1" role="tab">
          <b-button block v-b-toggle="'id'+item.issueId" :variant="checkStatus(item)" class="text-left">
            <span>Ärendeid: {{item.issueId}}</span>
          </b-button>
        </b-card-header>

        <b-collapse :id="'id'+item.issueId" accordion="my-accordion" role="tabpanel">
          <b-card-body id="issueBody">
            <h6>Status på ärendet: {{ item.issueStatus.toLowerCase() }}</h6>
            <div>
              <b-button v-if="showOpenButton(item.issueStatus)" id="changeIssueStatusOpen" @click="openIssue(item)">Öppna ärendet</b-button>
              <b-button v-if="showCloseButton(item.issueStatus)" class="ml-md-1" id="changeIssueStatusClose" @click="closeIssue(item)">Stäng ärendet</b-button>
            </div>
            <label for="whenIssue">När inträffade händelsen?</label>
            <b-card-text id="whenIssue">{{item.whenIssue}}</b-card-text>
            <label for="whereIssue">Var inträffade händelsen?</label>
            <b-card-text id="whereIssue">{{item.whereIssue}}</b-card-text>
            <label for="detailsIssue">Detaljer om ärendet:</label>
            <b-card-text id="detailsIssue">{{item.details}}</b-card-text>
            <label for="awarenessIssue">Är andra anställda medvetna om detta?</label>
            <b-card-text id="awarenessIssue">{{item.employeeAwareness}}</b-card-text>
            <label v-if="item.attachmentFileName != ''" for="attachmentIssue">Bilaga</label>
            <div>
              <b-card-text
                id="attachmentIssue"
                v-if="item.attachmentFileName != ''"
              >{{item.attachmentFileName}}</b-card-text><b-button v-if="item.attachmentFileName != ''" class="btn-sm" @click="getFile(item)">Ladda ner</b-button>
            </div>
            <router-link :to="'lawyer/postbox/' + item.issueId">
              <b-button variant="primary" class="btn-lg mt-2" @click="selectIssue(item)">Safe postbox</b-button>
            </router-link>
          </b-card-body>
        </b-collapse>
      </b-card>
    </div>
  </div>
</template>

<script>
import {statusAssigned, statusOpen, statusClosed, variationIssueAssigned, variationIssueOpen, variationIssueClosed} from '@/_helpers/config.js'

export default {
  data() {
    return {
      ascSorting: false,
      issues: [],
      selectedMonth: {},
      searchfield: "",
      searchCounter: 0,
      showClosed: false,
      months: [
        { id: 0, name: "Januari" },
        { id: 1, name: "Februari" },
        { id: 2, name: "Mars" },
        { id: 3, name: "April" },
        { id: 4, name: "Maj" },
        { id: 5, name: "Juni" },
        { id: 6, name: "Juli" },
        { id: 7, name: "Augusti" },
        { id: 8, name: "September" },
        { id: 9, name: "Oktober" },
        { id: 10, name: "November" },
        { id: 11, name: "December" }
      ],
    }
  },
  methods: {
    changeIssueOrder: function () {
      if (this.ascSorting) {
        this.ascSorting = false
      }
      else {
        this.ascSorting = true
      }
    },

    checkStatus: function(item){      
      if(item.issueStatus === `${statusAssigned}`){
        return `${variationIssueAssigned}`;  
      } else if(item.issueStatus === `${statusClosed}`){
        return `${variationIssueClosed}`
      }else {
        return `${variationIssueOpen}`;
      }
    },

    checkUnassigned() {
      let unassigned = 0;
      let temp = this.issues;
      for( const [, value] of Object.entries(temp)){
        if(value.issueStatus === `${statusAssigned}`){
          unassigned++;
        }
      }
      return this.$parent.nrMessagesLawyer = unassigned;
    },

    clearFilters: function () {
      this.selectedCategory = {}
      this.selectedMonth = {}
      this.$parent.selectedStatus = {}
      this.searchfield = ""
    },
    
    async openIssue(item){
      let statuses = this.$parent.statuses
      let changeStatus = {}
      changeStatus.issueId = item.issueId;
      changeStatus.statusId = 0;
      for(const [, value] of Object.entries(statuses)){
        if(value.status === `${statusOpen}`){
          changeStatus.statusId = value.id;
          item.issueStatus = value.status
        }
      }
      this.$store.commit("setChangeStatusBody", changeStatus)
      this.$store.dispatch("changeStatus")
      this.$store.dispatch("getIssuesForLawyer");
      this.filteredIssues = this.issuesLawyer;
      this.checkUnassigned();
    },

    async closeIssue(item){
      let statuses = this.$parent.statuses
      let changeStatus = {}
      changeStatus.issueId = item.issueId;
      changeStatus.statusId = 0;
      for(const [, value] of Object.entries(statuses)){
        if(value.status === `${statusClosed}`){
          changeStatus.statusId = value.id;
          item.issueStatus = value.status
        }
      }
      this.$store.commit("setChangeStatusBody", changeStatus)
      this.$store.dispatch("changeStatus")
      this.$store.dispatch("getIssuesForLawyer");
      this.filteredIssues = this.issuesLawyer;
    },

    getFile(item){
      let file = {}
      file.issueId = item.issueId;
      file.filename = item.attachmentFileName;
      this.$store.commit("setFileToDownload", file)
      this.$store.dispatch("getFileForIssue")
    },

    selectIssue(value){      
      this.$parent.issue = value;
    },

    showCloseButton: function(value) {
      if(value === `${statusOpen}`){
        return true;
      } else {return false;}
    },

    showOpenButton: function(value) {
      if(value === `${statusOpen}` || value === `${statusClosed}`){
        return false;
      } else {return true;}
    },

    updateSearchCounter(numberOfMatches) {
      this.searchCounter = numberOfMatches
    }

  },
        
  async mounted() {
    this.$store.state.fetchResponse = "";
    await this.$store.dispatch("getCategories");
    await this.$store.dispatch("getIssuesForLawyer");
    this.issues = await this.$store.state.issuesLawyer;
    await this.$store.dispatch("getStatuses")
    this.$parent.statuses = await this.$store.state.statuses
    await this.checkUnassigned();
  },

  created() {
    this.$store.dispatch("getIssuesForLawyer");
    this.$store.dispatch("getMessagesForLawyer");
    this.filteredIssues = this.issuesLawyer;
  },

  computed: {
    categories: {
      get() {
        return this.$store.state.categories;
      }
    },

    selectedStatus: {
      get() {
        return this.$parent.selectedStatus
      },
      set(value) { 
        this.$parent.selectedStatus = value
      }
    },

    filterIssues: function() {
      let temp = this.issues
      //fritextsökning
      if (typeof temp.length === "undefined" || temp.length === 0) {
        console.log("listan var tom!")

        return null
      } else {
        let searchResult = []

        //sökfält
        temp.forEach(issue => {
          if (
            issue.issueId === this.searchfield ||
            issue.details
              .toLowerCase()
              .includes(this.searchfield.toLowerCase()) ||
            issue.employeeAwareness
              .toLowerCase()
              .includes(this.searchfield.toLowerCase()) ||
            issue.issueStatus
              .toLowerCase()
              .includes(this.searchfield.toLowerCase()) ||
            issue.whenIssue
              .toLowerCase()
              .includes(this.searchfield.toLowerCase()) ||
            issue.whereIssue
              .toLowerCase()
              .includes(this.searchfield.toLowerCase())
          ) {
            searchResult.push(issue)
          }
        })

        //månad
        if (JSON.stringify(this.selectedMonth) == "{}") {
          //console.log("ingen månad vald")
        } else {
          searchResult = searchResult.filter(issue => {
            let dateCreate = new Date(issue.created)
            return dateCreate.getMonth() === this.selectedMonth.id
          })
        }
        //visa stängda
        if(!this.showClosed){
          searchResult = searchResult.filter(issue => {
            return issue.issueStatus.toLowerCase() !== "closed"
          })
        }
        //status
        if (JSON.stringify(this.$parent.selectedStatus) == "{}") {
          //console.log("ingen status vald")
        } else {
          searchResult = searchResult.filter(issue => {
            return issue.issueStatus.toLowerCase() === this.$parent.selectedStatus.status.toLowerCase()
          })
        }

        // //sortera
        searchResult = this.ascSorting ? searchResult : searchResult.sort((a, b) => b.issueId - a.issueId)

        this.updateSearchCounter(searchResult.length)
        return searchResult
      }
    }
  }
}
</script>


<style scoped>
.search-bar {
  position: relative;
}
.search-bar input {
  padding-left: 30px;
}
.search-icon {
  position: absolute;
  top: 8px;
  left: 8px;
}
label {
  font-weight: bold;
}
#issueBody {
  position: relative;
}
.trash-icon {
  font-size: 1.5rem;
  position: absolute;
  top: 3px;
  right: 3px;
}
#issueC {
  font-weight: normal;
  text-align: left;
}

#counter-and-filter-remover {
  display: flex;
  justify-content: space-between;
  margin-bottom: 5px;
}

.btns-to-the-right {
  display: flex;
  justify-content: flex-end;
  margin-bottom: 10px;
}

#changeIssueStatusOpen, #changeIssueStatusClose {
  width: 10rem;
  margin-top: 5px;
  margin-bottom: 5px;
}

.squareUnassigned{
  color: blue;
}

.squareAssigned{
    color: rgba(104,199,212);
}

.squareOpen{
  color: grey;

}

.text-square{
  font-size: small;
}

#show-closed{
  display:flex;
  justify-content: space-between;
}
#closed-issue-text{
  display:flex;
  justify-content: right;
}

.btn-filter{
    width: 132px;
    text-align: center;
    padding: 0;
    padding-top: 0.4rem;
    padding-bottom: 0.4rem;
}

</style>
