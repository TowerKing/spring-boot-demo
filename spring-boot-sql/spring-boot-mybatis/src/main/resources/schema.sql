create table t_user (
  id int(11) not null auto_increment,
  name varchar(20),
  create_time timestamp,
  update_time timestamp,
  primary key (id)
);

create table t_student (
  id int(11) not null auto_increment,
  student_no varchar(10) not null,
  student_name varchar(30) not null,
  primary key (id)
);

create table t_page_rows (
  id int(11) not null auto_increment,
  title varchar(10) not null,
  create_time datetime not null,
  update_time datetime not null,
  primary key (id)
);