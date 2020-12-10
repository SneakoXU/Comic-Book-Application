<template>
  <div class="container">
       <a
            id="show-form-button"
            href="#"
            v-on:click.prevent="showForm = true"
            v-if="showForm === false"
            >Create a Collection</a>
     
      <div class="form-container" v-if="showForm === true">
           <h2 class="header">CREATE A COLLECTION</h2>
          <form v-on:submit.prevent="addNewCollection" >
               <div class="form-input">
                    <label for='name'>Collection Name: </label>
                    <input id='name' type='text' v-model="collection.collectionname" class="form-control"/>
                </div>
                <label class="form-input">Visibility: </label>
                <select name='public-status' id='public-status' class="select" v-model="selected" >
                    <option disabled value="">Please Select</option>
                    <option>Public</option>
                    <option>Private</option>
                </select>
                <div class="actions">
                    <button class="form-submit" type='submit' v-on:click="addNewCollection">Create</button>
                    <button class="form-cancel" v-on:click="showForm = false">Cancel</button>
                </div>    
          </form>
      </div>
  </div>
</template>

<script>
import CollectionService from '../services/CollectionService.js';
export default {
    name:'add-collection',
    data(){
        return{
            collection:{
                 collectionname: '', 
                 creator_id: '',
                 publicstatus: '',
                 datecreated: ''
            },
            selected:'',
            showForm: false,
           
        }
    },
    methods:{
        addNewCollection() {
            const newCollection = {
                collectionname: this.collection.collectionname, 
                creator_id: this.$store.state.user.id,
                publicstatus: this.collection.publicstatus,
                datecreated: this.collection.datecreated
            };
            
            CollectionService.addCollection(newCollection).then(response => {
                if(response.status === 201){
                    
                }
            })
            
        },
   
    }

}
</script>

<style>

</style>