-- Database initialization scripts

-- create database
CREATE DATABASE seckill;

-- use database
USE seckill;

-- use seckill table
CREATE TABLE seckill(
  `seckill_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'goods id',
  `name` VARCHAR(120) NOT NULL COMMENT 'goods name',
  `number` INT NOT NULL COMMENT 'goods number',
  `start_time` TIMESTAMP NOT NULL COMMENT 'kill start',
  `end_time` TIMESTAMP NOT NULL COMMENT 'kill end',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create time',
  PRIMARY KEY (seckill_id),
  KEY idx_start_time(start_time),
  KEY idx_end_time(end_time),
  KEY idx_create_time(create_time)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf-8 COMMENT='seckill table';

-- init data
INSERT INTO
  seckill(name, number, start_time, end_time)
VALUES
  ('$1000 get IPhone6', 100, '2018-8-1 17:00:00', '2018-8-2 17:00:00'),
  ('$1000 get IPad2', 200, '2018-8-1 17:00:00', '2018-8-2 17:00:00'),
  ('$1000 get MI5', 300, '2018-8-1 17:00:00', '2018-8-2 17:00:00'),
  ('$1000 get Honour', 400, '2018-8-1 17:00:00', '2018-8-2 17:00:00');


-- create successful case
-- User login authentication related information
CREATE TABLE success_killed(
  `seckill_id` BIGINT NOT NULL COMMENT 'goods id in success',
  `user_phone` BIGINT NOT NULL COMMENT 'user phone',
  `state` TINYINT NOT NULL DEFAULT -1 COMMENT 'state: -1:invalid, 0:succeed; 1: payed; 2:deliver',
  `create_time` TIMESTAMP NOT NULL COMMENT 'create time' DEFAULT CURRENT_TIMESTAMP COMMENT 'create time',
  PRIMARY KEY(seckill_id, user_phone),
  KEY idx_create_time(create_time)
)ENGINE=InnoDB COMMENT='success_killed table';

-- connect sql console
