drop database if exists jandan_pic;

create database jandan_pic;

use jandan_pic;

create table comment (
	`id` int primary key,
    `author` varchar(32),
    `date` long,
    `content` varchar(512),
    `oo` int,
    `xx` int,
    `tucao` int,
    `read` int,
    `score` double
);

create index comment_score_index on comment(score);

create table pic (
    `url` varchar(128),
	`comment_id` int,
    foreign key (`comment_id`) references comment (`id`) on delete cascade
);

-- create table tucao (
-- 	`id` int primary key,
--     `author` varchar(32),
--     `content` varchar(512),
--     `date` long,
--     `oo` int,
--     `xx` int,
-- 	`is_hot` boolean,
--     `comment_id` int,
--     foreign key (`comment_id`) references comment (`id`) on delete cascade
-- );