# CREATE DATABASE `smartlab_coordinator` /*!40100 DEFAULT CHARACTER SET latin1 */;

CREATE TABLE `coordinator_vote` (
  `id_vote` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `online_users` int(11) DEFAULT NULL,
  `external_temperature` float DEFAULT NULL,
  `internal_temperature` float DEFAULT NULL,
  `vote` int(11) DEFAULT NULL,
  `created` timestamp NULL DEFAULT NULL,
  `hour` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_vote`),
  UNIQUE KEY `id_vote_UNIQUE` (`id_vote`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;
