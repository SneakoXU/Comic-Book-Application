<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
    <router-view />
  </div>
</template>

<style>
  @font-face{
    font-family: "Roof Runners";
    /* src: url("https://www.1001fonts.com/roof-font.html") format("truetype") */
    src: local("../assets/roof.runners.tff") format("truetype")
  }

  *{
    font-family: "Roof Runners", sans serif;
  }
</style>

