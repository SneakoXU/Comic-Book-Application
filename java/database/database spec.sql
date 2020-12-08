BEGIN TRANSACTION;

CREATE TABLE comic
(
        comic_id serial,
        title varchar(100),
        description varchar(1000),
        thumbnail_url varchar(70) not null,
        
        constraint pk_comic primary key(comic_id)
        
);

CREATE TABLE character
(
        character_id serial,
        character_name varchar(50) not null,
        description varchar(1000),
        thumbnail_url varchar(70) not null,
        
        constraint pk_character primary key(character_id)
);

CREATE TABLE story
(
        story_id serial,
        title varchar(100),
        description varchar(1000),
        thumbnail_url varchar(70) not null,
        
        constraint pk_story primary key(story_id)     
);

CREATE TABLE series
(
        series_id serial,
        title varchar(100),
        description varchar(1000),
        thumbnail_url varchar(70) not null,
        
        constraint pk_series primary key(series_id)
);

CREATE TABLE author
(
       author_id serial,
       firstName varchar(25),
       lastName varchar(25),
       description varchar(1000),
       thumbnail_url varchar(70),
       
       constraint pk_author primary key(author_id)                 
);

CREATE TABLE comic_character
(
        comic_id int,
        character_id int,
        
        constraint fk_comic_character_comic foreign key(comic_id) references comic(comic_id),
        constraint fk_comic_character_character foreign key(character_id) references character(character_id)
);

CREATE TABLE comic_author
(
        comic_id int,
        author_id int,
        
        constraint fk_comic_creator_comic foreign key(comic_id) references comic(comic_id),
        constraint fk_comic_creator_author foreign key(author_id) references author(author_id)
);

CREATE TABLE comic_story
(
        comic_id int,
        story_id int,
        
        constraint fk_comic_story_comic foreign key(comic_id) references comic(comic_id),
        constraint fk_comic_story_story foreign key(story_id) references story(story_id)
);

CREATE TABLE comic_series
(
        comic_id int,
        series_id int,
        
        constraint fk_comic_series_comic foreign key(comic_id) references comic(comic_id),
        constraint fk_comic_series_series foreign key(series_id) references series(series_id)
);

CREATE TABLE author_character
(
        author_id int,
        character_id int,
        
        constraint fk_creator_character_author foreign key(author_id) references author(author_id),
        constraint fk_creator_character_character foreign key(character_id) references character(character_id)
);

CREATE TABLE author_story
(
        author_id int,
        story_id int,
        
        constraint fk_author_story_author foreign key(author_id) references author(author_id),
        constraint fk_author_story_story foreign key(story_id) references story(story_id)
);

CREATE TABLE author_series
(
        author_id int,
        series_id int,
        
        constraint fk_author_series_author foreign key(author_id) references author(author_id),
        constraint fk_author_series_series foreign key(series_id) references series(series_id)
);

CREATE TABLE story_character
(
        story_id int,
        character_id int,
        
        constraint fk_story_character_story foreign key(story_id) references story(story_id),
        constraint fk_story_character_character foreign key(character_id) references character(character_id)
);

CREATE TABLE story_series
(
        story_id int,
        series_id int,
        
        constraint fk_story_series_story foreign key(story_id) references story(story_id),
        constraint fk_story_series_series foreign key(series_id) references series(series_id)
);

CREATE TABLE series_character
(
        series_id int,
        character_id int,
        
        constraint fk_series_character_series foreign key(series_id) references series(series_id),
        constraint fk_series_character_character foreign key(character_id) references character(character_id)
);

CREATE TABLE collection_comic
(
        collection_id int,
        comic_id int,
        
        constraint fk_collection_comic_collection foreign key(collection_id) references collections(collection_id),
        constraint fk_collection_comic_comic foreign key(comic_id) references comic(comic_id)
);


COMMIT;