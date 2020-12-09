<template>
    <div class="comic-display">
        <h2>List of Comics in {{collection.name}}</h2>
        
    </div>
</template>

<script>
import collectionService from '@/services/CollectionService.js';

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
    }

}
</script>

<style>

</style>