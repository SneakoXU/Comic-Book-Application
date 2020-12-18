<template>
  <div id="app">
    
    <div class="nav" :class="{navw : watchParty}">
      <router-link class="navItem" :class="{navItemw : watchParty}" v-bind:to="{ name: 'home' }" >
        <img class="logo" :class="{logow : watchParty}" src="../assets/Images/BTC icon.png" alt="BT Comic Logo">
      </router-link>
      <login v-if="showLogin" />
      <register v-if="showRegister" />
      <div class="itemBox">
        <router-link class="navItem" :class="{navItemw : watchParty}"  v-bind:to="{ name: 'search' }">Search</router-link>
        <router-link class="navItem" :class="{navItemw : watchParty}" v-bind:to="{ name: 'collection-display' , params: {username: $store.state.user.username}}" v-if="loggedIn">Collections</router-link>
        <router-link class="navItem" :class="{navItemw : watchParty}" v-bind:to="{ name: 'friends', params: {username: $store.state.user.username}}" v-if="loggedIn">Friends</router-link>
        <a class="navItem" :class="{navItemw : watchParty}" v-if="!loggedIn" v-on:click="showLogin=true, showRegister = false">Login</a>
        <a class="navItem" :class="{navItemw : watchParty}"  v-if="!loggedIn" v-on:click="showLogin=false, showRegister = true">Register</a>
        <router-link class="navItem" :class="{navItemw : watchParty}" id="usr" v-bind:to="{ name: 'user', params: {username: $store.state.user.username}}" v-if="loggedIn">{{$store.state.user.username}}</router-link>
        <router-link class="navItem" :class="{navItemw : watchParty}" v-bind:to="{ name: 'logout' }" v-if="loggedIn">Logout</router-link>
      </div>
    </div>
    <router-view  />
  </div>
</template>
<script>
import Login from './views/Login.vue';
import Register from './views/Register.vue';

export default 
{
  components:
  {
    Login,
    Register
  },
  data()
  {
    return{
      showLogin: false,
      showRegister: false,
      watchParty:false
    }
  },
  created()
  {

  },
  computed:
  {
    
    loggedIn()
    {
      return this.$store.state.token != '';
    }
  },
  methods:
  {

    login()
    {
      this.register= false;
      this.showLogin = true;
    },
    register()
    {
      this.showLogin = false;
      this.register= true;
    }
  }
}
</script>
<style>
  @font-face{
    font-family: "Runners";    
    src: url("../assets/Fonts/roof/PG_Roof Runners.ttf");
  }

  @font-face{
    font-family: "Runners-bold";    
    src: url("../assets/Fonts/roof/PG_Roof Runners_bold.ttf");
  }

  @font-face{
    font-family: "SBoom";
     src: url("../assets/Fonts/super_boom/SUPER BOOM.ttf");
  }
  
  
  *{
    font-family: "Runners", sans serif;
    text-decoration: none;
  }



  i
{
    float: right;
}

.online
{
    color: #0F0;
    text-shadow: 0px 0px 5px #4F4;
}

