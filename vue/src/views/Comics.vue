<template>
    <div class="main">
        <div id="backgd"></div>
        <h1 v-if="notFound" class="no-results">Collection not found</h1>
        <div class="container">
            
            <div class="result-container"  v-for="result in results.data.comicBookIDs" v-on:click="setDetail(result)" v-bind:key="'comic:' +result.id">
                <div class="inception" >
                    <p id="title">{{result.title}}</p>
                    <!-- NEED TO FIX LOADING GIF -->              
                </div>

                <img :src="result.thumbnail.path.substring(0, result.thumbnail.path.length - 4) + '/portrait_xlarge.jpg'" alt="Comic Book Image Result" :title="result.title" class="result-image">
            </div>
        </div>

        <div class="search-container">
            <div class="popup" v-if="detailShowing">
                <div class="clickable-overlay" v-on:click="detailShowing=false"></div>
                <comic-detail
                :result="this.comic" 
                />
                <button class="form-search" v-on:click="detailShowing=false">Close</button>
                <button class="form-cancel" v-if="this.results.data.userID == this.userId" v-on:click="removeComic(comic.id)">Delete</button>
                
            <!-- <router-link v-bind:to="{name: 'comic'}" v-show="onClick() === true"> -->
            </div>
        </div>

    

  </div> 
</template>

<script>
import CollectionService from '../services/CollectionService.js';
import ComicDetail from "../components/ComicDetail.vue";
import AuthService from '../services/AuthService.js';


export default {
    name: 'comics',
    collectionId : 0,
    components: 
    {
        ComicDetail
    },
    data()
    {
        return{
            notFound :false,
            detailShowing: false,
            userId:0,
            results: 
            {
                data: 
                {
                    results: []
                }
            },
            comic : 
            {
                id:0
            }
        }
    },

    beforeCreate()
    {
        
    },
    created()
    {
        this.collectionId = this.$route.params.id
        CollectionService.getCollectionsById(this.collectionId)
        .then(response =>
        {
            this.results = response;
        })
        .catch(error =>
        {
            if (error.response.status === 404) 
            {
                this.notFound = true;
            }
        })
        
        AuthService.getIdByUsername()
        .then(response=>
        {
            this.userId = response.data;
        })
        .catch(response =>
        {
        });
        
    },

    methods:{
        removeComic(comicId)
        {
            CollectionService.removeComicFromCollection(this.collectionId, comicId).then(() =>
            {
            CollectionService.getCollectionsById(this.collectionId).then(response =>
            {
                this.results = response;
            })}
            );
            this.detailShowing=false;
            this.$forceUpdate;
        },
        setDetail(comic){
            this.comic=comic;
            this.detailShowing=true;
            
        }
    }
}
</script>
  
<style scoped>

.container
{
    width: 100vw;
    margin-top:5%;
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
}

.result-container
{
    cursor: pointer;
}

#title
{
    max-width:200px;
    white-space: nowrap;

    overflow: hidden;
}

</style>