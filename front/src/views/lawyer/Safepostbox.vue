<template>
  <div class="conatiner-fluid">
    <b-card>  
      <b-card-body>
        <p class="h2">Ärendenr: {{issue.issueId}}</p>
        <div role="tablist">
          <b-card no-body class="mb-1 text-left" v-for="item in filteredMessages" :key="item.id">
            <b-card-header no-body header-tag="header" class="p-1" role="tab">
              <b-button 
              block 
              v-b-toggle="'id'+ item.id"
              variant="secondary">
              Meddelande nr {{item.id}}</b-button>
            </b-card-header>
            <b-collapse 
              :id="'id' + item.id" 
              accordion="accord"
              role="tab" >
              <b-card-body>
                <b-card-text>
                  <p class="h4">Du skrev: </p>
                  <p class="p-small">{{ item.message }}</p>
                </b-card-text>
                <div>
                  <p class="h4"> Svar från anmälaren: </p>
                  <b-form-text 
                    
                    id="textarea-rows"
                    rows="5"
                  >
                    <p class="p-small">{{item.reply}}</p>
                  </b-form-text>
                </div>
              </b-card-body>
            </b-collapse>
          </b-card>
          <div id="new-message">
            <label for="new-message-header" class="h3 pt-2">Skicka nytt meddelande</label>
            <b-textarea id="new-message-header" rows="1" v-model="messageToSend.message"></b-textarea>
            <b-button id="new-message-button" class="mt-3 mb-3 w-25" variant="primary" @click="sendMessage()">Skicka</b-button>
          </div>
          <router-link to="/lawyer">
            <b-button id="safepost-back-button" class="mt-3 mb-3 w-25" variant="secondary">Tillbaka</b-button>
          </router-link>
        </div>
      </b-card-body>
    </b-card>   
  </div>
</template>

<script>
export default {

  data(){
    return{
      messageToSend: {
        message: "",
        tempUserId: 0
      } 
    }
  },
  async created() {
    
  },
  computed:{
    issue:{
      get(){
        return this.$parent.issue;
      }
    },
    issues:{
      get(){
        return this.$store.state.issues;
      }
    },
    filteredMessages: function() {
      let messages = this.$store.state.messages;
      let issueId = this.issue.issueId;
      let filteredMessages = {}
      
      for( const [item, value] of Object.entries(messages)){
        let num = item.slice(5)
        if(num === issueId.toString()){
          console.log("value: ",value);
          for(const [i, v] of Object.entries(value)){
            console.log(i, v);
            for(const [j, val] of Object.entries(v)){
              if(j === 'messages'){
                console.log(j, val);
                Object.assign(filteredMessages, val)
              }
            }
          }
        }
      }
      console.log("filteredMessages: ", filteredMessages);
      
      return filteredMessages
    }
  },
  methods:{
    sendMessage(){
      this.messageToSend.tempUserId = this.issue.tempUserId;
      console.log(this.messageToSend.tempUserId);
      console.log(this.messageToSend.message);
      
      this.$store.commit("setMessageBody", this.messageToSend)
      this.$store.dispatch("sendMessageToUser")
    }
  }
}
</script>

<style>


</style>