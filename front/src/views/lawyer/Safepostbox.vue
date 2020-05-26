<template>
  <div>
    <b-card>  
      <b-card-body>
        <p class="h2">Ärendenr: {{issue.issueId}}</p>
        <div role="tablist">
          <b-card no-body class="mb-1 text-left" v-for="(item, index) in filteredMessages" :key="item.id">
            <b-card-header no-body header-tag="header" class="p-1" role="tab">
              <b-button 
              block 
              v-b-toggle="'id'+ item.id"
              :variant="checkReply(item)">
              Meddelande nr {{(parseInt(index) + 1)}}
              </b-button>
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
                  <b-form-text>
                    <p class="p-small">{{item.reply}}</p>
                  </b-form-text>
                </div>
              </b-card-body>
            </b-collapse>
          </b-card>
          <div id="new-message">
            <label for="new-message-header" class="h3 pt-2">Skicka nytt meddelande</label>
            <b-textarea id="new-message-header" rows="1" v-model="messageToSend.message"></b-textarea>
            <b-button id="new-message-button" class="mt-3 mb-3" variant="primary" @click="sendMessage()">Skicka</b-button>
          </div>
          <router-view v-if="this.$store.state.fetchResponse != ''"></router-view>
          <router-link to="/lawyer">
            <b-button id="safepost-back-button" class="mt-3 mb-3" variant="secondary">Tillbaka</b-button>
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
          for(const [, v] of Object.entries(value)){
            for(const [j, val] of Object.entries(v)){
              if(j === 'messages'){
                Object.assign(filteredMessages, val)
              }
            }
          }
        }
      }      
      return filteredMessages
    }
  },
  methods:{
    sendMessage(){
      this.$store.state.fetchResponse = "";
      this.messageToSend.tempUserId = this.issue.tempUserId;      
      this.$store.commit("setMessageBody", this.messageToSend)
      this.$store.dispatch("sendMessageToUser")
    },
    checkReply: function(item){      
      if(item.reply === null){
        return 'info';  
      } else {
        return 'secondary';
      }
    }
  }
}
</script>

<style>

@media (min-width: 576px) {  

}
 
/* Medium devices (tablets, 768px and up) The navbar toggle appears at this breakpoint */
@media (min-width: 768px) {  
  #new-message-button, #safepost-back-button{
    width: 100%;  
  }
}
 
/* Large devices (desktops, 992px and up) */
@media (min-width: 992px) { 
  #new-message-button, #safepost-back-button{
    width: 25%;  
  }
}
 
/* Extra large devices (large desktops, 1200px and up) */
@media (min-width: 1200px) {
}

</style>