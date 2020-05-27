<template>
  <div class="new-issue">
    <b-container class="container">
      <b-form id="main-form">
        <p class="h2">Anmälan om oegentligheter</p>

        <b-row class="mt-2">
          <b-col sm="12">
            <label class="lbl" for="select-category">Vad gäller ärendet?(*)</label>
          </b-col>
          <b-col sm="12">
            <b-form-select
              class="inputbox"
              id="select-category"
              v-model="setselectedCategory"
              required
            >
              <b-form-select-option
                v-for="category in this.$store.state.categories"
                :key="category.id"
                :value="category"
              >{{category.categoryName}}</b-form-select-option>
            </b-form-select>
          </b-col>
        </b-row>

        <b-row class="mt-2">
          <b-col sm="12">
            <label class="lbl" for="datePicker-when">När inträffade händelsen?(*)</label>
          </b-col>
          <b-col sm="12">
            <b-form-datepicker v-model="whenIssue" id="datePicker-when" required placeholder="Ange datum"></b-form-datepicker>
          </b-col>
        </b-row>

        <b-row class="mt-2">
          <b-col sm="12">
            <label class="lbl" for="textarea-where">Var inträffade händelsen?(*)</label>
          </b-col>
          <b-col sm="12">
            <b-form-textarea class="inputbox" id="textarea-where" required v-model="whereIssue"></b-form-textarea>
          </b-col>
        </b-row>

        <b-row class="mt-2">
          <b-col sm="12">
            <label class="lbl" for="textarea-details">Detaljer om ärendet(*)</label>
          </b-col>
          <b-col sm="12">
            <b-form-textarea class="inputbox" id="textarea-details" required v-model="details"></b-form-textarea>
          </b-col>
        </b-row>

        <b-row class="mt-2">
          <b-col sm="12">
            <label class="lbl" for="textarea-knowledge">Är andra anställda medvetna om detta?(*)</label>
          </b-col>
          <b-col sm="12">
            <b-form-textarea
              class="inputbox"
              id="textarea-knowledge"
              required
              v-model="employeeAwareness"
            ></b-form-textarea>
          </b-col>
        </b-row>

        <b-row class="d-flex" id="row-attachment">
          <b-col sm="12">
            <b-form-group>
          <b-form-file id="file-default"
              v-model="attachment"
              :state="Boolean(attachment)"
              placeholder="Välj eller släpp bilaga här"
              drop-placeholder="Släpp bilaga här"
            ></b-form-file>
         </b-form-group>
         
          </b-col>
        </b-row>
        <b-row>
          <b-col>
            
            <b-button
              to="./forhandsgranska"
              variant="primary"
              class="btn"
              id="btn-preview"
            >Förhandsgranska</b-button>
          </b-col>
        </b-row>
      </b-form>
    </b-container>
  </div>
</template>

<script>

import "bootstrap-vue/dist/bootstrap-vue.css";
export default {
  
  data() {
    return {
      formdata: {}
    };
  },

  computed: {
    category: {
      get() {
        return this.$store.state.formdata.category;
      },
      set(value) {
        this.$store.state.formdata.category = value;
      }
    },
    whenIssue: {
      get() {
        return this.$store.state.formdata.whenIssue;
      },
      set(value) {
        this.$store.state.formdata.whenIssue = value;
      }
    },
    whereIssue: {
      get() {
        return this.$store.state.formdata.whereIssue;
      },
      set(value) {
        this.$store.state.formdata.whereIssue = value;
      }
    },
    details: {
      get() {
        return this.$store.state.formdata.details;
      },
      set(value) {
        this.$store.state.formdata.details = value;
      }
    },
    employeeAwareness: {
      get() {
        return this.$store.state.formdata.employeeAwareness;
      },
      set(value) {
        this.$store.state.formdata.employeeAwareness = value;
      }
    },
    attachment: {
      get() {
        return this.$store.state.formdata.attachment;
      },
      set(value) {
        this.$store.state.formdata.attachment = value;
      }
    },
    setselectedCategory: {
      get() {
        return this.$store.state.selectedCategory;
      },
      set(value) {
        this.$store.state.selectedCategory = value;
      }
    }
  },
  created: async function() {
    await this.$store.dispatch("getCategories");
  }
};
</script>

<style scoped>
#select-what {
  text-overflow: ellipsis;
}
#main-form {
  padding-top: 10px;
}
#btn-preview {
  margin-top: 15px;
  margin-bottom: 15px;
}
#row-attachment {
  padding-top: 8px;
  justify-content: left;
  align-items: baseline;
}
#btn-attachment {
  background-color: white;
  color: rgb(46, 46, 46);
  border-radius: 10%;
}
#lbl-attachment {
  padding-left: 10px;
  justify-self: center;
  align-self: center;
  color: gray;
}
.inputbox {
  margin: 0px 0px 6px;
  padding: 0px;
}
.lbl {
  padding-top: 6px;
  margin-bottom: 0px;
}

.datePicker-when{
width: fit-content;
}



</style>
