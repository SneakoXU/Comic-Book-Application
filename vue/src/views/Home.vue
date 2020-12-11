<template>
  <div class="home"> 
    <h1>Welcome to BT Comics</h1>  
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

 .home {
   color: white;
   text-shadow: 2px 2px black;
   text-align: center;
 }

 h1{
   font-size: 70px;
 }
 
</style>
