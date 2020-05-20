<template>
<b-card>
    <b-card-header class="dashboard container">
        <b-nav pills fill type="light" variant="light">
            <b-nav-item :to="{ path: '/admin/'}" exact-active-class="active">
                Panel
            </b-nav-item>
            <b-nav-item :to="{ path: '/admin/arenden'}" exact-active-class="active">
                Nya ärenden <span id="nr-of-messages">{{nrMessagesAdmin}}</span>
            </b-nav-item>
            <b-nav-item to="/vissla" @click="logout" exact-active-class="active">
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

</style>