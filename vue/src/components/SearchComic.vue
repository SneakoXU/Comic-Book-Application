<template>
  <div class="main">
      <div id="backgd"></div>
    <div class="form-container">
        <h2 class="header">Search for a Comic Book</h2>
        <form v-on:submit.prevent="searchByName">
            <div class="search-form">
            <div class="form-input">
            <!-- <label for="search-title">Search By Title: </label> -->
            <input type="text" id="search-title" class="search-input" v-model="searchTerm">
            </div>
            <div class="actions">
            <button class="form-search" type='submit'>Search</button>
            <button class="form-cancel">Cancel</button>
            </div> 
          </div>
              
        </form>
    </div>
 
  <div class="container">
    <div class="result-container" v-for="result in results.data.results" v-bind:key="result.id">
        <img class="result-image" v-if="isLoading" src="../../assets/Images/loading.gif"/>
        <div class="inception"  v-if ="!isLoading">
            <p id="title">{{result.title}}</p>
            <!-- NEED TO FIX LOADING GIF -->              
        </div>
        <img :src="result.thumbnail.path + '/portrait_xlarge.jpg'" alt="Comic Book Image Result" :title="result.title" class="result-image">
        
    </div>
  </div> 
    <div class="next-page" v-if="showNextButtons === true">
        <button class="form-previous" v-on:click="previousPage">Previous</button>
        
        <p class="page-number">Displaying Page {{page+1}} of {{Math.ceil(this.results.data.total/30)}}</p>
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
           page: 0,
           results: {
               data: {
                   results: []
               }

           },
         

        }
    },

    beforeCreate(){
        
    },
    created(){

    },

    methods:{
        searchByName(){
            this.page = 0;
            ComicService.nextComicsSearch(this.searchTerm, 0).then(response => {
                this.results = response.data;
                this.showNextButtons = true;
                this.isLoading = false;
            })
        },

        nextPage(){
            this.increment();
            ComicService.nextComicsSearch(this.searchTerm, this.page).then(response => {
                this.results = response.data;                
            })
        },
        increment(){
            this.page++;
        },

        
        //NEED TO FIX - doesn't work properly (page decrements but returns new results)
        previousPage(){
            this.decrement();
             ComicService.nextComicsSearch(this.searchTerm, this.page).then(response => {
                this.results = response.data;                
            })
        },

        decrement(){
            if(this.page > 0)
            {
                this.page--;
            }
            
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

#backgd
{
    position: fixed;
    top:0;
    height:100vh;
    width:100vw;
    background: #EEE;
    /* background-color: #114b5f; */
    
    z-index: -100;
}

  .result-image{
    height: 275px;
    width: 250px;
    display: flex;
    box-shadow: 7px 7px 5px rgba(0,0,0,.5);
    border-color: #000;
    border-width: 2px;
    border-style: solid;
    border-radius: 3px;
  }


.form-container
{
    position: fixed;
    top:0;
    max-width: 15vw;
    padding:1%;
    height:100%;
    z-index: -1;
}

  .result-image:hover{
    cursor: pointer;
  }

    .inception
    {
        width: 100%;
    }

  .result-container{
      width:200px;
      display: flex;
      flex-wrap: wrap;
      max-height: 40vh;
      margin: 20px 30px 20px 30px;
      justify-content: center;
      

  } 

  .container{
      margin-top:5%;
      max-width: 85vw;
      float:right;
      display: flex;
      flex-wrap: wrap;
      justify-content: space-evenly;
  } 

  #title{
    font-family: "Runners-bold";
    white-space: nowrap;
    text-align: center;
    overflow: hidden;
    text-overflow: ellipsis;
    color: black;
    text-shadow: 1px 1px gray;
    text-align: center;
    display: block;
    font-size: 120%;
      
  }

.header{
    color:black;
    text-shadow: 2px 2px gray;
    padding-top: 50%;
}

.header, form{
    
    width: 100%;
    display: flex;
    justify-content: center;
}

.next-page {
    
    background-color: #ED1D24;
    width: 100vw;
    display: flex;
    justify-content: center;
    position: fixed;
    bottom:0;
}

.form-previous, .form-next, .page-number
{
    font-family: "Runners-bold";
}


</style>