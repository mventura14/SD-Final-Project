-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema skillvilladb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `skillvilladb` ;

-- -----------------------------------------------------
-- Schema skillvilladb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `skillvilladb` DEFAULT CHARACTER SET utf8 ;
USE `skillvilladb` ;

-- -----------------------------------------------------
-- Table `location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `location` ;

CREATE TABLE IF NOT EXISTS `location` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `address` VARCHAR(300) NULL,
  `city` VARCHAR(50) NULL,
  `state` VARCHAR(50) NULL,
  `zipcode` VARCHAR(10) NULL,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(200) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `role` VARCHAR(45) NULL,
  `created_at` DATETIME NULL,
  `updated_at` DATETIME NULL,
  `enabled` TINYINT NOT NULL,
  `email` VARCHAR(245) NULL,
  `first_name` VARCHAR(245) NULL,
  `last_name` VARCHAR(245) NULL,
  `location_id` INT NULL,
  `image_url` TEXT NULL,
  `bio` TEXT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  INDEX `fk_user_location1_idx` (`location_id` ASC),
  CONSTRAINT `fk_user_location1`
    FOREIGN KEY (`location_id`)
    REFERENCES `location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `community`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `community` ;

CREATE TABLE IF NOT EXISTS `community` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL,
  `updated_at` DATETIME NULL,
  `enabled` TINYINT NULL,
  `description` TEXT NULL,
  `location_id` INT NULL,
  `discord_url` VARCHAR(2000) NULL,
  `image_url` VARCHAR(2000) NULL,
  `created_at` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_community_location1_idx` (`location_id` ASC),
  CONSTRAINT `fk_community_location1`
    FOREIGN KEY (`location_id`)
    REFERENCES `location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `skill_category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `skill_category` ;

CREATE TABLE IF NOT EXISTS `skill_category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `skill`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `skill` ;

CREATE TABLE IF NOT EXISTS `skill` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL,
  `category_id` INT NOT NULL,
  `description` TEXT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_skill_category1_idx` (`category_id` ASC),
  CONSTRAINT `fk_skill_category1`
    FOREIGN KEY (`category_id`)
    REFERENCES `skill_category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `post_category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `post_category` ;

CREATE TABLE IF NOT EXISTS `post_category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `post`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `post` ;

CREATE TABLE IF NOT EXISTS `post` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `created_at` DATETIME NULL,
  `updated_at` DATETIME NULL,
  `community_id` INT NOT NULL,
  `description` TEXT NULL,
  `enabled` TINYINT NULL,
  `user_id` INT NOT NULL,
  `location_id` INT NULL,
  `post_category_id` INT NOT NULL,
  `image_url` VARCHAR(2000) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_post_community1_idx` (`community_id` ASC),
  INDEX `fk_post_user1_idx` (`user_id` ASC),
  INDEX `fk_post_location1_idx` (`location_id` ASC),
  INDEX `fk_post_post_category1_idx` (`post_category_id` ASC),
  CONSTRAINT `fk_post_community1`
    FOREIGN KEY (`community_id`)
    REFERENCES `community` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_post_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_post_location1`
    FOREIGN KEY (`location_id`)
    REFERENCES `location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_post_post_category1`
    FOREIGN KEY (`post_category_id`)
    REFERENCES `post_category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `comment` ;

CREATE TABLE IF NOT EXISTS `comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `created_at` DATETIME NULL,
  `updated_at` DATETIME NULL,
  `message` TEXT NULL,
  `user_id` INT NOT NULL,
  `post_id` INT NOT NULL,
  `in_reply_to_id` INT NULL,
  INDEX `fk_post_has_user_user1_idx` (`user_id` ASC),
  INDEX `fk_post_has_user_post1_idx` (`post_id` ASC),
  PRIMARY KEY (`id`),
  INDEX `fk_comment_comment1_idx` (`in_reply_to_id` ASC),
  CONSTRAINT `fk_post_has_user_post1`
    FOREIGN KEY (`post_id`)
    REFERENCES `post` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_post_has_user_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_comment1`
    FOREIGN KEY (`in_reply_to_id`)
    REFERENCES `comment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `community_has_skill`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `community_has_skill` ;

