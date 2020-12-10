# Java Server Documentation v1.1b
for yall doing the client <3

## Paths

***
- ## Comics 
    -     /comics/query/title/{name}
         - Makes request to Marvel API to get the first 100 comics that begin with {name}
         - Public
  
    -     /comics/query/id/{id}
         - Makes request to Marvel API to get a comic with the matching {id}.
         - Public
    -     /comics/thumbnail/{id}
         - Gets the url of the comic in the database matching {id} as a String 
         - Public 
     ***

- ## Collections
    -     /collections/public
         - Gets all public collections
         - Public
    -     /collections/{id}
         - Gets a public collection matching the id if it exists
         - Public
    -     /collections/{collectionId}/add/{comicId}
         - Adds a comic from the marvel api matching {comicId} to the collection matching {collectionId}
         - Currently Public. Will be made private
    -     /collections/{collectionId}/remove/{comicId}
         - Removes the comic matching {comicId} from the collection matching {collectionId}
         - Currently Public. Will be made private
     -     /collections/thumbnail/{collectionId}
         - Gets the url of the first comic in the collection matching {collectionId} as a String
         - Currently Public. Will be made private
    -     /collections/delete/{collectionId}
         - Deletes the collection matching {collectionId} if it exists
         - Currently Public. Will be made private
    -     /collections/create
         - Creates a form from the response body
         - Currently Public. Will be made private
         - Example:     
           *** 
                <form action="http://localhost:8080/collections/create" method="post">
                     <label for="name" >Collection Name:</label><br>
                     <input name="name" type="text" id="nameInput"><br>
                     <label for="isPublic" >Is this collection public?</label><br>
                     <input name="isPublic" type="checkbox" id="isPublicInput"><br>
                     <input type="submit" value="Submit">
                 </form>

             <form action="http://localhost:8080/collections/create" method="post">
                 <label for="name" >Collection Name:</label><br>
                 <input name="name" type="text" id="nameInput"><br>
                 <label for="isPublic" >Is this collection public?</label><br>
                 <input name="isPublic" type="checkbox" id="isPublicInput"><br>
                 <input type="submit" value="Submit">
             </form>

     ***

- ## Authentication
     -     /register
         - Creates an account
         - Public
         - Example: 
           ***    
                <form action="http://localhost:8080/register" method="post" target="_blank">
                    <label for="username" >Username:</label><br>
                    <input name="username" type="text" id="usernameInput" value="Jam"><br>
                    <label for="password" >Password:</label><br>
                    <input name="password" type="text" id="passwordInput" value="yes"><br>
                    <label for="confirmPassword" >Confirm Password:</label><br>
                    <input name="confirmPassword" type="text" id="confirmPasswordInput" value="yes"><br>
                    <label for="role" >Role:</label><br>
                    <input name="role" type="text" id="roleInput" value="ADMIN"><br>
                    <input type="submit" value="Submit">
                </form>

              <form action="http://localhost:8080/register" method="post" target="_blank">
                  <label for="username" >Username:</label><br>
                  <input name="username" type="text" id="usernameInput" value="Jam"><br>
                  <label for="password" >Password:</label><br>
                  <input name="password" type="text" id="passwordInput" value="yes"><br>
                  <label for="confirmPassword" >Confirm Password:</label><br>
                  <input name="confirmPassword" type="text" id="confirmPasswordInput" value="yes"><br>
                  <label for="role" >Role:</label><br>
                  <input name="role" type="text" id="roleInput" value="ADMIN"><br>
                  <input type="submit" value="Submit">
              </form>

           ***

     -     /login
         - Get an authenticated bearer token such as `{"token":"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJKYW0iLCJhdXRoIjoiUk9MRV9BRE1JTiIsImV4cCI6MTYwNzU2OTkxOH0.S8XRbCF0c7vzuV_5kh2XKZMErqXk-qX9TmhzA9fDFHkGi19USM0Oi-uXbEu-NK9_DPA0CN35EIaxsreP0GXpfQ","user":{"id":4,"username":"Jam","authorities":[{"name":"ROLE_ADMIN"}],"friends":null}}`
         - Used when pushing with axios
         - Public
     -     /loginraw
         - Get an authenticated bearer token such as `{"token":"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJKYW0iLCJhdXRoIjoiUk9MRV9BRE1JTiIsImV4cCI6MTYwNzU2OTkxOH0.S8XRbCF0c7vzuV_5kh2XKZMErqXk-qX9TmhzA9fDFHkGi19USM0Oi-uXbEu-NK9_DPA0CN35EIaxsreP0GXpfQ","user":{"id":4,"username":"Jam","authorities":[{"name":"ROLE_ADMIN"}],"friends":null}}`
         - Used when sending raw form data via Vanilla HTML or Postman
         - Public
         - Example:  
           ***
               
                <form action="http://localhost:8080/login" method="post" target="_blank">
                    <label for="username" >Username:</label><br>
                    <input name="username" type="text" id="usernameInput" value="username"><br>
                    <label for="password" >Password:</label><br>
                    <input name="password" type="text" id="passwordInput" value="password"><br>
                    <input type="submit" value="Submit">
                </form> 

              <form action="http://localhost:8080/login" method="post" target="_blank">
                  <label for="username" >Username:</label><br>
                  <input name="username" type="text" id="usernameInput" value="username"><br>
                  <label for="password" >Password:</label><br>
                  <input name="password" type="text" id="passwordInput" value="password"><br>
                  <input type="submit" value="Submit">
               </form>

     ***
- ## Friends
   -     /user/friends
     - Gets the friends of the logged in user
     - Private
   -     /user/friends/add/{id}
     - Sends the user matching {id} a friend request if they exist
     - Private
   -     /user/friends/remove/{id}
     - Removes a user matching {id} from the logged in user's friend list
     - Private
   -     /user/friends/request/incoming
     - Gets a list of pending friend requests to the logged in user
     - Private
   -     /user/friends/request/deny/{requestId}
     - Cancels an incoming request to the logged in user matching {requestId}
     - Private
   -     /user/friends/request/cancel/{requestId}
     - Cancels an outgoing request from the logged in user matching {requestId}
     - Private
   -     /user/friends/request/accept/{requestId}
     - Accepts an incoming request to the logged in user matching {requestId} and adds the request sender as a friend
     - Private