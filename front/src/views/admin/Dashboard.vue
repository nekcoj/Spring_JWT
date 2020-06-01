<template>
  <div class="text-left">
    <div role="tablist">
      <b-form-group label="Filtrera på kategori" label-for="select-category">
        <b-form-select class="inputbox" v-model="selectedCategory" id="select-category">
          <p selectedCategory>Välj kategori</p>
          <b-form-select-option
            v-for="category in categories"
            :key="category.id"
            :value="category"
          >{{category.categoryName}}</b-form-select-option>
        </b-form-select>
      </b-form-group>
      <b-form-group label="Filtrera på månad" label-for="select-month">
        <b-form-select id="select-month" v-model="selectedMonth">
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
      </div>
      <div class="change-issue-order">
        <b-btn variant="secondary" id="btn-sort-asc-desc" v-on:click="changeIssueOrder">
          <span v-if="ascSorting">Sortera fallande</span>
          <span v-else>Sortera stigande</span>
        </b-btn>
      </div>
          <div class="change-issue-order">
            <b-btn id="btn-sort-asc-desc" v-on:click="clearFilters">
              <span>Rensa filter</span>
            </b-btn>
        </div>
  
      <b-card no-body class="mb-1 text-left" v-for="item in filterIssues" :key="item.issueId">
        <b-card-header header-tag="header" class="p-1" role="tab">
          <b-button block v-b-toggle="'id'+item.issueId" :variant="checkStatus(item)" class="text-left">
            <span>Ärendeid: {{item.issueId}}</span>
          </b-button>
        </b-card-header>
        <b-collapse :id="'id'+item.issueId" accordion="my-accordion" role="tabpanel">
          <b-card-body id="issueBody">
            <span @click="deleteIssue(item)">
              <font-awesome-icon icon="trash-alt" class="trash-icon"></font-awesome-icon>
            </span>

            <div v-for="category in categories" :key="category.id" :value="category.id">
              <div v-if="checkCategory(item, category)">
                <h6>
                  Kategori:
                  <em>{{ item.categoryName }}</em>
                </h6>
              </div>
            </div>
                            <!-- HÄR ÄR DET-->
            <b-form-group label="Ändra kategori" label-for="change-category">
              <b-form-select id="change-category" v-model="categoryToChangeTo">
                <b-form-select-option
                  v-for="category in categories"
                  :key="category.id"
                  :value="category"
                >{{category.categoryName}}</b-form-select-option>>
              </b-form-select>
              <b-button v-on:click="issueChangeCategory(item)" class="mt-1">Utför</b-button>
            </b-form-group>

            <h6>
              Status:
              <em>{{ item.issueStatus.toLowerCase() }}</em>
            </h6>
            <div v-if="item.issueStatus.toLowerCase() === 'unassigned'">
              <b-form-group label="Tilldela ärendet" label-for="change-assigned">
                <b-form-select id="change-assigned" v-model="selectedLawyer">
                  <b-form-select-option
                    class
                    v-for="lawyer in lawyers"
                    :key="lawyer.id"
                    :value="lawyer"
                  >{{ lawyer.username }}</b-form-select-option>
                </b-form-select>
                <b-button v-on:click="assignIssueToLawyer(item)" class="mt-1">Tilldela</b-button>
              </b-form-group>
            </div>
            <div id="issueForm">
              <label for="whenIssue">När inträffade händelsen?</label>
              <b-card-text id="whenIssue">{{item.whenIssue}}</b-card-text>
              <label for="whereIssue">Var inträffade händelsen?</label>
              <b-card-text id="whereIssue">{{item.whereIssue}}</b-card-text>
              <label for="detailsIssue">Detaljer om ärendet:</label>
              <b-card-text id="detailsIssue">{{item.details}}</b-card-text>
              <label for="awarenessIssue">Är andra anställda medvetna om detta?</label>
              <b-card-text id="awarenessIssue">{{item.employeeAwareness}}</b-card-text>
              <label for="attachmentIssue">Bilaga</label>
              <b-card-text
                id="attachmentIssue"
              >{{item.attachment === null ? "ingen bilaga" : item.attachmentFileName}}</b-card-text>
            </div>
          </b-card-body>
        </b-collapse>
      </b-card>
    </div>

    <div class="row">
      <div class="col-4">
        <b-form-group>
          <b-form-select v-model="addRemoveOption">
            <b-form-select-option value="1">Lägg till</b-form-select-option>
            <b-form-select-option value="2">Ta bort</b-form-select-option>
          </b-form-select>
        </b-form-group>
      </div>
      <div class="col-7 p-0 mx-auto">
        <b-form-input placeholder="Lägg till/Ta bort kategori" v-model="addRemoveText"></b-form-input>
      </div>
      <div class="container-admin col-12 justify-content-center"></div>
      <div class="col-12">
        <b-button v-on:click="addRemoveCategory">Utför</b-button>
      </div>
    </div>
  </div>
</template>

<script>
import {statusAssigned, statusOpen, statusClosed,  variationIssueAssigned, statusUnassigned, variationIssueOpen, variationIssueClosed, variationIssueUnassigned} from '@/_helpers/config.js'

