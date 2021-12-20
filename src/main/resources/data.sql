INSERT INTO q_user (id, username, password) VALUES
(1,  'admin', '$2a$12$GYWvOAjBiKEGzITHtoYH5O5Vlhzj/JvIwpLivXD.U3J4ePMTYLb62'),
(2,  'husein', '$2a$12$GYWvOAjBiKEGzITHtoYH5O5Vlhzj/JvIwpLivXD.U3J4ePMTYLb62');
-- ROLES

INSERT INTO q_role (id, role_name, description) VALUES (1, 'ROLE_ADMIN', 'Administrator');
INSERT INTO q_role (id, role_name, description) VALUES (2, 'ROLE_USER', 'User');

INSERT INTO q_user_role(user_id, role_id) VALUES
 (1, 1), -- give admin ROLE_ADMIN
 (2, 2); -- give husein ROLE_USER