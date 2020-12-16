BEGIN TRANSACTION;


CREATE SEQUENCE IF NOT EXISTS seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;


CREATE TABLE IF NOT EXISTS users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	online_status boolean,
	description varchar (280),
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE IF NOT EXISTS collections
(
        collection_id serial,
        collectionName varchar(100) not null,
        creator_id int not null,
        publicStatus boolean not null,
        dateCreated date not null,
        
        constraint pk_collections primary key (collection_id),
        constraint fk_collections_users foreign key (creator_id) references users (user_id)
);

CREATE TABLE IF NOT EXISTS comicbook_collections
(
        collection_id int,
        receiving_user int,
        
        constraint fk_comicbook_collections_collections foreign key (collection_id) references collections (collection_id),
        constraint fk_comicbook_collections_users foreign key (receiving_user) references users (user_id)
);

CREATE TABLE IF NOT EXISTS comic
(
        comic_id serial,
        title varchar(100),
        description varchar(1000),
        thumbnail_url varchar(70) not null,
        
        constraint pk_comic primary key(comic_id)
        
);

CREATE TABLE IF NOT EXISTS character
(
        character_id serial,
        character_name varchar(50) not null,
        description varchar(1000),
        thumbnail_url varchar(70) not null,
        
        constraint pk_character primary key(character_id)
);

CREATE TABLE IF NOT EXISTS story
(
        story_id serial,
        title varchar(100),
        description varchar(1000),
        thumbnail_url varchar(70) not null,
        
        constraint pk_story primary key(story_id)     
);

CREATE TABLE IF NOT EXISTS series
(
        series_id serial,
        title varchar(100),
        description varchar(1000),
        thumbnail_url varchar(70) not null,
        
        constraint pk_series primary key(series_id)
);

CREATE TABLE IF NOT EXISTS author
(
       author_id serial,
       firstName varchar(25),
       lastName varchar(25),
       description varchar(1000),
       thumbnail_url varchar(70),
       
       constraint pk_author primary key(author_id)                 
);

CREATE TABLE IF NOT EXISTS comic_character
(
        comic_id int,
        character_id int,
        
        constraint fk_comic_character_comic foreign key(comic_id) references comic(comic_id),
        constraint fk_comic_character_character foreign key(character_id) references character(character_id)
);

CREATE TABLE IF NOT EXISTS comic_author
(
        comic_id int,
        author_id int,
        
        constraint fk_comic_creator_comic foreign key(comic_id) references comic(comic_id),
        constraint fk_comic_creator_author foreign key(author_id) references author(author_id)
);

CREATE TABLE IF NOT EXISTS comic_story
(
        comic_id int,
        story_id int,
        
        constraint fk_comic_story_comic foreign key(comic_id) references comic(comic_id),
        constraint fk_comic_story_story foreign key(story_id) references story(story_id)
);

CREATE TABLE IF NOT EXISTS comic_series
(
        comic_id int,
        series_id int,
        
        constraint fk_comic_series_comic foreign key(comic_id) references comic(comic_id),
        constraint fk_comic_series_series foreign key(series_id) references series(series_id)
);

CREATE TABLE IF NOT EXISTS author_character
(
        author_id int,
        character_id int,
        
        constraint fk_creator_character_author foreign key(author_id) references author(author_id),
        constraint fk_creator_character_character foreign key(character_id) references character(character_id)
);

CREATE TABLE IF NOT EXISTS author_story
(
        author_id int,
        story_id int,
        
        constraint fk_author_story_author foreign key(author_id) references author(author_id),
        constraint fk_author_story_story foreign key(story_id) references story(story_id)
);

CREATE TABLE IF NOT EXISTS author_series
(
        author_id int,
        series_id int,
        
        constraint fk_author_series_author foreign key(author_id) references author(author_id),
        constraint fk_author_series_series foreign key(series_id) references series(series_id)
);

CREATE TABLE IF NOT EXISTS story_character
(
        story_id int,
        character_id int,
        
        constraint fk_story_character_story foreign key(story_id) references story(story_id),
        constraint fk_story_character_character foreign key(character_id) references character(character_id)
);

CREATE TABLE IF NOT EXISTS story_series
(
        story_id int,
        series_id int,
        
        constraint fk_story_series_story foreign key(story_id) references story(story_id),
        constraint fk_story_series_series foreign key(series_id) references series(series_id)
);

CREATE TABLE IF NOT EXISTS series_character
(
        series_id int,
        character_id int,
        
        constraint fk_series_character_series foreign key(series_id) references series(series_id),
        constraint fk_series_character_character foreign key(character_id) references character(character_id)
);

CREATE TABLE IF NOT EXISTS collection_comic
(
        collection_id int,
        comic_id int,
        
        constraint fk_collection_comic_collection foreign key(collection_id) references collections(collection_id),
        constraint fk_collection_comic_comic foreign key(comic_id) references comic(comic_id)
);

CREATE TABLE IF NOT EXISTS user_friend
(
        user_id int,
        friend_id int,
        
        constraint fk_user_friend_comic foreign key(user_id) references users(user_id),
        constraint fk_user_friend_friend foreign key(user_id) references users(user_id)
);

CREATE TABLE IF NOT EXISTS requeststatus
(
        status_id int,
        status varchar(8),
	
	constraint pk_requeststatus primary key(status_id)
);



CREATE TABLE IF NOT EXISTS friendrequest
(
        request_id serial,
        recipient_id int,
        sender_id int,
        status_id int,
    	    
        constraint pk_friendrequest primary key(request_id),
	constraint fk_friendrequest_recipient foreign key(recipient_id) references users(user_id),
	constraint fk_friendrequest_sender foreign key(sender_id) references users(user_id),
	constraint fk_friendrequest_status foreign key(status_id) references requeststatus(status_id)
);
	
CREATE TABLE IF NOT EXISTS comment
(
    comment_id serial,
    commenter_id int,
	collection_id int,
    likes int,
    text varchar(140) ,
    	    
    constraint pk_comment primary key(comment_id),
	constraint fk_comment_commenter foreign key(commenter_id) references users(user_id),
	constraint fk_comment_collection foreign key(collection_id) references collections(collection_id)
);

CREATE TABLE IF NOT EXISTS subscription
(
        user_id int,
	collection_id int,
    	    
	constraint fk_subscription_subscriber foreign key(user_id) references users(user_id),
	constraint fk_subscription_collection foreign key(collection_id) references collections(collection_id)
);

COMMIT;