export default {
  data() {
    return {
      ascSorting: true,
      categoryToChangeTo: {},
      issues: [],
      searchCounter: 0,
      selectedCategory: {},
      selectedMonth: {},
      searchfield: "",
      selectedLawyer: {},
      statuses: [],
      lawyers: [],
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
      addRemoveOption: null,
      addRemoveText: null,
    }
  },
  methods: {
    addRemoveCategory: function () {
      if (this.addRemoveOption === "1") {
        this.categories.push(this.addRemoveText)
      } else if (this.addRemoveOption === "2") {
        var index = this.categories.indexOf(this.addRemoveText)
        if (index !== -1) this.categories.splice(index, 1)
      }
    },
    issueChangeCategory: async function (issue) {
      if (JSON.stringify(this.categoryToChangeTo) === "{}") {
        // console.log("ingen ny kategori vald")
      }
      else {
        await this.$store.commit("setIssueToChangeCategoryFor", issue)
        await this.$store.commit("setNewCategory", this.categoryToChangeTo.id)
        await this.$store.dispatch("issueChangeCategory")
        await this.$store.dispatch("getIssues")
        this.issues = await this.$store.state.issues
        await this.checkUnassigned();
      } 
    },
    assignIssueToLawyer: async function (issue) {
      if(JSON.stringify(this.selectedLawyer) === "{}"){
        // console.log("ingen jurist vald")
      }
      else{
      let combinedIds = {
        lawyerId: this.selectedLawyer.id,
        issueId: issue.issueId
      }
      await this.$store.commit("setIssueToAssign", combinedIds)
      await this.$store.dispatch("assignIssue")
      }
    },

    deleteIssue: function (item) {
      this.$store.dispatch("deleteItem", item)
    },
    updateSearchCounter(numberOfMatches) {
      this.searchCounter = numberOfMatches
    },
    clearFilters: function () {
      this.selectedCategory = {}
      this.selectedMonth = {}
      this.$parent.selectedStatus = {}
      this.searchfield = ""
    },
    changeIssueOrder: function () {
      if (this.ascSorting) {
        this.ascSorting = false
      }
      else {
        this.ascSorting = true
      }
    },

    checkUnassigned() {
      let unassigned = 0;
      let temp = this.issues;
      for( const [, value] of Object.entries(temp)){
        if(value.issueStatus === `${statusUnassigned}`){
          unassigned++;
        }
      }
      return this.$parent.nrMessagesAdmin = unassigned;
    },

    checkStatus: function(item){      
      if(item.issueStatus === `${statusAssigned}`){
        return `${variationIssueAssigned}`;  
      } else if(item.issueStatus === `${statusClosed}`){
        return `${variationIssueClosed}`;
        } else if(item.issueStatus === `${statusUnassigned}`){
        return `${variationIssueUnassigned}`;
      }else {
        return `${variationIssueOpen}`;
      }
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
      this.checkUnassigned();
    },

    checkCategory(item, category){
      if(item.categoryId === category.id){
        item.categoryName = category.categoryName
        return true;
      } else{
        return false;
      }
    },
  },

  async mounted() {
    await this.$store.dispatch("getLawyers")
    this.lawyers = await this.$store.state.lawyers
    await this.$store.dispatch("getCategories")
    await this.$store.dispatch("getStatuses")
    this.$parent.statuses = await this.$store.state.statuses
    await this.$store.dispatch("getIssues")
    this.issues = await this.$store.state.issues
    await this.checkUnassigned();
   
  },
  created() {
    this.$store.dispatch("getIssues")
    this.issues = this.$store.state.issues
    this.filteredIssues = this.issues
  },


  computed: {
    categories: {
      get() {
        return this.$store.state.categories
      },
      set(value) {
        this.$store.commit("setCategories", value)
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

    filterIssues: function () {
      let temp = this.$store.state.issues
      //fritextsökning
      if (typeof temp.length === "undefined" || temp.length === 0) {
       

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

        //kategori
        if (JSON.stringify(this.selectedCategory) == "{}") {
          //console.log("ingen kategori vald")
        } else {
          searchResult = searchResult.filter(issue => {
            return issue.categoryId === this.selectedCategory.id
          })
        }

        //månad
        if (JSON.stringify(this.selectedMonth) == "{}") {
          //console.log("ingen månad vald")
        } else {
          searchResult = searchResult.filter(issue => {
            let dateCreate = new Date(issue.created)
            return dateCreate.getMonth() === this.selectedMonth.id
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
  margin-bottom: 10px;
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
#issueForm {
  margin-top: 15px;
}
.trash-icon {
  font-size: 1.5rem;
  position: absolute;
  top: 3px;
  right: 3px;
  cursor: pointer;
}
#issueC {
  font-weight: normal;
  text-align: left;
}

.container-admin {
  text-align: center !important;
}

.search-parent-admin {
  margin-bottom: 20px;
}

#counter-and-filter-remover {
  display: flex;
  justify-content: space-between;
  margin-bottom: 5px;
}

.change-issue-order {
  display: flex;
  margin-bottom: 10px;

 
}
#btn-sort-asc-desc{
    width: 132px;
}
</style>
