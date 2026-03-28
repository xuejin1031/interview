USE social_media;

DELIMITER //

CREATE PROCEDURE register_user(IN p_user_name VARCHAR(255), IN p_email VARCHAR(255), IN p_password VARCHAR(255), IN p_biography TEXT)
BEGIN
    INSERT INTO users (user_name, email, password, biography) VALUES (p_user_name, p_email, p_password, p_biography);
END //

CREATE PROCEDURE login_user(IN p_email VARCHAR(255), IN p_password VARCHAR(255))
BEGIN
    SELECT user_id FROM users WHERE email = p_email AND password = p_password;
END //

CREATE PROCEDURE create_post(IN p_user_id BIGINT, IN p_content TEXT, IN p_image VARCHAR(500))
BEGIN
    INSERT INTO posts (user_id, content, image) VALUES (p_user_id, p_content, p_image);
END //

CREATE PROCEDURE get_all_posts()
BEGIN
    SELECT p.post_id, p.content, p.image, p.created_at, u.user_name, u.email
    FROM posts p
    JOIN users u ON p.user_id = u.user_id
    ORDER BY p.created_at DESC;
END //

CREATE PROCEDURE update_post(IN p_post_id BIGINT, IN p_content TEXT, IN p_image VARCHAR(500))
BEGIN
    UPDATE posts SET content = p_content, image = p_image WHERE post_id = p_post_id;
END //

CREATE PROCEDURE delete_post(IN p_post_id BIGINT)
BEGIN
    DELETE FROM posts WHERE post_id = p_post_id;
END //

CREATE PROCEDURE create_comment(IN p_post_id BIGINT, IN p_user_id BIGINT, IN p_content TEXT)
BEGIN
    INSERT INTO comments (post_id, user_id, content) VALUES (p_post_id, p_user_id, p_content);
END //

CREATE PROCEDURE get_comments_by_post(IN p_post_id BIGINT)
BEGIN
    SELECT c.comment_id, c.content, c.created_at, u.user_name, u.email
    FROM comments c
    JOIN users u ON c.user_id = u.user_id
    WHERE c.post_id = p_post_id
    ORDER BY c.created_at ASC;
END //

DELIMITER ;