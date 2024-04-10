CREATE TABLE category
(
    id         BIGINT NOT NULL,
    created_at datetime NULL,
    updated_at datetime NULL,
    title      VARCHAR(255) NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE category_seq
(
    next_val BIGINT NULL
);

CREATE TABLE jt_instructor
(
    batch_id VARCHAR(255) NULL,
    subject  VARCHAR(255) NULL,
    id       BIGINT NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE jt_student
(
    batch_id VARCHAR(255) NULL,
    id       BIGINT NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE jt_ta
(
    rating_per_session DOUBLE NULL,
    session_id BIGINT NULL,
    id         BIGINT NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE jt_user
(
    id       BIGINT NOT NULL,
    email_id VARCHAR(255) NULL,
    name     VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE mp_instructor
(
    id       BIGINT NOT NULL,
    email_id VARCHAR(255) NULL,
    name     VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    batch_id VARCHAR(255) NULL,
    subject  VARCHAR(255) NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE mp_student
(
    id       BIGINT NOT NULL,
    email_id VARCHAR(255) NULL,
    name     VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    batch_id VARCHAR(255) NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE mp_ta
(
    id         BIGINT NOT NULL,
    email_id   VARCHAR(255) NULL,
    name       VARCHAR(255) NULL,
    password   VARCHAR(255) NULL,
    rating_per_session DOUBLE NULL,
    session_id BIGINT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE product
(
    id            BIGINT NOT NULL,
    created_at    datetime NULL,
    updated_at    datetime NULL,
    `description` VARCHAR(255) NULL,
    image         VARCHAR(255) NULL,
    price DOUBLE NULL,
    title         VARCHAR(255) NULL,
    category_id   BIGINT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE product_seq
(
    next_val BIGINT NULL
);

CREATE TABLE st_user
(
    user_type  INT    NOT NULL,
    id         BIGINT NOT NULL,
    email_id   VARCHAR(255) NULL,
    name       VARCHAR(255) NULL,
    password   VARCHAR(255) NULL,
    batch_id   VARCHAR(255) NULL,
    subject    VARCHAR(255) NULL,
    rating_per_session DOUBLE NULL,
    session_id BIGINT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE tc_instructor
(
    id       BIGINT NOT NULL,
    email_id VARCHAR(255) NULL,
    name     VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    batch_id VARCHAR(255) NULL,
    subject  VARCHAR(255) NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE tc_student
(
    id       BIGINT NOT NULL,
    email_id VARCHAR(255) NULL,
    name     VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    batch_id VARCHAR(255) NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE tc_ta
(
    id         BIGINT NOT NULL,
    email_id   VARCHAR(255) NULL,
    name       VARCHAR(255) NULL,
    password   VARCHAR(255) NULL,
    rating_per_session DOUBLE NULL,
    session_id BIGINT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE tc_user
(
    id       BIGINT NOT NULL,
    email_id VARCHAR(255) NULL,
    name     VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

ALTER TABLE product
    ADD CONSTRAINT FK1mtsbur82frn64de7balymq9s FOREIGN KEY (category_id) REFERENCES category (id) ON DELETE NO ACTION;

CREATE INDEX FK1mtsbur82frn64de7balymq9s ON product (category_id);

ALTER TABLE jt_ta
    ADD CONSTRAINT FK6vwcjedyvspg79nch4upoy2r7 FOREIGN KEY (id) REFERENCES jt_user (id) ON DELETE NO ACTION;

ALTER TABLE jt_student
    ADD CONSTRAINT FKcdd1n57kqnhoqnlgi7tpfhndy FOREIGN KEY (id) REFERENCES jt_user (id) ON DELETE NO ACTION;

ALTER TABLE jt_instructor
    ADD CONSTRAINT FKr77qonwnafpdy64wrhvjx7eyn FOREIGN KEY (id) REFERENCES jt_user (id) ON DELETE NO ACTION;