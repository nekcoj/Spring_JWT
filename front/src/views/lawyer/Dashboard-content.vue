<template>
  <div class="text-left">
    <b-form-group label="Filtrera på kategori" label-for="select-category">
      <b-form-select class id="select-category" v-model="selectedCategory">
        <b-form-select-option
          v-for="category in categories"
          :key="category"
          :value="category"
        >{{ category }}</b-form-select-option>
      </b-form-select>
    </b-form-group>
    <b-form-group label="Filtrera på månad" label-for="select-month">
      <b-form-select class id="select-month" v-model="selectedMonth">
        <b-form-select-option v-for="month in months" :key="month" :value="month">{{ month }}</b-form-select-option>
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
      <b-card
        no-body
        class="mb-1 text-left"
        v-for="item in items"
        :key="item.id"
      >
        <b-card-header header-tag="header" class="p-1" role="tab">
          <b-button block 
            v-b-toggle="item.id"
            variant="secondary"
            class="text-left"
            
            >
            
            <span>Ärendeid: {{item.id}}</span>
          </b-button>
        </b-card-header>
        <b-collapse 
          :id=item.id 
          accordion="my-accordion" 
          role="tabpanel">
          
          <b-card-body id="issueBody">
            <font-awesome-icon icon="trash-alt" class="trash-icon"></font-awesome-icon>
            <!-- Status toLowerCase() -->
            <h6>Status på ärendet: {{ status.toLowerCase() }}</h6>

            <label for="whenIssue">När inträffade händelsen?</label>
            <b-card-text id="whenIssue">{{item.when}}</b-card-text>
            <label for="whereIssue">När inträffade händelsen?</label>
            <b-card-text id="whereIssue">{{item.where}}</b-card-text>
            <label for="detailsIssue">Detaljer om ärendet:</label>
            <b-card-text id="detailsIssue">{{item.details}}</b-card-text>
            <label for="awarenessIssue">Är andra anställda medvetna om detta?</label>
            <b-card-text id="awarenessIssue">{{item.awareness}}</b-card-text>
            <label for="attachmentIssue">Bilaga</label>
            <b-card-text id="attachmentIssue">{{item.attachment}}</b-card-text>
            <router-link :to="{name: 'Safe postbox jurist', params:{tempUserID: item.id}}"><b-button variant="primary" class="btn-lg">Safe postbox</b-button></router-link>
          </b-card-body>
        </b-collapse>
      </b-card> 
      <router-view>
        </router-view>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      items: {
        1: {
          id: "1",
          when: "Idag",
          where: "Hemma",
          details: "inga detaljer",
          awareness: "alla vet",
          attachment: "test.png"
        },
        2: {
          id: "2",
          when: "Igår",
          where: "På kontoret",
          details: "Du vet vad som hände! Det var helt galet!",
          awareness: "ALLA VET!!!",
          attachment: "bildbevis.jpeg"
        },
        3: {
          id: "3",
          when: "Torsdags",
          where: "Borta",
          details: "hmmmm. det var inget mer",
          awareness: "alla vet",
          attachment: "test2.zip"
        }
      },

      categories: [
        "Mutor, korruption & förfalskning",
        "Dataskydd och brott mot IT-säkerhet",
        "Diskriminering, trakasserier och andra arbetsrelaterade lagproblem",
        "Bedrägeri, missbruk och stöld",
        "Hälsa, säkerhet & miljö",
        "Penningtvätt",
        "Personal",
        "Annat"
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
        "December"
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

      methods: {
        postboxLink() {
          this.$router.push("/juristSpostbox");
        },
        addRemoveCategory: function() {
          if (this.addRemoveOption === "1") {
            this.categories.push(this.addRemoveText);
          } else if (this.addRemoveOption === "2") {
            var index = this.categories.indexOf(this.addRemoveText);
            if (index !== -1) this.categories.splice(index, 1);
          }
        },
        mounted: {}
      }
    };
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
