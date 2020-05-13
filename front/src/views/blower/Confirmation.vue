<template>
  <div id="confirmation" class="container">
    <div id="confirmation-header">
      <span>
        <p class="h3"><em>Ditt ärende har mottagits.</em></p>
        <p class="h4"> Inom ca 10 dagar skickar ansvarig jurist ett svar eller eventuella följdfrågor till safe postboxen.
        </p>
      </span>

    </div>
    <div id="id-and-pw">
 
        <p class="h5">Tillfälligt ID:</p>
        <p>{{this.$store.state.temporaryUser.username}}</p>
        <p class="h5">Tillfälligt lösenord:</p>
        <p>{{this.$store.state.temporaryUser.password}}</p>
        
    </div>
    <div id="id-and-pw-info">
      <span>
        <p>Tillfälligt ID och tillfälligt lösenord kan endast användas så länge ärendet ej är avslutat.
          Tänk på att spara ditt tillfälliga ID och tillfälliga lösenord på en säker plats,
          då dessa av säkerhetsskäl ej kan återställas.
        </p>
      </span>
    </div>
    <div id="all-done">
    <input type="button" onclick="window.opener=null; window.close(); return false;" value="Stäng fönstret" class="btn btn-primary">
    </div>
        
  </div>
</template>

<script>
export default {
  data() {
    return {
      temporaryUser:{}
    }
  },
  methods: {},
  computed:{},
}
</script>

<style scoped>
#confirmation{
  height: 80vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
  max-width: 80vw;

}
#confirmation-header{
  margin-top:50px;
}
</style>