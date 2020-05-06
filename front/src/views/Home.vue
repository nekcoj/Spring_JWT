<template>
  <div class="home">
    <b-button v-on:click="openwindow" class="btn btn-primary">Whistleblower</b-button>
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
      window.open("http://localhost:8080/whistle", "Safe window", "height=400,width=400")
    }
  }
}
</script>
