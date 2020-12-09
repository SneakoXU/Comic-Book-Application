<template>
  <div id="login" class="text-center" action="http://localhost:8080/login">
    <form class="form-signin" @submit.prevent="login">
      <h1 class="h1 mb-3 font-weight-normal">Please Sign In</h1>
      <div
        class="alert alert-danger"
        role="alert"
        v-if="invalidCredentials"
      >Invalid username and password!</div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >Thank you for registering, please sign in.</div>
      <label for="username" class="sr-only">Username</label>
      <input
        type="text"
        id="username"
        name="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <label for="password" class="sr-only">Password</label>
      <input
        type="password"
        id="password"
        name="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <router-link :to="{ name: 'register' }">Need an account?</router-link>
      <button type="submit">Sign in</button>
    </form>
    
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/login");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>
<style scoped>

  #login{
    background-image: url("../../assets/Images/green.jpg");
    display: flex;
    justify-content: center;
    align-items: center;
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    background-size: 100%;
    background-repeat: no-repeat;
    height: 400px;
    width: 65%;
    /* clip-path: polygon(0 0, 100% 0, 100% calc(100% - 4vw), 0 100%); */
  /* -moz-box-shadow:    20px 20px 10px 10px #000000;
  -webkit-box-shadow: 20px 20px 10px 10px #000000;
  box-shadow:         20px 20px 10px 10px #000000; */
    border-radius: 30px;
    box-shadow: 6px 12px black;
    
  } 

  .form-signin{
    display: flex;
    align-items: center;
    flex-direction: column;
    
  }

   #username:hover, #password:hover{
     width: 270px;
     box-shadow: 2px 5px;
   }

   #username, #password{
     margin-bottom: 15px;
     font-family:'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
   }

   #password{
     
   }

  button{
    margin-top: 5px;
    font-size: 20px;
  }

   button:hover{
    cursor: pointer;
  }


   input{
     height: 30px;
     width: 250px;
     font-size: 24px;
   }

   h1{
     font-family: "SBoom";
     font-size: 40px;
   }

   label{
     font-family: "SBoom";
   }



 



</style>
