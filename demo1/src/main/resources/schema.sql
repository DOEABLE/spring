create table if not exists TTT (
                                   id   int unsigned not null auto_increment primary key,
                                   name varchar(31)  not null
    );
alter table DemoUser modify column age smallint unsigned not null after username;