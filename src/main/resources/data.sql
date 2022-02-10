
insert into users (username, password, enabled) values
('admin', '$2b$10$jEDw4HHKDe75.mM47p4siui1XZrdGkf7Y0.oqKLY.Wt5TjIwuhByq', true, true),
('user', '$2b$10$UmpwL1ImYyVTy1eofo8t9.ecIFzyyCJsML8y0k1UJtMWG2W1hJ3T6', true);

-- some comments

-- ROLE_ prefix will be added at runtime
insert into roles (role) values
('ADMIN'),
('USER');

insert into users_roles (user_id, role_id) values
(1, 1), (1, 2),
(2, 2);

insert into customers (first_name, last_name, email, country) values
('first name', 'last name', 'my1.custom_email75@company.com', 'US'),
('first name', 'last name', 'my2.custom_email75@company.com', 'Bangladesh'),
('first name', 'last name', 'my3.custom_email75@company.com', 'India');