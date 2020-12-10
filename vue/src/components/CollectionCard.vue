<template>
<div class="card">
  <h2 class="title">{{collection.title}}</h2>
  <h3 class="owner">By: {{collection.owner}}</h3>
  <div class="container">
    <img id="face" src="../../assets/Images/Captain_America_Comics_Vol_1_2.jpg" alt="">
    <div class="view">
      Click to view more
    </div>
  </div>
</div>
</template>

<script>



export default {  
  name: 'collection-card',  
  data() {
    return {
      collection:{
        title: 'Test',
        owner: 'Me',
        dateCreated: '',
      }
    }
  },
  methods: {
    getCollections() {

    }
  }  

}
</script>

<style>
  div.card{
    display: flex;
    flex-direction: column;
    align-items: center;
    transition: .5s ease;
    margin: 30px;
  }

  #face {
    transition: .5s ease;
    opacity: 1;
  }

  .view {
    transition: .5s ease;
    opacity: 0;
    align-items: center;
    position: absolute;
    font-size: 150%; 
    top: 80%;
    left: 50%;
    transform: translate(-50%, -50%);
    -ms-transform: translate(-50%,-50%);
    text-align: center;
  }

  .title, .owner, .view {
    text-shadow: 2px 2px #000;
    color: white;
  }

   #face:hover {
    opacity: 0.5;
  }

   #face:hover + .view{
    opacity: 1;
  }

  #face {
    height: 350px;
    width: 275px;
  }

</style>