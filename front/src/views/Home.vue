<template>
  <div class="home">
    <div>
      <b-button v-on:click="openwindow" variant="primary" class="btn btn-lg btn-selection">Vissla</b-button>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src

export default {
  name: 'Home',
  components: {
  }
  ,
  methods: {
    openwindow: function(){
      window.open("/vissla", "Safe window", "height=600,width=420")
    }
  }
}
</script>

<style scoped>
.home{
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