create database quizdb;

use quizdb;

create table question(
id INT AUTO_INCREMENT PRIMARY KEY,
question_title VARCHAR(500),
option1 varchar(150),
option2 varchar(150),
option3 varchar(150),
option4 varchar(150),
right_answer varchar(150),
difficulty_level varchar(20));


ALTER TABLE quizdb.question
ADD category varchar(20);