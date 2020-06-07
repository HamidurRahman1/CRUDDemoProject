
drop table customers if exists;
drop table users_roles if exists;
drop table users if exists;
drop table roles if exists;

create table customers
(
  customer_id int primary key auto_increment,
  first_name varchar(30) not null,
  last_name varchar(30) null,
  email varchar(80) not null unique,
  country varchar(25) not null
);

create table users
(
  user_id int primary key auto_increment,
  username varchar(80) not null unique,
  password varchar(80) not null,
  enabled tinyint not null
);

create table roles
(
  role_id int primary key auto_increment,
  role varchar(25) unique not null
);

create table users_roles
(
  user_id int not null,
  role_id int not null,

  primary key (user_id, role_id),

  foreign key (user_id) references users(user_id),
  foreign key (role_id) references roles(role_id)
);