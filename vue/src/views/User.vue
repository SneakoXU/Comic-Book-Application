<template>
  <div class="main">
      
    <div id="backgd"></div>
    <div class="user-info">
        <h1>{{this.username}}</h1>
        <p>{{description}}</p>
        <button class="form-edit" v-on:click="showEdit = true" v-if="this.isUser">Edit Profile</button>
        <div class='popup' v-if="showEdit">
            <label for="newname">Username:</label>
            <input type="text" name="newname" id="newname" v-model="username" placeholder="New Username">
            <p class='error'>{{nameErrMsg}}</p>
            <label for="newdesc">Description:</label>
            <textarea rows="4" name="newdesc" id="newdesc" v-model="description" placeholder="New Description" /> 
            <div>
                <button class="form-add" v-on:click="submitEdit()" >Save</button>
                <button class="form-cancel" v-on:click="refreshParams()" >Cancel</button>
            </div>
        </div>
        <button class="form-add" v-on:click="addFriend()" v-if="this.canAdd">Add friend</button>
        <h2 v-if="requestSent">Request sent!</h2>
        <button class="form-cancel" v-on:click="removeFriend()" v-if="this.canRemove">Remove friend</button>
        <div class="incoming" v-if="incomingRequests.length != 0">
            <h2>Incoming requests</h2>
            <div class="request" v-for="request in incomingRequests" v-bind:key="request.id">
                <h3>
                    <router-link class="link" v-bind:to="{ name: 'user' , params: {username: request.sender.username}}">
                        {{request.sender.username}}
                    </router-link>    
                        sent you a request
                </h3>
                <div>
                    <button class="form-add" v-on:click="acceptRequest(request.id)">Accept</button>
                    <button class="form-cancel" v-on:click="denyReqest(request.id)">Deny</button>
                </div>
                
            </div>
        </div>
        <div class="outgoing" v-if="outgoingRequests.length != 0">
            <h2>Outgoing requests</h2>
            <div class="request" v-for="request in outgoingRequests" v-bind:key="request.id">
                <h3>{{outgoingMessage(request)}}</h3>
                <div>
                    <button class="form-cancel" v-if="isPending(request)" v-on:click="cancelRequest(request.id)">Cancel</button>
                    <button class="form-add" v-if="toBeAcknowleged(request)" v-on:click="acknowledgeRequest(request.id)">Okay</button>
                </div>
            </div>
        </div>
    </div>
    
    <div class="collections">
        <div class="header">
            <h2>Collections</h2>
        
        </div>
        <div class="result-container" v-for="result in results.data.results" v-bind:key="'collection:' + result.id">
            <router-link class="result-container" v-bind:to="{ name: 'comics', params: {id: result.id}}">
                <img class="result-image" v-if="isLoading" src="../../assets/Images/loading.gif"/>
                <div class="inception"  v-if ="!isLoading">
                    <p id="title">{{result.name}}</p>
                    <!-- NEED TO FIX LOADING GIF -->              
                </div>
                <!-- <router-link v-bind:to="{name: 'comic'}" v-show="onClick() === true"> -->
                <img :src="getCollectionImage(result)" alt="Collection Thumbnail" :title="result.title" class="result-image">
            </router-link>
            
        </div>
    </div>
  </div>
  
</template>

