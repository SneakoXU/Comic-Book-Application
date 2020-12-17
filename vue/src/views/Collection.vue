<template>
    <div class="comic-display">

        <div id="backgd"></div>
        <div class="form-container">
            
            <add-collection ></add-collection>
            
        </div>
        <h2 class="header">My Collections</h2>
        <div class='collections-backgd'></div>
        <div class="collections">            
                <div class="collection-containerl" v-for="result in results.data.results" v-bind:key="'collection:' + result.id">
                    <router-link class="collection-containerl" v-bind:to="{ name: 'comics', params: {id: result.id}}">

                        <p id="title">{{result.name}}</p>
                        <!-- NEED TO FIX LOADING GIF -->              
                   
                    <!-- <router-link v-bind:to="{name: 'comic'}" v-show="onClick() === true"> -->
                    <img :src="getCollectionImage(result)" alt="Comic Book Image Result" :title="result.title" class="result-image">
                    </router-link>
                    
                </div>
            </div>
        
    </div>
 
</template>

<script>
import CollectionService from '@/services/CollectionService.js';
import addCollection from '../components/AddCollection.vue';
export default {   
    
    components:{
        addCollection
    },    
    name: 'collection',
    data() {
        return {
            username: '',
            results: 
            {
                data: 
                {
                    results: []
                }
            },
            showForm: false,
            collections: []
            
        }
    },
    created() 
    {
        CollectionService.getCollections().then((response) =>
        {
            this.collections = response.data;
        });
        this.username = this.$store.state.user.username
        CollectionService.getCollectionsByOwnerSubscription(this.username).then(response => 
                {
                    this.results = response.data;
                    this.showNextButtons = true;
                    this.isLoading = false;
                });
    },
    methods: 
    {
        getCollectionImage(result)
        {
            let comic = result.comicBookIDs;
            return comic[0] == undefined? "https://cdn.discordapp.com/attachments/784490257771921420/789145235296485416/nocomics-collection.png":comic[0].thumbnail.path.substring(0, comic[0].thumbnail.path.length -4) + "/portrait_xlarge.jpg";
        }
    }
}
</script>

<style>
#title
{
    text-align: center;
    font-family: 'Runners-bold';
    height:unset;
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
.form-container
{
    margin-top:5vh;
    top:0;
    width: 20vw;
    padding:1%;
    height:100%;
    margin-left:1%;
}

.collection-containerl
{
    width:200px;
    margin: 0 2vw;
    display: flex;
    flex-wrap: wrap;
    max-height: 40vh;
    justify-self: flex-start;
}

.collection-containerl:visited
{
    color:black;
  text-decoration: none;
}

.comic-display{
    margin-top:5vh;
    justify-content: space-between;
    display: flex;
    max-width: 100vw;
}
.collections
{
    text-decoration: underline;
    justify-self: right;
    display: flex;
    width: 80vw;
    flex-wrap: wrap;
    margin-top:15vh;

}
.collections-backgd
{
    position: fixed;
    right:0;
    width: 77vw;
    background-color: #DDD;
    height: 100%;
    z-index: -1;
}

.header
{
    font-size: 200%;
    position: fixed;
    margin-left: 50vw;
    top:10%;
}

.popup-header
{

    align-self: center;
}

.result-image
{
    height: 244px;
    width: 200px;
}

</style>