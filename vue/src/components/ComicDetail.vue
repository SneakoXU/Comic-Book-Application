<template>
<div class="comic-info">
    <h2 class="title">{{comic.title}}</h2>
    <h3 class="author">{{comic.author}}</h3>
    <h4 class="description" v-html="comic.description"></h4>
    <div class="container">
        <!-- <img :src="comic.image"> -->
    </div>
</div>
</template>


<script>

export default {
    components: {

    },
    name: 'comic-detail',
    props: [
        "result",
    ],
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

        created() {
           
            this.comic.title=this.result.title,
            // this.author=result.author, 
            this.comic.description=this.result.description
            // this.comic.image=this.result.thumbnail.path+this.result.thumbnail.extension
            },
    methods: {

        }

    }

</script>

<style>

</style>