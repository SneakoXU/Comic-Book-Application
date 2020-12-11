<template>
  <div class="search-container">
      <search-comic></search-comic>
  </div>
</template>

<script>
import SearchComic from '../components/SearchComic.vue';
export default {
    components: {
        SearchComic,
    },
    name: 'search'

}
</script>

<style scoped>
/*     .search-container{
       background-color: #2A75B3;
       background-color: #0476F2;
    } */
</style>