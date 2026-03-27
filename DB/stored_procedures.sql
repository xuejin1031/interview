-- Stored Procedures for Social Media Platform

USE social_media;

DELIMITER //

-- Procedure to register user
CREATE PROCEDURE register_user(IN p_phone VARCHAR(15), IN p_password VARCHAR(255))
BEGIN
    INSERT INTO users (phone_number, password) VALUES (p_phone, p_password);
END //

-- Procedure to login (check credentials)
CREATE PROCEDURE login_user(IN p_phone VARCHAR(15), IN p_password VARCHAR(255))
BEGIN
    SELECT id FROM users WHERE phone_number = p_phone AND password = p_password;
END //

-- Procedure to create post
CREATE PROCEDURE create_post(IN p_user_id BIGINT, IN p_content TEXT)
BEGIN
    INSERT INTO posts (user_id, content) VALUES (p_user_id, p_content);
END //

-- Procedure to get all posts
CREATE PROCEDURE get_all_posts()
BEGIN
    SELECT p.id, p.content, p.created_at, p.updated_at, u.phone_number
    FROM posts p
    JOIN users u ON p.user_id = u.id
    ORDER BY p.created_at DESC;
END //

-- Procedure to update post
CREATE PROCEDURE update_post(IN p_id BIGINT, IN p_content TEXT)
BEGIN
    UPDATE posts SET content = p_content, updated_at = CURRENT_TIMESTAMP WHERE id = p_id;
END //

-- Procedure to delete post
CREATE PROCEDURE delete_post(IN p_id BIGINT)
BEGIN
    DELETE FROM posts WHERE id = p_id;
END //

-- Procedure to create comment
CREATE PROCEDURE create_comment(IN p_post_id BIGINT, IN p_user_id BIGINT, IN p_content TEXT)
BEGIN
    INSERT INTO comments (post_id, user_id, content) VALUES (p_post_id, p_user_id, p_content);
END //

DELIMITER ;