package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.techelevator.model.marvel.fields.Field;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class User extends Field
{

   private int id;
   private String username;
   private String description;
   private boolean isOnline;
   @JsonIgnore
   private String password;
   @JsonIgnore
   private boolean activated;
   private Set<Authority> authorities = new HashSet<>();
   private List<Long> friends;

   public User() { }

   public User(int id, String username, String password, String authorities, List<Long> friends) {
      this.id = id;
      this.username = username;
      this.password = password;
      this.activated = true;
      this.friends = friends;
   }
   
   

   public String getDescription() {
	return description;
   }

   public void setDescription(String description) {
		this.description = description;
	}

	public boolean isOnline() {
		return isOnline;
	}

	public void setIsOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}

	public int getId() {
      return id;
	}

   public void setId(int id) {
      this.id = id;
   }

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public boolean isActivated() {
      return activated;
   }

   public void setActivated(boolean activated) {
      this.activated = activated;
   }

   public Set<Authority> getAuthorities() {
      return authorities;
   }

   public void setAuthorities(Set<Authority> authorities) {
      this.authorities = authorities;
   }

   public void setAuthorities(String authorities) {
      String[] roles = authorities.split(",");
      for(String role : roles) {
         String authority = role.contains("ROLE_") ? role : "ROLE_" + role;
         this.authorities.add(new Authority(authority));
      }
   }
   
   public List<Long> getFriends() {
	   return friends;
   }
   
   public void setFriends(List<Long> friends) {
	   this.friends = friends;
   }
   
   public User addFriends(List<Long> friends)
   {
	   this.friends.addAll(friends);
	   return this;
   }
   
   public User addFriends(long friend)
   {
	   this.friends.add(friend);
	   return this;
   }
   
   public User removeComics(List<Long> friends)
	{
		for(int i = 0; i < this.friends.size(); i++)
			if(friends.contains(this.friends.get(i)))
				this.friends.remove(i--);
		return this;
	}
   
   public User removeComics(long friend)
   {
	   	try 
	   	{
		   friends.remove(friends.indexOf(friend));
	   	} 
	   	catch (Exception e) 
	   	{
			System.out.println("Could not remove friend " + friend);
	   	}

		return this;
	}
  

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      User user = (User) o;
      return id == user.id &&
              activated == user.activated &&
              Objects.equals(username, user.username) &&
              Objects.equals(password, user.password) &&
              Objects.equals(authorities, user.authorities);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, username, password, activated, authorities);
   }

   @Override
   public String toString() {
      return "User{" +
              "id=" + id +
              ", username='" + username + '\'' +
              ", activated=" + activated +
              ", authorities=" + authorities +
              '}';
   }
}
