<template>
  <b-card id="gdpr-box" class="mx-auto">
    <b-alert show variant="danger">
      Innan du kan logga in måste du godkänna att du tagit del av att vi behandlar dina uppgifter enligt GDPR.

      <b-button variant="primary" id="login-button" @click="gdprConsentAndLogin">Godkänn och Logga in</b-button> 
    </b-alert>
  </b-card>
</template>

<script>
export default {
  methods: {
    gdprConsentAndLogin: async function() {
      await this.$store.dispatch("updateGDPR")
      await this.$parent.login();
    }
  }
}
</script>

<style scoped>
@media (min-width: 576px) {  
  #gdpr-box {
    width: 75%;
  }
}
 
/* Medium devices (tablets, 768px and up) The navbar toggle appears at this breakpoint */
@media (min-width: 768px) {  

}
 
/* Large devices (desktops, 992px and up) */
@media (min-width: 992px) { 
 #gdpr-box {
   width: 50%
 }
}
 
/* Extra large devices (large desktops, 1200px and up) */
@media (min-width: 1200px) {  
    
}
</style>