ALTER TABLE category
DROP
COLUMN `description`;

ALTER TABLE st_user
    MODIFY user_type INT NULL;