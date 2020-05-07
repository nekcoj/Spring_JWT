<template>
  <div class="new-issue">
    <section class="container">
      <b-form id="main-form"><p class="h2">Anmälan om oegentligheter</p>

        <b-row class="mt-2">
          <b-col sm="2">
            <label class="lbl" for="select-what">Vad gäller ärendet?(*)</label>
          </b-col>
          <b-col sm="10">
            <b-form-select class="inputbox" id="select-what" v-model="formdata.what"
              :options="categories" required>
            </b-form-select>
          </b-col>
        </b-row>

        <b-row class="mt-2">
          <b-col sm="2">
            <label class="lbl" for="textarea-when">När inträffade händelsen?(*)</label>
          </b-col>
          <b-col sm="10">
            <b-form-textarea class="inputbox" id="textarea-when" v-model="formdata.when">
            </b-form-textarea>
          </b-col>
        </b-row>

        <b-row class="mt-2">
          <b-col sm="2">
            <label class="lbl" for="textarea-where">Var inträffade händelsen?(*)
            </label>
          </b-col>
          <b-col sm="10">
            <b-form-textarea class="inputbox" id="textarea-where" v-model="formdata.where">
            </b-form-textarea>
          </b-col>
        </b-row>

        <b-row class="mt-2">
          <b-col sm="2">
            <label class="lbl" for="textarea-details">Detaljer om ärendet(*)
            </label>
          </b-col>
          <b-col sm="10">
            <b-form-textarea class="inputbox" id="textarea-details" v-model="formdata.details">
            </b-form-textarea>
          </b-col>
        </b-row>

        <b-row class="mt-2">
          <b-col sm="2">
            <label class="lbl" for="textarea-knowledge">Är andra anställda medvetna om detta?(*)
            </label>
          </b-col>
          <b-col sm="10">
            <b-form-textarea class="inputbox" id="textarea-knowledge" v-model="formdata.knowledge">
            </b-form-textarea>
          </b-col>
        </b-row>

        <b-row class="d-flex" id="row-attachment">
          <b-col sm="2">
            <b-button id="btn-attachment">Välj fil</b-button>
          </b-col>
          <b-col><label id="lbl-attachment" for="btn-attachment">Bilaga (Frivilligt)</label>
          </b-col>
        </b-row>
        <b-row>
          <b-col>
            <b-button variant="primary" class="btn" id="btn-preview">Förhandsgranska</b-button>
          </b-col>
        </b-row>

        
          <!-- <span><p>Var inträffade händelsen?</p></span>
          <div class="row form-group">
            <b-form-textarea type="text">
            </b-form-textarea>
          </div>
            <span><p>Detaljer om händelsen</p></span>
            <div class="row form-group">
              <b-form-textarea type="text">
              </b-form-textarea>
            </div>
            <span><p>När inträffade händelsen?</p></span>
          <div class="row form-group">
            <b-form-textarea type="text">
            </b-form-textarea>
          </div>-->
        </b-form>
    </section>
  </div>
</template>

<script>
export default {
  data() {
    return {
      formdata: {},
      categories: ["Mutor, korruption & förfalskning"]
    };
  }
};
</script>

<style scoped>
#main-form{
  padding-top:10px;
}
#btn-preview{
  margin-top:15px;
  margin-bottom:15px;
}
#row-attachment{
  padding-top: 8px;
  align-items: baseline;
}
#btn-attachment{
  background-color:white;
  color: rgb(46, 46, 46);
  border-radius: 10%;
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
/* #new-issue{
  display:flex;
  justify-content:center;
} */
</style>
