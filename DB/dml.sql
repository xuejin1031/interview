-- DML for Social Media Platform

USE social_media;

-- Insert sample users
INSERT INTO users (user_name, email, password, biography) VALUES ('User1', 'user1@example.com', '$2a$10$examplehashedpassword1', 'Hello, I am User1.');
INSERT INTO users (user_name, email, password, biography) VALUES ('User2', 'user2@example.com', '$2a$10$examplehashedpassword2', 'Nice to meet you.');

-- Insert sample posts
INSERT INTO posts (user_id, content) VALUES (1, 'Hello World!');
INSERT INTO posts (user_id, content) VALUES (2, 'This is a test post.');

-- Insert sample comments
INSERT INTO comments (user_id, post_id, content) VALUES (2, 1, 'Nice post!');
INSERT INTO comments (user_id, post_id, content) VALUES (1, 2, 'Thanks for sharing.');