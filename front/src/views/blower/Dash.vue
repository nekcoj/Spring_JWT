<template>
  <div class="dash container">
    <div class="row">
      <div class="col-md-12">
        <p id="issueStatus">Status på ärendet: {{issueStatus}}</p>
        <router-link :to="{ path: '/safepostbox'}" v-if="messages.length">
          <b-button variant="primary" class="btn-selection btn-lg" id="btn-new-issue">Safe postbox</b-button>
        </router-link>
      </div>
      <div class="col-md-12">
        <router-link to="./vissla">
          <b-button variant="danger" class="btn-loggaut btn-selection btn-lg" @click="logout" id="btn-followup">Logga ut</b-button>
          <!--ok byt till den här då 
          <b-button variant="primary" class="btn-selection btn-lg" id="btn-followup">Logga ut</b-button>
          -->
          </router-link>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    data() {
      return {
      }
    },
    async mounted() {
      await this.$store.dispatch("getMessages")     
      await this.$store.dispatch("getIssueStatusForUser")
    },
    computed: {
      issueStatus() {
          return this.$store.state.issueStatusUser;
      },
      messages:{
        get(){
          return this.$store.state.messages;
        }
      }
    },
    methods:{

      logout(){
        const { dispatch } = this.$store;
        dispatch('account/logout');
      },

    }
  }
</script>

<style scoped>

.dash{
  height: 80vh;
  display: flex;
  flex-direction: column;
  justify-content: center; 
}

.btn-loggaut{
  display:inline-block;
  min-height:100px;
  border-radius:100%;
  /*background-color:rgb(224, 35, 35);*/
}

.btn-selection{
margin-bottom: 20px;
margin-top: 20px;
}

#issueStatus {
  font-size: 1.2em;
}
</style>