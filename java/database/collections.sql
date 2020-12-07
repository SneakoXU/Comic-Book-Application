BEGIN TRANSACTION;

CREATE TABLE collections
(
        collection_id serial,
        collectionName varchar(100) not null,
        creator_id int not null,
        publicStatus boolean not null,
        dateCreated date not null,
        
        constraint pk_collections primary key (collection_id),
        constraint fk_collections_users foreign key (creator_id) references users (user_id)
);

CREATE TABLE comicbook_collections
(
        collection_id int,
        receiving_user int,
        
        constraint fk_comicbook_collections_collections foreign key (collection_id) references collections (collection_id),
        constraint fk_comicbook_collections_users foreign key (receiving_user) references users (user_id)
);

COMMIT TRANSACTION;