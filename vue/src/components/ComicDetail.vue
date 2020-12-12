<template>
<div class="comic-info">
    <h2 class="title">{{comic.title}}</h2>
    <h3 class="author">{{comic.author}}</h3>
    <h4 class="description">{{comic.description}}</h4>
    <div class="container">
        <img src={{comic.image}}>
  
</template>


<script>
import comicService from '../../services/ComicService.js';
export default {
    components: {
        comicService
    },
    name: 'comic-detail',
    data() {
        return {
            comic:{
                
                title: '',
                author: '',
                description: '',
                image: '' 

            }
        }
    },

    methods: {
        created() {
            comicService.getThumbnail(id).then(response => {
                this.comic.image=response.data;
            });
        },

    }
}
</script>

<style>

</style>