<template>
  <div class="container">
       <button
            id="show-form-button"
            class="form-create"
            href="#"
            v-on:click.prevent="showForm = true"
            >Add Collection</button>
     
      <div class="form-container" v-if="showForm === true">
           
          <form v-on:submit.prevent="addNewCollection" class="popup">
              <h2 class="popup-header">{{responseMessage}}</h2>
               <div v-show="!formAddedSuccess" class="form-input">
                    <label for='name'>Collection Name: </label><br>
                    <input id='name' type='text' v-model="collection.name" class="form-control"/>
                </div><br>
                <div v-show="!formAddedSuccess">
                    <label class="form-input collection-name-input">Collection is: </label><br>
                    <select name='public-status' id='public-status' class="select" v-model="collection.public" >      
                        <option value=true selected>Public</option>
                        <option value=false>Private</option>
                    </select>
                </div>
                <!-- <input type="checkbox" class="public-status" checked="checked" 
                v-model="collection.public" v-on:checked="collection.public = !collection.public"><br> -->
                <div class="actions">
                    <button v-show="!formAddedSuccess" class="form-submit" type='submit'>Create</button>
                    <button class="form-cancel" v-on:click=closeCreateCollection()>Close</button>
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
                 name: '', 
                 userId: '',
                 public: true,
                 dateCreated: ''
            },
            showForm: false,
            formAddedSuccess: false,
            formAddedFailure: false,
            responseMessage: ''
           
        }
    },
    created()
    {
        this.responseMessage = "Create a Collection";
    },
    methods:{
        closeCreateCollection()
        {
            this.$forceUpdate();
            this.showForm = false;
            this.formAddedSuccess = false;
            this.formAddedFailure = false;
            this.responseMessage = "Create a Collection";
            
        },
        addNewCollection() {
            this.collection = {
                name: this.collection.name, 
                userId: this.$store.state.user,
                public: this.collection.public
            };
            
            CollectionService.addCollection(this.collection).then(response => {
                if(response.status === 201){
                    this.$router.push('/user/' + this.$store.state.user.username + "/collections");
                    this.formAddedSuccess = true
                    this.collection.name = "";
                    this.responseMessage = "Collection added!";
                    location.reload();                   
                }
            })
            .catch((error) =>{
                const response =  error.response;
                if(response.status === 500) {
                    this.responseMessage = "Error, Hulk smashed the server"
                }
                else if(response.status === 404) {
                    this.responseMessage = "Error, Wolverine sliced up this URL"
                }
                else{
                    this.responseMessage = "Error, X-Men not found. They're only functions."
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
        width: 100%;
    }
    #show-form-button{
        margin-top: 20px;
    }

    

    

    collection-name-input
    {
        width: 50%;
    }

    .actions
    {
        margin-top:10%;
        width:100%;
        display: flex;
        justify-content: space-between;
    }

    .actions button
    {
        padding:10px;
        font-size: 110%;
        font-family: Runners-bold;
        box-shadow: 2px 2px 5px rgba(0,0,0,.5);
        border-color: #000;
        border-width: 2px;
        border-style: solid;
        border-radius: 3px;
        outline:0;
    }

    



</style>