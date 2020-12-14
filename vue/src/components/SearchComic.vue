<template>
  <div class="main">
      <div id="backgd"></div>
    <div class="form-container">
        <h2 class="search-text">Search for a {{this.searchType}}</h2>
        <form v-on:submit.prevent="searchByName">
            <div class="search-form">
            <div class="form-input">
            <!-- <label for="search-title">Search By Title: </label> -->
            <select name='public-status' id='public-status' class="select" v-model="searchType" @change="resetResults()">      
                        <option value="Comic Book" selected>Comics</option>
                        <option value="Collection">Collections</option>
                        <option value="User">Users</option>
            </select>
            <input type="text" id="search-title" class="search-input" @change="fixString()" v-model="inputTerm">
            </div>
            <div class="actions">
            <button class="form-search" type='submit'>Search</button>
            
            </div> 
          </div>
              
        </form>
    </div>
  <div class="search-container">
            <h1 v-if="noResults" class="no-results">No results</h1>
            <div class="popup" v-if="detailShowing">
                <div class="clickable-overlay" v-on:click="detailShowing=false"></div>
                <comic-detail
                :result="this.result" 
                />
                <button class="form-add" v-on:click="addComic(result.id)">{{this.add()}}</button>
                <button class="form-cancel" v-on:click="detailShowing=false">Close</button>
            <!-- <router-link v-bind:to="{name: 'comic'}" v-show="onClick() === true"> -->
            </div>
            <div class="popup" v-if="userCollectionsShowing">
                <div class="clickable-overlay" v-on:click="userCollectionsShowing=false, comicAdded = false"></div>
                <div v-show="!comicAdded">
                    <h2>Select collection to add to:</h2>
                
                    <div class="collection-list" v-for="collection in userCollections.data.data.results" v-bind:key="collection.id" v-on:click="addComicToCollection(collection.id)">{{collection.name}}</div>
                    
                </div>
                <div v-show="comicAdded">
                    <h2>Comic added!</h2>
                </div>
                <button class="form-cancel" v-on:click="userCollectionsShowing=false, comicAdded = false">Close</button>
            </div>
      
    <div class="show-div" v-if="searchType == 'Comic Book'">
        <div class="result-container"  v-for="result in results.data.results" v-on:click="setDetail(result)" v-bind:key="'comic:' +result.id">
            <img class="result-image" v-if="isLoading" src="../../assets/Images/loading.gif"/>
            <div class="inception"  v-if ="!isLoading">
                <p id="title">{{result.title}}</p>
                <!-- NEED TO FIX LOADING GIF -->              
            </div>

            <img :src="result.thumbnail.path + '/portrait_xlarge.jpg'" alt="Comic Book Image Result" :title="result.title" class="result-image">
            
        </div>
    </div>
    <div class="show-div" v-if="searchType == 'Collection'">
        <div class="result-container" v-for="result in results.data.results" v-bind:key="'collection:' + result.id">
            <router-link class="result-container" v-bind:to="{ name: 'comics', params: {id: result.id}}">
            <img class="result-image" v-if="isLoading" src="../../assets/Images/loading.gif"/>
            <div class="inception"  v-if ="!isLoading">
                <p id="title">{{result.name}}</p>
                <!-- NEED TO FIX LOADING GIF -->
            </div>
            <!-- <router-link v-bind:to="{name: 'comic'}" v-show="onClick() === true"> -->
            <img :src="getCollectionImage(result)" alt="Comic Book Image Result" :title="result.title" class="result-image">
            </router-link>
            
        </div>
        </div>
        <div class="show-div" v-if="searchType == 'User'">
            <div  class="result-container"  v-for="result in results.data.results" v-bind:key="'user:' +result.id">
                <router-link class="result-container" v-bind:to="{ name: 'user', params: {username: result.username}}">
                    <img class="result-image" v-if="isLoading" src="../../assets/Images/loading.gif"/>
                    <div class="inception"  v-if ="!isLoading">
                        <p id="title">{{result.username}}</p>
                        <!-- NEED TO FIX LOADING GIF -->              
                    </div>
                
                <!-- <router-link v-bind:to="{name: 'comic'}" v-show="onClick() === true"> -->
                    <img src="http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available/portrait_xlarge.jpg" :title="result.title" class="result-image">
                </router-link>
                
            </div>
        </div>
  </div> 
    <div class="next-page" v-if="showNextButtons === true">
        <div>
        <button class="form-previous" v-show="page > 0" v-on:click="previousPage">Previous</button>
        
        <p class="page-number">Displaying Page {{page+1}} of {{totalPages}}</p>
        <button class="form-next" type='submit' v-show="page+1 < totalPages" v-on:click="nextPage">Next</button>
        </div>
    </div> 

  </div> 
</template>

