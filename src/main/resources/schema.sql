create table slipp_board_category (
  no bigint not null auto_increment,
  name varchar(255) not null,
  descriptions varchar(255),
  primary key (no)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table slipp_board (
  id bigint not null auto_increment,
  title varchar(32) not null,
  content varchar(255) not null,
  category bigint not null,
  image_url varchar(255),
  is_deleted tinyint(1) not null default 0,
  created_by varchar(32),
  modified_by varchar(32),
  created_date datetime,
  modified_date datetime,

  primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table slipp_board add constraint FK__slipp_board__slipp_board_category
foreign key (category) references slipp_board_category (no);
iedDate;