CREATE TABLE IF NOT EXISTS `community_has_skill` (
  `community_id` INT NOT NULL,
  `skill_id` INT NOT NULL,
  PRIMARY KEY (`community_id`, `skill_id`),
  INDEX `fk_community_has_skill_skill1_idx` (`skill_id` ASC),
  INDEX `fk_community_has_skill_community1_idx` (`community_id` ASC),
  CONSTRAINT `fk_community_has_skill_community1`
    FOREIGN KEY (`community_id`)
    REFERENCES `community` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_community_has_skill_skill1`
    FOREIGN KEY (`skill_id`)
    REFERENCES `skill` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `user_has_skill`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_has_skill` ;

CREATE TABLE IF NOT EXISTS `user_has_skill` (
  `user_id` INT NOT NULL,
  `skill_id` INT NOT NULL,
  `level` VARCHAR(200) NULL,
  PRIMARY KEY (`user_id`, `skill_id`),
  INDEX `fk_user_has_skill_skill1_idx` (`skill_id` ASC),
  INDEX `fk_user_has_skill_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_skill_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_skill_skill1`
    FOREIGN KEY (`skill_id`)
    REFERENCES `skill` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `user_has_community`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_has_community` ;

CREATE TABLE IF NOT EXISTS `user_has_community` (
  `user_id` INT NOT NULL,
  `community_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `community_id`),
  INDEX `fk_user_has_community_community1_idx` (`community_id` ASC),
  INDEX `fk_user_has_community_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_community_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_community_community1`
    FOREIGN KEY (`community_id`)
    REFERENCES `community` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `community_event`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `community_event` ;

CREATE TABLE IF NOT EXISTS `community_event` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(245) NULL,
  `start_date_time` DATETIME NULL,
  `end_date_time` DATETIME NULL,
  `description` TEXT NULL,
  `image_url` VARCHAR(2000) NULL,
  `enabled` TINYINT NULL,
  `created_at` DATETIME NULL,
  `updated_at` DATETIME NULL,
  `user_id` INT NOT NULL,
  `community_id` INT NOT NULL,
  `location_id` INT NULL,
  `discord_url` VARCHAR(2000) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_community_event_user1_idx` (`user_id` ASC),
  INDEX `fk_community_event_community1_idx` (`community_id` ASC),
  INDEX `fk_community_event_location1_idx` (`location_id` ASC),
  CONSTRAINT `fk_community_event_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_community_event_community1`
    FOREIGN KEY (`community_id`)
    REFERENCES `community` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_community_event_location1`
    FOREIGN KEY (`location_id`)
    REFERENCES `location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_has_community_event`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_has_community_event` ;

CREATE TABLE IF NOT EXISTS `user_has_community_event` (
  `user_id` INT NOT NULL,
  `community_event_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `community_event_id`),
  INDEX `fk_user_has_community_event_community_event1_idx` (`community_event_id` ASC),
  INDEX `fk_user_has_community_event_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_community_event_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_community_event_community_event1`
    FOREIGN KEY (`community_event_id`)
    REFERENCES `community_event` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

