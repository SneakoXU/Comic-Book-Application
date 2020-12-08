# Java Server Documentation
for yall doing the client <3

## Paths
- ## Comics 
    -     /comics/query/title/{name}
         - Makes request to Marvel API to get the first 100 comics that begin with {name}
         - Public
  
    -     /comics/query/id/{id}
         - Makes request to Marvel API to get a comic with the matching {id}.
         - Public
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
    -     /collections/delete/{collectionId}
         - Deletes the collection matching {collectionId} if it exists
         - Currently Public. Will be made private
    -     /collections/create
         - Creates a form from the response body
         - Currently Public. Will be made private
         - Example:     
           -     <form action="http://localhost:8080/collections/create" method="post">
                     <label for="name" >Collection Name:</label><br>
                     <input name="name" type="text" id="nameInput"><br>
                     <label for="isPublic" >Is this collection public?</label><br>
                     <input name="isPublic" type="checkbox" id="isPublicInput"><br>
                     <input type="submit" value="Submit">
                 </form>