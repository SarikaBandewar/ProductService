ALTER TABLE category
    ADD `description` VARCHAR(255) NULL;

DROP TABLE category_seq;

DROP TABLE product_seq;

ALTER TABLE st_user
    MODIFY user_type INT NULL;