<template>
<div class="comic-info">
    <h2 class="title">{{comic.title}}</h2>
    <h3 class="author">{{comic.author}}</h3>
    <h4 class="description">{{comic.description}}</h4>
    <div class="container">
        <img id="comic-page" src="../../assets/Images/Captain_America_Comics_Vol_1_2.jpg" alt="">
  
</template>

<script>
export default {
    name: 'comic-detail',
    data() {
        return {
            comic:{
                title: '',
                author: '',
                description: '',

            }
        }
    },
    methods: {

    }
}
</script>

<style>

</style>