<template>
  <div class="text-left">
    <div role="tablist">
      <b-form-group label="Filtrera på kategori" label-for="select-category" >
        <b-form-select class="inputbox" id="select-category">
          <b-form-select-option
            v-for="category in categories"
            :key="category.id"
            :value="category"
            v-model="selectedCategory"
          >{{category.categoryName}}</b-form-select-option>
        </b-form-select>
      </b-form-group>
      <b-form-group label="Filtrera på månad" label-for="select-month">
        <b-form-select id="select-month" v-bind="selectedMonth">
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
            <font-awesome-icon icon="trash-alt" class="trash-icon"></font-awesome-icon>
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
              <b-form-select id="change-assigned">
                <b-form-select-option
                  class
                  v-for="lawyer in lawyers"
                  :key="lawyer.id"
                  :value="lawyer"
                >{{ lawyer.username }}</b-form-select-option>
              </b-form-select>
              <b-button v-on:click="assignIssueToLawyer" class="mt-1">Tilldela</b-button>
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
      <div class="col-6"></div>
      <div class="col-6 p-0">
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
    };
  },
  methods: {
    addRemoveCategory: function() {
      if (this.addRemoveOption === "1") {
        this.categories.push(this.addRemoveText);
      } else if (this.addRemoveOption === "2") {
        var index = this.categories.indexOf(this.addRemoveText);
        if (index !== -1) this.categories.splice(index, 1);
      }
    },
    assignIssueToLawyer: async function() {
      console.log("trying to assign issue to lawyer");
      await fetch("http://localhost:9090/issue/assign", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(`{ 
   	"lawyerId" : 45,
    "issueId" : 20
   }`)
      });
      console.log("hur gick det? vem vet");
    },
    getIssues: async function() {}
  },
  async mounted() {
    await this.$store.dispatch("getLawyers");
    this.lawyers = await this.$store.state.lawyers;
    await this.$store.dispatch("getCategories");
    await this.$store.dispatch("getIssues");
    this.issues = await this.$store.state.issues;
    //this.getIssues()
    console.log("these issues: ", this.issues);
  },
  created() {
    this.$store.dispatch("getIssues");
    this.issues = this.$store.state.issues;
    this.filteredIssues = this.issues;
  },
  computed: {
    categories: {
      get() {
        console.log("tryna get categories");
        return this.$store.state.categories;
      },
      set(value) {
        console.log("tryna COMMIT to setCategories");
        this.$store.commit("setCategories", value);
      }
    },

    // selectedCategory: {
    //   get() {
    //     console.log("tryna get selectedCategory")

    //     return this.$store.state.selectedCategory;
    //   },
    //   set(value) {
    //     console.log("tryna COMMIT  selectedCategory")
    //     this.$store.commit("setSelectedCategory", value)
    //     console.log("här är felet")

    //   }
    // },

    // searchfield: {
    //   get() {
    //     return this.$store.state.searchfield;
    //   },
    //   set(value) {
    //     this.$store.commit("setSearchField", value);
    // //   }
    // // },
    // selectedMonth: {
    //   get() {
    //     return this.$store.state.selectedMonth;
    //   },
    //   set(value) {
    //     this.$store.commit("setSelectedMonth",  value);
    //   }
    // },

    filterIssues: function() {
      console.log("kör filterIssues: funktion()");
      console.log("Kategorier: ", this.categories)
      console.log("såhär ser vald kategori ut: ", this.selectedCategory)
      console.log("och den har namnet: ",this.selectedCategory.categoryName)
      let temp = this.$store.state.issues;

      //fritextsökning
      if (temp.length === 0) {
        console.log("listan var tom!");
        return null;
      } else {
        console.log("listan var INTE tom");
        console.log("temp: ", temp);

        let searchResult = [];
          console.log("såhär ser sökfältet ut: ", this.searchfield)
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
            console.log("pushar detta issue till searchResult: ", issue.issueId)
            searchResult.push(issue)
          }
        });
      // let searchResult = temp.filter(issue => {
        //   return (
        //     issue.issueId.equals(this.search) ||
        //     issue.details.toLowerCase().includes(this.search.toLowerCase()) ||
        //     issue.employeeAwareness
        //       .toLowerCase()
        //       .includes(this.search.toLowerCase()) ||
        //     issue.issueStatus.toLowerCase().includes(this.search.toLowerCase()) ||
        //     issue.whenIssue.toLowerCase().includes(this.search.toLowerCase()) ||
        //     issue.whereIssue.toLowerCase().includes(this.search.toLowerCase())
        //   )
        // })
        console.log("Filtrerat searchResult på fritext: ", searchResult);


        console.log("vald kategori: ", this.selectedCategory)
        // //kategori
        // searchResult = searchResult.filter(issue => {
        //   return issue.categoryId.equals(this.$store.state.selectedCategory);
        // });
        // console.log("filtrerat searchResult på kategori: ", searchResult);

        // //månad
        // searchResult = searchResult.filter(issue => {
        //   return issue.created
        //     .getMonth()
        //     .equals(this.$store.state.selectedMonth.id);
        // });
        // console.log("filtrerat searchResult på månad: ", searchResult);

        // //sortera
        // searchResult = this.$store.state.sortDesc
        //   ? searchResult
        //   : searchResult.sort((a, b) => b.issueId - a.issueId);

        // console.log("sorterat searchResult: ", searchResult);

        return searchResult;
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
}
#issueC {
  font-weight: normal;
  text-align: left;
}
</style>
