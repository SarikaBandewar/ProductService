CREATE TABLE category
(
    id         BIGINT NOT NULL,
    created_at datetime NULL,
    updated_at datetime NULL,
    title      VARCHAR(255) NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE jt_instructor
(
    id       BIGINT NOT NULL,
    batch_id VARCHAR(255) NULL,
    subject  VARCHAR(255) NULL,
    CONSTRAINT pk_jt_instructor PRIMARY KEY (id)
);

CREATE TABLE jt_student
(
    id       BIGINT NOT NULL,
    batch_id VARCHAR(255) NULL,
    CONSTRAINT pk_jt_student PRIMARY KEY (id)
);

CREATE TABLE jt_ta
(
    id         BIGINT NOT NULL,
    session_id BIGINT NULL,
    rating_per_session DOUBLE NULL,
    CONSTRAINT pk_jt_ta PRIMARY KEY (id)
);

CREATE TABLE jt_user
(
    id       BIGINT NOT NULL,
    name     VARCHAR(255) NULL,
    email_id VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    CONSTRAINT pk_jt_user PRIMARY KEY (id)
);

CREATE TABLE mp_instructor
(
    id       BIGINT NOT NULL,
    name     VARCHAR(255) NULL,
    email_id VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    batch_id VARCHAR(255) NULL,
    subject  VARCHAR(255) NULL,
    CONSTRAINT pk_mp_instructor PRIMARY KEY (id)
);

CREATE TABLE mp_student
(
    id       BIGINT NOT NULL,
    name     VARCHAR(255) NULL,
    email_id VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    batch_id VARCHAR(255) NULL,
    CONSTRAINT pk_mp_student PRIMARY KEY (id)
);

CREATE TABLE mp_ta
(
    id         BIGINT NOT NULL,
    name       VARCHAR(255) NULL,
    email_id   VARCHAR(255) NULL,
    password   VARCHAR(255) NULL,
    session_id BIGINT NULL,
    rating_per_session DOUBLE NULL,
    CONSTRAINT pk_mp_ta PRIMARY KEY (id)
);

CREATE TABLE product
(
    id            BIGINT NOT NULL,
    created_at    datetime NULL,
    updated_at    datetime NULL,
    title         VARCHAR(255) NULL,
    price DOUBLE NULL,
    category_id   BIGINT NULL,
    `description` VARCHAR(255) NULL,
    image         VARCHAR(255) NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

CREATE TABLE st_user
(
    id         BIGINT NOT NULL,
    user_type  INT NULL,
    name       VARCHAR(255) NULL,
    email_id   VARCHAR(255) NULL,
    password   VARCHAR(255) NULL,
    batch_id   VARCHAR(255) NULL,
    subject    VARCHAR(255) NULL,
    session_id BIGINT NULL,
    rating_per_session DOUBLE NULL,
    CONSTRAINT pk_st_user PRIMARY KEY (id)
);

CREATE TABLE tc_instructor
(
    id       BIGINT NOT NULL,
    name     VARCHAR(255) NULL,
    email_id VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    batch_id VARCHAR(255) NULL,
    subject  VARCHAR(255) NULL,
    CONSTRAINT pk_tc_instructor PRIMARY KEY (id)
);

CREATE TABLE tc_student
(
    id       BIGINT NOT NULL,
    name     VARCHAR(255) NULL,
    email_id VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    batch_id VARCHAR(255) NULL,
    CONSTRAINT pk_tc_student PRIMARY KEY (id)
);

CREATE TABLE tc_ta
(
    id         BIGINT NOT NULL,
    name       VARCHAR(255) NULL,
    email_id   VARCHAR(255) NULL,
    password   VARCHAR(255) NULL,
    session_id BIGINT NULL,
    rating_per_session DOUBLE NULL,
    CONSTRAINT pk_tc_ta PRIMARY KEY (id)
);

CREATE TABLE tc_user
(
    id       BIGINT NOT NULL,
    name     VARCHAR(255) NULL,
    email_id VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    CONSTRAINT pk_tc_user PRIMARY KEY (id)
);

ALTER TABLE jt_instructor
    ADD CONSTRAINT FK_JT_INSTRUCTOR_ON_ID FOREIGN KEY (id) REFERENCES jt_user (id);

ALTER TABLE jt_student
    ADD CONSTRAINT FK_JT_STUDENT_ON_ID FOREIGN KEY (id) REFERENCES jt_user (id);

ALTER TABLE jt_ta
    ADD CONSTRAINT FK_JT_TA_ON_ID FOREIGN KEY (id) REFERENCES jt_user (id);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (id);