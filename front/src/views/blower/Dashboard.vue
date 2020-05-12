<template>
  <div>
    <b-form-group label="Filtrera på kategori" label-for="select-category">
      <b-form-select class="" id="select-category" v-model="selectedCategory">
        <b-form-select-option
          v-for="category in categories"
          :key="category"
          :value="category"
          >{{ category }}</b-form-select-option
        >
      </b-form-select>
    </b-form-group>
    <b-form-group label="Filtrera på månad" label-for="select-month">
      <b-form-select class="" id="select-month" v-model="selectedMonth">
        <b-form-select-option
          v-for="month in months"
          :key="month"
          :value="month"
          >{{ month }}</b-form-select-option
        >
      </b-form-select>
    </b-form-group>
    <div class="search-parent">
      <div class="search-bar">
        <b-form-input type="text" placeholder="Fritext sökning"></b-form-input>
        <span class="search-icon">
          <font-awesome-icon icon="search"></font-awesome-icon>
        </span>
      </div>
    </div>

    <div role="tablist">
      <b-card no-body class="mb-1" id="issueContainer">
        <b-card-header header-tag="header" class="p-1" role="tab">
          <b-button block v-b-toggle.accordion-1 variant="secondary">
            <span>Ärendenummer(1)</span>
          </b-button>
        </b-card-header>
        <b-collapse
          id="accordion-1"
          visible
          accordion="my-accordion"
          role="tabpanel"
        >
          <b-card-body id="issueBody">
            <font-awesome-icon
              icon="trash-alt"
              class="trash-icon"
            ></font-awesome-icon>
            <!-- Status toLowerCase() -->
            <h6>Status på ärendet: {{ status.toLowerCase() }}</h6>
            <b-form-group label="Ändra kategori" label-for="change-category">
              <b-form-select id="change-category">
                <b-form-select-option
                  class=""
                  v-for="category in categories"
                  :key="category"
                  :value="category"
                  >{{ category }}</b-form-select-option
                >
              </b-form-select>
            </b-form-group>
            <b-form-group label="Tilldela ärendet" label-for="change-assigned">
              <b-form-select id="change-assigned">
                <b-form-select-option
                  class=""
                  v-for="lawyer in lawyers"
                  :key="lawyer"
                  :value="lawyer"
                  >{{ lawyer }}</b-form-select-option
                >
              </b-form-select>
            </b-form-group>
            <label for="whenIssue">När inträffade händelsen?</label>
            <b-card-text id="whenIssue">2020-05-14</b-card-text>
            <label for="whereIssue">När inträffade händelsen?</label>
            <b-card-text id="whereIssue">Hemma</b-card-text>
            <label for="detailsIssue">Detaljer om ärendet:</label>
            <b-card-text id="detailsIssue"
              >Jag har inte borstat tänderna</b-card-text
            >
            <label for="awarenessIssue"
              >Är andra anställda medvetna om detta?</label
            >
            <b-card-text id="awarenessIssue">100%</b-card-text>
            <label for="attachmentIssue">Bilaga</label>
            <b-card-text id="attachmentIssue">bild.jpg</b-card-text>
          </b-card-body>
        </b-collapse>
      </b-card>

      <b-card no-body class="mb-1">
        <b-card-header header-tag="header" class="p-1" role="tab">
          <b-button block v-b-toggle.accordion-2 variant="secondary"
            ><span>Ärendenummer(2)</span></b-button
          >
        </b-card-header>
        <b-collapse id="accordion-2" accordion="my-accordion" role="tabpanel">
          <b-card-body>
            <b-card-text>{{ text }}</b-card-text>
          </b-card-body>
        </b-collapse>
      </b-card>

      <b-card no-body class="mb-1">
        <b-card-header header-tag="header" class="p-1" role="tab">
          <b-button block v-b-toggle.accordion-3 variant="secondary"
            ><span>Ärendenummer(3)</span></b-button
          >
        </b-card-header>
        <b-collapse id="accordion-3" accordion="my-accordion" role="tabpanel">
          <b-card-body>
            <b-card-text>{{ text }}</b-card-text>
          </b-card-body>
        </b-collapse>
      </b-card>
    </div>
    <div class="row">
      <div class="col-4">
        <b-form-group>
          <b-form-select v-model="addRemoveOption">
            <b-form-select-option value="1">
              Lägg till
            </b-form-select-option>
            <b-form-select-option value="2">
              Ta bort
            </b-form-select-option>
          </b-form-select>
        </b-form-group>
      </div>
      <div class="col-8 p-0 mx-auto">
        <b-form-input
          placeholder="Lägg till/Ta bort Kategori"
          v-model="addRemoveText"
        ></b-form-input>
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
      lawyers: [
        "Joacim Norbeck",
        "Ralf Tjärnlund",
        "Sofia Fredman",
        "Magnus Pettersson",
      ],
      categories: [
        "Mutor, korruption & förfalskning",
        "Dataskydd och brott mot IT-säkerhet",
        "Diskriminering, trakasserier och andra arbetsrelaterade lagproblem",
        "Bedrägeri, missbruk och stöld",
        "Hälsa, säkerhet & miljö",
        "Penningtvätt",
        "Personal",
        "Annat",
      ],
      months: [
        "Januari",
        "Februari",
        "Mars",
        "April",
        "Maj",
        "Juni",
        "Juli",
        "Augusti",
        "September",
        "Oktober",
        "November",
        "December",
      ],
      selectedCategory: null,
      selectedMonth: null,
      status: "UNASSIGNED",
      text: `Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry
          richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor
          brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon
          tempor, sunt aliqua put a bird on it squid single-origin coffee nulla
					assumenda shoreditch et.`,
      addRemoveOption: null,
      addRemoveText: null,
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
  },
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
