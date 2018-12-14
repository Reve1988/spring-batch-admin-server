<template>
  <div id="app">
    <navigation></navigation>
    <contents></contents>
  </div>
</template>

<script>
  import Navigation from "./components/Navigation";
  import Contents from "./components/Contents";

  export default {
    name: 'App',
    components: {
      Navigation,
      Contents
    }
  }
</script>

<style>
  @import "assets/css/wro.css";
</style>
