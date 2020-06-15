INSERT INTO role (name) VALUES ('ROLE_ADMIN');
INSERT INTO role (name) VALUES ('ROLE_EMPLOYEE');

-- password: 123456 
INSERT INTO app_user (id, name, email, password)
	VALUES ('49ccab25-7ede-415d-aaa0-04a4571236ed', 'Robson', 'robson.costa@gmail.com', '$2a$10$3Qrx0rv8qSmZ8s3RlD5qE.upleP7.Qzbg5EoIAm62evEkY4c023TK');

INSERT INTO app_user_role (app_user_id, role_name) VALUES ('49ccab25-7ede-415d-aaa0-04a4571236ed', 'ROLE_ADMIN');