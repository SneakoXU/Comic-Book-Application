<template>
    <div class="comic-display">

        <div id="backgd"></div>
        <div class="form-container">
            
            <add-collection ></add-collection>
            
        </div>
        <h2 class="header">My Collections</h2>
        <div class="collections">
            
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
    created() {
        CollectionService.getCollections().then((response) =>{
            this.collections = response.data;
        });
        this.username = this.$store.state.user.username
        CollectionService.getCollectionsByOwner(this.username).then(response => 
                {
                    this.results = response.data;
                    this.showNextButtons = true;
                    this.isLoading = false;
                });
    },
    methods: {
        getCollectionImage(result)
        {
            let comic = result.comicBookIDs;
            return comic[0] == undefined? "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available/portrait_xlarge.jpg":comic[0].thumbnail.path.substring(0, comic[0].thumbnail.path.length -4) + "/portrait_xlarge.jpg";
        }
    }

}
</script>

<style>
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
    margin-top:5%;

    top:0;
    max-width: 15vw;
    padding:1%;
    height:100%;
    margin-left:1%;
}

.comic-display{
    margin-top:5%;
    justify-content: space-between;
    display: flex;
    max-width: 100vw;
}
.collections
{
    justify-self: right;
    display: flex;
    width: 80vw;
    flex-wrap: wrap;
}

.header
{
    position: fixed;
    left:50vw;
}
</style>