<template>
<div class="dashboard container" id="dashboard-container">
<b-card id="dashboard-content">
    <b-card-header id="dashboard-header">
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
    <b-card-body id="dashboard-body">
        <router-view>

                </router-view>
            </b-card-body>
        </b-card>
    </div>
</template>
<script>
import {statusUnassigned} from '@/_helpers/config.js'
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
            if(value.status.toUpperCase() === `${statusUnassigned}`.toUpperCase()){
                this.selectedStatus = value;
            }
        }
    }
  },
  
}
</script>


<style scoped>
#dashboard-container, #dashboard-content {
    border: none;
}

#dashboard-header{
    border: 1px solid black;
    border-bottom: none;
}

#dashboard-body{
    border: 1px solid black;
}

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