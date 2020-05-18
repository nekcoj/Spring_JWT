<template>
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
        <span class="liten-text">* obligatoriska fält</span>
        <div class="form-group text-center">
          <b-button variant="primary" id="btn-vissla-login" class="btn btn-lg" @click="login">Logga in</b-button>
        </div>
      </b-form>
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
      console.log("in login function");
      
      this.submitted = true;
      const { dispatch } = this.$store;
      
            if (this.loginCredentials.username && this.loginCredentials.password) {
                dispatch('account/login', this.loginCredentials)
            }
    },
    onSubmit: async function(){
      let url = "http://localhost:9090/login";

      const requestOptions = {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(this.loginCredentials),
      };

      const result = await fetch(url, requestOptions);

      let login = null;

      if(result.ok){
        login = await result.json();
        this.$store.state.tokenId = login.token;
        /**ANVÄND DETTA PÅ JURIST/ADMIN LOGIN MED!!*/
        this.$router.push({path: login.path})   
      }
  
      return login;
    },
  }
}
</script>

<style scoped>

#vissla-login{
  display:flex;
  flex-direction:column;
}
#vissla-login *{
  display: flex;
  flex-direction: column;
  justify-content: center;
  width:80vw;
  align-self:center;
}
#vissla-login #vissla-ID{
  border: 1px solid black;
}
#vissla-login #vissla-PW{
  border: 1px solid black;
}
#vissla-login #btn-vissla-login{
  width:30vw;
}
span.liten-text{
  margin-top: -10px;
  font-size:80%;
}
</style>
