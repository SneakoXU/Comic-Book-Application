package com.techelevator.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.helpers.SubstituteLogger;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.vote.ConsensusBased;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.techelevator.dao.UserDAO;
import com.techelevator.model.FriendRequest;
import com.techelevator.model.User;
import com.techelevator.model.marvel.fields.Container;
import com.techelevator.model.marvel.fields.DataWrapper;
import com.techelevator.model.marvel.fields.Field;

@CrossOrigin
@RestController
@RequestMapping("/user")
@PreAuthorize("isAuthenticated()")
public class UserController 
{
	private UserDAO userDAO;
	
	public UserController(UserDAO userDAO)
	{
		this.userDAO = userDAO;
	}
	
	@PreAuthorize("permitAll()")
	@RequestMapping(value="/{name}/{number}/{page}", method = RequestMethod.GET)
    public DataWrapper getUsersByName(@PathVariable String name, @PathVariable int number, @PathVariable int page, Principal principal) 
	{
		List<User> users = userDAO.getUsers(name, number, page);
        return new DataWrapper().setData(new Container().setResultsList(users).setCount(users.size()).setTotal(userDAO.getUsers("", Integer.MAX_VALUE, 0).size()));
    }
	
	@PreAuthorize("permitAll()")
	@RequestMapping(value="/subscribed/{id}", method = RequestMethod.GET)
    public boolean isSubscribed(@PathVariable int id, Principal principal) 
	{
        return userDAO.isSubscribed(userDAO.findIdByUsername(principal.getName()), id);
    }
	
	@PreAuthorize("permitAll()")
	@RequestMapping(value="/{number}/{page}", method = RequestMethod.GET)
    public DataWrapper getUsers(@PathVariable int number, @PathVariable int page, Principal principal) 
	{
		List<User> users = userDAO.getUsers("", number, page);
        return new DataWrapper().setData(new Container().setResultsList(users).setCount(users.size()).setTotal(userDAO.getUsers("", Integer.MAX_VALUE, 0).size()));
    }
	
	@PreAuthorize("permitAll()")
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable int id, Principal principal) 
	{
        return userDAO.getUserById(id);
    }
	
	@PreAuthorize("permitAll()")
	@RequestMapping(value="/get/id", method = RequestMethod.GET)
    public int getUserId(Principal principal) 
	{
		System.out.println(userDAO.findIdByUsername(principal.getName()));
		if(principal != null)
			return userDAO.findIdByUsername(principal.getName());
		return -1;
    }
	
	@PreAuthorize("permitAll()")
	@RequestMapping(value="/get/id/{name}", method = RequestMethod.GET)
    public int getUserIdByName(@PathVariable String name, Principal principal) 
	{
		return userDAO.findIdByUsername(name);
    }
	
	@CrossOrigin
    @PreAuthorize("permitAll()")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(Principal principal)
    { 
    	if(principal != null)
    	{
    		System.out.println("Logging out user " + principal.getName());
    		userDAO.setIsOnline(userDAO.findIdByUsername(principal.getName()), false);
    		return "Logged out";
    	}
    	return "You must be logged in to log out";
    }
	
	@RequestMapping(value="/edit/description", method = RequestMethod.POST)
	public void editDescription(@RequestBody String description, Principal principal)
	{
		
		description = description.substring(0,description.length()-1);
		
		for(Entry<String,String> entry : MarvelAPIController.ILLEGAL_CHARACTERS.entrySet())
		{
			description = MarvelAPIController.replace(description, entry.getValue(), entry.getKey());
			System.out.println(description + " " + entry.getValue());
		}
		description = MarvelAPIController.replace(description, "%25", "%");
		
		
		System.out.println("Changing " + principal.getName() + "'s description to " + description);
        userDAO.setDescription(userDAO.findIdByUsername(principal.getName()), description);
    }
	
	@RequestMapping(value="/edit/name", method = RequestMethod.POST)
	public boolean editName(@RequestBody String name, Principal principal)
	{
		name = name.substring(0,name.length()-1);
		System.out.println("Changing " + principal.getName() + "'s name to " + name);
        return userDAO.setName(userDAO.findIdByUsername(principal.getName()), name);
    }
	
	@CrossOrigin
	@RequestMapping(value="/friends", method = RequestMethod.GET)
	public List<User> getFriends(Principal principal)
	{
		System.out.println("Gettting " + principal.getName() + "'s friends");
        return userDAO.getFriendsByUserId(userDAO.findIdByUsername(principal.getName()));
    }
	
	@RequestMapping(value="/friends/add/{id}", method = RequestMethod.POST)
	public void addFriend(@PathVariable int id, Principal principal) 
	{
		System.out.println("Recieved request from " + principal.getName() + " to add " + userDAO.getUsername(id));
		int pid = userDAO.findIdByUsername(principal.getName());
		if(id == pid)
			throw new ResponseStatusException(
      	          HttpStatus.FORBIDDEN, "You cannot send yourself a friend request");
		for(User u : userDAO.getFriendsByUserId(pid))
			if(u.getId() == id)
				throw new ResponseStatusException(
		      	          HttpStatus.FORBIDDEN, userDAO.getUsername(pid) + " is already your friend");
		if(!userDAO.userExists(pid))
		{
			throw new ResponseStatusException(
	      	          HttpStatus.NOT_FOUND, "user does not exist");
		}
		System.out.println("Creating friend request");
        userDAO.sendFriendRequest(userDAO.findIdByUsername(principal.getName()), id);
    }
	
	@RequestMapping(value="/friends/remove/{id}", method = RequestMethod.POST)
	public void removeFriend(@PathVariable int id, Principal principal) 
	{
		System.out.println("Removing " + userDAO.getUsername(id) + " from " + principal.getName() + "'s friend list");
        userDAO.removeFriend(id, userDAO.findIdByUsername(principal.getName()));
    }
	
	@RequestMapping(value="/friends/request/incoming", method = RequestMethod.GET)
	public List<FriendRequest> getIncomingRequests(Principal principal) 
	{
        return userDAO.getIncomingRequests(userDAO.findIdByUsername(principal.getName()));
    }
	
	@RequestMapping(value="/friends/request/outgoing", method = RequestMethod.GET)
	public List<FriendRequest> getOutgoingRequests(Principal principal) 
	{
        return userDAO.getOutGoingRequests(userDAO.findIdByUsername(principal.getName()));
    }
	
	@RequestMapping(value="/friends/request/cancel/{id}", method = RequestMethod.POST)
	public void cancelRequest(@PathVariable int id, Principal principal) 
	{
        userDAO.changeRequestStatusSender(userDAO.findIdByUsername(principal.getName()), id, FriendRequest.CANCELED);
    }
	
	@RequestMapping(value="/friends/request/deny/{id}", method = RequestMethod.POST)
	public void denyRequest(@PathVariable int id, Principal principal) 
	{
        userDAO.changeRequestStatusRecipient(userDAO.findIdByUsername(principal.getName()), id, FriendRequest.DENIED);
    }
	
	@RequestMapping(value="/friends/request/accept/{id}", method = RequestMethod.POST)
	public void acceptRequest(@PathVariable int id, Principal principal) 
	{
        userDAO.addFriend(id, userDAO.findIdByUsername(principal.getName()));
    }
	
	@RequestMapping(value="/friends/request/acknowledge/{id}", method = RequestMethod.POST)
	public void acknowledgeRequest(@PathVariable int id, Principal principal) 
	{
		userDAO.changeRequestStatusSender(userDAO.findIdByUsername(principal.getName()), id, FriendRequest.ACKNOWLEGED);
    }

}
