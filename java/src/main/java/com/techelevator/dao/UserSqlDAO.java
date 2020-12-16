package com.techelevator.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javax.management.openmbean.ArrayType;
import javax.xml.ws.http.HTTPException;

import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.techelevator.model.FriendRequest;
import com.techelevator.model.User;

@Service
public class UserSqlDAO implements UserDAO {

    private JdbcTemplate jdbcTemplate;

    public UserSqlDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int findIdByUsername(String username) {
    	try 
    	{
    		return jdbcTemplate.queryForObject("select user_id from users where username = ?", int.class, username);
		} 
    	catch (Exception e) 
    	{
			return -1;
		}
        
    }
    
    @Override
    public List<User> getUsers(String name, int number, int page)
    {
    	List<User> users = new ArrayList<>();
    	String sql = "select * from users where username ilike ? limit ? offset ?;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, "%" + name + "%", number, page*number);
		while(results.next()) {
			users.add (mapRowToUserPublic(results));
		}
		return users;
    }

	@Override
	public User getUserById(int userId) {
		String sql = "SELECT * FROM users WHERE user_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
		if(results.next()) {
			return mapRowToUserPublic(results);
		} else {
			throw new RuntimeException("userId "+userId+" was not found.");
		}
	}
	
	@Override
	public void setIsOnline(int id, boolean isOnline)
	{
		System.out.println("[BACKEND] setting user with id " + id + " to status " + isOnline);
		String sql = "update users set online_status = ? where user_id = ?;";
		jdbcTemplate.update(sql, isOnline, id);
	}
	
	@Override
	public boolean userExists(int id)
	{
		String sql = "SELECT user_id FROM users WHERE user_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
		return results.next();
	}
	
	@Override
	public List<User> getFriendsByUserId(int userId)
	{
		List<User> friends = new ArrayList<>();
		String sql = "select * from users as u inner join user_friend as uf on uf.user_id = u.user_id where uf.friend_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
		while(results.next()) 
			friends.add(mapRowToUserPublic(results));
		sql = "select * from users as u inner join user_friend as uf on uf.friend_id = u.user_id where uf.user_id = ?";
		results = jdbcTemplate.queryForRowSet(sql, userId);
		while(results.next()) 
			friends.add(mapRowToUserPublic(results));
		return friends;
	}
	
	@Override
	public void sendFriendRequest(int userId, int friendId)
	{
		if(!friendExists(userId, friendId) && userExists(friendId) && !friendRequestExists(userId, friendId))
		{
			String sql = "insert into friendrequest (recipient_id, sender_id, status_id) values (?, ?, 0);";
			jdbcTemplate.update(sql, friendId, userId);
		}
	}
	
	@Override
	public List<FriendRequest> getOutGoingRequests(int userId)
	{
		List<FriendRequest> requests = new ArrayList<FriendRequest>();
		
		String sql = "SELECT * FROM friendrequest WHERE sender_id = ? and status_id in (0, 1, 2)";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
		while(results.next()) 
			requests.add(mapRowToFriendRequest(results));
		return requests;
	}
	
	@Override
	public List<FriendRequest> getIncomingRequests(int userId)
	{
		List<FriendRequest> requests = new ArrayList<FriendRequest>();
		
		String sql = "SELECT * FROM friendrequest WHERE recipient_id = ? and status_id = 0";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
		while(results.next()) 
			requests.add(mapRowToFriendRequest(results));
		return requests;
	}
	
