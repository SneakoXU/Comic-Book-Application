<template>
  <div class="home">   
    <div class="container">
      <collection-card></collection-card>
    </div>
  </div>
</template>

<script>
import CollectionCard from "../components/CollectionCard.vue";

export default {
  name: "home",
  components: {
    CollectionCard
  }
};
</script>

<style>
  /* div.home {
    background-image: url("../../assets/Images/blue backgd.jpg");
    background-size: 100%;
    
    background-repeat: no-repeat;    
  } */

 
 
</style>