<script>
import ComicService from '../services/ComicService.js';
import AuthService from '../services/AuthService.js';
import CollectionService from '../services/CollectionService.js';
import ComicDetail from "../components/ComicDetail.vue";


export default {
    name: 'search-comic',
    components: {
    ComicDetail
  },
    data()
    {
        return{
            searchType : 'Comic Book',
            noResults : false,
            isLoading: true,
            inputTerm : '',
            searchTerm: '',
            showNextButtons: false,
            page: 0,
            totalPages : 0,
            detailShowing: false,
            userCollectionsShowing: false,
            comicToAdd: 0,
            userCollections :[],
            comicAdded:false,
            results: 
            {
                data: 
                {
                    results: []
                }
            },
            result: {}
        }
    },

    beforeCreate()
    {
        
    },
    created()
    {
        if(this.$store.state.token != '')
        {
            CollectionService.getCollectionsByOwner(this.$store.state.user.username).then(response =>
            {
                this.userCollections = response;
            });
        }
    },

    methods:{
        add()
        {
            if(this.$store.state.token != '')
            {
                return "Add"
            }
            else
            {
                return "Log in to add"
            }
        },
        addComic(comicId)
        {
            if(this.$store.state.token != '')
            {
                this.comicToAdd = comicId;
                this.detailShowing = false;
                this.userCollectionsShowing = true;
            }
            else
            {
                this.detailShowing = false;
                this.$parent.$parent.showLogin = true;
            }
        },
        addComicToCollection(collectionId)
        {
            CollectionService.addComicToCollection(collectionId,this.comicToAdd);
            this.comicAdded = true;
        },
        getCollectionImage(result)
        {
            let comic = result.comicBookIDs;
            return comic[0] == undefined? "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available/portrait_xlarge.jpg":comic[0].thumbnail.path.substring(0, comic[0].thumbnail.path.length -4) + "/portrait_xlarge.jpg";
        },
        fixString()
        {
            let tempString = this.inputTerm;
            let illegalCharacters= 
            {
                '%' : '%25',
                ' ' : '+',
                '!' : '%21',
                '@' : '%40',
                '#' : '%23',
                '$' : '%24',
                '^' : '%5E',
                '&' : '%26',
                '"' : '%27',
                '(' : '%28',
                ')' : '%29',
                ',' : '%2C',
                
            }
            for (let key in illegalCharacters)
            {
                tempString = tempString.replaceAll(key, illegalCharacters[key])
            }
            if(tempString.length != 0)
                tempString += '/';
            this.searchTerm = tempString;
        },
        resetResults()
        {
            this.results = {
                data: 
                {
                    results: []
                }
            };
  
            this.showNextButtons = false;

        },
        searchByName()
        {
            this.noResults = false;
            this.page = 0;
            if(this.searchType == "Comic Book")
            {
                ComicService.nextComicsSearch(this.searchTerm, 0).then(response => 
                {
                    this.updateSearch(response); 
                    if(this.results.data.results.length == 0)
                        this.noResults = true;              
                })
            }
            else if(this.searchType == "User")
            {
                AuthService.getUsers(this.searchTerm, 0).then(response => 
                {
                    this.updateSearch(response);     
                    if(this.results.data.results.length == 0)
                        this.noResults = true;      
                })
            }
            else if(this.searchType == "Collection")
            {
                CollectionService.getCollectionsPages(this.searchTerm, 0).then(response => 
                {
                    this.updateSearch(response);       
                    if(this.results.data.results.length == 0)
                        this.noResults = true;
                })
            }
            
        },
        updateSearch(response)
        {
            this.totalPages = Math.ceil(response.data.data.total/30);
            this.results = response.data;
            this.showNextButtons = this.totalPages > 1;
            this.isLoading = false;
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
        }, 

        setDetail(properties){
            this.result=properties;
            this.detailShowing=true;
            
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

router-link
{
    max-width:200px;
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

.show-div
{
    width: 70vw;
    max-width: 70vw;
    display: flex;
    flex-wrap: wrap;
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




.form-container
{
    position: fixed;
    top:0;
    max-width: 25vw;
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



.search-container{
    margin-top:5vh;
    height: 90vh;
    max-width: 70vw;
    width:70vw;
    float:right;
    display: flex;
    flex-wrap: wrap;
    padding-bottom: 5vh;
    justify-content: left;
    background-color: #DDD;
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

.search-text{
    color:black;
    text-shadow: 2px 2px gray;
    padding-top: 20%;
}

.search-text, form{
    
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

.collection-list
{
    padding:5px;
    background-color: #EEE;
    font-family: "Runners-bold";
}

.collection-list:hover
{
    cursor: pointer;
    background-color: #BBB;
}
.popup h2
{
    color:black;
    text-shadow: 2px 2px rgba(0,0,0,0.5);
}

.no-results
{
    align-self: center;
    margin-left: 35%;
    color:#999;
}


</style>