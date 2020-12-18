<template>
    <div class="main">
        <div id="backgd"></div>
        
        <h1 v-if="notFound" class="no-results">Collection not found</h1>
        <div class="container">
            <div class="form-container">
                <div class="collection-info">
                <h2>{{this.results.data.name}}</h2>
                <button class='form-cancel' v-if="isUser" v-on:click="deleteCollection()">Delete Collection</button>
                <button class='form-edit' v-if="canSubscribe" v-on:click="subscribe()">Subscribe</button>
                <button class='form-cancel' v-if="canUnSubscribe" v-on:click="unSubscribe()">Unsubscribe</button>
                </div>
                <form v-if="this.$store.state.token!=''" action="">
                    <textarea v-bind:class="{'text-truncate':comment.text.length ==0}" v-model="comment.text" placeholder= "Leave a comment..." name="comments" id="comment-text"  rows="10"></textarea>
                    <p v-if="comment.text.length > 0 && comment.text.length <= 140">{{140 - this.comment.text.length}} characters left</p>
                    <p class='error' v-if="comment.text.length > 140">Delete {{this.comment.text.length - 140}} characters to submit</p>
                    <button class="form-add" v-if="comment.text.length > 0 && comment.text.length <= 140" type="submit" v-on:click="addComment()">Add Comment</button>

                </form>
                <h2 class="comment-title">Comments</h2>
                <div v-for="comment in comments.data" v-bind:key="'comment:' + comment.id">
                    <div class="comment">
                    <h3 class="commenter">
                        {{comment.commenter_name}}
                    </h3>
                    <p class="comment">{{comment.text}}</p>
                    <button class="form-cancel" v-if="comment.commenter_id == activeUser" v-on:click="deleteComment(comment.id)">Delete</button>
                    </div>
                </div> 
            </div>
            <div class="no-comics" v-if="results.data.comicBookIDs.length==0">
                <h2 >No Comics in this Collection</h2>
                <h3 class="search-suggestion" v-if="isUser">Go to Search to Add Comics</h3>
            </div>
            <div class='comics'>
                <div class="result-container"  v-for="result in results.data.comicBookIDs" v-on:click="setDetail(result)" v-bind:key="'comic:' +result.id">
            
                    <div class="inception" >
                        <p id="title">{{result.title}}</p>
                        <!-- NEED TO FIX LOADING GIF -->
                    </div>
                <img :src="result.thumbnail.path.substring(0, result.thumbnail.path.length - 4) + '/portrait_xlarge.jpg'" alt="Comic Book Image Result" :title="result.title" class="result-image">
                </div>
            </div>
               
        </div>

        <div class="search-container">
            <div class="popup" v-if="detailShowing">
                <div class="clickable-overlay" v-on:click="detailShowing=false"></div>
                <comic-detail
                :result="this.comic" 
                />
                <button class="form-search" v-on:click="detailShowing=false">Close</button>
                <button class="form-cancel" v-if="this.results.data.userID == this.activeUser && this.$store.state.token != ''" v-on:click="removeComic(comic.id)">Delete</button>
                
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
            this.comment.commenter_id = this.activeUser;
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

.comics{
    margin-top: 10vh;
    width:75vw;
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
}

button
{
    margin-top:5vh;
    width: 100%;
}

textarea
{
    margin-top: 30px ;
    max-width: 100%;
    min-width: 100%;
}

.text-truncate
{
    
    max-height: 40px;
}



.container
{
    max-width: 100vw;
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

.comment-title
{
    font-size: 200%;
}

.commenter
{
    margin-top: 0;
}

div.comment {
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

.form-container
{
    margin-top: 0;
}

h2 {
    font-size: 300%;
    text-align: center;
}

.no-comics {
    color: darkgray;
    text-align: center;
    margin-left: 10vw;
}
button {
    margin: 0px;
}

@media only screen and (orientation:portrait) {

    .comics, .form-container
    {
        width:45%;
    }

    .container
    {
        max-width: 100vw;
    }

    .form-container
    {
        position: unset;
        max-width: 45vw;
        margin-left: 2vw;
    }

    h2
    {
        margin-top: 10vh;
        max-width: 50vh;
        word-wrap: break-word;
        overflow-wrap: break-word;
        white-space:pre-wrap;
    }

    .comics
    {
        margin-top: 10vh;
        justify-content: center;
    }

    .no-comics 
    {
        opacity: 0;
    }

}

</style>