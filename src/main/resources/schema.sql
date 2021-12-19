CREATE TABLE IF NOT EXISTS q_role (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  description varchar(100) DEFAULT NULL,
  role_name varchar(100) DEFAULT NULL
);


CREATE TABLE IF NOT EXISTS q_user (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  username varchar(255) NOT NULL,
  password varchar(255) NOT NULL
);


CREATE TABLE IF NOT EXISTS q_user_role (
  user_id BIGINT NOT NULL,
  role_id BIGINT NOT NULL,
  CONSTRAINT FK_Q_USER_ID FOREIGN KEY (user_id) REFERENCES q_user (id),
  CONSTRAINT FK_Q_ROLE_ID FOREIGN KEY (role_id) REFERENCES q_role (id)
);

CREATE TABLE IF NOT EXISTS q_note (
    note_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    title VARCHAR(255),
    description TEXT (65535),
    date_time_created datetime(6),
    tags VARCHAR(255)
);
ALTER TABLE q_note ADD FOREIGN KEY (user_id) REFERENCES q_user(id);