<template>
  <div class="container">
    <div class="form-container">
        <h2 class="header">Search for a Comic Book</h2>
        <form v-on:submit.prevent="searchByName">
            <div class="form-input">
            <label for="search-title">Search By Title: </label>
            <input type="text" id="search-title" class="search-input" v-model="searchTerm">
            </div>
            <div class="actions">
            <button class="form-search" type='submit'>Search</button>
            <button class="form-cancel">Cancel</button>
            </div>    
        </form>
    </div>
  </div>  
</template>

<script>
import ComicService from '../components/ComicService.js';
export default {
    name: 'search-comic',
    data(){
        return{
           searchTerm: '',
           results: {} 
        }
    },
    methods:{
        searchByName(){
            ComicService.searchComicsByName(this.searchTerm).then(response => {
                this.results = response.data;
            })
        }
    }

}
</script>

<style>

</style>