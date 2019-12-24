CREATE TABLE user_role
(
    id        int PRIMARY KEY AUTO_INCREMENT,
    role_name varchar(50) NOT NULL
);

INSERT INTO user_role
VALUES (1, 'ADMIN'),
       (2, 'USER');

CREATE TABLE user
(
    id       BIGINT AUTO_INCREMENT,
    user_name    VARCHAR(255) NOT NULL,
    password VARCHAR(150) NOT NULL,
    salt     varchar(50)  NOT NULL,
    role_id  int          NOT NULL DEFAULT 2,
    CONSTRAINT user_pk PRIMARY KEY (id),
    CONSTRAINT role_fk FOREIGN KEY (role_id)
        REFERENCES user_role (id) ON DELETE RESTRICT
);

ALTER TABLE user
    AUTO_INCREMENT = 100;

INSERT INTO user
VALUES (1, 'tom', '1043ee247aa02eb9db8752488c441faab1987f38a9cc7273ec0af580df390f3c', 'OUJsaKYYAtqtAwtlnsGeLz7XPRA=',
        1),
       (2, 'jerry', '0321b8f47714898d51a7687a95f962e7a0f4cc1d4a58ba9c0e1130f4dd84076a', 'AWMUzF47hUp+P3cUyV+Kc03llys=',
        2);
