-- Loading Locations
INSERT INTO location (location) VALUES ('Dhaka');
INSERT INTO location (location) VALUES ('Chittagong');
INSERT INTO location (location) VALUES ('Rajshahi');
INSERT INTO location (location) VALUES ('Khulna');
INSERT INTO location (location) VALUES ('Sylhet');


-- Loading Users
INSERT INTO user (email, password, name) VALUES ('bashar@gmail.com', '$2a$10$ce37eHrHbaWnsv12j57Ub.1DqSzybvGtkBIiT8zsTOEYLERHVzcOa', 'Bashar');

INSERT INTO status (details, privacy, location_id, user_id, created_at, updated_at)
VALUES ('Hello.', 'PUBLIC', 1, 1, '2021-07-08 23:46:24.861848', '2021-07-08 23:46:24.861848');
INSERT INTO status (details, privacy, location_id, user_id, created_at, updated_at)
VALUES ('Hello from the other side.', 'PUBLIC', 1, 1, '2021-07-10 23:46:24.861848', '2021-07-10 23:46:24.861848');
INSERT INTO status (details, privacy, location_id, user_id, created_at, updated_at)
VALUES ('This is a private status.', 'PRIVATE', 1, 1, '2021-07-11 23:46:24.861848', '2021-07-11 23:46:24.861848');