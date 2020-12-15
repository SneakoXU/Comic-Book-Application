package com.techelevator.model;

public class FriendRequest 
{

	public static final int PENDING = 0;
	public static final int ACCEPTED = 1;
	public static final int DENIED = 2;
	public static final int CANCELED = 3;
	public static final int ACKNOWLEGED = 4;
	
	private int id;
	private User sender;
	private User recipient;
	private int status;
	
	public FriendRequest() {};
	
	public FriendRequest(int id, User sender, User recipient, int status) {
		super();
		this.id = id;
		this.sender = sender;
		this.recipient = recipient;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getRecipient() {
		return recipient;
	}

	public void setRecipient(User recipient) {
		this.recipient = recipient;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
	
	
}
