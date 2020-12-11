<template>
  <div>
    <div class="form-container">
        <h2 class="header">Search for a Comic Book</h2>
        <form v-on:submit.prevent="searchByName">
            <div class="form-input">
            <!-- <label for="search-title">Search By Title: </label> -->
            <input type="text" id="search-title" class="search-input" v-model="searchTerm">
            </div>
            <div class="actions">
            <button class="form-search" type='submit'>Search</button>
            <button class="form-cancel">Cancel</button>
            </div> 
          
              
        </form>
    </div>
 
  <div class="container">
    <div>
    </div> 
    <div class="result-container" v-for="result in results.data.results" v-bind:key="result.id">
        <div class="inception">
            <p id="title">{{result.title}}</p>
            <!-- NEED TO FIX LOADING GIF -->
              <img class="result-image" v-if="isLoading" src="../../assets/Images/loading.gif"/>
        <img :src="result.thumbnail.path + '.jpg'" alt="Comic Book Image Result" v-if ="!isLoading" class="result-image">
        </div>
        
    </div>
  </div> 
    <div class="next-page" v-if="showNextButtons === true">
        <button class="form-previous" v-on:click="previousPage">Previous</button>
        <button class="form-next" type='submit' v-on:click="nextPage">Next</button>
    </div> 

  </div> 
</template>

<script>
import ComicService from '../services/ComicService.js';
export default {
    name: 'search-comic',
    data(){
        return{
        isLoading: true,
           searchTerm: '',
           showNextButtons: false,
           page: 1,
           results: {
               data: {
                   results: []
               }

           },
         

        }
    },

    beforeCreate(){
        
    },
    methods:{
        searchByName(){
            ComicService.searchComicsByName(this.searchTerm).then(response => {
                this.results = response.data;
                this.showNextButtons = true;
                this.isLoading = false;
            })
        },

        nextPage(){
            ComicService.nextComicsSearch(this.searchTerm, this.page).then(response => {
                this.results = response.data;
                this.increment();
            })
        },
        increment(){
            this.page++;
        },

        
        //NEED TO FIX - doesn't work properly (page decrements but returns new results)
        previousPage(){
             ComicService.nextComicsSearch(this.searchTerm, this.page).then(response => {
                this.results = response.data;
                this.decrement();
            })
        },

        decrement(){
            this.page--;
        }
    }

}
</script>
  
<style scoped>

h2{
    font-size: 36px;
    color: white;
    text-shadow: 2px 2px black;
   text-align: center;
}
  .result-image{
    height: 275px;
    width: 250px;
    display: flex;
  }

  .result-image:hover{
    cursor: pointer;
  }



  .result-container{
  
      display: flex;
      flex-wrap: wrap;
      max-height: 40vh;
      margin: 20px 30px 20px 30px;
      

  } 

  .container{
      display: flex;
      flex-wrap: wrap;
      justify-content: space-evenly;
  } 

  #title{
      max-width: 250px;
      white-space: nowrap;
      text-align: center;
      overflow: hidden;
      text-overflow: ellipsis;
      
  }

  #title::after {
  content: "...";
}

.header, form{
    width: 100%;
    display: flex;
    justify-content: center;
}

</style>