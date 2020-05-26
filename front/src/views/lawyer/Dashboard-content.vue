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

      <b-card no-body class="mb-1 text-left" v-for="item in filterIssues" :key="item.issueId">
        <b-card-header header-tag="header" class="p-1" role="tab">
          <b-button block v-b-toggle="'id'+item.issueId" variant="secondary" class="text-left">
            <span>Ärendeid: {{item.issueId}}</span>
          </b-button>
        </b-card-header>

        <b-collapse :id="'id'+item.issueId" accordion="my-accordion" role="tabpanel">
          <b-card-body id="issueBody">
            <h6>Status på ärendet: {{ item.issueStatus.toLowerCase() }}</h6>

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
            >{{item.attachment === null ? "ingen bilaga" : item.attachment}}</b-card-text>
             <router-link :to="'lawyer/postbox/' + item.issueId"><b-button variant="primary" class="btn-lg" @click="selectIssue(item)">Safe postbox</b-button></router-link>
          </b-card-body>
        </b-collapse>
      </b-card>
    </div>
  </div>
</template>

<script>

export default {
  data() {
    return {
      issues: [],
      selectedMonth: {},
      searchfield: "",
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
    postboxLink() {
      this.$router.push("/juristpostbox");
    },
    selectIssue(value){      
      this.$parent.issue = value;
    }
  },
        
  async mounted() {
    this.$store.state.fetchResponse = "";
    await this.$store.dispatch("getCategories");
    await this.$store.dispatch("getIssuesForLawyer");
    this.issues = await this.$store.state.issuesLawyer;
    console.log("mina issues: ", this.issues)
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
    filterIssues: function() {
      let temp = this.issues;

      //fritextsökning
      if (typeof temp.length === 'undefined' || temp.length === 0){
        //console.log("listan var tom!");
        return null;
      } else {
        let searchResult = [];
        
        //sökfält
        temp.forEach(issue => {
          if(
          issue.issueId === this.searchfield ||
            issue.details.toLowerCase().includes(this.searchfield.toLowerCase()) ||
            issue.employeeAwareness
              .toLowerCase()
              .includes(this.searchfield.toLowerCase()) ||
            issue.issueStatus
              .toLowerCase()
              .includes(this.searchfield.toLowerCase()) ||
            issue.whenIssue.toLowerCase().includes(this.searchfield.toLowerCase()) ||
            issue.whereIssue.toLowerCase().includes(this.searchfield.toLowerCase())
          ){
            searchResult.push(issue)
          }
        })

        //månad
        if (JSON.stringify(this.selectedMonth) == "{}"){
          //console.log("ingen månad vald")
          }
        else{
        searchResult = searchResult.filter(issue => {
          let dateCreate = new Date(issue.created)
          return dateCreate.getMonth() === this.selectedMonth.id;
        });
        
        // //sortera
        // searchResult = this.$store.state.sortDesc
        //   ? searchResult
        //   : searchResult.sort((a, b) => b.issueId - a.issueId);

        // console.log("sorterat searchResult: ", searchResult);
        }
        
        return searchResult;
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
</style>
