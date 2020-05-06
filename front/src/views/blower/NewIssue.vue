<template>
  <div class="new-issue">
    <section class="container">
      <b-form>

        <b-row class="mt-2">
          <b-col sm="2">
            <label for="select-what">Vad gäller ärendet?(*)</label>
          </b-col>
          <b-col sm="10">
            <b-form-select id="select-what" v-model="formdata.what"
              :options="categories" required>
            </b-form-select>
          </b-col>
        </b-row>

        <b-row class="mt-2">
          <b-col sm="2">
            <label for="textarea-when">När inträffade händelsen?(*)</label>
          </b-col>
          <b-col sm="10">
            <b-form-textarea id="textarea-when" v-model="formdata.when">
            </b-form-textarea>
          </b-col>
        </b-row>

        <b-row class="mt-2">
          <b-col sm="2">
            <label for="textarea-where">Var inträffade händelsen?(*)
            </label>
          </b-col>
          <b-col sm="10">
            <b-form-textarea id="textarea-where" v-model="formdata.where">
            </b-form-textarea>
          </b-col>
        </b-row>

        <b-row class="mt-2">
          <b-col sm="2">
            <label for="textarea-details">Detaljer om ärendet(*)
            </label>
          </b-col>
          <b-col sm="10">
            <b-form-textarea id="textarea-details" v-model="formdata.details">
            </b-form-textarea>
          </b-col>
        </b-row>

        <b-row class="mt-2">
          <b-col sm="2">
            <label for="textarea-knowledge">Är andra anställda medvetna om detta?(*)
            </label>
          </b-col>
          <b-col sm="10">
            <b-form-textarea id="textarea-knowledge" v-model="formdata.knowledge">
            </b-form-textarea>
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
/* #new-issue{
  display:flex;
  justify-content:center;
} */
</style>
