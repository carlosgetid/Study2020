create database sys_offline_study2020;
use sys_offline_study2020;

create table tb_topic
(
topic_ID int primary key auto_increment not null,
topic_Name varchar(70) not null,
topic_Date timestamp default current_timestamp,
topic_Favorite boolean default false
);

alter table tb_topic auto_increment=1001;

create table tb_category
(
category_ID int primary key auto_increment not null,
category_Selection boolean default false,
category_Name varchar(50) not null,
category_Date timestamp default current_timestamp,
category_Favorite boolean default false
);

alter table tb_category auto_increment=2001;

create table tb_exercise
(
exercise_ID int primary key auto_increment not null,
exercise_Selection boolean default false,
exercise_Text varchar(200) not null,
exercise_Date timestamp default current_timestamp,
exercise_Favorite boolean default false
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