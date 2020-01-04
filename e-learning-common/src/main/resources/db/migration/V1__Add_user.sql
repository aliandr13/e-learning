CREATE TABLE user_role
(
    id   int PRIMARY KEY,
    role varchar(20) NOT NULL
);

INSERT INTO user_role(id, role)
VALUES (1, 'ADMIN'),
       (2, 'TEACHER'),
       (3, 'STUDENT');

CREATE TABLE user
(
    id        BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_name VARCHAR(255) NOT NULL UNIQUE,
    password  VARCHAR(150) NOT NULL,
    salt      varchar(50)  NOT NULL,
    role_id   int          NOT NULL DEFAULT 3,
    CONSTRAINT role_fk FOREIGN KEY (role_id)
        REFERENCES user_role (id) ON DELETE RESTRICT
);

ALTER TABLE user
    AUTO_INCREMENT = 100;

INSERT INTO user (id, user_name, password, salt, role_id)
VALUES (1, 'admin', '5c5212a214c634002e7f970fa293746fb7d49c7cfc7087edecd4c32402c06007', 'SEZhwD0GPt9W099OZkx2y9kZzJc=',
        1),
       (2, 'teacher', '7205baa2f9ff6455de144716f620285eff0bc89b4d0607eb3346272b4dc7ac2c',
        '4kSwPVILWcrRre7xItQSUCBzBcM=', 2),
       (3, 'student', '4f8cc2bce35fa155e06ac1d48af1e8ecc614f7392a1868f39dd117d6cdf71762',
        'fWOYaqYJw1j1AgNK9nFBfO0esrU=', 3);
