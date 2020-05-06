<template>
    <div id="vissla-login">
        <b-form id="vissla-form" @submit="onSubmit">
            <div class="form-group">
                <span>Ange det tillfälliga ID du blev tilldelad när du skapade ärendet(*):</span>
                <b-form-input
                    id="vissla-ID"
                    type="text"
                    v-model="form.username"
                    required
                    placeholder="ID"
                ></b-form-input>
            </div>
            <div class="form-group">
                <span>Ange det tillfälliga ID du blev tilldelad när du skapade ärendet(*):</span>
                <b-form-input
                    id="vissla-PW"
                    type="password"
                    v-model="form.password"
                    required
                    placeholder="Lösenord"
                ></b-form-input>
            </div>
            <div class="form-group text-center">
                <b-button variant="primary" id="btn-vissla-login" class="btn btn-lg" type="submit">Logga in</b-button>
            </div>
            
        </b-form>
    </div>
</template>
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

</style>

<script>

export default {
  data() {
    return {
      form: {
        username: '',
        password: '',
      }
    }
  }, 
    //detta är gammalt bös jag klippt från förra projektet
    /*methods: {
        async springLogin() {
            let username = this.form.username;
            let password = this.form.password;
            console.log(username + ' ' + password)
            const credentials =
            "username=" +
            encodeURIComponent(username) +
            "&password=" +
            encodeURIComponent(password);
            let response = await fetch("/rest/login", {
                method: "POST",
                redirect: "manual",
                headers: { "Content-Type": "application/x-www-form-urlencoded" },
                body: credentials
            });


            if(response.url.includes('error')){
                console.log('Fel!')
            } else {
                console.log("Success")
                this.$store.dispatch("isUserLoggedIn")
                console.log(response);
                this.$router.push('/profile');
            }
        },
        onSubmit(evt) {
            evt.preventDefault()
            //alert(JSON.stringify(this.form))
            this.springLogin()
        }
    }
    */
}
</script>