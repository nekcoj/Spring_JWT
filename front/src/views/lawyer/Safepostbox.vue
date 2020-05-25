<template>
     <b-card>
     <b-card-header class="safepostbox container">
        <b-nav pills fill type="light" variant="light">
            <b-nav-item :to="{ path: '/lawyer/'}" exact-active-class="active">
                Panel
            </b-nav-item>
            <b-nav-item :to="{ path: '/jurist/arenden'}" exact-active-class="active">
                Nya ärenden
            </b-nav-item>
            <b-nav-item to="/loggaut" exact-active-class="active">
                Logga ut
            </b-nav-item>
        </b-nav>
    </b-card-header>  
    <b-card-body>
      <p class="h2">Ärendenr: 293229</p>
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
      <b-button id="new-message-button" class="mt-3 mb-3" variant="primary">Skicka</b-button>

    </div>
    <router-link to="/jurist">
    <b-button id="safepost-back-button" variant="secondary">Tillbaka</b-button>
    </router-link>
  </div>
  
    </b-card-body>
     </b-card>
     
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
    },


    

}
</script>

<style>


</style>