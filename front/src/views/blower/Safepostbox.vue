<template>
    <div role="tablist">
    <b-card no-body class="mb-1" v-for="message in messages" :key="message.id">
      <b-card-header header-tag="header" class="p-1" role="tab">
        <b-button block v-b-toggle="'accordion'+message.id" variant="info">Meddelande från jurist</b-button>
      </b-card-header>
      <b-collapse :id="'accordion'+ message.id" visible accordion="my-accordion" role="tabpanel" >
        <b-card-body>
          <b-card-text id="messageFromLawyer">{{message.message}}</b-card-text>
          <div>
            <span id="answer-blower"> Svara: </span>
            <b-form-textarea v-model="messageToSend.message"
              id="textarea-rows"
              rows="5" 
            >{{messageToSend.message}}</b-form-textarea>
          </div>
          <b-button id="safepost-blower-button" :to="{ path: '/user'}" @click="sendReply(message.id)" variant="primary">Skicka svar</b-button>
        </b-card-body>
      </b-collapse>
    </b-card>
    <b-button id="safepost-blower-button-back" :to="{ path: '/user'}" variant="primary">Tillbaka</b-button>
  </div>
  
</template>

<script>
export default {
  data(){
    return{
      messageToSend: {
        message: "",
        postboxId: 0
      }

    }
  },

  computed: {
    messages: {
      get(){
        return this.$store.state.messages;
      }
    }
  },

  methods: {
    sendReply(value){
      this.messageToSend.postboxId = value;
      this.$store.commit("setMessageBody", this.messageToSend)
      this.$store.dispatch("sendReply")
    }
  }

}
</script>

<style>

#safepost-blower-button{
  margin-top: 1.5em;
}

#safepost-blower-button-back{
  margin-top: 5em;  
}

#answer-blower{
  display: flex;
}

#messageFromLawyer{
  text-align: left;
}

</style>