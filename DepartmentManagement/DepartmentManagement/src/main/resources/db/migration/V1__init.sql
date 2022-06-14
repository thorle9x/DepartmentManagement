-- USER -- password: 123456
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `updated_by` varchar(50) DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `pass_word` varchar(4000) NOT NULL,
  `user_name` varchar(20) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ROLE --
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `updated_by` varchar(50) DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `is_active` bigint DEFAULT NULL,
  `role_name` varchar(50) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- USER_ROLE --
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `updated_by` varchar(50) DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `is_active` bigint DEFAULT NULL,
  `role_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
  KEY `FK859n2jvi8ivhui0rl0esws6o` (`user_id`),
  CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- USER data --
INSERT INTO USER (USER_NAME, PASS_WORD, FIRST_NAME, LAST_NAME) VALUES ('username01', '$2a$10$CBAEKCjo19xCMixqMK4iv.YSH07H46mbgiWsE8ACkOAmchB7EYtR6', 'Nguyen', 'A');
INSERT INTO USER (USER_NAME, PASS_WORD, FIRST_NAME, LAST_NAME) VALUES ('username02', '$2a$10$CBAEKCjo19xCMixqMK4iv.YSH07H46mbgiWsE8ACkOAmchB7EYtR6', 'Pham', 'B');
INSERT INTO USER (USER_NAME, PASS_WORD, FIRST_NAME, LAST_NAME) VALUES ('username03', '$2a$10$CBAEKCjo19xCMixqMK4iv.YSH07H46mbgiWsE8ACkOAmchB7EYtR6', 'Ly', 'C');
-- ROLE data --
INSERT INTO ROLE (IS_ACTIVE, ROLE_NAME) VALUES (1, 'ROLE_ADMIN');
INSERT INTO ROLE (IS_ACTIVE, ROLE_NAME) VALUES (1, 'ROLE_USER');
INSERT INTO ROLE (IS_ACTIVE, ROLE_NAME) VALUES (1, 'ROLE_GUEST');
-- USER_ROLE  --
INSERT INTO USER_ROLE (IS_ACTIVE, USER_ID, ROLE_ID) VALUES (1, 1, 1);
INSERT INTO USER_ROLE (IS_ACTIVE, USER_ID, ROLE_ID) VALUES (1, 1, 2);
INSERT INTO USER_ROLE (IS_ACTIVE, USER_ID, ROLE_ID) VALUES (1, 2, 2);
INSERT INTO USER_ROLE (IS_ACTIVE, USER_ID, ROLE_ID) VALUES (1, 3, 3);