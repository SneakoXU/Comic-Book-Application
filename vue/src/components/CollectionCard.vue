<template>
<div>
<div class="card">
  <h2 class="title">{{collection.name}}</h2>
  <h3 class="owner">By: {{username}}</h3>
  <div class="image-container">
    <img class="face" :src="thumbnail" alt="">
    <div class="view">
      Click to view more
    </div>
  </div>
</div>
</div>
</template>

<script>

import AuthService from '../services/AuthService.js';


export default {  
  props:['collection'],
  name: 'collection-card',  
  data() {
    return {
      username:''
    }
  },
  methods: {

  },
  created()
  {
    AuthService.getUserById(this.collection.userID).then(response => 
      {
          this.username = response.data.username;      
      })
  },
  computed:
  {
    thumbnail()
    {
      if(this.collection.comicBookIDs[0] != undefined)
        return this.collection.comicBookIDs[0].thumbnail.path;
      else
        return "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available.jpg";
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
    width: 20vw;
    height: 30vh;
  }

  .face {
    transition: .5s ease;
    opacity: 1;
    box-shadow: 7px 7px 5px rgba(0,0,0,.5);
    border-color: #000;
    border-width: 2px;
    border-style: solid;
    border-radius: 3px;
    height: 350px;
    width: 275px;
    
  }

  .view {
    width: 50%;
    transition: .5s ease;
    opacity: 0;
    align-items: center;
    font-size: 150%; 
    margin-top:-30%;
    text-align: center;
    
  }

  .title, .owner, .view {
    text-shadow: 2px 2px #000;
    color: white;
  }

   .face:hover {
    opacity: 0.5;
  }

   .face:hover + .view{
    opacity: 1;
  }
  .view:hover {
    opacity: 1;
  }

  .image-container
  {
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
    width:100%;
  }


</style>