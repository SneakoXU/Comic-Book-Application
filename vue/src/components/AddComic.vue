<template>
  <div class="container">
       <a
            id="show-form-button"
            href="#"
            v-on:click.prevent="showForm = true"
            v-if="showForm === false"
            >Add Comic Book to A Collection</a>
        <div class="form-container" v-if="showForm === true">
            <h2 class="header">Add a Comic Book</h2>
            <form v-on:submit.prevent="addComic">
                <div class="form-input">
                    <label for="comic-title">Title: </label>
                    <input type="text" id="comic-title" class="comic-input" v-model="newComic.title">
                </div>
                 <div class="actions">
                    <button class="form-submit" type='submit'>Create</button>
                    <button class="form-cancel" v-on:click="showForm = false">Cancel</button>
                </div>    
            </form>
  </div>
</template>

<script>
import CollectionService from '../services/ComicService.js'
export default {
    name: 'add-comic',
    data(){
        return{
          newComic:{
            collection_id: '',
            comic_id: '', 
            title: '', 
            description: '', 
            thumbnail_url: ''
          },
          showForm: false  
          
        }
    },

    methods:{
        addComic(){
            const newComic = {
                collection_id: this.newComic.collection_id,
                comic_id: this.newComic.comic_id,
                title: this.newComic.title,
                description: this.newComic.description,
                thumbnail_url: this.newComic.thumbnail_url

            };

            CollectionService.addComicToCollection(this.$route.params.collectionId, this.$route.params.comicId)
                    .then(response => {
                        if(response.status ===201){
                            
                }
            })
        }
    }
}
</script>

<style>

</style>