.offline
{
    color: rgb(85, 85, 85);
}

  input::placeholder
  {
    font-family: 'Runners';
  }

  input[type="password"]
  {
    font-family: 'Courier New', Courier, monospace;
  }
  html{
    background-image: url("../assets/Images/blue.jpg");
    background-size: cover;
    /*background-size: 100%;*/
    background-attachment: fixed;
    background-repeat: no-repeat;
  }
  .nav{
    background-color: #ED1D24;
    box-shadow: 5px 5px black;
    /* transform: skew(-10deg); */
    display: flex; 
    width: 100%;  
    position: fixed; 
    top:0;
    z-index: 100;
  }

  body
  {
    margin:0;
  }

  .error
  {
    margin-top:0;
    color:#ED1D24;
  }

  .itemBox{
    display: flex;
    width: 100%;
    justify-content: flex-end;
    padding-bottom: 5px;
  }

  .navItem{
    font-weight: bold;
    text-decoration: none;
    color: black;
    font-size: 20px;
    /* margin-bottom: 2px; */
    margin: auto 0.5%;

  }

  .navItem:hover{
    /* color: #2A75B3; */
    color: #FAA;
  }

  /* .navItem{
    display: flex;
    justify-content: space-evenly;
    width: 100%;
    padding: 2px 2px 2px 2px;
    margin: 2px 2px 2px 2px;
  } */

  img{
    height: 70px;
    width: 100px;
  }

  input, select, textarea
    {
        font-size: 20px;
        padding: 5px;
        box-sizing:border-box;
        box-shadow: 2px 2px 5px rgba(0,0,0,.5);
        border-color: #000;
        border-width: 2px;
        border-style: solid;
        border-radius: 3px;
        font-family: 'Runners-bold';
        outline:none;
        resize: none;
    }

    button
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

    .form-submit, .form-add
    {
        background-color: #0C2;
    }
    .form-submit:hover, .form-add:hover
    {
      color:#CFC;
      cursor: pointer;
    }

    a
    {
      cursor: pointer;
    }

    .form-search
    {
      background-color:#FFF;
    }

    .form-search:hover
    {
      background-color:#BBB;
      cursor: pointer;
    }

    .form-edit
    {
      background-color:rgb(167, 61, 144);
    }

    .form-edit:hover
    {
      color:rgb(238, 201, 238);
      cursor: pointer;
    }

    .form-create
    {
        background-color: #1a65c9;
    }
    .form-create:hover
    {
      color:#c4d9f5;
      cursor: pointer;
    }

    .form-cancel
    {
        background-color: #ED1D24;
    }

    .form-cancel:hover
    {
      color:#FCC;
      cursor: pointer;
    }

    
.link
{
  color:#ED1D24;
}

.link:visited
{
  color: #ED1D24;
}

@media only screen and (orientation:landscape) {

.popup
{
    left:25%;
    top:20%;
    background-color: #FFF;
    position: fixed;
    width:40%;
    min-height:12%;
    display: flex;
    flex-direction: column;
    padding:2% 5%;
    border-width: 2px;
    box-shadow: 7px 7px 5px rgba(0,0,0,.5);
    border-color: #000;
    border-width: 2px;
    border-style: solid;
    border-radius: 3px;
    z-index: 100;
    
}


}

.image-card
{
  width: 20vw;
  height: 24.4vw;
  display: flex;
  box-shadow: 7px 7px 5px rgba(0,0,0,.5);
  border-color: #000;
  border-width: 2px;
  border-style: solid;
  border-radius: 3px;
}

.popup div h2
{
  color: #000;
  text-shadow: 2px 2px 1px rgba(0,0,0,.5);
}

.result-container{
width:200px;
display: flex;
flex-wrap: wrap;
max-height: 40vh;
margin: 20px 30px 20px 30px;
justify-content: center;
text-decoration: none;
color:black;

} 
.result-container:visited
{
  color:black;
  text-decoration: none;
}

.result-image
{
height: 240px;
width: 250px;
display: flex;
box-shadow: 7px 7px 5px rgba(0,0,0,.5);
border-color: #000;
border-width: 2px;
border-style: solid;
border-radius: 3px;
}

.clickable-overlay
{
  position: fixed;
  width: 100vw;
  height: 100vh;
  top:0;
  left:0;
  z-index: -1;
}

.no-results
{
    align-self: center;
    margin-left: 35%;
    color:#999;
}

  .navw
{
    background-color: rgba(0,0,0,0);
    box-shadow:unset;
}

.navItemw
{
    color: rgba(255,255,255,0.1);
}

.logow
{
  opacity: .3;
}
.logow:hover
{
  opacity: 1;
}


*:visited
{
  text-decoration: none;
}


@media only screen and (orientation:portrait) {


  .itemBox
  {
    justify-content: space-around;
  }
  .navItem
  {
    font-size: 150%;
  }

  .image-card
  {
    width: 40vw;
    height: 48.8vw;
  }

  .collection-container
  {
    width: 100vw;
    flex-wrap: wrap;
    top:0
  }

  .wrapper
  {
    padding-top: 10vh;
    min-width: 20vw;
  }

  .popup
    {
      background-color: #FFF;
      position: fixed;
      width:40%;
      min-height:10%;
      display: flex;
      flex-direction: column;
      padding:2% 5%;
      border-width: 2px;
      box-shadow: 7px 7px 5px rgba(0,0,0,.5);
      border-color: #000;
      border-width: 2px;
      border-style: solid;
      border-radius: 3px;
      z-index: 100;
      min-width: 80vw;
      left:5%;
      top:30%;
    }

  #title
  {
    text-align: center;
  }

}

    
</style>


