<template>
    <div class="comic-display">

        <div id="backgd"></div>
        <div class="form-container">
            <h2 class="header">My Collections</h2>
            <add-collection ></add-collection>
        </div>
        
    </div>
 
</template>

<script>
import collectionService from '@/services/CollectionService.js';
import addCollection from '../components/AddCollection.vue';
export default {   
    
    components:{
        addCollection
    },    
    name: 'collection-display',
    data() {
        return {
            showForm: false,
            collections: []
            
        }
    },
    created() {
        collectionService.getCollections().then((response) =>{
            this.collections = response.data;
        })
    },
    methods: {
        },

    computed: {
        /* displayCollections() {
            this.collections.forEach(collection => {
                return this.collection.title;             
            });
        } */
        
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
    position: fixed;
    top:0;
    max-width: 15vw;
    padding:1%;
    height:100%;
    margin-left:1%;
    z-index: -1;
}
</style>