create database gallery;
use gallery;
select * from gallery;
select * from user_gallery;
select * from role;
insert into user(id, name, password, login) values (2, 'Marek', 'password', 'MA');
insert into role(id, name) values (3, 'ROLE_CLIENT');
insert into user_role (user_id, role_id) values (2, 3);