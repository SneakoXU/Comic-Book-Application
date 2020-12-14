<template>
  <div class="main">
      <div id="backgd"></div>
    <div class="user-info">
        <h1>{{this.username}}</h1>
    </div>
    <div class="friends">

    </div>
    <h2>Collections</h2>
    <div class="collections">
        <div class="result-container" v-for="result in results.data.results" v-bind:key="'collection:' + result.id">
            <router-link class="result-container" v-bind:to="{ name: 'comics', params: {id: result.id}}">
                <img class="result-image" v-if="isLoading" src="../../assets/Images/loading.gif"/>
                <div class="inception"  v-if ="!isLoading">
                    <p id="title">{{result.name}}</p>
                    <!-- NEED TO FIX LOADING GIF -->              
                </div>
                <!-- <router-link v-bind:to="{name: 'comic'}" v-show="onClick() === true"> -->
                <img :src="getCollectionImage(result)" alt="Collection Thumbnail" :title="result.title" class="result-image">
            </router-link>
            
        </div>
    </div>
  </div>
  
</template>

<script>
import CollectionService from '../services/CollectionService';
export default {
    components: {
        
    },
    data()
    {
        return{
            username: '',
            results: 
            {
                data: 
                {
                    results: []
                }
            }
        }
    },
    created()
    {
        this.username = this.$route.params.username
        CollectionService.getCollectionsByOwner(this.username)
        .then(response => 
        {
            this.results = response.data;
            this.showNextButtons = true;
            this.isLoading = false;
            this.$forceUpdate();
        })

    },
    watch:
    {
        $route()
        {
            this.username = this.$route.params.username
            CollectionService.getCollectionsByOwner(this.username).then(response => 
                {
                    this.results = response.data;
                    this.showNextButtons = true;
                    this.isLoading = false;
                    this.$forceUpdate();
                })
            this.$nextTick(this.$forceUpdate)
        }
    },
    updated()
    {
        
    },
    name: 'user',
    methods:{
        getCollectionImage(result)
        {
            let comic = result.comicBookIDs;
            return comic[0] == undefined? "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available/portrait_xlarge.jpg":comic[0].thumbnail.path.substring(0, comic[0].thumbnail.path.length -4) + "/portrait_xlarge.jpg";
        }
    }

}
</script>

<style scoped>

h1
{
    margin-top:10%;
}
.user-info
{
    display: flex;
    justify-content: center;
}
.collections
{
    display: flex;
    width: 100vw;
}
</style>