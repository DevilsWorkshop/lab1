-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema sun_aggr
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `sun_aggr` ;

-- -----------------------------------------------------
-- Schema sun_aggr
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sun_aggr` DEFAULT CHARACTER SET utf8 ;
USE `sun_aggr` ;

-- -----------------------------------------------------
-- Table `sun_aggr`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sun_aggr`.`user` ;

CREATE TABLE IF NOT EXISTS `sun_aggr`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NULL,
  `login` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sun_aggr`.`source`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sun_aggr`.`source` ;

CREATE TABLE IF NOT EXISTS `sun_aggr`.`source` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `url` VARCHAR(45) NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_source_user_idx` (`user_id` ASC),
  CONSTRAINT `fk_source_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `sun_aggr`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sun_aggr`.`article`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sun_aggr`.`article` ;

CREATE TABLE IF NOT EXISTS `sun_aggr`.`article` (
  `id` INT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `content` VARCHAR(2000) NULL,
  `status` TINYINT(1) NULL,
  `date` DATETIME NULL,
  `marktype` ENUM('a', 'b') NULL,
  `tag` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sun_aggr`.`filter`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sun_aggr`.`filter` ;

CREATE TABLE IF NOT EXISTS `sun_aggr`.`filter` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `filtercol` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sun_aggr`.`sourcefilter`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sun_aggr`.`sourcefilter` ;

CREATE TABLE IF NOT EXISTS `sun_aggr`.`sourcefilter` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `filter_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_sourcefilter_filter1_idx` (`filter_id` ASC),
  CONSTRAINT `fk_sourcefilter_filter1`
    FOREIGN KEY (`filter_id`)
    REFERENCES `sun_aggr`.`filter` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
