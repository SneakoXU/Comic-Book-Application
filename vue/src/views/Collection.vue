<template>
    <div class="comic-display">
        <h2>List of Collections</h2>
       
        <add-collection ></add-collection>       
    </div>
 
</template>

<script>
import collectionService from '@/services/CollectionService.js';
import collectionCard from '@/components/CollectionCard.vue';
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

</style>