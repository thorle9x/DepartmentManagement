-- USER -- password: 123456
INSERT INTO USERS (USER_ID, USER_NAME, PASS_WORD, FIRST_NAME, LAST_NAME) VALUES (1, 'username01', '$2a$10$CBAEKCjo19xCMixqMK4iv.YSH07H46mbgiWsE8ACkOAmchB7EYtR6', 'Nguyen', 'A');
INSERT INTO USERS (USER_ID, USER_NAME, PASS_WORD, FIRST_NAME, LAST_NAME) VALUES (2, 'username02', '$2a$10$CBAEKCjo19xCMixqMK4iv.YSH07H46mbgiWsE8ACkOAmchB7EYtR6', 'Pham', 'B');
INSERT INTO USERS (USER_ID, USER_NAME, PASS_WORD, FIRST_NAME, LAST_NAME) VALUES (3, 'username03', '$2a$10$CBAEKCjo19xCMixqMK4iv.YSH07H46mbgiWsE8ACkOAmchB7EYtR6', 'Ly', 'C');

-- USER_ROLES --
INSERT INTO USER_ROLES (USER_ROLE_ID, IS_ACTIVE, ROLE_NAME) VALUES (1, 1, 'ROLE_ADMIN');
INSERT INTO USER_ROLES (USER_ROLE_ID, IS_ACTIVE, ROLE_NAME) VALUES (2, 1, 'ROLE_USER');
INSERT INTO USER_ROLES (USER_ROLE_ID, IS_ACTIVE, ROLE_NAME) VALUES (3, 1, 'ROLE_GUEST');

-- ASSIGNED_USER_ROLES --
INSERT INTO ASSIGNED_USER_ROLES (ASSIGNED_USER_ROLE_ID, IS_ACTIVE, USER_ID, USER_ROLE_ID) VALUES (1, 1, 1, 1);
INSERT INTO ASSIGNED_USER_ROLES (ASSIGNED_USER_ROLE_ID, IS_ACTIVE, USER_ID, USER_ROLE_ID) VALUES (2, 1, 1, 2);
INSERT INTO ASSIGNED_USER_ROLES (ASSIGNED_USER_ROLE_ID, IS_ACTIVE, USER_ID, USER_ROLE_ID) VALUES (3, 1, 2, 2);
INSERT INTO ASSIGNED_USER_ROLES (ASSIGNED_USER_ROLE_ID, IS_ACTIVE, USER_ID, USER_ROLE_ID) VALUES (4, 1, 3, 3);


