<template>
  <div class="dash">
    <div>
      <router-link to="./safepostbox">
        <b-button variant="primary" class="btn-selection btn-lg" id="btn-new-issue">Safe postbox</b-button>
      </router-link>
    </div>
    <div>
      <router-link to="./loggaut">
        <b-button variant="danger" class="btn-loggaut btn-selection btn-lg" id="btn-followup">Logga ut</b-button>
        <!--ok byt till den här då 
        <b-button variant="primary" class="btn-selection btn-lg" id="btn-followup">Logga ut</b-button>
        -->
        </router-link>
    </div>
  </div>
</template>

<style scoped>

.dash{
  height: 80vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
  
}
.btn-loggaut{
  display:inline-block;
  min-height:100px;
  border-radius:100%;
  /*background-color:rgb(224, 35, 35);*/
}

.btn-selection{
margin-bottom: 20px;
margin-top: 20px;
}


</style>