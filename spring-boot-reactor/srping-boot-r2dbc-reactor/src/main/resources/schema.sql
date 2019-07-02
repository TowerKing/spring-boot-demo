create table t_user (
  id int(11) auto_increment,
  name varchar(30) not null,
  age int(11) not null,
  create_time timestamp not null,
  update_time timestamp not null
  primary key (id)
)