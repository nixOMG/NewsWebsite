-- Remember to create the Role and insert data into it before running the website!
create database newspaperonl;
use newspaperonl;
CREATE TABLE Role (
    role_id INT PRIMARY KEY auto_increment,
    description VARCHAR(255)
);

UPDATE user SET role_id=2 where user_id=1;
INSERT INTO Role (description) VALUES
('NOT ACTIVATED'),
('USER'),
('SUBSCRIBER'),
('WRITER'),
('EDITOR'),
('ADMIN');

select * from user;
select * from role;

-- Force drop table (Replace Role with other tables's name)
SET foreign_key_checks = 0;
drop table Role;





