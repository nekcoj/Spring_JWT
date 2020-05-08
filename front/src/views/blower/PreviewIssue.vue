<template>
  <div class="new-issue">
    <b-container class="container">
      
      <b-form id="main-form"><p class="h2">Anmälan om oegentligheter</p><p class="h4">Förhandsgranska</p>

        <b-row class="mt-2">
          <b-col sm="12">
            <label class="lbl" for="select-category">Vad gäller ärendet?(*)</label>
          </b-col>
          <b-col sm="12">
            <b-form-text class="inputbox" id="select-category">{{category}}
            </b-form-text>
          </b-col>
        </b-row>

        <b-row class="mt-2">
          <b-col sm="12">
            <label class="lbl" for="textarea-whenIssue">När inträffade händelsen?(*)</label>
          </b-col>
          <b-col sm="12">
            <b-form-text class="inputbox" id="textarea-whenIssue">{{whenIssue}}
            </b-form-text>
          </b-col>
        </b-row>

        <b-row class="mt-2">
          <b-col sm="12">
            <label class="lbl" for="textarea-whereIssue">Var inträffade händelsen?(*)
            </label>
          </b-col>
          <b-col sm="12">
            <b-form-text class="inputbox" id="textarea-whereIssue">{{whereIssue}}
            </b-form-text>
          </b-col>
        </b-row>

        <b-row class="mt-2">
          <b-col sm="12">
            <label class="lbl" for="textarea-details">Detaljer om ärendet(*)
            </label>
          </b-col>
          <b-col sm="12">
            <b-form-text class="inputbox" id="textarea-details">{{details}}
            </b-form-text>
          </b-col>
        </b-row>

        <b-row class="mt-2">
          <b-col sm="12">
            <label class="lbl" for="textarea-employeeAwareness">Är andra anställda medvetna om detta?(*)
            </label>
          </b-col>
          <b-col sm="12">
            <b-form-text class="inputbox" id="textarea-employeeAwareness">{{employeeAwareness}}
            </b-form-text>
          </b-col>
        </b-row>

        <b-row class="d-flex" id="row-attachment">
          
          <b-col ><span id="col-attachment-text" >Bilaga:</span>
          </b-col>
          <b-col id="col-attachment-filename"><label id="lbl-attachment">{{attachment}}</label>
          </b-col>

        </b-row>

        <b-row class="d-flex" id="row-verify">
          <b-col></b-col>
          <b-col>
            <b-button to="./nyttarende" variant="primary" class="btn" id="btn-back">
              Tillbaka
            </b-button></b-col>
          <b-col>
            <b-button to="./bekraftelse" variant="primary" class="btn" v-on:click="sendNewIssue">
              Skicka
            </b-button>
          </b-col>
          <b-col></b-col>

        </b-row>

        </b-form>
    </b-container>
  </div>
</template>

<script>
export default {
  data() {
    return {
      formdata: {},
      clicked: false
    };
  },
  methods:{
    sendNewIssue: async function(){
      if(this.clicked){
        return;
      }
      this.clicked = true;
      await this.$store.dispatch("newIssue", this.formdata);
      setTimeout(() => {
        this.clicked = false;
      }, 500);

    }
  },
  computed:{
    category:{
      get() {
        return this.$store.state.formdata.category;
      }
    },
    whenIssue:{
      get(){
        return this.$store.state.formdata.whenIssue;
      }
    },
    whereIssue:{
      get(){
        return this.$store.state.formdata.whereIssue;
      }
    },
    details:{
      get(){
        return this.$store.state.formdata.details;
      }
    },
    employeeAwareness:{
      get(){
        return this.$store.state.formdata.employeeAwareness;
      }
    },
    attachment:{
      get(){
        return this.$store.state.formdata.attachment;
      }
    },
  }
};
</script>

<style scoped>
.h2{
  padding-bottom:10px;
}
#select-what{
  text-overflow:ellipsis;
}
#main-form{
  padding-top:10px;
}
#row-attachment{
  padding-top: 8px;
  justify-content: left;
  align-items:baseline;
}
#lbl-attachment{
  padding-top:15px;
  padding-left:10px;
  justify-self: center;
  align-self: center;
  color:gray;
}
#col-attachment-text{
  display:flex;
  justify-content: right;
  padding-right:10px;
}
#col-attachment-filename{
  display:flex;
  justify-content: left;
}
.inputbox{
  margin: 0px 0px 6px;
  padding:0px;
}
.lbl{
  padding-top: 6px;
  margin-bottom: 0px;
}

</style>
