<template>
  <div  class="popup">
    <form class="form-register" @submit.prevent="register">
      <h1 >Create Account</h1>
      <div class="error" role="alert" v-if="registrationErrors">
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
      <a class="link" v-on:click="$parent.showLogin=true, $parent.showRegister=false">Have an account?</a>
      <div><button class="btn btn-lg btn-primary btn-block" type="submit">
      Create Account
      </button>
      <button class="form-cancel" v-on:click.prevent="$parent.showRegister=false">Close</button></div>
    </form>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      user: 
      {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: 
  {
    register() 
    {
      if (this.user.password != this.user.confirmPassword) 
      {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } 
      else 
      {
        authService
          .register(this.user)
          .then(() => 
          {
            this.$parent.showLogin=false;

            authService
              .login(this.user)
              .then(response => 
              {
                if (response.status == 200) {
                  this.$store.commit("SET_AUTH_TOKEN", response.data.token);
                  this.$store.commit("SET_USER", response.data.user);
                  this.$parent.showRegister=false

                }
              })
              .catch(error => 
              {
                const response = error.response;

                if (response.status === 401) {
                  this.invalidCredentials = true;
                }
              });
        
    
          })
          .catch((error) => 
          {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) 
            {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
            else if (response.status === 409) 
            {
              this.registrationErrorMsg = 'User already exists';
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

    }

  input{
    height: 30px;
     width: 250px;
     font-size: 24px;
  }

  #username:focus, #password:focus, #confirmPassword:focus{
    width: 270px; 
    box-shadow: 2px 5px;
    
    
  }

  #password, #confirmPassword{
    
  }
  
  h1{
    /* font-family: "SBoom"; */
    font-size: 40px;
    margin-top:-4%;
  }

 .popup
  {
    left:32.5vw;
    width: 25vw;
  }
</style>
