DROP database if exists CelebHeights;
create database CelebHeights;

show databases;
USE CelebHeights;



CREATE TABLE Height (
	totalInches int PRIMARY KEY NOT NULL,
    feet int,
    remainingInches int
);

CREATE TABLE Celeb (
	CelebId int PRIMARY KEY auto_increment NOT NULL,
    celebName varchar(255),
	totalInches int,
    totalInchesString varchar(50),
    searchUrl longtext,
    PhotoUrl longtext,
    DateSearched DateTime
    #FOREIGN KEY (totalInches) REFERENCES Height(totalInches)
);

CREATE TABLE Visitor (
	visitorId int PRIMARY KEY auto_increment NOT NULL,
	totalInches int,
    entryTime DateTime
    #FOREIGN KEY (totalInches) REFERENCES Height(totalInches)
);

CREATE TABLE Audits (
	auditID int PRIMARY KEY auto_increment NOT NULL,
    auditType varchar(50),
    auditTime DateTime
);