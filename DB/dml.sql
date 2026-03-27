-- DML for Social Media Platform

USE social_media;

-- Insert sample users
INSERT INTO users (phone_number, password) VALUES ('1234567890', 'password1');
INSERT INTO users (phone_number, password) VALUES ('0987654321', 'password2');

-- Insert sample posts
INSERT INTO posts (user_id, content) VALUES (1, 'Hello World!');
INSERT INTO posts (user_id, content) VALUES (2, 'This is a test post.');

-- Insert sample comments
INSERT INTO comments (post_id, user_id, content) VALUES (1, 2, 'Nice post!');
INSERT INTO comments (post_id, user_id, content) VALUES (2, 1, 'Thanks for sharing.');