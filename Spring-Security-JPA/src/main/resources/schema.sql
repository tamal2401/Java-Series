drop table if EXISTS user;

create table user (
    id INTEGER(10) NOT NULL AUTO_INCREMENT,
    user VARCHAR(128) NOT NULL,
    password VARCHAR(128) NOT NULL,
    active BOOLEAN,
    roles VARCHAR(128) NOT NULL,
    PRIMARY KEY (id)
);