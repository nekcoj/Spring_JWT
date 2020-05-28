<template>
<div class="dashboard container">
<b-card class="m-0 p-0">
    <b-card-header>
        <b-nav pills fill type="light" variant="light">
            <b-nav-item :to="{ path: '/admin/'}" exact-active-class="active">
                Panel
            </b-nav-item>
             <b-nav-item  exact-active-class="active" @click="setStatusAdmin()">
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
import {statusAssigned} from '@/_helpers/config.js'
export default {
  data() {
    return {
        issue:{},
        messages: {},
        nrMessagesAdmin: 0,
        selectedStatus: {},
        statuses: [],
    }
  },
   methods: {
    logout(){
         const { dispatch } = this.$store;
         dispatch('account/logout');
    },

    setStatusAdmin() {
        for(const [, value] of Object.entries(this.statuses)){
            if(value.status.toLowerCase() === `${statusAssigned}`){
                this.selectedStatus = value;
            }
        }
    }
  },
  
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

</style>