<template>
<div class="dashboard container">
<b-card class="m-0 p-0">
    <b-card-header>
        <b-nav pills fill type="light" variant="light">
            <b-nav-item :to="{ path: '/admin/'}" exact-active-class="active">
                Panel
            </b-nav-item>
            <b-nav-item :to="{ path: '/admin/arenden'}" exact-active-class="active">
                Nya ärenden <span id="nr-of-messages">{{nrMessagesAdmin}}</span>
            </b-nav-item>
            <b-nav-item to="/inloggning" @click="logout" exact-active-class="active">
                Logga ut
            </b-nav-item>
        </b-nav>
    </b-card-header>    
    <b-card-body class="m-0 p-0">
        <router-view>

        </router-view>
    </b-card-body>
</b-card>
</div>
</template>
<script>
export default {
  data() {
    return {
        nrMessagesAdmin: 1
    }
  },
  methods: {
    logout(){
        const { dispatch } = this.$store;
        dispatch('account/logout');
    },

  }
}
</script>


<style scoped>

#nr-of-messages{
    font-size: small!important;
    color: black;
    position: relative;
    bottom: 5px;
    font-weight: bold;
}

@media (min-width: 576px) {  
  
}
 
/* Medium devices (tablets, 768px and up) The navbar toggle appears at this breakpoint */
@media (min-width: 768px) {  

}
 
/* Large devices (desktops, 992px and up) */
@media (min-width: 992px) { 
  .dashboard{
    width: 50%;
    margin-left: auto;
    margin-right: auto;
  }
}
 
/* Extra large devices (large desktops, 1200px and up) */
@media (min-width: 1200px) {  
    
}

</style>