	@Override
	public boolean friendExists(int userId, int friendId)
	{
		String sql = "SELECT user_id FROM user_friend WHERE (user_id = ? and friend_id = ?) or (friend_id = ? and user_id = ?)";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId, friendId, userId, friendId);
		return results.next();
	}
	
	@Override
	public boolean friendRequestExists(int userId, int friendId)
	{
		String sql = "SELECT sender_id FROM friendrequest WHERE sender_id = ? and recipient_id = ? and status_id in (0)";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId, friendId);
		return results.next();
	}
	
	@Override
	public void changeRequestStatusSender(int senderId, int requestId, int status)
	{
		String sql = "update friendrequest set status_id = ? where request_id = ? and sender_id = ?";
		jdbcTemplate.update(sql, status, requestId, senderId);
	}
	
	@Override
	public void changeRequestStatusRecipient(int recipientId, int requestId, int status)
	{
		String sql = "update friendrequest set status_id = ? where request_id = ? and recipient_id = ?";
		jdbcTemplate.update(sql, status, requestId, recipientId);
	}
	
	@Override
	public void setDescription(int id, String desc)
	{
		String sql = "update users set description = ? where user_id = ?";
		jdbcTemplate.update(sql, desc, id);
	}
	
	@Override
	public boolean setName(int id, String name)
	{
		name = name.toLowerCase();
		if(getUsername(id).equals(name) || !userExists(findIdByUsername(name)))
		{
			System.out.println("Setting name...");
			String sql = "update users set username = ? where user_id = ?";
			jdbcTemplate.update(sql, name, id);
			return true;
		}
		else 
		{
			System.out.println("Tried to set name to user that already exists");
			return false;
		}
		
	}
	
	@Override
	public void subscribe(int id, int collectionId)
	{
		if(!isSubscribed(id, collectionId))
		{
			String sql = "insert into subscription (user_id, collection_id) values (?, ?)";
			jdbcTemplate.update(sql, id, collectionId);
		}
		else 
		{
			System.out.println("Already subscribed");
		}
	}
	
	@Override
	public void unSubscribe(int id, int collectionId)
	{
		if(isSubscribed(id, collectionId))
		{
			String sql = "delete from subscription where user_id = ? and collection_id = ?";
			jdbcTemplate.update(sql, id, collectionId);
		}
		else 
		{
			System.out.println("Not subscribed");
		}
	}
	
	@Override
	public boolean isSubscribed(int id, int collectionId)
	{
		String sql = "select user_id from subscription where user_id = ? and collection_id = ?";
		return jdbcTemplate.queryForRowSet(sql, id, collectionId).next();
	}
	
	@Override
	public void addFriend(int requestId, int recipientId) {
		String sql = "insert into user_friend (user_id, friend_id) select sender_id, recipient_id from friendrequest where request_id = ? and recipient_id = ?";
		jdbcTemplate.update(sql, requestId, recipientId);
		sql = "update friendrequest set status_id = 1 where request_id = ? and recipient_id = ?";
		jdbcTemplate.update(sql, requestId, recipientId);
	}
	
	@Override
	public void removeFriend(int friendId, int userId) {
		String sql = "delete from user_friend where (user_id = ? and friend_id = ?) or (friend_id = ? and user_id = ?)";
		jdbcTemplate.update(sql, userId, friendId, userId, friendId);
	}

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "select * from users";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            User user = mapRowToUser(results);
            users.add(user);
        }

        return users;
    }

    @Override
    public User findByUsername(String username) throws UsernameNotFoundException {
        for (User user : this.findAll()) {
            if( user.getUsername().toLowerCase().equals(username.toLowerCase())) {
                return user;
            }
        }
        throw new UsernameNotFoundException("User " + username + " was not found.");
    }

    @Override
    public boolean create(String username, String password, String role) {
        boolean userCreated = false;

        // create user
        String insertUser = "insert into users (username,password_hash,role) values(?,?,?)";
        String password_hash = new BCryptPasswordEncoder().encode(password);
        String ssRole = "ROLE_" + role.toUpperCase();

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        String id_column = "user_id";
        userCreated = jdbcTemplate.update(con -> {
                    PreparedStatement ps = con.prepareStatement(insertUser, new String[]{id_column});
                    ps.setString(1, username);
                    ps.setString(2, password_hash);
                    ps.setString(3, ssRole);
                    return ps;
                }
                , keyHolder) == 1;
        int newUserId = (int) keyHolder.getKeys().get(id_column);

        return userCreated;
    }
    
    @Override
    public String getUsername(int id)
    {
    	String sql = "SELECT username FROM users WHERE user_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
		if(results.next())
				return results.getString("username");
		return "";
    }

    private User mapRowToUser(SqlRowSet rs) {
        User user = new User();
        user.setId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password_hash"));
        user.setAuthorities(rs.getString("role"));
        user.setActivated(true);
        user.setIsOnline(rs.getBoolean("online_status"));
        user.setDescription(rs.getString("description"));
        return user;
    }
    
    private User mapRowToUserPublic(SqlRowSet rs) {
        User user = new User();
        user.setId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setIsOnline(rs.getBoolean("online_status"));
        user.setDescription(rs.getString("description"));
        return user;
    }
    
    private FriendRequest mapRowToFriendRequest(SqlRowSet rs) {
    	return new FriendRequest(rs.getInt("request_id"), getUserById(rs.getInt("sender_id")), getUserById(rs.getInt("recipient_id")), rs.getInt("status_id"));
    	
    }
}
