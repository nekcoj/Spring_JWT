<template>
    <div role="tablist">
    <b-card no-body class="mb-1" v-for="message in messages" :key="message.id">
      <b-card-header header-tag="header" class="p-1" role="tab">
        <b-button block v-b-toggle.accordion-1 variant="info">Meddelande från jurist</b-button>
      </b-card-header>
      <b-collapse id="accordion-1" visible accordion="my-accordion" role="tabpanel" >
        <b-card-body>
          <b-card-text>{{ items.answer }}</b-card-text>
            <div>
                <span id="answer-blower"> Svara: </span>
              <b-form-textarea v-model="reply"
                id="textarea-rows"
                rows="5" 
              >{{reply}}</b-form-textarea>
            </div>
             <b-button id="safepost-blower-button" variant="primary">Skicka svar</b-button>
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
      items:{
        answer: `Halloj, jag har lite frågor angående det där som du sa om den där grejen... 
        Hade du verkligen inte borstat tänderna på morgonen?`,
      },
      reply: ''  
    }
  },
  mounted() {},
  computed: {
    messages: {
      get(){
        return this.$store.state.messages;
      }
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

</style>