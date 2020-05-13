<template>
  <div class="container">
    <div id="login-lawyer">
      <b-form @submit.stop.prevent>
        <div>
          <label for="text-username">Användarnamn (*):</label>
          <b-input type="text" id="text-username" v-model="form.usernameLawyer"></b-input>
        </div>
        <div>
          <label for="text-password">Lösenord (*):</label>
          <b-input :type="form.passwordFieldType" id="text-password" v-model="form.passwordLawyer"></b-input>
          <b-form-checkbox name="check-button" @change="showPassword()">Visa lösenord</b-form-checkbox>
        </div>
        <em>(*) Obligatoriska fält</em>
        <p id="text-capslock" style="display:none"> Caps lock är aktiverat</p>
      </b-form>
    </div>
     <b-button href="/" v-b-modal.modal-center id="lawyer-login-button">Logga in</b-button> 
    <b-modal id="modal-center" centered title="GDPR" ok-title="Godkänn" cancel-title="Stäng">
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
        usernameLawyer: "",
        passwordLawer: "",
        passwordFieldType:"password"
      },
      
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
    }
  }
}

</script>

<style>
#lawyer-login * {
  display: flex;
  flex-direction: column;
  justify-content: center;
  width: 80vw;
  align-self: center;
}

#lawyer-login-button{
    margin-top: 2%;
}

#text-capslock{
    color: red;
}

</style>