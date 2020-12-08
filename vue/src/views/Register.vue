<template>
  <div id="register" class="text-center">
    <form class="form-register" @submit.prevent="register">
      <h1 class="h3 mb-3 font-weight-normal">Create Account</h1>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <label for="username" class="sr-only">Username</label>
      <input
        type="text"
        id="username"
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
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <input
        type="password"
        id="confirmPassword"
        class="form-control"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required
      />
      <router-link :to="{ name: 'login' }">Have an account?</router-link>
      <button class="btn btn-lg btn-primary btn-block" type="submit">
        Create Account
      </button>
    </form>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style scoped>


  #register{
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
    height: 450px;
    width: 65%;
    /* clip-path: polygon(0 0, 100% 0, 100% calc(100% - 4vw), 0 100%); */
  /* -moz-box-shadow:    20px 20px 50px 50px #000000;
  -webkit-box-shadow: 20px 20px 50px 50px #000000;
  box-shadow:         20px 20px 20px 30px #000000; */
  border-radius: 30px;
  box-shadow: 6px 12px black;
    margin-top: 10px;
  } 
    .form-register{
    display: flex;
    align-items: center;
    flex-direction: column;
    justify-content: center;
  }

     #username, #password, #confirmPassword{
     margin-bottom: 15px;
   }

  button{
    margin-top: 5px;
    font-size: 20px;
  }

  button:hover{
    cursor: pointer;
  }

  label{
    font-family: "Super Boom";
  }

  input{
    height: 30px;
     width: 250px;
     font-size: 32px;
  }

  #username:hover, #password:hover, #confirmPassword:hover{
    width: 270px; 
    box-shadow: 2px 5px;
    
  }

  h1{
    font-family: "Super Boom";
    font-size: 40px;
  }

 
</style>
