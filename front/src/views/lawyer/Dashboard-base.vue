<template>
<div  class="dashboard container">
<b-card>
    <b-card-header>
        <b-nav pills fill type="light" variant="light">
            <b-nav-item :to="{ path: '/jurist/'}" exact-active-class="active">
                Panel
            </b-nav-item>
            <b-nav-item :to="{ path: '/jurist/arenden'}" exact-active-class="active">
                Nya ärenden
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
</div>
</template>
<script>
export default {
  data() {
    return {}
  },
  methods: {
    logout(){
        const { dispatch } = this.$store;
        dispatch('account/logout');
    }
  }
}
</script>


<style scoped>

</style>