<template>
<b-card id="dashboard">
    <b-card-header class="dashboard-container">
        <b-nav pills fill type="light" variant="light">
            <b-nav-item :to="{ path: '/lawyer'}" exact-active-class="active">
                Panel
            </b-nav-item>
            <b-nav-item  exact-active-class="active" @click="setStatus()">
                Nya ärenden <span id="nr-of-messages-lawyer">{{nrMessagesLawyer}}</span>
            </b-nav-item>
            <b-nav-item to="/inloggning" @click="logout" exact-active-class="active">
                Logga ut
            </b-nav-item>
        </b-nav>
    </b-card-header>    
    <b-card-body class="dashboard-body">
        <router-view>

        </router-view>
    </b-card-body>
</b-card>
</template>
<script>

import {statusAssigned} from '@/_helpers/config.js'
export default {
  data() {
    return {
        issue:{},
        messages: {},
        nrMessagesLawyer: 0,
        selectedStatus: {},
        statuses: [],
    }
  },
  methods: {
    logout(){
         const { dispatch } = this.$store;
         dispatch('account/logout');
    },

    setStatus() {
        for(const [, value] of Object.entries(this.statuses)){
            if(value.status === `${statusAssigned}`){
                this.selectedStatus = value;
            }
        }
    }
  },
  
}
</script>


<style scoped>

#nr-of-messages-lawyer{
    font-size: small!important;
    color: black;
    position: relative;
    bottom: 5px;
    font-weight: bold;
}

#dashboard {
    border: none;
}

.dashboard-container{
    border: 1px solid black;
    border-bottom: none;
}

.dashboard-body{
    border: 1px solid black
}

@media (min-width: 576px) {  
  
}
 
/* Medium devices (tablets, 768px and up) The navbar toggle appears at this breakpoint */
@media (min-width: 768px) {  

}
 
/* Large devices (desktops, 992px and up) */
@media (min-width: 992px) { 
  .dashboard-container, .dashboard-body{
    width: 50%;
    margin-left: auto;
    margin-right: auto;
  }
}
 
/* Extra large devices (large desktops, 1200px and up) */
@media (min-width: 1200px) {  
    
}


</style>