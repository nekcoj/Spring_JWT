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
            <span @click="deleteIssue(item)">
              <font-awesome-icon icon="trash-alt" class="trash-icon"></font-awesome-icon>
            </span>
            <!-- Status toLowerCase() -->
            <h6>Status på ärendet: {{ status.toLowerCase() }}</h6>
            <b-form-group label="Ändra kategori" label-for="change-category">
              <b-form-select id="change-category">
                <b-form-select-option
                  v-for="category in categories"
                  :key="category.id"
                  :value="category.id"
                >{{category.categoryName}}</b-form-select-option>>
              </b-form-select>
            </b-form-group>
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
export default {
  data() {
    return {
      issues: [],
      selectedCategory: {},
      selectedMonth: {},
      searchfield: "",
      selectedLawyer: {},
      lawyers: [
        //   "Joacim Norbeck",
        //   "Ralf Tjärnlund",
        //   "Sofia Fredman",
        //   "Magnus Pettersson",
      ],
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
      status: "UNASSIGNED",
      text: `Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry
          richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor
          brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon
          tempor, sunt aliqua put a bird on it squid single-origin coffee nulla
					assumenda shoreditch et.`,
      addRemoveOption: null,
      addRemoveText: null
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
    assignIssueToLawyer: async function (issue) {
      console.log("trying to assign issue to lawyer")
      console.log("selectedLawyer: ", this.selectedLawyer)
      console.log("issue: ", issue)
      let combinedIds = {
        lawyerId: this.selectedLawyer.id,
        issueId: issue.issueId
      }
      let sendThisToBackend = JSON.stringify(combinedIds)
      console.log(
        "ett objekt med 2 st idnummer, stringifierat: ",
        sendThisToBackend
      )
      await fetch("http://localhost:9090/issue/assign", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: sendThisToBackend
      })
    },

    getIssues: async function () { },
    deleteIssue: function (item) {
      this.$store.dispatch("deleteItem", item)
    }
  },
  async mounted() {
    await this.$store.dispatch("getLawyers")
    this.lawyers = await this.$store.state.lawyers
    await this.$store.dispatch("getCategories")
    await this.$store.dispatch("getIssues")
    this.issues = await this.$store.state.issues
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

    filterIssues: function () {
      let temp = this.$store.state.issues

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

          // //sortera
          // searchResult = this.$store.state.sortDesc
          //   ? searchResult
          //   : searchResult.sort((a, b) => b.issueId - a.issueId);

          // console.log("sorterat searchResult: ", searchResult);
        }

        return searchResult
      }
    }
  }
};
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

#select-month {
  margin-bottom: 20px;
}
</style>
