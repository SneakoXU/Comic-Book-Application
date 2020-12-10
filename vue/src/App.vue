<template>
  <div id="app">
    <div id="nav">
      <img class="logo" src="../assets/Images/BTC icon.png" alt="BT Comic Logo">
      <div class="itemBox">
      <router-link class="navItem" v-bind:to="{ name: 'home' }" >Home</router-link>&nbsp;|&nbsp;
      <router-link class="navItem" v-bind:to="{ name: 'login' }" >Login</router-link>&nbsp;|&nbsp;
      <router-link class="navItem" v-bind:to="{ name: 'register' }" >Register</router-link>&nbsp;|&nbsp;
      <router-link class="navItem" v-bind:to="{ name: 'collection-display' }">Collections</router-link>&nbsp;|&nbsp;
      <router-link class="navItem" v-bind:to="{ name: 'search' }">Search</router-link>&nbsp;|&nbsp;
      <router-link class="navItem" v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
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
    font-family: "SBoom";
     src: url("../assets/Fonts/super_boom/SUPER BOOM.ttf");
  }
  
  *{
    font-family: "Runners", sans serif;
  }
  html{
    background-image: url("../assets/Images/blue backgd.jpg");
    background-size: cover;
  }
  #nav{
    background-color: #ED1D24;
    box-shadow: 5px 3px black;
    transform: skew(-10deg);
    display: flex;
  }

  .itemBox{
    display: flex;
    width: 100%;
    justify-content: flex-end;
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


