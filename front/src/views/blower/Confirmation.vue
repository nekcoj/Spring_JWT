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
      <span>
        <p class="h5">Tillfälligt ID:</p>
        <p>aoeusnth</p>
        <p class="h5">Tillfälligt lösenord:</p>
        <p>iueodnth</p>
        </span>
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
    <b-btn variant="success" id="btn-all-done" class="btn">Stäng fönstret</b-btn>
    </div>
        
  </div>
</template>

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
<script>
export default {
  methods: {}
}
</script>