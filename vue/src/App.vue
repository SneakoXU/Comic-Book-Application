<template>
  <div id="app">
    <div id="nav">
      <img class="logo" src="../assets/Images/BTC icon.png" alt="BT Comic Logo">
      <div class="itemBox">
      <router-link class="navItem" v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link class="navItem" v-bind:to="{ name: 'login' }">Login</router-link>&nbsp;|&nbsp;
      <router-link class="navItem" v-bind:to="{ name: 'register' }">Register</router-link>&nbsp;|&nbsp;
      <router-link class="navItem" v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
      </div>
    </div>
    <router-view />
  </div>
</template>

<style>
  @font-face{
    font-family: "Roof Runners", "Super Boom";
    /* src: url("https://www.1001fonts.com/roof-font.html") format("truetype") */
    src: local("../assets/roof.runners.tff") format("truetype"), local("../assets/SUPER BOOM.ttf") format("truetype");


  }

  *{
    font-family: "Roof Runners", sans serif;
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

