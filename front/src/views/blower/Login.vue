<template>
  <div class="container">
    <div id="vissla-login">
      <b-form id="vissla-form">
        <div class="form-group">
          <span>Ange det tillfälliga ID du blev tilldelad när du skapade ärendet(*):</span>
          <b-form-input
            id="vissla-ID"
            type="text"
            v-model="loginCredentials.username"
            required
            placeholder="ID"
          ></b-form-input>
        </div>
        <div class="form-group">
         <span>Ange det tillfälliga lösenord du blev tilldelad när du skapade ärendet(*):</span>
          <b-form-input
            id="vissla-PW"
            type="password"
            v-model="loginCredentials.password"
            required
            placeholder="Lösenord"
          ></b-form-input>
        </div>
        <em><span class="liten-text">* obligatoriska fält</span></em>
        <div class="form-group text-center">
          <b-button variant="primary" id="btn-vissla-login" class="btn btn-lg" @click="login">Logga in</b-button>
        </div>
      </b-form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      loginCredentials: {
        username: "",
        password: ""
      },
      submitted: false
    }
  },
  methods:{
    login: async function(){
      this.submitted = true;
      const { dispatch } = this.$store;
        if (this.loginCredentials.username && this.loginCredentials.password) {
          dispatch('account/login', this.loginCredentials)              
        }
    },
  }
}
</script>

<style scoped>
 
#vissla-login #vissla-ID{
  border: 1px solid black;
}
#vissla-login #vissla-PW{
  border: 1px solid black;
}
 #vissla-login #btn-vissla-login{
  width:120px;
} 
span.liten-text{
  margin-top: -10px;
  font-size:80%;
}

#vissla-login{
    margin-top: 15%;
    width: 100%;
}

@media (min-width: 576px) {  
  
}
 
/* Medium devices (tablets, 768px and up) The navbar toggle appears at this breakpoint */
@media (min-width: 768px) {  

}
 
/* Large devices (desktops, 992px and up) */
@media (min-width: 992px) { 
  #vissla-login{
    margin-top: 7%;
    width: 50%;
    margin-left: auto;
    margin-right: auto;
  }
}
 
/* Extra large devices (large desktops, 1200px and up) */
@media (min-width: 1200px) {  
    
}
</style>
