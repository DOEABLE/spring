# # truncate table DemoUser;
# insert into DemoUser(username, age) values ('A1', 1);
# insert into DemoUser(username, age) values ('B1', 2);
# insert into DemoUser(username, age)
# select username 'C1', 3
# from DemoUser
# where not exists(select * from DemoUser where username='C1')
#
