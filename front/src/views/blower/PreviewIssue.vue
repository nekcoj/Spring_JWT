<template>
  <div class="new-issue">
    <b-container class="container">
      <b-form id="main-form"><p class="h2">Anmälan om oegentligheter</p>

        <b-row class="mt-2">
          <b-col sm="12">
            <label class="lbl" for="select-what">Vad gäller ärendet?(*)</label>
          </b-col>
          <b-col sm="12">
            <b-form-select class="inputbox" id="select-what" v-model="formdata.what"
              :options="categories" required>
            </b-form-select>
          </b-col>
        </b-row>

        <b-row class="mt-2">
          <b-col sm="12">
            <label class="lbl" for="textarea-when">När inträffade händelsen?(*)</label>
          </b-col>
          <b-col sm="12">
            <b-form-textarea class="inputbox" id="textarea-when" required v-model="formdata.when">
            </b-form-textarea>
          </b-col>
        </b-row>

        <b-row class="mt-2">
          <b-col sm="12">
            <label class="lbl" for="textarea-where">Var inträffade händelsen?(*)
            </label>
          </b-col>
          <b-col sm="12">
            <b-form-textarea class="inputbox" id="textarea-where" required v-model="formdata.where">
            </b-form-textarea>
          </b-col>
        </b-row>

        <b-row class="mt-2">
          <b-col sm="12">
            <label class="lbl" for="textarea-details">Detaljer om ärendet(*)
            </label>
          </b-col>
          <b-col sm="12">
            <b-form-textarea class="inputbox" id="textarea-details" required v-model="formdata.details">
            </b-form-textarea>
          </b-col>
        </b-row>

        <b-row class="mt-2">
          <b-col sm="12">
            <label class="lbl" for="textarea-knowledge">Är andra anställda medvetna om detta?(*)
            </label>
          </b-col>
          <b-col sm="12">
            <b-form-textarea class="inputbox" id="textarea-knowledge" required v-model="formdata.knowledge">
            </b-form-textarea>
          </b-col>
        </b-row>

        <b-row class="d-flex" id="row-attachment">
          
          <b-col sm="3">
            <b-button id="btn-attachment">Välj fil</b-button>
          </b-col>
          <b-col sm="9"><label id="lbl-attachment" for="btn-attachment">Bilaga (Frivilligt)</label>
          </b-col>
        </b-row>
        <b-row>
          <b-col>
            <b-button to="./confirmation" variant="primary" class="btn" id="btn-preview">Skicka</b-button>
          </b-col>
        </b-row>

        </b-form>
    </b-container>
  </div>
</template>

<script>
export default {
  data() {
    return {
      formdata: {}
    };
  }
};
</script>

<style scoped>
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
  padding-left:10px;
  justify-self: center;
  align-self: center;
  color:gray;
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
