<template>
  <div class="new-issue">
    <b-container class="container">
      
      <b-form id="main-form"><p class="h2">Anmälan om oegentligheter</p><p class="h4">Förhandsgranska</p>

        <b-row class="mt-2">
          <b-col sm="12">
            <label class="lbl" for="select-what">Vad gäller ärendet?(*)</label>
          </b-col>
          <b-col sm="12">
            <b-form-text class="inputbox" id="select-what">Mutor, korruption & förfalskning
            </b-form-text>
          </b-col>
        </b-row>

        <b-row class="mt-2">
          <b-col sm="12">
            <label class="lbl" for="textarea-when">När inträffade händelsen?(*)</label>
          </b-col>
          <b-col sm="12">
            <b-form-text class="inputbox" id="textarea-when">I förrgår.
            </b-form-text>
          </b-col>
        </b-row>

        <b-row class="mt-2">
          <b-col sm="12">
            <label class="lbl" for="textarea-where">Var inträffade händelsen?(*)
            </label>
          </b-col>
          <b-col sm="12">
            <b-form-text class="inputbox" id="textarea-where">På kontoret.
            </b-form-text>
          </b-col>
        </b-row>

        <b-row class="mt-2">
          <b-col sm="12">
            <label class="lbl" for="textarea-details">Detaljer om ärendet(*)
            </label>
          </b-col>
          <b-col sm="12">
            <b-form-text class="inputbox" id="textarea-details">Jag fick 100000000 kr för att göra en grej. Sen kom jag på att det kanske var en olaglig grej. Ooops, liksom. Sånt som händer. No harm no foul.
            </b-form-text>
          </b-col>
        </b-row>

        <b-row class="mt-2">
          <b-col sm="12">
            <label class="lbl" for="textarea-knowledge">Är andra anställda medvetna om detta?(*)
            </label>
          </b-col>
          <b-col sm="12">
            <b-form-text class="inputbox" id="textarea-knowledge">Nej.
            </b-form-text>
          </b-col>
        </b-row>

        <b-row class="d-flex" id="row-attachment">
          
          <b-col ><span id="col-attachment-text" >Bilaga:</span>
          </b-col>
          <b-col id="col-attachment-filename"><label id="lbl-attachment">Bilaga.txt</label>
          </b-col>

        </b-row>

        <b-row class="d-flex" id="row-verify">
          <b-col></b-col>
          <b-col>
            <b-button to="./nyttarende" variant="primary" class="btn" id="btn-back">
              Tillbaka
            </b-button></b-col>
          <b-col >
            <b-button to="./bekraftelse" variant="primary" class="btn" id="btn-verify">
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
      formdata: {}
    };
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
