create database users;
use users;

create table login
(userid varchar(20) primary key,
password varchar(20),
Email varchar(50),
auth_status int
);

create table user_details
(userid varchar(20) primary key,
First_name varchar(20),
Last_name varchar(20),
Mobile_no varchar(20),
Date_of_Birth varchar(20),
City varchar(50),
Country varchar(20)
);

create table authentication(
userid varchar(20) primary key,
Otp varchar(6)
);


create table forgpass_verification(
userid varchar(20) primary key,
Otp varchar(6)
);

create table elections (
  code char(8) ,
  title varchar(50) ,
  description longtext ,
  unameConducted varchar(20) ,
  noOfCandidates int ,
  start_date varchar(20),
  end_date varchar(20)
);

create table candidates (
  candidateID int NOT NULL AUTO_INCREMENT,
  candidateName varchar(20) ,
  electionCode char(8) ,
  PRIMARY KEY (`candidateID`)
);

create table votes (
  electionCode char(8) ,
  uname varchar(20) ,
  candidateID int 
);

select * from login;
select * from user_details;
select * from authentication;
select * from forgpass_verification;
select * from elections;
select * from candidates;
select * from votes;

/*
truncate table user_details;
truncate table login;
truncate table authentication;
truncate table forgpass_verification;
truncate table elections;
truncate table candidates;
truncate table votes;

show create table user_details;
drop database users;
drop table user_details;
drop table login;
drop table authentication;
drop table elections;
drop table votes;
describe user_details;
show columns from user_details;
*/

/*
select candidates.candidateID,counts from 
(select candidateID from candidates where electionCode="ade6689e") as candidates left join
(select candidateID,count(uname) as counts from votes where electionCode="ade6689e" group by candidateID) as votes
on candidates.candidateID=votes.candidateID;
*/

/*
insert into login (userid,password,Email,auth_status)
values ('root9','root9','a',1);
*/
/*
select max(counts) from
(select count(uname) as counts from votes where electionCode="ade6689e" group by candidateID) as votes;

select votes.candidateID,candidateName from (select candidateID,count(uname) as counts from votes where electionCode="ade6689e" group by candidateID) as votes join candidates on candidates.candidateID=votes.candidateID where counts=5;

insert into votes (electionCode,uname,candidateID) 
values ('ade6689e','ro5',3);

select * from user_details where userid="admin";
*/