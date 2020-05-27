<template>
<b-card>
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
    <b-card-body>
        <router-view>

        </router-view>
    </b-card-body>
</b-card>
</template>
<script>
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
            if(value.status.toLowerCase() === 'assigned'){
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


</style>