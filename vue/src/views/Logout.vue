<template>
  <h1>Logout</h1>
</template>

<script>
import AuthService from '../services/AuthService'

export default {
  created() 
  {
    AuthService.logout();
    this.$store.commit("LOGOUT");
    this.$router.push("/");
  }
};
</script>
