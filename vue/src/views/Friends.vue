<template>
  <div class="friends"> 
    <div id="backgd"></div>
    <h1 class="no-results" v-if="results.data.length==0">You have no friends</h1>
    <div class="result-container"  v-for="result in results.data" v-bind:key="'result:' + result.id">
      <router-link class="result-container" v-bind:to="{ name: 'user', params: {username: result.username}}">
          <div class="inception">
              <p id="title">{{result.username}}</p>
              <!-- NEED TO FIX LOADING GIF -->              
          </div>
      
      <!-- <router-link v-bind:to="{name: 'comic'}" v-show="onClick() === true"> -->
          <img src="http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available/portrait_xlarge.jpg" :title="result.title" class="result-image">
      </router-link>   
    </div>
  </div>
</template>

<script>
import AuthService from '../services/AuthService.js';
export default {
  name: "friends",
  components: {

  },
  data()
  {
    return {
      results: 
      {
          data: []
      }
    }
  },
  created()
  {
    AuthService.getFriends()
    .then(res =>
    {
      console.log(res)
      this.results = res;
    })
  } 
}
</script>

<style>


.friends
{
  margin-top:10vh;
  display: flex;
  flex-wrap: wrap;
}
 
</style>