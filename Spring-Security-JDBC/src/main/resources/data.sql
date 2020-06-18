insert  into  users(username, password, enabled) values ('tamal', 'test', true)
insert  into  users(username, password, enabled) values ('admin', 'admin', true)

insert into authorities(username, authority) values ('tamal', 'user')
insert into authorities(username, authority) values ('admin', 'user')
insert into authorities(username, authority) values ('admin', 'admin')