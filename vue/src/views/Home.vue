<template>
  <div class="home">
    <h1 id="header">Home</h1>
    <p>You must be authenticated to see this</p>
  </div>
</template>

<script>
export default {
  name: "home"
};
</script>

<style>
  div.home {
    background-image: url("../../assets/Images/blue backgd.jpg");
    background-size: 100%;
    background-repeat: no-repeat;    
  };
  #header {
    background-image: "../../assets/Images/nav bar.png";
  }
</style>
