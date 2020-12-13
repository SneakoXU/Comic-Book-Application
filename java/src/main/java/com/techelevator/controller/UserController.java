package com.techelevator.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.techelevator.dao.UserDAO;
import com.techelevator.model.FriendRequest;
import com.techelevator.model.User;

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
    public List<User> getUsers(@PathVariable String name, @PathVariable int number, @PathVariable int page, Principal principal) 
	{
        return userDAO.getUsers(name, number, page);
    }
	
	@PreAuthorize("permitAll()")
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable int id, Principal principal) 
	{
        return userDAO.getUserById(id);
    }
	
	@RequestMapping(value="/friends", method = RequestMethod.GET)
	public List<User> getFriends(Principal principal)
	{
        return userDAO.getFriendsByUserId(userDAO.findIdByUsername(principal.getName()));
    }
	
	@RequestMapping(value="/friends/add/{id}", method = RequestMethod.POST)
	public void addFriend(@PathVariable int id, Principal principal) 
	{
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
			System.out.println("yesyesyes");
			throw new ResponseStatusException(
	      	          HttpStatus.NOT_FOUND, "user does not exist");
		}
        userDAO.sendFriendRequest(userDAO.findIdByUsername(principal.getName()), id);
    }
	
	@RequestMapping(value="/friends/remove/{id}", method = RequestMethod.POST)
	public void removeFriend(@PathVariable int id, Principal principal) 
	{
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

}
