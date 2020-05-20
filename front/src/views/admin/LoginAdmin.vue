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
          <b-form-checkbox name="check-button" @change="showPassword()">Visa lösenord</b-form-checkbox>
        </div>
        <em>(*) Obligatoriska fält</em>
        <p id="text-capslock" style="display:none"> Caps lock är aktiverat</p>
      </b-form>
    </div>
     <b-button variant="primary" id="login-button" @click="login">Logga in</b-button> 
    <b-modal id="modal-center"  v-if="!this.$store.state.gdprConsent" centered title="GDPR" ok-title="Godkänn" cancel-title="Stäng">
      <p class="my-4">
        Vi behandlar personuppgifter som inkommit
        till oss enligt bestämmelserna i dataskyddsförordningen,
        GDPR (General Data Protection Regulation).
      </p>
    </b-modal>
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

    }
  }
}

</script>

<style>

#login-admin{
  margin-top: 7%;
}


#login-button{
    margin-top: 40px;  
}

p#text-capslock{
    color: red;
    margin-bottom: 0;
}

</style>