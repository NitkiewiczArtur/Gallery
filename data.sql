use gallery;
insert into user(id, name, surname, login, password) values (1, 'Arkadiusz', 'Lizak' ,'arli', 'password');
insert into user(id, name, surname, login, password) values (2, 'Marek', 'Markoski', 'mama', 'password');
insert into user(id, name, surname, login, password) values (3, 'Agata', 'Dyrka', 'agdy', 'password');

insert into role(id, name) values (1, 'ROLE_USER');
insert into role(id, name) values (2, 'ROLE_PHOTOGRAPHER');
insert into role(id, name) values (3, 'ROLE_CLIENT');

insert into user_role (user_id, role_id) values (1, 1);
insert into user_role (user_id, role_id) values (1, 2);
insert into user_role (user_id, role_id) values (2, 1);
insert into user_role (user_id, role_id) values (2, 3);
insert into user_role (user_id, role_id) values (3, 1);
insert into user_role (user_id, role_id) values (3, 3);