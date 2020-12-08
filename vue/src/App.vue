<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'login' }">Login</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'register' }">Register</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
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
  
</style>

