<template>
  <div class="whistle">
    <div>
      <b-button variant="primary" class="btn-selection btn-lg" id="btn-new-issue">Skapa nytt ärende</b-button>
    </div>
    <div>
      <b-button variant="primary" class="btn-selection btn-lg" id="btn-followup">Följ upp ärende</b-button>
    </div>
  </div>
</template>

<style scoped>

.whistle{
  height: 80vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
  
}

.btn-selection{
margin-bottom: 20px;
margin-top: 20px;
}


</style>