<script>
import AuthService from '../services/AuthService';
import CollectionService from '../services/CollectionService';
export default {
    components: {
        
    },
    data()
    {
        return{
            username: '',
            showEdit: false,
            description: '',
            tempdesc:'',
            nameErrMsg: '',
            tempname:'',
            userId: 0,
            canAdd: false,
            isUser:false,
            canRemove: false,
            requestSent:false,
            user: 
            {
                username: "",
                password: ""
            },
            results: 
            {
                data: 
                {
                    results: []
                }
            },
            incomingRequests: [],
            outgoingRequests: []
        }
    },
    created()
    {
        this.username = this.$route.params.username
        CollectionService.getCollectionsByOwner(this.username)
        .then(response => 
        {
            this.results = response.data;
            this.showNextButtons = true;
            this.isLoading = false;
            this.$forceUpdate();
        })

        this.isUser = this.username == this.$store.state.user.username;
        this.refreshParams()
    },
    watch:
    {
        $route()
        {
            this.username = this.$route.params.username
            CollectionService.getCollectionsByOwner(this.username).then(response => 
                {
                    this.results = response.data;
                    this.showNextButtons = true;
                    this.isLoading = false;
                    this.$forceUpdate();
                });
                        
            
            this.refreshParams()
            this.$nextTick(this.$forceUpdate)
        }
    },
    updated()
    {
        
    },
    name: 'user',
    methods:
    {
        refreshParams()
        {
            AuthService.getIdByUsername(this.username).then(response => 
            {
            this.userId = response.data;
                AuthService.getUserById(this.userId).then(res=>
                {
                    console.log(res);
                    if(res.data.description != undefined)
                    {
                        this.description = res.data.description
                    }
                    else
                    {
                        this.description = ''
                    }
                })
            })
            
            this.isUser = this.username == this.$store.state.user.username;
            this.incomingRequests = [];
            this.outgoingRequests = [];
            this.canAddFriend();
            this.canRemoveFriend()
            this.getOutgoing();
            this.getIncoming();
            this.requestSentMethod()
            this.showEdit=false;
        },
        isPending(request)
        {
            return request.status == 0;
        },
        toBeAcknowleged(request)
        {
            return request.status == 1 || request.status == 2;
        },
        outgoingMessage(request)
        {
            switch(request.status) 
            {
                case 0:
                    return "Request sent to " + request.recipient.username
                case 1:
                    return request.recipient.username + " accepted your request"
                case 2:
                    return request.recipient.username + " denied your request"
                default:
                    return "Something went wrong"
            }
        },
        submitEdit()
        {
            AuthService.setName(this.username).then(res =>
            {
                if(res.data == true)
                {
                    if(this.description != undefined && this.description.length > 0)
                    {
                        AuthService.setDesc(this.fixString( this.description));
                    }
                    this.showEdit = false;
                    this.$router.push("/user/" + this.username);
                    this.user.password = this.$store.state.user.password;
                    this.$store.commit("LOGOUT");
                    this.login();
                    
                }
                else
                {
                    this.nameErrMsg = "Name already exists";
                }
            });
            
        },
        login() 
        {
            this.user.username = this.username;
            AuthService
                .login(this.user)
                .then(response => 
                {
                if (response.status == 200) {
                    this.$store.commit("SET_AUTH_TOKEN", response.data.token);
                    response.data.user.password = this.user.password;
                    this.$store.commit("SET_USER", response.data.user);
                    this.$forceUpdate()
                    this.refreshParams();
                }
                })
                .catch(error => 
                {
                const response = error.response;

                if (response.status === 401) {
                    this.invalidCredentials = true;
                }
                });
                this.$parent.showLogin=false;
        },
        fixString(tempString)
        {

            let illegalCharacters= 
            {
                '%' : '%25'
                // ' ' : '+',
                // '!' : '%21',
                // '@' : '%40',
                // '#' : '%23',
                // '$' : '%24',
                // '^' : '%5E',
                // '&' : '%26',
                // '"' : '%27',
                // '(' : '%28',
                // ')' : '%29',
                // ',' : '%2C',
                
            }
            for (let key in illegalCharacters)
            {
                tempString = tempString.replaceAll(key, illegalCharacters[key])
            }
            if(tempString.length != 0)
                tempString += '';
            return tempString;
        },
        getOutgoing()
        {
            if(this.isUser)
            {
                AuthService.getOutgoing().then(res=>
                {
                    this.outgoingRequests = res.data
                })
            }
        },
        getIncoming()
        {
            if(this.isUser)
            {
                AuthService.getIncoming().then(res=>
                {
                    this.incomingRequests = res.data
                })
            }
        },
        acceptRequest(id)
        {
            AuthService.acceptIncoming(id)
            .then(()=>
            {
                this.refreshParams()
            })
        },
        denyReqest(id)
        {
            AuthService.denyIncoming(id)
            .then(()=>
            {
                this.refreshParams()
            })
        },
        cancelRequest(id)
        {
            AuthService.cancelOutgoing(id)
            .then(()=>
            {
                this.refreshParams()
            })
            
        },
        acknowledgeRequest(id)
        {
            AuthService.acknowledgeOutgoing(id)
            .then(()=>
            {
                this.refreshParams()
            })
        },
        addFriend()
        {
            AuthService.addFriend(this.userId)
            .then(()=>
            {
                this.refreshParams()
            })
        },
        canAddFriend()
        {
            AuthService.getFriends()
            .then(friends =>
            {
                AuthService.getOutgoing().then(requests =>
                {
                    this.canAdd = !this.isUser && (friends.data.find(e => e.username == this.username) == undefined) && (requests.data.find(e => e.sender.username == this.$store.state.user.username && (e.status !=3 && e.status != 4) && e.recipient.username == this.username) == undefined);
                })
                
            })
            .catch(() =>
            {
                this.canAdd = false;
            })
        },
        requestSentMethod()
        {
            AuthService.getOutgoing().then(requests=>
            {
                this.requestSent = !this.isUser && (requests.data.find(e => e.sender.username == this.$store.state.user.username && (e.status !=3 && e.status != 4) && e.recipient.username == this.username) != undefined)
            })
        },
        canRemoveFriend()
        {
            AuthService.getFriends()
            .then(res =>
            {
                this.canRemove = !this.isUser && (res.data.find(e => e.username == this.username) != undefined);
            })
            .catch(() =>
            {
                this.canRemove = false;
            })
        },
        removeFriend()
        {
            AuthService.removeFriend(this.userId)
            .then(()=>
            {
                this.refreshParams()
            })
        },

        getCollectionImage(result)
        {
            let comic = result.comicBookIDs;
            return comic[0] == undefined? "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available/portrait_xlarge.jpg":comic[0].thumbnail.path.substring(0, comic[0].thumbnail.path.length -4) + "/portrait_xlarge.jpg";
        }
    }

}
</script>

<style scoped>

textarea
{
    font-family: 'Runners';
}

.request
{
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: space-between;
}

h1
{
    margin-top:10vh;
    padding:0;
    margin:0;
}

.main
{
    display: flex;
}

button
{
    width: 100%;
}

.request button
{

    width: unset;
    height: unset;
    margin:0;
}

.user-info
{
    padding-left: 1vw;
    margin-top: 10vh;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-items: top;
    width: 25vw;
}

.header
{
    margin-top:-7vh;
    font-size: 150%;
}

.inception
{
    max-width:200px;
    white-space: nowrap;

    overflow: hidden;
}

.collections
{
    margin-top:15vh;
    display: flex;
    width: 100vw;
}

p
{
    font-size: 120%;
    magin-top:0;
}
</style>