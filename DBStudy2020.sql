create database sys_offline_study2020;
use sys_offline_study2020;

create table tb_topic
(
topic_ID int primary key auto_increment not null,
topic_Name varchar(70) not null,
topic_Date timestamp default current_timestamp,
topic_Favorite boolean default false not null
);

alter table tb_topic auto_increment=1001;

create table tb_category
(
category_ID int primary key auto_increment not null,
category_Name varchar(50) not null,
category_Date timestamp default current_timestamp,
category_Favorite boolean default false not null    
);

alter table tb_category auto_increment=2001;

create table tb_exercise
(
exercise_ID int primary key auto_increment not null,
exercise_Text varchar(200) not null,
exercise_Date timestamp default current_timestamp,
exercise_Favorite boolean default false not null
);

alter table tb_exercise auto_increment=3001;

create table tb_topic_category
(
topic_ID int not null,
category_ID int not null,
primary key (topic_ID, category_ID),
constraint foreign key fk_tb_topic_category_topic_ID(topic_ID) references tb_topic (topic_ID),
constraint foreign key fk_tb_topic_category_category_ID(category_ID) references tb_category (category_ID)
);

create table tb_topic_exercise
(
topic_ID int not null,
exercise_ID int not null,
primary key (topic_ID, exercise_ID),
constraint foreign key fk_tb_topic_exercise_topic_ID(topic_ID) references tb_topic (topic_ID),
constraint foreign key fk_tb_topic_exercise_exercise_ID(exercise_ID) references tb_exercise (exercise_ID)
);

create table tb_category_exercise
(
category_ID int not null,
exercise_ID int not null,
primary key (category_ID, exercise_ID),
constraint foreign key fk_tb_category_exercise_category_ID(category_ID) references tb_category (category_ID),
constraint foreign key fk_tb_category_exercise_exercise_ID(exercise_ID) references tb_exercise (exercise_ID)
);

insert into tb_topic (topic_Name, topic_Favorite) values ('Water',false);
insert into tb_topic (topic_Name, topic_Favorite) values ('Sand',true);
insert into tb_topic (topic_Name, topic_Favorite) values ('Earth',false);
insert into tb_topic (topic_Name, topic_Favorite) values ('Air',true);
insert into tb_topic (topic_Name, topic_Favorite) values ('Ocean',False);

select topic_Name, topic_Date, topic_Favorite from tb_topic;
select topic_Name, date_format(topic_Date, '%d/%m/%Y %H:%i %p'), topic_Favorite from tb_topic;

select * from tb_topic;

insert into tb_category (category_Name, category_Favorite) values ('cat1',false);
insert into tb_category (category_Name, category_Favorite) values ('cat2',true);
insert into tb_category (category_Name, category_Favorite) values ('cat3',false);