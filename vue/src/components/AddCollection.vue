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
                    <input id='name' type='text' v-model="collection.name" class="form-control"/>
                </div>
                <label class="form-input">Visibility: </label>
                <!-- <select name='public-status' id='public-status' class="select" v-model="collection.isPublic" >                    
                    <option value=true selected>Public</option>
                    <option value=false>Private</option>
                </select> -->
                <input type="checkbox" class="public-status" checked="checked" 
                v-model="collection.public" v-on:checked="collection.public = !collection.public">
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
                 name: '', 
                 userId: '',
                 public: true,
                 dateCreated: ''
            },
            showForm: false,
            formAddedSuccess: false,
            formAddedFailure: false,
            errorMsg: ''
           
        }
    },
    methods:{
        addNewCollection() {
            this.collection = {
                name: this.collection.name, 
                userId: this.$store.state.user,
                public: this.collection.public              
            };
            
            CollectionService.addCollection(this.collection).then(response => {
                if(response.status === 201){
                    this.$router.push({
                        path:'/collections'                        
                    });
                    this.formAddedSuccess = true
                    this.collection.name = "";
                }
            })
            .catch((error) =>{
                const response =  error.response;
                if(response.status === 500) {
                    this.errorMsg = "Error, Hulk smashed the server"
                }
                else if(response.status === 404) {
                    this.errorMsg = "Error, Wolverine sliced up this URL"
                }
                else{
                    this.errorMsg = "Error, X-Men not found. They're only functions."
                }
                this.formAddedFailure = true;

            })            
        },
   
    }

}
</script>

<style>
    .container{
        display: flex;
        flex-direction: column;
        flex-wrap: wrap;
        width: 70px;
    }
    #show-form-button{
        margin-top: 20px;
    }
</style>