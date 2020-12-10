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
                <select name='public-status' id='public-status' class="select" v-model="collection.publicStatus" >                    
                    <option value="true" selected>Public</option>
                    <option value="false">Private</option>
                </select>
                <div class="actions">
                    <button class="form-submit" type='submit'>Create</button>
                    <button class="form-cancel" v-on:click="showForm = false">Cancel</button>
                </div> 
            <div class="status-message success" v-show="formAddedSuccess">Collection Created</div>
            <div class="status-message error" v-show="formAddedFailure">{{errorMsg}}</div>
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
                 publicStatus: '',
                 datecreated: ''
            },
            showForm: false,
            formAddedSuccess: false,
            forAddedFailure: false,
            errorMsg: '',
           
        }
    },
    methods:{
        addNewCollection() {
            collection = {
                collectionname: this.collection.collectionname, 
                creator_id: this.$store.state.user.id,
                publicstatus: this.select.value,
                datecreated: new Date()
            };
            
            CollectionService.addCollection(collection).then(response => {
                if(response.status === 201){
                    this.$router.push({
                        path:'/collections'                        
                    });
                    this.formAddedSuccess = true
                }
            })
            .catch((error) =>{
                const response =  error.response;
                if(response.status === 500) {
                    this.errorMsg = "Error, URL not found"
                }
                if(response.status === 404) {
                    this.errorMsg = "Error, URL not found"
                }
                this.formAddedFailure = true;
            })
            
        },
   
    }

}
</script>

<style>

</style>