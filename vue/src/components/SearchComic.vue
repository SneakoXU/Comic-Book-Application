<template>
  <div class="main">
      <div id="backgd"></div>
    <div class="form-container">
        <h2 class="header">Search for a {{this.searchType}}</h2>
        <form v-on:submit.prevent="searchByName">
            <div class="search-form">
            <div class="form-input">
            <!-- <label for="search-title">Search By Title: </label> -->
            <select name='public-status' id='public-status' class="select" v-model="searchType">      
                        <option value="Comic Book" selected>Comics</option>
                        <option value="Collection">Collections</option>
                        <option value="User">Users</option>
            </select>
            <input type="text" id="search-title" class="search-input" v-model="searchTerm">
            </div>
            <div class="actions">
            <button class="form-search" type='submit'>Search</button>
            <button class="form-add">Add</button>
            </div> 
          </div>
              
        </form>
    </div>
  <div class="search-container">

    <div v-if="searchType == 'Comic Book'">
        <div class="result-container"  v-for="result in results.data.results" v-bind:key="result.id">
            <img class="result-image" v-if="isLoading" src="../../assets/Images/loading.gif"/>
            <div class="inception"  v-if ="!isLoading">
                <p id="title">{{result.title}}</p>
                <!-- NEED TO FIX LOADING GIF -->              
            </div>
            <!-- <router-link v-bind:to="{name: 'comic'}" v-show="onClick() === true"> -->
            <img :src="result.thumbnail.path + '/portrait_xlarge.jpg'" alt="Comic Book Image Result" :title="result.title" class="result-image">
            
        </div>
    </div>
    <div class="result-container" v-show="searchType == 'Collection'" v-for="result in results.data.results" v-bind:key="result.id">
        <img class="result-image" v-if="isLoading" src="../../assets/Images/loading.gif"/>
        <div class="inception"  v-if ="!isLoading">
            <p id="title">{{result.name}}</p>
            <!-- NEED TO FIX LOADING GIF -->              
        </div>
        <!-- <router-link v-bind:to="{name: 'comic'}" v-show="onClick() === true"> -->
        <!-- <img :src="result.thumbnail.path + '/portrait_xlarge.jpg'" alt="Comic Book Image Result" :title="result.title" class="result-image"> -->
        
    </div>
    <div class="result-container" v-show="searchType == 'User'" v-for="result in results.data.results" v-bind:key="result.id">
        <img class="result-image" v-if="isLoading" src="../../assets/Images/loading.gif"/>
        <div class="inception"  v-if ="!isLoading">
            <p id="title">{{result.username}}</p>
            <!-- NEED TO FIX LOADING GIF -->              
        </div>
        <!-- <router-link v-bind:to="{name: 'comic'}" v-show="onClick() === true"> -->
        <!-- <img :src="result.thumbnail.path + '/portrait_xlarge.jpg'" alt="Comic Book Image Result" :title="result.title" class="result-image"> -->
        
    </div>
  </div> 
    <div class="next-page" v-if="showNextButtons === true">
        <div>
        <button class="form-previous" v-on:click="previousPage">Previous</button>
        
        <p class="page-number">Displaying Page {{page+1}} of {{Math.ceil(this.results.data.total/30)}}</p>
        <button class="form-next" type='submit' v-on:click="nextPage">Next</button>
        </div>
    </div> 

  </div> 
</template>

<script>
import ComicService from '../services/ComicService.js';
import AuthService from '../services/AuthService.js';
import CollectionService from '../services/CollectionService.js';


export default {
    name: 'search-comic',
    data()
    {
        return{
            searchType : 'Comic Book',
            isLoading: true,
            searchTerm: '',
            showNextButtons: false,
            page: 0,
            results: 
            {
                data: 
                {
                    results: []
                }
            }
        }
    },

    beforeCreate()
    {
        
    },
    created()
    {

    },

    methods:{
        searchByName()
        {
            this.page = 0;
            if(this.searchType == "Comic Book")
            {
                ComicService.nextComicsSearch(this.searchTerm, 0).then(response => 
                {
                    console.log(response);
                    this.results = response.data;
                    this.showNextButtons = true;
                    this.isLoading = false;               
                })
            }
            else if(this.searchType == "User")
            {
                AuthService.getUsers(this.searchTerm, 0).then(response => 
                {
                    this.results = response.data;
                    this.showNextButtons = true;
                    this.isLoading = false;               
                })
            }
            else if(this.searchType == "Collection")
            {
                CollectionService.getCollectionsPages(this.searchTerm, 0).then(response => 
                {
                    
                    this.results = response.data;
                    this.showNextButtons = true;
                    this.isLoading = false;          
                })
            }

            
        },

        nextPage()
        {
            this.increment();
            if(this.searchType == "Comic Book")
            {
                ComicService.nextComicsSearch(this.searchTerm, this.page).then(response => 
                {
                    this.results = response.data;                
                })
            }
            else if(this.searchType == "User")
            {
                AuthService.getUsers(this.searchTerm, this.page).then(response => 
                {
                    this.results = response.data;                
                })
            }
            else if(this.searchType == "Collection")
            {
                CollectionService.getCollectionsPages(this.searchTerm, this.page).then(response => 
                {
                    
                    this.results = response.data;                
                })
            }
        },
        increment(){
            this.page++;
        },

        
        //NEED TO FIX - doesn't work properly (page decrements but returns new results)
        previousPage(){
            this.decrement();
             if(this.searchType == "Comic Book")
            {
                ComicService.nextComicsSearch(this.searchTerm, this.page).then(response => 
                {
                    this.results = response.data;                
                })
            }
            else if(this.searchType == "User")
            {
                AuthService(this.searchTerm, this.page).then(response => 
                {
                    this.results = response.data;                
                })
            }
            else if(this.searchType == "Collection")
            {
                AuthService(this.searchTerm, this.page).then(response => 
                {
                    this.results = response.data;                
                })
            }
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

select
{
    width: 50%;
    border-radius: 3px 0px 0px 3px;
}

input
{
    width: 100%;
    border-radius: 0px 3px 3px 0px;
}

.form-input
{
    display: flex;
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
    max-width: 20vw;
    padding:1%;
    height:100%;
    margin-left:1%;
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

.search-container{
    margin-top:5%;
    max-width: 70vw;
    float:right;
    display: flex;
    flex-wrap: wrap;

    justify-content: left;
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

.next-page div{
    width: 30%;
    display: flex;
    justify-content: space-between;
}

.form-previous, .form-next, .page-number
{
    font-family: "Runners-bold";
}


</style>