SET SQL_MODE = '';
DROP USER IF EXISTS skills@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'skills'@'localhost' IDENTIFIED BY 'skills';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'skills'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `location`
-- -----------------------------------------------------
START TRANSACTION;
USE `skillvilladb`;
INSERT INTO `location` (`id`, `address`, `city`, `state`, `zipcode`) VALUES (1, '123 Right Lane', 'Vienna', 'Virginia', '22181');
INSERT INTO `location` (`id`, `address`, `city`, `state`, `zipcode`) VALUES (2, '435 Mountain Way', 'Denver', 'Colorado', '80203');
INSERT INTO `location` (`id`, `address`, `city`, `state`, `zipcode`) VALUES (3, '237 Cowboy Lane', 'Dallas', 'Texas', '77093');
INSERT INTO `location` (`id`, `address`, `city`, `state`, `zipcode`) VALUES (4, '289 Helicoptor Avenure', 'Moab', 'Utah', '84532');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `skillvilladb`;
INSERT INTO `user` (`id`, `username`, `password`, `role`, `created_at`, `updated_at`, `enabled`, `email`, `first_name`, `last_name`, `location_id`, `image_url`, `bio`) VALUES (1, 'test', '$2a$10$3dRffI/xMcwUnKkgA.s5T.AlBB3lgwBJleReRBPSE0ihTpgiKzC5q', 'standard', NULL, NULL, 1, 'tester@me.com', 'Alexander', 'Price', 1, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcStqe-2uM_Z2VQbUXqtoFjGnY53CT9ZBQv5-WDTz_lLpXrDEvWz_EJ_AZto5XeZcWKGB5A&usqp=CAU', NULL);
INSERT INTO `user` (`id`, `username`, `password`, `role`, `created_at`, `updated_at`, `enabled`, `email`, `first_name`, `last_name`, `location_id`, `image_url`, `bio`) VALUES (2, 'PixelPulse', '$2a$10$3dRffI/xMcwUnKkgA.s5T.AlBB3lgwBJleReRBPSE0ihTpgiKzC5q', 'standard', NULL, NULL, 1, 'pixelprincess@email.com', 'Angie', 'Park', 1, 'https://wallpapers.com/images/hd/aesthetic-profile-picture-bz0zswmqkcfm45ik.jpg', NULL);
INSERT INTO `user` (`id`, `username`, `password`, `role`, `created_at`, `updated_at`, `enabled`, `email`, `first_name`, `last_name`, `location_id`, `image_url`, `bio`) VALUES (3, 'NovaNinja', '$2a$10$3dRffI/xMcwUnKkgA.s5T.AlBB3lgwBJleReRBPSE0ihTpgiKzC5q', 'standard', NULL, NULL, 1, 'ninja1234@mail.com', 'Josh', 'Keller', 1, 'https://i.pinimg.com/originals/0b/1e/82/0b1e82b05b5ff03293f1149b5769ae85.png', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `community`
-- -----------------------------------------------------
START TRANSACTION;
USE `skillvilladb`;
INSERT INTO `community` (`id`, `name`, `updated_at`, `enabled`, `description`, `location_id`, `discord_url`, `image_url`, `created_at`) VALUES (1, 'Denver Women Over 30 Snowboarders', '1000-01-01', 1, '', 1, '', 'https://images.squarespace-cdn.com/content/v1/59a219fb914e6bc2a73bd7db/1591380870287-JFHDFYKUQH5HA89OD7QP/BTBounds-Boreal-Melissa-Riitano-Nirvana-Ortanez.jpg', '2020-10-01');
INSERT INTO `community` (`id`, `name`, `updated_at`, `enabled`, `description`, `location_id`, `discord_url`, `image_url`, `created_at`) VALUES (2, 'Dallas Tech Enthusiasts', '1000-01-01', 1, NULL, 1, NULL, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSkAkO2moEA5NifgZbbzV87ASCGm1YPoSU0VA&s', '1000-01-01');
INSERT INTO `community` (`id`, `name`, `updated_at`, `enabled`, `description`, `location_id`, `discord_url`, `image_url`, `created_at`) VALUES (3, 'Moab Mechanics', '1000-01-01', 1, NULL, 1, NULL, 'https://images.pexels.com/photos/2244746/pexels-photo-2244746.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500', '1000-01-01');

COMMIT;


-- -----------------------------------------------------
-- Data for table `skill_category`
-- -----------------------------------------------------
START TRANSACTION;
USE `skillvilladb`;
INSERT INTO `skill_category` (`id`, `name`) VALUES (1, 'Automotive');
INSERT INTO `skill_category` (`id`, `name`) VALUES (2, 'Culinary');
INSERT INTO `skill_category` (`id`, `name`) VALUES (3, 'Business');
INSERT INTO `skill_category` (`id`, `name`) VALUES (4, 'Art');
INSERT INTO `skill_category` (`id`, `name`) VALUES (5, 'Craftsmanship');
INSERT INTO `skill_category` (`id`, `name`) VALUES (6, 'Martial Arts');
INSERT INTO `skill_category` (`id`, `name`) VALUES (7, 'Health and Wellness');
INSERT INTO `skill_category` (`id`, `name`) VALUES (8, 'Mechanical');
INSERT INTO `skill_category` (`id`, `name`) VALUES (9, 'Dance');
INSERT INTO `skill_category` (`id`, `name`) VALUES (10, 'Language');
INSERT INTO `skill_category` (`id`, `name`) VALUES (11, 'Gardening');
INSERT INTO `skill_category` (`id`, `name`) VALUES (12, 'Creative');
INSERT INTO `skill_category` (`id`, `name`) VALUES (13, 'Technical');
INSERT INTO `skill_category` (`id`, `name`) VALUES (14, 'Academic');
INSERT INTO `skill_category` (`id`, `name`) VALUES (15, 'Team Sports');
INSERT INTO `skill_category` (`id`, `name`) VALUES (16, 'Individual Sports');
INSERT INTO `skill_category` (`id`, `name`) VALUES (17, 'Racquet Sports');
INSERT INTO `skill_category` (`id`, `name`) VALUES (18, 'Water Sports');
INSERT INTO `skill_category` (`id`, `name`) VALUES (19, 'Extreme Sports');
INSERT INTO `skill_category` (`id`, `name`) VALUES (20, 'Endurance Sports');
INSERT INTO `skill_category` (`id`, `name`) VALUES (21, 'Adventure Sports');
INSERT INTO `skill_category` (`id`, `name`) VALUES (22, 'Equestrian Sports');
INSERT INTO `skill_category` (`id`, `name`) VALUES (23, 'Pets');
INSERT INTO `skill_category` (`id`, `name`) VALUES (24, 'Entertaining');
INSERT INTO `skill_category` (`id`, `name`) VALUES (25, 'Leisure Activities');

COMMIT;


-- -----------------------------------------------------
-- Data for table `skill`
-- -----------------------------------------------------
START TRANSACTION;
USE `skillvilladb`;
INSERT INTO `skill` (`id`, `name`, `category_id`, `description`) VALUES (1, 'Baking', 2, NULL);
INSERT INTO `skill` (`id`, `name`, `category_id`, `description`) VALUES (2, 'Painting', 4, NULL);
INSERT INTO `skill` (`id`, `name`, `category_id`, `description`) VALUES (3, 'Creative Writing', 14, NULL);
INSERT INTO `skill` (`id`, `name`, `category_id`, `description`) VALUES (4, 'Dressage', 22, NULL);
INSERT INTO `skill` (`id`, `name`, `category_id`, `description`) VALUES (5, 'Archery', 16, NULL);
INSERT INTO `skill` (`id`, `name`, `category_id`, `description`) VALUES (6, 'Swimming', 18, NULL);
INSERT INTO `skill` (`id`, `name`, `category_id`, `description`) VALUES (7, 'Boxing', 6, NULL);
INSERT INTO `skill` (`id`, `name`, `category_id`, `description`) VALUES (8, 'Growing Vegetables', 11, NULL);
INSERT INTO `skill` (`id`, `name`, `category_id`, `description`) VALUES (9, 'Knitting', 4, NULL);
INSERT INTO `skill` (`id`, `name`, `category_id`, `description`) VALUES (10, 'Hip Hop Dancing', 9, NULL);
INSERT INTO `skill` (`id`, `name`, `category_id`, `description`) VALUES (11, 'Small Business', 3, NULL);
INSERT INTO `skill` (`id`, `name`, `category_id`, `description`) VALUES (12, 'Dog Training', 23, NULL);
INSERT INTO `skill` (`id`, `name`, `category_id`, `description`) VALUES (13, 'Water Skiing', 18, NULL);
INSERT INTO `skill` (`id`, `name`, `category_id`, `description`) VALUES (14, 'Snowboarding', 19, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `post_category`
-- -----------------------------------------------------
START TRANSACTION;
USE `skillvilladb`;
INSERT INTO `post_category` (`id`, `name`) VALUES (1, 'Culinary');

COMMIT;


-- -----------------------------------------------------
-- Data for table `post`
-- -----------------------------------------------------
START TRANSACTION;
USE `skillvilladb`;
INSERT INTO `post` (`id`, `created_at`, `updated_at`, `community_id`, `description`, `enabled`, `user_id`, `location_id`, `post_category_id`, `image_url`) VALUES (1, '2021-12-05', '2021-12-12', 1, 'Everyone join the denver womans snowboarding community!', 1, 1, 1, 1, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `skillvilladb`;
INSERT INTO `comment` (`id`, `created_at`, `updated_at`, `message`, `user_id`, `post_id`, `in_reply_to_id`) VALUES (1, '2021-11-01', NULL, 'Cant Wait!', 1, 1, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `community_has_skill`
-- -----------------------------------------------------
START TRANSACTION;
USE `skillvilladb`;
INSERT INTO `community_has_skill` (`community_id`, `skill_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_has_skill`
-- -----------------------------------------------------
START TRANSACTION;
USE `skillvilladb`;
INSERT INTO `user_has_skill` (`user_id`, `skill_id`, `level`) VALUES (1, 1, 'Expert');
INSERT INTO `user_has_skill` (`user_id`, `skill_id`, `level`) VALUES (1, 3, 'Intermediate ');
INSERT INTO `user_has_skill` (`user_id`, `skill_id`, `level`) VALUES (1, 9, 'Beginner');
INSERT INTO `user_has_skill` (`user_id`, `skill_id`, `level`) VALUES (1, 7, 'Expert');
INSERT INTO `user_has_skill` (`user_id`, `skill_id`, `level`) VALUES (2, 3, 'Expert');
INSERT INTO `user_has_skill` (`user_id`, `skill_id`, `level`) VALUES (2, 10, 'Beginner');
INSERT INTO `user_has_skill` (`user_id`, `skill_id`, `level`) VALUES (2, 1, 'Expert');
INSERT INTO `user_has_skill` (`user_id`, `skill_id`, `level`) VALUES (2, 5, 'Intermediate');
INSERT INTO `user_has_skill` (`user_id`, `skill_id`, `level`) VALUES (3, 2, 'Expert');
INSERT INTO `user_has_skill` (`user_id`, `skill_id`, `level`) VALUES (3, 7, 'Beginner');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_has_community`
-- -----------------------------------------------------
START TRANSACTION;
USE `skillvilladb`;
INSERT INTO `user_has_community` (`user_id`, `community_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `community_event`
-- -----------------------------------------------------
START TRANSACTION;
USE `skillvilladb`;
INSERT INTO `community_event` (`id`, `title`, `start_date_time`, `end_date_time`, `description`, `image_url`, `enabled`, `created_at`, `updated_at`, `user_id`, `community_id`, `location_id`, `discord_url`) VALUES (1, 'Bowling class with Pro Ted Kaminsky', '2021-05-23', '2021-05-23', 'Come and learn how to strike like a pro with World Champion Ted Kaminsky', NULL, 1, '2021-01-20', '2021-03-12', 1, 1, 1, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_has_community_event`
-- -----------------------------------------------------
START TRANSACTION;
USE `skillvilladb`;
INSERT INTO `user_has_community_event` (`user_id`, `community_event_id`) VALUES (1, 1);

COMMIT;

