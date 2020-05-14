<template>
   <div class="safepostbox container">
    <div role="tablist">
    <b-card no-body class="mb-1 text-left" v-for="item in items" :key="item.id">
      <b-card-header header-tag="header" class="p-1" role="tab">
        <b-button 
        block 
        v-b-toggle="item.id"
        variant="secondary">
        Meddelande nr {{item.id}}</b-button>
      </b-card-header>
      <b-collapse 
      :id=item.id 
      accordion="accord"
      role="tab" >
        <b-card-body>
          <b-card-text><p class="h4">Du skrev: </p>
          <p class="p-small">{{ item.question }}</p></b-card-text>
                    <div>
                        <p class="h4"> Svar från anmälaren: </p>
            <b-form-text 
              v-model="item.answer"
                id="textarea-rows"
                rows="5"
            >
            <p class="p-small">{{item.answer}}</p>
            </b-form-text>
            </div>
        </b-card-body>
      </b-collapse>
    </b-card>
    <div id="new-message">
      <p class="h3">Skicka nytt meddelande</p>
      <label for="new-message-header">Rubrik</label>
      <b-textarea id="new-message-header" rows="1"></b-textarea>

    </div>
    <router-link to="../jurist">
    <b-button id="safepost-blower-button-back" variant="primary">Tillbaka</b-button>
    </router-link>
  </div>
</div>  
</template>

<script>
export default {
    data(){
        return{
            items: {
              1: {
                  id: "1",
                  question: `Hej, jag har lite frågor angående den där grejen... 
                  Hade du verkligen inte borstat tänderna på morgonen?`,
                  answer: "Här är mitt svar, från mig, anmälaren! Jag hade inte borstat tänderna."
            },
              2: {
                  id: "2",
                  question: `Varför inte?`,
                  answer: "Det bara blev så."
            },
              3: {
                  id: "3",
                  question: `Illa. Gör inte om det.`,
                  answer: "Okej, jag lovar."
            }
            },  
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