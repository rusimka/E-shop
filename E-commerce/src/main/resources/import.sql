INSERT INTO roles(name) VALUES ('ROLE_ADMIN');
INSERT INTO roles(name) VALUES('ROLE_CUSTOMER');
INSERT INTO users(username, email, password) VALUES ('admin', 'admin@armedia.com', '$2a$12$eg14.46S1PyFY.y6fyCaLemgRZ31voIIPjMTnk1ZgcMOHmJQjZrWG');
INSERT INTO users_roles(user_id, roles_id) VALUES('1', '1');
