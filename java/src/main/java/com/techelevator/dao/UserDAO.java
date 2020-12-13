package com.techelevator.dao;

import com.techelevator.model.FriendRequest;
import com.techelevator.model.User;

import java.util.List;

public interface UserDAO {

    List<User> findAll();

    User getUserById(int userId);

    User findByUsername(String username);

    int findIdByUsername(String username);

    boolean create(String username, String password, String role);

	List<User> getFriendsByUserId(int userId);

	boolean friendExists(int userId, int friendId);

	boolean userExists(int id);

	void sendFriendRequest(int userId, int friendId);

	List<FriendRequest> getOutGoingRequests(int userId);

	List<FriendRequest> getIncomingRequests(int userId);

	void changeRequestStatusSender(int senderId, int requestId, int status);
	
	void changeRequestStatusRecipient(int recipientId, int requestId, int status);

	boolean friendRequestExists(int userId, int friendId);

	void addFriend(int requestId, int recipientId);

	void removeFriend(int friendId, int userId);

	String getUsername(int id);

	List<User> getUsers(String name, int number, int page);
}
