<template>
  <b-card id="response-box" class="mx-auto">
    <b-alert show variant="info">
      {{this.$store.state.fetchResponse}}
    </b-alert>
  </b-card>
</template>

<script>
export default {
  
}
</script>

<style scoped>
@media (min-width: 576px) {  
  #response-box {
    width: 75%;
  }
}
 
/* Medium devices (tablets, 768px and up) The navbar toggle appears at this breakpoint */
@media (min-width: 768px) {  

}
 
/* Large devices (desktops, 992px and up) */
@media (min-width: 992px) { 
 #response-box {
   width: 50%
 }
}
 
/* Extra large devices (large desktops, 1200px and up) */
@media (min-width: 1200px) {  
    
}
</style>