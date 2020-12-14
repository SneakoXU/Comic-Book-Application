<template>
  <div id="app">
    
    <div id="nav">
      <router-link class="navItem" v-bind:to="{ name: 'home' }" >
        <img class="logo" src="../assets/Images/BTC icon.png" alt="BT Comic Logo">
      </router-link>
      <div class="itemBox">
        <router-link class="navItem" v-bind:to="{ name: 'search' }">Search</router-link>
        <router-link class="navItem" v-bind:to="{ name: 'collection-display' , params: {username: $store.state.user.username}}" v-if="loggedIn">Collections</router-link>
        <router-link class="navItem" v-bind:to="{ name: 'friends', params: {username: $store.state.user.username}}" v-if="loggedIn">Friends</router-link>
        <router-link class="navItem" v-bind:to="{ name: 'login' }" v-if="!loggedIn">Login</router-link>
        <router-link class="navItem" v-bind:to="{ name: 'register' }" v-if="!loggedIn">Register</router-link>
        <router-link class="navItem" id="usr" v-bind:to="{ name: 'user', params: {username: $store.state.user.username}}" v-if="loggedIn">{{$store.state.user.username}}</router-link>
        <router-link class="navItem" v-bind:to="{ name: 'logout' }" v-if="loggedIn">Logout</router-link>
      </div>
    </div>
    <router-view />
  </div>
</template>
<script>
export default {
  computed:
  {
    loggedIn()
    {
      return this.$store.state.token != '';
    }
  },
  methods:
  {
    
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
  #nav{
    background-color: #ED1D24;
    box-shadow: 5px 5px black;
    /* transform: skew(-10deg); */
    display: flex; 
    width: 100%;  
    position: fixed; 
    top:0;
  }

  body
  {
    margin:0;
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

  input, select
    {
        font-size: 20px;
        padding: 5px;
        box-sizing:border-box;
        box-shadow: 2px 2px 5px rgba(0,0,0,.5);
        border-color: #000;
        border-width: 2px;
        border-style: solid;
        border-radius: 3px;
        font-family: Runners-bold;
        outline:none;
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

    .form-search
    {
      background-color:#FFF;
    }

    .form-search:hover
    {
      background-color:#BBB;
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

.popup
{
    left:25%;
    top:20%;
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
    z-index: 1;
    
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

.result-image{
height: 275px;
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

    
</style>


