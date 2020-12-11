<template>
  <div id="app">
    <div id="nav">
      <router-link class="navItem" v-bind:to="{ name: 'home' }" >
        <img class="logo" src="../assets/Images/BTC icon.png" alt="BT Comic Logo">
      </router-link>
      <div class="itemBox">

        <router-link class="navItem" v-bind:to="{ name: 'collection-display' }">Collections</router-link>
        <router-link class="navItem" v-bind:to="{ name: 'search' }">Search</router-link>
        <router-link class="navItem" v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
        <router-link class="navItem" v-bind:to="{ name: 'login' }" >Login</router-link>
        <router-link class="navItem" v-bind:to="{ name: 'register' }" >Register</router-link>
      </div>
    </div>
    <router-view />
  </div>
</template>
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
</style>


