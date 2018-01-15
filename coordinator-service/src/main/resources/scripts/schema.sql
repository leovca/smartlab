# CREATE DATABASE `smartlab_coordinator` /*!40100 DEFAULT CHARACTER SET latin1 */;

CREATE TABLE `smartlab_coordinator`.`vote` (
  `id_vote` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NULL,
  `online_users` INT NULL,
  `external_temperature` FLOAT NULL,
  `internal_temperature` FLOAT NULL,
  `vote` INT NULL,
  PRIMARY KEY (`id_vote`),
  UNIQUE INDEX `id_vote_UNIQUE` (`id_vote` ASC));
