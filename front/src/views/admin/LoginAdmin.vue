<template>
  <div class="container">
    <div id="login-admin">
      <b-form @submit.stop.prevent>
        <div>
          <label for="text-username">Användarnamn (*):</label>
          <b-input type="text" id="text-username" v-model="loginCredentials.username"></b-input>
        </div>
        <div>
          <label for="text-password">Lösenord (*):</label>
          <b-input :type="form.passwordFieldType" id="text-password" v-model="loginCredentials.password"></b-input>
          <b-form-checkbox id="showPassword" name="check-button" @change="showPassword()">Visa lösenord</b-form-checkbox>
        </div>
        <span id="mandatory"><em>* obligatoriska fält</em></span>
        <p id="text-capslock" style="display:none"> Caps lock är aktiverat</p>
      </b-form>
    </div>
    <b-button variant="primary" id="login-button" @click="login">Logga in</b-button> 
    <div>
      <router-view v-if="checkLocalStorage()"></router-view>
    </div>
  </div>
</template>

<script>

export default {
  data() {
    return {
      form: {
        passwordFieldType:"password"
      },
      loginCredentials: {
        username: "",
        password: ""
      },
      submitted: false
    };
  },

  mounted(){
    let text = document.getElementById ('text-capslock')
    document.addEventListener("keyup", function(event){
      if(event.getModifierState('CapsLock')){
        text.style.display = "block"
      }else{
        text.style.display = "none"
      }
          
    })
  },
  methods:{
    showPassword: function() {
      this.form.passwordFieldType = this.form.passwordFieldType === 'password' ? 'text' : 'password'
    },

    login: async function(){
      this.submitted = true;
      const { dispatch } = this.$store;
      if (this.loginCredentials.username && this.loginCredentials.password) {
        dispatch('account/login', this.loginCredentials)              
      }
    },
    checkLocalStorage(){
      let user = localStorage.getItem('user')
      if(user != null && this.$store.state.gdprConsent === false){
        return true;
      }
      else {
        return false;
      }
    }

  }
}

</script>

<style>
#showPassword{
  text-align: left;
}

#login-button{
  margin-top: 40px;  
}

p#text-capslock{
  color: red;
  margin-bottom: 0;
}

#mandatory{
  margin-top: -10px;
  font-size:80%;  
}

#text-username{
  border: 1px solid black;
}

#text-password{
  border: 1px solid black;
}

#login-admin{
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
  #login-admin{
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