use gallery;
insert into user(id, name, surname, login, password) values (1, 'Arkadiusz', 'Lizak' ,'arli', '$2a$10$5HLFGfpJa59WwVK1cKsRveGOmPJFj4VradQSvv7JDROrKl15ePkwq');
insert into user(id, name, surname, login, password) values (2, 'Marek', 'Markoski', 'mama', '$2a$10$WmI0CnKZCpJz8xjVHMfn7eETgu.EBAbFdD.YIv6s.IiHVjWHJ/Q2G');
insert into user(id, name, surname, login, password) values (3, 'Agata', 'Dyrka', 'agdy', '$2a$10$ahIbLlvHS2fH6SaLNIfPFeIgxz6iHG4z7LL75teat1bTUG9bVq/6W');

insert into role(id, name) values (1, 'ROLE_USER');
insert into role(id, name) values (2, 'ROLE_PHOTOGRAPHER');
insert into role(id, name) values (3, 'ROLE_CLIENT');

insert into user_role (user_id, role_id) values (1, 1);
insert into user_role (user_id, role_id) values (1, 2);
insert into user_role (user_id, role_id) values (2, 1);
insert into user_role (user_id, role_id) values (2, 3);
insert into user_role (user_id, role_id) values (3, 1);
insert into user_role (user_id, role_id) values (3, 3);