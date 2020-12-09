<template>
    <div class="comic-display">
        <h2>List of Collections</h2>        
    </div>
</template>

<script>
import collectionService from '@/services/CollectionService.js';
import collectionCard from '@/components/CollectionCard.vue';
export default {   

    name: 'collection-display',
    data() {
        return {
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