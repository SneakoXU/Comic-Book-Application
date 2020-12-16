<template>
    <div class="main">
        <div id="backgd"></div>
        
        <h1 v-if="notFound" class="no-results">Collection not found</h1>
        <div class="container">
            <div class="form-container">
                <h2>{{this.results.data.name}}</h2>
                <button class='form-cancel' v-if="isUser" v-on:click="deleteCollection()">Delete Collection</button>
                <button class='form-edit' v-if="canSubscribe" v-on:click="subscribe()">Subscribe</button>
                <button class='form-cancel' v-if="canUnSubscribe" v-on:click="unSubscribe()">Unsubscribe</button>
                <form v-if="this.$store.state.token!=''" action="">
                    <textarea v-model="comment.text" name="comments" id="comment-text"  rows="10"></textarea>
                    <button class="form-add" type="submit" v-on:click="addComment()">Add Comment</button>

                </form>
                <div v-for="comment in comments.data" v-bind:key="'comment:' + comment.id">
                    <h3>
                        {{comment.commenter_name}}
                    </h3>
                    <p>{{comment.text}}</p>
                    <button class="form-cancel" v-if="comment.commenter_id == activeUser" v-on:click="deleteComment(comment.id)">Delete</button>
                </div> 
            </div>
            <div class="result-container"  v-for="result in results.data.comicBookIDs" v-on:click="setDetail(result)" v-bind:key="'comic:' +result.id">
                <div class="inception" >
                    <p id="title">{{result.title}}</p>
                    <!-- NEED TO FIX LOADING GIF -->              
                </div>

                <img :src="result.thumbnail.path.substring(0, result.thumbnail.path.length - 4) + '/portrait_xlarge.jpg'" alt="Comic Book Image Result" :title="result.title" class="result-image">
            </div>
               
        </div>

        <div class="search-container">
            <div class="popup" v-if="detailShowing">
                <div class="clickable-overlay" v-on:click="detailShowing=false"></div>
                <comic-detail
                :result="this.comic" 
                />
                <button class="form-search" v-on:click="detailShowing=false">Close</button>
                <button class="form-cancel" v-if="this.results.data.userID == this.userId" v-on:click="removeComic(comic.id)">Delete</button>
                
            <!-- <router-link v-bind:to="{name: 'comic'}" v-show="onClick() === true"> -->
            </div>
        </div>


    

  </div> 
</template>

<script>
import CollectionService from '../services/CollectionService.js';
import ComicDetail from "../components/ComicDetail.vue";
import AuthService from '../services/AuthService.js';


export default {
    name: 'comics',
    collectionId : 0,
    components: 
    {
        ComicDetail
    },
    data()
    {
        return{
            notFound :false,
            detailShowing: false,
            activeUser: 0,
            userId:0,
            isUser: false,
            canSubscribe: false,
            canUnSubscribe:false,
            results: 
            {
                data: 
                {
                    results: []
                }
            },
            comic : 
            {
                id:0
            },
            comment: 
            {
                collection_id: 0,
                text: '',
                commenter_id: 0
            },
            comments: 
            {
                data:[]
            }
        }
    },

    watch:
    {
        $route()
        {            
            this.$forceUpdate();           
            
        }
    },

    beforeCreate()
    {
        
    },
    created()
    {
        this.refresh();
        
        
    },

    methods:
    {
        refresh()
        {
            this.collectionId = this.$route.params.id
            CollectionService.getCollectionsById(this.collectionId)
            .then(response =>
            {
                
                this.results = response;
                this.userId = response.data.userID;
                AuthService.getUserById(this.userId)
                .then(response=>
                {
                    
                    AuthService.getId().then(res=>
                    {
                        this.activeUser = res.data;
                        AuthService.getUserById(this.userId).then(res=>
                        {
                            this.isUser = res.data.username == this.$store.state.user.username
                            this.checkCanSubscribe();
                            this.checkCanUnSubscribe();
                            this.getComments();
                        });
                    });

                    
                })
                .catch(response =>
                {
                });
            })
            .catch(error =>
            {
                if (error.response.status === 404) 
                {
                    this.notFound = true;
                }
            })
        },
        checkCanSubscribe()
        {
            AuthService.isSubscribed(this.results.data.id).then(res=>
            {
                this.canSubscribe = !this.isUser && this.$store.state.token != '' && !res.data
            })
        },
        checkCanUnSubscribe()
        {
            AuthService.isSubscribed(this.results.data.id).then(res=>
            {
                this.canUnSubscribe = !this.isUser && this.$store.state.token != '' && res.data
            })
        },
        subscribe()
        {
            CollectionService.subscribe(this.results.data.id).then(()=>
            {
                this.refresh();
            })
        },
        unSubscribe()
        {
            CollectionService.unSubscribe(this.results.data.id).then(()=>
            {
                this.refresh();
            })
        },
        deleteCollection()
        {
            CollectionService.deleteCollection(this.collectionId).then(()=>
            {
                this.$router.push("/user/" + this.$store.state.user.username + "/collections");
            });
            
        },

        removeComic(comicId)
        {
            CollectionService.removeComicFromCollection(this.collectionId, comicId).then(() =>
            {
            CollectionService.getCollectionsById(this.collectionId).then(response =>
            {
                this.results = response;
            })}
            );
            this.detailShowing=false;
            this.$forceUpdate;
        },
        setDetail(comic){
            this.comic=comic;
            this.detailShowing=true;
            
        },

        deleteComment(id){
            CollectionService.deleteComment(id).then(()=>
            {
                CollectionService.getComments(this.collectionId).then(res =>
                {
                    this.comments = res;
                })
                
            })
            
        },

        addComment(){
            this.comment.commenter_id = this.userId;
            this.comment.collection_id = this.collectionId;
            CollectionService.addComment(this.comment)
        },

        getComments(){

            CollectionService.getComments(this.collectionId).then(response => {
                this.comments = response;
                this.$forceUpdate()
            })

        },
        getUserById: async function(id)
        {
            AuthService.getUserById(id).then(res=>
            {
                return res.data.username;

            })
        }
    }
}
</script>
  
<style scoped>

button
{
    margin-top:5vh;
    width: 100%;
}

textarea {
    margin-top: 30px ;
    max-width: 100%;
    min-width: 100%;
}

.container
{
    width: 100vw;
    margin-top:5%;
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
}

.result-container
{
    cursor: pointer;
}

#title
{
    max-width:200px;
    white-space: nowrap;

    overflow: hidden;
}

</style>