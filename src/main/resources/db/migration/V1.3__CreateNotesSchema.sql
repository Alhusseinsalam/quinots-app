CREATE TABLE IF NOT EXISTS q_note (
    note_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    title VARCHAR(255),
    description TEXT (65535),
    date_time_created datetime(6),
    tags VARCHAR(255)
);
ALTER TABLE q_note ADD FOREIGN KEY (user_id) REFERENCES q_user(id);