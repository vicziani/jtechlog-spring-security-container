create table JTECHLOG_USER (id bigint generated by default as identity (start with 1), username varchar(255), name varchar(255), primary key (id));
insert into JTECHLOG_USER (id, username, name) values (default, 'admin1', 'John Doe');
insert into JTECHLOG_USER (id, username, name) values (default, 'user1', 'Jane Doe');
