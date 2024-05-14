-- Remember to create the Role and insert data into it before running the website!
create database newspaperonl;
use newspaperonl;
CREATE TABLE Role (
    role_id INT PRIMARY KEY auto_increment,
    description VARCHAR(255)
);
drop database newspaperonl;

select * from article;
select * from user;
select * from role;
select * from category;
select * from image;
select * from tag;

-- Force drop table (Replace Role with other tables's name)
SET foreign_key_checks = 0;
drop table user;


UPDATE user SET role_id=5 where user_id=2;
INSERT INTO Role (description) VALUES
('NOT ACTIVATED'),
('USER'),
('SUBSCRIBER'),
('WRITER'),
('EDITOR'),
('ADMIN');

INSERT INTO Tag (tag_id, name) VALUES (1, 'Thể thao');
INSERT INTO Tag (tag_id, name) VALUES (2, 'Chính trị');
INSERT INTO Tag (tag_id, name) VALUES (3, 'Giáo dục');
INSERT INTO Tag (tag_id, name) VALUES (4, 'Đời sống');
INSERT INTO Tag (tag_id, name) VALUES (5, 'Khoa học');


INSERT INTO Category (category_id, description, user_id) VALUES (1, "Thể thao", 1);
INSERT INTO Category (category_id, description, user_id) VALUES (2, "Chính trị", 1);
INSERT INTO Category (category_id, description, user_id) VALUES (3, "Giáo dục", 1);
INSERT INTO Category (category_id, description, user_id) VALUES (4, "Khoa học", 1);







