<template>
    <div role="tablist">
    <b-card no-body class="mb-1" v-for="item in items" :key="item.id">
      <b-card-header header-tag="header" class="p-1" role="tab">
        <b-button 
        block 
        v-b-toggle="item.id"
        variant="info">
        Meddelande nr {{item.id}}</b-button>
      </b-card-header>
      <b-collapse 
      :id=item.id
      visible 
      accordion="accord"
      role="tab" >
        <b-card-body>
          <b-card-text><p>Du skrev: </p>{{ item.question }}</b-card-text>
                    <div>
                        <p> Svar från anmälaren: </p>
            <b-form-text 
              v-model="item.answer"
                id="textarea-rows"
                rows="5"
               
            >{{item.answer}}</b-form-text>
            </div>
        </b-card-body>
      </b-collapse>
    </b-card>
    <router-link to="../jurist">
    <b-button id="safepost-blower-button-back" variant="primary">Tillbaka</b-button>
    </router-link>
  </div>
  
</template>

<script>
export default {
    data(){
        return{
            items: {
              1: {
                  id: "1",
                  question: `Halloj, jag har lite frågor angående det där som du sa om den där grejen... 
                  Hade du verkligen inte borstat tänderna på morgonen?`,
                  answer: "Här är mitt svar, från mig, anmälaren! Jag hade inte borstat tänderna."
            },
              2: {
                  id: "2",
                  question: `Varför inte? ?`,
                  answer: "Det bara blev så."
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