/*
-- Query: SELECT * FROM todo_list.users
LIMIT 0, 1000

-- Date: 2024-01-23 20:07
*/
INSERT INTO `todo_list.users` (`id`,`email`,`name`,`password`,`username`) VALUES (1,'ana@og.com','Ana Pham','$2a$12$/bjuHnHJmLX6C3QMFJBHT.yTN2dGrxp4.dVlCq1wlyvdKv.KFP/ye','ana');
INSERT INTO `todo_list.users` (`id`,`email`,`name`,`password`,`username`) VALUES (2,'topson@og.com','Topson Topias','$2a$12$/bjuHnHJmLX6C3QMFJBHT.yTN2dGrxp4.dVlCq1wlyvdKv.KFP/ye','topson');


/*
-- Query: SELECT * FROM todo_list.roles
LIMIT 0, 1000

-- Date: 2024-01-23 20:08
*/
INSERT INTO `todo_list.roles` (`id`,`name`) VALUES (1,'ROLE_ADMIN');
INSERT INTO `todo_list.roles` (`id`,`name`) VALUES (2,'ROLE_USER');

/*
-- Query: SELECT * FROM todo_list.users_roles
LIMIT 0, 1000

-- Date: 2024-01-23 20:09
*/
INSERT INTO `todo_list.users_roles` (`user_id`,`role_id`) VALUES (2,1);
INSERT INTO `todo_list.users_roles` (`user_id`,`role_id`) VALUES (1,2);
